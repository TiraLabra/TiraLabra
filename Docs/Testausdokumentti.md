#Testausdokumentti

Ohjelman testaus on jakautunut karkeasti kolmeen erilliseen näkökulmaan: yksikkötestaukseen, AStarin testaukseen ja yleiseen suorituskykytestaukseen.

##Yksikkötestaus

Yksikkötestaus on hoidettu käytännössä kokonaan JUnit-testauksena. Tavoitteena on lähellä sataa prosenttia oleva rivikattavuus ohjelman keskeisten toiminnallisten osien kohdalta. Tämän seurantaan projektissa on hyödynnetty Cobertura-raportointeja. Valtaosassa luokista kattavuus onkin Coberturan mukaan 100 prosenttia.

Yksikkötestauksen ulkopuolelle tietoisesti jätetyt luokat ovat Kuvanayttaja, Heuristiikka-rajapinta, Main, TekstiUI ja suorituskykytestausluokat. Nämä on jätetty testaamatta sillä perusteella, että ne ovat joko triviaaleja tapauksia (kuten rajapinta), hankalia testattavaksi (kuten Kuvanäyttäjän JFramen näyttäminen) tai käyttöliittymäelementtiä, joka ei ole kurssin keskeisen aihealueen ja painopisteiden kannalta olennaista.

Yksikkötestauksessa on pyritty tarkastelemaan yleisimpiä syötearvoja, joskaan kattavaa analyysiä eri syöteluokista ja mahdollisista raja-arvoista ei ole tehty. Myöskään ohjelmakoodia ei ole suunniteltu selviämään joka tilanteessa virheellisistä syötteistä, joten mielestäni valittu testaustyyli on tilanteeseen varsin sopiva.

Luonnollisesti kaikki JUnit-testaus on automaattisesti toistettavassa muodossa koska tahansa.

##Hakualgoritmin oikean toiminnan testaus

Oma kokonaisuutensa on AStar-luokka, jonka kattava testaus JUnit-testeillä on haastavaa. Tässä olen automaattisten testien lisäksi suorittanut toistuvasti käsin testaamista, ja tulosten ja ohjelman etenemisen tutkimista mm. aputulosteiden ja debuggerin avulla, jotta olen voinut varmistua algoritmin oikeasta toiminnallisuudesta.

Esimerkkinä tästä käsin tehdystä tarkastelusta eräs käyttämäni esimerkkiratkaisu. Tämä ei ole tosin ainoa, vaan käsin varioimalla yksityiskohtia olen tutkinut, että algoritmi varmasti toimisi loogisella tavalla.

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

#### AStar

AStarin suorituskykyä eri heuristiikoilla tarkasteltiin tekemällä muutamista eri hakualueista mittauksia. Jokaisella heuristiikalla suoritus ajettiin viisi kertaa, ja suoritusajoista laskettiin keskiarvo. Mukaan taulukoitiin myös kyseisellä heuristiikalla tapahtuneet laskenta-askeleet ja toteutuneen reitin pituus. Laskenta-askeleiksi siis lasketaan jokainen node, johon haku etenee.

Mittaustulokset ja niitä kuvaava graafi löytyy pfd:nä tästä dokumenttihakemistosta tiedostosta suorituskykymittaukset.pdf.

Päädyin laskemaan eri heuristiikkojen suhteellisia suoritusaikoja. Tässä lähtökohdaksi valittiin joka kohdassa hitain Dijkstra, johon muita verrataan. Dijkstran suoritusaika siis saa arvon 100%, ja muiden siihen verrattuja prosenttiarvoja, kussakin hakutilanteessa. 

Yhteensä testitilanteita tehtiin neljä. Kolme ensimmäistä samasta maastokartasta, erilaisine reitteineen, ja lisäksi vielä yksi melko yksinkertainen pieni reittihaku. Kuvaajassa nämä hakutilanteet ovat vaaka-akselilla samassa järjestyksessä kun ne on taulukoitu.

* Tapaus 1: Laaja lähes diagonaalisesti ylänurkasta alanurkkaan ulottuva haku. Tarkoitettu kuvaamaan perustapausta haun etsimisessä.
* Tapaus 2: Samalla kartalla tehty lyhyempi hakureitti. Valittu edustamaan hieman pienempää hakua hyvällä kartta-aineistolla.
* Tapaus 3: Reitti alkaen vasemmasta alakulmasta yläviistoon. Valittu siksi, että reitin suuntaisesti on hyvin paljon seinämää, ja haku ei olisi kovinkaan suoraviivainen.
* Tapaus 4: Vertailukohtana melko yksinkertainen hakualue, ja lyhyt reitti.

##### Tulosten tulkinta:

Kaiken kaikkiaan Dijkstra vs. muut -asetelma toteutuu aika oletetusti. Kun hakualue on yksinkertainen ja suoraviivainen (tapaus 4), kunnon heuristiikkaa sisältävät versiot päihittävät Dijkstran selkeästi, sillä käymättä jää huomatttavan iso osa alueesta. Sen sijaan kun hakupolku sisältää paljon seinämiä ja vaikuttaa vaikealta (tapaus 3), ero Dijkstran ja tehokkaampien heuristiikkojen välillä kutistuu. Molemmat toteutetut heuristiikat ovat alle neljäsosan nopeampia tässä tapauksessa, kun yksinkertaisessa tapauksessa paras tulos kutistui vain alle 20 prosenttiin suoritusajasta.

Tarkkoja O-luokituksia on tästä aineistosta hankala laskea, sillä kuten huomataan, hakualueen muoto ja yksityiskohdat vaikuttavat läpikäyntiin aika huomattavasti.

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

