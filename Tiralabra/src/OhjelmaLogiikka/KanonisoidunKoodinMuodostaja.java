package OhjelmaLogiikka;

/**
 * Apuluokka joka muodostaa kanonisoidut huffman-koodit.
 *
 */
public class KanonisoidunKoodinMuodostaja {

    private long koodi;
    private int vanhaPituus;

    /**
     * Konstruktori.
     */
    public KanonisoidunKoodinMuodostaja() {
        koodi = -1;
        vanhaPituus = 0;
    }

    /**
     * Muodostaa kanonisen koodin. Uusi koodi riippuu edellisestä kutsusta joten
     * metodia on kutsuttava koodeille oikeassa järjestyksessä (lyhyistä
     * koodeista pitkiin, samanpituiset koodit blokkien suuruusjärjestyksessä)
     *
     * @param nykyinenPituus käsiteltävän koodin pituus biteissä.
     * @return uusi koodi tallennettuna long-muuttujaan
     * @see <a href="https://en.wikipedia.org/wiki/Canonical_Huffman_code">https://en.wikipedia.org/wiki/Canonical_Huffman_code</a>
     */
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
