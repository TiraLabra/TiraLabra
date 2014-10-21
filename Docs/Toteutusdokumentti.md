# Toteutus

## Yleisrakenne

Suorituksen aluksi Kartanlukija-apuohjelma luo verkon tekstitiedostosta.
Luotu verkko annetaan parametrina Astar-oliolle aloitus- ja kohdesolmujen
ohella.

Astar alustaa verkon solmut laskien niille et‰isyysarvion kohdesolmuun
Manhattan-et‰isyyten‰. 

Astar-oliolta kysytt‰ess‰ lyhint‰ polkua se palauttaa kohdesolmun, joka
sis‰lt‰‰ viitteen edelliseen solmuun aloitussolmuun asti. T‰m‰ polku
tulostetaan tulostaVerkko-metodilla.


## Astar-toteutus

Astar-luokan konstruktorille annetaan verkko sek‰ aloitus- ja kohdesolmu.
Konstruktorissa verkon solmuille lasketaan et‰isyysarvio kohdesolmuun.
Aloitussolmu lis‰t‰‰n l‰pik‰ym‰ttˆmien minimikekoon. L‰pik‰ym‰ttˆmien
solmujen et‰isyys alkuun on miljoona, joka vastaa ‰‰retˆnt‰ arvoa t‰ss‰
yhteydess‰.

Lyhimm‰n polun etsinn‰ss‰ algoritmi ottaa k‰sittelyyn minimikeosta
pienimm‰ll‰ et‰isyysarvioilla varustetun solmun niin kauan kunnes kohde-
solmu on k‰sitelty.

Valitun solmun vieruslista k‰yd‰‰n l‰pi ja muutetaan niiden solmujen
et‰isyysarvioita ja polkua, joihin k‰sittelyss‰ olevasta solmusta 
p‰‰st‰‰n lyhyemp‰‰ reitti‰ kuin aiemmin oli tiedossa. Vierussolmut
lis‰t‰‰n l‰pik‰ym‰ttˆmien minimikekoon, ellei solmua ole jo k‰sitelty.

Algoritmin aikavaativuudeltaan haastavin osa on et‰isyysarvioltaan
pienimm‰n solmun valitseminen jokaisella solmulla, mik‰ on minimikeon
kera |V| log |V|. Jokaisen solmun kohdalla sen vieruslistat k‰sitell‰‰n,
joissa vaativinta on minimikeon pienennysoperaatio |E| log |V|. 
Summattuna aikavaativuudeksi saadaan O((|E| + |V|) log |V|), miss‰ |V| on
solmujen ja |E| kaarten lukum‰‰r‰.

L‰hteen‰ on k‰ytetty Tira kev‰t 2014 -materiaalin pseudokoodiesityst‰.


## Kartanlukija-apuohjelma

Kartanlukijalle annetaan Javan File-oliona tekstitiedostossa sijaitseva
kartta. Kartta luetaan Javan Scannerilla riveitt‰in, jotka taas luetaan
kirjaimittain. Jokaisesta kirjaimesta tehd‰‰n solmu ja kirjaimen arvo
merkataan solmun painoksi. Lopuksi kutsutaan Verkon vieruslistojen
luonti-metodia.

Kartanlukija ottaa tekstitiedostoa lukiessaan muistiin aloitus- ja
kohdesolmut, joita Astar tarvitsee.

Kartanlukija tekee jokaisesta merkist‰ solmun, jotka k‰yd‰‰n uudestaan
l‰pi vieruslistoja tehdess‰. Tekstitiedoston muutto verkoksi on aika-
vaativuudeltaan lineaarinen kartan koon suhteen.


## tulostaVerkko-apumetodi

tulostaVerkko-metodi yhdist‰‰ verkon ja polun graafiseksi esitykseksi.
Verkko tulostuu System.out.print -komennoilla tekstipohjaisena, jossa
lyhin polku on merkitty pistein aloitussolmusta A kohdesolmuun B.

Metodin aikavaativuus on neliˆllinen solmujen lukum‰‰r‰n suhteen, sill‰
se k‰y jokaisen l‰pi verkon jokaisen solmun ja tarkistaa jokaisen solmun
kohdalla, kuuluuko solmu lyhimp‰‰n polkuun.

## Tietorakenteet

