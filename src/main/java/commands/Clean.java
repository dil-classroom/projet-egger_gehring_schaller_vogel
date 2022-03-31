package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

    @Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @Override
    public Integer call() throws IOException {
        Path path = Paths.get( System.getProperty("user.dir") + site.toString());
        File file = path.toFile();
        delete(file);
        return 0;
    }

    // Test commit signé
    private void delete(File file) throws IOException {
        if (file.isDirectory()) {
            System.out.println("Here");
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    delete(entry);

                }
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }
}