package tp1;

import net.sf.json.JSONObject;
import tp1.utils.DateUtil;
import tp1.utils.JSONUtil;

/**
 * @author Maxime
 *
 */
public class ValidationLots {

	private static final int DROITS_PASSAGE_MINIMUM = 0;
            private static final int DROITS_PASSAGE_MAXIMUM = 10;
        
	private static final int NB_SERVICES_MINIMUM = 0;
	private static final int NB_SERVICES_MAXIMUM = 5;

	private static final int SUPERFICIE_MINIMUM = 0;
	private static final int SUPERFICIE_MAXIMUM = 50000;

	public static void executerValidationLots(JSONObject json) throws LotsException {

		JSONUtil jsonUtil = new JSONUtil(json);

		validerElementsLot(jsonUtil);
	}

	private static void validerElementsLot(JSONUtil jsonUtil) throws LotsException {

		contientDescription(jsonUtil);
		contientDroitsPassage(jsonUtil);
		validerDroitsPassageMinimum(jsonUtil);
		validerDroitsPassageMaximum(jsonUtil);
		contientServices(jsonUtil);
		validerNbServicesMinimum(jsonUtil);
		validerNbServicesMaximum(jsonUtil);
		contientSuperficie(jsonUtil);
		validerSuperficieMinimum(jsonUtil);
		validerSuperficieMaximum(jsonUtil);
		contientDateMesure(jsonUtil);
		validerFormatDate(jsonUtil);
	}

	private static void contientDescription(JSONUtil jsonUtil) throws LotsException {

		if (!jsonUtil.containsKey("description")) {
			throw new LotsException("L'élément description est absent");
		}
	}

	private static void contientDroitsPassage(JSONUtil jsonUtil) throws LotsException {

		if (!jsonUtil.containsKey("nombre_droits_passage")) {
			throw new LotsException("L'élément nombredroitspassage est absent");
		}
	}

	private static void validerDroitsPassageMinimum(JSONUtil jsonUtil) throws LotsException {

		if (jsonUtil.getInt("nombre_droits_passage") < DROITS_PASSAGE_MINIMUM) {
			throw new LotsException(
					"L'élément nombredroitspassage est inférieur à " 
							+ ValidationLots.DROITS_PASSAGE_MINIMUM);
		}
	}

	private static void validerDroitsPassageMaximum(JSONUtil jsonUtil) throws LotsException {

		if (jsonUtil.getInt("nombre_droits_passage") > DROITS_PASSAGE_MAXIMUM) {
			throw new LotsException(
					"L'élément nombredroitspassage est supérieur à " 
							+ ValidationLots.DROITS_PASSAGE_MAXIMUM);
		}
	}

	private static void contientServices(JSONUtil jsonUtil) throws LotsException {

		if (!jsonUtil.containsKey("nombre_services")) {
			throw new LotsException("L'élément nombreservices est absent");
		}
	}

	private static void validerNbServicesMinimum(JSONUtil jsonUtil) throws LotsException {

		if (jsonUtil.getInt("nombre_services") < NB_SERVICES_MINIMUM) {
			throw new LotsException("L'élément nombreservices est inférieur à " 
					+ ValidationLots.NB_SERVICES_MINIMUM);
		}
	}

	private static void validerNbServicesMaximum(JSONUtil jsonUtil) throws LotsException {

		if (jsonUtil.getInt("nombre_services") > NB_SERVICES_MAXIMUM) {
			throw new LotsException("L'élément nombreservices est supérieur à " 
					+ ValidationLots.NB_SERVICES_MAXIMUM);
		}
	}

	private static void contientSuperficie(JSONUtil jsonUtil) throws LotsException {

		if (!jsonUtil.containsKey("superficie")) {
			throw new LotsException("L'élément superficie est absent");
		}
	}

	private static void validerSuperficieMinimum(JSONUtil jsonUtil) throws LotsException {

		if (jsonUtil.getInt("superficie") < SUPERFICIE_MINIMUM) {
			throw new LotsException("L'élément superficie est inférieur à " 
					+ ValidationLots.SUPERFICIE_MINIMUM);
		}
	}

	private static void validerSuperficieMaximum(JSONUtil jsonUtil) throws LotsException {

		if (jsonUtil.getInt("superficie") > SUPERFICIE_MAXIMUM) {
			throw new LotsException("L'élément superficie est supérieur " 
					+ ValidationLots.SUPERFICIE_MAXIMUM);
		}
	}

	private static void contientDateMesure(JSONUtil jsonUtil) throws LotsException {

		if (!jsonUtil.containsKey("date_mesure")) {
			throw new LotsException("L'élément date mesure est absent");
		}
	}

	private static void validerFormatDate(JSONUtil jsonUtil) throws LotsException {

		if (DateUtil.parse(jsonUtil.getString("date_mesure")) == null) {
			throw new LotsException("L'élément date ne respecte pas la norme ISO8601");
		}
	}

}
