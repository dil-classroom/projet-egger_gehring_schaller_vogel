package commands;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import utils.DirectoryDeleter;
import utils.ParserContentFile;
import utils.Watcher;

/**
 * Cette classe implémente la commande build qui construit un site statique, la commande serve
 * s'attend à trouver les fichiers de contenu dans le répertoire content et le résultat de la
 * compilation sera mis dans le répertoire build
 */
@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {
    public static final String templateFolderName = "template";
    public static final String outputFolderName = "build";
    public static final String contentFolderName = "content";

    private TemplateLoader loader = null;
    private Handlebars handlebars = null;
    private Template template = null;

    /**
     * roorDirectory contient le fichier où se situe le site. Il est initialisé automatiquement par
     * picocli
     */
    @CommandLine.Parameters(paramLabel = "cheminDuSite", description = "chemin du site")
    public File rootDirectory;

    @CommandLine.Option(names = {"-w", "--watch"})
    private boolean hotReload = false;

    /**
     * Cette fonction est utilisée pour initialiser un template loader, car on ne sait pas utiliser
     * correctement picoCLI pour ajouter un constructeur c'est pourquoi cette fonction existe et
     * n'est pas dans un constructeur
     */
    private void init() {
        try {
            loader = new FileTemplateLoader(rootDirectory + "/template", ".html");
            handlebars = new Handlebars(loader);
            template = handlebars.compile("layout");
        } catch (IOException ex) {
            Logger.getLogger(Build.class.getName())
                    .log(Level.SEVERE, "Impossible de trouver le layout", ex);
        }
    }

    /***
     * Cette methode détermine si le rootDirectoryToTest est un dossier contenant un site statique initialisé ou non
     * @param rootDirectoryToTest le repertoire du site statique
     * @throws NullPointerException,IllegalArgumentException si l'argument est null ou n'est pas un site statique respectivement
     */
    private void testeDirectoryIsRootSite(File rootDirectoryToTest) {
        if (rootDirectoryToTest == null) {
            throw new NullPointerException("Le nom de dossier ne peut pas être vide");
        }
        if (!rootDirectoryToTest.exists() || !rootDirectoryToTest.isDirectory()) {
            throw new IllegalArgumentException("Le dossier n'existe pas");
        }

        if (!Path.of(rootDirectoryToTest.toString(), contentFolderName).toFile().exists()) {
            throw new IllegalArgumentException(
                    "Ce site n'a pas de contenu ou ce repertoire ne contient pas de site");
        }

        if (!Path.of(rootDirectoryToTest.toString(), templateFolderName, "layout.html")
                .toFile()
                .exists()) {
            throw new IllegalArgumentException("Un fichier template/layout.html est nécessaire");
        }
        if (!Files.isWritable(Path.of(rootDirectoryToTest.toString()))) {
            throw new IllegalArgumentException("Le dossier de sortie est en lecture seule");
        }
    }

    /**
     * Cette methode génère l'html à partir de contenu markdown
     *
     * @param markdown Un string contenant le markdown
     * @return le html résultant de la compilation du markdown avec flexmark
     */
    public static String genHtmlFromMarkdown(String markdown) {
        MutableDataHolder options = new MutableDataSet();
        options.set(
                Parser.EXTENSIONS,
                Arrays.asList(AutolinkExtension.create(), JekyllTagExtension.create()));

        // change soft break to hard break
        options.set(HtmlRenderer.SOFT_BREAK, "<br/>");
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Document document = parser.parse(markdown);
        return renderer.render(document);
    }

    /**
     * Cette méthode génère le fichier html résultat d'un fichier de contenu. Le fichier de layout
     * est utilisé pour construire une page html complète
     *
     * @param contentFile un fichier de contenu ayant le séparateur '---' et le contenu markdown
     * @return le html correspondant à la compilation du fichier de contenu
     */
    public String createHtmlFromContentFile(@NotNull File contentFile) {
        try {
            var contentAndMeta =
                    ParserContentFile.parse(new BufferedReader(new FileReader(contentFile)));

            contentAndMeta.put(
                    "content", genHtmlFromMarkdown((String) contentAndMeta.get("content")));

            return template.apply(contentAndMeta);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la création du résultat " + e.getMessage());
        }
        return null;
    }

    /**
     * cette méthode exécute l'action principale de la classe, ici la commande build qui construit
     * le site statique
     *
     * @return 0 si tout est en ordre, -1 sinon
     * @throws IOException
     */
    @Override
    public Integer call() throws IOException {
        testeDirectoryIsRootSite(rootDirectory);
        init();
        if (hotReload) {
            var w = new Watcher(); // watch only the content folder
            w.register(this, Path.of(rootDirectory.toString(), contentFolderName));
            // register only the first time, we don't want to watch again when we are called back
            hotReload = false;
        }

        File buildDir = Path.of(rootDirectory.getPath(), outputFolderName).toFile();
        if (buildDir.exists() && buildDir.isDirectory()) DirectoryDeleter.delete(buildDir);
        if (!buildDir.mkdir()) {
            Logger.getAnonymousLogger()
                    .log(
                            Level.SEVERE,
                            "Erreur lors de la création du dossier '" + buildDir.getName() + "'");
            return -1;
        }
        var contentFolder = new File(rootDirectory + "/" + contentFolderName);
        if (!contentFolder.isDirectory()) {
            throw new NullPointerException("Le dossier de contenu n'existe pas");
        }

        for (File file : Objects.requireNonNull(contentFolder.listFiles())) {
            if (file.getName().endsWith(".md")) {
                String result = createHtmlFromContentFile(file);
                if (result != null) {
                    File htmlFile =
                            new File(
                                    rootDirectory
                                            + "/"
                                            + outputFolderName
                                            + "/"
                                            + file.getName().replace(".md", ".html"));
                    Files.write(htmlFile.toPath(), result.getBytes());
                }
            } else {
                // copy the file to the build folder using the file name
                Files.copy(
                        file.toPath(),
                        Path.of(buildDir.toString(), file.getName()).toFile().toPath());
            }
        }

        return 0;
    }
}
