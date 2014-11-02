Määrittelydokumentti
====================

Harjoitustyössäni tulen tekemään ohjelman, joka etsii nopeimman reitin verkon kahden pisteen välillä. Ohjelmointikielenä toimii Java.


# Mitä algoritmeja ja tietorakenteita toteutat työssäsi
A*-hakua reitin etsimiseen ja verkkoa, johon reittihakua käytetään. Lisäksi kaikki A*-haun vaatimat tietorakenteet.

# Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet
Ohjelmani etsii nopeimman reitin verkon kahden pisteen välillä. Verkon pisteiden välisillä reiteillä on omat pituusarvonsa. Käytän A*-hakua, koska se on minulle jotenkuten ennestään tuttu, ja painotettuja verkkoja, koska haku sopii niihin hyvin.	

# Mitä syötteitä ohjelma saa ja miten näitä käytetään
Ohjelma saa syötteeksi verkon ja kaksi sen pistettä, joiden välinen reitti tullaan etsimään. Tulosteena ohjelma antaa nopeimman reitin verkon pisteiden välillä ja tämän reitin painoarvon.

# Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)
Tavoitteenani on A*-haulle määritelty aikavaativuus O(|E|) = O(b^d) ja tilavaativuus O(|V|) = O(b^d).

# Lähteet
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
Vanhat JohTek-kurssin materiaalit