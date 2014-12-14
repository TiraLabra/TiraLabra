# Toteutusdokumentti

## Ohjelman yleisrakenne
Ohjelma käynnistetään ajamalla pääluokka App.java. Ohjelmaan toteutetut tietorakenteet ovat linkitetty lista sekä prioriteettijono.

## Saavutetut aika- ja tilavaativuudet
### Linked List
Harjoitustyöhön sisältyy toteutus linkitetystä listasta. Linkitetty lista koostuu MyList-luokasta jossa on listan implementaatio sekä ListNode-luokasta, joka on toteutus listan solmuille.

* search:
Aikavaativuus on `O(n)`: listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* add:
Aikavaativuus on `O(1)`: uusi solmu tulee aina listan alkuun, jolloin aika on vakio.
* contains
Aikavaativuus on `O(n)`: listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* remove:
Aikavaativuus on `O(n)`: remove kutsuu ensin search-metodia jonka avulla haluttu solmu löydetään. Tämän jälkeinen poisto-operaatio on aikavaatimuksiltaan vakio.

Listan tilavaativuus on `O(n)`, listan koko riippuu sen alkioiden määrästä.

### Prioriteettijono

A-star algoritmin avointen naapurisolmujen

* insert:
Aikavaativuus on `O(log n)`: listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* min:
Aikavaativuus on `O(1)`: palautetaan vain jonon ensimmäinen alkio.
* heapify:
Aikavaativuus on `O(log n)`: heapify-metodi on vakioaikainen, mutta se kutsuu itseään. Pahimmassa tapauksessa binäärikekoa pitää käydä läpi sen koko korkeuden verran.
* contains:
Aikavaativuus on `O(n)`: Jonoa käydään läpi n indeksiä, kunnes oikea solmu löytyy.
* bubbleUp:
Aikavaativuus on `O(n)`: Jonoa käydään läpi n vanhempaa jotka ovat arvoltaan suurempia kuin käsiteltävä solmu.
* decreaseCost:
Aikavaativuus on `O(n)`: Arvon vaihtamisen aikavaativuus on A(1), mutta metodi kutsuu bubbleUp-metodia.
* removeNode:
Aikavaativuus on `O(log n)`: removeNode käyttää bubbleUp-, decreaseCost- ja heapify-metodia, jonka aikavaativus on O(log n).
* deleteMinimum:
Aikavaativuus on `O(log n)`: deleteMinimum käyttää heapify-metodia.


Prioriteettijonon aikavaativuus on siis huonoimmassa tapauksessa `O(log n)`.



## Suorituskyky- ja O-analyysivertailu
Testausdokumentista löytyy testejä jotka on toteutettu algoritmin eri heuristiikoilla.

* Euclidean distance
Heuristiikka on hitaampi, mutta toisaalta myös varmempi, sillä Manhattan- ja Diagonal distanceen verrattuna heuristiikka tekee ajallisesti
kalliimaan Math.abs-laskuoperaation.

* Manhattan distance
Tarkkojen laskentaoperaatioiden sijaan seuraavasta hyvästä siirrosta tehdään arvio, jonka mukaan edetään. On hieman epävarmempi
 kuin Euclidean, mutta nopeampi sillä kalliita laskuoperaatioita ei tehdä.

* Diagonal distance
On pääpiirteiltään sama kuin Manhattan distance, mutta on erikoistunut karttoihin, joissa on mahdollista liikkua vinosti. Tässä toteutuksessa liikkeen suunnalla ei ole merkitystä liikkeen kustannuksen kannalta, joten Manhattan ja diagonal toimivat suurinpiirtein samalla lailla.

* Dijkstra's algorithm
Dijkstran algoritmissa ei ole heuristiikkaa ollenkaan, joten algoritmi käy läpi kaikki Nodet yksitellen niin kauan, että
loppupiste löytyy. Tämän takia Dijkstra on yleensä huomattavasti hitaampi, kuin muut algoritmit.


## Minkälaisilla syötteillä ohjelma toimii hyvin? Miksi?
Mitä vähemmän polussa on esteitä ja mitä pienempi kartta on, sitä nopeammin ohjelma toimii. Mitä vähemmän valinnaisuutta, sitä vähemmän nodeja on läpikäytävänä, ja siten ohjelma toimii myös nopeammin. Lisäksi syötteitä voidaan optimisoida käyttämällä erilaisia heuristiikkoja ja painotuksia muuttamalla.

