package blokus.logiikka;

import blokus.AI.PelaajaAI;
import blokus.conf.GlobaalitMuuttujat;
import blokus.conf.LaattojenMuodot;
import hashMap.OmaHashMap;

/**
 * Pelaaja luokka hallitsee pelaajan komponentteja. Ja laskee pelaajan sen
 * hetkisen pistemäärän.
 *
 * @author Simo Auvinen
 */
public class Pelaaja  {

    private int pelaajanID;
    private PelaajanLaatat laatat;
    private TarkastusLauta tarkastusLauta;
    private boolean olenkoAI;
    private PelaajaAI pelaajaAI;
    /**
     * Tällähetkellä "kädessä" oleva laatta
     */
    private Laatta valittuna;

    /**
     * Määrittää ID:n pelaajalle Luo pelaajalle laatat ja tarkastus laudan ja
     * ottaa pelaajan käteen ensimmäisen laatan.
     *
     * @param pelaajanID 
     * @param ai 
     */
    public Pelaaja(int pelaajanID, boolean ai) {
        this.olenkoAI = ai;
        this.pelaajanID = pelaajanID;
        laatat = new PelaajanLaatat(pelaajanID);
        tarkastusLauta = new TarkastusLauta(pelaajanID);
       
        
        if (ai){
            pelaajaAI = new PelaajaAI(this); 
            valittuna = new Laatta(1, LaattojenMuodot.MALLI1, 1, pelaajanID);
        } else {
            valittuna = laatat.getSeuraavaLaatta();
        }
    }

    public PelaajaAI getPelaajaAI() {
        return pelaajaAI;
    }
    
    

    /**
     * Hakee pelaajan käteen automaattisesti seuraavan vapaan laatan
     */
    public void vaihdaValittuaSeuraavaan() {
        valittuna = laatat.getSeuraavaLaatta();
    }

    /**
     *
     * @param y
     * @param x Vaihtaa valittua laattaa laattavalitsimesta löytyvään laattaan
     * koordinaattien perusteella.
     */
    public void vaihdaValittuaLaattaa(int y, int x) {
        int laattaId = laatat.getLaattaValitsimesta(y, x);
        if (laattaId != GlobaalitMuuttujat.TYHJA) {
            laatat.palautaLaattaValitsimeen(valittuna.getId());
            laatat.poistaLaattaValitsemesta(laattaId);
            valittuna = laatat.getLaattaById(laattaId);
        }
    }

    /**
     * Hakee pelaajan sen hetkisen pistemäärän
     *
     * @return sen hetkinen pistemäärä
     */
    public int getPisteet() {
        OmaHashMap<Integer, Laatta> apu = laatat.getPelatutLaatat();
        int pisteet = 0;
        for (Laatta laatta : apu.getDatas()) {
            pisteet += laatta.getKoko();

        }
        return 89 - pisteet;
    }

    public int getPelaajantID() {
        return pelaajanID;
    }

    public PelaajanLaatat getLaatat() {
        return laatat;
    }

    public TarkastusLauta getTarkastusLauta() {
        return tarkastusLauta;
    }

    public Laatta getValittuna() {
        return valittuna;
    }

 

    public boolean getOlenkoAi() {
        return olenkoAI;
    }
}
