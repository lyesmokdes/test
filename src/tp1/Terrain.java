package tp1;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import tp1.utils.JSONUtil;

/**
 * @author Maxime
 *
 * Classe Terrain Cette classe représente un terrain. Un terrain est définit
 * par: typeTerrain de type int. prixM2Minimum de type double. prixM2Maximum de
 * type double. nbLot de type int. lot de type Lot[]. totalTerrain de type
 * double. taxeMunicipal de type double. taxeScolaire de type double.
 *
 * but: représenter l'ensemble des caractéristiques et des comportements définit
 * sur un objet Terrain. date: Le mardi 26 janvier 2016. auteur: Maxime Varin
 * adresse courriel: varin.maxime@courrier.uqam.ca Code permanent: VARM27108903
 * Cours INF2015-40
 */
public class Terrain {

    private final int typeTerrain;
    private final double prixM2Minimum;
    private final double prixM2Maximum;

    private List<Lot> lots;

    private double totalTerrain;
    private double taxeMunicipal;
    private double taxeScolaire;

    /**
     * Constructeur sans argument pour la classe Terrain.
     *
     * Cette méthode construit un objet Terrain. Ce constructeur sans argument
     * initialise toutes les variables d'instances à une valeur par défaut.
     *
     */
    public Terrain() {
        this.typeTerrain = 0;
        this.prixM2Minimum = 0.0;
        this.prixM2Maximum = 0.0;
        this.lots = new ArrayList<>();
        this.taxeMunicipal = 0.0;
        this.taxeScolaire = 0.0;
        this.totalTerrain = 0.0;

    }

    public Terrain(JSONObject json) throws JSONException, LotsException, TerrainException {

        JSONUtil jsonUtil = new JSONUtil(json);

        ValidationTerrain.executerValidationTerrain(json);
        this.typeTerrain = jsonUtil.getInt("type_terrain");
        this.prixM2Minimum = jsonUtil.getDoubleFromCurrency("prix_m2_min");
        this.prixM2Maximum = jsonUtil.getDoubleFromCurrency("prix_m2_max");

        this.lots = new ArrayList<>();

        JSONArray lotissement = json.getJSONArray("Lotissements");

        for (int i = 0; i < lotissement.size(); i++) {
            Lot nouveauLot = new Lot(lotissement.getJSONObject(i));
            nouveauLot.setTerrain(this);

            this.lots.add(nouveauLot);
        }
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instances typeTerrain.
     *
     * @return la valeur de la variable d'instances typeTerrain.
     */
    public int getTypeTerrain() {
        return typeTerrain;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instances
     * prixM2Minimum.
     *
     * @return la valeur de la variable d'instances prixM2Minimum.
     *
     */
    public double getPrixM2Minimum() {
        return prixM2Minimum;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instances
     * prixM2Maximum.
     *
     * @return la valeur de la variable d'instances prixM2Maximum.
     *
     */
    public double getPrixM2Maximum() {
        return prixM2Maximum;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instances totalTerrain.
     *
     * @return la valeur de la variable d'instances totalTerrain.
     */
    public double getTotalTerrain() {
        return totalTerrain;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instance taxeScolaire.
     *
     * @return la valeur de la variable d'instance taxeScolaire.
     */
    public double getTaxeScolaire() {
        return taxeScolaire;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instances
     * taxeMunicipale.
     *
     * @return la valeur de la variable d'instances taxeMunicipale.
     */
    public double getTaxeMunicipale() {
        return taxeMunicipal;
    }

    /**
     * Cette méthode modifie la valeur de la variable d'instance totalTerrain
     * par la valeur passée en paramètre.
     *
     * @param totalTerrain la valeur totale du terrain.
     */
    public void setTotalTerrain(double totalTerrain) {
        this.totalTerrain = totalTerrain;
    }

    /**
     * Cette méthode modifie la valeur de la variable d'instance taxeScolaire
     * par la valeur passée en paramètre.
     *
     * @param taxeScolaire la valeur des taxes scolaire.
     */
    public void setTaxeScolaire(double taxeScolaire) {
        this.taxeScolaire = taxeScolaire;
    }

    /**
     * Cette méthode modifie la valeur de la variable d'instance taxeMunicipale
     * par la valeur passée en paramètre.
     *
     * @param taxeMunicipal la valeur des taxes municipales.
     */
    public void setTaxeMunicipale(double taxeMunicipal) {
        this.taxeMunicipal = taxeMunicipal;
    }

    public List<Lot> getLots() {
        return lots;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instance nbLots.
     *
     * @return nbLots nombre de lots.
     */
    public int getNbLot() {
        return this.lots.size();
    }

    /**
     * Cette méthode retourne une description de l'objet terrain sous la forme
     * d'un String.
     *
     * @return un String représentant l'objet Terrain.
     */
    @Override
    public String toString() {
        return "Terrain [typeTerrain=" + typeTerrain + ", prixM2Minimum=" + prixM2Minimum + ", prixM2Maximum="
                + prixM2Maximum + ", nbLot=" + getNbLot() + ", lot=" + this.lots.toString() + "]";
    }
}
