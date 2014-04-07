/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import sanapuuro.letters.LetterContainer;
import java.util.List;

/**
 * Grid that is used to get and set letter containers to it.
 *
 * @author skaipio
 */
public class Grid {

    public final int width, height;     // Grid height and width by cells, preferably square.
    private final LetterContainer[][] containers;   // The actual grid as 2d-array.

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.containers = new LetterContainer[width][height];
    }

    /**
     * Clears the grid removing all letter containers from it.
     */
    public void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.containers[x][y] = null;
            }
        }
    }

    /**
     * Tells whether the grid has a letter container at given coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     * @return True if grid has letter container at x and y, false otherwise.
     */
    public boolean hasContainerAt(int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates (" + x + "," + y + ") are not within grid.");
        }
        return this.containers[x][y] != null;
    }
    
    public boolean isFull(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (this.containers[x][y] == null){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the letter container at given coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     * @return Returns letter container at x and y, if none exists, returns
     * null.
     */
    public LetterContainer getContainerAt(int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        return this.containers[x][y];
    }

    /**
     * Sets the letter container at given coordinates if there is none present
     * yet.
     *
     * @param container Letter container to set to x and y.
     * @param x coordinate
     * @param y coordinate
     * @return True if letter container was set to grid successfully, false
     * otherwise.
     */
    public boolean setContainerAt(LetterContainer container, int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates (" + x + "," + y + ") are not within grid.");
        }
        if (!this.hasContainerAt(x, y)) {
            this.containers[x][y] = container;
            container.setX(x);
            container.setY(y);
            return true;
        }
        return false;
    }

    /**
     * Removes the letter container - if there is any - at given coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void removeContainerAt(int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        this.containers[x][y] = null;
    }
    
    /**
     * Removes a container from the grid based on its coordinates.
     * @param container Container to remove from the grid.
     */
    public void removeContainer(LetterContainer container){
        this.removeContainerAt(container.getX(), container.getY());
    }

    /**
     * Removes given containers from the grid based on their coordinates.
     * @param containers Containers to remove.
     */
    public void removeContainersFromGrid(List<LetterContainer> containers) {
        for (LetterContainer container : containers) {
            this.removeContainer(container);
        }
    }

    /**
     * Sets the currently selected letters to the grid permanently and also
     * removes them from the player's letter pool.
     */
    public void setLettersToGridPermanently(List<LetterContainer> containers) {
        for (LetterContainer container : containers) {
            container.setToGridPermanently();
        }
    }

    /**
     * Checks whether given coordinates are within grid bounds.
     *
     * @param x coordinate
     * @param y coordinate
     * @return True if x and y are within bounds, false otherwise.
     */
    public boolean isWithinGrid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

//     public enum Size {
//
//        SMALL(8, 8), MEDIUM(10, 10), LARGE(12, 12);
//
//        public final int Width, Height;
//
//        private Size(int width, int height) {
//            this.Width = width;
//            this.Height = height;
//        }
//    }
}
