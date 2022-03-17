package commands;

import java.io.IOException;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

    @Override public Integer call() throws IOException {
        return 0;
    }

}