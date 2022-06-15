package utils;

import java.io.File;
import java.nio.file.Files;

/**
 * Cette classe fournit une seule méthode static : delete qui permet de supprimer un dossier ainsi
 * que son contenu.
 */
public class DirectoryDeleter {
    /**
     * La méthode delete permet de supprimer un dossier ou un fichier désigné par file ainsi que son
     * contenu s'il en a.
     *
     * @param file le fichier / dossier à supprimer
     */
    public static void delete(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    delete(f);
                }
            }
        }
        file.delete();
    }
}
