Testaus
=======

Samoilla heuristiikoilla, hakusyvyyksillä ja algoritmeilla toteutettuna tekoälyt pelaavat aina tismalleen samanlaisen pelin, sillä niiden toteutuksessa ei ole satunnaisuutta. Toistuvat testit eivät siis ainakaan vielä ole mielekkäitä. Saattaa olla että lisään tekoälyn toimintaan vielä satunnaisuutta esimerkiksi siten että useista yhtä hyvistä siirroista ei valitakaan ensimmäistä vaan arvotaan joku. Luulisin että tällä olisi merkitystä lähinnä pelin alussa, jolloin pelissä on todella useita samanarvoisia siirtoja. Myöhemmin eri siirtojen arvot taitavat lähes aina poiketa toisistaan. Tällöinkin voisi ehkä arpoa jonkin siirron esimerkiksi muutaman parhaan siirron joukosta, jos haluaisi satunnaisuutta.

Aluksi testasin ohjelman toimintaa ilman satunnaisuutta. Laitoin eri hakusyvyyksillä varustetut minimax-algoritmia käyttävät tekoälyt pelaamaan toisiaan vastaan ja sain seuraavanlaiset tulokset:
v= valkoinen voitti, m= musta voitti, s= siirtomäärä tuli täyteen

| Valkoisen syvyys Mustan syvyys-> | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|---|---|---|---|---|---|---|---|
| 1 | v | v | s | m | s | v | s |
| 2 | v | m | s | m | m | m | m |
| 3 | v | s | m | s | m | m | s |
| 4 | s | v | m | s | v | v | v |
| 5 | s | v | v | s | m | v | v |
| 6 | v | v | v | s | v | s | v |
| 7 | m | s | s | s | s | s | s |


Nyt peli näyttäisi päättyvän usein pattitilanteeseen, jossa molemmat pelaajat liikuttavat kuninkaitaan edestakaisin. Jos toinen pelaajista tekisi jotain muuta, se todennäköisesti myös häviäisi pelin. Joten ei kannata tehdä mitään muuta. Heuristiikan säätäminen saattaisi auttaa hieman tähän ongelmaan.

Muuten tulokset näyttäisivät melko ennalta odotettujen mukaisilta. Suurempi hakusyvyys on avain voittoon. Muilla hakusyvyyksillä tekoäly toimii riittävän ripeästi, mutta 7 alkaa olla jo vähän hidas. Tällä hetkellä ohjelmassa voi valita hakusyvyydet 1-7, mutta suuremmat syvyydetkin varmaan olisivat mahdollisia. Toki algoritmin toiminta nopeutuu pelin loppua kohti, kun nappuloiden määrä laudalla pienenee. Ehkä algoritmi voisikin kasvattaa hakusyvyyttä pelin edetessä.


Suorituskykytestaus
-------------------

Yhden siirron laskemiseen kulunut aika vaihtelee toki runsaasti siirrosta toiseen. Alussa siirtojen laskemisessa kestää kauemmin ja ne nopeutuvat loppua kohti, kun mahdollisten siirtojen määräkin vähenee.

EkaAI:lla, joka valitsee aina mahdollisten siirtojen listasta ensimmäisen siirron, menee siirron toteuttamiseen 0-1 ms.

Minimax-algoritmia käyttävän tekoälyn nopeutta testasin siten että molemmilla pelaajilla oli sama hakusyvyys. Kun hakusyvyys oli yksi siirtoihin käytetyt ajat näyttivät seuraavilta: 9ms 3ms 4ms 3ms 1ms 2ms 4ms 7ms 2ms 5ms 0ms 1ms 0ms 4ms 0ms 4ms 4ms 3ms 1ms 0ms 6ms 1ms 4ms 0ms 3ms 1ms 0ms 0ms 3ms 

Hakusyvyydellä kaksi: 20ms 9ms 10ms 1ms 3ms 5ms 1ms 2ms 3ms 1ms 0ms 5ms 1ms 0ms 2ms 4ms 3ms 4ms 1ms 4ms 2ms 0ms 1ms 0ms 1ms 1ms 3ms 13ms 1ms 3ms 2ms 9ms 1ms 1ms 0ms 5ms 0ms 1ms 0ms 3ms 0ms 5ms 0ms 4ms 0ms 4ms 0ms 2ms

Hakusyvyydellä kolme: 55ms 16ms 7ms 1ms 7ms 4ms 16ms 3ms 3ms 10ms 3ms 3ms 3ms 2ms 13ms 29ms 14ms 18ms 0ms 1ms 6ms 8ms 14ms 2ms 1ms 6ms 6ms 2ms 1ms 2ms 2ms 1ms 9ms 3ms 4ms 9ms 1ms 8ms 0ms 4ms 2ms 2ms 2ms 0ms 1ms 1ms 1ms 0ms 1ms 18ms 3ms 15ms 1ms 22ms 0ms 12ms 0ms 6ms 0ms 4ms 0ms 6ms 0ms 6ms 0ms 5ms 0ms 5ms 0ms 4ms 1ms 5ms 0ms 3ms 

