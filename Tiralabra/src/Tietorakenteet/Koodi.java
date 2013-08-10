package Tietorakenteet;

public class Koodi {

    public long koodi;
    public int pituus;

    @Override
    public int hashCode() {
        int hashCode = (int) (koodi % Integer.MAX_VALUE);
        
        hashCode ^= (hashCode << 5) + (hashCode >> 2) + pituus;
        return hashCode;
    }

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
