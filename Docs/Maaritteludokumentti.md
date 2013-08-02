Valitsin tehtäväkseni labyrintin lyhimmän ratkaisun löytämisen. Labyrintti 
toteutetaan tekstinä, jossa 0 merkitsee seinää ja 1 merkitsee seinätöntä
 kohtaa. Algoritmin edetessä reittiä merkitään pisteellä. Käyttäjän on mahdollista
luoda oma labyrintti antamalla syötteenä ykkösiä ja nollia rivi kerrallaan.
  Tulen käyttämään ratkaisussa verkon leveyssuuntaista läpikäyntiä (BFS). Tämä
 algoritmi sopii hyvin labyrintin lyhimmän ratkaisun läpikäyntiin, sillä voin
 tarkastella tilannetta taso kerrallaan, etsien aina parasta mahdollista 
vaihtoehtoa. Algoritmi pitää muistissaan aina leveyssuuntaispuuta, joka kertoo
 lyhimmän reitin alusta vuorossa olevaan solmuun. Puu on toteutettu taulukkona.
 Koska labyrintin kohdalla on kyseessä suuntaamaton verkko, on algoritmin
 aikavaativuus (|V| + |E|) ja tilavaativuus pahimmassa tapauksessa |V|
 (Tietorakenteiden perusteet, luentomateriaali, kevät 2013). 
Aion ratkaista ongelman myös käyttäen A*-tietorakennetta ja Dijkstran algoritmia.
Dijkstran algoritmi käyttää apunaan minimikekoa, jonne läpikäytyjen solmujen 
etäisyydet tallennetaan. Dijkstran algoritmi etsii aina reitin, jota pitkin 
on lyhin matka seuraavaan solmuun, joten se tuonee myös ratkaisun tähän ongelmaan. 
Dijkstran algoritmin aikavaativuus on ((|E|+|V|)log|V|) ja tilavaativuus on |V|.
A*-tietorakenne on kuten Dijkstran algoritmi mutta voi liikkua myös ruudukolla 
myös 45 asteen kulmassa ja näin ollen on oletettavasti nopeampi kuin Dijkstran 
algoritmi. 
