/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.rajapinnat;

import java.util.Comparator;
import tira.Lista;

/**
 * Polku/Reitti verkossa.
 * 
 * @author E
 */
public interface Node {

    /**
     * Node tuntee arvioidun jäljellä olevan kustannuksen. Heuristinen arvio.
     * 
     * @return 
     */
    double getArvioituKustannus();

    void setArvioituKustannus(double estimate);

    /**
     * Node tuntee kuljetun reitin kustannuksen.
     * 
     * @return 
     */
    double getKustannus();

    void setKustannus(double cost);

    /**
     * Node tuntee solmun, jossa on
     * 
     * @return 
     */
    Value getSolmu();

    void setSolmu(Value current);
    /**
     * Node tuntee viimeksi kuljetun kaaren
     * 
     * @return 
     */
    Edge getKuljettuKaari();

    void setKuljettuKaari(Edge viimeisinKaari);

    /**
     * Node tuntee reitin, jota pitkin siihen on päästy
     * 
     * @return 
     */
    Node getPrevious();
    
    void setPrevious(Node edellinen);
    /**
     * Nodet tuntevat oman keskinäisen vertailunsa.
     * 
     * @param tarkkuus
     * @return 
     */
    Comparator<Node> vertailija( int tarkkuus );
    
    /**
     * Gui:ta varten.
     * 
     * @return 
     */
    Lista<Value> solmut(); 
    
    /**
     * Tuloksien esittämistä varten.
     * 
     * @return Reitin pituus
     */
    int size();
}
