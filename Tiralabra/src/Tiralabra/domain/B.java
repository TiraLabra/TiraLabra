package Tiralabra.domain;

import Tiralabra.util.ALista;

/**
 * Toteuttaa 2-3 B-puun. 2-3 B-puussa solmulla voi olla 1-2 arvoa, ja 0-3 lasta.
 *
 * @author Pia Pakarinen
 */
public class B implements Puu {

    /**
     * Puun juurisolmu.
     */
    private SolmuB juuri;

    /**
     * Luo uuden 2-3 B-puun ja tälle juurisolmun annetulla arvolla.
     *
     * @param emo juurisolmun arvo
     */
    public B(int emo) {
        juuri = new SolmuB(emo, null);
    }

    @Override
    public String tulostaArvot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(int key) {
        SolmuB i = juuri;
        while (true) {
            //uuden solmu arvo löytyy jo puusta, ei tehdä mitään
            if (i.getEnsimmainenArvo() == key) {
                return;
            } else if (i.solmunKoko() == 2 && i.getToinenArvo() == key) {
                return;
            }

            //uusi solmu on pienempi kuin nykyisen solmun pienin
            if (key < i.getEnsimmainenArvo()) {
                //ollaan lehtisolmussa
                if (i.getVasen() == null) {

                    break;
                }

                i = i.getVasen();
            } //suurempi kuin nykyisen solmun pienin
            else if (key > i.getEnsimmainenArvo()) {
                if (i.solmunKoko() == 2) {
                    //pienempi kuin nykyisen solmun toinen arvo
                    if (i.getToinenArvo() > key) {
                        //ollaan lehtisolmussa
                        if (i.getKeski() == null) {

                            break;
                        }
                        i = i.getKeski();
                    } //suurempi kuin kumpikaan nykyisen solmun arvoista
                    else {
                        //ollaan lehtisolmussa
                        if (i.getOikea() == null) {
                            lisaaOikealle(i, key);
                            break;
                        }
                        i = i.getOikea();
                    }
                } //solmussa vain yksi arvo, uusi sitä suurempi
                else {
                    i = i.getOikea();
                }
            }
        }

    }

    @Override
    public void delete(int key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean search(int key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Lisää solmun, kun uuden solmun paikka on nykyisen solmun oikeana lapsena.
     *
     * @param i solmu, jolle uusi solmu tulee oikeaksi lapseksi
     * @param key uuden solmun arvo
     */
    private void lisaaOikealle(SolmuB i, int key) {
        //luodaan apulista, joka selvittää käsiteltävien arvojen järjestyksen
        ALista apulista = new ALista(key);
        apulista.lisaa(i.getEnsimmainenArvo());
        apulista.lisaa(i.getToinenArvo());
        int pienin = apulista.getLista().getArvo();
        int keski = apulista.getLista().getNext().getArvo();

        //lehtisolmussa on vielä tilaa
        if (i.solmunKoko() < 2) {
            i.lisaaArvo(key);
        } //parenttiin mahtuu; lisätään sinne keskimmäinen arvo, pienimmästä tulee uusi
        // keskimmäinen lapsi
        else if (i.getParent().solmunKoko() < 2) {
            i.getParent().lisaaArvo(keski);
            i.getParent().setKeski(new SolmuB(pienin, i));
            if (key == keski) {
                i.poistaArvo(pienin);
            } else {
                i.poistaArvo(keski);
            }
        } else {
            //parent ja lehti molemmat täynnä, keskimmäinen arvo matkustaa puussa ylöspäin
            if (keski != key) {
                i.poistaArvo(keski);
                if (i.getEnsimmainenArvo() == pienin) {
                    i.lisaaArvo(apulista.getLista().getNext().getNext().getArvo());
                } else {
                    i.lisaaArvo(pienin);
                }
            }
            matkusta(i.getParent(), keski);
        }
    }

    /**
     * Kutsutaan tilanteessa, jossa uutta arvoa ei voida asettaa lehtisolmuun
     * tai tämän vanhempaan. Matkustaa puussa ylöspäin kunnes arvolle löytyy
     * paikka tai uusi juuri luodaan.
     *
     * @param s käsitteillä oleva solmu
     * @param key arvo vailla paikkaa
     */
    private void matkusta(SolmuB s, int key) {
        if (s.solmunKoko() < 2) {
            s.lisaaArvo(key);
            return;
        }
    }

}