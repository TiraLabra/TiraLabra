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

# Yksikkötestit

Yksikkötestaus toteutettiin jUnitilla. Jokaisen luokan (paitsi App-luokan) toimintaa testattiin jUnit-testeillä joilla pyrin testaamaan jokaisen luokan ydintoiminnallisuutta. En testannut lainkaan itsestäänselviä metodeja kuten gettereitä ja setteritä. Testasin myös enemmän sitä, että metodit toimivat kuten piti, ja jätin pienemmälle huomiolle esimerkiksi vialliset syötteet ja muut epäonnistumistapaukset. ListNode-luokalla ei ollut gettereiden ja settereiden lisäksi ollenkaan omaa toiminnallisuutta, joten en testannut luokan toimintaa.

Parannuksena yksikkötesteihin olisi voinut toteuttaa myös enemmän viallisilla syötteillä olevia testejä sekä jUnit-testejä, joissa myös suoritusaikaa olisi mitattu. Toteutin suorituskykytestauksen tosin erikseen.


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

Seuraavassa testissä käytettiin jokaista heuristiikkaa erikseen samassa kartassa(map2) ja ajettiin ne 10000 kertaa, ja toistettiin tämä viisi kertaa. Karttana toimi map2, joka on avoin kahden pisteen välillä.

## Huom! Näistä testeistä löytyi bugi, joka vaikutti suoritusaikoihin joten ajat eivät välttämättä olleet korrektit.
## Yksittäiset testit


| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 8153               | 7045               | 7204              | 739
| 7897               | 7123               | 7188              | 754
| 7889               | 7085               | 7324              | 800
| 7893               | 7106               | 7112              | 725
| 7741               | 7147               | 7313              | 754

### Keskiarvo

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 7915               | 7101               | 7228              | 754

### Yhteensä

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 39575              | 35506              | 36142             | 3774

Nopein algoritmeistä oli odotetusti dijkstran algoritmi, jossa laskentaa ei tarvitse ollenkaan suorittaa. Tähän vaikutti erityisesti kentän muoto, sillä siinä ei ollut lainkaan esteitä jolloin algoritmin olisi tarvinnut arvioida reittiä uudestaan. Hitain oli myös odotetusti Euclidean distance, sillä eksaktit laskutoimitukset vievät enemmän aikaa kuin Manhattan- ja Diagonal distancen arviot.

***

Tässä testissä ajettiin ohjelman map3-karttaa joka ajettiin 10000 kertaa, ja toistettiin tämä viisi kertaa. Kartassa aloituspiste on ympyröity seinillä.

### Keskiarvo

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 4477               | 5621               | 5623              | 1884

### Yhteensä

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 22388              | 28109              | 28116             | 9421

Edelliseen karttaan verrattuna muiden algoritmien suoritusajat nopeutuivat, mutta dijkstran nopeus oli noin kolme kertaa hitaampi. Tämä johtuu siitä, ettei noodeja pisteytetä vaan haetaan niin kauan, kunnes sopiva löytyy. Euclidean distance oli myös hieman nopeampi kuin muut algoritmit.


## Suorituskykytestaus 2:

Edellisten testien jälkeen huomasin koodissa ilmenneen bugin, jonka takia heuristiikkojen laskeminen ei toiminut täysin oikein. Tein siis uudet suorituskykytestit, joiden tulokset ovat seuraavat:

Testissä käytettiin jokaista heuristiikkaa erikseen samassa kartassa(map3) ja ajettiin ne 10000 kertaa, ja toistettiin tämä viisi kertaa. Karttana toimi map3:

```java

        public static String map3() { 
        return "##############################\n" + 
               "#____________________________#\n" + 
               "#_________##############_____#\n" + 
               "#______________________#_____#\n" + 
               "#___________o__________#__x__#\n" + 
               "#______________________#_____#\n" + 
               "#______________________#_____#\n" + 
               "#_________##############_____#\n" + 
               "#____________________________#\n" + 
               "#____________________________#\n" + 
               "##############################"; }

```


### Suoritusajan keskiarvo

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 1567               | 1176               | 1176              | 4378
| 1414 | 1165 | 1184 | 4293

