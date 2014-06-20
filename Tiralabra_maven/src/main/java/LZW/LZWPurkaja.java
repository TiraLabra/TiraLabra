package LZW;

import Apuvalineet.BinaariMuuntaja;
import Apuvalineet.Purkaja;
import Tietorakenteet.HajautusTaulu;

/**
 * Luokka joka suorittaa Lempel-Ziv-Welchin algoritmilla pakatun tiedoston
 * purkamisen. Perii Purkaja -luokan.
 * 
 * Luokan ohjelmakoodi ei toimi oikein (t‰st‰ dokumentaatiossa lis‰‰).
 */

public class LZWPurkaja extends Purkaja {
    private BinaariMuuntaja muuntaja;
    private StringBuilder teksti;
    private HajautusTaulu ascii;
    private HajautusTaulu laaj;
    private HajautusTaulu kaannettyLaaj;
    private int merkkienPituus;
    
    public LZWPurkaja() {
        super(".lzw");
        this.teksti = new StringBuilder();
        this.muuntaja = new BinaariMuuntaja();
        this.laaj = new HajautusTaulu();
        this.kaannettyLaaj = new HajautusTaulu();
        
        YleisMetodeja y = new YleisMetodeja();
        this.ascii = y.alustaAscii();
        this.merkkienPituus = 9;
    }
    
    protected HajautusTaulu getLaaj() {
       return this.laaj; 
    }
    
    /**
     * Laajennettu hajautustaulu "k‰‰nnettyn‰".
     * Ts. sis‰lt‰‰ (arvo, avain) -pareja.
     * @return 
     */
    
    protected HajautusTaulu getKaannettyLaaj() {
        return this.kaannettyLaaj; 
    }
    
    protected String getTeksti() {
        return this.teksti.toString();
    }
    
    protected int getPituus() {
        return this.merkkienPituus;
    }
    
    /**
     * Testausmetodi.
     * @param pituus 
     */
    
    protected void setPituus(int pituus) {
        this.merkkienPituus = pituus;
    }
    
    /**
     * Muodostaa tiedoston sis‰llˆst‰ bin‰‰riesitykjsen ja purkaa sen.
     * T‰m‰n j‰lkeen palauttaa puretun tekstin.
     * @param teksti
     * @return 
     */
    
    @Override
    protected String puretunTiedostonSisalto(String teksti) {
        String binaarina = tekstiBinaarina(teksti, 0);
        kirjoitettavaTeksti(binaarina);
        
        return this.teksti.toString();
    }
    
    /**
     * Muuntaa parametrina saadun pakkauksen bin‰‰riesityksen tekstiksi, joka
     * vastaa pakkaamattoman tiedoston sis‰ltˆ‰.
     * T‰m‰ tapahtuu lukemalla 9-merkkisi‰ bittijonoja ja k‰‰nt‰en n‰ist‰
     * merkkijonoja. Sen j‰lkeen merkkijonoille tehd‰‰n hyvin samanlaisia
     * asioita kuin lukiessa; lis‰t‰‰n koodistoon jos ne eiv‰t siell‰ viel‰
     * ole ja lis‰t‰‰n ne tekstiin.
     * @param binaarina
     */
    
    protected void kirjoitettavaTeksti(String binaarina) {
        String nykyinen = "";
        int i = 0;
        
        while (i < binaarina.length()) {
            String merkkijono = seuraavaMerkkiJono(binaarina, i);
            nykyinen = kasitteleMerkkijono(nykyinen, merkkijono);

            i += merkkienPituus;
        }
        
        teksti.append(nykyinen);
    }
    
    /**
     * K‰sittelee seuraavan merkkijonon k‰sitellen niist‰ jokaisen merkin
     * yksi kerrallaan. K‰sittelyn tulos on "nykyisen" arvo.
     * @param nykyinen
     * @param merkkijono
     * @return 
     */
    
    protected String kasitteleMerkkijono(String nykyinen, String merkkijono) {
        for (int j = 0; j < merkkijono.length(); j++) {
            String merkki = merkkijono.charAt(j) + "";
            nykyinen = kasitteleMerkki(nykyinen, merkki);
        }
        
        return nykyinen;
    }
    
    /**
     * K‰sittelee seuraavan merkin. Parametrissa "nykyinen" on merkkijono,
     * joka on saatu viime k‰sittelyn tuloksena.
     * Jos n‰iden yhdistelm‰ ei ole laajennetussa koodistossa, lis‰t‰‰n se
     * sinne ja "nykyinen" tekstiin. T‰llˆin palautetaan "merkki" seuraavaa
     * k‰sittely‰ varten.
     * Muuten palautetaan "nykyinen + merkki" (koska nykyinen oli koodistossa).
     * 
     * @param nykyinen
     * @param merkki
     * @return 
     */
    
    protected String kasitteleMerkki(String nykyinen, String merkki) {
        String seuraava = nykyinen + merkki;
        
        if (! nykyinen.isEmpty() && ! laaj.sisaltaaAvaimen(seuraava)) {
            
            teksti.append(nykyinen);
            
            lisaaKoodistoon(seuraava);
            seuraava = merkki;
        }
        
        return seuraava;
    }
    
    /**
     * Lis‰‰ merkkijonon koodistoon (laaj + kaannettyLaaj).
     * Lis‰yksen yhteydess‰ kasvatetaan luettavien bittijonojen pituutta mik‰li
     * siihen on tarvetta.
     * @param avain 
     */
    
    protected void lisaaKoodistoon(String avain) {
        YleisMetodeja yleis = new YleisMetodeja();
        
        String arvo = yleis.koodistoonLisattavaArvo(ascii, laaj);
        
        merkkienPituus = yleis.merkkienPituus(merkkienPituus, arvo.length());
        
        laaj.lisaa(avain, arvo);
        kaannettyLaaj.lisaa(arvo, avain);
    }
    
    /**
     * Palauttaa seuraavan merkkijonon k‰sittely‰ varten.
     * @param binaarina
     * @param i
     * @return 
     */
    
    protected String seuraavaMerkkiJono(String binaarina, int i) {
        String bittijono = seuraavaBittijono(binaarina, i);
        return merkkijono(bittijono);
    }    
    
    /**
     * Palauttaa seuraavan bittijonon josta muodostetaan palautettava
     * merkkijono.
     * @param binaarina
     * @param i
     * @return 
     */
    
    protected String seuraavaBittijono(String binaarina, int i) {
        int loppu = Math.min(binaarina.length(), i + merkkienPituus);
        return binaarina.substring(i, loppu);
    }
    
    /**
     * K‰‰nt‰‰ bittijonon merkkijonoksi tarkistamalla onko se k‰‰nnetyss‰
     * hajautustaulussa(laaj). Jos ei ole, palauttaa bittijonoa vastaavan
     * ascii merkin.
     * @param bittijono
     * @return 
     */
    
    protected String merkkijono(String bittijono) {
        if (kaannettyLaaj.sisaltaaAvaimen(bittijono)) {
            return kaannettyLaaj.getArvo(bittijono);
        }
        
        String loppuosa = bittijono.substring(bittijono.length() - 8);
        return muuntaja.asciiMerkkina(loppuosa) + "";
    }
}