<h1> Testausdokumentti Pacman </h1>

Ohjelmaa on testattu käyttäen JUnitia. Jokaisen luokan koodia on testattu erikseen, mahdollisimman kattavasti, omassa testiluokassaan. Pyrkimyksenä on ollut testata jokaista yksittäistä metodia. Kokoavien metodien testaus ei ole ollut kovin laajaa, koska pienten metodien testaus kattaa myös näiden kokoavien metodien testaamisen. Private metodeja ei ole testattu.

Pelin toimintaa on myös testattu pelaamalla peliä paljon, useampi koekäyttäjä on testannut pelin toimintaa.

Testit voidaan suorittaa normaalisti, kuten tavallinen ohjelma koodi, valitsee vain oikean testi tiedoston ajettavaksi. Testit sijaitsevat tiedostopolun /Tiralabra/test/Pacman/ takaa. Hakemistossa Pacman on hakemistot pakkausten mukaan ja näiden alta löytyy luokka kohtaiset testit.

<h2> Koodin empiirinen testaus </h2>

<h3>Suorituskykytestaus</h3>
Suorituskyky testauksessa on tarkasteltu kuinka kauan tärkeimpien osa-alueiden, kuten hakualgoritmin, järjestämisen ja pelin kokonaisvaltainen suorittaminen, kuluttaa aikaa.

<h4>Haamut</h4>
Ensimmäiseksi tarkasteltavana on hakualgoritmin toiminta eri haamujen liikuttamisessa. Haamuista Red, Magenta ja Cyan käyttävät liikkumiseensa hakualgoritmia.

<h4>Red</h4>
![alt text](red.png "Red")

Ensimmäinen kaavio kuvaa kuinka paljon haamua Red liikuttava metodi, joka käyttää hakualgoritmia AStar, kuluttaa aikaa satunnaisella pelin pelaamiskerralla. Red liikkuu siten, että se jahtaa Mania kohteenaan Man itse.

Kaaviosta pystyy havaitsemaan selkeää heittelyä, jotka käyvät ilmi pelin kulun lähemmässä tarkastelussa. Alussa oleva korkea piikki selittyy sillä, että tässä vaiheessa punaisen haamun etäisyys Manista on todennäköisesti suurimmillaan. 

Suurempi selkeä heilahdus metodin suorituskerralla 38 selittyy sillä, että haamu heikennetään. Haamun maali ruutu, joka on tähän asti ollut Man itse, muuttuu. Heikennetyn punaisen haamun maali on ruutu, joka pelialustan vastakkaisella puolella peilattuna pystyakselin suhteen. Aikaa kuluu enemmän siten siksi, että uusi ruutu sijaitsee kauempana. Kun haamut ovat heikennettyjä, Man liikkuu nopeammin, joten saa otettua paremmin etäisyyttä haamuihin.  Jo kohdassa 43 Red muuttuu takaisin vahvaksi, koska Man syö tämän. Haamu palaa alkuun, joten matka taas kohteeseen, eli Maniin, on sen verran pidempi, että aikaa kuluu liikuttamiseen. Tällainen noin 4ms kuluttava heilahtelu johtuu siitä, että haamu vaihtelee vahvasta heikoksi ja heikosta vahvaksi.

Suuri heilahdus kohdassa 127 johtuu siitä, että tässä vaiheessa haamu oli heikkona ja Man liikkui nopeasti kauas haamusta, joten seuraavan liikahduksen selvittämiseen meni huomattavasti aikaan.
Pääosin kuitenkin haamun liikuttamismetodi toimii nopeudella 0ms tai 1ms. Tämä johtuu siitä, että pääosan pelistä haamu kulkee vain muutaman ruudun Manin perässä, joten lyhin reitti haamun ruudusta Manin ruutuun ei kuluta juurikaan aikaa.


