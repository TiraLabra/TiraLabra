# Testausdokumentti

Testaus on toteutettu jUnit-testeillä. Tällä hetkellä jUnit-yksikkötestit testaavat luokkien sekä metodien toimintaa.

Hakualgoritmin toimintaa testattiin myös havainnollistavilla testeillä:
Kartalla o on aloituspiste, x loppupiste ja # on seinä. P-kirjaimilla on merkitty algoritmin laskema polku.

Molemmat puolet auki:

![Kuva3](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic3.png)

Yläreitti on tukittu:

![Kuva 1](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic1.png)

Alareitti on tukittu:

![Kuva 2](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic2.png)

Algoritmi osaa hakea polun oikein pienien muutostenkin jälkeen. 

Algoritmin toimintaa testattiin myös suuremmilla labyrinteilla:

![Kuva 4](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic4.png)

![Kuva 5](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic5.png)

Pienillä muutoksilla labyrinttiin polku muuttuu ja optimointi toimii: oikopolun tekemällä reitti on 4 askelta lyhyempi.


# Suorituskykytestaus

Suorituskykytestaus tehtiin suorittamalla ohjelma kymmenen kertaa ja laskemalla aikojen keskiarvo. Testasin A*-algoritmin toimintaa käyttäen valmiita tietorakenteita, jolloin ArrayListiä sekä PriorityQueueta käyttämällä suorituksen ajan keskiarvoksi tuli noin 4,8ms. 
Karttana toimii Kartta-luokan oletustoteutus joka näkyy dokumentin toisessa kuvassa. Testauksissa ei lopuksi tulostettu karttaa.
Kun ArrayListin korvasi MyList-luokalla tulokset olivat hieman heikommat, sillä suorituksen keskiarvoinen aika oli n. 6,8ms.


MyListia ja Javan omaa PriorityQueue-metodia käytettäessä tein testejä, jossa Astar-luokka ajetaan 100000 kertaa ja tämä toistetaan 5 kertaa, testitulokset olivat seuraavat:

100000 suorituksen ajat olivat seuraavat:

Operaatioon kului aikaa: 670ms, 513ms, 525ms, 492ms, 498ms.

Eli yhteensä 500000 ajoon kului aikaa 2698ms.
Aikaa keskimäärin per 100000 operaatiota kului 539ms.

***

Samat testit suuremmalla labyrintilla (kuva 5) tehdessä veivät jo huomattavasti enemmän aikaa:

100000 suorituksen ajat olivat seuraavat:

Operaatioon kului aikaa: 8009ms, 7409ms, 8208ms, 7882ms, 7513ms.

Eli yhteensä 500000 ajoon kului aikaa 39021ms.
Aikaa keskimäärin per 100000 operaatiota kului 7804ms.

Sekä kartan koko että polun pituuskin oli moninkertainen; pienessä kartassa 3 askelta, suuressa 40. Täten pidempi suoritusaika oli odotettavissa.

***

Seuraavassa testissä ajoin ohjelman Euclidean distance-heuristiikkaa käyttäen 100000 kertaa, ja toistin tämän 5 kertaa. Tein toisen testin javan omalla prioriteettijonolla ja toisen omalla toteutuksellani. Tulokset olivat seuraavat:

| MyPriorityQueue | Javan priorityqueue |
|-----------------|---------------------|
| 77231           | 54906               |
| 79066           | 54983               |
| 79855           | 54983               |
| 78546           | 54919               |
| 76546           | 55539               |

| Keskiarvo:      | Keskiarvo:          |
| --------------- | ------------------- |
| 78249           | 55045               |

| Yhteensä:       | Yhteensä:           |
| --------------- | ------------------- |
| 391246          | 275227              |


Javan prioriteettijonoa käyttämällä suoritusnopeus on huomattavasti parempi. Toisaalta yksittäisissä suorituksilla nopeudella ei ole niin paljoa väliä, jos yksittäisen suorituksen nopeus on noin 0,8ms.

***

Seuraavassa testissä käytettiin jokaista heuristiikkaa erikseen samassa kartassa(map2) ja ajettiin ne 10000 kertaa, ja toistettiin tämä viisi kertaa. Karttana toimi map2, joka on avoin kartta kahden pisteen välillä.

### Yksittäiset testit

| Euclidean distance | Manhattan distance | Diagonal distance | Djikstra
| ------------------ | ------------------ | ----------------- | --------
| 8153               | 7045               | 7204              | 739
| 7897               | 7123               | 7188              | 754
| 7889               | 7085               | 7324              | 800
| 7893               | 7106               | 7112              | 725
| 7741               | 7147               | 7313              | 754

### Keskiarvo

| Euclidean distance | Manhattan distance | Diagonal distance | Djikstra
| ------------------ | ------------------ | ----------------- | --------
| 7915               | 7101               | 7228              | 754

### Yhteensä

| Euclidean distance | Manhattan distance | Diagonal distance | Djikstra
| ------------------ | ------------------ | ----------------- | --------
| 39575              | 35506              | 36142             | 3774

Nopein algoritmeistä oli odotetusti dijkstran algoritmi, jossa laskentaa ei tarvitse ollenkaan suorittaa. Tähän vaikutti erityisesti kentän muoto, sillä siinä ei ollut lainkaan esteitä jolloin algoritmin olisi tarvinnut arvioida reittiä uudestaan. Hitain oli myös odotetusti Euclidean distance, sillä eksaktit laskutoimitukset vievät enemmän aikaa kuin Manhattan- ja Diagonal distancen arviot.

***

Tässä testissä ajettiin ohjelman map3-karttaa joka ajettiin 10000 kertaa, ja toistettiin tämä viisi kertaa. Kartassa aloituspiste on ympyröity seinillä.

### Keskiarvo

| Euclidean distance | Manhattan distance | Diagonal distance | Djikstra
| ------------------ | ------------------ | ----------------- | --------
| 4477               | 5621               | 5623              | 1884

### Yhteensä

| Euclidean distance | Manhattan distance | Diagonal distance | Djikstra
| ------------------ | ------------------ | ----------------- | --------
| 22388              | 28109              | 28116             | 9421

Edelliseen karttaan verrattuna muiden algoritmien suoritusajat nopeutuivat, mutta dijkstran nopeus oli noin kolme kertaa hitaampi. Tämä johtuu siitä, ettei noodeja pisteytetä vaan haetaan niin kauan, kunnes sopiva löytyy. Euclidean distance oli myös hieman nopeampi kuin muut algoritmit.