#Testausdokumentti

Ohjelman testaus on jakautunut karkeasti kolmeen erilliseen näkökulmaan, yksikkötestaukseen AStarin testaukseen ja yleiseen suorituskykytestaukseen.

##Yksikkötestaus

Yksikkötestaus on hoidettu käytännössä kokonaan JUnit-testauksena. Tavoitteena on lähellä sataa prosenttia oleva rivikattavuus ohjelman keskeisten toiminnallisten osien kohdalta. Tämän seurantaan projektissa on hyödynnetty Cobertura-raportointeja. Valtaosassa luokista kattavuus onkin Coberturan mukaan 100 prosenttia.

Yksikkötestauksen ulkopuolelle tietoisesti jätetyt luokat ovat Kuvanayttaja, Heuristiikka-rajapinta, Main, TekstiUI ja suorituskykytestausluokat.
Nämä on jätetty testaamatta sillä perusteella, että ne ovat joko triviaaleja tapauksia (kuten rajapinta), hankalia testattavaksi (kuten Kuvanäyttäjän JFramen näyttäminen) tai käyttöliittymäelementtiä, joka ei ole kurssin keskeisen aihealueen ja painopisteiden kannalta olennaista.

Luonnollisesti kaikki JUnit-testaus on automaattisesti toistettavassa muodossa koska tahansa.

##Hakualgoritmin oikean toiminnan testaus

Oma kokonaisuutensa on AStar-luokka, jonka kattava testaus JUnit-testeillä on haastavaa. Tässä olen automaattisten testien lisäksi suorittanut toistuvasti käsin testaamista, ja tulosten ja ohjelman etenemisen tutkimista mm. aputulosteiden ja debuggerin avulla, jotta olen voinut varmistua algoritmin oikeasta toiminnallisuudesta.

Esimerkkinä tästä käsin tehdystä tarkastelusta eräs käyttämäni esimerkkiratkaisu.

Alue:

    0000000000000000
    000000000█000000
    500000000█000000
    000000000█000000
    000000000█000000
    000000000█000000
    05█3███████████0
    0000000000█0█000
    00000█0000███000
    00000█0000000000
    00000█0000000000
    00000█0000000000
    00000█0000000000
    0000000000000000
    0000000000000000
    0000000000000000

Reitti:

    -000000000000000
    0-0000000█000000
    5-0000000█000000
    0-0000000█000000
    0-0000000█000000
    0-0000000█000000
    -5█3███████████0
    0-00000000█0█000
    00-00█0000███000
    000-0█0000000000
    0000-█0000000000
    0000-█0000000000
    0000-█0000000000
    00000-0000000000
    000000-000000000
    0000000--------0


##Suorituskykytestaus

Suorituskykytesta



#### ArrayListOma

Labtool-palautteessa toivottiin jotain suorituskykytestausta ArrayListOma-luokalle. Testasin tätä hyvin yksinkertaisella int-lukujen lisäämisellä. Mittauksista ei ole yhtä tarkkaa tallennettua dataa kuin AStar-mittauksista, mutta yhteenvetona seuraava. Kestomittaukset ovat jälleen viiden kerran keskiarvoja.

alkukoko 10, määrä 10 000:       8ms       
alkukoko 1000, määrä 10 000:     7ms

alkukoko 10, määrä 1 000 000:    kesto 29ms
alkukoko 1000, määrä 1 000 000:  kesto 26ms

alkukoko 10, määrä 10 000 000:   kesto 1646ms
alkukoko 1000, määrä 10 000 000: kesto 2409ms

Edellisten tulkitsemisena voisi todeta, että pienemmillä syötemäärillä suuri alkukoko suoriutuu hieman nopeammin tehtävästä. Joskin täytyy muistaa, että näin pienillä otoskoilla erot voivat johtua osaltaan myös sattumasta.

Mielenkiintoinen ero tuli 10 miljoonan yksilön lisäyksessä. Siinä suuri alkukoko ilmeisesti aiheuttaa sen, että jossain vaiheessa joudutaan tekemään huonossa kohtaa turhan iso taulukon kopiointi, mistä aiheutuu ylikuormaa verrattuna pienempään. Tästä siis huomaa, että kun ei voida ennalta tietää syötteen kokoa, on hieman sattumasta kiinni, millainen taulukon alkukoko tuottaa tehokkaimpaa lopputulosta.

