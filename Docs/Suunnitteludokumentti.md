Toteutusdokumetti

Ohjelmointikielenä JavaScript ja sen kirjastot.

-jQuery ( jquery.com/ )
-HTML 5 Node Diagram ( https://github.com/boundary/html5-node-diagram/ )


Ohjelman tiedostot

-d2.js (Controiller)
 Itse ohjelman varsinainen logiikka on rakennettu tähän  tiedostoon. Se sisältää funktiot Initialize, Search, Randomize, reconstructPath jne.

-diagram.css
 Ohjeman käyttämät tyylit

-jQuery
 jquery-2.1.0.min.js

-HTML 5 Node Diagram
 models.js
 diagram.js
 underscore-min.js

Ohjelma saa syötteenä staattisen nodes.json-tiedoston, jonka pohjalta se luo solmuverkon ja niiden väliset yhteydet. Osa yhteyksistä on yksisuuntaisia. Etäisyyden solmujen välillä koostuvat pikseleistämäärästä, joka jaetaan kymmenellä. Solmuja on mahdollista Drag-n-Dropata haluamaansa sijaintiin tai verkon sijainnin voi myös satunnaistaa Randomize-funktiolla.
Haku suoritetaan valitsemalla solmut klikkaamalla. Jokaisen haun jälkeen kenttä voidaan Initialisoida käyttämällä Initialize-funktiota. Haku tuottaa lyhimmän mahdollisen reitin kahden solmun välillä ja visualisoi tuloksen sekä verkkoon että tulosalueeseen.

Parannusehdotukset
-Json-tiedoston dynaaminen syöttö ja sen validointi
-Etäisyyksien määrittely Json-tiedostossa (on tehty, mutta ei mahdollista käyttää niitä pikselien sijaan)
-A*-haun lisäksi toinen hakualgoritmi
-Etäisyyksien lisäksi solmujen välien yhteyteen nopeusarvo
-Haun automaattinen re-run Drag-n-Dropin jälkeen.