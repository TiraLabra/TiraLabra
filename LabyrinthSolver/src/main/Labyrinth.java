package main;

import labyrinthgenerator.LabyrinthGenerator;
import labyrinthsolver.LabyrinthSolver;

/**
 * Labyrintti-olio.
 *
 * @author Juri Kuronen
 */
public class Labyrinth {

    /**
     * Labyrintti on tallennettu korkeus*leveys-kokoiseen arrayhun.
     */
    private byte[][] labyrinth;
    /**
     * Labyrintin leveys.
     */
    private int width;
    /**
     * Labyrintin korkeus.
     */
    private int height;
    /**
     * Labyrintin generointialgoritmi.
     */
    public LabyrinthGenerator lg;
    /**
     * Labyrintin ratkaisualgoritmi.
     */
    public LabyrinthSolver ls;

    /**
     * Luo uuden labyrintin annetulla leveydellä ja korkeudella sekä alustaa
     * 2-ulotteisen arrayn labyrintin solujen kaaria varten.
     *
     * @param w Labyrintin leveys.
     * @param h Labyrintin korkeus.
     */
    public Labyrinth(int w, int h) {
        width = w;
        height = h;
        labyrinth = new byte[h][w];
    }

    /**
     * Palauttaa labyrintin leveyden.
     *
     * @return Palauttaa labyrintin leveyden.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Palauttaa labyrintin korkeuden.
     *
     * @return Palauttaa labyrintin korkeuden.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Tarkastaa, onko labyrintti generoitu.
     *
     * @return Palauttaa true, jos labyrintti on generoitu.
     */
    public boolean isGenerated() {
        return labyrinth[0][0] != 0;
    }

    /**
     * Palauttaa annetusta koordinaatista lähtevät kaaret.
     *
     * @param coordinate Annettu koordinaatti.
     * @return Palauttaa annetusta koordinaatista lähtevät kaaret.
     */
    public byte getEdges(int coordinate) {
        if (validCoordinate(coordinate % width, coordinate / width)) {
            return labyrinth[coordinate / width][coordinate % width];
        } else {
            return 0;
        }
    }

    /**
     * Tarkastaa, lähteekö annetusta koordinaatista kaari annettuun suuntaan.
     *
     * @param coordinate Annettu koordinaatti.
     * @param direction Suunta.
     * @return Palauttaa true, jos annetusta koordinaatista lähtee kaari
     * annettuun suuntaan.
     */
    public boolean hasEdge(int coordinate, byte direction) {
        if (validCoordinate(coordinate % width, coordinate / width)) {
            return (labyrinth[coordinate / width][coordinate % width] & direction) != 0;
        } else {
            return false;
        }
    }

    /**
     * Päivittää labyrintin koon. Tämän jälkeen asettaa uuden labyrintin
     * labyrintingeneroijaan ja -ratkaisijaan.
     *
     * @param w Labyrintin leveys.
     * @param h Labyrintin korkeus.
     */
    public void updateLabyrinth(int w, int h) {
        if (w < 2 || h < 2 || w > 5000 || h > 5000) {
            return;
        }
        width = w;
        height = h;
        labyrinth = new byte[h][w];
        if (lg != null) {
            lg.labyrinth = this;
        }
        if (ls != null) {
            ls.labyrinth = this;
            ls.reset();
        }
    }

    /**
     * Asettaa tälle labyrintille uuden generoijan.
     *
     * @param newLG Labyrintin generoija, mikä asetetaan.
     */
    public void setLabyrinthGenerator(LabyrinthGenerator newLG) {
        lg = newLG;
        lg.labyrinth = this;
    }

    /**
     * Asettaa tälle labyrintille uuden ratkojan.
     *
     * @param newLS Labyrintin ratkoja, mikä asetetaan.
     */
    public void setLabyrinthSolver(LabyrinthSolver newLS) {
        ls = newLS;
        ls.labyrinth = this;
    }

    /**
     * Jos labyrintingeneroija on asetettu, generoi labyrintin. Uuden labyrintin
     * generoitua resetoi labyrintin ratkaisijan.
     *
     * @throws java.lang.Exception Heittää poikkeuksen, jos algoritmi yrittää
     * käsitellä labyrintin ulkopuolista koordinaattia.
     */
    public void generateLabyrinth() throws Exception {
        if (lg != null) {
            lg.createEmptyLabyrinthIfNeeded();
            lg.generateLabyrinth();
            if (ls != null) {
                ls.reset();
            }
        }
    }

    /**
     * Jos labyrintinratkaisija on asetettu, ja labyrintti generoitu, ratkaisee
     * labyrintin.
     */
    public void solveLabyrinth() {
        if (ls != null) {
            if (labyrinth[0][0] > 0) {
                ls.solveLabyrinth();
            }
        }
    }

    /**
     * Palauttaa koordinaatin kohteeseen.
     *
     * @param coordinateOrig Solun koordinaatti.
     * @param mask Suunnan maski.
     * @return Palauttaa kohteen koordinaatin.
     * @throws java.lang.Exception Palauttaa poikkeuksen, jos kohteen
     * koordinaatti ei ole labyrintin sisällä.
     */
    public int getTargetCoordinate(int coordinateOrig, byte mask) throws Exception {
        int x = coordinateOrig % width;
        int y = coordinateOrig / width;
        if (mask == 1 && validCoordinate(x, y - 1)) {
            return coordinateOrig - width;
        }
        if (mask == 2 && validCoordinate(x + 1, y)) {
            return coordinateOrig + 1;
        }
        if (mask == 4 && validCoordinate(x, y + 1)) {
            return coordinateOrig + width;
        }
        if (validCoordinate(x - 1, y)) {
            return coordinateOrig - 1;
        }
        throw new Exception("Target coordinate not in labyrinth.");
    }

