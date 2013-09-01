package Controller;

import Model.LaskinModel;
import View.Kayttoliittyma;

/**
 * Controllerin tehtävänä on hoitaa ja validoida liikenne käyttöliittymän ja
 * itse laskimen välillä
 *
 */
public class Controller {

    private LaskinModel laskin;
    private Kayttoliittyma kayttoliittyma;

    public Controller() {
        laskin = new LaskinModel();
        kayttoliittyma = new Kayttoliittyma(this);

    }

    public Controller(LaskinModel laskin, Kayttoliittyma kayttoliittyma) {
        this.laskin = laskin;
        this.kayttoliittyma = kayttoliittyma;

    }

    /**
     * Ohjelma käynnistetään controllerin käskyllä run().
     */
    public void run() {
        kayttoliittyma.run();
    }

    /**
     * Käyttöliittymä antaa parametrit controllerille tämän metodin kautta kun
     * halutaan tehdä laskutoimituksia. UNDER CONSTRUCTION.
     *
     * @param matriisiA Käyttöliittymän matriisi A
     * @param matriisiB Käyttöliittymän matriisi B
     */
    public String laske(String matriisiA, String matriisiB, String laskutoimitus, String skalaari) throws Exception {
        int numero = Integer.parseInt(laskutoimitus);
        double[][] palautettava;
        if (numero == 1) {
            palautettava = laskin.kerroSkalaarilla(luoMatriisi(matriisiA.split("\\r?\\n")), Integer.parseInt(skalaari));
        } else if (numero == 4) {
            return laskin.laskeDeterminantti(luoMatriisi(matriisiA.split("\\r?\\n"))) + "";
        } else if (numero == 2) {
            palautettava = laskin.laskeYhteen(luoMatriisi(matriisiA.split("\\r?\\n")), luoMatriisi(matriisiB.split("\\r?\\n")));
        } else if (numero == 3) {
            palautettava = laskin.kerroMatriisit(luoMatriisi(matriisiA.split("\\r?\\n")), luoMatriisi(matriisiB.split("\\r?\\n")));
        } else {
            throw new Exception("Valitse laskutoimitus");
        }

        return luoStringMatriisi(palautettava);
    }

    /**
     * Luo liukulukutaulukon pilkuilla erotetusta string jonosta, mikä
     * käyttöliittymältä saadaan.
     *
     * @param matriisiPilkuillaEroteltuna Käyttöliittymältä saatu String
     * taulukko matriisin alkioista.
     * @return liukulukutaulukko String taulukosta.
     */
    public double[][] luoMatriisi(String[] matriisiPilkuillaEroteltuna) throws Exception {
        try {
            poistaEiNumeerisetMerkitLopusta(matriisiPilkuillaEroteltuna);
            String[] ekaRivi = matriisiPilkuillaEroteltuna[0].split(",");

            double[][] matriisi = new double[matriisiPilkuillaEroteltuna.length][ekaRivi.length];
            for (int i = 0; i < matriisi.length; i++) {
                String[] rivi = matriisiPilkuillaEroteltuna[i].split(",");
                for (int j = 0; j < matriisi[0].length; j++) {
                    try {
                        matriisi[i][j] = Double.parseDouble(rivi[j]);
                    } catch (Exception e) {
                        throw new Exception("matriisissa on ylimääräisiä merkkejä");
                    }
                }
            }
            return matriisi;
        } catch (Exception e) {
            throw new Exception("Matriisi(it) on tyhjä(/tyhjiä)");
        }
    }

    /**
     * Poistaa jokaisen taulukon Stringin lopusta ei-numeeriset merkit.
     *
     * @param matriisiPilkuillaEroteltuna String taulukko, mistä halutaan
     * poistaa ei numeeriset merkit lopusta luoMatriisi metodia varten
     */
    private void poistaEiNumeerisetMerkitLopusta(String[] matriisiPilkuillaEroteltuna) {
        for (int i = 0; i < matriisiPilkuillaEroteltuna.length; i++) {
            String rivi = matriisiPilkuillaEroteltuna[i];
            int viimeinenKirjain = rivi.length() - 1;
            while (!Character.isDigit(rivi.charAt(viimeinenKirjain))) {
                viimeinenKirjain--;
            }
            matriisiPilkuillaEroteltuna[i] = rivi.substring(0, viimeinenKirjain + 1);
        }
    }

    /**
     * Muodostaa String olion matriisista käyttöliittymää varten.
     *
     * @param palautettavaMatriisi
     * @return Matriisi String-muodossa
     */
    private String luoStringMatriisi(double[][] palautettavaMatriisi) {
        String stringMatriisi = "";
        for (int i = 0; i < palautettavaMatriisi.length; i++) {
            for (int j = 0; j < palautettavaMatriisi[0].length; j++) {
                stringMatriisi += palautettavaMatriisi[i][j] + ", ";
            }

            stringMatriisi = stringMatriisi.substring(0, stringMatriisi.length() - 2);
            stringMatriisi += "\n";
        }
        return stringMatriisi;

    }
}
