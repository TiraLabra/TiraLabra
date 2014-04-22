package hashMap;

import linkitettyLista.Lista;

/**
 * HashMap toteutus
 *
 * @param <A>
 * @param <B>
 * @author Ilkimys
 */
public class OmaHashMap<A, B> {

    private int pituus = 100;
    private int koko = 0;
    private Entry[] map;

    /**
     * Asettaa oletusarvoisen pituuden Mapille
     */
    public OmaHashMap() {
        this.map = new Entry[pituus];

    }

    /**
     * Palauttaa avainta vastaavan datan. Null jos avainta ei löydy
     *
     * @param avain
     * @return
     */
    public B get(A avain) {
        Entry<A, B> tulos = map[hajautusFunktio(avain)];
        while (tulos != null && !avain.equals(tulos.getAvain())) {
            tulos = tulos.haeSeuraava();
        }
        if (tulos != null) {
            return tulos.getData();
        } else {
            return null;
        }
    }

    /**
     * Asettaa uuden entryn mappiin. Jos avain on jo mapissa, niin korvaa siinä
     * olevan datan.
     *
     * @param avain avain joka hajautetaan
     * @param data
     */
    public void put(A avain, B data) {
        int index = hajautusFunktio(avain);
        Entry<A, B> tulos = map[index];

        if (tulos == null) {
            map[index] = new Entry<A, B>(avain, data);
        } else {
            while (true) {
                if (tulos.getAvain().equals(avain)) {
                    tulos.setData(data);
                    break;
                } else if (tulos.haeSeuraava() == null) {
                    tulos.setSeuraava(new Entry<A, B>(avain, data));
                    break;
                }
                tulos = tulos.haeSeuraava();
            }
        }
        koko++;
    }

    /**
     * Poistaa avainta vastaavan entryn mapistä
     *
     * @param avain
     */
    public void remove(A avain) {
        int index = hajautusFunktio(avain);
        Entry<A, B> tulos = map[index];
        if (tulos != null && avain.equals(tulos.getAvain())) {
            map[index] = tulos.haeSeuraava();
            koko--;
        } else {
            Entry<A, B> edellinen = tulos;
            while (tulos != null && !avain.equals(tulos.getAvain())) {
                edellinen = tulos;
                tulos = tulos.haeSeuraava();
            }
            if (tulos != null && avain.equals(tulos.getAvain())) {
                edellinen.setSeuraava(tulos.haeSeuraava());
                koko--;
            }
        }

    }

    /**
     * Palauttaa kaikki mapissa olevat datat linkitettynä listana
     *
     * @return Lista<B>
     */
    public Lista<B> getDatas() {
        Lista<B> values = new Lista<B>();
        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                Entry<A, B> e = map[i];
                while (true) {
                    values.lisaaListaan(e.getData());
                    if (e.haeSeuraava() == null) {
                        break;
                    }
                    e = e.haeSeuraava();
                }
            }

        }

        return values;
    }

    /*
     * Laskee avainta vastaavan indeksin 
     * @return avainta vastaava indeksi
     */
    private int hajautusFunktio(A avain) {
        return avain.hashCode() % map.length;
    }

    /**
     * Tyhjentää Mapin kokonaan
     */
    public void tyhjenna() {
        this.map = new Entry[pituus];
        koko = 0;

    }

    /**
     * Palauttaa mapin sen hetkisen koon
     *
     * @return int koko
     */
    public int getKoko() {
        return koko;
    }

    /**
     * Tarkistaa onko mappi tyhjä
     *
     * @return true jos tyhjä
     */
    public boolean isEmpty() {
        if (koko == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
     *  
     * @return true jos avain löytyy mapista
     */

    public boolean sisaltaaAvaimen(A avain) {
        int index = hajautusFunktio(avain);
        Entry<A, B> tulos = map[index];
        if (tulos == null) {
            return false;
        }
            while (true) {
                if (tulos.getAvain().equals(avain)) {
                   return true;                  
                } else if (tulos.haeSeuraava() == null) {
                    return false;
                   
                }
                tulos = tulos.haeSeuraava();
            }
        
            
    }
}
