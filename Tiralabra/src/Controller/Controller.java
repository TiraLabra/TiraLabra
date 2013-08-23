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
    public String laske(String matriisiA, String matriisiB, int laskutoimitus, String skalaari) throws Exception {
        if (laskutoimitus == 1) {
            laskin.kerroSkalaarilla(luoMatriisi(matriisiA.split("\\r?\\n")), Integer.parseInt(skalaari));
        } else if (laskutoimitus == 2) {
            laskin.laskeYhteen(luoMatriisi(matriisiA.split("\\r?\\n")), luoMatriisi(matriisiB.split("\\r?\\n")));
        } else if (laskutoimitus == 3) {
            laskin.kerroMatriisit(luoMatriisi(matriisiA.split("\\r?\\n")), luoMatriisi(matriisiB.split("\\r?\\n")));
        } else if (laskutoimitus == 4) {
            laskin.laskeDeterminantti(luoMatriisi(matriisiA.split("\\r?\\n")));
        } else {
            throw new Exception("Valitse laskutoimitus");
        }

        return null;

    }

    /**
     * Luo liukulukutaulukon pilkuilla erotetusta string jonosta, mikä
     * käyttöliittymältä saadaan.
     *
     * @param matriisiPilkuillaEroteltuna Käyttöliittymältä saatu String
     * taulukko matriisin alkioista.
     * @return liukulukutaulukko String taulukosta.
     */
    public double[][] luoMatriisi(String[] matriisiPilkuillaEroteltuna) {
        poistaEiNumeerisetMerkitLopusta(matriisiPilkuillaEroteltuna);
        String[] ekaRivi = matriisiPilkuillaEroteltuna[0].split(",");
        double[][] matriisi = new double[matriisiPilkuillaEroteltuna.length][ekaRivi.length];
        for (int i = 0; i < matriisi.length; i++) {
            String[] rivi = matriisiPilkuillaEroteltuna[i].split(",");
            for (int j = 0; j < matriisi[0].length; j++) {
                matriisi[i][j] = Double.parseDouble(rivi[j]);
            }
        }
        return matriisi;
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
            matriisiPilkuillaEroteltuna[i] = rivi.substring(0, viimeinenKirjain);
        }
    }
}
