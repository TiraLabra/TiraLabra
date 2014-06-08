package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import labyrinthgenerator.*;
import labyrinthsolver.*;
import main.Labyrinth;

/**
 * Graafinen käyttöliittymä.
 *
 * @author Juri Kuronen
 */
public class Gui implements Runnable {

    /**
     * Ikkuna.
     */
    JFrame frame;
    /**
     * Labyrintti, jolle gui luodaan.
     */
    Labyrinth labyrinth;
    /**
     * Piirtoalusta.
     */
    Canvas canvas;
    /**
     * Labyrintin korkeuden tai leveyden maksimikoko, minkä GUI hyväksyy.
     */
    int maxSize;
    /**
     * Labyrintin korkeuden tai leveyden minimikoko, minkä GUI hyväksyy.
     */
    int minSize;

    /**
     * Asettaa labyrintin ja maksimikoon.
     *
     * @param l Labyrintti, jolle gui luodaan.
     */
    public Gui(Labyrinth l) {
        labyrinth = l;
        maxSize = 100;
        minSize = 2;
    }

    /**
     * Alustaa ja käynnistää guin.
     */
    @Override
    public void run() {
        frame = new JFrame("Labyrinth solver");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            addCanvas(frame.getContentPane());
        } catch (Exception e) {
            openSetSizeWindow();
            return;
        }
        addMenuBar();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Laskee solukoon piirtoalustaa varten. Solukoko on 500 jaettuna
     * max(labyrintin korkeus, labyrintin leveys).
     *
     * @return Palauttaa solukoon piirtoalustaa varten.
     */
    public int getCellSize() {
        if (labyrinth.getHeight() > labyrinth.getWidth()) {
            return 500 / labyrinth.getHeight();
        }
        return 500 / labyrinth.getWidth();
    }

    /**
     * Lisää piirtoalustan ikkunaan.
     *
     * @param container Container, mihin piirtoalusta lisätään.
     * @throws Exception Heittää poikkeuksen, jos labyrintin koko ei ole
     * hyväksyttävä.
     */
    void addCanvas(Container container) throws Exception {
        int cellSize = getCellSize();
        canvas = new Canvas(labyrinth, cellSize);
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        if (height > maxSize || height < minSize || width > maxSize || width < minSize) {
            throw new Exception("Gui can only handle up to 95x95 labyrinths!");
        }
        canvas.setPreferredSize(new Dimension((2 + width) * cellSize, (2 + height) * cellSize));
        container.add(canvas);
    }

    /**
     * Lisää ikkunaan menun.
     */
    void addMenuBar() {
        JMenuBar menubar = new JMenuBar();

        /*
         Menu-valinta labyrintin editoimiseen.
         */
        JMenu lMenu = new JMenu("Labyrinth");
        lMenu.add(createSetSizeMenuItem());

        /*
         * Menu-valinnat labyrintin generoijan valitsemiseen.
         */
        JMenu lgMenu = new JMenu("Choose generator");
        lgMenu.add(createLabyrinthGeneratorMenuItem(new KruskalsAlgorithm(), "Kruskal's Algorithm"));
        lgMenu.add(createLabyrinthGeneratorMenuItem(new PrimsAlgorithm(), "Prim's Algorithm"));
        lgMenu.add(createLabyrinthGeneratorMenuItem(new RecursiveBacktracker(), "Recursive Backtracker"));

        /*
         Menu-valinnat labyrintin ratkaisijan valitsemiseen.
         */
        JMenu lsMenu = new JMenu("Choose solver");
        lsMenu.add(createLabyrinthSolverMenuItem(new DFS(), "DFS"));
        lsMenu.add(createLabyrinthSolverMenuItem(new BFS(), "BFS"));
        lsMenu.add(createLabyrinthSolverMenuItem(new WallFollower(), "Wall follower"));

        /*
         Luo päämenu, lisää menuvalinnat ja lisää lopuksi päämenu menubariin.
         */
        JMenu main = new JMenu("Modify labyrinth");
        main.add(lMenu);
        main.add(lgMenu);
        main.add(lsMenu);
        menubar.add(main);

        /*
         Generointi- ja ratkojanappulat.
         */
        menubar.add(createGenerateButton());
        menubar.add(createSolveButton());

        /*
         Lisää menubar ikkunaan.
         */
        frame.setJMenuBar(menubar);
    }

