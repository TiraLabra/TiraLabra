package Astar;

/**
*	AtahtiReitinhaku-luokka pitää sisällään A*-algoritmin.
*	Luokka tulee käyttämään Keko-luokkaa tietorakenteenaan.
*	@author Ilkka Maristo
*/
public class AtahtiReitinhaku {
	
    private Keko avoinLista; 
	private char[][] labyrintti;
	private Ruutu[][] ruudut;
	private Ruutu lahtoRuutu; 
	private Ruutu maaliRuutu;

    /**
    * Konstruktori
    * @param k Labyrintti, jota käsitellään.
    */
    public AtahtiReitinhaku(char[][] k){

        this.labyrintti = k;
        
    }

    /**
    * Luodaan lähtöRuutu-olio.
    * @param lahtoY lähtoruudun y-kordinaatit.
    * @param lahtoX Lähtoruudun x-kordinaatit.
    */
    public boolean setLahto(int lahtoY, int lahtoX){
        // lähtöruudulla ei ole vanhempaa
        this.lahtoRuutu = new Ruutu(null, lahtoY, lahtoX, 0, 0);
        return true;
    }
    /**
    * Luodaan maaliRuutu-olio jota verrataan kun selvitetään
    * ollaanko päädytty maaliin.
    * @param maaliY Maaliruudun y-kordinaatit.
    * @param maaliX Maaliruudun x-kordinaatit.
    */
    public boolean setMaali(int maaliY, int maaliX){
        this.maaliRuutu = new Ruutu(null, maaliY, maaliX, 0, 0);
        return true;
    }
    /**
    * Metodi, joka suorittaa itse a*-algoritmin. 
    * @param kork labyrintin korkeus.
    * @param lev labyrintin leveys.
    */
    public char[][] aTahtiAlgoritmi(int kork, int lev) {

        int korkeus = kork;
		int leveys = lev;

		this.avoinLista = new Keko(korkeus, leveys);
		this.ruudut = new Ruutu[korkeus][leveys];
		
	// lähtöruutu avoimeen listaan
        lisaaKekoon(this.lahtoRuutu.getY(), this.lahtoRuutu.getX(), 0, null);
	Ruutu kasiteltava = null;

	int naapuriY = 0;
        int naapuriX = 0;
	int naapuriG = 0;
        Ruutu naapuriVanhempi = null;

	// toistetaan kunnes avointen ruutujen lista on tyhjä
        // TODO: onTyhja()
	while (!this.avoinLista.onTyhja()) 
        {
			
            //kasiteltava = poistaRuutuKeosta();
            kasiteltava.setLoytyykoSuljetusta(true);

            // jos sama kuin maaliruutu, ollaan maalissa, joten voidaan
            // merkata paraspolku ja palauttaa se
            if (kasiteltava.getY() == this.maaliRuutu.getY() &&
                kasiteltava.getX() == this.maaliRuutu.getX()) {
                    return this.luoPolku(kasiteltava);
            }
			
            // Tutkitaan naapuriruudut
            for (int i = 0; i < 4; i++) {
			
		// yllä-
                if (i==0) {
                    // jos ei olla labyrintin yläreunassa
                    if (kasiteltava.getY() != 0){
                        naapuriY = kasiteltava.getY() - 1;
                        naapuriX = kasiteltava.getX();
                        naapuriG = kasiteltava.getG()+1;
                        naapuriVanhempi = kasiteltava;
                    }
                }
		// vasemmalla-
		else if (i==1){
                    // jos ei olla labyrintin vasemmassa reunassa
                    if (kasiteltava.getX() != 0){
                        naapuriY = kasiteltava.getY();
                        naapuriX = kasiteltava.getX() - 1;
                        naapuriG = kasiteltava.getG()+1;
                        naapuriVanhempi = kasiteltava;
                    }
                }
		// oikealla-
		else if (i==2){
                    // jos ei olla labyrintin oikeassa reunassa
                    if (kasiteltava.getX() != leveys-1){
                        naapuriY = kasiteltava.getY();
                        naapuriX = kasiteltava.getX() + 1;
                        naapuriG = kasiteltava.getG()+1;
                        naapuriVanhempi = kasiteltava;
                    }
                }
		//alla-oleva ruutu
		else if (i==3){
                    // jos ei olla labyrintin alareunassa
                    if (kasiteltava.getY() != korkeus-1){
                        naapuriY = kasiteltava.getY() + 1;
                        naapuriX = kasiteltava.getX();
                        naapuriG = kasiteltava.getG()+1;
                        naapuriVanhempi = kasiteltava;
                    }
            }


                // jos naapuriruutua ei ole olemassa, lisataan se avoimeenlistaan
                if(this.ruudut[naapuriY][naapuriX] == null){
                    this.lisaaKekoon(naapuriY,naapuriX,naapuriG, naapuriVanhempi);
                }
				
            // Jos ruutu on suljetussalistassa, ei tarvitse tehdä mitään,
            // koska tämä A*-algoritmi _ei_ salli diagonaalisia siirtymiä
            // ruutujen välillä. Näin ollen jokaisen siirtymän kustannus on aina
            // vakio 1, joten G-arvojen vertailua ei tarvitse tehda.
            
			
          } // for

        }// while
	
	// ei ratkaisua
        System.out.println("Ei ratkaisua");
           return null;
	}
    
	/**
	* Muokkaa labyrinttia siten, etta lisaa 'o' merkkeja
	* ilmaisemaan parhaan loydetyn polun.
	* @param ruutu Ruutu, joka on maaliruutu.
	* @return Labyrintti, johon merkitty lyhin polku 'o'-merkeilla.
	*/
        private char[][] luoPolku(Ruutu ruutu) {

		//TODO
		return labyrintti;
	}
    
	/**
	* Luo annettujen parametrien avulla uuden Ruutu-olion,
	* joka laitetaan luonnin jalkeen avoimeenlistaan (eli RuutuKekoon).
	* @param y Ko. ruudun y-akselin arvo.
	* @param x Ko. ruudun x-akselin arvo.
	* @param g Ko. ruudun g-arvo.
	* @param parent Ko. ruudun vanhempi.
	*/
	public void lisaaKekoon(int y, int x, int g, Ruutu parent) {
            
            //TODO

	}
        
	/**
	* Poistaa pienimman F-arvon omaavan ruudun avoimestalistasta.
	* @return Avoimestalistasta poistettu ruutu
	*/
	
       public Ruutu poistaRuutuKeosta() {
            
            //TODO
            
		return poistettu;
	}
        
        
	/**
	* Laskee heuristisen arvion parametreina annetuiden koordinaattien
	* perusteella. Kyseessa on ns. manhattanin heurestiikka.
	* Tämä heurestiikka on toimiva, sillä ko. ohjelmassa liikutaan vain vaaka-
	* ja pystysuunnassa, jolloin matkaa ei koskaan yliarvioida.	
	* @param y Ko. ruudun y-akselin arvo.
	* @param x Ko. ruudun x-akselin arvo.
	* @return Laskettu H-arvo.	
	*/
    private int heuristinen_arvio(int y, int x) {
        int h = 1*(Math.abs(x - this.maaliRuutu.getX()) + Math.abs(y - this.maaliRuutu.getY()));
        return h;
    }
}


