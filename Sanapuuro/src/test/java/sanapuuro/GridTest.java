/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import sanapuuro.letters.LetterContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class GridTest {

    private Grid grid;

    public GridTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.grid = new Grid(8, 8);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void gridHasNoLetterContainersAfterClearing() {
        this.grid.setContainerAt(new LetterContainer(new Letter('a', 0, 0)), 0, 0);
        this.grid.setContainerAt(new LetterContainer(new Letter('b', 0, 0)), 7, 7);
        boolean hasContainers = false;
        this.grid.clear();
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.height; y++) {
                if (this.grid.getContainerAt(x, y) != null) {
                    hasContainers = true;
                    break;
                }
            }
            if (hasContainers) {
                break;
            }
        }
        assertFalse("grid shouldn't have any letters", hasContainers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridNegativeX() {
        this.grid.getContainerAt(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridNegativeY() {
        this.grid.getContainerAt(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridXTooBig() {
        this.grid.getContainerAt(this.grid.width, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridYTooBig() {
        this.grid.getContainerAt(0, this.grid.height);
    }
}
