/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

import sanapuuro.grid.Grid;

/**
 *
 * @author skaipio
 */
public class TwoPlayerGame{
    private final Player playerOne, playerTwo;
    private ConsoleController currentController;
    private final Grid grid;
    private int successiveSkips = 0;
    
    private GameView view;

    public TwoPlayerGame(Grid grid, Player playerOne, Player playerTwo) {
        this.grid = grid;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentController = playerOne.getController();
        this.view = new GameView(grid, this.currentController, playerOne, playerTwo);
    }

    public void startGame() {
        while(true){
            this.view.printView();
            boolean noSkip = currentController.makeMove();
            if (!noSkip){
                successiveSkips++;
            }else{
                successiveSkips = 0;
            }
            if (this.grid.isFull() || successiveSkips == 2){
                break;
            }
        }
    }
}
