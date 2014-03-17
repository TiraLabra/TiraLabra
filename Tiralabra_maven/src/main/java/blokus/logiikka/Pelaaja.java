package blokus.logiikka;

import blokus.conf.GlobaalitMuuttujat;
import java.util.HashMap;

/**
 * Pelaaja luokka hallitsee pelaajan komponentteja. Ja laskee pelaajan sen
 * hetkisen pistemäärän.
 *
 * @author Simo Auvinen
 */
public class Pelaaja {

    private int pelaajanID;
    private PelaajanLaatat laatat;
    private TarkastusLauta tarkastusLauta;
    /**
     * Tällähetkellä "kädessä" oleva laatta
     */
    private Laatta valittuna;

    /**
     * Määrittää ID:n pelaajalle Luo pelaajalle laatat ja tarkastus
     * laudan ja ottaa pelaajan käteen ensimmäisen laatan.
     * @param id 
     */
    public Pelaaja(int pelaajanID) {
        this.pelaajanID = pelaajanID;
        laatat = new PelaajanLaatat(pelaajanID);
        tarkastusLauta = new TarkastusLauta(pelaajanID);
        valittuna = laatat.getSeuraavaLaatta();
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
        HashMap<Integer, Laatta> apu = laatat.getPelatutLaatat();
        int pisteet = 0;
        for (Laatta laatta : apu.values()) {
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
}
