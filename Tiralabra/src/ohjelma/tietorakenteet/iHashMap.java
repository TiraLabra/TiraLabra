/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.tietorakenteet;

/**
 *
 * @author kkivikat
 */
public class iHashMap<K, V> {

    private int arvojenmaara = 0;
    private iHashMapTaulu<K, V>[] taulu;

    public iHashMap() {
        taulu = new iHashMapTaulu[5];
    }

    /**
     * Tarkastaa onko HashMap tyhjä.
     */
    public boolean onkoTyhja() {
        return arvojenmaara == 0;
    }

    /**
     * Palauttaa HashMapissa olevien arvojen määrän.
     */
    public int getArvojenMaara() {
        return arvojenmaara;
    }

    public int haeIndeksi(int h, int pituus) {
        return h & (pituus - 1);
    }

    /**
     * Palauttaa avaimelle kuuluvan arvon.
     */

    public V get(Object avain) {
        if (avain == null) {
            return getNullArvo();
        }
        int hash = avain.hashCode();                                            // Haetaan avaimen hashkoodia vastaava kori/indeksi
        for (iHashMapTaulu<K, V> e = taulu[haeIndeksi(hash, taulu.length)]; e != null; e = e.seuraava) {
            Object k;
            if (e.hash == hash && ((k = e.avain) == avain || avain.equals(k))) {// käydään läpi vain tämä kori, koska hashkoodin perusteella tiedetään
                return e.arvo;                                                  // että arvon on oltava tämän korin listan jossakin kohdassa.
            }
        }
        return null;
    }

    /**
     * Lisää avain-arvo parin.
     */

    public V put(K avain, V arvo) {
        if (avain == null) {
            return putNull(arvo);
        }

        int hash = avain.hashCode();
        int koriIndeksi = haeIndeksi(hash, taulu.length);                       // lasketaan mihin kohtaan taulukkoa arvot sijoitetaan.

        for (iHashMapTaulu<K, V> e = taulu[koriIndeksi]; e != null; e = e.seuraava) {
            Object k;
            if (e.hash == hash && ((k = e.avain) == avain || avain.equals(k))) { // katsotaan jos taulukossa on jo samalla avaimella asetettu arvo
                V vanhaArvo = e.arvo;                                            // jos on, korvataan vanha arvo uudella.
                e.arvo = arvo;
                return vanhaArvo;
            }
        }
        uusiEntry(hash, avain, arvo, koriIndeksi);                              // muuten lisäys
        ++arvojenmaara;
        return null;
    }

    /**
     * Lopullinen avain-arvo parin lisäys mikäli arvoa ei ollut vielä olemassa
     * eikä se ole null.
     */
    public void uusiEntry(int hash, K avain, V arvo, int bucketIndeksi) {
        iHashMapTaulu<K, V> e = taulu[bucketIndeksi];
        taulu[bucketIndeksi] = new iHashMapTaulu<K, V>(hash, avain, arvo, e);
    }

    private V putNull(V arvo) {
        for (iHashMapTaulu<K, V> e = taulu[0]; e != null; e = e.seuraava) {
            if (e.seuraava == null) {
                V vanhaArvo = e.arvo;
                e.arvo = arvo;
                return vanhaArvo;
            }
        }
        uusiEntry(0, null, arvo, 0);
        return null;
    }

    /**
     * Hakee nullille arvon.
     */
    private V getNullArvo() {
        for (iHashMapTaulu<K, V> e = taulu[0]; e != null; e = e.seuraava) {
            if (e.avain == null) {
                return e.arvo;
            }
        }
        return null;
    }
}