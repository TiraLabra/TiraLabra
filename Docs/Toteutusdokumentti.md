Toteutus
========

Rakenne
-------

Tähän voisi ehkä piirtää vaikka luokkakaavion

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


Aika- ja tilavaativuudet
------------------------

Minimax-algoritmin aikavaativuus riippuu läpikäytyjen solmujen määrästä. Läpikäytyjen solmujen määrä taas riippuu siitä, kuinka moneen solmuun kustakin solmusta voidaan haarautua eli kuinka monta mahdollista siirtoa kussakin pelitilanteessa on ja kuinka monta siirtoa eteenpäin pelitilanteita tutkitaan. Jos mahdollisten siirtojen määrä kullakin tasolla on b ja laskettavien tasojen määrä on d, aikavaativuus on O(b^d).


Puutteet ja parannusehdotukset
------------------------------

Algoritmia ei ole järkevää käyttää kovin suurilla hakusyvyyksillä, koska sen toiminta muuttuu tällöin erittäin hitaaksi. Hakusyvyyden kasvattamisesta saattaisi joissain tilanteissa kuitenkin olla hyötyä. Tämän takia tutkittavien solmujen määrää olisi hyvä pystyä karsimaan mahdollisimman paljon.


Lähteet
-------

[1] http://en.wikipedia.org/wiki/Minimax (24.8.2014)