## Minkälaisilla syötteillä ohjelma toimii huonosti? Miksi?
Ohjelma toimii huonosti kartoissa, jossa loppupisteeseen on useampia yhtä nopeita reittejä. Tällöin algoritmien suoritus ei ole niin nopeaa, sillä algoritmi tutkii molemmat (tai kaikki) mahdolliset reitit mikäli niiden heuristiset arvot ovat samat. Tällöin algoritmi tutkii turhaan pahimmassa tapauksessa jopa kaikki mahdollisimmat lyhyimmät polut ennen suorituksen loppumista ja maaliin saapumista. Tämä ongelma voitaisiin ratkaista käyttämällä ns. "tie-breaking"-heuristiikkoja, joissa heuristisia arvoja pyöristetään hyvin pienesti, jolloin Astar suosii reittejä, jotka lähestyvät loppupistettä. Tie breaking-heuristiikan puuttuminen selittää myös hieman omituisia reittejä, joita Astar tekee välillä. Esimerkiksi suorilla poluilla se suosii välillä vinoliikkeitä sivuttaisliikkeiden sijaan. Lisäksi pysty- ja vaakasuoraiset sekä viistoliikkeet ovat kustannuksiltaan samanarvoisia, joten algoritmi toimii välillä "ongelmallisesti":


          Euclidean distance:
          ##############################
          #o___________________________#
          #_P__________________________#
          #__PPPPP_____________________#
          #_______P____________________#
          #________P_________P_________#
          #_________P_PP___PP_PP_______#
          #__________P__P_P_____P_P____#
          #______________P_______P_PP__#
          #__________________________x_#
          ##############################
          Path length was 25 steps.
          Runtime was 10ms.

          Manhattan distance:
          ##############################
          #o___________________________#
          #_P__________________________#
          #__P_________________________#
          #___P________________________#
          #____P_______________________#
          #_____P______________________#
          #______P_____________________#
          #_______P____________________#
          #________PPPPPPPPPPPPPPPPPPx_#
          ##############################
          Path length was 25 steps.
          Runtime was 7ms.

          Diagonal distance:
          ##############################
          #o___________________________#
          #_P__________________________#
          #__P_________________________#
          #___P________________________#
          #____P_______________________#
          #_____P______________________#
          #______P_____________________#
          #_______P____________________#
          #________PPPPPPPPPPPPPPPPPPx_#
          ##############################
          Path length was 25 steps.
          Runtime was 7ms.

          Dijkstra:
          ##############################
          #o___________________________#
          #_P__________________________#
          #__P_________________________#
          #___P________________________#
          #____P_______________________#
          #_____P______________________#
          #______P_____________________#
          #_______P___P________________#
          #________PPP_PPPPPPPPPPPPPPx_#
          ##############################
          Path length was 25 steps.
          Runtime was 15ms.

Toisaalta polun pituus jokaisessa on sama, joten jokainen niistä on periaatteessa validi lyhyin reitti. Tie breaking-heuristiikoilla algoritmin toimintaa näissä tilanteissa voitaisiin parantaa.

Ohjelmasta puuttuu myös tarkistus, että alku- ja loppupisteet varmasti asetetaan ja että algoritmi pysäytetään, mikäli polkua loppupisteeseen ei löydy. Tällöin ohjelma voi jäädä ikuiseen looppiin, jos syötteissä on virheitä.


## Pseudokoodin ja todellisen koodin väliset ongelmat (esim. kuinka usein algoritmi joutuu muokkaamaan listaa muualta kuin alusta tai lopusta). Miten nämä voidaan parhaiten ottaa huomioon tietorakenteissa ja ohjelmanyleisessä toiminnallisuudessa?

