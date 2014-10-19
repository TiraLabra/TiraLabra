package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Hakupuiden yliluokka. Tämä abstrakti luokka sisältää hakupuille yhteisen
 * toiminnallisuuden toteutuksen sekä puille yhteisiä apumetodeja.
 *
 * @author Markus
 */
public abstract class YliHakupuu implements Hakupuu {

    protected Puusolmu juuri;

    //ULOSPÄIN NÄKYVÄT
    public Puusolmu getJuuri() {
        return juuri;
    }

    public int getKoko() {
        return getKoko(juuri);
    }

    public int getKorkeus() {
        return getKorkeus(juuri);
    }

    public Puusolmu haeSuurin() {
        return getSuurin(juuri);
    }

    public Puusolmu haePienin() {
        return getPienin(juuri);
    }

    /**
     * Palauttaa totuusarvon puun tyhjyydestä.
     *
     * @return True jos puu tyhjä, muulloin false.
     */
    public boolean onTyhja() {
        return juuri == null;
    }

    public void tyhjenna() {
        juuri = null;
    }

    public boolean hae(int avain) {
        Puusolmu solmu = haeSolmu(avain);
        return solmu != null;
    }

    public void lisaaKaikki(int[] avaimet) {
        for (int i : avaimet) {
            lisaa(i);
        }
    }

    /**
     * Etsii ja palauttaa puusta avainta vastaavan solmun
     *
     * @param avain Haettavan solmun avain.
     * @return Avainta vastaava solmu.
     */
    protected PunamustaPuusolmu haeSolmu(int avain) {
        PunamustaPuusolmu solmu = null;
        if (!onTyhja()) {
            solmu = (PunamustaPuusolmu) juuri;
            while (solmu != null && solmu.getAvain() != avain) {
                if (avain < solmu.getAvain()) {
                    solmu = solmu.getVasen();
                } else {
                    solmu = solmu.getOikea();
                }
            }
        }
        return solmu;
    }

    public void poista(int avain) {
        PunamustaPuusolmu solmu = haeSolmu(avain);
        if (solmu == null) {
            return;
            // Jos solmulla 2 lasta vaihdetaan se itsensä edeltäjään.
        } else if (solmu.getVasen() != null && solmu.getOikea() != null) {
            PunamustaPuusolmu edeltaja = (PunamustaPuusolmu) edeltaja(solmu);
            solmu.setAvain(edeltaja.getAvain());
            solmu = edeltaja;
        }
        //Ny solmulla 0 tai 1 lasta.
        PunamustaPuusolmu lapsi = solmu.getVasen() == null ? solmu.getOikea() : solmu.getVasen();

        //Korvataan poistettava lapsella
        korvaaSolmu(solmu, lapsi);
    }

    // SOLMUJEN KÄSITTELYÄ
    protected int getKoko(Puusolmu solmu) {
        if (solmu == null) {
            return 0;
        } else {
            return 1 + getKoko(solmu.getVasen()) + getKoko(solmu.getOikea());
        }
    }

    protected int getKorkeus(Puusolmu solmu) {
        if (solmu == null) {
            return 0;
        } else {
            return 1 + Math.max(getKorkeus(solmu.getVasen()), getKorkeus(solmu.getOikea()));
        }
    }

    /**
     * Parannettu kiertotoiminto vasempaan joka myös vaihtaa juuren
     *
     * @param solmu Solmu jonka suhteen kierto tehdään
     */
    protected void vasenKierto(Puusolmu solmu) {
        Puusolmu oikea = solmu.getOikea();
        korvaaSolmu(solmu, oikea);
        solmu.setOikea(oikea.getVasen());
        if (oikea.getVasen() != null) {
            oikea.getVasen().setVanhempi(solmu);
        }
        oikea.setVasen(solmu);
        solmu.setVanhempi(oikea);
    }

