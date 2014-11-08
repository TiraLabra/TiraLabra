package com.mycompany.tiralabra_maven;

import com.mycompany.ui.Cli;

public class App 
{
    public static void main( String[] args )
    {
        Cli c = new Cli(1);
        c.run();
        
//        //debug
//        Kasi kivi = new Kasi("KIVI");
//        Kasi paperi = new Kasi("PAPERI");
//        Kasi sakset = new Kasi("SAKSET");
//        
//        kivi.lisaaVoittavaKasiYksi(paperi);
//        paperi.lisaaVoittavaKasiYksi(sakset);
//        sakset.lisaaVoittavaKasiYksi(kivi);
//        
//        System.out.println("kivi vs paperi: " + kivi.voittaako(paperi));
//        System.out.println("kivi vs sakset: " + kivi.voittaako(sakset));
//        System.out.println("kivi vs kivi: " + kivi.voittaako(kivi));
    }
}
