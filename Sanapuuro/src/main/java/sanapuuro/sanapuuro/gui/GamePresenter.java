/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.SwingUtilities;
import sanapuuro.sanapuuro.Game;
import sanapuuro.sanapuuro.GameTimerListener;
import sanapuuro.sanapuuro.Player;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.letters.LetterPool;

/**
 * Updates game logic instances and GUI instances according to input. Resembles
 * the presenter in MVP pattern.
 *
 * @author skaipio
 */
public class GamePresenter implements MouseListener, GameTimerListener {

    private final Game game = new Game();
    private Player player;
    private Grid grid;
    private final GameView gameView;
    private final TimerWrapper timer = new TimerWrapper();
    private final Selector selector = new Selector();
    private LetterPool letterPool;

    public GamePresenter(GameView gameView) {
        this.gameView = gameView;    
    }

    /**
     * Starts a new game and prepares the views.
     */
    public void newGame() {
        this.game.newGame(timer);
        this.grid = this.game.getGrid();
        this.player = this.game.getPlayer();
        this.letterPool = this.player.getLetterPool();
        this.initComponents();
        this.gameView.addMouseListener(this);
        this.gameView.addKeyListener(selector);
        this.updateLetterPoolPanel();
        this.selector.setLocation(grid.width / 2, grid.height / 2);
    }
    
    private void initComponents(){
        this.gameView.letterPool.init(this.letterPool.poolSize);
        this.gameView.letterPool.addListenerToCells(this.selector);
        
//        this.mainGameView.letterGrid.addMouseListener(this);
        this.gameView.letterGrid.addListenerToCells(this);
        
        this.timer.addActionListener(gameView.time);
        this.timer.addListener(this);
    }

    /**
     * Handles mouse click events from views.
     *
     * @param e Event from one of the controller's views.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getComponent() instanceof LetterPoolCellButton) {
            LetterPoolCellButton cell = (LetterPoolCellButton) e.getComponent();
            System.out.println(cell.requestFocusInWindow());
            this.leftClickLetterPoolCell(cell);
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getSource() instanceof GridCellPanel) {
            GridCellPanel cell = (GridCellPanel) e.getSource();
            this.leftClickGridCell(cell);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            this.selector.removeSelection();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof GridCellPanel) {
            GridCellPanel cell = (GridCellPanel) e.getSource();
            if (!this.selector.selectionMode) {
                cell.hoverOn();
            } else if (this.cellIsTail(cell)
                    || (!cell.isSelected() && this.cellIsAlignedWithAndNeighbourOfLastSelection(cell))) {
                cell.hoverOn();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof GridCellPanel) {
            GridCellPanel cell = (GridCellPanel) e.getSource();
            if (!this.selector.selectionMode) {
                cell.hoverOff();
            } else if (this.cellIsTail(cell)
                    || (!cell.isSelected() && this.cellIsAlignedWithAndNeighbourOfLastSelection(cell))) {
                cell.hoverOff();
            }

        }
    }

    private void deselectCells(List<LetterContainer> selectedContainers) {
        for (LetterContainer container : selectedContainers) {
            this.gameView.letterGrid.setCellSelectionAt(false, container.getX(), container.getY());
        }
    }

    private void leftClickLetterPoolCell(LetterPoolCellButton cell) {
        this.letterPool.setCurrentSelection(cell.index);
        this.gameView.letterPool.setHoverTo(cell.index);
    }

    private void leftClickGridCell(GridCellPanel cell) {
        if (!this.player.getSelectedContainers().isEmpty() && this.cellIsTail(cell)) {
            this.attemptToSubmitSelections();
        } else if (this.player.getSelectedContainers().isEmpty() || cellIsAlignedWithAndNeighbourOfLastSelection(cell)) {
            this.attemptToSelectOrAddCell(cell);
        }
    }

    private void attemptToSelectOrAddCell(GridCellPanel cell) {
        this.selector.selectionMode = true;
        if (this.player.addLetterTo(cell.x, cell.y)) {
            LetterContainer container = this.letterPool.getCurrentSelection();
            this.gameView.letterPool.setContainerAsUsed(container.letterPoolIndex(), true);
            this.gameView.letterGrid.getCellAt(cell.x, cell.y).setLetter(container.letter.toString());
        }
        LetterContainer previousSelection = this.player.getLastSelection();
        if (this.player.selectLetterAt(cell.x, cell.y)) {
            
            this.updateSelectedLettersLabel();
            if (previousSelection != null) {
                this.removeSelectableLightUpsAroundContainer(previousSelection);
            }
            this.selectCell(cell);
            this.lightUpSelectableCells();

            this.selector.setLocation(cell.x, cell.y);
        }
    }

    private void attemptToSubmitSelections() {
        LetterContainer tail = this.player.getLastSelection();
        List<LetterContainer> selections = this.player.getSelectedContainers();
        List<LetterContainer> added = this.player.getAddedContainers();
        if (this.player.submitSelectedLetters()) {
            this.gameView.score.setText(this.player.getScore() + "");
        } else {
            this.removeLettersFromCells(added);
        }
        this.gameView.state.setText(this.player.getStatus());
        this.clearSelections(selections);
        this.removeSelectableLightUpsAroundContainer(tail);
        this.selector.selectionMode = false;
    }

    private boolean cellIsTail(GridCellPanel cell) {
        if (this.player.getSelectedContainers().isEmpty()) {
            return false;
        }
        LetterContainer tail = this.player.getLastSelection();
        return cell.x == tail.getX() && cell.y == tail.getY();
    }

    private boolean cellIsAlignedWithAndNeighbourOfLastSelection(GridCellPanel cell) {
        LetterContainer root = this.player.getFirstSelection();
        LetterContainer tail = this.player.getLastSelection();
        if (cellOnSameRowWithRootAndTail(cell, root, tail)) {
            return this.cellHasNoHorizontalGapToTail(cell, tail);
        } else if (cellOnSameColumnWithRootAndTail(cell, root, tail)) {
            return this.cellHasNoVerticalGapToTail(cell, tail);
        }
        return false;
    }

    private boolean cellOnSameRowWithRootAndTail(GridCellPanel cell, LetterContainer root, LetterContainer tail) {
        return cell.y == tail.getY() && cell.y == root.getY();
    }

    private boolean cellOnSameColumnWithRootAndTail(GridCellPanel cell, LetterContainer root, LetterContainer tail) {
        return cell.x == tail.getX() && cell.x == root.getX();
    }

    private boolean cellHasNoHorizontalGapToTail(GridCellPanel cell, LetterContainer tail) {
        return Math.abs(cell.x - tail.getX()) == 1;
    }

    private boolean cellHasNoVerticalGapToTail(GridCellPanel cell, LetterContainer tail) {
        return Math.abs(cell.y - tail.getY()) == 1;
    }

    private void selectCell(GridCellPanel cell) {
        cell.select();
        cell.hoverOn();
        this.updateSelectedLettersLabel();
    }

    private void lightUpSelectableCells() {
        LetterContainer tail = this.player.getLastSelection();
        if (this.grid.isWithinGrid(tail.getX(), tail.getY() + 1)) {
            GridCellPanel cell = this.gameView.letterGrid.getCellAt(tail.getX(), tail.getY() + 1);
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
        if (this.grid.isWithinGrid(tail.getX(), tail.getY() - 1)) {
            GridCellPanel cell = this.gameView.letterGrid.getCellAt(tail.getX(), tail.getY() - 1);
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
        if (this.grid.isWithinGrid(tail.getX() + 1, tail.getY())) {
            GridCellPanel cell = this.gameView.letterGrid.getCellAt(tail.getX() + 1, tail.getY());
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
        if (this.grid.isWithinGrid(tail.getX() - 1, tail.getY())) {
            GridCellPanel cell = this.gameView.letterGrid.getCellAt(tail.getX() - 1, tail.getY());
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
    }

    private void removeSelectableLightUpsAroundContainer(LetterContainer container) {
        if (this.grid.isWithinGrid(container.getX(), container.getY() + 1)) {
            this.gameView.letterGrid.getCellAt(container.getX(), container.getY() + 1).showAsSelectable(false);
        }
        if (this.grid.isWithinGrid(container.getX(), container.getY() - 1)) {
            this.gameView.letterGrid.getCellAt(container.getX(), container.getY() - 1).showAsSelectable(false);
        }
        if (this.grid.isWithinGrid(container.getX() + 1, container.getY())) {
            this.gameView.letterGrid.getCellAt(container.getX() + 1, container.getY()).showAsSelectable(false);
        }
        if (this.grid.isWithinGrid(container.getX() - 1, container.getY())) {
            this.gameView.letterGrid.getCellAt(container.getX() - 1, container.getY()).showAsSelectable(false);
        }
    }

    private void updateSelectedLettersLabel() {
        List<LetterContainer> selectedLetters = this.player.getSelectedContainers();
        StringBuilder letters = new StringBuilder(selectedLetters.size());
        for (LetterContainer lc : selectedLetters) {
            letters.append(lc.letter.character);
        }
        this.gameView.selectedLetters.setText(letters.toString());
    }

    private void updateLetterPoolPanel() {
        for (LetterContainer container : this.letterPool.getLetters()) {
            int index = container.letterPoolIndex();
            this.gameView.letterPool.setContainerAsUsed(index, letterPool.isIndexUsed(index));
            this.gameView.letterPool.setLetterToCell(container.letter.toString(), container.letterPoolIndex());
        }
        this.gameView.letterPool.setHoverTo(this.letterPool.getCurrentSelectedIndex());
    }

    private void clearSelections(List<LetterContainer> selections) {
        this.deselectCells(selections);
        this.updateLetterPoolPanel();
        this.gameView.selectedLetters.setText("");
    }

    private void removeLettersFromCells(List<LetterContainer> addedContainers) {
        for (LetterContainer container : addedContainers) {
            this.gameView.letterGrid.getCellAt(container.getX(), container.getY()).removeLetter();
        }
    }

    @Override
    public void notifyTimeOut() {
        this.gameView.gameOverMessage.setText("You finished with a score of " + this.player.getScore());
        this.gameView.showGameOverView();
    }

    
    /**
     * A helper class for GamePresenter handling most of the user interaction, such as
     * moving the selection cursor in grid and selecting/adding/removing letters.
     */
    private class Selector implements KeyListener, ActionListener {

