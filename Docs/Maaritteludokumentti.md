# Määrittelydokumentti

Tässä harjoitustyössä on tarkoituksena toteuttaa joukkoliikenteen reittiopas pääkaupunkiseudulle. Tähän käytetään HSL:n [XML-muodossa tarjoamaa aikatauludataa](http://developer.reittiopas.fi/pages/en/kalkati.net-xml-database-dump.php).

Sovellus osaa etsiä parhaan reitin kahden pisteen välille käyttäen joukkoliikennevälineitä.

## Käytettävät algoritmit
Reitin hakuun käytetään [A*-algoritmia](http://en.wikipedia.org/wiki/A*_search_algorithm). 
Verkon solmuina toimivat pysäkit ja kaarina joukkoliikenteen linjat.
Reitin haun tulisi tällä algoritmilla toimia ajassa O((|E| + |V|) log (|V|)). Haku toteutetaan ensin hakemaan lyhin mahdollinen reitti pisteiden välille. Reitin pituutena käytetään tässä pysäkkien välistä etäisyyttä linnuntietä pitkin.
Tämän jälkeen sitä parannellaan ottamaan huomioon aikataulut ja huomioimaan odotusaika pysäkeillä, joka tietysti pyritään saamaan mahdollisimman pieneksi.

Pysäkkien ja linjojen käsittelyyn tarvitaan avuksi myös erilaisia puumaisia tietorakenteita.

## Muuta
Ohjelma lukee XML-tiedoston käynnisttettäessä tietorakenteisiin. Reitti haetaan käyttäen pysäkkien nimiä ja mahdollisesti myös pysäkin koordinaatteja.
Sovellukseen toteutetaan myös jonkinlainen visualisointi löydetylle reitille.
