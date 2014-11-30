# Toteutusdokumentti

**Ohjelman yleisrakenne**
Ohjelma yrittää voittaa pelajaan käden kivi-paperi-sakset(-lisko-spock) -pelissä. Toteutus on hyvin suoraviivainen:
* Ensimmäisellä kierroksella kone valitsee ennalta määrätyn käden pelattavaksi
* Jokaisen kierroksen jälkeen kone tallentaa kierroksen tilanteen (pelaajan käsi, tietokoneen käsi ja voittaja)
* Kierroksilla 2-5 kone olettaa pelaajan pelaavan aina samalla tavalla (Jos pelaaja voitti edellisen kierroksen, pelaaja pelaa uudelleen samaa kättä. Jos pelaaja hävisi kierroksen, pelaaja valitsee hävinnyttä kättä seuraavan käden rotaatiosta kivi-paperi-sakset(-lisko-spock))
* Kierroksen 5 jälkeen kone käyttää heuristiikkaa (johon toivottavasti on kerääntynyt riittävästi dataa). Heuristiikka etsii edellistä kierrosta vastaavan jo pelatun tilanteen ja kertoo mitä kättä pelaaja pelasi tämän jälkeen. Mikäli tilanteita on useita, heuristiikka palauttaa pelaajan useimmiten pelaamaan käden em. tilanteessa. Kone asettaa tätä kättä voittavan käden tietokoneen seuraavaksi kädeksi. Heuristiikka pitää muistissaan ennalta määrätyn määrän statistiikkaa (tällä hetkellä 20 viimeisintä kierrosta)

**Saavutetut aika- ja tilavaativuudet**
Ohjelma toimii vakioajassa ja -tilassa. 

* Statistiikka tallentaa pelattujen kierrosten lukumäärän, joka siis kasvaa yhdellä per kierros -> aika O(1), tila O(1)
* Statistiikka tallentaa pelaajan voittojen määrän -> aika O(1), tila O(1)
* Tekoäly valitsee alussa rotaation perusteella koneen pelaaman käden. Maksimimäärä rotaatiota on 4 (kivi-paperi-sakset-lisko-spock) jonka jälkeen ollaan samassa kädessä mistä rotaatio aloitettiin -> aika O(4) = O(1), tila O(5) = O(1)
* Heuristiikka tallentaa linkitettyyn listaan pelejä. Kun pelien määrä on saavuttanut 20 alkiota, seuraava talletettava peli laitetaan listan loppuun ja lista ensimmäinen alkio poistetaan (lista koko siis on maksimissaan 20). Sijoitusoperaatiot ovat vakioaikaisia -> aika O(1), tila O(20) = O(1)

**Mahdolliset puutteet ja parannusehdotukset**
Ohjelma käsittelee pelin käsiä sekä käsi-olioina, joilla on tekstimuotoinen kenttä nimi, että numeroina (heuristiikkassa). Selvyyden vuoksi yritän saada koko ohjelman käsittelemään käsiä ainoastaan numeraalisessa muodossa ja itse käyttöliittymä hoitaa numeroiden muuttamisen tekstiksi. 

Heuristiikan laajentaminen parantaisi koneen voittosuhdetta joten talletettavien käsien määrän nostaminen voi tulla kyseeseen. 
