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
     * @throws IllegalArgumentException jos annettu koodin pituus on pienempi kuin edellinen annettu pituus
     * @see <a href="https://en.wikipedia.org/wiki/Canonical_Huffman_code">https://en.wikipedia.org/wiki/Canonical_Huffman_code</a>
     */
    public long muodostaKanoninenHuffmanKoodi(int nykyinenPituus) {
        if (nykyinenPituus < vanhaPituus) {
            throw new IllegalArgumentException("Annettu koodin pituus pienempi kuin edellinen");
        }
        ++koodi;
        
        if (nykyinenPituus > vanhaPituus) {
            koodi = koodi << (nykyinenPituus - vanhaPituus);
            vanhaPituus = nykyinenPituus;
        }
     
        // bittien paikka on vaihdettava koska purkaja aloittaa koodin lukemisen eri puolelta ja ei muuten löydä vastaavuutta
        return vaihdaBittiJarjestys(koodi, vanhaPituus);
    }
    /**
     * Kääntää bittikuvion ympäri, esim 10001000 -> 00010001. Tämä on tarpeellista johtuen siitä miten purkaaja lukee bittejä tiedostosta
     * @param koodi käännettävä bittikuvio
     * @param pituus kuinka monta bittiä on merkitseviä
     * @return käännetty bittikuvio
     */
    private long vaihdaBittiJarjestys(long koodi, int pituus) {
        long uusiKoodi = 0L;

        for (int i = 0; i < pituus; ++i) {
            uusiKoodi = BittiUtility.tallennaBitinArvoPaikalle(uusiKoodi, BittiUtility.haeBitinArvoPaikasta(koodi, i), pituus - 1 - i);
        }
        return uusiKoodi;
    }
}
