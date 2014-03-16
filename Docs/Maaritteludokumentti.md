<h1>Määrittelydokumentti</h1>

Harjoitustyöni tarkoituksena on jatkokehittää ohjelmoinnin harjoitustyönä tekemääni Pacman-peliä. Tämän hetkisessä versiossa haamut liikkuvat käytäviä pitkin satunnaisesti minne sattuu, aion muokata peliä siten, että jokaisella haamulla on oma tavoitteensa; eli ruutu johon ne jokaisella vuorollaan aina pyrkivät. Jokaisella haamulla vaihtuu tavoite ruutu, joka liikkumiskerralla, sillä ruutu riippuu Pacmanin sijainnista alustalla. Tämän takia paras reitti haluttuun ruutuun täytyy laskea ennen jokaista liikkumiskertaa. 

Aion parhaimman mahdollisimman reitin selvittämiseen käyttää A*-algoritmia, koska algoritmi löytää varmasti lyhimmän reitin haluttuun ruutuun tehokkaasti.

Käyttäjä ei anna ohjelmalle syötteitä, mutta ohjelma saa pelialustan tekstitiedostona, jonka perusteella pelialusta luodaan. Tekstitiedostossa pelialustan eri ominaisuudet on merkitty eri tunnuksin, mitkä koodi sitten tunnistaa ja asettaa ominaisuudet alustalle.

A*-algoritmin aikavaativuuden tavoite on tässä ohjelmassa O(|E|+|V|log|V|) ja tilavaativuuden tavoite on O(n2).

Lähteet:
http://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf
