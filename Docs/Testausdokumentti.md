Testaus
=======

Samoilla heuristiikoilla, hakusyvyyksillä ja algoritmeilla toteutettuna tekoälyt pelaavat aina tismalleen samanlaisen pelin, sillä niiden toteutuksessa ei ole satunnaisuutta. Toistuvat testit eivät siis ole erityisen mielekkäitä. Tekoälyn toimintaan olisi toki mahdollista lisätä satunnaisuutta tilanteisiin, joissa on useita yhtä hyviä siirtomahdollisuuksia. Tällöin tekoäly valitsee niistä aina ensimmäisen, mutta olisi toki mahdollista myös valita satunnaisesti joku kyseisistä siirroista.

Aluksi testasin tekoälyjen toimintaa toisiaan ja ihmispelaajaa vastaan. Laitoin ensin eri hakusyvyyksillä varustetut minimax-algoritmia käyttävät tekoälyt pelaamaan toisiaan vastaan ja sain seuraavanlaiset tulokset:
v= valkoinen voitti, m= musta voitti, s= siirtomäärä tuli täyteen

| Valkoisen syvyys Mustan syvyys-> | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|---|---|---|---|---|---|---|---|
| 1 | s | v | m | s | v | v | s |
| 2 | m | s | s | m | s | v | m |
| 3 | s | m | s | s | s | v | m |
| 4 | s | v | m | m | v | s | s |
| 5 | m | m | m | m | m | s | s |
| 6 | s | m | m | m | m | m | m |
| 7 | m | v | v | v | s | s | m | 


Nyt peli näyttäisi päättyvän usein pattitilanteeseen, jossa molemmat pelaajat liikuttavat kuninkaitaan edestakaisin. Jos toinen pelaajista tekisi jotain muuta, se todennäköisesti myös häviäisi pelin. Joten ei kannata tehdä mitään muuta. Heuristiikan lisäsäätäminen saattaisi auttaa hieman tähän ongelmaan.

Ohjelmassa on mahdollista valita hakusyvyydet 1-7 molemmille pelaajille erikseen. Suuremmat hakusyvyydet toimivat toki hitaammin kuin pienemmät, mutta mikään ei ole kohtuuttoman hidas. Suurempi hakusyvyys ei mkuitenkaan takaa voittoa pelissä. Musta pelaaja voitti vain 23,8% peleistä, joissa sillä oli suurempi hakusyvyys ja 47,6% tällaisista peleistä päättyi pattitilanteeseen. Valkoinen pelaaja taas voitti vain 19,0% peleistä, joissa sillä oli suurempi hakusyvyys ja 23,8% tällaisista peleistä päättyi pattitilanteeseen. Muokkasin heuristiikkaa näiden testien jälkeen useita kertoja, mutta en onnistunut saamaan siitä sellaista, että hakusyvyys vaikuttaisi merkittävästi pelin lopputulokseen. Ilmeisesti tammi on niin yksinkertainen peli, että heuristiikan pitäisi olla todella hyvä, jotta eri pelitilanteet olisi oikeasti mahdollista pisteyttää oikein.

Testasin tekoälyn toimintaa myös ihmispelaajaa eli itseäni vastaan. Voitin minimax-tekoälyn kaikilla hakusyvyyksillä. Se ei siis vielä ole kovinkaan hyvä ihmistä vastaan. Oma hakusyvyyteni on kuitenkin suhteellisen pieni. En miettinyt siirtoja kuin yhden tai kahden siirron päähän. Tekoäly teki peleissä joitakin erittäin hyviä siirtoja, mutta useimmissa tapauksissa sen siirrot johtivat vain siihen että sain sen nappuloita syötyä. Pelit päättyivät aina niin että minulla oli jäljellä huomattavasti enemmän nappuloita kuin vastustajalla. Yritin parantaa tekoälyn toimintaa kasvattamalla merkittävästi laudalla olevien nappuloiden arvoa, mutta sekään ei saanut tekoälyä pitämään parempaa huolta sen nappuloista.


Suorituskykytestaus
-------------------

Tein suorituskykytestausta tutkimalla kuinka kauan aikaa yhteen siirtoon menee kun molemmilla pelaajilla on käytössä sama algoritmi samalla hakusyvyydellä. Yhden siirron laskemiseen kulunut aika vaihtelee toki runsaasti siirrosta toiseen. Alussa siirtojen laskemisessa kestää kauemmin ja ne nopeutuvat loppua kohti, kun mahdollisten siirtojen määräkin vähenee.

Suorituskykytestauksen tulokset löytyvät tiedostosta suorituskykytestaus.pdf. Kaaviosta nähdään, että suuremmat hakusyvyydet hidastavat algoritmin toimintaa selvästi. Hakusyvyys on merkitty sulkuihin käytetyn algoritmin nimen perään. Minimax-algoritmi ilman alfa-beta -karsintaa alkaa olla erittäin hidas hakusyvyydellä kuusi. Hakusyvyydellä seitsemän algoritmi on jo lähes käyttökelvoton. Kaaviosta näkee myös että minimax-algoritmi alfa-beta -karsinnalla on merkittävästi nopeampi kuin algoritmi ilman karsintaa. Karsinnan kanssa hakusyvyys seitsemänkin on vielä täysin käyttökelpoinen. Kokeilin myös hakusyvyyksiä kahdeksan ja yhdeksän, mutta ne olivat jo turhan hitaita.

EkaAIlla menee jokaiseen siirtoon aikaa vain 0-1 ms eli tämä on se aika joka menee siirron toteuttamiseen kun toteutettavaa siirtoa ei tarvitse sen kummemmin selvittää. Minimax-algoritmilla taas eri siirtoihin kuluva aika vaihtelee hyvinkin paljon. Tämän takia tein vielä erikseen kaavion, jossa on vertailtu ensimmäisten siirtojen selvittämiseen kuluvaa aikaa. Tästä näkee selvästi kuinka käytetty aika kasvaa hakusyvyyden kasvaessa ja kuinka pelkkä minimax-algoritmi muuttuu jo lähes käyttökelvottomaksi hakusyvyydellä 6.

Siirron selvittäminen myös nopeutuu selvästi kun siirreltäviä nappuloita ja siten mahdollisia siirtoja on vähemmän. Useissa peleissä näytti käyvän niin, että toiselle pelaajalle jää vain yksi nappula jäljelle, jolloin tämä pelaaja saa siirtonsa tehtyä erittäin nopeasti. Toisella pelaajalla on sitävastoin jäljellä useita nappuloita, joiden kaikki mahdolliset siirtovaihtoehdot on tutkittava. Tällä pelaajalla kestää sitten siirron tekemisessä paljon kauemmin.

