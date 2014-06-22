#Toteutus#

##Yleisrakenne##

###Sisään- ja ulostulo sekä yleiset työkalut###

Tiedoston lukemiseen ja kirjoittamiseen toteutin kolme luokkaa: Luokka IO lukee ja kirjoittaa tiedostoon tavu kerrallaan käyttäen javan FileInputStream- ja FileOutputStream -luokkia. Koska tavujonon koodatut esitykset ovat LZ77:n tapauksessa 9 tai 17 ja LZW tapauksessa 12 bittiä pitkiä, tarvitaan luokat InputBuffer ja OuputBuffer hallinnoimaan bittijonon "tavuttamista". Luokat pitävät yllä jonoja, joista kirjoitetaan tiedostoon kun seuraava tavu täyttyy. 

ByteAsBits-luokka hallinnoi tavun erilaisia esityksiä. Ohjelmassa seikkailee kolmenlaisia esityksiä tavulle: javan byte-tietotyyppi(arvoiltaan -128 - 127), integer(0 - 256) sekä 8-paikkainen boolean-taulukko. ByteAsBits on tavua esittävä olio, joka voidaan alustaa millä tahansa kolmesta esityksestä ja joka voi palauttaa tavun kaikissa muodoissa.


ArrayUtils sisältää staattisia metodeita byte- ja boolean-taulukkojen muunnoksiin.

###LZ77###

LZ77-pakkaaja ja -purkaja käyttävät luokkia SlidingTable, LinkedQueue ja SlidingWindow. 

SlidingTable on jonomaisesti toimiva päällekirjoittava taulukko. Taulukolla on maksimikoko, jonka täyttyessä aletaan päällekirjoittaa nollaindeksistä alkaen. Päällekirjoitettava alkio palautetaan. Taulukosta voi hakea alkioita sekä suhteellisella indeksillä, jolla nollaindeksi on viimeisenä lisättyä seuraava, tai todellisella indeksillä, jolloin nollaindeksi on taulukon konkreettinen nollaindeksi. SlidingTablea käytetään purkajassa, sekä pakkaajan apuluokassa SlidingWindow.

SlidingWindow on pakkaajan apuluokka, algoritmin liukuvan ikkunan implementaatio. Luokka ylläpitää SlidingTable-taulukkoa sekä jonon jokaista tavun arvoa kohtaan. Kun alkio lisätään liukuikkunan, sen todellinen indeksi SlidingTable-taulukossa tallennetaan sitä vastaavaan jonoon. Kun alkio putoaa ikkunnasta, poistetaan sitä vastaavasta jonosta alkio. Kun haetaan parasta osumaa annetulle merkkijonolle, verrataan vain niitä merkkijonoja, jotka alkavat samalla tavulla.

LinkedQueue on linkitetyllä listalla toteutettu jono. Luokka käyttää solmuja kuvaamaan luokkaa LinkedNode, sekä iteraattoria LinkedQueueIterator. 

###LZW###

LZW käyttää luokkaa Dictionary, joka vastaa algoritmin hakemistoa. Dictionary tallentaa tavujonot taulukkoon, ja viitteet taulukon alkiohin binäärihakupuihin. Binäärihakupuita on jokaista alkumerkkiä vastaan kuten SlidingWindow-luokassa jonoja. Binääripuiden solmuja vastaa luokka BinaryTreeNode. Dictionary-oliosta voi hakea siis sekä tavujonon, että taulukkoindeksin mukaan. Sekä pakkaaja että purkaja käyttävät samaa Dictionary-luokkaa. 

##Aika- ja tilavaativuudet##

Koska LZ77:n ikkunan ja LZW:n hakemiston koot ovat kiinteitä, algoritmien aika- ja tilavaativuudet ovat suhteessa tiedoston kokoon aina lineaarisia, on mielekkäämpä verrata niiden vaativuuksia suhteessa ikkunan tai hakemiston kokoon. Tämä on olennaista, sillä pakkaussuhde paranee näitä kasvattaessa. 

###Aputietorakenteet###

SlidingTable-luokan add- ja get-metodit ovat vakioaikaisia, sillä ne viittaavat suoraan tiettyyn taulukon indeksiin joka saadaan perustoimituksilla. pollAll-metodi käy kaikki alkiot kerran läpi, siis sen aikavaatimus on lineaarinen suhteessa taulukon kokoon. Tilavaativuudet ovat myös vakiollisia, lukuunottamatta pollAll-metodia, joka luo uuden taulukon, jonka koko on SlidingTablen koko, siis tilavaativuus on tähän suhteessa lineaarinen. 

LinkedQueuen kaikki operaatiot ovat vakioaikaisia, sillä luokka pitää kirjaa sekä ensimmäisestä että viimeisestä alkiosta. 

###SlidingWindow###

Add-metodi lisää alkion sekä SlidingTable-taulukkoon, että viitteen LinkedQueue-jonoon. Nämä toimitukset ovat molemmat vakioaikaisia, siis metodin aikavaatimus on vakio. 

FindBestMatch-metodi suorittaa jokaiselle annetun taulukon alkumerkkiä vastaavan jonon alkiolle metodin matchLength, jonka aikavaatimus on lineaarinen suhteessa annetun taulukon pituuteen. Koska jonon pituus voi pahimmassa tapauksessa olla ikkunan koko, on metodin aikavaativuus siis suuruusluokkaa O(n*m), jossa n on ikkunan koko ja m suurin sallittu koodattavan tavujonon pituus. 

###Dictionary###

Lisättäessä Dictionary-hakemistoon tallennetaan itse tavujono taulukkoon ja indeksi tallennetaan binäärihakupuuhun. Binäärihakupuuhun lisättäessä addToTree-metodilla suoritetaan compareArrays-metodi log n kertaa, jossa n on puun koko. CompareArrays-metodin aikavaativuus on lineaarinen suhteessa lyhyemmän tavujonon pituuteen. Siis addToTree-metodin aikavaativuus on suuruusluokkaa O(log n * s), jossa n on puun koko (pahimmassa tapauksessa koko hakemiston koko) ja s lisättävän tavujonon pituus. SearchFromTree toimii vastaavasti, joten contains- ja get(byte[]) -metodien aikavaativuudet ovat myös O(log n * s), jossa s on haettavan taulukon pituus.   

###Yhteenveto###

LZW-purkajan ja -pakkaajan sekä LZ77-pakkaajan aikavaativuudet ovat verrannollisia SlidingWindow:n ja Dictionary:n hakutoimintojen aikavaativuuksiin, ne suorittavat tiedoston kokoon verrannollisen määrän näitä operaatioita. 

LZ77-purkajan aikavaativuus on T(k,n,m) = (k*m)+n , jossa k on tiedoston pituus, n ikkunan pituus ja m suurin sallittu osuman pituus. Koska ennen kuin SlidingTable on täynnä ei tavuja kirjoiteta, täytyy suorittaa lopuksi pollAll, josta tulee ylimääräiset n operaatioita. Muuten ikkunan pituus ei vaikuta purkajan toimintaan, sillä SlidingTablen muut metodit ovat vakioaikaisia.        
 