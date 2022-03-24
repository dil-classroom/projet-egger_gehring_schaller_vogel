package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

    @Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @Override
    public Integer call() throws IOException {
        File file = site.toFile();
        delete(file);

        return 0;
    }

    private void delete(File file) throws IOException {
        if (file.isDirectory()) {
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