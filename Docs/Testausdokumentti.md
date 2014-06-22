Kaikki JUnit testit testipakkauksissa. Suorituskykytestit ovat ajettavia main-luokkia, jotka löytyvät tiralabra.performance hakemistossa. Suorituskykytestien ajaminen: 
''' 
java -cp Tiralabra_maven tiralabra.performance.testiluokka
'''
game.ai
=======
* Mutaatiotestiprosentti: 28%
* Testattu että tekoäly ei aiheuta virheitä ohjelmassa esim, jättämällä haun aikana tekemiään siirtoja pöydälle. 
* Siirtojen alfa- ja beta -arvojen laskeminen testattu debuggauksella, eikä siinä pitäisi olla suurempia virheitä. JUnit testit hankalia, sillä objektiivisesti paras siirto on usein mahdoton selvittää.
* Lisäksi tehokkuustestit AIPerformance -luokassa. Tulos viiden pelin testauksesta:
 <img src="performancetests.gif"> 
* Musta ja valkoinen pelaaja voittavat suurinpiirtein yhtä paljon ensimmäisten kahden vuoron ollessa satunnaisia, joten tekoäly ei suosi kumpaakaan pelaajaa.

game
====
* Mutaatiotestiprosentti: 91%
* Testattu, että nappuloiden asettaminen ja kääntäminen toimii moitteetta kuhunkin suuntaan.
* Testattu, että siirtojen ja käyttämättömien siirtojen (pass) peruminen toimii moitteetta.
* Testattu, että BigInteger-avain laudalle lasketaan oikein.

utilities
=========
* Mutaatiotestiprosentti: 68%
* Testattu perustoimintojen virheettömyys kattavasti.
* Testattu ArrayListin operaatioiden ja järjestämisen tehokkuus. Miljoonalla satunnaisella luvulla järjestämiseen menee keskimäärin sekunti.
* ZobristHashista testattu, että sama pelilauta saa aina saman BigInteger -avaimen.

app/app.ui
==========
* Ei testattu, mutta toistuva ajaminen osoittaa toimiviksi. Luokkien tehtävä on yksinkertainen eikä niitä ole tarkoitusta missään vaiheessa laajentaa, vaan pikemminkin ehkä korvata kunnollisella käyttöliittymällä, joten testejä ei tehty.