    /**
     * Luo sliderin labyrintin kokotiedon säätämiseen.
     *
     * @param label JLabel, johon kirjoitetaan sliderin kokotieto.
     * @param labelText Teksti, joka ilmaisee mitä kokotietoa muutetaan.
     * @return Palauttaa uuden sliderin labyrintin kokotiedon säätämiseen.
     */
    JSlider createSlider(final JLabel label, final String labelText) {
        JSlider slider = new JSlider();
        slider.setMinimum(5);
        slider.setMaximum(95);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider thisSlider = (JSlider) changeEvent.getSource();
                if (thisSlider.getValueIsAdjusting()) {
                    label.setText(labelText + ": " + thisSlider.getValue());
                }
            }
        });
        return slider;
    }

    /**
     * Luo menu-valinnan kokotietojen asettamiseen, ja lisää menu-valintaan
     * tapahtuman kuuntelijan, joka avaa säätelyikkunan.
     *
     * @return Palauttaa menu-valinnan labyrintin kokotietojen asettamiseen.
     *
     * @see openSetSizeWindow()
     */
    JMenuItem createSetSizeMenuItem() {
        JMenuItem item = new JMenuItem("Set size");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSetSizeWindow();
            }
        });
        return item;
    }

    /**
     * Avaa ikkunan, jolla voi säädellä labyrintin kokotietoja.
     *
     * @see createSlider
     */
    void openSetSizeWindow() {
        JOptionPane optionPane = new JOptionPane();
        JLabel widthLabel = new JLabel();
        widthLabel.setText("width: 50");
        JLabel heightLabel = new JLabel();
        heightLabel.setText("height: 50");
        JSlider widthSlider = createSlider(widthLabel, "width");
        JSlider heightSlider = createSlider(heightLabel, "height");
        Object[] objects = {widthLabel, widthSlider, heightLabel, heightSlider};
        optionPane.setMessage(objects);
        JDialog dialog = optionPane.createDialog(frame, "Set labyrinth size");
        dialog.setVisible(true);
        if (!dialog.isVisible()) {
            int newWidth = widthSlider.getValue();
            int newHeight = heightSlider.getValue();
            labyrinth.updateLabyrinth(newWidth, newHeight);
            frame.dispose();
            run();
        }
    }

    /**
     * Luo menu-valinnan annetun labyrintin generoijan valinnalle, ja lisää
     * menu-valintaan tapahtuman kuuntelijan.
     *
     * @param lg Annettu labyrintin generoija.
     * @param title Menu-valinnan otsikko.
     * @return Palauttaa menu-valinnan annetun labyrintin generoijan valinnalle.
     *
     * @see
     * main.Labyrinth#setLabyrinthGenerator(labyrinthgenerator.LabyrinthGenerator)
     */
    JMenuItem createLabyrinthGeneratorMenuItem(final LabyrinthGenerator lg, final String title) {
        JMenuItem item = new JMenuItem(title);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinth.setLabyrinthGenerator(lg);
            }
        });
        return item;
    }

    /**
     * Luo menu-valinnan annetun labyrintin ratkaisijan valinnalle, ja lisää
     * menu-valintaan tapahtuman kuuntelijan.
     *
     * @param ls Annettu labyrintin ratkaisija.
     * @param title Menu-valinnan otsikko.
     * @return Palauttaa menu-valinnan annetun labyrintin ratkaisijan
     * valinnalle.
     *
     * @see main.Labyrinth#setLabyrinthSolver(labyrinthsolver.LabyrinthSolver)
     */
    JMenuItem createLabyrinthSolverMenuItem(final LabyrinthSolver ls, final String title) {
        JMenuItem item = new JMenuItem(title);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinth.setLabyrinthSolver(ls);
            }
        });
        return item;
    }

    /**
     * Luo 'Generate!'-nappulan ja lisää siihen tapahtuman kuuntelijan.
     *
     * @return Palauttaa 'Generate!'-nappulan.
     * @see main.Labyrinth#generateLabyrinth()
     */
    JButton createGenerateButton() {
        JButton gen = new JButton("Generate!");
        gen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    labyrinth.generateLabyrinth();
                    canvas.update();
                } catch (Exception ex) {
                }
            }
        });
        return gen;
    }

    /**
     * Luo 'Solve!'-nappulan ja lisää siihen tapahtuman kuuntelijan.
     *
     * @return Palauttaa 'Solve!'-nappulan.
     *
     * @see main.Labyrinth#solveLabyrinth()
     */
    JButton createSolveButton() {
        JButton solve = new JButton("Solve!");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinth.solveLabyrinth();
                canvas.update();
            }
        });
        return solve;
    }

}
