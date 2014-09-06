#Reittialgoritmien toteutus

**Simulaatio**-luokka sisältää tiedon "maailmasta", joka on erityyppisiä ruutuja sisältävä 2-uloitteinen taulukko. Ruuduille voidaan asettaa erilaiset liikkumiskustannukset. Seinän lävitse ei voi liikkua lainkaan.

**Algoritmi** on abstrakti luokka, joka toimii pohjatoteutuksena kaikille tässä harjoitustyössä toteutetuille reittialgoritmeille. Algoritmille annetaan luomisvaiheessa tieto edellä mainitusta maailma-ruudukosta, alku- ja maalipisteen koordinaatit, tieto siitä monenko millisekunnin hidaste suoritukseen mahdollisesti halutaan, ja tieto siitä onko vinottain liikkuminen sallittua. Algoritmi toteuttaa runnable-rajapinnan eli se voidaan asettaa suoritukseen omaan säikeeseensä, kuten simulaatio-luokassa tehdään. Algoritmilta voidaan kysyä onko se valmis (tai sille voidaan antaa erityisen Paivitettava-rajapinnan toteuttava luokka jonka paivita()-metodia kutsutaan reitin löydyttyä), jonka jälkeen algoritmilta voidaan kysyä valmista reittiä. Reitti palautetaan linkitettynä listana "solmuja" maalisolmusta alkusolmuun. Algoritmi pitää myös suorituksen aikana yllä "RuudunTila"-enumeista koostuvaa maailmaruudukon kokoista ruudukkoa, jota tutkimalla saadaan selville mitä ruutua algoritmi on milläkin hetkellä tutkimassa, missä se on jo käynyt, ja mitkä on merkitty tutkittaviksi. Tästä on suuresti apua algoritmin toiminnan visualisoimisessa.

Käyttäjän asettamasta asetuksesta on kiinni, voiko reitti kulkea vinottain vai pelkästään kohtisuoria suuntia pitkin. Vinottain kulkemisen kustannus on periaatteessa sama kuin kohtisuoran suunnan kulkemisen kustannus, eli käytännössä tosielämän ajattelumaailmasta poiketen kulkeminen vinottain vastoin Pythagoraan oppeja ei ole työläämpää kuin vaakasuoraan kulkeminen. Tämä saattaa johtaa omituiselta näyttäviin (mutta täysin oikein toimiviin) tilanteisiin, joissa algoritmi löytää lyhimmän mahdollisen reitin, joka kuitenkin näyttää tekevän omituisen "koukkauksen" johonkin suuntaan. Tällaisissa tilanteissa on kuitenkin huomioitavaa, että tällainen reitti on aivan yhtä lyhyt kuin reitti, jossa oltaisiin kuljettu "suoraan" perille.

Tässä harjoitustyössä toteutettiin reittialgoritmit Leveyssuuntainen haku (BreadthFirstAlgoritmi), Dijkstran algoritmi (DijkstraAlgoritmi), Ahne "paras ensin" -algoritmi (GreedyBestFirstAlgoritmi) ja A*-haku (AStarAlgotitmi). Niiden toteutukset muistuttavat jonkun verran toisiaan mutta niiden toiminnallisuus on kirjoitettu mahdollisimman selkeästi ja koodi on kommentoitua. Algoritmien yksityiskohtaista toteutusta käsitellään tiedostoissa jotka ovat saman nimisiä kuin ko. algoritmit.

##Breadth First Search
Leveyssuuntainen haku eli BreadthFirstAlgoritmi on yksinkertaisin toteutetuista reittialgoritmeista. Algoritmi ei ota lainkaan huomioon ruutujen liikkumiskustannuksia ja etenee leveyssuuntaisesti joka suuntaan kunnes maaliruutu on löydetty. Tämä algoritmi ei siis löydä aina lyhintä mahdollista reittiä, jos ruudukossa on ruutuja, joilla on keskenään erit liikkumiskustannukset. Tämän algoritmin käyttäminen onkin mielekästä vain, jos ruudukossa on seinien lisäksi vain yhden tyyppisiä ruutuja (esim. lattiaa).

Haku käyttää hyväkseen Jono-tietorakennetta, jossa pidetään yllä listaa toistaisekti tutkimattomista koordinaateista. Aluksi lisätään alkupiste tähän listaan.

Niin kauan kuin tutkittavien listassa riittää alkioita, otetaan sieltä ulos seuraava, ja jos ei olla maalissa, lisätään tutkittavien listaan tämän sijainnin naapurit , joissa ei olla vielä käyty. Jatketaan tätä kunnes päästään maaliin tai tutkittavien alkioiden joukko on tyhjä, jolloin reittiä ei löytynyt.

Lähde: http://www.redblobgames.com/pathfinding/a-star/introduction.html kohta Breadth First Search

