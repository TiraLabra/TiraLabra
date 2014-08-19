

package tiralabra_maven;
import lista.LinkitettyMatriisiLista;
import java.util.Scanner;
import jama.*;
import java.util.ArrayList;


/**
 * Komennonkasittelija.
 * <P> Luokan tarkoituksena on analysoida mitä käskyjä käyttäjä on antanut, ja 
 * komentaa muita ohjelman osia toimimaan käskyjen perusteella. 
 * 
 * @author risto
 */
public class KomennonKasittelija {
    /**
     * Komento on enum, joka kertoo määrittelee suoritettavan käskyn.
     */
    private Komento komento;
    /**
     * Käskyjono on taulukko, joka sisältää käyttäjän antaman käskyn osat String-muodossa.
     */
    private String[] kaskyjono;
    private Scanner scan;
    private Kayttoliittyma kali;
    private Tiedostonkasittelija tk = new Tiedostonkasittelija();
    /**
     * matriisilista on linkitetty lista, joka sisältää käyttäjän tallentamat matriisit.
     */
    private LinkitettyMatriisiLista matriisilista = new LinkitettyMatriisiLista();
    
    
    /**
     * Komennonkasittelijakonstruktori.
     * 
     * @param scan syötteiden tutkimista varten.
     * @param kali luokka tuntee ohjelman käyttöliittymän voidakseen käyttää tiettyjä 
     * käyttöliittymän metodeja, kuten quit().
     */
    public KomennonKasittelija(Scanner scan, Kayttoliittyma kali) {
        this.scan = scan;
        this.kali = kali;
    }
    
    
    /**
     * Suoritakomento.
     * <P> Metodi tutkii käyttäjän antaman käskyn ja vastaa käskyjen edellyttämien toimenpiteiden
     * suorittamisesta.
     * 
     * 
     * @param kasky on käyttäjän antama merkkijono.
     * @throws Exception mikäli merkkijono ei vastaa mitään komentoa tai on muuten virheellinen, 
     * metodi heittää poikkeuksen.
     */
    public void suoritaKomento(String kasky) throws Exception {
        
        kaskyjono = kasky.split(" ");
        
        // jos käyttäjä antaa vain yhden sanan, se tulkitaan tulostettavaksi matriisin nimeksi
      
        
        
        
        try {
            komento = maaritaKomento(kaskyjono[0]);          
        }
        
        catch (Exception e) {
            throw new Exception();
        }
       
        
        if (komento == Komento.KERRO) {
            suoritaKertolasku();
        }
        
        if (komento == Komento.TIEDOSTO) {
            haeTiedosto();
        }
        
        if (komento == Komento.DETERMINANTTI) {
            suoritaDeterminantti();
        }
        
        if (komento == Komento.MATRIISI) {
            suoritaMatriisi();           
        }
        
        if (komento == Komento.QUIT) {
            kali.quit();
        }
        
        if (komento == Komento.KOMENNOT) {
            kali.tulostaKomennot();
        }
        
        if (komento == Komento.KAANNA) {
            suoritaKaanto();
        }
        
        if (komento == Komento.TULOSTALISTA) {
            matriisilista.tulostaNimet();
        }
        
        if (komento == Komento.TULOSTA) {
            suoritaTulosta();
        }
        
        if (komento == Komento.PLUS) {
            suoritaPlus();
        }
        
        if (komento == Komento.MIINUS){
            suoritaMiinus();
        }
        
        if (komento == Komento.SMONI) {
            suoritaSmoni();
        }
    }

    /**
     * Suoritamatriisi. Metodi pyytää käyttäjältä matriisin arvot ja luo niistä 
     * uuden matriisin, joka talletetaan linkitettyyn listaan.
     * @throws Exception Mikäli käyttäjän antama käsky ei ole korrekti, metodi heittää
     * poikkeuksen.
     */
    public void suoritaMatriisi() throws Exception {
           int m;
           int n;
           String[] ulottuvuudet;
           String matriisinNimi;
           String[] arvot;
           String syote;
           double[][] valimatriisi;
       
               ulottuvuudet = kaskyjono[1].split("x");
               m = Integer.parseInt(ulottuvuudet[0]);
               n = Integer.parseInt(ulottuvuudet[1]);
               matriisinNimi = kaskyjono[2];
               if (matriisilista.onkoNimiKaytossa(matriisinNimi)) {
                    System.out.println("Virhe: " + matriisinNimi + " on jo käytössä, anna käsky uudestaan");
                    return;
                }               
               valimatriisi = kali.keraaMatriisinLuvut(m, n);

           
           Matrix matriisi = new Matrix(valimatriisi);
           matriisilista.lisaa(matriisinNimi, matriisi);
        }
    
