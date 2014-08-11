package huffmanKoodaaja.kasittely;

import huffmanKoodaaja.kasittely.tietorakenteet.Frekvenssitaulu;
import huffmanKoodaaja.kasittely.tietorakenteet.Puu;

public class Algoritmi {
    private Tiedosto tiedosto;
    private boolean pakkaus;

    public void setTiedosto(Tiedosto tiedosto) {
        this.tiedosto = tiedosto;
        this.pakkaus = tiedosto.isPakkaus();
    }

    public void kasittele() {
        if (pakkaus) {
            pakkaa();
        } else {
            pura();
        }
    }

    private void pakkaa() {
        Frekvenssitaulu taulukko = new Frekvenssitaulu();
        tiedosto.lueTaulukoksi(taulukko);
        Puu puu = new Puu();
        puu.luo(taulukko.luoJono());
        tiedosto.kirjoitaTaulukko(taulukko);
        tiedosto.kirjoitaPakattu(puu);
    }

    private void pura() {
        Frekvenssitaulu taulukko = new Frekvenssitaulu();
        tiedosto.lueTaulukko(taulukko);
        Puu puu = new Puu();
        puu.luo(taulukko.luoJono());
        tiedosto.kirjoitaPurettu(puu);
    }
    
}