    /**
     * Parannettu kiertotoiminto oikeaan joka myös vaihtaa juuren
     *
     * @param solmu Solmu jonka suhteen kierto tehdään
     */
    protected void oikeaKierto(Puusolmu solmu) {
        Puusolmu vasen = solmu.getVasen();
        korvaaSolmu(solmu, vasen);
        solmu.setVasen(vasen.getOikea());
        if (vasen.getOikea() != null) {
            vasen.getOikea().setVanhempi(solmu);
        }
        vasen.setOikea(solmu);
        solmu.setVanhempi(vasen);
    }

    /**
     * Apumetodi jokavaihtaa viitaukset solmuihin päittäin. Parametrina saadut
     * solmut siis vaihtavat keskenään paikkaa puussa.
     *
     * @param vanha Alipuun juurena toiminut solmu.
     * @param uusi Solmu joka tulee olemaan uusi alipuun juuri.
     */
    protected void korvaaSolmu(Puusolmu vanha, Puusolmu uusi) {
        if (vanha.getVanhempi() == null) {
            juuri = uusi;
        } else {
            if (PuuOperaatiot.onVasen(vanha)) {
                vanha.getVanhempi().setVasen(uusi);
            } else {
                vanha.getVanhempi().setOikea(uusi);
            }
        }
        if (uusi != null) {
            uusi.setVanhempi(vanha.getVanhempi());
        }
    }

    /**
     * Kertoo onko parametrin solmu jonkin solmun vasen lapsi
     *
     * @param solmu Solmu josta jonka suhde vanhempaan halutaan selvittää
     * @return True mikäli solmu on vasen lapsi jollekin solmulle, muulloin
     * false
     */
    protected boolean onVasen(Puusolmu solmu) {
        return solmu == null ? false : solmu.getVanhempi() == null ? false : solmu.getVanhempi().getVasen() == solmu;
    }

    /**
     * Kertoo onko parametrin solmu jonkin solmun oikea lapsi
     *
     * @param solmu Solmu josta jonka suhde vanhempaan halutaan selvittää
     * @return True mikäli solmu on oikea lapsi jollekin solmulle, muulloin
     * false
     */
    protected boolean onOikea(Puusolmu solmu) {
        return solmu == null ? false : solmu.getVanhempi() == null ? false : solmu.getVanhempi().getOikea() == solmu;
    }

    /**
     * Palauttaa avaimeltaan pienimmän parametrina saadusta solmusta alkavasta
     * alipuusta löytyvän solmun.
     *
     * @param solmu Puusolmu, jonka alipuusta arvoa etsitään.
     * @return Pienimmän avaimen omaava solmu.
     */
    protected Puusolmu getPienin(Puusolmu solmu) {
        if (solmu != null) {
            while (solmu.getVasen() != null) {
                solmu = solmu.getVasen();
            }
        }
        return solmu;
    }

    /**
     * Palauttaa avaimeltaan suurimman parametrina saadusta solmusta alkavasta
     * alipuusta löytyvän solmun.
     *
     * @param solmu Puusolmu, jonka alipuusta arvoa etsitään.
     * @return Suurimman avaimen omaava solmu.
     */
    protected Puusolmu getSuurin(Puusolmu solmu) {
        if (solmu != null) {
            while (solmu.getOikea() != null) {
                solmu = solmu.getOikea();
            }
        }
        return solmu;
    }

    /**
     * Palauttaa parametrina saadun solmun seuraajan puussa, mikäli sellainen on
     * olemassa.
     *
     * @param solmu Solmu, jonka seuraaja halutaan selvittää
     * @return Parametrina saadun solmun seuraaja tai null, mikäli sellaista ei
     * löydy.
     */
    protected Puusolmu seuraaja(Puusolmu solmu) {
        if (solmu == null) {
            return null;
        }
        if (solmu.getOikea() != null) {
            return getPienin(solmu.getOikea());
        }
        Puusolmu vanhempi = solmu.getVanhempi();
        while (onOikea(solmu)) {
            solmu = vanhempi;
            vanhempi = solmu.getVanhempi();
        }
        return vanhempi;
    }

