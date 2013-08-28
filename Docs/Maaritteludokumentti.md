Aiheenmäärittely
================

Tämän projektin tarkoituksena on toteuttaa laskin joka pystyy käsittelemään ja
laskemaan (joitakin) kaavoja.
* * *

Tarkoitan tässä kaavoilla merkkijonoja, jotka koostuvat operaattoreista,
operandeista sekä näiden tulkintajärjestystä ohjaavista sulkumerkeistä.

Ohjelma pystyy muuntamaan tavanomaisella matemaattisella notaatiolla annetut
kaavat käänteiseen puolalaiseen notaatioon (RPN). Tarkoituksenani on toteuttaa
tämä toiminnallisuus implementoimalla Edsger Dijkstran keksimä Shunting yard
-algoritmi [1].

Lisäksi ohjelma pystyy laskemaan RPN-kaavan arvon. Käyttäjä pystyy suoraan
antamaan tällaisen kaavan tai se voidaan tuottaa edellä mainitulla algoritmilla.
Ensi vaiheen tavoitteenani on toteuttaa aritmetiikan neljän peruslaskutoimituksen
suorittamiseen liittyvä toiminnallisuus luonnollisilla luvuilla. Jos aikaa jää,
saatan toteuttaa laskimeen muitakin ominaisuuksia.

Shunting yard -algoritmin aika- ja tilavaativuukdet ovat luokkaa O(n), sillä
jokainen operaattori sekä operandi luetaan kerran ja siitä säilytetään yhtä
kopiota yhdessä aputietorakenteessa (sekä paluuarvona toimivassa taulukossa).
Siispä aika- ja tilavaativuudet riippuvat täysin syötteen pituudesta.

Shunting yard -algoritmin toteuttamiseen tarvittavat tietorakenteet ovat pino ja
jono. Aion toteuttaa nämä tietorakenteet linkitettyinä, jolloin niiden lisäys- ja
poisto-operaatioiden pitäisi olla aina vakioaikaisia. Näiden tietorakenteiden
tilavaativuus on lineaarisesti suoraan verrannollinen niihin talletettujen
alkioiden määrään. Toisin sanoen pyrin siihen, että käyttämieni pino- ja
jonotietorakenteiden edellä mainittujen operaatioiden aikafunktion asymptoottinen
yläraja on luokkaa O(1) ja tilafunktion O(n).

Lisäksi ohjelma pystyy käsittelemään UNIX grep -tyylisiä säännöllisiä lausekkeita
käyttäen äärellistä (epädeterminististä) automaattia joka käy läpi tiloja
leveyssuuntaisesti [2]. Parhaassa tapauksessa tällaisen läpikäynnin aikavaativuus
lienee luokkaa O(n). Esimerkiksi jos säännöllinen kieli on muotoa a+ koska
jokaisella tilalla voi tällöin vain yksi mahdollinen tilasiirtymä ja viimeinen
tilasiirtymä on silmukka. Tällöin silmukan suorituskerrat riippuvat täysin 
syötteenä olevasta merkkijonosta.

Lähteet:
1.	Shunting yard -algoritmi: http://en.wikipedia.org/wiki/Shunting-yard_algorithm
2.	Russ Cox: Regular Expression Matching Can Be Simple And Fast, Tammikuu 2007
	http://swtch.com/~rsc/regexp/regexp1.html
