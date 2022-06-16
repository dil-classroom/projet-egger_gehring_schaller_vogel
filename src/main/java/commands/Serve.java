package commands;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import utils.Watcher;

/**
 * Cette classe implémente la commande serve du programme, lorsque la commande est lancée, un
 * serveur http et lancé et le site statique est généré
 */
@Command(name = "serve", description = "Serve un site statique")
public class Serve implements Callable<Integer> {

    /***
     * roorDirectory contient le fichier ou se situe le site. Il est initialisé automatiquement par picocli
     */
    @CommandLine.Parameters(
            paramLabel = "rootDirectory",
            description = "répertoire racine root des fichiers markdown")
    public File rootDirectory;

    @CommandLine.Option(names = {"-w", "--watch"})
    private boolean hotReload = false;

    /**
     * cette méthode exécute l'action principale de la classe, ici la commande serve
     *
     * @return 0 si tout est en ordre, -1 sinon
     * @throws IOException
     */
    @Override
    public Integer call() throws IOException {
        // Validation du paramètre
        if (rootDirectory == null) {
            throw new NullPointerException("Le nom de dossier ne peut pas être null");
        }

        if (!rootDirectory.exists()) {
            System.err.println("Erreur: dossier inexistant");
            return -1;
        }

        if (!rootDirectory.isDirectory()) {
            System.err.println("Erreur: rootDirectory doit être un dossier");
            return -1;
        }

        // Vérifie que le dossier contient un fichier index.html
        File indexFile = new File(rootDirectory + "/build/index.html");
        if (!indexFile.exists() || !indexFile.isFile()) {
            System.err.println("Erreur: le fichier /build/index.html est incorrect ou inexistant");
            return -1;
        }
        if (hotReload) {
            var w = new Watcher(); // watch only the content folder
            w.register(this, Path.of(rootDirectory.toString(), "content"));
            // register only the first time, we don't want to watch again when we are called back
            hotReload = false;
        }
        // Lance le serveur
        startServer(4242);

        return 0;
    }

    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private void startServer(int port) throws IOException {
        ThreadPoolExecutor threadPoolExecutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", port), 0);
        server.createContext("/", new MyHttpHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started on port " + port);
    }

    private class MyHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            handleResponse(httpExchange);
        }

        private void handleResponse(HttpExchange httpExchange) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            String htmlResponse =
                    readFile(rootDirectory + "/build/index.html", StandardCharsets.UTF_8);
            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }
}
