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

<h2>Saavutetut aika- ja tilavaativuudet</h2>
Pelialusta on kaksiulotteinen taulukko, jonka leveys on aina 19 peliruutua ja korkeus on 21 peliruutua. Merkitään pelialustan kaikkia solmuja V:llä.

<pre><code>Astar(A, a, b) 
	//A pelialusta, a lahtö, b maali

	lisaaSopivatSolmut(A, B)
	muunnaSopivatListaKaymattomatTaulukoksi(B)
	alustus(b)
	alkuun[a] = 0


	//liiku(maali, alusta)
	while(solmu b ei ole viela joukossa S)
		jarjestetaan joukko K // kaymattomat
		valitaan solmu u kuuluu joukkoon K\S, jolla alkuun[u]+loppuun[u] on pienin
		
		pollaaPienin()
		S = S+{u}

		for jokainen suunta // oikea, vasen, alas, ylös
			if ruutu ei ole seinä lisataan naapurit listaan

		
		for jokainen solmu v joka kuuluu naapureihin
			if alkuun[v] > alkuun[u] + w(u,v)
				alkuun[v] = alkuun[u] + w(u,v)
				edellinen[v] = u
	
	


lisaaSopivatSolmut(A, B)
	// B on sopivat solmut
	for kaikille pelialustan solmuille
		if ruuduntyyppi != seina
			lisataan sopiviin ruutuihin joukkoon M


muunnaSopivatListaKaymattomatTaulukoksi(B)
	for kaikille sopiville solmuille
		lisataan m joukkoon kayttamattomat K


alustus(b)
	for kaikille somuille kaymattomille solmuille
		alkuun[k] = aareton
		loppuun[k] = arvioi suora etaisyys k~>b
		edellinen[k] = NIL

pollaaPienin()
	for uusi kaymättamat joukko N // N.lenght = K.lenght-1
		N[i] = K[i+1]
		K = N
</code></pre>

<h3>Aikavaativuus</h3>
<pre><code>Astar(A, a, b) 
	//A pelialusta, a lahtö, b maali

	lisaaSopivatSolmut(A, B)  //O(V)
	muunnaSopivatListaKaymattomatTaulukoksi(B) //O(1)
	alustus(b)  //O(1)
	alkuun[a] = 0  //O(1)


	//liiku(maali, alusta)
	while ei olla maalissa
		jarjestetaan joukko K //O(nlogn)
		valitaan solmu u kuuluu joukkoon K\S, jolla alkuun[u]+loppuun[u] on pienin  //O(1)
		
		pollaaPienin()
		S = S+{u}

		for jokainen suunta // oikea, vasen, alas, ylös
			if ruutu ei ole seinä lisataan naapurit listaan

		
		for jokainen solmu v joka kuuluu naapureihin
			if alkuun[v] > alkuun[u] + w(u,v)
				alkuun[v] = alkuun[u] + w(u,v)
				edellinen[v] = u
	
	


lisaaSopivatSolmut(A, B)
	// B on sopivat solmut
	for kaikille pelialustan solmuille //O(V)
		if ruuduntyyppi != seina  //O(1)
			lisataan sopiviin ruutuihin joukkoon M  //O(1)


muunnaSopivatListaKaymattomatTaulukoksi(B)
	for kaikille sopiville solmuille  //O(1)
		lisataan m joukkoon kayttamattomat K  //O(1)


alustus(b)
	for kaikille somuille kaymattomille solmuille  //O(1)
		alkuun[k] = aareton  //O(1)
		loppuun[k] = arvioi suora etaisyys k~>b  //O(1)
		edellinen[k] = NIL  //O(1)

pollaaPienin()
	for uusi kaymättamat joukko N // N.lenght = K.lenght-1  //O(1)
		N[i] = K[i+1]  //O(1)
		K = N  //O(1)
</code></pre>
<h3>Tilavaativuus</h3>