    /**
     * Lisää labyrinttiin reitin kahden koordinaatin välille.
     *
     * @param coordinateOrig Solun koordinaatti.
     * @param coordinateTarget Kohteen koordinaatti.
     * @throws java.lang.Exception Palauttaa poikkeuksen, jos jokin annetuista
     * koordinaateista ei ollut labyrintin sisällä.
     */
    public void addPassage(int coordinateOrig, int coordinateTarget) throws Exception {
        int x = coordinateOrig % width;
        int y = coordinateOrig / width;
        int dx = coordinateTarget % width;
        int dy = coordinateTarget / width;
        if (!(validCoordinate(x, y) && validCoordinate(dx, dy))) {
            throw new Exception("Invalid coordinates given.");
        }
        if (y - 1 == dy) {
            labyrinth[y][x] |= 1;
            labyrinth[dy][dx] |= 4;
        } else if (x + 1 == dx) {
            labyrinth[y][x] |= 2;
            labyrinth[dy][dx] |= 8;
        } else if (y + 1 == dy) {
            labyrinth[y][x] |= 4;
            labyrinth[dy][dx] |= 1;
        } else if (x - 1 == dx) {
            labyrinth[y][x] |= 8;
            labyrinth[dy][dx] |= 2;
        }
    }

    /**
     * Tarkastaa, ovatko annetut x ja y labyrintin sisällä.
     *
     * @param x X-koordinaatti.
     * @param y Y-koordinaatti.
     * @return Palauttaa true, jos koordinaatit ovat labyrintin sisällä.
     */
    public boolean validCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Tarkastaa, ovatko annetut x ja y labyrintin sisällä. Tämän jälkeen
     * tarkastaa, onko x- ja y-koordinaatissa oleva solu halutussa tilassa.
     *
     * @param x X-koordinaatti.
     * @param y Y-koordinaatti.
     * @param visited Array, jossa on tietoa labyrintin solujen tilasta.
     * @param state Naapurien haluttu tila.
     * @return Palauttaa true, jos koordinaatit ovat labyrintin sisällä.
     */
    public boolean validCoordinate(int x, int y, int[][] visited, int state) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return visited[y][x] == state;
    }

    /**
     * Annetun koordinaatin naapurit, jotka ovat tilassa state. State = 2
     * tarkoittaa vierailtua ja state = 0 vierailematonta naapuria.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tilasta.
     * @param state Naapurien haluttu tila.
     * @return Palauttaa listan annetun koordinaatin naapureista, jotka ovat
     * osana labyrinttia ja tilassa state.
     *
     * @see main.MyList
     * @see validCoordinate
     */
    public MyList getListOfNeighbors(int coordinate, int[][] visited, int state) {
        MyList<Integer> listOfNeighbours = new MyList(4);
        int x = coordinate % width;
        int y = coordinate / width;
        /*
         EAST
         */
        if (validCoordinate(x + 1, y, visited, state)) {
            listOfNeighbours.add(coordinate + 1);
        }
        /*
         SOUTH
         */
        if (validCoordinate(x, y + 1, visited, state)) {
            listOfNeighbours.add(coordinate + width);
        }
        /*
         NORTH
         */
        if (validCoordinate(x, y - 1, visited, state)) {
            listOfNeighbours.add(coordinate - width);
        }
        /*
         WEST
         */
        if (validCoordinate(x - 1, y, visited, state)) {
            listOfNeighbours.add(coordinate - 1);
        }
        return listOfNeighbours;
    }

    /**
     * Annetun koordinaatin kaarella yhdistetyt naapurit, jotka ovat tilassa
     * state. State = 2 tarkoittaa vierailtua ja state = 0 vierailematonta
     * naapuria.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tiloista.
     * @param state Naapurien haluttu tila.
     * @return Palauttaa listan annetun koordinaatin vierailemattomista
     * naapureista, jos niihin kulkee kaari lähtökoordinaatista.
     *
     * @see main.MyList
     */
    public MyList getListOfConnectedNeighbors(int coordinate, int[][] visited, int state) {
        MyList<Integer> listOfNeighbours = new MyList(4);
        int x = coordinate % width;
        int y = coordinate / width;
        /*
         EAST
         */
        if ((labyrinth[y][x] & 2) > 0 && visited[y][x + 1] == state) {
            listOfNeighbours.add(coordinate + 1);
        }
        /*
         SOUTH
         */
        if ((labyrinth[y][x] & 4) > 0 && visited[y + 1][x] == state) {
            listOfNeighbours.add(coordinate + width);
        }
        /*
         NORTH
         */
        if ((labyrinth[y][x] & 1) > 0 && visited[y - 1][x] == state) {
            listOfNeighbours.add(coordinate - width);
        }
        /*
         WEST
         */
        if ((labyrinth[y][x] & 8) > 0 && visited[y][x - 1] == state) {
            listOfNeighbours.add(coordinate - 1);
        }
        return listOfNeighbours;
    }

}
