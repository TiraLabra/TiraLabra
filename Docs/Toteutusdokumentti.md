Toteutus
========

Rakenne
-------

Ohjelma koostuu peli-, käyttöjärjestelmä-, tietorakenne- ja algoritmiluokista. Kurssin kannalta kiinnostavimpia ovat tietorakenne- ja algoritmiluokat. Niiden avulla on toteutettu peliä pelaavat tekoälyt. Yksi tekoälyistä käyttää minimax-algoritmia. Se on toteutettu wikipedian pseudokoodin perusteella:

```
function minimax(node, depth, maximizingPlayer)

    if depth = 0 or node is a terminal node		//vakioaikainen
    
        return the heuristic value of node		//vakioaikainen
        
    if maximizingPlayer					//vakioaikainen
    
        bestValue := -∞					//vakioaikainen
        
        for each child of node				//suoritetaan mahdollisten siirtojen lukumäärän verran
        
            val := minimax(child, depth - 1, FALSE)	//rekursiivinen tämän metodin kutsu
            
            bestValue := max(bestValue, val)		//vakioaikainen
            
        return bestValue				//vakioaikainen
        
    else						//vakioaikainen
    
        bestValue := +∞					//vakioaikainen
        
        for each child of node				//suoritetaan mahdollisten siirtojen lukumäärän verran
        
            val := minimax(child, depth - 1, TRUE)	//rekursiivinen tämän metodin kutsu
            
            bestValue := min(bestValue, val)		//vakioaikainen
            
        return bestValue				//vakioaikainen

(* Initial call for maximizing player *)
minimax(origin, depth, TRUE) [1]
```

Algoritmin toimintaa nopeutettiin lisäämällä siihen alfa-beeta -karsinta. Sekin perustuu wikipedian pseudokoodiin:

```
 function alphabeta(node, depth, α, β, maximizingPlayer)
 
      if depth = 0 or node is a terminal node		//vakioaikainen
      
          return the heuristic value of node		/vakioaikainen
          
      if maximizingPlayer				//vakioaikainen
      
          for each child of node			//suoritetaan mahdollisten siirtojen lukumäärän verran
          
              α := max(α, alphabeta(child, depth - 1, α, β, FALSE))	//rekursiivinen tämän metodin kutsu
              
              if β ≤ α					//vakioaikainen
              
                  break (* β cut-off *)			//vakioaikainen
                  
          return α					//vakioaikainen
          
      else						//vakioaikainen
      
          for each child of node			//suoritetaan mahdollisten siirtojen lukumäärän verran
          
              β := min(β, alphabeta(child, depth - 1, α, β, TRUE))	//rekursiivinen tämän metodin kutsu
              
              if β ≤ α					//vakioaikainen
              
                  break (* α cut-off *)			//vakioaikainen
                  
          return β					//vakioaikainen
          
(* Initial call *)
alphabeta(origin, depth, -∞, +∞, TRUE) [2]
```

Ohjelmassa on mahdollista valita algoritmin käyttämä hakusyvyys. Tällä hetkellä vaihtoehdot ovat 1-9, mutta alfa-beeta -karsinta mahdollistaisi ehkä suuremmatkin hakusyvyydet.

Minimax-algoritmin lisäksi ohjelmassa on tekoäly, joka valitsee sallittujen siirtojen listalta aina ensimmäisen mahdollisen siirron.

Algoritmit käyttävät itse toteutettuja tietorakenteita listaa ja solmua. Jokaiseen solmuun voidaan tallentaa siirto ja pelilaudan arvo kyseisen siirron jälkeen. Listaan taas tallennetaan tässä tapauksessa solmuja ja listatietorakenteen käyttö mahdollistaa sen, että lisättävien solmujen määrää ei tarvitse tietää ennalta ja listalta on helppoa kysyä sen suurinta arvoa, jota tarvitaan parhaan siirron selvittämiseen. Listalta voidaan myös kysyä sen kokoa ja tietyssä kohdassa sijaitsevaa alkiota. Näitä hyödynnetään pelilaudan sallittujen siirtojen tallennukseen.

