package com.mycompany.Tiralabra_maven.logiikka.paikkaKeko;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * Oman tietorakenteen (minimikeko) toteutus. Keon alkiot
 * tyyppiä Paikka.
 */
public class OmaKekoAlkionaPaikka extends MinKekoAlkionaPaikka {

    private Paikka[] kekoTaulukko; //HUOM. kekotaulukko alkaa indeksistä 1 !!!
    private int heapSize;

    public OmaKekoAlkionaPaikka() {
        this.kekoTaulukko = new Paikka[1000000];
        this.heapSize = 0;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void vaihda(int i, int j) {
        Paikka apuPaikka;
        apuPaikka = this.kekoTaulukko[i];
        this.kekoTaulukko[i] = this.kekoTaulukko[j];
        this.kekoTaulukko[j] = apuPaikka;
        this.kekoTaulukko[i].heapIndex = i;
        this.kekoTaulukko[j].heapIndex = j;
    }

    private void heapHeapify(int i) {

        int left;
        int right;
        int smallest;

        left = left(i);
        right = right(i);
        if (right <= this.heapSize) {
            if (this.kekoTaulukko[left].compareTo(this.kekoTaulukko[right]) < 0) {
                smallest = left;
            } else {
                smallest = right;
            }
            if (this.kekoTaulukko[i].compareTo(this.kekoTaulukko[smallest]) > 0) {
                vaihda(i, smallest);
                heapHeapify(smallest);
            }
        } else if (left == this.heapSize && this.kekoTaulukko[i].compareTo(this.kekoTaulukko[left]) > 0) {
            vaihda(i, left);
        }
    }

    /**
     * Kekoon lisätään kekoalkio.
     *
     * @param kekoAlkio lisättävä kekoalkio
     */
    @Override
    public void heapInsert(Paikka kekoAlkio) {
//////        System.out.println("heapInsert");
        int i;
        this.heapSize++;
        i = this.heapSize;
        while (i > 1 && this.kekoTaulukko[parent(i)].compareTo(kekoAlkio) > 0) {
            this.kekoTaulukko[i] = this.kekoTaulukko[parent(i)];
            this.kekoTaulukko[i].heapIndex = i;
            i = parent(i);
        }

        kekoAlkio.heapIndex = i;
        this.kekoTaulukko[i] = kekoAlkio;

//////        System.out.println("i=" + i + " " + this.kekoTaulukko[i].etaisyysAlkuun + "," + this.kekoTaulukko[i].etaisyysLoppuun);
//////
//////        this.testTulostaKekoTaulukko();

    }

    /**
     * Keosta poistetaan huipulla oleva pienimmän avaimen sisältävä kekoalkio.
     *
     * @return keosta poistettu pienimmän avaimen sisältänyt kekoalkio
     */
    @Override
    public Paikka heapDelMin() {
        Paikka min = this.kekoTaulukko[1];
        this.kekoTaulukko[1] = this.kekoTaulukko[this.heapSize];
        this.kekoTaulukko[1].heapIndex = 1;
        this.heapSize--;
        this.heapHeapify(1);
        return min;
    }

    /**
     * Pienentää minimikeon kekoalkion avainta ja asettaa kekoalkion oikealle
     * paikalle keossa. Algoritmeissa Dijkstra ja Astar tätä metodia kutsutaan
     * VAIN kun Paikka.etaisyysAlkuun on muuttunut (ja sitämyötä myös kekoalkion
     * avain on muuttunut), jolloin algorotmien metodi relax palauttaa arvon
     * true.
     *
     * @param kekoAlkio oikealle paikalle asetettava kekoalkio
     */
    @Override
    public void heapDecreaseKey(Paikka kekoAlkio) {
//        Dijkstrassa ja Astarissa AINA PIENENNETAAN etaisyysarviota, joten
//        nyt ei tarvitse testata onko uusi avain pienempi kuin olemassa oleva
        int i = kekoAlkio.heapIndex;
        while (i > 1 && this.kekoTaulukko[parent(i)].compareTo(kekoAlkio) > 0) {
            vaihda(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Tarkastaa onko keko tyhjä.
     *
     * @return palautetaan true, jos keko on tyhjä
     */
    @Override
    public boolean heapIsEmpty() {
        if (this.heapSize == 0) {
            return true;
        }
        return false;
    }

    /**
     * Testausta varten.
     */
    public int getHeapSize() {
        return this.heapSize;
    }

    /**
     * Testausta varten.
     */
    public int testParent(int i) {
        return this.parent(i);
    }

    /**
     * Testausta varten.
     */
    public int testRight(int i) {
        return this.right(i);
    }

    /**
     * Testausta varten.
     */
    public int testLeft(int i) {
        return this.left(i);
    }

    /**
     * Testausta varten.
     */
    public void testTulostaKeko() {
        System.out.println("testTulostaKeko");
        Paikka poistettavaPaikka;
        while (!this.heapIsEmpty()) {
            poistettavaPaikka = this.heapDelMin();
            System.out.println(poistettavaPaikka.etaisyysAlkuun + ", " + poistettavaPaikka.etaisyysLoppuun + " ja heapIndex=" + poistettavaPaikka.heapIndex);
        }
    }

    /**
     * Testausta varten.
     */
    public void testTulostaKekoTaulukko() {
        System.out.println("testTulostaKekoTaulukko");
        System.out.println("heapSize: " + this.heapSize);
        for (int i = 1; i <= heapSize; i++) {
            System.out.println("i=" + i + ", etAlk=" + this.kekoTaulukko[i].etaisyysAlkuun + ", etLop=" + this.kekoTaulukko[i].etaisyysLoppuun + " ja heapIndex=" + this.kekoTaulukko[i].heapIndex);
        }
    }
    
    /**
     * Testausta varten.
     */
    public void testHeapify(int i) {
        this.heapHeapify(i);
    }
    
}
