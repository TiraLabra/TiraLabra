package Hike;





import Hike.gameWindow.GameWindow;
import javax.swing.SwingUtilities;

class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        GameWindow game = new GameWindow();
        game.run();

        
    }
}
