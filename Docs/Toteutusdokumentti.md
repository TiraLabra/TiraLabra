Ohjelman rakenne:
 --------------
Ohjelman keskeisin komponentti on PuunTutkija luokka, joka vastaa hakupuiden suorituskyvyn testauksesta. Luokalle annetaan luomisen yhteydess� tutkittavat hakupuut, joihin suoritettavat mittaukset sitten kohdistetaan. Suorituskyvyn tutkimiseen luokka k�ytt�� Sekuntikello luokkaa, jolla voidaan mitata suoritukseen kuluvaa aikaa nanosekunteina.
Mitatut ajat talletetaan Mittaustulos olioon, joka kirjaa tulokset ja laskee n�ist� tilastoja. Useita mittaustuloksia voidaan tallettaa Vertailu olioon helppoa siirt�mist� ja tulostusta varten. Yhteen vertailu olioon on tarkoitus tallettaa joko saman teht�v�n suoritusta koskevat Mittaustulos-oliot eri puilla tai useiden eri suoritusten Mittaustulokset tietyll� puulla.
Ohjelman k�ytt�liittym� saa parametrina Puuntutkija olion. K�ytt�liittym�n vastuulla on antaa PuunTutkijalle tietojoukot ja kuvaukset suoritettaviin mittauksiin.

Saavutetut tila ja aika vaativuudet:
 ----------------
Kukin puu sis�lt�� suoraan vain yhden viitemuuttujan. O(1)
Jokaista puuhun lis�tt�v�� alkiota kohti on luotava uusi solmu, joista kukin sis�lt�� 3 viitemuuttujaa O(1) ja avaimen, joka on jokin vertailukelpoinen olio. T�ss� toteutuksessa integer jolle p�tee O(1).
T�ll�in koko solmun tilavaativuus on O(1).

Punamustan puun solmut sis�lt�v�t edell� mainitun lis�ksi nimetyn luokan Vari arvon ja AVL puun solmut vasaavasti kokonaislukuna talletettavan korkeus arvon. Kummankin n�ist� tilavaativuus on vakio O(1), joten ne eiv�t vaikuta ratkaisevasti solmun kokoluokkaan.

Kaikki puut ovat tilaativuudeltaan O(n), miss� n on puussa esiintyvien solmujen m��r�.


Suorituskyvyvertailu:
 ----------------
n:ll� tarkoitetaan seuraavassa aina solmujen lukum��r�� puussa.


Haku tapahuu kaikissa puissa l�hes identtisell� tavalla ja se on suhteessa puun korkeuteen. T�ll�in hakuoperaatioiden suorituskyky riippuu t�ysin siit� miten puu on rakentunut. Tarkkaan ottaen siit� miten korkea puu on.
Haussa etsitt�v�� arvoa verrataan aina solmun arvoon alkaen puun juuresta. Mik�li etsitt�v� avain on pienempi, edet��n solmun vasempaan lapseen. Jos etsitt�v� avain puolestaan on suurempi, edet��n oikeaan lapseen. Avaimien arvojen vertaaminen on vakioaikaista (O(1)) ja vertailuja suoritetaan enint��n puun korkeuden verran. Kun on p��sty lehteen ja etsitt�v�� avainta ei ole tullut vastaan, tiedet��n ettei arvoa l�ydy puusta.

Bin��rinen hakupuu on korkeudeltaan pahimmillaan n esimerkiksi silloin kun on lis�tty arvoja kasvavassa j�rjestyksess�. T�ll�in hakuaika on my�s vastaavasti O(n). Kuitenkin sattumanvaraisia lis�ys ja poisto-operaatioita teht�ess� puun korkeus l�henee arvoa log(n), joten hakuaika on my�s vastaavasti keskim��rin O( log(n) ).

Punamusta puu ja AVL - puu ovat tasapainotettuja, joten niiden lis�ys ja poisto-operaaiot(joista lis�� my�hemmin) takaavat ett� puun korkeus on aina logaritminen siin� olevien solmujen m��r��n n�hden ja t�ll�in hakuaika on my�s ilman muuta logaritminen eli O( log(n) ).

Splay-puun haku toimii muuten vastaavalla tavalla kuin bin��risen hakupuun, mutta etsitylle solmulle tehd��n Splay operaatio. Splay opertaatio siirt�� solmun puun juureksi k�ytte�en kierto-operaatioita. Yksi kierto-operaatio on suoritusajaltaan vakio ja niit� suoritetaan splay-operaatioissa enint��n puun korkeuden verran. Koska yksi kierto nostaa solmua yhden asteen yl�sp�in ja Puun korkeus voi splay puussa olla pahimmassa tapauksessa n, suoritetaan pahimmillaan n-1 kiertoa. T�m� antaisi ymm�rt�� ett� pahinmmassa tapauksessa suoritusaika olisi O(n). Kuitenkin koska Splay-operaatio muokkaa puuta ja operaation j�lkeen etsitty arvo on siirtynyt puun huipulle, t�st� saadaan monimutkaisen matematiikan kautta selvitetty� ett� keskim��r�inen suoritusaika Splay-operaatiolle on pahimmassakin tapauksessa O( log(n) ). T�ll�in my�s haun t�yty tapahtua pahimmassakin tapauksessa keskim��rin ajassa O( log() ).