Linkitettyä listaa voisi muokata niin, että hakuoperaatiota search `O(n)` ei lainkaan tarvitsisi käyttää poistossa, jolloin listan aikavaatimus olisi vakio `O(1). Tämä voitaisiin toteuttaa esimerkiksi sillä, että (kartta)Nodelle tallennettaisiin sen indeksi listassa, jolloin hakuoperaatiota ei tarvittaisi lainkaan. Poisto-operaation aikana noden previous voitaisiin asettaa nextiksi ja päinvastoin, jolloin aikavaativuus ei olisi lainkaan riippuvainen listan läpikäynnistä.

Prioriteettijonosta muualta kuin keon huipulta poistaminen on hidasta. Kekoehdon ylläpitämisen vuoksi poistoa ei voi toteuttaa suoraan: aluksi pitää hakea noden indeksi, sen jälkeen vähentää sen arvoa, käyttää bubbleUp-metodia sen nostattamiseksi kunnes se on keon huipulla ja vasta sitten Noden poistaminen onnistuu. Tätä voitaisiin optimoida esimerkiksi tutkimalla, olisiko Fibonaccin keko parempi toteutusvaihtoehto.

Lisäksi esimerkiksi Node voisi säilyttää tietoa indeksistään Prioriteettijonossa, jolloin hakuoperaatiota search ei tarvittaisi, vaan arvon vähentäminen, bubbleUp jne toimisivat suoraan. Toisaalta indeksi pitäisi silloin muistaa tallentaa aina jokaisen operaation yhteydessä, jossa Nodejen paikka vaihtuu(insert, delete, heapify, bubbleUp jne). Tämä voisi olla kova refaktoroinnin paikka.

## Testaa ja raportoi muutaman optimoinnin/muutoksen tuoma vaihtelu ohjelman suorituskykyyn.
Testausdokumentista lopusta löytyy jonkin verran tietoa, miten ohjelma käyttyy eri heuristiikoilla. Eri kartoissa eri heuristiikkoja käyttämällä voidaan saada optimoitua hyvinkin paljon ohjelman suoritusaikaa. Yleensäottaen voidaan sanoa että tässä toteutuksessa Diagonal/Manhattan distance toimii heuristiikoista nopeiten, sillä liikkeet joka suuntaan ovat kustannuksiltaan samat. Dijkstra on yleensä hitain, sillä se käy kaikki Nodet läpi ottamatta huomioimatta heuristiikkaa lainkaan.

Testasin suorituskykyraportissani aiemmin myös Javan omaa priorityqueue-luokkaa ja vertasin suoritusaikoja omaan toteutukseeni prioriteettijonosta, ja totesin että oman toteutukseni ajat olivat heikommat. Yksittäisissä suorituksissa erot eivät olleet huimat, mutta suuremmalla laskennalla ero on kuitenkin huomattava.

Suurilla kartoilla suoritusaika moninkertaistuu, sillä mitä enemmän Nodeja on, sitä hitaampaa open-prioriteettijonon käsittely on puun kasvattassa kokoaan.



## Voiko algoritmin aikavaativuutta parantaa lisäämällä tilavaativuutta? Miten?
Ei oikeastaan, sillä Astarin tilavaativuus on huonoimmassa tapauksessa eksponentiaalinen, ja täten se tarkoittaa myös että aikavaativuuskin on myös eksponentiaalinen.
Toisaalta jos heuristiikkalaskentaa ei oteta huomioon, A\* voi parhaassa mahdollisessa tapauksessa optimiheuristiikalla olla aika- ja tilavaativuudeltaan `O(n)`, jossa n on polun pituus.

## Työn mahdolliset puutteet ja parannusehdotukset
Työn voisi toteuttaa niin, että ohjelmaan voisi lisätä omia karttoja sekä alun App.java on tällä hetkellä toteutukseltaan hieman huono,
sillä kartan tai heuristiikan vaihtaminen onnistuu pelkästään muokkaamalla App.java-tiedostoa.

Lisäksi voisi lisätä asetuksen, jossa väli-ilmansuuntiin liikkuminen poistettaisiin, jolloin Manhattan distancella ja Diagonal distancen eroja voisi tutkia. Algoritmi ei myöskään ole täydellinen, sillä vaikka optimaaliset polut löytyvätkin ohjelma tekee jostain syystä liian mutkikkaita polkuja. Tämä pitäisi tutkia ja korjata.


## Lähteet
http://www.policyalmanac.org/Finnish/astar_fin.pdf

http://www.redblobgames.com/pathfinding/a-star/introduction.html

http://en.wikipedia.org/wiki/A*_search_algorithm

http://www.cokeandcode.com/main/tutorials/path-finding/

Tietorakenteet ja algoritmit-kurssin luentomateriaali
