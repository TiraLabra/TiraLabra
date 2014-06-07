package logiikka;

import apuneuvot.*;

/**
 * Matriisilaskin-luokka, joka kapseloi sisäänsä kaikki laskuoperaatiot.
 *
 * @author Eversor
 */
public class Matriisilaskin {

    private MatriisienKopioija kopioija;
    private MatriisienVertailu vertailu;
    private MatriisienGeneroija generoija;
    private Yhteenlasku yhteenlasku;
    private Vahennyslasku vahennyslasku;
    private Skalaaritulo skalaaritulo;
    private Kertolasku kertolasku;
    private Transpoosi transpoosi;
    private Strassen strassen;
    private Determinantti determinantti;
    private Kaanteismatriisi kaanteismatriisi;

    /**
     * Konstruktori, joka luo uudet ilmentymät apuneuvo- ja laskuoperaatio-
     * luokista. Ilmentymät asetetaan niiden private muuttujiin.
     *
     */
    public Matriisilaskin() {
        kopioija = new MatriisienKopioija();
        vertailu = new MatriisienVertailu();
        generoija = new MatriisienGeneroija();
        
        yhteenlasku = new Yhteenlasku();
        vahennyslasku = new Vahennyslasku();
        skalaaritulo = new Skalaaritulo();
        kertolasku = new Kertolasku();
        transpoosi = new Transpoosi();
        strassen = new Strassen(yhteenlasku, vahennyslasku, kertolasku);
        determinantti = new Determinantti(kopioija);
        kaanteismatriisi = new Kaanteismatriisi(kopioija, generoija);     
    }

    /**
     * Metodi, joka kutsuu Yhteenlasku-luokan summaa -metodia parametreinaan
     * kaksi summattavaa matriisia. Huom. matriisien oltava samankokoisia!
     *
     * @param A Ensimmäinen yhteenlaskettava matriisi muotoa m x n
     * @param B Toinen yhteenlaskettava matriisi muotoa m x n
     * @return Palauttaa yhteenlasketun matriisin muotoa m x n
     */
    public double[][] summaa(double[][] A, double[][] B) {
        return yhteenlasku.summaa(A, B);
    }

    /**
     * Metodi, joka kutsuu Vahennyslasku-luokan vahenna -metodia parametreinaan
     * kaksi toistaan vähennettävää matriisia. Huom. matriisien oltava
     * samankokoisia!
     *
     * @param A Ensimmäinen matriisi, josta vähennetään toinen matriisi, muotoa
     *          m x n
     * @param B Toinen matriisi, joka vähennetään ensimmäisestä matriisista,
     *          muotoa m x n
     * @return Palauttaa erotusmatriisin muotoa m x n
     */
    public double[][] vahenna(double[][] A, double[][] B) {
        return vahennyslasku.vahenna(A, B);
    }

    /**
     * Metodi, joka kutsuu Skalaaritulo-luokan kerro -metodia parametreinaan
     * matriisi sekä reaaliluku, jolla kyseistä matriisia kerrotaan.
     *
     * @param A Matriisi, jonka alkiot halutaan kertoa reaaliluvulla
     * @param c Reaaliluku, jolla kerrotaan matriisin alkiot
     * @return Palauttaa reaaliluvulla kerrotun matriisin
     */
    public double[][] kerro(double[][] A, double c) {
        return skalaaritulo.kerro(A, c);
    }

    /**
     * Metodi, joka kutsuu Kertolasku-luokan kerro -metodia parametreinaan kaksi
     * toisiinsa kerrottavaa matriisia. Toteuttaa yhtälön tulomatriisi = eka *
     * toka. Huom. matriisien kertolasku ei ole vaihdannainen, joten
     * laskujärjestyksellä on väliä! Huom2. ensimmäisen matriisin sarakkeiden
     * määrä pitää vastata toisen matriisin rivien määrää!
     *
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa m x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa n x q
     * @return Palauttaa tulomatriisin muotoa m x q
     */
    public double[][] kerro(double[][] eka, double[][] toka) {
        return kertolasku.kerro(eka, toka);
    }

    /**
     * Metodi, joka kutsuu Transpoosi-luokan transpoosaa -metodia parametrinaan
     * matriisi, jonka transpoosi halutaan.
     *
     * @param A Matriisi, jonka transpoosi halutaan
     * @return Palauttaa matriisin transpoosin
     */
    public double[][] transpoosaa(double[][] A) {
        return transpoosi.transpoosaa(A);
    }

