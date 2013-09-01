Aiheenmäärittely
==============

Tämän projektin tarkoituksena on toteuttaa laskin joka pystyy käsittelemään ja
laskemaan (joitakin) kaavoja.
* * *

Tarkoitan tässä kaavoilla merkkijonoja, jotka koostuvat operaattoreista,
operandeista sekä näiden tulkintajärjestystä ohjaavista sulkumerkeistä.

Ohjelma pystyy muuntamaan tavanomaisella inifiksinotaatiolla annetut kaavat
käänteiseen puolalaiseen notaatioon (RPN). Tarkoituksenani on toteuttaa tämä
toiminnallisuus implementoimalla Edsger Dijkstran keksimä Shunting yard
-algoritmi [1]. Lisäksi tulkkiluokat osaavat käsitellä joitain lyhenteiksi
kutsumiani metakaavoja jotka muutetaan toisiksi kaavoiksi RPN-muotoon.

Ohjelma pystyy laskemaan aritmeettisen RPN-kaavan arvon. Käyttäjä pystyy suoraan
antamaan tällaisen kaavan tai se voidaan tuottaa edellä mainitulla algoritmilla.
Aritmeettinen laskin osaa laskea neljä peruslaskutoimitusta kohtalaisen
suuruisilla luonollisilla luvuilla. Tarkoitan tällä sitä, että laskutoimituksen
operandien sekä tuloksen pitää mahtua Javan int-tyyppiseen muuttujaan.

Shunting yard -algoritmin aika- ja tilavaativuukdet ovat luokkaa O(n), sillä
jokainen operaattori sekä operandi luetaan kerran ja siitä säilytetään yhtä
kopiota yhdessä aputietorakenteessa. Siispä aika- ja tilavaativuudet riippuvat
täysin lineaarisesti syötteen pituudesta.

Shunting yard -algoritmin toteuttamiseen tarvittavat tietorakenteet ovat pino ja
jono. Aion toteuttaa nämä tietorakenteet linkitettyinä, jolloin niiden lisäys-
ja poisto-operaatioiden pitäisi olla aina vakioaikaisia. Näiden tietorakenteiden
tilavaativuus on lineaarisesti suoraan verrannollinen niihin talletettujen
alkioiden määrään. Toisin sanoen pyrin siihen, että käyttämieni pino- ja
jonotietorakenteiden edellä mainittujen operaatioiden aikafunktion
asymptoottinen yläraja on luokkaa O(1) ja tilafunktion O(n).

Lisäksi ohjelma pystyy käsittelemään UNIX grep -tyylisiä säännöllisiä
lausekkeita [2] käyttäen äärellistä (epädeterminististä) automaattia [3], joka
käy läpi tiloja ahneella leveyssuuntaisella haulla. Tarkoitan ahneella tässä
sitä että automaatti pyrkii käymään syötteen läpi mahdollisimman nopeasti
käyttämällä tyhjän merkin tilasiirymiä vain jos muuta mahdollisuutta ei ole.
Automaatti rakennetaan itse kehittämälläni (keskeneräiseksi jääneellä)
algoritmilla joskin olen jossain määrin saanut apua Barry Brownin aihetta
käsittelevästä Youtube-videosta [4]. Lisäksi Algoritmi saattaa muistuttaa
Thompsonin NFA-algoritmia jota on Russ Cox esittelee kirjoituksessaan [3]. Joka
tapauksessa päädyin oman algoritmin suunnitteluun sillä en onnistunut löytämään
mistään tarpeeksi helppotajuisia ja käytännönläheisiä esimerkkiratkaisuja.

Parhaassa tapauksessa automaatin tilojen läpikäynnin aikavaativuus on luokkaa
O(n). Esimerkiksi jos säännöllinen kieli on muotoa a+, jokaisella tilalla voi
olla vain yksi mahdollinen tilasiirtymä ja viimeinen tilasiirtymä on silmukka.
Tällöin silmukan suorituskerrat riippuvat täysin syötteenä olevan merkkijononn
pituudsta. Pahimmassa tapauksessa automaatin jokainen tila haarautuisi ja
hakuoperaatio joutuisi käymään kaikki automaatin tilat läpi jolloin operaation
kesto määräytyisi tilojen määrän perusteella. En kuitenkaan osaa keksiä
esimerkkiä tällaisesta tapauksesta ja pidän sellaisen aktualisoitumista oikeilla
syötteillä varsin epätodennäköisenä. Tavanomaisilla syötteillä hakuoperaation
kesto saattaisi olla ehkä polynomiaalinen.

* * *

Lähteet:
-------

1.	Shunting yard -algoritmi:
	http://en.wikipedia.org/wiki/Shunting-yard_algorithm
2.	Russ Cox: Regular Expression Matching Can Be Simple And Fast, Tammikuu 2007:
	http://swtch.com/~rsc/regexp/regexp1.html
3.	Epädeterministinen äärellinen automaatti:
	http://en.wikipedia.org/wiki/Nondeterministic_finite_automaton
4.	Barry Brown: Regular Expression to NFA:
	http://www.youtube.com/watch?v=RYNN-tb9WxI
