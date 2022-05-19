// import commands.*;
// import picocli.CommandLine;
// import picocli.CommandLine.Command;
//
// import java.util.concurrent.Callable;
//
// @Command(
//        name = "statique",
//        description = "A brand new static site generator.",
//        subcommands = {Init.class, Clean.class, Build.class, Serve.class, Version.class})
// public class Statique implements Callable<Integer> {
//    public static void main(String... args) {
//        int exitCode = new CommandLine(new Statique()).execute(args);
//        if (exitCode != 0) {
//            System.exit(exitCode);
//        }
//    }
//
//    @Override
//    public Integer call() throws Exception {
//        CommandLine.usage(this, System.out);
//        return 0;
//    }
// }

import java.nio.file.Path;
import java.util.concurrent.Callable;
import utils.Watcher;

public class Statique implements Callable<Integer> {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Watcher watcher = new Watcher();
        var s = new Statique();
        watcher.register(s, Path.of("testWatch"));
        System.out.println("Watching...");
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Callback called !");
        return 0;
    }
}
