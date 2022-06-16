package commands;

import java.util.Properties;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

/***
 * Cette class implémente la commanded version du programme et affiche la version du programme
 */
@Command(name = "-version", description = "Affiche la version du programme")
public class Version implements Callable<Integer> {
    @Override
    public Integer call() {
        try {
            // crée un fichier de propriété
            final Properties properties = new Properties();
            // le charge
            properties.load(
                    Version.class.getClassLoader().getResourceAsStream("project.properties"));
            // récupère la version
            System.out.println("Statique :\tv" + properties.getProperty("version"));
        } catch (Exception e) {
            System.out.println(
                    "Error lors de la lecture du numéros de version:\n" + e.getMessage());
            return -1;
        }
        return 0;
    }
}
