package tp1;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import tp1.utils.NumberUtil;

/**
 *
 * @author Maxime Classe: EvaluationFonciere.java
 *
 * Cette classe procède à tout les calculs demandés au programme, à savoir:
 * Calcul de la valeur des lots en fonction du type et de la superficie du
 * terrain. Calcul des droits de passage des lots en fonction du type de
 * terrain. Calcul de la valeur des services des lots en fonction de la
 * superficie du terrain.
 *
 * but: procéder à l'ensemble des calculs demandés au programme. date: Le mardi
 * 26 janvier 2016. auteur: Maxime Varin adresse courriel:
 * varin.maxime@courrier.uqam.ca Code permanent: VARM27108903 Cours INF2015-40
 */
public class EvaluationFonciere {

    private static final double TERRAIN_VALEUR_DE_BASE_FIXE = 733.77;

    private static final double DROITS_PASSAGE_AGRICOLE_RATIO = 0.05;
    private static final double DROITS_PASSAGE_RESIDENTIEL_RATIO = 0.1;
    private static final double DROITS_PASSAGE_COMMERCIAL_RATIO = 0.15;
    private static final double DROITS_PASSAGE_MONTANT_DE_BASE = 500.00;

    private static final double TAXE_MUNICIPALE = 0.025;
    private static final double TAXE_SCOLAIRE = 0.012;

    private static final int NB_SERVICES_DE_BASE = 2;

    public static JSONObject executerCalculs(Terrain terrain)
            throws JSONException, EvaluationFonciereException {

        double valeurTotalTerrain = 0.0;
        for (Lot lot : terrain.getLots()) {

            lot.setValeurDuLot(getValeurTotalLot(lot));

            valeurTotalTerrain += getValeurTotalLot(lot);
        }

        // Ajout d'un montant fixe de 733.77 $ pour couvrir la valeur de base
        valeurTotalTerrain += TERRAIN_VALEUR_DE_BASE_FIXE;
        terrain.setTotalTerrain(NumberUtil.round5cents(valeurTotalTerrain));

        // calcul de la taxe scolaire et de la taxe municipal
        terrain.setTaxeScolaire(NumberUtil.round5cents(valeurTotalTerrain * TAXE_MUNICIPALE));
        terrain.setTaxeMunicipale(NumberUtil.round5cents(valeurTotalTerrain * TAXE_SCOLAIRE));

        // ----------------------------------------------------------------------
        // *** LA SECTION DE GÉNÉRATION DE SORTIE
        JSONObject resultats = new JSONObject();
        resultats = Sortie.preparerResultats(terrain);

        return resultats;
    }

    private static double calculerDroitsPassage(Lot lot) throws EvaluationFonciereException {
        Terrain terrain = lot.getTerrain();
        double droitPassage = 0.0;

        // calcul du montant des droits de passage en fonction du type de
        // terrain
        switch (terrain.getTypeTerrain()) {
            case 0:
                droitPassage = DROITS_PASSAGE_MONTANT_DE_BASE
                        - (verPositif(lot.getNbDroitsPassage()) * (DROITS_PASSAGE_AGRICOLE_RATIO * calculerValeurLot(lot)));
                break;
            case 1:
                droitPassage = DROITS_PASSAGE_MONTANT_DE_BASE - (verPositif(lot.getNbDroitsPassage())
                        * (DROITS_PASSAGE_RESIDENTIEL_RATIO * calculerValeurLot(lot)));
                break;
            case 2:
                droitPassage = DROITS_PASSAGE_MONTANT_DE_BASE - (verPositif(lot.getNbDroitsPassage())
                        * (DROITS_PASSAGE_COMMERCIAL_RATIO * calculerValeurLot(lot)));
                break;
            default:
                throw new EvaluationFonciereException("Type terrain invalide.");

        }

        return droitPassage;
    }

    /**
     * Cette méthode vérifie que le nombre de Droits de passage est un chiffre
     * positif. Elle retourne 0 lorsque le nombre entré est négatif.
     *
     * @param DroitsPassage: le nombre de droits de passage sur ce lot
     * @return: le nombre de passages ajusté au besoin.
     */
    public static int verPositif(int DroitsPassage) {
        int nbPassages;
        if (DroitsPassage < 0) {
            nbPassages = 0;
        } else {
            nbPassages = DroitsPassage;
        }

        return nbPassages;
    }

    private static double calculerService(Lot lot) throws EvaluationFonciereException {
        Terrain terrain = lot.getTerrain();
        double montantService;

        /*
		 * calcul du montant des services d'un lot en fonction de la superficie
		 * du lot et du type de terrain
         */
        switch (terrain.getTypeTerrain()) {
            case 0:
                montantService = 0.0;
                break;
            case 1:
                if (lot.getSuperficie() <= 500) {
                    montantService = 0.0;
                } else if (lot.getSuperficie() > 500 && lot.getSuperficie() <= 10000) {
                    montantService = 500.0 * (lot.getNbServices() + NB_SERVICES_DE_BASE);
                } else {
                    montantService = 10000.0 * (lot.getNbServices() + NB_SERVICES_DE_BASE);
                }
                break;
            case 2:
                if (lot.getSuperficie() <= 500) {
                    montantService = 500.0 * (lot.getNbServices() + NB_SERVICES_DE_BASE);
                } else {
                    montantService = 1500.0 * (lot.getNbServices() + NB_SERVICES_DE_BASE);
                }

                montantService = Math.min(5000, montantService);

                break;
            default:
                throw new EvaluationFonciereException("Type terrain invalide.");

        }

        return montantService;
    }

    private static double calculerValeurLot(Lot lot) throws EvaluationFonciereException {

        double valeurLot = 0.0;
        Terrain terrain = lot.getTerrain();
        // calcul de la valeur du lot en fonction du type de terrain
        switch (terrain.getTypeTerrain()) {
            case 0:
                valeurLot = terrain.getPrixM2Minimum() * lot.getSuperficie();
                break;
            case 1:
                valeurLot = getPrixMoyenM2(terrain) * lot.getSuperficie();
                break;
            case 2:
                valeurLot = terrain.getPrixM2Maximum() * lot.getSuperficie();
                break;
            default:
                throw new EvaluationFonciereException("Type terrain invalide.");

        }

        return valeurLot;
    }

    private static double getPrixMoyenM2(Terrain terrain) {
        return (terrain.getPrixM2Minimum() + terrain.getPrixM2Maximum()) / 2;
    }

    private static double getValeurTotalLot(Lot lot) throws EvaluationFonciereException {
        return calculerValeurLot(lot) + calculerService(lot) + calculerDroitsPassage(lot);
    }
}
