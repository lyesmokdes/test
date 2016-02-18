package tp1;

/**
 * @author Maxime Cette classe écrit une chaîne de caractères dans un fichier
 * JSON.
 *
 * but: écrire une chaîne de caractères dans un fichier. date: Le mardi 26
 * janvier 2016. auteur: Maxime Varin adresse courriel:
 * varin.maxime@courrier.uqam.ca Code permanent: VARM27108903 Cours INF2015-40
 */
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class FileWriter {

    /**
     * Méthode: writeStringIntoFile
     *
     * Cette méthode écrit le contenu de la chaîne de caractère passée en
     * paramètre dans le fichier spécifié en paramètre.
     *
     * @param data: le contenu à écrire.
     * @param filePath: le chemin du fichier dans lequel on veut écrire.
     * @param fileEncoding: l'encodage du fichier dans lequel on veut écrire.
     * @throws FileNotFoundException: Le fichier spécifié est introuvable.
     * @throws IOException Une erreur d'entrée/sortie.
     */
    public static void writeStringIntoFile(String data, String filePath,
            String fileEncoding) throws FileNotFoundException, IOException {
        IOUtils.write(data, new FileOutputStream(filePath),
                fileEncoding);
    }

}
