/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import com.mycompany.tiralabra_maven.App;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import verkko.Pysakki;
import verkko.VerkkoOmallaTietorakenteella;
import verkko.rajapinnat.Node;

/**
 *
 * @author E
 */
public class AStarTest extends TestCase {

    private VerkkoOmallaTietorakenteella verkko;
    private AStar aStar;
    private List<ReittiLaskin> testattavatLaskimet;
    private Pysakki oletusAlku, oletusMaali;

    @Before
    public void setUp() {
        verkko = new VerkkoOmallaTietorakenteella();
        testattavatLaskimet = new ArrayList();
        testattavatLaskimet.add(App.bfs); testattavatLaskimet.add(App.bfsVaihdoton);
        testattavatLaskimet.add(App.normaali); testattavatLaskimet.add(App.normaaliMatkaaMinimoiva);
        testattavatLaskimet.add(App.vaihdoton); testattavatLaskimet.add(App.vaihdotonMatkaaMinimoiva);      
        oletusAlku = verkko.getPysakit()[5];
        oletusMaali = verkko.getPysakit()[17];
    }
   
    @Test
    public void testVerkkoOk() {
        assertTrue( verkko.getPysakit() != null && verkko.getPysakit().length > 0);
        assertTrue( verkko.getLinjat()  != null && verkko.getLinjat().length  > 0);
    }

    
    @Test
    public void testReittiLoytyy() {
        ReittiLaskin reittiLaskin = testattavatLaskimet.get(testattavatLaskimet.size()-1);
        aStar = new AStar(verkko, reittiLaskin);
        aStar.setDebugMode(false);
        aStar.setDebugPrint(false);
        
        Node reitti =  aStar.etsiReitti(oletusAlku, oletusMaali);
        assertTrue( reitti!=null );
    }
    
    @Test
    public void testHeuristiikkaVaikuttaa() {
        
        long bfsAika = laskeEtsintaAika(oletusAlku,oletusMaali, testattavatLaskimet.get(0), 0);
        long heurAika = laskeEtsintaAika(oletusAlku,oletusMaali, testattavatLaskimet.get(testattavatLaskimet.size()-1), 0);
        assertTrue( heurAika<=bfsAika );                
    }
    
    @Test
    public void testHeuristiikkaOnnistuu() {

        for ( ReittiLaskin reittiLaskin : testattavatLaskimet ) {
            double onnistumistenOsuus = laskeHeuristiikanToimivuus(oletusAlku, oletusMaali, reittiLaskin );
            assertTrue( Double.compare(onnistumistenOsuus, 1) >= 0 );            
        }
    }

    
    //////////////
    //APUMETODIT//
    //////////////
    
    private double laskeHeuristiikanToimivuus(Pysakki alku, Pysakki maali, ReittiLaskin reittiLaskin)  {
        
        AStar aStar = new AStar( verkko, reittiLaskin );
        aStar.setDebugMode(false);   // koko jono: true, vain ratkaisuun asti: false
        aStar.setDebugPrint(false);
        /*Reitti reitti = */aStar.etsiReitti(alku, maali); // lasketaan reitti
        return aStar.getDebugTieto().getHeuristiikanOnnistumiset();
        
              
    }
    
    private Long laskeEtsintaAika( Pysakki alku, Pysakki maali, ReittiLaskin reittiLaskin, int mode ) {
        long start = System.currentTimeMillis();        
        AStar aStar = new AStar( verkko, reittiLaskin );
        aStar.setDebugMode(false);  
        aStar.setDebugPrint(false);
        Node reitti;
        if ( mode == 0)  reitti = aStar.etsiReitti(alku, maali);
        else reitti = aStar.etsiReittiOma(alku, maali);
        
        long stop = System.currentTimeMillis();
        return stop-start;        
    }
        
}