    /**
     * Metodi laskee kahden matriisin summan ja tallentaa sen käyttäjän antamalla nimellä.
     */
    public void suoritaPlus() {
        
        Matrix tulosmatriisi = null;
        String tulosmatriisinnimi = kaskyjono[3];
        
        try {
            Matrix[] operandit = haeKaksiOperandia();
            tulosmatriisi = operandit[0].plus(operandit[1]);
            
        }
        
        catch (Exception e) {
            System.out.println("Ainakin toinen antamistasi matriisin nimistä oli virheellinen");
            return;
        }
        
        matriisilista.lisaa(tulosmatriisinnimi, tulosmatriisi);
        
       
    }
    
    /**
     * Metodi tulostaa käyttäjän nimeän matriin arvot.
     */
    public void suoritaTulosta() {
        String tulostettavanNimi = kaskyjono[1];
        try {
            matriisilista.hae(tulostettavanNimi).print(10,5);
        }
        
        catch (Exception e) {
            System.out.println("Antamasi matriisin nimellä ei löytynyt mitään");
            return;
        }
    }
    
    
    /**
     * Hae kaksi operandia. Copy-pasten välttämäseksi metodi hakee kaksipaikkaisen
     * operaation operandit käyttäjän antamasta käskystä ja palauttaa yksiulotteisen taulukon
     * jossa nämä operandit ovat.
     * @return Lista Matrix-olioita.
     * @throws Exception Mikäli käyttäjän antamalla nimellä ei löydy matriiseja, metodi heittää poikkeuksen.
     */
    public Matrix[] haeKaksiOperandia() throws Exception {
        Matrix[] palautettavaLista = new Matrix[2];
        palautettavaLista[0] = matriisilista.hae(kaskyjono[1]);
        palautettavaLista[1] = matriisilista.hae(kaskyjono[2]);
        return palautettavaLista;
        
    }
    
    
    /**
     * Suorita miinus. Metodi laskee kahden matriisin erotuksen ja tallentaa tuloksen
     * working space -listaan käyttäjän antamalla nimellä
     */
    public void suoritaMiinus() {
        Matrix tulosmatriisi = null;
        String tulosmatriisinnimi = kaskyjono[3];
        
        try {
            Matrix[] operandit = haeKaksiOperandia();
            tulosmatriisi = operandit[0].minus(operandit[1]);
            
        }
        
        catch (Exception e) {
            System.out.println("Ainakin toinen antamistasi matriisin nimistä oli virheellinen");
            return;
        }
        
        matriisilista.lisaa(tulosmatriisinnimi, tulosmatriisi);
        
        
    }        
    
       
    /**
     * Determinantti. Metodi laskee käyttäjän antaman matriisin determinantin.
     * 
     * <P> Mikäli käyttäjän haluaa laskea muun kuin neliömatriisin determinantin
     * tai mikäli käyttäjän antaman nimen mukaista matriisia ei löydy, metodi lopettaa
     * suorituksen ja tulostaa virheilmoituksen.
     */
    public void suoritaDeterminantti() {
        String matriisinnimi = kaskyjono[1];
        Matrix laskettava;
        double determinantti;
        
        try { 
            
            laskettava = matriisilista.hae(matriisinnimi);
        }
        
        catch (Exception e) {
            System.out.println("Virheellinen matriisin nimi");
            return;
        }
        try {
            determinantti = laskettava.det();
            System.out.println("Determinantti on " + determinantti);        
        }
        catch (Exception e) {
            System.out.println("Matriisin determinanttia ei voitu laskea");
            return;
        }
        
    }
    
    
    /**
     * Kertolasku. Metodi laskee käyttäjän antamien matriisien tulon ja luo tulosmatriisin
     * käyttäjän antamalla nimellä.
     */
    public void suoritaKertolasku() {
        String tulosmatriisinnimi = kaskyjono[3];
        Matrix tulos = null;
        
        try {
         
            Matrix[] operandit = haeKaksiOperandia();   
            tulos = operandit[0].times(operandit[1]);
        }
        
        catch (Exception e) {
            System.out.println("Matriiseja näillä nimillä ei löydetty");
            return;
        }
        
        matriisilista.lisaa(tulosmatriisinnimi, tulos);
        
    }
    /**
     * Hae tiedosto. Metodi tallentaa käyttäjän antamasta tiedostosta luetun matriisin
     * annetulla nimellä.
     */
    public void haeTiedosto() {
        String matriisinnimi = kaskyjono[1];
        
        try {
            Matrix matriisi = tk.lueTiedosto(kaskyjono[2]);
            matriisilista.lisaa(matriisinnimi, matriisi);
            }
        catch (Exception e) {
            System.out.println("Tiedostoa ei voitu lukea. Tämä voi johtua tiedostopolusta tai tiedoston formaatista");
        }
    }
    
    
    /**
     * Kääntö. Metodi laskee käyttäjän antaman matriisin kääntömatriisin, ja 
     * tallentaa tuloksen luomaansa tulosmatriisiin.
     */
    public void suoritaKaanto() {
        Matrix tulosmatriisi;
        String matriisinnimi = kaskyjono[1];
        String tulosmatriisinnimi = kaskyjono[2];
        try {
            Matrix kaannettava = matriisilista.hae(matriisinnimi);
            tulosmatriisi = kaannettava.inverse();
            matriisilista.lisaa(tulosmatriisinnimi, tulosmatriisi);
        }
        catch (IllegalArgumentException ie) {
            System.out.println("Matriisi ei ole kääntyvä");
        }
        catch (Exception e) {
            System.out.println("Huono syöte");
            return;
        }
    }
    
