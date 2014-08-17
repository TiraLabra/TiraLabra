package com.mycompany.tiralabra_maven;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //new KahteenSuuntaanLinkitettyLista().poistaSolmu(null);
        HajautustauluLinkitetyllaListalla hajis = new HajautustauluLinkitetyllaListalla(16);
        for(int i=0;i<100;i++) {
        	hajis.lisaaMerkinta("a"+i, "b"+i);
        }
        
        System.out.println(hajis.etsiMerkinta("a85").getAvain());
    }
}
