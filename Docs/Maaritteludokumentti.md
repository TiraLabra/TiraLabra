Aiheenmäärittely
================

Tämän projektin tarkoituksena on toteuttaa laskin joka pystyy käsittelemään kaavoja.
------------------------------------------------------------------------------------

Tarkoitan tässä kaavoilla merkkijonoja, jotka koostuvat operaattoreista, operandeista sekä näiden tulkintajärjestystä
ohjaavista sulkumerkeistä.

Tarkoituksenani on toteuttaa tämä toiminnallisuus implementoimalla Edsger Dijkstran keksimä Shunting yard -algoritmi.
Ensi vaiheen tavoitteenani on edellämainitun lisäksi myös toteuttaa aritmetiikan neljän peruslaskutoimituksen
suorittamiseen liittyvä toiminnallisuus luonnollisilla luvuilla käyttäen käänteistä puolalaista notaatiota johon lause on
saatettu edellämainitulla algoritmilla tai mahdollisesti suoraan käyttäjän toimesta. Jos aikaa jää, saatan toteuttaa
laskimeen muitakin ominaisuuksia.

Shunting yard -algoritmin toteuttamiseen tarvittavat tietorakenteet ovat pino ja jono. Aion toteuttaa nämä tietorakenteet
linkitettyinä, jolloin niiden lisäys- ja poisto-operaatioiden pitäisi olla aina vakioaikaisia. Näiden tietorakenteiden
tilavaativuus on lineaarisesti suoraan verrannollinen niihin talletettujen alkioiden määrään. Toisin sanoen pyrin siihen,
että käyttämieni pino- ja jonotietorakenteiden aikafunktion asymptoottinen yläraja on luokkaa O(1) ja tilafunktion O(n).

Lähteet:
http://en.wikipedia.org/wiki/Shunting-yard_algorithm
