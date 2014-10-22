package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Hakupuiden yliluokka. Tämä abstrakti luokka sisältää hakupuille yhteisen
 * toiminnallisuuden toteutuksen sekä puille yhteisiä apumetodeja.
 *
 * @author Markus
 */
public abstract class YliHakupuu implements Hakupuu {

    /**
     * Puun juurisolmu. Mikäli juuri on null, puu on tyhjä.
     */
    protected Puusolmu juuri;

    public Puusolmu getJuuri() {
        return juuri;
    }

    public int getKoko() {
        return getKoko(juuri);
    }

    public int getKorkeus() {
        return getKorkeus(juuri);
    }

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

    public void poista(int avain) {
        poistaSolmu(avain);
    }

    /**
     * Etsii ja palauttaa puusta avainta vastaavan solmun.
     *
     * @param avain Haettavan solmun avain.
     * @return Avainta vastaava solmu. Tai null mikäli avaimen arvoa ei löydy
     * puusta.
     */
    protected Puusolmu haeSolmu(int avain) {
        Puusolmu solmu = null;
        if (!onTyhja()) {
            solmu = juuri;
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

    /**
     * Poistaa avainta vastaavan solmun puusta ja palauttaa sen.
     *
     * @param avain Puusta poistettavan solmun avaimen arvo.
     * @return Puusta poistettu solmu tai null, mikäli mitään ei poistettu.
     */
    protected Puusolmu poistaSolmu(int avain) {
        Puusolmu solmu = haeSolmu(avain);
        if (solmu == null) {
            return null;
            // Jos solmulla 2 lasta vaihdetaan se itsensä edeltäjään.
        } else if (solmu.getVasen() != null && solmu.getOikea() != null) {
            Puusolmu edeltaja = edeltaja(solmu);
            solmu.setAvain(edeltaja.getAvain());
            solmu = edeltaja;
        }
        //Ny solmulla 0 tai 1 lasta.
        Puusolmu lapsi = solmu.getVasen() == null ? solmu.getOikea() : solmu.getVasen();

        //Korvataan poistettava lapsella
        korvaaSolmu(solmu, lapsi);
        return solmu;
    }

    /**
     * Laskee annetusta solmusta alkavan alipuun koon rekursiivisesti ja
     * palauttaa saadun arvon kokonaislukuna.
     *
     * @param solmu Solmu jonka alipuun koko lasketaan.
     * @return Alipuun solmujen määrä tai 0, mikäli annettu solmu on null.
     */
    protected int getKoko(Puusolmu solmu) {
        if (solmu == null) {
            return 0;
        } else {
            return 1 + getKoko(solmu.getVasen()) + getKoko(solmu.getOikea());
        }
    }

    /**
     * Laskee annetusta solmusta alkavan alipuun korkeuden rekursiivisesti ja
     * palauttaa saadun arvon kokonaislukuna.
     *
     * @param solmu Solmu jonka alipuun korkeus lasketaan.
     * @return Alipuun tasojen määrä eli korkeus tai 0, mikäli annettu solmu on
     * null.
     */
    protected int getKorkeus(Puusolmu solmu) {
        if (solmu == null) {
            return 0;
        } else {
            return 1 + Math.max(getKorkeus(solmu.getVasen()), getKorkeus(solmu.getOikea()));
        }
    }

    /**
     * Vaihtaa solmun ja sen oikean lapsen paikkaa puussa. Lisäksi päivittää
     * asianmukaisten solmujen viitemuuttujien arvot vastaamaan uutta
     * tilannetta.
     *
     * @param solmu Solmu joka vaihtaa paikkaa oikean lapsensa kanssa.
     */
    protected void vasenKierto(Puusolmu solmu) {
        if (solmu == null ? true : solmu.getOikea() == null) {
            return;
        }
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
     * Vaihtaa solmun ja sen vasemman lapsen paikkaa puussa. Lisäksi päivittää
     * asianmukaisten solmujen viitemuuttujien arvot vastaamaan uutta
     * tilannetta.
     *
     * @param solmu Solmu joka vaihtaa paikkaa vasemman lapsensa kanssa.
     */
    protected void oikeaKierto(Puusolmu solmu) {
        if (solmu == null ? true : solmu.getVasen() == null) {
            return;
        }
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
     * Apumetodi joka korvaa parametrina saadun solmun puussa toisella
     * parametrina saadulla solmulla. Päivittää puun juuren arvon, mikäli
     * tarpeen.
     *
     * @param vanha Solmu joka korvataan.
     * @param uusi Solmu joka tulee korvattavan solmun paikalle.
     */
    protected void korvaaSolmu(Puusolmu vanha, Puusolmu uusi) {
        if (vanha.getVanhempi() == null) {
            juuri = uusi;
        } else {
            if (onVasen(vanha)) {
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
     * Kertoo onko parametrin solmu jonkin solmun vasen lapsi.
     *
     * @param solmu Solmu jonka suhde vanhempaan halutaan selvittää
     * @return True mikäli solmu on vasen lapsi jollekin solmulle, muulloin
     * false.
     */
    protected boolean onVasen(Puusolmu solmu) {
        return solmu == null ? false : solmu.getVanhempi() == null ? false : solmu.getVanhempi().getVasen() == solmu;
    }

    /**
     * Kertoo onko parametrin solmu jonkin solmun oikea lapsi
     *
     * @param solmu Solmu jonka suhde vanhempaan halutaan selvittää
     * @return True mikäli solmu on oikea lapsi jollekin solmulle, muulloin
     * false.
     */
    protected boolean onOikea(Puusolmu solmu) {
        return solmu == null ? false : solmu.getVanhempi() == null ? false : solmu.getVanhempi().getOikea() == solmu;
    }

    /**
     * Palauttaa avaimeltaan avaimeltaan pienimmän parametrina saadusta solmusta
     * alkavasta alipuusta löytyvän solmun.
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

    /**
     * Asettaa parametrina saadun solmun oikealle paikalle puussa. Asettaa myös
     * asianmukaiset viitemuuttujien arvot asianmukaisissa solmuissa.
     *
     * @param uusi Puuhun lisättävä solmu.
     * @return True jos solmu lisätään, muulloin false.
     */
    protected boolean lisaa(Puusolmu uusi) {
        if (onTyhja()) {
            juuri = uusi;
        } else {
            //Puu ei ollut tyhjä
            Puusolmu solmu = this.juuri;
            //Etsitään oikea paikka uudelle solmulle.
            while (true) {
                if (uusi.getAvain() == solmu.getAvain()) {
                    return false;
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
        return true;
    }

    //TULOSTUSTOIMINTOJA
    /**
     * Tulostaa puun solmujen avainten arvot esijärjestyksessä
     */
    public void tulostaEsijarjestys() {
        esijarjestys(juuri);
    }

    /**
     * Tulostaa puun solmujen avainten arvot sisäjärjestyksessä
     */
    public void tulostaSisajarjestys() {
        sisajarjestys(juuri);
    }

    /**
     * Tulostaa puun solmujen avainten arvot jälkijärjestyksessä
     */
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
