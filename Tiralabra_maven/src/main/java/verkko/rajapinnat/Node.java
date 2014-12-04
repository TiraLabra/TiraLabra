/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.rajapinnat;

import java.util.Comparator;
import tira.Lista;

/**
 * Polku/Reitti verkossa
 * 
 * @author E
 */
public interface Node {

    double getArvioituKustannus();

    void setArvioituKustannus(double estimate);

    double getKustannus();

    void setKustannus(double cost);

    Value getSolmu();

    void setSolmu(Value current);

    Edge getKuljettuKaari();

    void setKuljettuKaari(Edge viimeisinKaari);

    Node getPrevious();

    void setPrevious(Node edellinen);
    
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
