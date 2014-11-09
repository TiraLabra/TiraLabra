/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import verkko.Pysakki;
import verkko.Verkko;

/**
 *
 * @author E
 */
public class AStarTest extends TestCase {

    private Verkko verkko;
    private ReittiLaskin bfs, normaali, vaihdoton;
    private AStar aStar;
    private Pysakki oletusAlku, oletusMaali;

    @Before
    public void setUp() {
        verkko = new Verkko();
        // A*-hausta saadaan leveyssuuntainen haku: heuristiikan arvo == 0 aina
        bfs = new ReittiLaskin(1, 0, 0, 0, 0, 400);
        // Tavallinen, vain matka-aikaa laskeva haku
        normaali = new ReittiLaskin(1, 0, 0, 1, 0, 400);
        // Minimoi vaihtojen määrää
        vaihdoton = new ReittiLaskin(1, 0, 3, 1, 0, 400);
        oletusAlku = verkko.getPysakit()[5];
        oletusMaali = verkko.getPysakit()[17];
    }

    /*
    WIP: Verkon luomisen testaus omaan testiluokkaansa
    */
    
   
    @Test
    public void testVerkkoOk() {
        assertTrue( verkko.getPysakit() != null && verkko.getPysakit().length > 0);
        assertTrue( verkko.getLinjat()  != null && verkko.getLinjat().length  > 0);
    }

    
    @Test
    public void testReittiLoytyy() {
        ReittiLaskin reittiLaskin = vaihdoton;
        aStar = new AStar(verkko, reittiLaskin);
        aStar.setDebugMode(false);
        aStar.setDebugPrint(false);
        
        Reitti reitti =  aStar.etsiReitti(oletusAlku, oletusMaali);
        assertTrue( reitti!=null );
    }
    @Test
    public void testHeuristiikkaVaikuttaa() {
        
        long bfsAika = laskeEtsintaAika(oletusAlku,oletusMaali,bfs);
        long heurAika = laskeEtsintaAika(oletusAlku,oletusMaali,vaihdoton);
        assertTrue( heurAika<=bfsAika );                
    }
    
    @Test
    public void testHeuristiikkaOnnistuu() {
        List<ReittiLaskin> testattavatLaskimet = new ArrayList();
        testattavatLaskimet.add(bfs); testattavatLaskimet.add(normaali);
        testattavatLaskimet.add(vaihdoton);
        for ( ReittiLaskin reittiLaskin : testattavatLaskimet ) {
            double onnistumistenOsuus = laskeHeuristiikanToimivuus(oletusAlku, oletusMaali, reittiLaskin );
            assertTrue( Double.compare(onnistumistenOsuus, 0.99) > 0 );
            // WIP: pitäisi toimia 100%. Heuristiikan kulkunopeutta pitää säätää
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
        return aStar.getHeuristiikanOnnistumiset();
        
              
    }
    
    private Long laskeEtsintaAika( Pysakki alku, Pysakki maali, ReittiLaskin reittiLaskin ) {
        long start = System.currentTimeMillis();        
        AStar aStar = new AStar( verkko, reittiLaskin );
        aStar.setDebugMode(false);   // koko jono: true, vain ratkaisuun asti: false
        aStar.setDebugPrint(false);
        Reitti reitti = aStar.etsiReitti(alku, maali);
        long stop = System.currentTimeMillis();
        return stop-start;        
    }
    
    /*
    WIP: Suorituskykytestaus, vertailu
    */
    
}
