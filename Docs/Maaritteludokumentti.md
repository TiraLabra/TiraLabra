Aiheenmäärittely
================

Tämän projektin tarkoituksena on toteuttaa laskin joka pystyy käsittelemään ja laskemaan (joitakin) kaavoja.
* * *

Tarkoitan tässä kaavoilla merkkijonoja, jotka koostuvat operaattoreista, operandeista sekä näiden tulkintajärjestystä
ohjaavista sulkumerkeistä.

Ohjelma pystyy muuntamaan tavanomaisella matemaattisella notaatiolla annetut kaavat käänteiseen puolalaiseen notaatioon
(RPN). Tarkoituksenani on toteuttaa tämä toiminnallisuus implementoimalla Edsger Dijkstran keksimä Shunting yard
-algoritmi.

Lisäksi ohjelma pystyy laskemaan RPN-kaavan arvon. Käyttäjä pystyy suoraan antamaan tällaisen kaavan tai se voidaan
tuottaa edellä mainitulla algoritmilla. Ensi vaiheen tavoitteenani on toteuttaa aritmetiikan neljän peruslaskutoimituksen
suorittamiseen liittyvä toiminnallisuus luonnollisilla luvuilla. Jos aikaa jää, saatan toteuttaa laskimeen muitakin
ominaisuuksia.

Shunting yard -algoritmin toteuttamiseen tarvittavat tietorakenteet ovat pino ja jono. Aion toteuttaa nämä tietorakenteet
linkitettyinä, jolloin niiden lisäys- ja poisto-operaatioiden pitäisi olla aina vakioaikaisia. Näiden tietorakenteiden
tilavaativuus on lineaarisesti suoraan verrannollinen niihin talletettujen alkioiden määrään. Toisin sanoen pyrin siihen,
että käyttämieni pino- ja jonotietorakenteiden edellä mainittujen operaatioiden aikafunktion asymptoottinen yläraja on
luokkaa O(1) ja tilafunktion O(n).

Lähteet:
http://en.wikipedia.org/wiki/Shunting-yard_algorithm
