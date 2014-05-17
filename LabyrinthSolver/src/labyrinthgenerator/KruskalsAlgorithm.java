package labyrinthgenerator;

import main.Labyrinth;

/**
 * Modifioitu satunnaistettu Kruskalin algoritmi labyrintin generoimiseen.
 *
 * @author Juri Kuronen
 */
public class KruskalsAlgorithm extends LabyrinthGenerator {

    /**
     * Maskit kaaren suunnille. 1 = NORTH, 2 = EAST, 4 = SOUTH, 8 = WEST.
     */
    private final byte[] masks = {1, 2, 4, 8};

    /**
     * @param l Labyrintti, jolle algoritmi ajetaan.
     * @see
     * labyrinthgenerator.LabyrinthGenerator#LabyrinthGenerator(main.Labyrinth)
     */
    public KruskalsAlgorithm(Labyrinth l) {
        super(l);
    }

    /**
     * @see labyrinthgenerator.LabyrinthGenerator#routine()
     */
    @Override
    public void routine() {
        System.out.println("Kruskal's Algorithm");
        super.routine();
    }

    /**
     * Joukkoalkion olio Kruskalin algoritmia varten.
     */
    private class SetElement {

        /**
         * Joukon ID.
         */
        final int id;
        /**
         * Reitti joukon juureen.
         */
        SetElement root;
        /**
         * Kuinka monta alkiota on yhdistettynä tähän joukkoon, kun tämä alkio
         * on juuri. Saadakseen oikean luvun, täytyy ensin mennä koko joukon
         * juureen.
         */
        int elementsJoinedByRoot;

        /**
         * Konstruktori asettaa joukon ID:ksi annetun arvon, tämän alkion
         * juureksi, ja alkioiden lukumäärän yhdeksi.
         *
         * @param value Joukon ID.
         */
        SetElement(int value) {
            id = value;
            root = this;
            elementsJoinedByRoot = 1;
        }

        /**
         * Hakee koko joukon juuren rekursiivisesti ja korjaa matkalla olleiden
         * alkioiden juuren koko joukon juureksi.
         *
         * @return Koko joukon juuri.
         */
        SetElement getRoot() {
            if (this.root == this) {
                return this;
            }
            SetElement rt = this.root.getRoot();
            this.root = rt;
            return rt;
        }

        /**
         * Hakee rekursiivisesti koko joukon juuren ja katsoo sen ID:n.
         *
         * @return Palauttaa koko joukon ID:n.
         */
        int getId() {
            return getRoot().id;
        }

        /**
         * Hakee rekursiivisesti koko joukon juuren, jonka joukon koko on
         * ajantasalla.
         *
         * @return Palauttaa koko joukon koon.
         */
        int getNumOfElements() {
            return getRoot().elementsJoinedByRoot;
        }

        /**
         * Hakee rekursiivisesti tämän joukon juuren, sekä annetun toisen joukon
         * alkion juuren. Päivittää juuren koon ja asettaa toisen joukon
         * juureksi tämän joukon juuren.
         *
         * @param se2 Annettu, toisen joukon alkio.
         */
        void joinTwoSets(SetElement se2) {
            SetElement rt = getRoot();
            SetElement rt2 = se2.getRoot();
            rt.elementsJoinedByRoot += rt2.elementsJoinedByRoot;
            rt2.root = rt;
        }
    }

