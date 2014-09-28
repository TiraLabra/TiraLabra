
package com.mycompany.tiralabra_maven;

/**
* Linkitetty lista -tietorakenne. Toimii kuin pino. Kokoa ei tarvitse ennalta
* määrittää.
* 
* Linkitetty lista koostuu listasolmuista. Lista käydään läpi aloittamalla
* ensimmäisestä listasolmusta ja jatketaan sen seuraajaan kunnes viimeinen
* listasolmu tulee vastaan.
*/
public class LinkitettyLista {
    private Listasolmu ylin;

    /**
     * Palauttaa listan ylimmän (ensimmäisen) listasolmun
     * 
     * Aikavaativuus: vakio
     * 
     * @return listan ensimmäinen listasolmu
    */
    public Listasolmu getYlin() {
        return ylin;
    }
    
    /**
     * Lisää solmun listan ensimmäiseksi
     * 
     * Aikavaativuus: vakio
     * 
     * @param    solmu  listaan lisättävä solmu
    */
    public void lisaa(Solmu solmu) {
        Listasolmu uusi = new Listasolmu();
        uusi.setSisalto(solmu);
        uusi.setSeuraava(this.ylin);
        this.ylin = uusi;
    }
    
//    public Solmu poista() {
//        Listasolmu poistettava = this.ylin;
//        this.ylin = poistettava.getSeuraava();
//        return poistettava.getSisalto();
//    }
    
    /**
     * Kertoo onko lista tyhjä vai ei
     * 
     * Aikavaativuus: vakio
     * 
     * @return onko lista tyhjä vai ei
    */
    public boolean onkoTyhja() {
        return ylin == null;
    }
    
    /**
     * Laskee listassa olevien listasolmujen lukumäärän.
     * 
     * Pahin tapaus: kaikki listasolmut käydään läpi (myös paras tapaus)
     * Aikavaativuus: lineaarinen listasolmujen lukumäärän suhteen
     * 
     * @return listan solmujen lukumäärä
    */
    public int koko() {
        Listasolmu listasolmu = ylin;
        int koko = 0;
        
        while (listasolmu != null) {
            koko++;
            listasolmu = listasolmu.getSeuraava();
        }
        
        return koko;
    }
}
