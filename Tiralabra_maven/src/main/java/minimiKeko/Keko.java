package minimiKeko;

/**
 *
 * @param <D> Talletettavan datan tyyppi
 * @author Ilkimys
 */
public class Keko<D> {

    private Solmu[] keko;
    private int pituus;
    private int koko;

    /**
     * Luo uuden minimikeon
     * @param max keon maksimikoko
     */
    public Keko(int max) {
        pituus = max + 1;
        keko = new Solmu[pituus];
        koko = 0;
    }

    /**
     * Lisää kekoon tietyn avaimen ja siihen liittyvän datan
     * @param avain integer avain
     * @param data avaimeen viittaava data
     */
    public void lisaaKekoon(int avain, D data) {
        
        if (pituus > koko +1) {
            koko++;
            int i = koko;
            while (i > 1 && keko[parent(i)].getKey() > avain) {
                keko[i] = keko[parent(i)];
                i = parent(i);
            }
            keko[i] = new Solmu(avain, data);

        }
    }

    /**
     * Laskee tietyn avaimen arvoa
     * @param index laskettavan avaimen indeksi
     * @param uusiArvo uusiarvo avaimelle
     */
    public void laskeAvaimenArvoa(int index, int uusiArvo) {
        if (uusiArvo < keko[index].getKey()) {
            keko[index].setKey(uusiArvo);
            while (index > 1 && keko[parent(index)].getKey() > keko[index].getKey()) {
                Solmu apu = keko[index];
                keko[index] = keko[parent(index)];
                keko[parent(index)] = apu;
                index = parent(index);
            }
        }
    }

    /**
     * Etsii ensimmäisen indeksin missä on haluttu data
     * @param data etsittävä data
     * @return indeksi
     */
    public int dataIndeksi(D data) {
        for (int i = 0; i < keko.length; i++) {
            if (keko[i] != null) {
                if (data.equals(keko[i].getData())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Palauttaa dataa vastaavan avaimen arvon
     * @param data
     * @return
     */
    public int dataaVastaavaKey(D data) {
        for (int i = 0; i < keko.length; i++) {
            if (keko[i] != null) {
                if (data.equals(keko[i].getData())) {
                    return keko[i].getKey();
                }
            }
        }
        return -1;
    }

    /**
     * Palauttaa keon päällimäisen eli pienimmän avaimen ja poistaa sen keosta.
     * @return
     */
    public Solmu poll() {
        if (koko > 0) {
            Solmu min = keko[1];
            keko[1] = keko[koko];
            koko--;
            heapify(1);
            return min;
        } else {
            return null;
        }
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int isoin;
        if (r <= koko) {
            if (keko[l].getKey() < keko[r].getKey()) {
                isoin = l;
            } else {
                isoin = r;
            }

            if (keko[i].getKey() > keko[isoin].getKey()) {
                Solmu apu = keko[i];
                keko[i] = keko[isoin];
                keko[isoin] = apu;
                heapify(isoin);
            } else if (l == koko && keko[i].getKey() > keko[l].getKey()) {
                Solmu apu = keko[i];
                keko[i] = keko[l];
                keko[l] = apu;
            }
        }

    }

    private int parent(int index) {
        return index / 2;
    }

    private int right(int index) {
        return 2 * index;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return koko == 0;
    }

    /**
     *
     * @return
     */
    public int getKoko() {
        return koko;
    }

    
}
