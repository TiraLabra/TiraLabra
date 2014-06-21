package labyrinthgenerator;

import util.SetElement;

/**
 * Modifioitu satunnaistettu Kruskalin algoritmi labyrintin generoimiseen.
 * 
* @author Juri Kuronen
 */
public class KruskalsAlgorithm extends LabyrinthGenerator {

    /**
     * Maskit kaaren suunnille. 1 = NORTH, 2 = EAST, 4 = SOUTH, 8 = WEST.
     */
    final byte[] masks = {1, 2, 4, 8};

    /**
     * Yliluokka alustaa random-olion.
     *
     * @see labyrinthgenerator.LabyrinthGenerator#LabyrinthGenerator()
     */
    public KruskalsAlgorithm() {
        super();
        name = "Kruskal's algorithm";
    }

    /**
     * <u>Alustus:</u><br>
     * Luo joukko-arrayn ja arrayn kaarille. Joka solulle oma joukkoonsa.
     * Kaari-arrayssä array[0] on solun koordinaatti ja array[1] on solun
     * mahdolliset kaaret - alussa kaikki mahdolliset.
     * <br><br>
     * <u>Toiminta:</u><br>
     * Arpoo solun ja solusta kaaren. Jos tämän kaaren erottamat solut kuuluvat
     * eri joukkoihin, yhdistä joukot. Toista tätä, kunnes kaikki alkiot
     * kuuluvat samaan joukkoon, jolloin labyrintti on generoitu.
     * <br><br>
     * Algoritmin toiminnasta löytyy tietoa myös määrittelydokumentista.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     * @see initialize(int[][], SetElement[])
     * @see main.Labyrinth#addPassage(int, int)
     * @see main.Labyrinth#getTargetCoordinate(int, byte)
     */
    @Override
    public void generateLabyrinth() throws Exception {
        int labyrinthSize = labyrinth.getHeight() * labyrinth.getWidth();
        int verticesLeft = labyrinthSize;
        int[][] edges = new int[verticesLeft][];
        SetElement[] elements = new SetElement[labyrinthSize];
        initialize(edges, elements);
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
     * Initialisoi kaari- ja joukko-arrayt.
     *
     * @param edges Kaari-array.
     * @param elements Joukko-array.
     */
    void initialize(int[][] edges, SetElement[] elements) {
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        int labyrinthSize = height * width;
        for (int i = 0; i < labyrinthSize; i++) {
            elements[i] = new SetElement(i);
            edges[i] = new int[2];
            edges[i][0] = i;
            int x = i % width;
            int y = i / width;
            if (y - 1 >= 0) {
                edges[i][1] |= 1;
            }
            if (x + 1 < width) {
                edges[i][1] |= 2;
            }
            if (y + 1 < height) {
                edges[i][1] |= 4;
            }
            if (x - 1 >= 0) {
                edges[i][1] |= 8;
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
    byte randomEdge(int coordinate, int edges) {
        int rand = random.nextInt(4);
        while ((edges & masks[rand]) == 0) {
            rand = (rand + 1) % 4;
        }
        return masks[rand];
    }

    /**
     * Tämä apumetodi tarkistaa, lähteekö solusta enää kaaria, jotka
     * yhdistäisivät solun muihin joukkoihin. Hoitaa siivoamistoimenpiteitä.
     *
     * @param edges Tämän solun kaaret kokonaislukuna.
     * @param orig Tämän solun joukkoalkio.
     * @param elements Joukkoalkioiden array.
     * @return Palauttaa totuusarvon joka kertoo kannattaako solua enää pitää.
     *
     * @see main.Labyrinth#getTargetCoordinate(int, byte)
     */
    boolean saveVertice(int[] edges, SetElement orig, SetElement[] elements) throws Exception {
        if (edges[1] == 0) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if ((edges[1] & masks[i]) > 0) {
                int targetCoordinate = labyrinth.getTargetCoordinate(edges[0], masks[i]);
                SetElement target = elements[targetCoordinate];
                if (orig.getId() == target.getId()) {
                    /*
                     Poistaa kaaren, joka yhdistäisi samoja joukkoja.
                     */
                    edges[1] ^= masks[i];
                }
            }
        }
        return edges[1] != 0;
    }

}
