package tp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import tp1.EvaluationFonciere;
import tp1.EvaluationFonciereException;
import tp1.FileReader;
import tp1.FileWriter;
import tp1.Terrain;

/**
 * @author: Sébastien Lang
 * @author: Didier Louboutin INF2015 - TP1
 *
 * LA MÉTHODE MAIN AVEC ERREUR CRÉATION DE FICHIER SORTIE LA MÉTHODE MAIN AVEC
 * ERREUR CRÉATION DE FICHIER SORTIE
 *
 * Classe principale "main" à partir de laquelle le programme s'exécute Exécutez
 * : java -jar Team23.jar {entrée} {sortie}
 *
 * entrée: Chemin du fichier d'entrée compatible JSONObject sortie: Chemin du *
 * fichier de sortie JSON
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String json;
        String jsonEntree;
        String jsonSortie;

        try {

            if (args.length == 2) {

                jsonEntree = args[0];
                jsonSortie = args[1];

                json = FileReader.loadFileIntoString(jsonEntree, "utf-8");
            } else if (args.length == 1) {

                jsonEntree = args[0];
                jsonSortie = "sortie/resultat.json";

                json = FileReader.loadFileIntoString(jsonEntree, "utf-8");
            } else {

                jsonEntree = "catalogue.json";
                jsonSortie = "c:/resultat.json";

                /**
                 * Utilisation du fichier catalogue.json placé dans le fichier
                 * .jar. Regardez dans le répertoire build et dezipper le
                 * fichier .jar pour le voir. De cette façon, il sera plus
                 * facile d'avancer vers le produit final.
                 */
                json = FileReader.loadFileFromJarIntoString(jsonEntree, "utf-8");
            }

            File sortieDir = new File(jsonSortie).getParentFile();
            if (sortieDir != null) {
                if (!sortieDir.exists()) {
                    if (!sortieDir.mkdir()) {
                        throw new IOException("Impossible de créer" + " le répertoire de sortie");
                    }
                }
            }

            JSONObject catalogue = JSONObject.fromObject(json);

            Terrain terrain = new Terrain(catalogue);

            JSONObject resultats = EvaluationFonciere.executerCalculs(terrain);

            System.out.println(resultats);

            /**
             * Écriture des résultats dans un fichier JSON de sortie.
             */
            FileWriter.writeStringIntoFile(resultats.toString(), jsonSortie, "utf-8");

        } catch (EvaluationFonciereException ex) {
            System.err.println("EvaluationFonciereException: " + ex.getClass() + " :: " + ex.getMessage());

        } catch (JSONException ex) {
            System.err.println("JSONException: " + ex.getClass() + " :: " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("FileNotFoundException: " + ex.getClass() + " :: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getClass() + " :: " + ex.getMessage());
        } catch (LotsException lotEx) {
            System.err.println("LotsException: " + lotEx.getClass() + " :: " + lotEx.getMessage());
        } catch (TerrainException terrEx) {
            System.err.println("TerrainException: " + terrEx.getClass() + " :: " + terrEx.getMessage());
        }

        System.out.println("Fin de la génération des résultats");
    }
}