Myös käytetty heuristiikka on oleellinen osa tekoälyn minimax-tekoälyn toimintaa. Heuristiikan avulla pisteytetään pelilaudan tilanne mahdollisten siirtojen jälkeen, jotta voidaan selvittää mikä siirroista on paras. Heuristiikka perustuu laudalla olevien nappuloiden ja kruunattujen nappuloiden määriin ja niiden sijoittumiseen laudalla, nappuloiden liikkumismahdollisuuksiin sekä kruunausrivillä oleviin tyhjiin paikkoihin, jotka mahdollistaisivat jonkin nappulan kruunaamisen. Käytetty heuristiikka perustuu osittain Jacek Mandziukin, Magdalena Kusiakin ja Karol Waledziakin julkaisuun Evolutionary-based heuristic generators for checkers and give-away checkers [3].


Aika- ja tilavaativuudet
------------------------

Minimax-algoritmin aikavaativuus riippuu läpikäytyjen solmujen määrästä. Läpikäytyjen solmujen määrä taas riippuu siitä, kuinka moneen solmuun kustakin solmusta voidaan haarautua eli kuinka monta mahdollista siirtoa kussakin pelitilanteessa on ja kuinka monta siirtoa eteenpäin pelitilanteita tutkitaan. Jos mahdollisten siirtojen määrä kullakin tasolla on b ja laskettavien tasojen määrä on d, aikavaativuus on O(b^d). Alfa-beta -karsinta pienentää keskimäärin tutkittavien solmujen määrää. Huonoimmassa mahdollisessa tilanteessa aikavaativuus on sama kuin minimax-algoritmilla ilman karsintaa. Parhaimmassa mahdollisessa tilanteessa taas onnistuttaisiin aina tutkimaan ensin paras mahdollinen siirto. Tällöin tutkittavien solmujen määrä puolittuisi ja aikavaativuus olisi O(b^(d/2)). Todellisuudessa ei tietenkään onnistutta aina tutkimaan ensin parasta mahdollista siirtoa ja aikavaativuus on jotain näiden kahden arvon välistä eli keskimäärin o(b^(3d/4)). [2]


Puutteet ja parannusehdotukset
------------------------------

Algoritmia ei ole järkevää käyttää kovin suurilla hakusyvyyksillä, koska sen toiminta muuttuu tällöin erittäin hitaaksi. Hakusyvyyden kasvattamisesta saattaisi joissain tilanteissa kuitenkin olla hyötyä. Tämän takia tutkittavien solmujen määrää olisi hyvä pystyä karsimaan mahdollisimman paljon. Alfa-beeta -karsinta auttaa tässä, mutta on olemassa myös tehokkaampia tapoja karsia tutkittavien solmujen määrää.

Helpoin ja tehokkain tapa parantaa tekoälyn toimintaa olisi kuitenkin heuristiikan kehittäminen. Heuristiikassa ei oteta huomioon läheskään kaikkia lähteenäni käyttämässä tutkimuksessa [3] mainittuja asioita. Niiden lisääminen mukaan parantaisi varmaan merkittävästi tekoälyn toimintaa. Eri asioiden painotuksissakin olisi varmasti paljon hiottavaa. Näitä varten olisi ollut kätevää, jos eri tekoälyillä olisi voinut käyttää eri heuristiikkoja ja jos heuristiikkoja olisi kätevästi voinut muokata esim. painotuskertoimia muokkaamalla.

Aihevalinta ei varmasti ollut järkevin mahdollinen tälle kurssille. Ajattelin tammen muistuttvan riittävästi shakkia, jotta siitä olisi saanut hyvän tekoälyprojektin tälle kurssille. Todellisuudessa tammi oli kuitenkin ehkä turhan yksinkertainen peli tämäntyyppiselle tekoälylle. Heuristiikan merkistys tekoälyn toiminnassa oli paljon suurempi kuin osasin ajatellakaan, mutta erilaisten heuristiikoiden toteutus ja vertailu taas tuntui menevän ohi tämän kurssin aihepiiristä. En halunnut toteuttaa shakkia, koska en osaa pelata sitä, mutta tammessa oli ihan sama ongelma. En löytänyt mistään kunnollista materiaalia siitä, millaiset tilanteet tammessa ovat hyviä ja tavoiteltavia ja millaiset taas huonoja ja miten eri asioita pitäisi painottaa esimerkiksi heuristiikassa. Shakista tietoa olisi varmasti löytynyt helpommin.


Lähteet
-------

[1] http://en.wikipedia.org/wiki/Minimax (24.8.2014)
[2] http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning (31.8.2014)
[3] http://www.mini.pw.edu.pl/~kwaledzik/legacy/research/papers/ExpSys07-Checkers.pdf (6.9.2014)

