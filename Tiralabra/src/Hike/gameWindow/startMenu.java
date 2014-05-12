package Hike.gameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;

class startMenu extends JPanel {

    private JFrame scorePanel;

    public startMenu() throws IOException {
        setBackground(Color.white);

        setLayout(null);
        JTextArea teksti = new JTextArea("Instruction, instructions. ");
        teksti.setBackground(Color.white);

        teksti.setLineWrap(true);
        teksti.setBounds(50, 50, 500, 200);

        JButton ok = new JButton("Start");
        ok.setBounds(600, 50, 100, 50);

        ImageIcon guide = new ImageIcon("./Pictures/guide.png");

        JLabel guideP = new JLabel(guide);
        guideP.setBounds(50, 50, 500, 200);

        add(teksti);
        add(ok);
        add(guideP);

    }

}
