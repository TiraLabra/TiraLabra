Toteutus
========

Rakenne
-------

Ohjelma koostuu peli-, käyttöjärjestelmä-, tietorakenne- ja algoritmiluokista. Kurssin kannalta kiinnostavimpia ovat tietorakenne- ja algoritmiluokat. Niiden avulla on toteutettu peliä pelaavat tekoälyt. Yksi tekoälyistä käyttää minimax-algoritmia. Se on toteutettu wikipedian pseudokoodin perusteella:

function minimax(node, depth, maximizingPlayer)

    if depth = 0 or node is a terminal node
    
        return the heuristic value of node
        
    if maximizingPlayer
    
        bestValue := -∞
        
        for each child of node
        
            val := minimax(child, depth - 1, FALSE)
            
            bestValue := max(bestValue, val)
            
        return bestValue
        
    else
    
        bestValue := +∞
        
        for each child of node
        
            val := minimax(child, depth - 1, TRUE)
            
            bestValue := min(bestValue, val)
            
        return bestValue

(* Initial call for maximizing player *)
minimax(origin, depth, TRUE) [1]

Algoritmin toimintaa nopeutettiin lisäämällä siihen alfa-beeta -karsinta. Sekin perustuu wikipedian pseudokoodiin:

 function alphabeta(node, depth, α, β, maximizingPlayer)
 
      if depth = 0 or node is a terminal node
      
          return the heuristic value of node
          
      if maximizingPlayer
      
          for each child of node
          
              α := max(α, alphabeta(child, depth - 1, α, β, FALSE))
              
              if β ≤ α
              
                  break (* β cut-off *)
                  
          return α
          
      else
      
          for each child of node
          
              β := min(β, alphabeta(child, depth - 1, α, β, TRUE))
              
              if β ≤ α
              
                  break (* α cut-off *)
                  
          return β
          
(* Initial call *)
alphabeta(origin, depth, -∞, +∞, TRUE) [2]

Ohjelmassa on mahdollista valita algoritmin käyttämä hakusyvyys. Tällä hetkellä vaihtoehdot ovat 1-7, mutta alfa-beeta -karsinta mahdollistaisi ehkä suuremmatkin hakusyvyydet.

Minimax-algoritmin lisäksi ohjelmassa on tekoäly, joka valitsee sallittujen siirtojen listalta aina ensimmäisen mahdollisen siirron.

Algoritmit käyttävät itse toteutettuja tietorakenteita listaa ja solmua. Jokaiseen solmuun voidaan tallentaa siirto ja pelilaudan arvo kyseisen siirron jälkeen. Listaan taas tallennetaan tässä tapauksessa solmuja ja listatietorakenteen käyttö mahdollistaa sen, että lisättävien solmujen määrää ei tarvitse tietää ennalta ja listalta on helppoa kysyä sen suurinta arvoa, jota tarvitaan parhaan siirron selvittämiseen.

Aika- ja tilavaativuudet
------------------------

Minimax-algoritmin aikavaativuus riippuu läpikäytyjen solmujen määrästä. Läpikäytyjen solmujen määrä taas riippuu siitä, kuinka moneen solmuun kustakin solmusta voidaan haarautua eli kuinka monta mahdollista siirtoa kussakin pelitilanteessa on ja kuinka monta siirtoa eteenpäin pelitilanteita tutkitaan. Jos mahdollisten siirtojen määrä kullakin tasolla on b ja laskettavien tasojen määrä on d, aikavaativuus on O(b^d). Alfa-beta -karsinta pienentää keskimäärin tutkittavien solmujen määrää. Huonoimmassa mahdollisessa tilanteessa aikavaativuus on sama kuin minimax-algoritmilla ilman karsintaa. Parhaimmassa mahdollisessa tilanteessa taas onnistuttaisiin aina tutkimaan ensin paras mahdollinen siirto. Tällöin tutkittavien solmujen määrä puolittuisi ja aikavaativuus olisi O(b^(d/2)). Todellisuudessa ei tietenkään onnistutta aina tutkimaan ensin parasta mahdollista siirtoa ja aikavaativuus on jotain näiden kahden arvon välistä eli keskimäärin o(b^(3d/4)). [2]


Puutteet ja parannusehdotukset
------------------------------

Algoritmia ei ole järkevää käyttää kovin suurilla hakusyvyyksillä, koska sen toiminta muuttuu tällöin erittäin hitaaksi. Hakusyvyyden kasvattamisesta saattaisi joissain tilanteissa kuitenkin olla hyötyä. Tämän takia tutkittavien solmujen määrää olisi hyvä pystyä karsimaan mahdollisimman paljon. Alfa-beeta -karsinta auttaa tässä, mutta on olemassa myös tehokkaampia tapoja karsia tutkittavien solmujen määrää.


Lähteet
-------

[1] http://en.wikipedia.org/wiki/Minimax (24.8.2014)
[2] http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning (31.8.2014)


