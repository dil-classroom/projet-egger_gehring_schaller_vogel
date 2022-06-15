package utils;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import org.yaml.snakeyaml.Yaml;

/***
 * Cette classe est utilisée pour parser un fichier de contenu, elle sépare le contenu et les métadonnées au délimiteur ---
 * puis elle parse la première partie (avant le séparateur) comme les métadonnées en yaml, et stock le reste du fichier après le délimiteur comme étant le contenu.
 * S'il n'y a pas de --- le fichier entier est retourné comme contenu.
 */
public class ParserContentFile {
    /**
     * parse parse un fichier de contenu en métadonnées et contenu, les métadonnées sont parsées
     * comme du yaml puis chaque clé valeur est mise la map retournée.
     *
     * @param reader le reader ouvert sur un fichier de contenu
     * @return une HashMap contenant les clés-valeurs des métadonnées ainsi que le contenu stocké à
     *     la clé "content"
     */
    public static HashMap<String, Object> parse(BufferedReader reader) {
        if (reader == null) {
            return new HashMap<>();
        }
        StringBuilder sb = new StringBuilder();
        String yaml = null;
        String content = null;
        boolean isYaml = true;
        try {
            String line;

            while ((line = reader.readLine()) != null) {
                // saute les lignes blanches uniquement lorsqu'on parse le yaml
                if (line.isBlank() && isYaml) continue;
                if (line.equals("---")) {
                    isYaml = false;
                    yaml = sb.toString();
                    sb.delete(0, sb.length());
                    continue;
                }
                sb.append(line).append("\n");
            }
            // supprime le dernier retour à la ligne \n
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        content = sb.toString();
        HashMap<String, Object> documentMap = new HashMap<>();
        if (yaml != null) {
            var mapYaml = (HashMap<String, Object>) new Yaml().load(new StringReader(yaml));
            if (mapYaml != null) {
                documentMap.putAll(mapYaml);
            }
        }
        if (!content.trim().isBlank()) {
            documentMap.put("content", content);
        }
        return documentMap;
    }
}
