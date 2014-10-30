package verkko.esimerkki;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



/**
 * Luokka pysäkkiverkon lukemiseen JSON-datasta (Johdatus tekoälyyn)
 */
public class Pysakkiverkko {   
   
    
    private PysakkiJSON[]   pysakit;
    private LinjaJSON[]     linjat;    
          
    /*
    Rakennetaan pysäkkiverkko annetuista tiedoista
    Toimii vain ratikkaverkon kanssa nykyisellään
    */
    public void create(String verkkoPolku, String linjaPolku) {
        JsonArray psArr = readJSON(verkkoPolku);
        Gson gson = new Gson();
        this.pysakit = new PysakkiJSON[psArr.size()];
        for (int i = 0; i < psArr.size(); i++) {
            this.pysakit[i] = gson.fromJson(psArr.get(i), PysakkiJSON.class);
        }
          
        // Luetaan raitiovaunulinjat linjat.json tiedostosta.
        JsonArray lnArr = readJSON(linjaPolku);
        this.linjat = new LinjaJSON[lnArr.size()];

        for (int i = 0; i < lnArr.size(); i++) {
            this.linjat[i] = gson.fromJson(lnArr.get(i), LinjaJSON.class);
        }

        
    }
    
    // automaattiset metodit : getterit
    public LinjaJSON[] getLinjat() {
        return linjat;
    }

    public PysakkiJSON[] getPysakit() {
        return pysakit;
    }

    /*
    Tiedostojen lukemiseen (JSON)
    */    
    private String readFileAsString(String filePath) {

        File tiedosto = new File(this.getClass().getClassLoader().getResource(filePath).getFile());
        byte[] buffer;
        try {
            buffer = Files.readAllBytes(tiedosto.toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return new String(buffer);
    }

    private JsonArray readJSON(String filePath) {

        JsonParser parser = new JsonParser();
        String json = "";

        json = readFileAsString(filePath);

        JsonArray arr = parser.parse(json).getAsJsonArray();
        return arr;
    }

}
