/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.utils;

import java.util.Iterator;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author seb
 */
public class JSONUtil {

    private final JSONObject json;

    public JSONUtil(JSONObject json) {
        this.json = json;
    }

    public boolean containsKey(String key) {
        Iterator<String> iter = this.json.keys();

        while (iter.hasNext()) {

            if (iter.next().trim().toLowerCase().contains(key.trim().toLowerCase())) {

                return true;

            }
        }

        return false;
    }

    public String findKey(String key) {
        Iterator<String> iter = this.json.keys();

        while (iter.hasNext()) {
            String originalKey = iter.next();
            if (originalKey.trim().toLowerCase().contains(key.trim().toLowerCase())) {
                return originalKey;
            }
        }
        throw new JSONException("La clé '" + key + "' ne peut être trouvée.");
    }

    public int getInt(String key) {
        return this.json.getInt(this.findKey(key));
    }

    public double getDouble(String key) {
        return this.json.getDouble(this.findKey(key));
    }

    public double getDoubleFromCurrency(String key) {
        String value = this.json.getString(this.findKey(key));
        return NumberUtil.parseDoubleFromCurrency(value);
    }

    public boolean getBoolean(String key) {
        return this.json.getBoolean(this.findKey(key));
    }

    public String getString(String key) {
        return this.json.getString(this.findKey(key));
    }

    public Long getLong(String key) {
        return this.json.getLong(this.findKey(key));
    }

    public JSONArray getJSONArray(String key) {
        return this.json.getJSONArray(this.findKey(key));
    }

    public JSONObject getJSONObject(String key) {
        return this.json.getJSONObject(this.findKey(key));
    }

    public boolean isEmpty() {
        return this.json.isEmpty();
    }

    public int size() {
        return this.json.size();
    }

}