    /**
     * Metodi, joka kutsuu Strassen-luokan kerro -metodia parametrinaan kaksi
     * toisiinsa kerrottavaa matriisia. Totetuttaa yhtälön tulomatriisi = eka *
     * toka. Huom. matriisien kertolasku ei ole vaihdannainen, joten
     * laskujärjestyksellä on väliä! Huom2. matriisien oltava samankokoisia
     * neliömatriiseja!
     *
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa n x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa n x n
     * @return Palauttaa tulomatriisin muotoa n x n
     */
    public double[][] kerroStrassenilla(double[][] eka, double[][] toka) {
        return strassen.kerro(eka, toka);
    }
    
    /**
     * Metodi, joka kutsuu Determinantti-luokan laskeDeterminantti -metodia
     * parametrinaan matriisi, jonka determinantti halutaan laskea.
     * Determinantin laskemisessa käytetään osittaistuettua LU-hajotelmaa.
     * 
     * @param A Matriisi, jonka determinantti halutaan laskea, muotoa n x n
     * @return Palauttaa determinantin arvon
     */
    public double laskeDeterminantti(double[][] A) {
        return determinantti.laskeDeterminantti(A);
    }

    /**
     * Metodi, joka kutsuu Kaanteismatriisi-luokan invertoi -metodia
     * parametrinaan matriisi, jonka käänteismatriisi halutaan approksimoida. 
     * Käänteismatriisin laskemisessa käytetään Gaussin eliminointi-menetelmää 
     * ja takaisinsijoitusta.
     * 
     * @param A Matriisi, jonka käänteismatriisi halutaan approksimoida,
     *          muotoa n x n
     * @return Palauttaa käänteismatriisin approksimaation
     */
    public double[][] invertoi(double[][] A) {
        if(!onkoKaantyva(A)){
            throw new IllegalArgumentException("Matriisi ei ole kääntyvä,"
                    + "joten sen käänteismatriisia ei voida muodostaa");
        }
        return kaanteismatriisi.invertoi(A);
    }
    
    /**
     * Metodi, joka selvittää onko annettu matriisi neliömatriisi. Matriisi on
     * neliömatriisi, jos sen rivien ja sarakkeiden lukumäärät ovat samat.
     *
     * @param A Matriisi, josta halutaan tieto onko neliömatriisi, muotoa m x n
     * @return Palauttaa true, jos matriisi on neliömatriisi
     */
    public boolean onkoNeliomatriisi(double[][] A) {
        return A.length == A[0].length;
    }

    /**
     * Metodi, joka selvittää onko annettu matriisi symmetrinen. Matriisi on
     * symmetrinen, jos sen transpoosi on matriisi itse.
     *
     * @param A Matriisi, jonka symmetrisyys halutaan selvittää, muotoa m x n
     * @return Palauttaa true, jos matriisi on symmetrinen
     */
    public boolean onkoSymmetrinen(double[][] A) {
        return vertailu.vertaile(A, transpoosaa(A));
    }
    
    /**
     * Metodi, joka selvittää onko annettu matriisi antisymmetrinen. Matriisi
     * on antisymmetrinen, jos sen transpoosi on itsensä negaatio.
     * 
     * @param A Matriisi, jonka antisymmetrisyys halutaan selvittää, muotoa m x n
     * @return Palauttaa true, jos matriisi on antisymmetrinen
     */
    public boolean onkoAntisymmetrinen(double[][] A) {
        return vertailu.vertaile(kerro(A, -1), transpoosaa(A));
    }
    
    /**
     * Metodi, joka selvittää onko annettu matriisi kääntyvä eli onko sille
     * olemassa käänteismatriisi. Matriisi on kääntyvä, jos sen determinantti on
     * eri kuin nolla.
     * 
     * @param A Matriisi, jonka kääntyvyys halutaan selvittää, muotoa n x n
     * @return Palauttaa true, jos matriisi on kääntyvä
     */
    public boolean onkoKaantyva(double[][] A) {
        if(determinantti.laskeDeterminantti(A) == 0){
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Metodi, joka selvittää onko annettu neliömatriisi ortogonaalinen eli onko 
     * sen transpoosi sama kuin sen käänteismatriisi.
     * 
     * @param A Matriisi, jonka ortogonaalisuus halutaan selvittää, muotoa n x n
     * @return Palauttaa true, jos matriisi on ortogonaalinen
     */
    public boolean onkoOrtogonaalinen(double[][] A) {
        if(vertailu.vertaile(invertoi(A), transpoosaa(A))) {
            return true;
        } else {
            return false;
        }
    }
}