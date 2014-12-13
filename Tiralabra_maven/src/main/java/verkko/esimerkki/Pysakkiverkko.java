package verkko.esimerkki;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Luokka pysäkkiverkon lukemiseen JSON-datasta
 */
public class Pysakkiverkko {

    /**
     * JSON-muotoiset pysäkit taulukossa
     */
    private PysakkiJSON[] pysakit;
    /**
     * JSON-muotoiset linjat taulukossa
     */
    private LinjaJSON[] linjat;

    /*
     Rakennetaan pysäkkiverkko annetuista tiedoista
     Toimii vain ratikkaverkon kanssa nykyisellään
     */
    /**
     * Lukee JSON-dataa pysäkkiverkosta ja tallentaa sen taulukkoon
     *
     * @param verkkoPolku
     * @param linjaPolku
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

    /**
     * Tiedoston lukeminen merkkijonona
     *
     * @param filePath Tiedoston osoite
     * @return Tiedoston sisältö merkkijonona
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

    /**
     * Lukee JSON-dataa
     *
     * @param filePath Tiedoston osoite
     * @return JSON-taulukko luetusta tiedostosta
     */
    private JsonArray readJSON(String filePath) {

        JsonParser parser = new JsonParser();
        String json = "";

        try {
            json = readFileAsString(filePath);

        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
        }
        // System.out.println(""+json);
        JsonArray arr = parser.parse(json).getAsJsonArray();
        return arr;
    }

}