        private boolean selectionMode = false;
        private int x, y;

        // TODO: At least some of the things handled by this class ought to be
        // in the Game Logic classes, mainly Player.
        public Selector() {

        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setLocation(int x, int y) {
            GridCellPanel cell = this.getCellAtCurrentLocation();
            cell.enableCursor(false);
            this.x = x < 0 ? grid.width + x : x % grid.width;
            this.y = y < 0 ? grid.height + y : y % grid.height;
            cell = this.getCellAtCurrentLocation();
            cell.enableCursor(true);
            if (cellIsTail(cell)) {
                lightUpSelectableCells();
            }
        }

        public void moveUp() {
            if (!selectionMode || canMoveTo(this.x, y - 1)) {
                this.setLocation(x, this.y - 1);
            }
        }

        public void moveDown() {
            if (!selectionMode || canMoveTo(this.x, y + 1)) {
                this.setLocation(x, this.y + 1);
            }
        }

        public void moveLeft() {
            if (!selectionMode || canMoveTo(this.x - 1, y)) {
                this.setLocation(this.x - 1, y);
            }
        }

        public void moveRight() {
            if (!selectionMode || canMoveTo(this.x + 1, y)) {
                this.setLocation(this.x + 1, y);
            }
        }

        public boolean canMoveTo(int x, int y) {
            if (x >= 0 && x < grid.width && y >= 0 && y < grid.height) {
                GridCellPanel cell = gameView.letterGrid.getCellAt(x, y);
                return cell.isSelectable() || cellIsTail(cell);
            }
            return false;
        }

        public void moveToNext() {
            LetterContainer tail = player.getLastSelection();
            int[] next = this.getNextPlaceFrom(tail.getX(), tail.getY());
            this.setLocation(next[0], next[1]);
        }

        public void removeSelection() {
            if (!this.selectionMode) {
                return;
            }
            List<LetterContainer> containers = player.getSelectedContainers();
            if (!containers.isEmpty()) {
                LetterContainer tail = player.getLastSelection();
                if (player.removeLastSelection()) {
                    GridCellPanel cell = gameView.letterGrid.getCellAt(tail.getX(), tail.getY());
                    cell.deselect();
                    removeSelectableLightUpsAroundContainer(tail);
                    if (!tail.isPermanent()) {
                        cell.removeLetter();
                    }
                    updateSelectedLettersLabel();
                    updateLetterPoolPanel();
                    if (player.getSelectedContainers().isEmpty()) {
                        this.selectionMode = false;
                    } else {
                        lightUpSelectableCells();
                        this.moveToNext();
                    }
                }
            }
        }

        private int[] getNextPlaceFrom(int x, int y) {
            LetterContainer root = player.getFirstSelection();
            int deltaX = x - root.getX();
            int deltaY = y - root.getY();
            if (deltaX != 0) {
                deltaX = deltaX > 0 ? 1 : -1;
            }
            if (deltaY != 0) {
                deltaY = deltaY > 0 ? 1 : -1;
            }
            if (grid.isWithinGrid(x + deltaX, y + deltaY)) {
                return new int[]{x + deltaX, y + deltaY};
            }
            return new int[]{x, y};
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (player.isEnabled()) {
                if (e.getKeyChar() == 'a') {
                    this.moveLeft();
                } else if (e.getKeyChar() == 'd') {
                    this.moveRight();
                } else if (e.getKeyChar() == 'w') {
                    this.moveUp();
                } else if (e.getKeyChar() == 's') {
                    this.moveDown();
                } else if (e.getKeyChar() == ' ') {
                    attemptToSubmitSelections();
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (player.isEnabled()) {
                LetterPoolCellButton btn = (LetterPoolCellButton) e.getSource();
                GridCellPanel cell = this.getCellAtCurrentLocation();
                addLetterUsing(btn, cell);
                selectLetterAt(cell);
                this.moveToNext();
            }
        }

        private GridCellPanel getCellAtCurrentLocation() {
            return gameView.letterGrid.getCellAt(x, y);
        }

        private void addLetterUsing(LetterPoolCellButton btn, GridCellPanel cell) {
            letterPool.setCurrentSelection(btn.index);
            if (player.addLetterTo(cell.x, cell.y)) {
                btn.setInUse(true);
            }
        }

        private void selectLetterAt(GridCellPanel cell) {
            LetterContainer previousSelection = player.getLastSelection();
            if (player.selectLetterAt(cell.x, cell.y)) {
                selectionMode = true;
                LetterContainer container = grid.getContainerAt(cell.x, cell.y);
                String letter = container.letter.toString();
                cell.setLetter(letter);
                updateSelectedLettersLabel();
                if (previousSelection != null) {
                    removeSelectableLightUpsAroundContainer(previousSelection);
                }
                lightUpSelectableCells();
                selectCell(cell);
            }
        }
    }
}
