package main;

import java.text.DecimalFormat;

/**
 * Labyrintti-olio.
 *
 * @author Juri Kuronen
 */
public class Labyrinth {

    /**
     * Labyrintti on tallennettu korkeus*leveys-kokoiseen arrayhun.
     */
    public byte[][] labyrinth;
    /**
     * Labyrintin leveys.
     */
    public int width;
    /**
     * Labyrintin korkeus.
     */
    public int height;

    /**
     *
     * @param w Labyrintin leveys.
     * @param h Labyrintin korkeus.
     */
    public Labyrinth(int w, int h) {
        width = w;
        height = h;
        labyrinth = new byte[height][width];
    }

    /**
     * Päivittää labyrintin koko.
     *
     * @param w Labyrintin leveys.
     * @param h Labyrintin korkeus.
     */
    public void updateLabyrinth(int w, int h) {
        width = w;
        height = h;
        labyrinth = new byte[height][width];
    }

    /**
     * (TEMP) Tulostusrutiini.
     */
    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(((labyrinth[i][j] & 1) > 0) ? "N" : "0");
                System.out.print(((labyrinth[i][j] & 2) > 0) ? "E" : "0");
                System.out.print(((labyrinth[i][j] & 4) > 0) ? "S" : "0");
                System.out.print(((labyrinth[i][j] & 8) > 0) ? "W " : "0 ");
            }
            System.out.println("");
        }
        System.out.println("");
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
        if (mask == 1) {
            if (coordinateOrig / width - 1 < 0) {
                throw new Exception("Target coordinate not in labyrinth.");
            }
            return coordinateOrig - width;
        }
        if (mask == 2) {
            if (coordinateOrig % width + 1 >= width) {
                throw new Exception("Target coordinate not in labyrinth.");
            }
            return coordinateOrig + 1;
        }
        if (mask == 4) {
            if (coordinateOrig / width + 1 >= height) {
                throw new Exception("Target coordinate not in labyrinth.");
            }
            return coordinateOrig + width;
        }
        if (coordinateOrig % width - 1 < 0) {
            throw new Exception("Target coordinate not in labyrinth.");
        }
        return coordinateOrig - 1;
    }

    /**
     * Lisää labyrinttiin reitin kahden koordinaatin välille.
     *
     * @param coordinateOrig Solun koordinaatti.
     * @param coordinateTarget Kohteen koordinaatti.
     */
    public void addPassage(int coordinateOrig, int coordinateTarget) {
        if (coordinateOrig - width == coordinateTarget) {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= 1;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= 4;
        } else if (coordinateOrig + 1 == coordinateTarget) {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= 2;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= 8;
        } else if (coordinateOrig + width == coordinateTarget) {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= 4;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= 1;
        } else {
            labyrinth[coordinateOrig / width][coordinateOrig % width] |= 8;
            labyrinth[coordinateTarget / width][coordinateTarget % width] |= 2;
        }
    }

    /**
     * Annetun koordinaatin vierailemattomat naapurit.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tilasta.
     * @return Palauttaa listan annetun koordinaatin vierailemattomista
     * naapureista.
     *
     * @see main.MyList
     */
    public MyList getListOfUnvisitedNeighbors(int coordinate, int[][] visited) {
        MyList<Integer> listOfNeighbours = new MyList(4);

        /*
         EAST
         */
        if (coordinate % width + 1 < width) {
            if (visited[coordinate / width][coordinate % width + 1] == 0) {
                listOfNeighbours.add(coordinate + 1);
            }
        }

        /*
         SOUTH
         */
        if (coordinate / width + 1 < height) {
            if (visited[coordinate / width + 1][coordinate % width] == 0) {
                listOfNeighbours.add(coordinate + width);
            }
        }

        /*
         NORTH
         */
        if (coordinate / width - 1 >= 0) {
            if (visited[coordinate / width - 1][coordinate % width] == 0) {
                listOfNeighbours.add(coordinate - width);
            }
        }

        /*
         WEST
         */
        if (coordinate % width - 1 >= 0) {
            if (visited[coordinate / width][coordinate % width - 1] == 0) {
                listOfNeighbours.add(coordinate - 1);
            }
        }

        return listOfNeighbours;
    }

    /**
     * Annetun koordinaatin naapurit, joissa on vierailtu. Eli naapurit, jotka
     * ovat osana labyrinttia.
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tilasta.
     * @return Palauttaa listan annetun koordinaatin naapureista, jotka ovat
     * osana labyrinttia.
     *
     * @see main.MyList
     */
    public MyList getListOfVisitedNeighbors(int coordinate, int[][] visited) {
        MyList<Integer> listOfNeighbours = new MyList(4);

        /*
         EAST
         */
        if (coordinate % width + 1 < width) {
            if (visited[coordinate / width][coordinate % width + 1] == 2) {
                listOfNeighbours.add(coordinate + 1);
            }
        }

        /*
         SOUTH
         */
        if (coordinate / width + 1 < height) {
            if (visited[coordinate / width + 1][coordinate % width] == 2) {
                listOfNeighbours.add(coordinate + width);
            }
        }

        /*
         NORTH
         */
        if (coordinate / width - 1 >= 0) {
            if (visited[coordinate / width - 1][coordinate % width] == 2) {
                listOfNeighbours.add(coordinate - width);
            }
        }

        /*
         WEST
         */
        if (coordinate % width - 1 >= 0) {
            if (visited[coordinate / width][coordinate % width - 1] == 2) {
                listOfNeighbours.add(coordinate - 1);
            }
        }

        return listOfNeighbours;
    }

    /**
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tiloista.
     * @return
     *
     * @see main.MyList
     */
    public MyList getListOfEdgesToUnvisitedNeighbors(int coordinate, int[][] visited) {
        MyList<Integer> listOfNeighbours = new MyList(4);

        /*
         EAST
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 2) > 0) {
            if (visited[coordinate / width][coordinate % width + 1] == 0) {
                listOfNeighbours.add(coordinate + 1);
            }
        }

        /*
         SOUTH
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 4) > 0) {
            if (visited[coordinate / width + 1][coordinate % width] == 0) {
                listOfNeighbours.add(coordinate + width);
            }
        }

        /*
         NORTH
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 1) > 0) {
            if (visited[coordinate / width - 1][coordinate % width] == 0) {
                listOfNeighbours.add(coordinate - width);
            }
        }

        /*
         WEST
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 8) > 0) {
            if (visited[coordinate / width][coordinate % width - 1] == 0) {
                listOfNeighbours.add(coordinate - 1);
            }
        }

        return listOfNeighbours;
    }

    /**
     *
     *
     * @param coordinate Koordinaatti, jossa solu on.
     * @param visited Array, jossa on tietoa labyrintin solujen tiloista.
     *
     * @see main.MyList
     */
    public MyList getListOfEdgesToVisitedNeighbors(int coordinate, int[][] visited) {
        MyList<Integer> listOfNeighbours = new MyList(4);

        /*
         EAST
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 2) > 0) {
            if (visited[coordinate / width][coordinate % width + 1] == 2) {
                listOfNeighbours.add(coordinate + 1);
            }
        }

        /*
         SOUTH
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 4) > 0) {
            if (visited[coordinate / width + 1][coordinate % width] == 2) {
                listOfNeighbours.add(coordinate + width);
            }
        }

        /*
         NORTH
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 1) > 0) {
            if (visited[coordinate / width - 1][coordinate % width] == 2) {
                listOfNeighbours.add(coordinate - width);
            }
        }

        /*
         WEST
         */
        if ((labyrinth[coordinate / width][coordinate % width] & 8) > 0) {
            if (visited[coordinate / width][coordinate % width - 1] == 2) {
                listOfNeighbours.add(coordinate - 1);
            }
        }

        return listOfNeighbours;
    }

    public String formatTime(long time) {
        return formatNumber(time / 1000) + "," + formatNumber(time % 1000) + " ms";
    }

    /**
     * Formatoi luvun muotoon "### " + "### " + ... + "### ".
     *
     * @param number Luku joka halutaan formatoida.
     * @return Palauttaa formatoidun luvun.
     */
    public String formatNumber(long number) {
        DecimalFormat df = new DecimalFormat("000");
        if (number / 1000 == 0) {
            return number + "";
        }
        String numberFormat = df.format(number % 1000);
        number /= 1000;
        while (number / 1000 != 0) {
            numberFormat = df.format(number % 1000) + " " + numberFormat;
            number /= 1000;
        }
        return number + " " + numberFormat;
    }

}
