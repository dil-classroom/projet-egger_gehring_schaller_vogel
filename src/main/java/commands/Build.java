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

@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {
    public static final String templateFolderName = "template";
    public static final String outputFolderName = "build";
    public static final String contentFolderName = "content";

    private TemplateLoader loader = null;
    private Handlebars handlebars = null;
    private Template template = null;

    @CommandLine.Parameters(paramLabel = "cheminDuSite", description = "chemin du site")
    public File rootDirectory;

    @CommandLine.Option(names = {"-w", "--watch"})
    private boolean hotReload = false;

    /**
     * This function is used to init the template loader, because we don't know how to use picocli
     * and add a constructor it is here and not in the constructor.
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
     * This function test  whether the root directory is a directory containing a static site.
     * @param rootDirectoryToTest the directory to test
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
     * This function generate the html from the markdown content
     *
     * @param markdown the string containing the markdown content
     * @return the html generated from the markdown content using flexmark
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
     * This function generate the standalone html result from a content file It use the layout file
     * to generate the html result
     *
     * @param contentFile the content file having meta info the '---' separator and the markdown
     *     content
     * @return The html result generated from the content file using the layout file
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
