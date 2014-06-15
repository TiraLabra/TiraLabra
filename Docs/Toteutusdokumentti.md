#Toteutus#

##Yleisrakenne##

###Sis‰‰n- ja ulostulo sek‰ yleiset tyˆkalut###

Tiedoston lukemiseen ja kirjoittamiseen toteutin kolme luokkaa: Luokka IO lukee ja kirjoittaa tiedostoon tavu kerrallaan k‰ytt‰en javan FileInputStream- ja FileOutputStream -luokkia. Koska tavujonon koodatut esitykset ovat LZ77:n tapauksessa 9 tai 17 ja LZW tapauksessa 12 bitti‰ pitki‰, tarvitaan luokat InputBuffer ja OuputBuffer hallinnoimaan bittijonon "tavuttamista". Luokat pit‰v‰t yll‰ jonoja, joista kirjoitetaan tiedostoon kun seuraava tavu t‰yttyy. 

ByteAsBits-luokka hallinnoi tavun erilaisia esityksi‰. Ohjelmassa seikkailee kolmenlaisia esityksi‰ tavulle: javan byte-tietotyyppi(arvoiltaan -128 - 127), integer(0 - 256) sek‰ 8-paikkainen boolean-taulukko. ByteAsBits on tavua esitt‰v‰ olio, joka voidaan alustaa mill‰ tahansa kolmesta esityksest‰ ja joka voi palauttaa tavun kaikissa muodoissa.


ArrayUtils sis‰lt‰‰ staattisia metodeita byte- ja boolean-taulukkojen muunnoksiin.

###LZ77### 

LZ77-pakkaaja ja -purkaja k‰ytt‰v‰t luokkia SlidingTable, LinkedQueue ja SlidingWindow. 

SlidingTable on jonomaisesti toimiva p‰‰llekirjoittava taulukko. Taulukolla on maksimikoko, jonka t‰yttyess‰ aletaan p‰‰llekirjoittaa nollaindeksist‰ alkaen. P‰‰llekirjoitettava alkio palautetaan. Taulukosta voi hakea alkioita sek‰ suhteellisella indeksill‰, jolla nollaindeksi on viimeisen‰ lis‰tty‰ seuraava, tai todellisella indeksill‰, jolloin nollaindeksi on taulukon konkreettinen nollaindeksi. SlidingTablea k‰ytet‰‰n purkajassa, sek‰ pakkaajan apuluokassa SlidingWindow.

SlidingWindow on pakkaajan apuluokka, algoritmin liukuvan ikkunan implementaatio. Luokka yll‰pit‰‰ SlidingTable-taulukkoa sek‰ jonon jokaista tavun arvoa kohtaan. Kun alkio lis‰t‰‰n liukuikkunan, sen todellinen indeksi SlidingTable-taulukossa tallennetaan sit‰ vastaavaan jonoon. Kun alkio putoaa ikkunnasta, poistetaan sit‰ vastaavasta jonosta alkio. Kun haetaan parasta osumaa annetulle merkkijonolle, verrataan vain niit‰ merkkijonoja, jotka alkavat samalla tavulla.

LinkedQueue on linkitetyll‰ listalla toteutettu jono. Luokka k‰ytt‰‰ solmuja kuvaamaan luokkaa LinkedNode, sek‰ iteraattoria LinkedQueueIterator. 

###LZW###

LZW k‰ytt‰‰ luokkaa Dictionary, joka vastaa algoritmin hakemistoa. Dictionary tallentaa tavujonot taulukkoon, ja viitteet taulukon alkiohin bin‰‰rihakupuihin. Bin‰‰rihakupuita on jokaista alkumerkki‰ vastaan kuten SlidingWindow-luokassa jonoja. Bin‰‰ripuiden solmuja vastaa luokka BinaryTreeNode. Dictionary-oliosta voi hakea siis sek‰ tavujonon, ett‰ taulukkoindeksin mukaan. Sek‰ pakkaaja ett‰ purkaja k‰ytt‰v‰t samaa Dictionary-luokkaa. 

