package commands;

import java.util.Properties;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

/***
 * Cette class implemente la commanded version du programme, et affiche la version du programme
 */
@Command(name = "-version", description = "Prints the version of the program")
public class Version implements Callable<Integer> {
    @Override
    public Integer call() {
        try {
            // create a new property file
            final Properties properties = new Properties();
            // load the one we store the version in
            properties.load(
                    Version.class.getClassLoader().getResourceAsStream("project.properties"));
            // get the version
            System.out.println("Statique :\tv" + properties.getProperty("version"));
        } catch (Exception e) {
            System.out.println("Error while reading version number:\n" + e.getMessage());
            return -1;
        }
        return 0;
    }
}
