package tp1;

import java.util.Date;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import tp1.utils.DateUtil;
import tp1.utils.JSONUtil;

/**
 * @author Maxime
 *
 * Classe Lot: Cette classe représente un lot. Un lot est défini par: Une
 * description de type String nbDroitsPassage de type int. nbServices de type
 * int. superficie de type int. valeurDuLot de type double.
 *
 * Cette classe contient des accesseurs pour récupérer la valeur de chacunes de
 * ces variables d'instances.
 *
 * but: représenter l'ensemble des caractéristiques et des comportements définis
 * sur un objet Lot. date: Le mardi 26 janvier 2016. auteur: Maxime Varin
 * adresse courriel: varin.maxime@courrier.uqam.ca Code permanent: VARM27108903
 * Cours INF2015-40
 */
public class Lot {

    private final String description;
    private int nbDroitsPassage;
    private final int nbServices;
    private final int superficie;
    private Terrain terrain;
    private double valeurDuLot;
    private Date dateMesure;

    /**
     * Constructeur sans argument pour la classe Lot.
     *
     * Cette méthode construit un objet Lot. Ce constructeur initialise toutes
     * les variables d'instances à une valeur par défaut.
     */
    public Lot() {
        this.description = "";
        this.nbDroitsPassage = 0;
        this.nbServices = 0;
        this.superficie = 0;
        this.valeurDuLot = 0.0;
    }

    public Lot(JSONObject json) throws JSONException, LotsException {
        JSONUtil jsonUtil = new JSONUtil(json);

        ValidationLots.executerValidationLots(json);

        this.description = jsonUtil.getString("description");
        this.nbDroitsPassage = jsonUtil.getInt("nombre_droits_passage");
        this.nbServices = jsonUtil.getInt("nombre_services");
        this.superficie = jsonUtil.getInt("superficie");

        this.dateMesure = DateUtil.parse(jsonUtil.getString("date_mesure"));
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instance description.
     *
     * @return valeur de la variable d'instance description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instance
     * nbDroitsPassage.
     *
     * @return: valeur de la variable d'instance nbDroitsPassage
     */
    public int getNbDroitsPassage() {
        return nbDroitsPassage;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instance nbServices.
     *
     * @return: valeur de la variable d'instance nbServices
     */
    public int getNbServices() {
        return nbServices;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instance superficie.
     *
     * @return valeur de la variable d'instance superficie
     */
    public int getSuperficie() {
        return superficie;
    }

    /**
     * Cette méthode retourne la valeur de la variable d'instance valeurDuLot.
     *
     * @return valeur de la variable d'instance valeurDuLot
     */
    public double getValeurDuLot() {
        return valeurDuLot;
    }

    /**
     * Cette méthode modifie la valeur de la variable d'instance valeurDuLot par
     * celle passée en paramètre.
     *
     * @param valeurDuLot la valeur du lot
     *
     */
    public void setValeurDuLot(double valeurDuLot) {
        this.valeurDuLot = valeurDuLot;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    /**
     * Cette méthode retourne l'ensemble des valeurs des variables d'instance de
     * l'objet Lot concaténé dans un String.
     *
     * @return une chaîne de caractères représentant l'objet Lot.
     */
    @Override
    public String toString() {
        return "Lot [description=" + description + ", nbDroitsPassage=" + nbDroitsPassage + ", nbServices=" + nbServices
                + ", superficie=" + superficie + "]";
    }

}
