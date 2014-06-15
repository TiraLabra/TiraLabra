#Testausdokumentti

Ohjelman testaus on jakautunut karkeasti kolmeen erilliseen näkökulmaan, yksikkötestaukseen AStarin testaukseen ja yleiseen suorituskykytestaukseen.

##Yksikkötestaus

Yksikkötestaus on hoidettu käytännössä kokonaan JUnit-testauksena. Tavoitteena on sataprosenttinen rivikattavuus ohjelman keskeisten toiminnallisten osien kohdalta. Tämän seurantaan projektissa on hyödynnetty Cobertura-raportointeja.

Aivan kaikkea ei ole testattu rivikattavuudenkaan osalta. Olen rajannut toissijaisia asioita testaamisen ulkopuolelle, kuten vaikkapa käyttöliittymäkoodi, tulostuksesta huolehtivat, tai muuten triviaalit asiat kurssin painopisteiden kannalta.

Luonnollisesti kaikki JUnit-testaus on automaattisesti toistettavassa muodossa koska tahansa.

##Hakualgoritmin oikean toiminnan testaus

Oma kokonaisuutensa on AStar-luokka, jonka kattava testaus JUnit-testeillä on haastavaa. Tässä olen automaattisten testien lisäksi suorittanut toistuvasti käsin testaamista, ja tulosten ja ohjelman etenemisen tutkimista mm. aputulosteiden ja debuggerin avulla, jotta olen voinut varmistua algoritmin oikeasta toiminnallisuudesta.

TODO: Tähän voisi liittää esimerkkikuvia ohjelman suorittamasta reitinratkaisusta (pienillä alueilla tehtynä)

##Suorituskykytestaus

Tulossa viimeisellä viikolla. Suunnitelmaa löytyy Suunnitteludokumentti.md-tiedostosta.
