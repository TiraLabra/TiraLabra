Määrittelydokumentti
====================
Aihe - Polunetsintä
-------------------
Polunetsinnän tarkoituksena on löytää mahdollisimman lyhyt reitti lähdöstä maaliin. Ennen polunetsinnän suorittamista ympäristöstä luodaan verkko, jossa polunetsintä suoritetaan.  

Polunetsintään käytän A*-algoritmia, koska sen avulla voi löytää lyhimmän reitin ja se on tehokas. 

Ohjelma saa syötteenä käyttäjältä kartan, jonka ohjelma muuttaa taulukoksi. Karttaa voi ajatella vaikkapa maastokarttana/labyrinttina, jossa kulkee polkuja (solmun painoarvo pieni) tai suota, jonka painoarvo on suuri tai joki, jota ei voi ylittää. Kartta koostuu numeroista, jotka kuvaavat solmun arvoa. Pienin arvo on 0 ja suurin on 9, joka tarkoittaa solmua, johon ei voi siirtyä. 

Aikavaativuuden tavoite algoritmille on O((m+n)log n), jossa m tarkoittaa verkon kaarien lukumäärää ja n solmujen lukumäärää. Käymättömät solmut tallennetaan minimikekoon. Tilavaativuuden tavoite on O(n^2), sillä taulukko vie sen verran tilaa. 

Lähteet
-------
http://fi.wikipedia.org/wiki/Polunetsint%C3%A4 

http://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf  