Hakusyvyydellä neljä: 69ms 38ms 57ms 11ms 7ms 7ms 20ms 3ms 22ms 25ms 6ms 14ms 1ms 6ms 3ms 6ms 14ms 13ms 2ms 3ms 2ms 18ms 2ms 3ms 8ms 9ms 2ms 13ms 1ms 10ms 9ms 3ms 1ms 2ms 6ms 1ms 1ms 2ms 1ms 0ms 26ms 0ms 4ms 0ms 27ms 0ms 22ms 1ms 9ms 0ms 21ms 0ms 13ms 0ms 18ms 0ms 13ms 0ms 13ms 1ms 8ms 1ms 17ms 0ms 10ms 0ms 8ms 0ms 8ms 0ms 8ms 0ms 8ms 0ms 7ms 0ms 8ms 0ms 8ms 0ms 7ms 0ms 8ms 0ms 8ms 0ms 7ms 1ms 17ms 0ms 9ms 0ms 8ms 0ms 8ms 0ms 8ms 0ms 7ms 0ms 11ms

Hakusyvyydellä viisi: 164ms 74ms 111ms 11ms 35ms 51ms 92ms 13ms 17ms 58ms 11ms 30ms 81ms 122ms 9ms 18ms 54ms 44ms 12ms 3ms 37ms 4ms 7ms 53ms 8ms 3ms 17ms 50ms 17ms 43ms 3ms 25ms 9ms 63ms 8ms 15ms 7ms 2ms 1ms 7ms 1ms 6ms 1ms 4ms 1ms 56ms 0ms 9ms 0ms 17ms 0ms 39ms 0ms 31ms 0ms 38ms 1ms 93ms 0ms 8ms 0ms 1ms 0ms 2ms

Hakusyvyydellä kuusi: 293ms 213ms 290ms 45ms 107ms 197ms 353ms 42ms 59ms 161ms 170ms 53ms 44ms 117ms 36ms 37ms 95ms 290ms 9ms 65ms 19ms 272ms 30ms 30ms 15ms 79ms 19ms 25ms 27ms 191ms 40ms 397ms 37ms 99ms 11ms 85ms 2ms 12ms 7ms 9ms 65ms 8ms 29ms 20ms 18ms 34ms 8ms 5ms 3ms 12ms 2ms 85ms 1ms 112ms 2ms 182ms 1ms 95ms 1ms 131ms 1ms 79ms 1ms 81ms 1ms 66ms 1ms 61ms 1ms 67ms 1ms 70ms 1ms 78ms 1ms 72ms 2ms 78ms 0ms 71ms 1ms 79ms 1ms 70ms 1ms 68ms 1ms 60ms 1ms 67ms 1ms 60ms 1ms 66ms 1ms 60ms 1ms 67ms 0ms 61ms 2ms

Hakusyvyydellä seitsemän: 869ms 580ms 1061ms 168ms 114ms 385ms 186ms 305ms 107ms 3677ms 609ms 1866ms 718ms 178ms 13ms 881ms 77ms 323ms 257ms 566ms 133ms 4922ms 74ms 2283ms 48ms 175ms 1166ms 101ms 161ms 391ms 20ms 1923ms 34ms 2526ms 18ms 1537ms 14ms 937ms 2ms 595ms 27ms 44ms 12ms 100ms 1ms 318ms 0ms 118ms 1ms 491ms 1ms 566ms 1ms 436ms 3ms 283ms 3ms 315ms 2ms 459ms 1ms 270ms 1ms 218ms 1ms 91ms 1ms 237ms 2ms 151ms 1ms 359ms 1ms 155ms 1ms 383ms 2ms 160ms 1ms 381ms 1ms 162ms 1ms 365ms 2ms 154ms 1ms 380ms 1ms 158ms 1ms 359ms 2ms 151ms 1ms 372ms 1ms 162ms 2ms 386ms 4ms

Piirrän noista myöhemmin jonkun graafin.

Suuremmilla hakusyvyyksillä seuraavan siirron suorittamiseen kuluva aika on selvästi pidempi. Siirron selvittäminen myös nopeutuu selvästi kun siirreltäviä nappuloita ja siten mahdollisia siirtoja on vähemmän. Useissa peleissä näytti käyvän niin, että toiselle pelaajalle jää vain yksi nappula jäljelle, jolloin tämä pelaaja saa siirtonsa tehtyä erittäin nopeasti. Toisella pelaajalla on sitävastoin jäljellä useita nappuloita, joiden kaikki mahdolliset siirtovaihtoehdot on tutkittava.

Tällähetkellä suurin mahdollinen hakusyvyys seitsemän on jo merkittävästi hitaampi kuin pienemmät hakusyvyydet. Se ei kuitenkaan välttämättä ole vielä häiritsevän hidas, joten voisin kokeilla vielä yhden suurempaakin hakusyvyyttä.

