
package com.mycompany.tiralabra_maven;

/**
* Linkitetty lista -tietorakenne. Toimii kuin jono. Kokoa ei tarvitse ennalta
* määrittää.
* 
* Linkitetty lista koostuu pinosolmuista. Lista käydään läpi aloittamalla
* ensimmäisestä pinosolmusta ja jatketaan sen seuraajaan kunnes viimeinen
* pinosolmu tulee vastaan.
*/
public class LinkitettyLista {
    private Pinosolmu ylin;

    /**
     * Palauttaa listan ylimmän (ensimmäisen) pinosolmun
     * 
     * @return listan ensimmäinen pinosolmu
    */
    public Pinosolmu getYlin() {
        return ylin;
    }
    
    /**
     * Lisää solmun listan viimeiseksi
     * 
     * @param    solmu  listaan lisättävä solmu
    */
    public void lisaa(Solmu solmu) {
        Pinosolmu uusi = new Pinosolmu();
        uusi.setSisalto(solmu);
        uusi.setSeuraava(this.ylin);
        this.ylin = uusi;
    }
    
//    public Solmu poista() {
//        Pinosolmu poistettava = this.ylin;
//        this.ylin = poistettava.getSeuraava();
//        return poistettava.getSisalto();
//    }
    
    /**
     * Kertoo onko lista tyhjä vai ei
     * 
     * @return onko lista tyhjä vai ei
    */
    public boolean onkoTyhja() {
        return ylin == null;
    }
    
    /**
     * Laskee listassa olevien pinosolmujen lukumäärän.
     * 
     * @return listan pinosolmujen lukumäärä
    */
    public int koko() {
        Pinosolmu pinosolmu = ylin;
        int koko = 0;
        
        while (pinosolmu != null) {
            koko++;
            pinosolmu = pinosolmu.getSeuraava();
        }
        
        return koko;
    }
}
