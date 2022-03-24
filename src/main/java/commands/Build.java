
package commands;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.io.File;

@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "rootDirectory", description = "root directory of markdown files")
    public File rootDirectory;

    @Override public Integer call() throws IOException {
        if(rootDirectory.exists()){
            if(rootDirectory.isDirectory()){
                String[] file = rootDirectory.list();
                assert file != null;
                System.out.println("OKAY");

            }else{
                System.out.println("Error: not a directory");
            }

        }else{
            System.out.println("Error: incorrect directory");
        }


        return 0;
    }

}