##Dijkstran algoritmi
Dijkstran algoritmi toimii kuten leveyssuuntainen haku, mutta se ottaa lisäksi huomioon maaston sijaintien liikkumiskustannukset. Dijkstran algoritmi löytää ruudukossa aina lyhimmän mahdollisen reitin.

Dijkstran algoritmi eroaa leveyssuuntaisesta hausta siten, että siinä missä leveyssuuntainen haku etenee tasaisesti joka suuntaan, dijkstran algoritmi suosii edetessään solmuja, joilla on alhaisempi liikkumiskustannus. Dijkstran algoritmi toisin sanoen levittäytyy tasaisesti joka suuntaan jo liikutun matkan (kustannuksen) mielessä, ei pelkän etäisyyden mielessä kuten leveyssuuntainen haku.

Haku käyttää hyväkseen Prioriteettikeko-tietorakennetta, josta valitaan tutkittavaksi solmuksi aina sellainen solmu, jossa tähän saakka kuljettu matka on mahdollisimman pieni. Aluksi lisätään aloituspiste tähän listaan. Lisäksi pidetään yllä taulukkoa, johon kirjataan parhaat lyhimmät matkat, joka kulkemalla on päästy kuhunkin pisteeseen maailmassa.

Niin kauan kuin tutkittavien listassa riittää alkioita, otetaan sieltä ulos seuraava, ja jos ei olla maalissa, lisätään tutkittavien listaan tämän sijainnin naapurit, joihin ei olla vielä päästy vähintään yhtä hyvää reittiä pitkin. Jatketaan tätä kunnes päästään maaliin tai tutkittavien alkioiden joukko on tyhjä, jolloin reittiä ei löytynyt.

Lähde: http://www.redblobgames.com/pathfinding/a-star/introduction.html kohta Dijkstra

##Greedy best first -algoritmi
Greedy Best First Search on niinsanottu heuristinen hakualgoritmi. Se tarkoittaa sitä, että se käyttää apunaan heuristiikkaa, eli arvioitua etäisyyttä maaliin. Greedy Best First Search on myös nimensä mukaisesti niinsanottu "ahne" ("greedy") algoritmi. Tämä tarkoittaa sitä, että algoritmi etenee "ahneesti" kohti maalisolmua, eikä välttämättä löydä parasta mahdollista reittiä.

Haku käyttää hyväkseen Prioriteettikeko-tietorakennetta, josta valitaan tutkittavaksi solmuksi aina sellainen solmu, josta arvioitu etäisyys maaliin on mahdollisimman pieni. Valinta toteutetaan antamalla prioriteettikeolle konstruktorissa vertailija, joka suosii solmuja edellä kuvatulla tavalla. Aluksi lisätään aloituspiste tähän listaan.

Niin kauan kuin tutkittavien listassa riittää alkioita, otetaan sieltä ulos seuraava, ja jos ei olla maalissa, lisätään tutkittavien listaan tämän sijainnin naapurit, joissa ei olla vielä käyty. Jatketaan tätä kunnes päästään maaliin tai tutkittavien alkioiden joukko on tyhjä, jolloin reittiä ei löytynyt.

Lähde: http://www.redblobgames.com/pathfinding/a-star/introduction.html kohta Greedy Best First

##A*-algoritmi

AStarAlgoritmi, joka oli tämän harjoitustyön alkuperäinen aihe, on eräänlainen yhdistelmä Dijkstran algoritmia ja GreedyBestFirst-algoritmia. Algoritmi ottaa huomioon sekä jo kuljetun etäisyyden että arvioidun jäljellä olevan matkan.

A* on myös niinsanottu heuristinen algoritmi, mutta siitä erikoinen, että se todellakin osaa löytää parhaan mahdollisen reitin. Se, että tapahtuuko näin käytännössä, riippuu käytössä olevasta heuristiikasta. Heuristiikoista on kerrottu tarkemmin tiedostossa Heuristiikat.md.

Haku käyttää hyväkseen Prioriteettikeko-tietorakennetta, josta valitaan tutkittavaksi solmuksi aina sellainen solmu, josta jo kuljetun matkan ja arvioidun etäisyyden maaliin summa on mahdollisimman pieni. Valinta toteutetaan antamalla prioriteettikeolle konstruktorissa vertailija, joka suosii solmuja edellä kuvatulla tavalla. Aluksi lisätään aloituspiste tähän listaan.

Niin kauan kuin tutkittavien listassa riittää alkioita, otetaan sieltä ulos seuraava, ja jos ei olla maalissa, lisätään tutkittavien listaan tämän sijainnin naapurit, joihin ei olla vielä päästy vähintään yhtä hyvää reittiä pitkin. Jatketaan tätä kunnes päästään maaliin tai tutkittavien alkioiden joukko on tyhjä, jolloin reittiä ei löytynyt.

Lähde: http://www.redblobgames.com/pathfinding/a-star/introduction.html kohta A*
