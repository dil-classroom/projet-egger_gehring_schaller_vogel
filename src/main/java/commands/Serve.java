package commands;

import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "serve", description = "Serve a static site")
public class Serve implements Callable<Integer> {

    @Override public Integer call() {
        return 0;
    }

}