
package commands;
import java.io.IOException;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    @Override public Integer call() throws IOException {
        return 0;
    }

}