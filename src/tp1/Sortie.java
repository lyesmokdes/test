/**
 *
 */
package tp1;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import tp1.utils.NumberUtil;

/**
 * @author Maxim
 *
 */
public class Sortie {

    /**
     *
     * @param terrain
     * @return
     * @throws JSONException
     * @throws EvaluationFonciereException
     */
    public static JSONObject preparerResultats(Terrain terrain) throws JSONException {
        // Construction de l'objet JSON de sortie
        JSONObject resultats = new JSONObject();

        resultats.accumulate("valeur_fonciere_totale", NumberUtil.formatCurrencyFromDouble(terrain.getTotalTerrain()));
        resultats.accumulate("taxe_scolaire", NumberUtil.formatCurrencyFromDouble(terrain.getTaxeScolaire()));
        resultats.accumulate("taxe_ municipale", NumberUtil.formatCurrencyFromDouble(terrain.getTaxeMunicipale()));

        JSONArray resultatsLots = new JSONArray();
        for (Lot lot : terrain.getLots()) {
            JSONObject resultatLot = new JSONObject();
            resultatLot.accumulate("description", lot.getDescription());
            resultatLot.accumulate("valeur_par_Lot", NumberUtil.formatCurrencyFromDouble(lot.getValeurDuLot()));

            resultatsLots.add(resultatLot);
        }

        resultats.accumulate("Lotissements", resultatsLots);

        return resultats;
    }

}
