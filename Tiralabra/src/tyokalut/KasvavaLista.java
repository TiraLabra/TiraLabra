package tyokalut;

/**
 * Luokka, jota käytetään esittämään laatikoiden asettelujärjestys vasemmalta oikealle
 * ja ylhäältä alas.
 * 
 * @author albis
 */
public class KasvavaLista {
    /**
     * Taulukko, johon laatikon asentoa kuvaavat sanat talletetaan.
     */
    private String[] lista;
    
    public KasvavaLista() {
        lista = new String[10];
    }
    
    /**
     * Listaan lisäämisen hoitava metodi, joka kasvattaa listan pituutta kymmenellä
     * aina, kun siihen ei mahdu enempää.
     * 
     * @param lisattava Laatikon asentoa kuvaava sana.
     */
    public void lisaa(String lisattava) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                lista[i] = lisattava;
                return;
            }
        }
        
        String[] kopio = new String[lista.length];
        kopioiAlkiot(lista, kopio, lista.length);
        
        lista = new String[kopio.length+10];
        kopioiAlkiot(kopio, lista, kopio.length);
        
        lista[kopio.length] = lisattava;
    }
    
    /**
     * Metodi, joka kertoo kuinka monta asentoa kuvaavaa sanaa listassa on,
     * eli kuinka monta laatikkoa mahtuu yhteen kerrokseen.
     * 
     * @return Listan pituutta kuvaava kokonaisluku.
     */
    public int length() {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                return i;
            }
        }
        return lista.length;
    }
    
    /**
     * Kopioi annetun määrän alkioita taulukon alusta toisen taulukon alkuun.
     * 
     * @param lahde Taulukko, jonka alkioita kopioidaan.
     * @param kohde Taulukko, johon alkioita kopioidaan.
     * @param montako Kopioitavien alkioiden määrä.
     */
    private void kopioiAlkiot(String[] lahde, String[] kohde, int montako) {
        for (int i = 0; i < montako; i++) {
            kohde[i] = lahde[i];
        }
    }
    
    /**
     * Kopioi listan sisältämät asennot.
     * 
     * @return Kopio kyseisestä oliosta.
     */
    public KasvavaLista kopioi() {
        KasvavaLista kopio = new KasvavaLista();
        
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                return kopio;
            }
            
            kopio.lisaa(lista[i]);
        }
        
        return kopio;
    }
    
    /**
     * Palauttaa annetussa indeksissä sijaitsevan asentoa kuvaavan merkkijonon.
     * 
     * @param indeksi Listan kohta, josta asento haetaan.
     * @return Palauttaa haetun asentotiedon.
     */
    public String getAsento(int indeksi) {
        return lista[indeksi];
    }
}
