##Toteutusdokumentaatio

###Ohjelman yleisrakenne
Ohjelma sisältää luokat Solmu, PunMusPuu, SplayPuu, BinaariHakupuu, suoritusKyky, AVLHakuPuu sekä rajapinnan PuuRajapinta.
Rajapinnassa määritellään jokaiselle puulle ominaiset operaatiot: lisää, poista, hae.

Binäärihakupuu on toteutettu pohjaluokkana, josta muut luokat perivät tarvittavia
metodeja sekä laajentavat mielensä mukaan. Puut koostuvat solmuista, jossa määritellään sen arvo, vanhempi sekä oikea että vasen. 

Punamustapuun toteutus vaati nil-solmun, jonka takia binäärihakupuun metodit eivät toimineet. Tämän takia punamustapuu ei peri binäärihakupuuta, vaan on oma toteutuksensa.

Suorituskyky luokka suorittaa CLIn jossa voi asettaa erilaisia suorituskykyä
mittaavia testejä

###Saavutetut aika- ja tilavaativuudet
Punamustapuun sekä AVL-hakupuiden keskiarvoiset aikavaativuudet ovat O(log n) ja tilavaativuus O(n) puun korkeuden suhteen. Kun vertailee binääripuun O(n) aikaa
vaikka lisäämässä 1000000 alkiota on huomattavissa on että punamustapuun sekä avl-
hakupuun ajankäyttö on huomattavasti pienempi kuin O(n). 

###Suorituskyky- ja O-analyysivertailu
Binäärihakupuu on hitain suorissa listoissa. Jos kyseessä on satunnaisten lukujen lisäys, saattaa binäärihakupuu olla nopein, sillä kiertoalgoritmit vievät aikansa. Yleensä kuitenkin punamustapuu sekä avl-hakupuu ovat nopeimmat puut.

###Työn puutteet ja parannusehdotukset
Puun tuloksista voisi tehdä graafisesti hienomman.

###Lähteet
Wikipedia
Tira-pruju
http://www.pp.htv.fi/uvaisane/yo/tira/