    /**
     * Alustaa joka solun omaan joukkoonsa. Alustaa joka solun arrayhun, missä
     * array[0] on solun koordinaatti ja array[1] on solun mahdolliset kaaret.
     * <br>
     * Arpoo solun ja solusta kaaren. Jos tämä kaari erottaa kaksi eri joukkoa,
     * yhdistä joukot. Toista tätä. Kun kaikki alkiot kuuluvat samaan joukkoon,
     * labyrintti on generoitu.
     * <br>
     * Labyrintin toiminnasta löytyy tietoa myös määrittelydokumentista.
     *
     * @see main.Labyrinth#addPassage(int, int)
     * @see main.Labyrinth#getTargetCoordinate(int, byte)
     */
    @Override
    public void generateLabyrinth() {
        CreateEmptyLabyrinthIfNeeded();
        int labyrinthSize = labyrinth.height * labyrinth.width;
        int verticesLeft = labyrinthSize;
        int[][] edges = new int[verticesLeft][];
        SetElement[] elements = new SetElement[labyrinthSize];

        for (int i = 0; i < labyrinthSize; i++) {
            elements[i] = new SetElement(i);
            edges[i] = new int[2];
            edges[i][0] = i;
            addEdgesToNeighbors(i, edges[i]);
        }
        while (true) {
            int rand = random.nextInt(verticesLeft);
            int coordinate = edges[rand][0];
            byte edge = randomEdge(coordinate, edges[rand][1]);
            int targetCoordinate = labyrinth.getTargetCoordinate(coordinate, edge);
            SetElement orig = elements[coordinate];
            SetElement target = elements[targetCoordinate];
            if (orig.getId() != target.getId()) {
                orig.joinTwoSets(target);
                labyrinth.addPassage(coordinate, targetCoordinate);
                if (orig.getNumOfElements() == labyrinthSize) {
                    break;
                }
            }
            edges[rand][1] ^= edge; // Käytetyn kaaren poisto
            /*
             * Apumetodi, joka hankkiutuu eroon soluista sitä mukaa kun niitä
             * ei enää voisi käyttää.
             */
            if (!saveVertice(edges[rand], orig, elements)) {
                edges[rand] = edges[verticesLeft - 1];
                verticesLeft--;
            }

        }
    }

    /**
     * Arpoo kaaren annetun solun kaarilistasta.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param edges Tämän solun kaaret kokonaislukuna.
     * @return Palauttaa sen maskin, jolla arvottu kaari saadaan luettua.
     */
    private byte randomEdge(int coordinate, int edges) {
        int rand = random.nextInt(4);
        while ((edges & masks[rand]) == 0) {
            rand = (rand + 1) % 4;
        }
        return masks[rand];
    }

    /**
     * Tämä apumetodi tarkistaa, lähteekö solusta enää kaaria, jotka
     * yhdistäisivät solun muihin joukkoihin.
     *
     * @param edges Tämän solun kaaret kokonaislukuna.
     * @param orig Tämän solun joukkoalkio.
     * @param elements Joukkoalkioiden array.
     * @return Palauttaa totuusarvon joka kertoo kannattaako solua enää pitää.
     *
     * @see main.Labyrinth#getTargetCoordinate(int, byte)
     */
    private boolean saveVertice(int[] edges, SetElement orig, SetElement[] elements) {
        if (edges[1] == 0) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if ((edges[1] & masks[i]) > 0) {
                int targetCoordinate = labyrinth.getTargetCoordinate(edges[0], masks[i]);
                SetElement target = elements[targetCoordinate];
                if (orig.getId() == target.getId()) {
                    edges[1] ^= masks[i];
                }
            }
        }
        return edges[1] != 0;
    }

    /**
     * Tallentaa alussa kaikki mahdolliset kaaret, mitä annetulla solulla voisi
     * olla.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param edges Solun array, johon kaaret tallennetaan.
     */
    private void addEdgesToNeighbors(int coordinate, int[] edges) {

        /*
         NORTH
         */
        if (coordinate / labyrinth.width - 1 >= 0) {
            edges[1] |= 1;
        }

        /*
         EAST
         */
        if (coordinate % labyrinth.width + 1 < labyrinth.width) {
            edges[1] |= 2;
        }

        /*
         SOUTH
         */
        if (coordinate / labyrinth.width + 1 < labyrinth.height) {
            edges[1] |= 4;
        }

        /*
         WEST
         */
        if (coordinate % labyrinth.width - 1 >= 0) {
            edges[1] |= 8;
        }
    }

}
