Ohjelman rakenne:
 --------------
Ohjelman keskeisin komponentti on PuunTutkija luokka, joka vastaa hakupuiden suorituskyvyn testauksesta. Luokalle annetaan luomisen yhteydessä tutkittavat hakupuut, joihin suoritettavat mittaukset sitten kohdistetaan. Suorituskyvyn tutkimiseen luokka käyttää Sekuntikello luokkaa, jolla voidaan mitata suoritukseen kuluvaa aikaa nanosekunteina.
Mitatut ajat talletetaan Mittaustulos olioon, joka kirjaa tulokset ja laskee näistä tilastoja. Useita mittaustuloksia voidaan tallettaa Vertailu olioon helppoa siirtämistä ja tulostusta varten. Yhteen vertailu olioon on tarkoitus tallettaa joko saman tehtävän suoritusta koskevat Mittaustulos-oliot eri puilla tai useiden eri suoritusten Mittaustulokset tietyllä puulla.
Ohjelman käyttöliittymä saa parametrina Puuntutkija olion. Käyttöliittymän vastuulla on antaa PuunTutkijalle tietojoukot ja kuvaukset suoritettaviin mittauksiin.

Saavutetut tila ja aika vaativuudet:
 ----------------
Kukin puu sisältää suoraan vain yhden viitemuuttujan. O(1)
Jokaista puuhun lisättävää alkiota kohti on luotava uusi solmu, joista kukin sisältää 3 viitemuuttujaa O(1) ja avaimen, joka on jokin vertailukelpoinen olio. Tässä toteutuksessa integer jolle pätee O(1).
Tällöin koko solmun tilavaativuus on O(1).

Punamustan puun solmut sisältävät edellä mainitun lisäksi nimetyn luokan Vari arvon ja AVL puun solmut vasaavasti kokonaislukuna talletettavan korkeus arvon. Kummankin näistä tilavaativuus on vakio O(1), joten ne eivät vaikuta ratkaisevasti solmun kokoluokkaan.

Kaikki puut ovat tilaativuudeltaan O(n), missä n on puussa esiintyvien solmujen määrä.


Suorituskyvyvertailu:
 ----------------
n:llä tarkoitetaan seuraavassa aina solmujen lukumäärää puussa.


Haku tapahuu kaikissa puissa lähes identtisellä tavalla ja se on suhteessa puun korkeuteen. Tällöin hakuoperaatioiden suorituskyky riippuu täysin siitä miten puu on rakentunut. Tarkkaan ottaen siitä miten korkea puu on.
Haussa etsittävää arvoa verrataan aina solmun arvoon alkaen puun juuresta. Mikäli etsittävä avain on pienempi, edetään solmun vasempaan lapseen. Jos etsittävä avain puolestaan on suurempi, edetään oikeaan lapseen. Avaimien arvojen vertaaminen on vakioaikaista (O(1)) ja vertailuja suoritetaan enintään puun korkeuden verran. Kun on päästy lehteen ja etsittävää avainta ei ole tullut vastaan, tiedetään ettei arvoa löydy puusta.

Binäärinen hakupuu on korkeudeltaan pahimmillaan n esimerkiksi silloin kun on lisätty arvoja kasvavassa järjestyksessä. Tällöin hakuaika on myös vastaavasti O(n). Kuitenkin sattumanvaraisia lisäys ja poisto-operaatioita tehtäessä puun korkeus lähenee arvoa log(n), joten hakuaika on myös vastaavasti keskimäärin O( log(n) ).

Punamusta puu ja AVL - puu ovat tasapainotettuja, joten niiden lisäys ja poisto-operaaiot(joista lisää myöhemmin) takaavat että puun korkeus on aina logaritminen siinä olevien solmujen määrään nähden ja tällöin hakuaika on myös ilman muuta logaritminen eli O( log(n) ).

Splay-puun haku toimii muuten vastaavalla tavalla kuin binäärisen hakupuun, mutta etsitylle solmulle tehdään Splay operaatio. Splay opertaatio siirtää solmun puun juureksi käytteäen kierto-operaatioita. Yksi kierto-operaatio on suoritusajaltaan vakio ja niitä suoritetaan splay-operaatioissa enintään puun korkeuden verran. Koska yksi kierto nostaa solmua yhden asteen ylöspäin ja Puun korkeus voi splay puussa olla pahimmassa tapauksessa n, suoritetaan pahimmillaan n-1 kiertoa. Tämä antaisi ymmärtää että pahinmmassa tapauksessa suoritusaika olisi O(n). Kuitenkin koska Splay-operaatio muokkaa puuta ja operaation jälkeen etsitty arvo on siirtynyt puun huipulle, tästä saadaan monimutkaisen matematiikan kautta selvitettyä että keskimääräinen suoritusaika Splay-operaatiolle on pahimmassakin tapauksessa O( log(n) ). Tällöin myös haun täyty tapahtua pahimmassakin tapauksessa keskimäärin ajassa O( log() ).


