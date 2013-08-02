Tarkoituksena on luoda kivi, paperi, sakset -tekoäly, joka kykenee saamaan ihmistä vastaan selvästi suuremman voittoprosentin kuin 50. Tekoäly perustuu vastustajan siirtojen seuraamiseen ja niistä oppimiseen. 

* * *

Aion toteuttaa tekoälyn muodostamalla pelipuun. Pelipuun jokainen solmu sisältää seuraavat tiedot: pelaajan peluu (kivi, paperi tai sakset), lopputulos (voitto, häviö tai tasapeli) ja kuinka monta kertaa kyseiseen solmuun on päädytty. Tällöin solmulla voi olla 9 lasta eli puun tilavaativuus on O(9^n), missä n on puun korkeus. Samalla tavalla siirron laskeminen vie aikaa O(n).
Toteutan myös pinorakenteen, jota pidän helpoimpana tapana hakea tieto edellisistä siirroista. 

* * *

Itse siirron päättämiseen teen algoritmin, joka käyttää painotettua todennäköisyyttä puusta saatujen tietojen perusteella. Mikäli pelattuja kierroksia on vähän, joudun kuitenkin käyttämään enemmän satunnaisuutta tai laskea pienemmällä syvyydellä. 

Tulen kokeilemaan tekoälyn toimivuutta erikokoisilla puun korkeuksilla, sekä lisäämällä erilaisia painotuksia peluisiin. 

* * * 

Ohjelmaan teen oman käyttöliittymän, jonka avulla pystyy pelaamaan tekoälyä vastaan tai laittaa tekoälyn pelaamaan toista tekoälyä vastaan. 
Pelitilanteessa tärkein syöte on Game-luokan playRound(int i), jolla ihminen pelaa kierroksen tekoälyä vastaan (0=kivi, 1=paperi, 2=sakset) ja tekoäly tekoälyä vastaan i kierrosta. 
