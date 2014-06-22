#Testausdokumentti

Ohjelman testaus on jakautunut karkeasti kolmeen erilliseen näkökulmaan, yksikkötestaukseen AStarin testaukseen ja yleiseen suorituskykytestaukseen.

##Yksikkötestaus

Yksikkötestaus on hoidettu käytännössä kokonaan JUnit-testauksena. Tavoitteena on lähellä sataa prosenttia oleva rivikattavuus ohjelman keskeisten toiminnallisten osien kohdalta. Tämän seurantaan projektissa on hyödynnetty Cobertura-raportointeja. Valtaosassa luokista kattavuus onkin Coberturan mukaan 100 prosenttia.

Yksikkötestauksen ulkopuolelle tietoisesti jätetyt luokat ovat Kuvanayttaja, Heuristiikka-rajapinta, Main, TekstiUI ja suorituskykytestausluokat.
Nämä on jätetty testaamatta sillä perusteella, että ne ovat joko triviaaleja tapauksia (kuten rajapinta), hankalia testattavaksi (kuten Kuvanäyttäjän JFramen näyttäminen) tai käyttöliittymäelementtiä, joka ei ole kurssin keskeisen aihealueen ja painopisteiden kannalta olennaista.

Luonnollisesti kaikki JUnit-testaus on automaattisesti toistettavassa muodossa koska tahansa.

##Hakualgoritmin oikean toiminnan testaus

Oma kokonaisuutensa on AStar-luokka, jonka kattava testaus JUnit-testeillä on haastavaa. Tässä olen automaattisten testien lisäksi suorittanut toistuvasti käsin testaamista, ja tulosten ja ohjelman etenemisen tutkimista mm. aputulosteiden ja debuggerin avulla, jotta olen voinut varmistua algoritmin oikeasta toiminnallisuudesta.

TODO: Tähän voisi liittää esimerkkikuvia ohjelman suorittamasta reitinratkaisusta (pienillä alueilla tehtynä)

##Suorituskykytestaus

Tulossa viimeisellä viikolla. Suunnitelmaa löytyy Suunnitteludokumentti.md-tiedostosta.