Lis�ys bin��risess� hakupuussa tapahtuu hyvin vastaavalla tavalla kuin haku. Puuta edet��n kerros kerrokselta alasp�in siirtyen joko oikealle tai vasemmalle kunnes ei voida edet� pitemm�lle. T�m�n j�lkeen asetetaan osoittimille oikeat arvot. T�ll�in lis�yksen aikavaativuus on suhteessa puun korkeuteen. Bin��risen hakupuun korkeus k�siteltiin jo aiemmin joten lis�ysaika on keskim��rin O( log(n) ) ja pahimmillaan O( n ).

Lis�ys AVL-puussa alkaa aivan kuten bin��risess�kin hakupuussa. Kun solmu on lis�tty puuhun tehd��n kuitenkin viel� tasapainotusoperaatio, jonka idea on taata ett� puun korkeus on logaritminen. Tasapainotus koostuu 0 tai enint��n (log(n) - 1):st� kierto-opertaatiosta. Kierto-operaartion aikavaativuuden todettiin jo aiemmin olevan vakio, joten tasapainotuksen aikavaativuus on vastaavasti pahimmillaan luokkaa O( log(n) ).

Punamustan puun lis�ys toimii vastaavalla idealla kuin AVL-puun, mutta tasapainotus poikkeaa toteutukseltaan huomattavasti. Tasapainotuksessa on 5 eri tilannetta. N�ist� kaikki ovat suoritusajaltaan vakioaikaisia, mutta tilanteessa jossa lis�tyn solmun vanhemman sisarsolmu on punainen joudutaan nousemaan puuta yl�sp�in rekursiivisesti. Nousu tapahtuu 2 kerrosta kerrallaan joten pahimmassakin tapauksessa toistoja tulee log( n ) / 2, joten suoritus on aina aikaluokassa O( log(n) ).

My�s Splay puu toimii bin��risen hakupuun tavoin mutta lis�yksen j�lkeen lis�tty solmu tuodaan puun juureksi SPlay operaation avulla. Splay puun molempien operaatioiden aikavaativuus on jo selvill� joten lis�ys on pahimmassakin tapauksessa keskim��r�isesti aikaluokassa O( log(n) ).



Poisto Bin��risess� hakupuussa alkaa haku-toiminnolla jolla etsit��n poistettava solmu puusta. T�m�n j�lkeen solmu poistetaan yhdell� kolmesta tavasta riippuen siit� montako lasta kyseisell� solmulla on. Kaikki 3 tapausta ovat kuitenkin vakioaikaisia joten poisto-opertaation tehokkuus riippuu t�ysin haun tehokkuudesta joka on jo k�sitelty aiemmin. N�in ollen poisto on pahimmillaan aikaluokkaa O(n) ja keskim��rin O(log(n)).

AVL-puun poisto alkaa vastaavalla tavalla kuin bin��risen hakupuun. Solmun poistamisen j�lkeen tehd��n viel� jo aiemmin k�sitelty tasapainotusoperaatio. N�ist� saadaan pahimman tapauksen aikavaativuudeksi O( log(n) ).

Punamusta puun poisto koostuu lis�yksen tavoin vakioaikaisista tapauksista, joista vain yksi sis�lt�� mahdollisen rekurion. Pahimmassa tapauksessa edet��n taas yl�sp�in puuta ja puun ollessa pahimmillaankin logaritmisen korkuinen voidaan rekursio suorittaa enint��n log(n) kertaa. T�ten poiston t�ytyy olla luokkaa O( log(n) ).

Splay puun poistossa suoritetaan ensin poistettavalle solmulle splay operaatio, jolloin se tulee puun juureksi. T�m�n j�lkeen poistetaan solmu ja korvataan se edelt�j�ll��n (vasemman alipuun suurin alkio). Splayn aikavaativuus on jo k�istelty joten luokaksi saadaan taas pahimmassakin tapauksessa keskim��rin O( log(n) ).


Puutteet ja parannusehdotukset:
 ------------------------------
* lis�� pseudokoodit
* Lis�� matematiikkaa yll�olevaan
* Lis�� tietojoukkojen yms haku tiedostosta
* Refaktoroi kaikki tietorakenteet
* Implementoi solmuille ja puille yli-ala -luokka rakenne
* Lis�� kommentteja
* Paranna JUnitin rivikattavuutta
* Graafinen k�ytt�liittym� ja/tai puiden tulostus


L�hteet:
* www.wikipedia.com/*
* www.wikipedia.fi/*