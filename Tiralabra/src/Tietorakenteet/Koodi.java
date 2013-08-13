package Tietorakenteet;
/**
 * Apuluokka huffman-koodeja varten
 * Sisältää koodi-kentän johonka koodi tallennetaan bitteinä; max 64 bittiä pitkä koodi sekä montako bittiä on merkitseviä koodissa.
 * Bitit tallennetaan LSB -> MSB-järjestyksessä eli ensiksi vähiten merkitsevät bitit otetaan käyttöön
 * 
 */
public class Koodi {

    public long koodi;
    public int pituus;
    /**
     * Hash code hashmappia varten. Katso lähdeluettelosta algoritmin lähde.
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hashCode = (int) (koodi % Integer.MAX_VALUE);
        
        hashCode ^= (hashCode << 5) + (hashCode >> 2) + pituus;
        return hashCode;
    }
    /**
     * Onko verrattava objekti sama kuin tämä
     * @param obj Verrattava objekti
     * @return Onko objekti sama
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Koodi other = (Koodi) obj;
        
        if (this.koodi != other.koodi) {
            return false;
        }
        
        if (this.pituus != other.pituus) {
            return false;
        }
        return true;
    }
}
