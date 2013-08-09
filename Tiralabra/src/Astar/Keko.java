package Astar;

/**
*  Luokka toteuttaa tietorakenteen minimikeko. 
*  Keko toimii A*-algoritmin avoimenalistana.
*
*  @author Ilkka Maristo
*/
public class Keko {
    
    private Ruutu[] ruudut;
    private int ruutujenMaara;
    
    /**
    * Luo uuden keon ja varaa sille tilaa
    * korkeus * leveys verran (labyrintin maksimikoko)
    * @param korkeus Labyrintin korkeus
    * @param leveys Labyrintin leveys
    */
    public Keko(int korkeus, int leveys) {

	// indeksointi alkaa 1:sta.
    this.ruudut = new Ruutu[(korkeus*leveys)+1];
	this.ruutujenMaara = 0;
	
	}
}
