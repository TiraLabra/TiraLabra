<h1> Toteutusdokumentti Pacman </h1>

<h2>Ohjelman yleisrakenne</h2>
Ohjelma muodostuu kuudesta paketista. Paketit ovat nimetty selkeästi, jotta on helposti pääteltävissä, mitä luokkia kukin paketti sisältää.

<h3>Peli</h3>

<h4>pacman</h4>
Paketti pacman sisältää vain Pelin aloittavan Main luokan. 
pacman.alusta
pacman.alusta paketti sisältää pelialustaan olennaisesti liittyvät luokat; Peliruudun ja Pelialustan. Pelialusta on yksinkertaisuudessaan kaksiulotteinen taulukko, joka muodostuu peliruuduista. Jokainen peliruutu tietää muun muassa omat koordinaattinsa, tiedon siitä onko ruudussa haamu tai Man, pistepallo tai ekstrapallo ja tiedon siitä mitä tyyppiä ruutu on (seinä, käytävä). 
Parhaiden reittien selvittämistä varten ruutu tietää myös etäisyysarvion maaliin ja lähtöruutuun sekä sen ruudun, josta ruutuun on siirrytty lyhimmässä reitissä. Pelialusta luokan tarkoitus on luoda pelialusta ja peliruudun tarkoitus on kertoa pelille tietoja itsestään. pacman.alusta paketti sisältää myös tekstitiedoston, johon on kirjattu pelialusta numeroin.

<h4>pacman.qui</h4>
Pakkaus pacman.gui sisältää pelin graafiseen liittymään ja näppäimistön toimintaan liittyvät toiminnot. Käyttöliittymä luokka toteuttaa graafisen liittymän ja huolehtii mahdollisista virheilmoituksista. Näppäimistönkuuntelija tulkitsee näppäimistön käyttöä ja piirtoalusta piirtää pelitilanteen jokaisen pelin actionperformed metodin päätteeksi. Rajapinta päivitettävän avulla voidaan toteuttaa metodi päivitä, jota käyttää esimerkiksi piirtoalusta uudestaan piirtämisessä. Pakkaus sisältää myös kaikki olennaiset kuvat (haamut, Man, kirsikka) .png-tyyppisenä.

<h4>pacman.hahmot</h4>
Pakkauksessa pacman.hahmot ovat kaikkien peliin liittyvien hahmojen luokat. Niin sanotusti ylimpänä on abstrakti luokka Hahmo, jonka kummatkin Man ja Haamu luokka toteuttavat. Luokkaan on kasattu kaikki metodit, joita kaikki haamut ja Man käyttävät. Man luokkaan on sisällytetty metodit, jotka käsittelevät pääosin Manin liikkumista pelialustalla. Man myös huolehtii itse omista elämistään.
Haamuja on pelissä neljä ja kaikki toimivat alustalla omalla tavallaan. Tämän takia tein jokaisesta haamusta oman luokkansa, joka perii ylemmän Haamu luokan. Yksittäisissä haamuluokissa, Red, Green, Cyan ja Magenta, huolehditaan haamujen erikoistarpeista. Esimerkiksi luokassa Magenta etsitään haamulle sopivaa ruutua maaliksi ja luokassa Green toteutetaan kokonaisuudessaan haamun liikkuminen, koska tämä haamu ei käytä muiden tavoin hakualgoritmia, vaan alkuperäisessä pelissä toteuttamaani liikkumistapaa. 
Pakkaus pacman.hahmot sisältää myös enum luokan Suunta, jonka avulla on toteutettu suunnat ylös, alas, oikealle ja vasemmalle helpommalla tavalla, joka myös siistii koodia.

<h4>pacman.peli</h4>
Pakkaus pacman.peli sisältää luokat, jotka käsittelevät pelin logiikkaa ja peliin olennaisesti liittyvät komponentit, pistelaskuri ja highscore. Pacman luokka sisältää kaikki metodit, jotka koskevat pelin logiikkaa ja HaamujenKasittelija huolehtii kaikesta haamujen liikuttamiseen liittyvästä toiminnasta. Pistelaskuri luokka huolehtii pelaajan pisteiden laskemisesti ja säilyttämisestä ja Highscore luokka huolehtii pelaajan pisteiden mahdollisesta tallentamisesta ennätys tiedostoon.

<h4>pacman.tietorakenteet</h4>
Pakkaus sisältää kaikki itse toteutetut tietorakenteet, järjestämisen sekä hakualgoritmin. Luokka Lista simuloi tavallisen listan toimintaa. Toteutettuja metodeja ovat esimerkiksi alkion hakeminen tietystä indeksistä, listaan lisääminen ja tarkistaminen kuuluuko annettu alkio listaan. Olen toteuttanut vain tarvitsemani perustoiminnot.
Järjestäjä luokka on toteutettu toimimaan lomitusjärjestämisen mukaisesti ja osaa järjestää yksiulotteisen taulukon johon on tallennettu peliruutuja. Luokka AStar toteuttaa hakualgoritmin astar. Hakualgoritmin avulla etsitään parhaat reitit haamuille liikkua.

<h3>Testit</h3>
Testit muodostuvat pakkauksista, jotka on nimetty samaan tapaan pelin pakkausten mukaisesti, mutta pakkausten nimen päätteenä on testi (esim. pacman.alusta.test). Jokaisessa pakkauksessa on jokaista pakkauksen luokkaa vastaava testiluokka, jossa on pyritty testaamaan pelin koodiluokkaa mahdollisimman kattavasti.
