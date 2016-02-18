/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author seb
 */
public class NumberUtil {

    /**
     * Défini le Currency format de l'application
     *
     * @return
     */
    private static DecimalFormat getDecimalFormatInstance() {
        DecimalFormatSymbols symbol = new DecimalFormatSymbols(Locale.CANADA_FRENCH);
        symbol.setDecimalSeparator('.');

        // Format du prix
        DecimalFormat format = new DecimalFormat("#.00 $", symbol);
        format.setMinimumFractionDigits(2);
        format.setCurrency(Currency.getInstance(Locale.CANADA_FRENCH));

        return format;
    }

    /**
     * Arroundir au 5 sous supérieur
     *
     * @param valeur
     * @return
     */
    public static double round5cents(double valeur) {
        return ((Math.ceil(valeur * 20)) / 20.0);
    }

    public static double parseDoubleFromCurrency(String value) {
        try {

            Number num = getDecimalFormatInstance().parse(value);

            return num.doubleValue();
        } catch (ParseException ex) {
            return 0;
        }
    }

    public static String formatCurrencyFromDouble(double value) {
        try {
            return getDecimalFormatInstance().format(value);
        } catch (IllegalArgumentException ex) {
            return "0.00 $";// todo: eval what to do better
        }
    }

}
