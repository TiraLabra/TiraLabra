package Tietorakenteet;

public class OmaTreeNode<K, V> {

    private final K AVAIN;
    private final V ARVO;
    private final OmaTreeNode VASEN;
    private final OmaTreeNode OIKEA;

    public OmaTreeNode(K avain, V arvo) {
        
        // OmaTreeNode(avain, arvo, null, null) ei toimi
        // javan genericsit on rasittavia
        AVAIN = avain;
        ARVO = arvo;
        VASEN = null;
        OIKEA = null;
    }

    public OmaTreeNode(K avain, V arvo, OmaTreeNode vasenLapsi, OmaTreeNode oikeaLapsi) {
        AVAIN = avain;
        ARVO = arvo;
        VASEN = vasenLapsi;
        OIKEA = oikeaLapsi;
    }

    public K haeAvain() {
        return AVAIN;
    }

    public V haeArvo() {
        return ARVO;
    }

    public OmaTreeNode vasenLapsi() {
        return VASEN;
    }

    public OmaTreeNode oikeaLapsi() {
        return OIKEA;
    }
    
    public boolean onLehti() {
        return VASEN == null && OIKEA == null;
    }
}
