package tp1;

/**
 * Cette classe permet de lire un fichier JSON et de retourner le résultat de la
 * lecture dans un String.
 *
 * @author Hiba
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileReader {

    /**
     * Méthode: loadFileIntoString
     *
     * Cette méthode lit un fichier JSON et retourne le résultat de la lecture
     * dans un String.
     *
     * @param filePath: le chemin d'accès du fichier JSON.
     * @param fileEncoding: l'encodage du fichier à lire.
     * @return une chaîne de caractère contenant le contenu du fichier.
     * @throws FileNotFoundException le nom du fichier spécifié est introuvable.
     * @throws IOException une erreur d'entrée/sortie.
     */
    public static String loadFileIntoString(String filePath, String fileEncoding)
            throws FileNotFoundException, IOException {
        return IOUtils.toString(new FileInputStream(filePath), fileEncoding);
    }

    /**
     * Méthode: loadFileFromJarIntoString
     *
     * Cette méthode lit un fichier JAR et retourne le résultat de la lecture
     * dans un String.
     *
     * @param filePath: le chemin d'accès du fichier.
     * @param fileEncoding: l'encodage du fichier à lire.
     * @return une chaîne de caractère contenant le contenu du fichier.
     * @throws FileNotFoundException le nom du fichier spécifié est introuvable.
     * @throws IOException une erreur d'entrée/sortie.
     */
    public static String loadFileFromJarIntoString(String filePath, String fileEncoding)
            throws FileNotFoundException, IOException {

        InputStream in = ClassLoader.getSystemResourceAsStream(filePath);

        return IOUtils.toString(in, fileEncoding);
    }
}
