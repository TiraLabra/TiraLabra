package util;

/**
 * Polunetsijän käyttämä TreeNode-apuluokka.
 */
public class TreeNode {

    /**
     * Tämän solmun vanhempi.
     */
    private final TreeNode parent;
    /**
     * Tämän solmun koordinaatti.
     */
    private final int coordinate;

    /**
     * Alustaa vanhemmalla ja solmun koordinaatilla.
     *
     * @param p Vanhempi solmu.
     * @param c Koordinaatti.
     */
    public TreeNode(TreeNode p, int c) {
        parent = p;
        coordinate = c;
    }

    /**
     * Palauttaa tämän solmun vanhemman.
     *
     * @return Palauttaa tämän solmun vanhemman.
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Palauttaa tämän solmun koordinaatin.
     *
     * @return Palauttaa tämän solmun koordinaatin.
     */
    public int getCoordinate() {
        return coordinate;
    }

}
