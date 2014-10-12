package Käyttöliittymä;



import Algoritmi.Reitinhaku;
import Algoritmi.Verkko;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class Aloitus extends JPanel implements ActionListener {
    

    private static String Piirrä = "Piirrä";
    private static String Tyhjennä = "Tyhjennä";
    private static String Puhdista = "Puhdista";
     
    private Kuva ikkuna;
    private Verkko verkko;
    private Reitinhaku reitti;
    
    /**
     * Konstruktori luo tarvittavat komponentit ja asettaa ne paikoilleen.
     */ 
    
    public Aloitus() {
        super(new BorderLayout());
         
        ikkuna = new Kuva();

 
        JButton Piirränappi = new JButton("Piirrä");
        Piirränappi.setActionCommand(Piirrä);
        Piirränappi.addActionListener(this);
         
        JButton Tyhjennänappi = new JButton("Tyhjennä");
        Tyhjennänappi.setActionCommand(Tyhjennä);
        Tyhjennänappi.addActionListener(this);
         
        JButton Puhdistanappi = new JButton("Puhdista");
        Puhdistanappi.setActionCommand(Puhdista);
        Puhdistanappi.addActionListener(this);
 
        ikkuna.setPreferredSize(new Dimension(800, 800));
        add(ikkuna, BorderLayout.CENTER);
 
        JPanel panel = new JPanel(new GridLayout(0,3));
        panel.add(Piirränappi);
        panel.add(Tyhjennänappi);
        panel.add(Puhdistanappi);
        add(panel, BorderLayout.SOUTH);
    }
 
    /**
     * Sisältää eri nappien painamisten seuraukset.
     * Nappi piirrä: etsii reitin ja piirtää sen.
     * Nappi Tyhjennä: poistaa kaiken mitä kuvaan on piirretty.
     * Nappi Puhdista: poistaa kaiken mitä kuvaan on piirretty paitsi seinäviivat.
     * 
     */     
    public void actionPerformed(ActionEvent e) {
        String komento = e.getActionCommand();
         
        if (Piirrä.equals(komento)) {
            
            try{
                verkko = new Verkko(ikkuna.kuva, ikkuna.haeMaali());
                reitti = new Reitinhaku(verkko, ikkuna.haeLahto(), (ikkuna.kuva.getHeight()*ikkuna.kuva.getWidth()));
                
                if(reitti.Haku()){
                    ikkuna.repaint();
                }else{
                    putsaa();
                    JOptionPane.showMessageDialog(new JFrame(), "Reittiä ei löytynyt");
                }   
                
                
            }catch(NullPointerException ex){
            }
            
        } else if (Tyhjennä.equals(komento)) {
            
            ikkuna.haeKuva();
            ikkuna.repaint();
            
        } else if (Puhdista.equals(komento)) {
            
            try{
                putsaa();
                ikkuna.repaint();
            }catch(NullPointerException ex){
            }
            
        }
    }
 
    /**
     * Luo ikkunan.
     */
    private static void LuoIkkuna() {

        JFrame frame = new JFrame("Shift+klik = alkupiste        CTRL+klik = loppupiste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Aloitus newContentPane = new Aloitus();
        frame.setContentPane(newContentPane);
 
        frame.pack();
        frame.setVisible(true);
    }

    
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                 LuoIkkuna();

            }
        });
    }
    
    /**
     * Poistaa kaiken mitä kuvaan on piirretty paitsi seinäviivat.
     */    
    public void putsaa(){
        
        int rgb=new Color(255,255,255).getRGB();
        for (int i = 0; i < verkko.taulukko[0].length; i++) {
            for (int j = 0; j < verkko.taulukko.length; j++) {
                if(verkko.taulukko[i][j].haeSeina()==false){
                    
                    ikkuna.kuva.setRGB(j, i, rgb);
                }
            }
        }
    }
}