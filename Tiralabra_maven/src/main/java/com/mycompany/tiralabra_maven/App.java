package com.mycompany.tiralabra_maven;

/**
 * Tässä luokassa testataan ja verrataan hajautustaulujen suorituskykyä
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //new KahteenSuuntaanLinkitettyLista().poistaSolmu(null);
        HajautustauluLinkitetyllaListalla hajis = new HajautustauluLinkitetyllaListalla();
        for(int i=0;i<100000;i++) {
        	hajis.lisaaMerkinta("a"+i, "b"+i);
        }
        
    	/*HajautustauluAvoimellaHajautuksella hajis = new HajautustauluAvoimellaHajautuksella(false);
        for(int i=0;i<100000;i++) {
        	hajis.lisaaMerkinta("a"+i, "b"+i);
        }*/
        System.out.println(hajis.etsiMerkinta("a85").getArvo());
        System.out.println(hajis.getTaulukonKoko());
        System.out.println(hajis.getMerkintoja());
        for(int i=0;i<62000;i++) {
        	hajis.poistaMerkinta("a"+i);
        }
        System.out.println(hajis.etsiMerkinta("a62000").getArvo());
        System.out.println(hajis.getTaulukonKoko());
        System.out.println(hajis.getMerkintoja());
        System.out.println(hajis.getTayttosuhde());
    	

    }
}
