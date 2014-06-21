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
    private final byte[] masks = {1, 2, 4, 8};

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
        int[][] edges = new int[labyrinthSize][];
        SetElement[] elements = new SetElement[labyrinthSize];
        initialize(edges, elements);
        while (true) {
            int rand = random.nextInt(verticesLeft);
            int coordinate = edges[rand][0];
            byte edge = randomEdge(coordinate, edges[rand][1]);
            /*
             Yhdistää kaaren erottamat solut samaksi joukoksi, jos
             mahdollista. Jos solut yhdistettiin, tarkastaa kuuluvatko kaikki
             solut jo samaan joukkoon, mikä tarkoittaisi, että labyrintti on
             generoitu.
             */
            if (unionPerformed(elements, coordinate, edge)
                    && elements[0].getNumOfElements() == labyrinthSize) {
                return;
            }
            /*
             Tämän jälkeen siivoaa arvotun solun kaarilistaa. Jos solun
             kaarilista tyhjeni, poistetaan solu solulistasta.
             */
            if (removeUselessEdges(edges[rand], elements[coordinate], elements)) {
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
     * Yhdistää kaaren erottamat solut samaksi joukoksi, jos mahdollista.
     * Tällöin lisää myös labyrinttiin tämän kaaren.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param edge Arvottu kaari.
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     */
    boolean unionPerformed(SetElement[] elements, int coordinate, byte edge) throws Exception {
        int targetCoordinate = labyrinth.getTargetCoordinate(coordinate, edge);
        SetElement orig = elements[coordinate];
        SetElement target = elements[targetCoordinate];
        if (elements[coordinate].getId() != elements[targetCoordinate].getId()) {
            orig.joinTwoSets(target);
            labyrinth.addPassage(coordinate, targetCoordinate);
            return true;
        }
        return false;
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
     * Tämä apumetodi siistii solun kaarilistasta ne kaaret pois, jotka
     * yhdistäisivät solun muihin joukkoihin.
     *
     * @param edges Tämän solun kaaret kokonaislukuna.
     * @param orig Tämän solun joukkoalkio.
     * @param elements Joukkoalkioiden array.
     * @return Palauttaa totuusarvon joka kertoo onko solulla enää kaaria.
     *
     * @see main.Labyrinth#getTargetCoordinate(int, byte)
     */
    boolean removeUselessEdges(int[] edges, SetElement orig, SetElement[] elements) throws Exception {
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
        return edges[1] == 0;
    }

}
