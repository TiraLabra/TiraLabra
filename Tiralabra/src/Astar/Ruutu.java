package Astar;

/**
*	A*-algoritmin labyrintti muodostuu ruuduista.
*	Luokka 'Ruutu' pitää sisällään kaikki yhden labyrintilla sijaitsevan
*	ruudun ominaisuudet.
*	@author Ilkka Maristo
*/
public class Ruutu {

    /**
     * Path Scoring: F = G + H
     * http://www.policyalmanac.org/games/aStarTutorial.htm
     *
     * Luokassa Ruutu kaytetään yllä olevan tutoriaalin mukaisia
     * muuttujia f, g ja h.
     *
     * g = etäisyys alkuun
     * h = arvioitu etäisyys maaliin
     */
	
	// A*-F-arvo
	private int f;
	// A*-G-arvo
        private int g;
	// A*-H-arvo
        private int h;
	// Kordinaatit labyrintilla
        private int paikkaY;
	private int paikkaX;
	// Viite vanhempaan
        private Ruutu parent;
        // Onko ruutu suljetussa listassa
        private boolean onkoSuljetussa;
        // Onko ruutu avoimessa listassa
        private boolean onkoAvoimessa;

    /**
    * Konstruktori, jossa asetetaan ruudulle tiedot sen vanhemmasta,
    * y- ja x-akselin arvoista, g- ja h-arvoista, sekä lasketaan f-arvo
    * edellisten avulla.
    * @param vanhempi Ruudun vanhempi
    * @param akseliY Ruudun y-akselin arvo
    * @param akseliX Ruudun x-akselin arvo
    * @param arvoG Ruudun G-arvo
    * @param arvoH Ruudun H-arvo
    */	
    public Ruutu (Ruutu vanhempi, int akseliY, int akseliX, int arvoG, int arvoH){
	this.parent = vanhempi;
	this.g = arvoG;
	this.h = arvoH;
	this.f = (arvoG + arvoH);
	this.paikkaY = akseliY;
	this.paikkaX = akseliX;
	this.onkoSuljetussa = false;
	this.onkoAvoimessa = false;
    }
    
    /**
    * Metodi vertailee parametrina saadun ruudun f-arvoa
    * ko. olion f-arvoon. 
    * @param vertailtava Ruutu jota vertaillaan tähän olioon.
    * @return Vertailun erotus	
    */
    public int vertaileFarvoja(Ruutu vertailtava){
	int erotus = this.f - vertailtava.getF();
	return erotus;
        // negatiivinen, jos vertailtava suurempi
    }
	
    /**
    * Asettaa ruudulle vanhemman.
    * @param vanhempi Ruudun uusi vanhempi.
    */
    public void setParent(Ruutu vanhempi){
	this.parent = vanhempi;
    }
	
    /**
    * Palauttaa ruudun vanhemman.
    * @return Ruutu, joka on ko. ruudun vanhempi.
    */
    public Ruutu getParent(){ 
	return this.parent;
    }
	
    /**
    * Palauttaa ruudun x-akselin arvon.
    * @return Ruudun x-akselin arvo.
    */
    public int getX(){
        return this.paikkaX;
    }
	
    /**
    * Palauttaa ruudun y-akselin arvon.
    * @return Ruudun y-akselin arvo.
    */
    public int getY(){
        return this.paikkaY;
   }
	
    /**
    * Palauttaa ruudun H-arvon.
    * @return Ruudun H-arvo.
    */
    public int getH(){
        return this.h;
    }

    /**
    * Asettaa ruudulle G-arvon.
    * @param g Ruudun G-arvo.
    */
    public void setG(int g) {
	this.g = g;
                // g vaikuttaa myös f:n arvoon
	this.f = this.g + this.h;
    }
	
    /**
    * Palauttaa ruudun G-arvon.
    * @return Ruudun G-arvo.
    */
    public int getG(){
	return this.g;
    }
	
    /**
    * Palauttaa ruudun F-arvon.
    * @return Ruudun F-arvo.
    */
    public int getF(){
	return this.f;
    }
	
    /**
    * Asettaa ruudulle tiedon siitä, loytyykö se avoimestalistasta.
    * @param b Totuusarvo siitä, loytyykö avoimestalistasta.
    */
    public void setLoytyykoAvoimesta(boolean b){
        onkoAvoimessa = b;
    }
	
    /**
    * Tieto siitä loytyykö ruutu avoimestalistasta.
    * @return Totuusarvo siitä loytyykö ruutu avoimestalistasta.
    */
    public boolean onkoAvoimessa(){
        return onkoAvoimessa;
    }
	
    /**
    * Asettaa ruudulle tiedon siitä, loytyyko se suljetustalistasta.
    * @param b Totuusarvo siitä, loytyykö suljetustalistasta.
    */
    public void setLoytyykoSuljetusta(boolean b){
        onkoSuljetussa = b;
    }
	
    /**
    * Tieto siita loytyykö ruutu suljetustalistasta.
    * @return Totuusarvo siitä loytyykö ruutu suljetustalistasta.
    */
    public boolean onkoSuljetussa(){
        return onkoSuljetussa;
    }

}

