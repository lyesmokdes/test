package tp1;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tp1.utils.JSONUtil;

/**
 * @author Maxime
 *
 */
public class ValidationTerrain {

	private static final int NB_LOTS_MAXIMUM = 10;
	private static final double MONTANT_MINIMUM = 0.0;

	private static final int TYPE_TERRAIN_MINIMUM = 0;
	private static final int TYPE_TERRAIN_MAXIMUM = 2;

	public static void executerValidationTerrain(JSONObject json) throws TerrainException {

		validerElementsTerrain(json);
	}

	private static void validerElementsTerrain(JSONObject json) throws TerrainException {

		JSONUtil jsonUtil = new JSONUtil(json);

		contientTypeTerrain(jsonUtil);
		validerTypeTerrainMinimum(jsonUtil);
		validerTypeTerrainMaximum(jsonUtil);
		contientPrixMin(jsonUtil);
		validerPrixMinimum(jsonUtil);
		contientPrixMax(jsonUtil);
		validerPrixMaximum(jsonUtil);
		validerNbLotsMinimum(json);
		validerNbLotsMaximum(json);
	}

	private static void contientTypeTerrain(JSONUtil jsonUtil) throws TerrainException {

		if (!jsonUtil.containsKey("type_terrain")) {
			throw new TerrainException("L'élément typeterrain est absent");
		}
	}

	private static void validerTypeTerrainMinimum(JSONUtil jsonUtil) throws TerrainException {

		if (jsonUtil.getInt("type_terrain") < TYPE_TERRAIN_MINIMUM) {
			throw new TerrainException(
					"L'élément typeterrain est inférieur à " 
							+ ValidationTerrain.TYPE_TERRAIN_MINIMUM);
		}
	}

	private static void validerTypeTerrainMaximum(JSONUtil jsonUtil) throws TerrainException {

		if (jsonUtil.getInt("type_terrain") > TYPE_TERRAIN_MAXIMUM) {
			throw new TerrainException(
					"L'élément typeterrain est supérieur à " 
							+ ValidationTerrain.TYPE_TERRAIN_MAXIMUM);
		}
	}

	private static void contientPrixMin(JSONUtil jsonUtil) throws TerrainException {

		if (!jsonUtil.containsKey("prix_m2_min")) {
			throw new TerrainException("L'élément prixmin est absent");
		}
	}

	private static void validerPrixMinimum(JSONUtil jsonUtil) throws TerrainException {

		if (jsonUtil.getDoubleFromCurrency("prix_m2_min") < MONTANT_MINIMUM) {
			throw new TerrainException("L'élément prixmin est inférieur à " 
					+ ValidationTerrain.MONTANT_MINIMUM);
		}
	}

	private static void contientPrixMax(JSONUtil jsonUtil) throws TerrainException {

		if (!jsonUtil.containsKey("prix_m2_max")) {
			throw new TerrainException("L'élément prixmax est absent");
		}
	}

	private static void validerPrixMaximum(JSONUtil jsonUtil) throws TerrainException {

		if (jsonUtil.getDoubleFromCurrency("prix_m2_max") < MONTANT_MINIMUM) {
			throw new TerrainException("L'élément prixmax est inférieur à " 
					+ ValidationTerrain.MONTANT_MINIMUM);
		}
	}

	private static void validerNbLotsMinimum(JSONObject json) throws TerrainException {

		String tableauJSON = extraireTableau(json.toString());

		JSONArray lotissement = JSONArray.fromObject(tableauJSON);

		if (lotissement.isEmpty()) {
			throw new TerrainException("Le terrain ne contient aucun lot");
		}
	}

	private static void validerNbLotsMaximum(JSONObject json) throws TerrainException {

		String tableauJSON = extraireTableau(json.toString());

		JSONArray lotissement = JSONArray.fromObject(tableauJSON);

		if (lotissement.size() > NB_LOTS_MAXIMUM) {
			throw new TerrainException("Le nombre de lots est supérieur à " 
					+ ValidationTerrain.NB_LOTS_MAXIMUM);
		}
	}

	private static String extraireTableau(String chaineJSON) {

		int debut = chaineJSON.indexOf("[");
		int fin = chaineJSON.lastIndexOf("]") + 1;
		String tableauJSON = chaineJSON.substring(debut, fin);

		return tableauJSON;
	}
}
