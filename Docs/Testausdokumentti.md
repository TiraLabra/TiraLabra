# Testaus

Testaukseen käytetään JUnit-testejä sekä PIT-mutaatiotestausta ongelma-
kohtien löytämiseen.

Suorituskykymitauksia.xlsx -tiedostossa on testituloksia eri osa-alueiden
suorituskyvystä 200 - 24320 solmun tapauksissa. Käytetyt karttatiedostot
löytyvät Tiralabra_maven-hakemistosta nimillä suorituskykykartta[1-5].txt.

## Astar-algoritmin testaus

Algoritmia testataan antamalla syötteeksi ennalta määritetty verkko, jonka
lyhimmän polun voi määrittää kommenttiin kirjoitetusta 2D-esityksestä.

Ensin testataan konstruktori ja solmujen alustuminen muutamalla 
yksinkertaisella testillä.

Kun perusteet on testattu, tehdään polunetsintä kolmesta verkosta:
1. yksinkertainen verkko, jossa aloitus- ja kohdesolmun välissä on seinää
2. aloitus- ja kohdesolmun välissä on vaikeakulkuista maastoa, lyhin polku
   ei ole linnuntietä lyhin, vaan kevein
3. aloitus- ja kohdesolmun välissä on seinää siten, että algoritmi haluaa
   mennä suoraan kohti, mutta joutuu huomaamaan, että seinä täytyy kiertää
   alakautta

## Tietorakenteiden testaus

### Verkko
Testeissä käydään läpi solmun lisäys ja haku. Vieruslistojen luonti 
testataan 2D-esimerkkiverkolla.

### Solmu
Solmusta testataan konstruktori, getterit, setterit ja vierussolmun lisäys. 
Vierussolmun lisäyksen yhteydessä testataan kaaripainojen haku myös siinä 
erikoistapauksessa, että annettu solmu ei ole tämän solmun vieruslistalla.

### Linkitetty lista
Tietorakenteelle ei ole erikseen omia testejä, vaan testaus sisältyy muiden
luokkien testeihin. Luokat ja niiden testit tehtiin ensin ArrayList:eillä. 
ArrayList:it korvattiin linkitetyllä listalla ja PIT kertoo, että jokainen 
rivi on katettu ja kaikki mutaatiot tapettu.

### Minimikeko
Kekoa testataan ensin luomalla pari kekoa, joista on piirretty puu testin 
kommentteihin. Keosta poistetaan solmuja, joiden pitäisi poistua oikeassa 
järjestyksessä.

Erikoistapauksiakin testataan, kuten tapaus, jossa heapify käsittelee vain 
vasenta lasta, sekä erittäin todennäköinen tilanne, jossa samoja arvoja on 
puussa useampia. 

Yksittäiset toiminnot testataan erikseen, kuten arvon pienennys ja keon 
alkioiden luku taulukon indeksien avulla. Solmun indeksin palautus testataan
keon yhteydessä.

## Kartanlukija-apuohjelman testaus

Kartanlukijaa testataan samoilla kolmella testillä kuin Astar-algoritmiakin,
mutta verkot on talletettu kartanlukijaTesti(1 - 4).txt -tiedostoihin. Näiden
lisäksi on yksi testi, jossa tiedostosta luetaan kaikilla numeroarvoilla
varustettu kartta.