    /**
     * Palauttaa parametrina saadun solmun edeltäjän puussa, mikäli sellainen on
     * olemassa.
     *
     * @param solmu Solmu, jonka edeltäjä halutaan selvittää
     * @return Parametrina saadun solmun edeltäjä tai null, mikäli sellaista ei
     * löydy.
     */
    protected Puusolmu edeltaja(Puusolmu solmu) {
        if (solmu == null) {
            return null;
        }
        if (solmu.getVasen() != null) {
            return getSuurin(solmu.getVasen());
        }
        Puusolmu vanhempi = solmu.getVanhempi();
        while (onVasen(solmu)) {
            solmu = vanhempi;
            vanhempi = solmu.getVanhempi();
        }
        return vanhempi;
    }

    /**
     * Palauttaa solmun isovanhemman mikäli sellainen on.
     *
     * @param solmu Solmu jonka isovanhempi halutaan selvittää
     * @return Löydetty solmu tai null mikäli parametrina saadulla solmulla ei
     * ole isovanhempaa
     */
    protected Puusolmu isovanhempi(Puusolmu solmu) {
        return solmu.getVanhempi() == null ? null : solmu.getVanhempi().getVanhempi();
    }

    /**
     * Palautaa solmun sisaruksen mikäli sellainen on.
     *
     * @param solmu Solmu jonka sisarus selvitetään
     * @return Tämän solmun sisarus tai NULL mikäli sellaista ei ole.
     */
    protected Puusolmu sisarus(Puusolmu solmu) {
        return solmu == null ? null : solmu.getVanhempi() == null ? null : solmu == solmu.getVanhempi().getVasen() ? solmu.getVanhempi().getOikea() : solmu.getVanhempi().getVasen();
    }

    protected void lisaa(Puusolmu uusi) {
        if (onTyhja()) {
            juuri = uusi;
        } else {
            //Puu ei ollut tyhjä
            Puusolmu solmu = this.juuri;
            //Etsitään oikea paikka uudelle solmulle.
            while (true) {
                if (uusi.getAvain() == solmu.getAvain()) {
                    return;
                } else if (uusi.getAvain() < solmu.getAvain()) {
                    if (solmu.getVasen() == null) {
                        solmu.setVasen(uusi);
                        break;
                    } else {
                        solmu = solmu.getVasen();
                    }
                } else {
                    if (solmu.getOikea() == null) {
                        solmu.setOikea(uusi);
                        break;
                    } else {
                        solmu = solmu.getOikea();
                    }
                }
            }
            uusi.setVanhempi(solmu);
        }
    }

    //TULOSTUSTOIMINTOJA
    public void tulostaEsijarjestys() {
        esijarjestys(juuri);
    }

    public void tulostaSisajarjestys() {
        sisajarjestys(juuri);
    }

    public void tulostaJalkijarjestys() {
        jalkijarjestys(juuri);
    }

    /**
     * Tulostaa annetusta juuresta alkavan puun esijärjestyksessä.
     *
     * @param juuri Tulostettavan puun juuri.
     */
    private void esijarjestys(Puusolmu juuri) {
        if (juuri != null) {
            System.out.print(juuri.getAvain() + " ");
            esijarjestys(juuri.getVasen());
            esijarjestys(juuri.getOikea());
        }
    }

    /**
     * Tulostaa annetusta juuresta alkavan puun sisäjärjestyksessä.
     *
     * @param juuri Tulostettavan puun juuri.
     */
    private void sisajarjestys(Puusolmu juuri) {
        if (juuri != null) {
            sisajarjestys(juuri.getVasen());
            System.out.print(juuri.getAvain() + " ");
            sisajarjestys(juuri.getOikea());
        }
    }

    /**
     * Tulostaa annetusta juuresta alkavan puun jälkijärjestyksessä.
     *
     * @param juuri Tulostettavan puun juuri.
     */
    private void jalkijarjestys(Puusolmu juuri) {
        if (juuri != null) {
            jalkijarjestys(juuri.getVasen());
            jalkijarjestys(juuri.getOikea());
            System.out.print(juuri.getAvain() + " ");
        }
    }
}
