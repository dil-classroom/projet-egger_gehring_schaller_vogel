package commands;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

    @Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @Override public Integer call() throws URISyntaxException, IOException {
        return 0;
    }

}