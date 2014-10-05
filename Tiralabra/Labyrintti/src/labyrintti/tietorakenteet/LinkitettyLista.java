/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.tietorakenteet;

/**
 * Luokka edustaa linkitettyä listaa.
 *
 * @author Mikael Parvamo
 */
public class LinkitettyLista {

    private ListaAlkio paa;
    private ListaAlkio viimeinen;
    private int koko;

    public LinkitettyLista() {
        this.paa = null;
        this.viimeinen = null;
        this.koko = 0;
    }

    /**
     * Metodi lisaa alkion linkitettyyn listaan.
     * @param ListaAlkio alkio, lisättävä alkio.
     * 
     */
    
    public void lisaaListaan(ListaAlkio alkio) {
        if (this.koko == 0) {
            this.paa = alkio;
            this.viimeinen = alkio;
            this.koko++;
        } else {
            ListaAlkio alkio1 = paa;

            while (alkio1.getSeuraava() != null) {
                alkio1 = alkio1.getSeuraava();
            }
            alkio1.setSeuraava(alkio);
            this.viimeinen = alkio;
            this.koko++;
        }
    }
    
    /**
     * Metodi poistaa parametrina saadun alkion listasta.
     * @param ListaAlkio alkio, lisättävä alkio.
     */ 

    public void poistaListasta(ListaAlkio alkio) {
        if (this.koko == 0) {
        } else {

            ListaAlkio alkio1 = this.paa;
            int i = 0;

            while (i < koko) {                          
                if (alkio1.equals(alkio) && i == 0) {                 //poistetaan paa
                    this.paa = alkio1.getSeuraava();
                    this.koko--;
                    break;
                }
                if (alkio1.getSeuraava()!= null && alkio1.getSeuraava().equals(alkio) && this.viimeinen.equals(alkio)) {           //poistetaan viimeinen
                    alkio1.setSeuraava(null);
                    this.koko--;
                }
                if (alkio1.getSeuraava() != null && alkio1.getSeuraava().equals(alkio)) {             //poistetaan "keskeltä" listaa
                    alkio1.setSeuraava(alkio1.getSeuraava().getSeuraava());
                    this.koko--;
                }
                alkio1 = alkio1.getSeuraava();

                i++;

            }
            ListaAlkio seuraava = null;       //alustetaan poistettavan alkion seuraaja
            alkio.setSeuraava(seuraava);
        }
    }
    
    /**
     * @return this.koko 
     */
    
    public int getKoko(){
        return this.koko;
    }
    
    /**
     * 
     * @return this.paa
     */
    
    public ListaAlkio getPaa(){
        return this.paa;
    }
}
