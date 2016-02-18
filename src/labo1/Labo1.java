package tp1;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EvaluationFonciere {

    public static final double POURCENTAGE_TERRAIN_AGRICOLE = 0.05, POURCENTAGE_TERRAIN_RESIDENTIEL = 0.1,
            POURCENTAGE_TERRAIN_COMMERCIAL = 0.15;

    public static final double montantBase = 500.00;

    public static final int cinqCentM2 = 500, dixMilleM2 = 10000, serviceBase = 2;
    public static final double cinqCentDollars = 500.0, milleDollars = 1000.0, milleCinqCentDollars = 1500.0,
            cinqMilleDollars = 5000.0;

    public static JSONObject executerCalculs(JSONObject catalogue) {
        JSONObject resultats = new JSONObject();
        //resultats.element("Message", "Simple test de résultats");//<- valueures retournées...
        DecimalFormat df = new DecimalFormat("#####.00"); 
        
        //**********************************************************************
        //1. Intanciation des objets Java Terrain/Lot à partir du catalogue dans une structure comme un ArrayList par exemple
        int typeTerrain = catalogue.getInt("type_terrain");
        String temporaire;
        temporaire = catalogue.getString("prix_m2_min");
        double prixMin = Double.parseDouble(temporaire.substring(0, temporaire.length() - 2));
        temporaire = catalogue.getString("prix_m2_max");
        double prixMax = Double.parseDouble(temporaire.substring(0, temporaire.length() - 2));
        
        Terrain terrain = new Terrain(typeTerrain, prixMin, prixMax);

        JSONArray jsonTableau = catalogue.getJSONArray("Lotissements");

        
        //**********************************************************************
        //2. Execution des calculs dans les autres methodes de cette classe en "private static".
        Map evaluationFonciere = new TreeMap();
        
        for (int i = 0; i < jsonTableau.size(); i++) {

            Lot lot = new Lot(jsonTableau.getJSONObject(i).getString("description"),
                    jsonTableau.getJSONObject(i).getInt("nombre_droits_passage"),
                    jsonTableau.getJSONObject(i).getInt(" nombre_services"),
                    jsonTableau.getJSONObject(i).getInt(" Superficie"));

            terrain.ajouterUnLot(lot);
            lot.setValeurDuLot(calculerValeurLot(terrain, i)
                    + calculerService(terrain, i)
                    + calculerDroitPassage(terrain, i, calculerValeurLot(terrain, i)));
            
            evaluationFonciere.put("test_lot_"+i, lot);
            
        }
        
        double totalTerrain = 0;
        for(int i = 0; i < terrain.getNbLot(); i++)  {
            totalTerrain = totalTerrain + terrain.getLot()[i].getValeurDuLot();
        }
        
        terrain.setTotalTerrain(Double.parseDouble(df.format(totalTerrain + Terrain.MONTANT_FIXE_BASE)));
        terrain.setTaxeScolaire(Double.parseDouble(df.format(terrain.getTotalTerrain()* Terrain.POURCENTAGE_TAXE_SCOLAIRE)));
        terrain.setTaxeMunicipale(Double.parseDouble(df.format(terrain.getTotalTerrain()* Terrain.POURCENTAGE_TAXE_MUNICIPAL)));
        
        //**********************************************************************
        //3. Récupération des résultats correctement dans un JSONObject 
        //resultats.element("test-evaluationFonciere", evaluationFonciere);
        
        resultats.accumulate("valeur_fonciere_totale", terrain.getTotalTerrain() + " $");
        resultats.accumulate("taxe_scolaire", terrain.getTaxeScolaiire() + " $");
        resultats.accumulate("taxe_municipale", terrain.getTxeMunicipale() + " $");
        
        JSONArray jsonTab = new JSONArray();
        
        for (int i = 0 ; i < terrain.getNbLot(); i++) {
            JSONObject jsonTmp = new JSONObject();
            jsonTmp.accumulate("description", terrain.getLot()[i].getDescription());
            jsonTmp.accumulate("valeur_par_Lot", df.format(terrain.getLot()[i].getValeurDuLot()) + " $");
            jsonTab.add(jsonTmp);
        }
            
        resultats.accumulate("Lotissements", jsonTab);
    
        
        
        
        
        //4. retourner le JSONObject résultats...
        //*** Il existe d'autre façon de faire mais celle-ci assure encapsulation et seul la methode executerCalculs() est publique tout acceptant et retournat un JSONObject.
        return resultats;

    }

    public static double calculerValeurLot(Terrain terrain, int positionLot) {

        double valeurLot = 0.0;

        double prixMoyenM2 = (terrain.getPrixM2Minimum() + terrain.getPrixM2Maximum()) / 2;

        return valeurLot;

    }

    public static double calculerDroitPassage(Terrain terrain, int positionLot, double valeurLot) {

        // valeur de retour
        double droitPassage = 0.0;

        // calcul du montant des droits de passage en fonction du type de
        // terrain
        if (terrain.getTypeTerrain() == 0) {
            droitPassage = montantBase
                    - (terrain.getLot(positionLot).getNbDroitsPassage() * (POURCENTAGE_TERRAIN_AGRICOLE * valeurLot));
        } else if (terrain.getTypeTerrain() == 1) {
            droitPassage = montantBase
                    - (terrain.getLot(positionLot).getNbDroitsPassage() * (POURCENTAGE_TERRAIN_RESIDENTIEL * valeurLot));
        } else if (terrain.getTypeTerrain() == 2) {
            droitPassage = montantBase
                    - (terrain.getLot(positionLot).getNbDroitsPassage() * (POURCENTAGE_TERRAIN_COMMERCIAL * valeurLot));
        }

        return droitPassage;
    }

    public static double calculerService(Terrain terrain, int positionLot) {

        // valeur de retour
        double montantService = 0.0;

        double montantServiceTemp = 0.0;

        /*
		 * calcul du montant des services d'un lot en fonction de la superficie
		 * du lot et du type de terrain
         */
        if (terrain.getTypeTerrain() == 0) {
            montantServiceTemp = 0.0;
        } else if (terrain.getTypeTerrain() == 1) {
            if (terrain.getLot(positionLot).getSuperficie() <= cinqCentM2) {
                montantServiceTemp = 0.0;
            } else if (terrain.getLot(positionLot).getSuperficie() > cinqCentM2 && terrain.getLot(positionLot).getSuperficie() <= dixMilleM2) {
                montantServiceTemp = cinqCentDollars * (terrain.getLot(positionLot).getNbServices() + serviceBase);
            } else if (terrain.getLot(positionLot).getSuperficie() > dixMilleM2) {
                montantServiceTemp = milleDollars * (terrain.getLot(positionLot).getNbServices() + serviceBase);
            }
        } else if (terrain.getTypeTerrain() == 2) {
            if (terrain.getLot(positionLot).getSuperficie() <= cinqCentM2) {
                montantServiceTemp = cinqCentDollars * (terrain.getLot(positionLot).getNbServices() + serviceBase);
            } else if (terrain.getLot(positionLot).getSuperficie() > cinqCentM2) {
                montantServiceTemp = milleCinqCentDollars * (terrain.getLot(positionLot).getNbServices() + serviceBase);
            }

        }

        // on verifie que le montant des services est inferieur � 50000 $
        if (montantServiceTemp > cinqMilleDollars) {
            montantService = cinqMilleDollars;
        } else {
            montantService = montantServiceTemp;
        }

        return montantService;
    }

}