Lisäys binäärisessä hakupuussa tapahtuu hyvin vastaavalla tavalla kuin haku. Puuta edetään kerros kerrokselta alaspäin siirtyen joko oikealle tai vasemmalle kunnes ei voida edetä pitemmälle. Tämän jälkeen asetetaan osoittimille oikeat arvot. Tällöin lisäyksen aikavaativuus on suhteessa puun korkeuteen. Binäärisen hakupuun korkeus käsiteltiin jo aiemmin joten lisäysaika on keskimäärin O( log(n) ) ja pahimmillaan O( n ).

Lisäys AVL-puussa alkaa aivan kuten binäärisessäkin hakupuussa. Kun solmu on lisätty puuhun tehdään kuitenkin vielä tasapainotusoperaatio, jonka idea on taata että puun korkeus on logaritminen. Tasapainotus koostuu 0 tai enintään (log(n) - 1):stä kierto-opertaatiosta. Kierto-operaartion aikavaativuuden todettiin jo aiemmin olevan vakio, joten tasapainotuksen aikavaativuus on vastaavasti pahimmillaan luokkaa O( log(n) ).

Punamustan puun lisäys toimii vastaavalla idealla kuin AVL-puun, mutta tasapainotus poikkeaa toteutukseltaan huomattavasti. Tasapainotuksessa on 5 eri tilannetta. Näistä kaikki ovat suoritusajaltaan vakioaikaisia, mutta tilanteessa jossa lisätyn solmun vanhemman sisarsolmu on punainen joudutaan nousemaan puuta ylöspäin rekursiivisesti. Nousu tapahtuu 2 kerrosta kerrallaan joten pahimmassakin tapauksessa toistoja tulee log( n ) / 2, joten suoritus on aina aikaluokassa O( log(n) ).

Myös Splay puu toimii binäärisen hakupuun tavoin mutta lisäyksen jälkeen lisätty solmu tuodaan puun juureksi SPlay operaation avulla. Splay puun molempien operaatioiden aikavaativuus on jo selvillä joten lisäys on pahimmassakin tapauksessa keskimääräisesti aikaluokassa O( log(n) ).



Poisto Binäärisessä hakupuussa alkaa haku-toiminnolla jolla etsitään poistettava solmu puusta. Tämän jälkeen solmu poistetaan yhdellä kolmesta tavasta riippuen siitä montako lasta kyseisellä solmulla on. Kaikki 3 tapausta ovat kuitenkin vakioaikaisia joten poisto-opertaation tehokkuus riippuu täysin haun tehokkuudesta joka on jo käsitelty aiemmin. Näin ollen poisto on pahimmillaan aikaluokkaa O(n) ja keskimäärin O(log(n)).

AVL-puun poisto alkaa vastaavalla tavalla kuin binäärisen hakupuun. Solmun poistamisen jälkeen tehdään vielä jo aiemmin käsitelty tasapainotusoperaatio. Näistä saadaan pahimman tapauksen aikavaativuudeksi O( log(n) ).

Punamusta puun poisto koostuu lisäyksen tavoin vakioaikaisista tapauksista, joista vain yksi sisältää mahdollisen rekurion. Pahimmassa tapauksessa edetään taas ylöspäin puuta ja puun ollessa pahimmillaankin logaritmisen korkuinen voidaan rekursio suorittaa enintään log(n) kertaa. Täten poiston täytyy olla luokkaa O( log(n) ).

Splay puun poistossa suoritetaan ensin poistettavalle solmulle splay operaatio, jolloin se tulee puun juureksi. Tämän jälkeen poistetaan solmu ja korvataan se edeltäjällään (vasemman alipuun suurin alkio). Splayn aikavaativuus on jo käistelty joten luokaksi saadaan taas pahimmassakin tapauksessa keskimäärin O( log(n) ).


Puutteet ja parannusehdotukset:
 ------------------------------
* lisää pseudokoodit
* Lisää matematiikkaa ylläolevaan
* Lisää tietojoukkojen yms haku tiedostosta
* Refaktoroi kaikki tietorakenteet
* Implementoi solmuille ja puille yli-ala -luokka rakenne
* Lisää kommentteja
* Paranna JUnitin rivikattavuutta
* Graafinen käyttöliittymä ja/tai puiden tulostus


Lähteet:
* www.wikipedia.com/*
* www.wikipedia.fi/*