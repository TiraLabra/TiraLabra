package logiikka;

import osat.Laatikko;
import osat.Lava;
import tyokalut.KasvavaLista;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albis
 */
public class Laskuri {
    private Laatikko laatikko;
    private Lava lava;
    
    private KasvavaLista parasJarjestys;
    
    public Laskuri() {
    }
    
    public KasvavaLista laske(Laatikko laatikko, Lava lava) {
        parasJarjestys = new KasvavaLista();
        this.laatikko = laatikko;
        this.lava = lava;
        
        asetaLaatikko(0, 0, lava, new KasvavaLista());
        
        return parasJarjestys;
    }
    
    private void asetaLaatikko(int x, int y, Lava vanhaLava, KasvavaLista jarjestysTahanAsti) {
        if (jarjestysTahanAsti.length() > parasJarjestys.length()) {
            parasJarjestys = jarjestysTahanAsti.kopioi();
        }
        
        if (x + laatikko.getLeveys() <= lava.getLeveys() && y + laatikko.getKorkeus() <= lava.getKorkeus()) {
            Lava leveysSivulleLava = vanhaLava.kopioi();
            leveysSivulleLava.merkitseLaatikko(x, y, laatikko.getLeveys(), laatikko.getPituus());
            Lava leveysEteenLava = leveysSivulleLava.kopioi();

            KasvavaLista leveysSivulleJarjestys = jarjestysTahanAsti.kopioi();
            leveysSivulleJarjestys.lisaa("VAAKA");
            KasvavaLista leveysEteenJarjestys = leveysSivulleJarjestys.kopioi();

            asetaLaatikko(x+laatikko.getLeveys(), y, leveysSivulleLava, leveysSivulleJarjestys);
            asetaLaatikko(x, y+laatikko.getPituus(), leveysEteenLava, leveysEteenJarjestys);
        }
        
        if (x + laatikko.getPituus() <= lava.getLeveys() && y + laatikko.getLeveys() <= lava.getPituus()) {
            Lava pituusSivulleLava = vanhaLava.kopioi();
            pituusSivulleLava.merkitseLaatikko(x, y, laatikko.getPituus(), laatikko.getLeveys());
            Lava pituusEteenLava = pituusSivulleLava.kopioi();

            KasvavaLista pituusSivulleJarjestys = jarjestysTahanAsti.kopioi();
            pituusSivulleJarjestys.lisaa("PYSTY");
            KasvavaLista pituusEteenJarjestys = pituusSivulleJarjestys.kopioi();

            asetaLaatikko(x+laatikko.getPituus(), y, pituusSivulleLava, pituusSivulleJarjestys);
            asetaLaatikko(x, y+laatikko.getLeveys(), pituusEteenLava, pituusEteenJarjestys);
        }
    }
}
