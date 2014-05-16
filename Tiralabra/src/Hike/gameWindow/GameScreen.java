package Hike.gameWindow;

import Hike.Algorithms.Dijkstra;
import Hike.ImageTable.ImageTable;
import Hike.controls.ClickListener;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;


import javax.swing.*;

public class GameScreen extends JPanel {

    private ShowPicture picture;
    private ShowPicture pixel;
    private int pixelX;
    private int pixelY;
    private ActionListener listener;
    private ImageTable table;
    private Dijkstra droute;
    private Graphics gra;
    private Object bimage;
    private MapGraphic map;

    public GameScreen() {
        setBackground(Color.gray);
        setLayout(null);

        JTextArea teksti = new JTextArea("Instruction, instructions. ");
        teksti.setBackground(Color.gray);
        teksti.setLineWrap(true);
        teksti.setBounds(50, 50, 200, 50);

        JButton ok = new JButton("Start");
        listener = new ClickListener(this);
        ok.setActionCommand(ClickListener.Actions.STARTGAME.name());
        ok.setBounds(600, 50, 100, 50);
        ok.addActionListener(listener);

        picture = new ShowPicture("../help.png");
        picture.setBounds(100, 200, 500, 300);
        add(teksti);
        add(ok);
        add(picture);

    }

    public void createPlayingField(String file) {
        removeAll();
        pixel = new ShowPicture("../pixel.png");
        picture = new ShowPicture(file);
        table = new ImageTable(picture);
        droute = new Dijkstra(table);
        


        picture.setBounds(50, 50, 800, 500);
        add(picture);
        repaint();

    }

    public void paintPixel(int y, int x) {
        pixel = new ShowPicture("../pixel.png");
        pixel.setBackground(Color.RED);
        pixel.setBounds(y, x, 1, 1);

        add(pixel);
        System.out.println("done");






    }

    public void openMap() {
        map = new MapGraphic();
        map.run();
    }
    


}
