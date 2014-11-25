/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.rajapinnat;

import java.util.Comparator;

/**
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
}