    /**
     * Suorita sklaarimonikerta. Metodi kertoo matriisin kaikki alkiot jollain reaaliluvulla.
     * Tulos tallennetaan matriisilistaan.
     */
    public void suoritaSmoni() {
        Matrix tulosmatriisi = null;
        String matriisinnimi = kaskyjono[3];
        try {
            Matrix operandi = matriisilista.hae(kaskyjono[2]);
            double skalaari = Double.parseDouble(kaskyjono[1]);
            
            tulosmatriisi = operandi.times(skalaari);
        }
        catch (Exception e) {
            System.out.println("Huono syöte");
            return;
        }
        
        matriisilista.lisaa(matriisinnimi, tulosmatriisi);
    }
  
    /**
     * Määritäkomento. Metodi selvittää minkä komennon käyttäjä on antanut. Metodin
     * tarkoitus on kapseloida merkkijono-operaatioita pois ja mahdollistaa käskyjen
     * käsittely enumin kautta.
     * @param kasky on String, joka sisältää käyttäjän antaman syötteen.
     * @return
     * @throws Exception jos käyttäjän antamaa käskyä ei löydy, metodi heittää poikkeuksen.
     */
    public Komento maaritaKomento(String kasky) throws Exception {
        if (kasky.equals("matriisi")) {
            return Komento.MATRIISI;
        }
        
        if (kasky.equals("quit")) {
            return Komento.QUIT;
        }
        
        if (kasky.equals("yhteenlasku")) {
            return Komento.PLUS;
        } 
        
        if (kasky.equals("tiedosto")) {
            return Komento.TIEDOSTO;
        }
        
        if (kasky.equals("determinantti")) {
            return Komento.DETERMINANTTI;
        }
        
        if (kasky.equals("kerro")) {
            return Komento.KERRO;
        }
        
        if (kasky.equals("kaanna")) {
            return Komento.KAANNA;
        }
        
        if (kasky.equals("komennot")) {
            return Komento.KOMENNOT;
        }
        
        if (kasky.equals("lista")) {
            return Komento.TULOSTALISTA;
        }
        
        if (kasky.equals("tulosta")) {
            return Komento.TULOSTA;
        }
        
        if (kasky.equals("vahennyslasku")) {
            return Komento.MIINUS;
        }
        
        if (kasky.equals("skalaarimonikerta")) {
            return Komento.SMONI;
        }
        
        else throw new Exception();
    }
    
    
}
