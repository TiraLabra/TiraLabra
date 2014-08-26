

package lista;
import jama.*;

/**
 * Linkitetty matriisilista. Olion tarkoitus on tarjota keino tallentaa käyttäjän
 * antamat matriisit. Tämä on toteutettu yhteen suuntaan linkitetyllä listalla, josta 
 * ei voi poistaa alkioita. Listan on tarkoitus sisältää tyyliin 10 alkiota, joten toiminnallisuudelle
 * ei ole asetettu kunnianhimoisia vaatimuksia.
 * @author risto
 */
public class LinkitettyMatriisiLista {
    
    private Solmu ekaSolmu;
    
    public LinkitettyMatriisiLista() {
        ekaSolmu = null;
    }
    
    
    /**
     * Lisää. Metodi lisää listaan uuden solmun, jonka key-arvo on annettu matriisi. 
     * @param nimi on string, joka on matriisin nimi ja toimii hakuavaimena.
     * @param matriisi on matriisi, joka on solmun varsinainen arvo.
     */
    public void lisaa(String nimi, Matrix matriisi) {
        
    
        
        Solmu lisattava = new Solmu();
        lisattava.nimi = nimi;
        lisattava.matriisi = matriisi;
        Solmu tutkittava = new Solmu();
        tutkittava = ekaSolmu;
        
        if (tutkittava == null) {
            ekaSolmu = lisattava;
            return;
        }
        
        while (tutkittava.next != null) {
            tutkittava = tutkittava.next;
        }
        
        tutkittava.next = lisattava;
        
    }
    
    
    /**
     * Hae. Metodi etsii matriisilistasta halutun nimisen matriisin. 
     * @param nimi etsityn matriisin nimi.
     * @return metodi palauttaa matriisin, jonka nimi vastaa käyttäjän syötettä.
     * @throws Exception Mikäli matriisia ei löydy, metodi heittää poikkeuksen.
     */
    public Matrix hae(String nimi) throws Exception {
        
        Solmu tutkittava = new Solmu();
        tutkittava = ekaSolmu;
        
        if (tutkittava == null) {
            throw new Exception();
        }
        
        if (tutkittava.nimi.equals(nimi)) {
            return tutkittava.matriisi;
        }
        
        while(tutkittava.next != null) {
            tutkittava = tutkittava.next;
            if (tutkittava.nimi.equals(nimi)) {
                return tutkittava.matriisi;
            }            
        }
        
        throw new Exception();
        
        
    }
    
    /**
     * Tulosta solmujen nimet. Metodi tulostaa kaikki käytössä olevat solmujen nimet.
     */
    public void tulostaNimet() {
        
        Solmu kasiteltava = new Solmu();
        kasiteltava = ekaSolmu;
        
        if (ekaSolmu == null) {
            System.out.println("Yhtään matriisia ei ole tallennettu");
            return;
        }
        
        System.out.print(ekaSolmu.nimi);
        
        while (kasiteltava.next != null) {
            kasiteltava = kasiteltava.next;
            System.out.print(", " + kasiteltava.nimi);
        }
        System.out.print("\n");
    }
    
    
    
    /**
     * Onko nimi käytössä. Metodi selvittää, onko käyttäjä jo luonut matriisin jollakin nimellä
     * @param nimiehdokas String, joka sisältää käyttäjän antaman nimen.
     * @return 
     */
    public boolean onkoNimiKaytossa(String nimiehdokas) {
        Solmu tutkittava = ekaSolmu;
        
        if (tutkittava == null) {
            return false;
        }
        
        if (tutkittava.nimi.equals(nimiehdokas)) {
            return true;
        }
        
        while (tutkittava.next != null) {
            tutkittava = tutkittava.next;
            if (tutkittava.nimi.equals(nimiehdokas)) {
                return true;
            }
        }
        return false;
    }
}
