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

Samat testit suuremmalla labyrintilla (kuva 5) tehdessä veivät jo huomattavasti enemmän aikaa:

100000 suorituksen ajat olivat seuraavat:

Operaatioon kului aikaa: 8009ms, 7409ms, 8208ms, 7882ms, 7513ms.

Eli yhteensä 500000 ajoon kului aikaa 39021ms.
Aikaa keskimäärin per 100000 operaatiota kului 7804ms.

Sekä kartan koko että polun pituuskin oli moninkertainen; pienessä kartassa 3 askelta, suuressa 40. Täten pidempi suoritusaika oli odotettavissa.