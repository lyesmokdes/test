/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package labo1;

/**
 *
 * @author Hiba
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class FileWriter {
    public static void WriteStringToFileIn(String json,String filePath, String fileEncoding) throws FileNotFoundException, IOException {
         IOUtils.write(json,new FileOutputStream(filePath), fileEncoding);
    }
}