Ohjelmassa on itsetehtyin‰ linkitetty lista ja minimikeko -tietorakenteet
sek‰ verkko:

### Minimikeko

Keon pienin alkio s‰ilytet‰‰n taulukon ensimm‰isess‰ paikassa.
Keon koko t‰ytyy tiet‰‰ etuk‰teen, sill‰ tietorakenne k‰ytt‰‰
taulukkoa, jonka koko on m‰‰ritelt‰v‰ aluksi.

Toteutin tietorakenteen muokkaamalla maksimikeon pseudokoodia
Tira kev‰t 2014 -materiaalista. Suurimmat erot materiaaliin n‰hden
ovat vaihdaKeskenaan-metodi, joka vaihtaa siististi kahden 
taulukon paikan solmut kesken‰‰n, sek‰ metodiin liittyv‰ indeksin-
pyˆritys. "Indeksinpyˆritys" tarkoittaa sit‰, ett‰ keon alkio
tiet‰‰ paikkansa keon taulukossa, jolloin haku voidaan toteuttaa
vakioaikaisena.

Kekoehdon korjausmetodi (heapify), pienimm‰n alkion poisto, alkion
lis‰ys ja alkion arvon pienennys ovat aikavaativuudeltaan
logaritmisia keon alkioiden lukum‰‰r‰n suhteen. Muut metodit ovat
vakioaikaisia. Heapify on toteutettu rekursiivisesti, joten sen
tilavaativuus on logaritminen alkioiden lukum‰‰r‰n suhteen, kuten
on myˆs pienimm‰n alkion poisto, sill‰ se kutsuu heapify-metodia.
Muut metodit ovat vakiotilaisia.

### Linkitetty lista

Linkitetty lista toimii kuin pino. Kokoa ei tarvitse ennalta 
m‰‰ritt‰‰. Tietorakenne koostuu kahdesta luokasta: listasta ja sen
alkioista, listasolmuista. Lista tiet‰‰ ensimm‰isen listasolmun ja
listasolmu tiet‰‰ sit‰ seuraavan listasolmun.

Toteutin tietorakenteen Tira kev‰t 2014 -materiaalista saamani
intuitiivisen k‰sityksen mukaan. Suurin ero materiaaliin n‰hden on
koko-metodi, joka laskee listan sis‰lt‰mien alkioiden lukum‰‰r‰n.
	
Aikavaativuudeltaan alkioiden lukum‰‰r‰n suhteen lineaarista koko-
metodia lukuunottamatta kaikki metodit ovat vakioiaikaisia.

### Verkko

Verkko sis‰lt‰‰ kokoelman solmuja. Tietorakenne koostuu kahdesta
luokasta: verkosta ja sen alkioista, solmuista. Verkko tiet‰‰
kaikki solmut ja solmut tiet‰v‰t kaaret toisiin solmuihin. Verkkoon
liittyen solmu tiet‰‰ paikkansa verkon 2D-esityksess‰ (X ja Y 
-koordinaatit) ja painonsa. Astariin liittyen solmu tiet‰‰
et‰isyyden alkuun, arvion loppuun ja polun, josta lyhin polku
saapui. Kun Astar ottaa solmuja k‰sittelyyn, k‰sittelem‰ttˆmi‰
solmuja pidet‰‰n minimikeossa, jonka toiminnan nopeuttamiseksi 
solmu tiet‰‰ indeksins‰ t‰ss‰ keossa.
	
Toteutin tietorakenteen Tira kev‰t 2014 -materiaalin oppien mukaan
siten, ett‰ verkko talletetaan vieruslistaesityksen‰. Verkko-
luokka sis‰lt‰‰ luoVieruslistat-metodin, joka luo vieruslistat
verkon solmuille Manhattan-tyylisesti.

Aikavaativuudeltaan vieruslistojen luonti ja solmun haku verkosta
ovat lineaarisia. Astar ei k‰yt‰ solmun hakua. Verkon l‰pik‰ynti
on aikavaativuudeltaan myˆs lineaarinen, mutta verkkoon ei ole
toteutettu t‰h‰n toiminnallisuutta. Solmun lis‰‰minen verkkoon on
vakioaikaista, sill‰ verkko k‰ytt‰‰ solmujen tallettamiseen
linkitettyn‰ listana toteutettua pinoa.


