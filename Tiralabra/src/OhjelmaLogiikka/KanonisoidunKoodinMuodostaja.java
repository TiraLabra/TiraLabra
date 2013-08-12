package OhjelmaLogiikka;

import OhjelmaLogiikka.BittiUtility;

public class KanonisoidunKoodinMuodostaja {

    private long koodi;
    private int vanhaPituus;

    public KanonisoidunKoodinMuodostaja() {
        koodi = -1;
        vanhaPituus = 0;
    }

    public long muodostaKoodi(int nykyinenPituus) {
        ++koodi;

        if (nykyinenPituus > vanhaPituus) {
            koodi = koodi << (nykyinenPituus - vanhaPituus);
            vanhaPituus = nykyinenPituus;
        }

        // bittien paikka on vaihdettava koska purkaja aloittaa koodin lukemisen eri puolelta ja ei muuten löydä vastaavuutta
        return vaihdaBittiJarjestys(koodi, vanhaPituus);
    }

    private long vaihdaBittiJarjestys(long koodi, int pituus) {
        long uusiKoodi = 0L;

        for (int i = 0; i < pituus; ++i) {
            uusiKoodi = BittiUtility.tallennaBitinArvoPaikalle(uusiKoodi, BittiUtility.haeBitinArvoPaikasta(koodi, i), pituus - 1 - i);
        }
        return uusiKoodi;
    }
}