##Aika- ja tilavaativuudet##

Koska LZ77:n ikkunan ja LZW:n hakemiston koot ovat kiinteit‰, algoritmien aika- ja tilavaativuudet ovat suhteessa tiedoston kokoon aina lineaarisia, on mielekk‰‰mp‰ verrata niiden vaativuuksia suhteessa ikkunan tai hakemiston kokoon. T‰m‰ on olennaista, sill‰ pakkaussuhde paranee n‰it‰ kasvattaessa. 

###Aputietorakenteet###

SlidingTable-luokan add- ja get-metodit ovat vakioaikaisia, sill‰ ne viittaavat suoraan tiettyyn taulukon indeksiin joka saadaan perustoimituksilla. pollAll-metodi k‰y kaikki alkiot kerran l‰pi, siis sen aikavaatimus on lineaarinen suhteessa taulukon kokoon. Tilavaativuudet ovat myˆs vakiollisia, lukuunottamatta pollAll-metodia, joka luo uuden taulukon, jonka koko on SlidingTablen koko, siis tilavaativuus on t‰h‰n suhteessa lineaarinen. 

LinkedQueuen kaikki operaatiot ovat vakioaikaisia, sill‰ luokka pit‰‰ kirjaa sek‰ ensimm‰isest‰ ett‰ viimeisest‰ alkiosta. 

###SlidingWindow###

Add-metodi lis‰‰ alkion sek‰ SlidingTable-taulukkoon, ett‰ viitteen LinkedQueue-jonoon. N‰m‰ toimitukset ovat molemmat vakioaikaisia, siis metodin aikavaatimus on vakio. 

FindBestMatch-metodi suorittaa jokaiselle annetun taulukon alkumerkki‰ vastaavan jonon alkiolle metodin matchLength, jonka aikavaatimus on lineaarinen suhteessa annetun taulukon pituuteen. Koska jonon pituus voi pahimmassa tapauksessa olla ikkunan koko, on metodin aikavaativuus siis suuruusluokkaa O(n*m), jossa n on ikkunan koko ja m suurin sallittu koodattavan tavujonon pituus. 

###Dictionary###

Lis‰tt‰ess‰ Dictionary-hakemistoon tallennetaan itse tavujono taulukkoon ja indeksi tallennetaan bin‰‰rihakupuuhun. Bin‰‰rihakupuuhun lis‰tt‰ess‰ addToTree-metodilla suoritetaan compareArrays-metodi log n kertaa, jossa n on puun koko. CompareArrays-metodin aikavaativuus on lineaarinen suhteessa lyhyemm‰n tavujonon pituuteen. Siis addToTree-metodin aikavaativuus on suuruusluokkaa O(log n * s), jossa n on puun koko (pahimmassa tapauksessa koko hakemiston koko) ja s lis‰tt‰v‰n tavujonon pituus. SearchFromTree toimii vastaavasti, joten contains- ja get(byte[]) -metodien aikavaativuudet ovat myˆs O(log n * s), jossa s on haettavan taulukon pituus.   

###Yhteenveto###

LZW-purkajan ja -pakkaajan sek‰ LZ77-pakkaajan aikavaativuudet ovat verrannollisia SlidingWindow:n ja Dictionary:n hakutoimintojen aikavaativuuksiin, ne suorittavat tiedoston kokoon verrannollisen m‰‰r‰n n‰it‰ operaatioita. 

LZ77-purkajan aikavaativuus on T(k,n,m) = (k*m)+n , jossa k on tiedoston pituus, n ikkunan pituus ja m suurin sallittu osuman pituus. Koska ennen kuin SlidingTable on t‰ynn‰ ei tavuja kirjoiteta, t‰ytyy suorittaa lopuksi pollAll, josta tulee ylim‰‰r‰iset n operaatioita. Muuten ikkunan pituus ei vaikuta purkajan toimintaan, sill‰ SlidingTablen muut metodit ovat vakioaikaisia.        
 