### Yhteensä

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 7836              | 5881              | 5880             | 21892
| 7071 | 5825 | 5921 | 21465

Edellisiin testeihin verrattuna suoritusajat ovat muilla heuristiikoilla huomattavasti nopeammat, paitsi Dijkstran.
Tämä selittyy sillä, että Dijkstran algoritmi joutuu käymään paljon suuremman määrän nodeja.

Muiden tulokset ovat odotettavat: Euclidean distance on Manhattan- ja Diagonal distancea hieman hitaampi kalliimman laskuoperaationsa takia. Manhattan ja Diagonal ovat tässä toteutuksessa periaatteessa sama algoritmi.


![Kuva7](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic7.png)

***

Testissä käytettiin jokaista heuristiikkaa erikseen samassa kartassa(map2) ja ajettiin ne 10000 kertaa, ja toistettiin tämä viisi kertaa.

```java

public static String map2() {
    return "##############################\n" +
            "#o___________________________#\n" +
            "#____________________________#\n" +
            "#____________________________#\n" +
            "#____________________________#\n" +
            "#____________________________#\n" +
            "#____________________________#\n" +
            "#____________________________#\n" +
            "#____________________________#\n" +
            "#__________________________x_#\n" +
            "##############################";
}
```



### Suoritusajan keskiarvo

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 2141               | 273            | 267             | 6035
| 2175 | 261 | 260 | 5841

### Yhteensä

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 10706              | 1368              | 1338             | 30177
| 10879 | 1309 | 1302 | 29209

Avoimessa kartassa Manhattan ja Diagonal distance ovat jälleen nopeimmat. Dijkstra on edelleen hitain, sillä sen pitää käydä läpi eniten nodeja päästäkseen maaliin. Erityisen huomioitavaa on
Euclidean distancen ja Manhattan/Diagonalin ero: Heuristiikkojen välillä on lähes kymmenkertainen nopeusero.
Jokaisessa suorituksessa polun pituus oli sama, 25 askelta.




Seuraavan testin ajoin suurella kartalla map4, samoilla spekseillä kuin aiemmin:

```java

            public static String map4() {
                         return "##########################################################################\n" +
                                "#o____#________________________________________________________#_________#\n" +
                                "#_____#_####################################################_###_#___###_#\n" +
                                "#_____#_#___________#________________________________________#_#_#___#___#\n" +
                                "#_______#___________#________________________________________#_#_#___#_###\n" +
                                "#######_#___________#_########################################_#_#___#_###\n" +
                                "#_______#___________#____________________________________________#___#___#\n" +
                                "#_#######________________________________________________________#___###_#\n" +
                                "#_______#___________#____________________________________________#_______#\n" +
                                "#######_#___________#____________________________________________#___###_#\n" +
                                "#_______#___________#________________________________________#_#_#___#___#\n" +
                                "#_#######___________#________________________________________#_#_#___#___#\n" +
                                "#_______#_####################################################_#_#___#___#\n" +
                                "#______________________________________________________________#_____#__x#\n" +
                                "##########################################################################\n";
            }
```

### Suoritusajan keskiarvo

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 41718               | 37060            | 37235             | 36730

### Yhteensä

| Euclidean distance | Manhattan distance | Diagonal distance | Dijkstra
| ------------------ | ------------------ | ----------------- | --------
| 208591              | 185300              | 186177            | 183650

Suurella kartalla suoritusajat olivat jo huomattavasti hitaammat (Euclideanilla n. 4ms/suorituskerta, muilla n. 3ms/suoritus). Jokainen heuristiikka laski optimaalisen polun pituudeksi 101 askelta. Yllättäen nopeimmiten suoriutui Dijkstran algoritmi, vaikka sokkeloimaisessa rakenteessa on paljon "vapaita" nodeja mitä käydä läpi. Ei-niin-yllättävästi Euclidean distance oli taas hitain. Mitä enemmän algoritmilla on läpikäytäviä Nodeja, sitä pidemmän ajan suoritus vie.

![Kuva6](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic6.png)
