TESTAUSDOKUMENTTI

TiraLabrassa tehtyyn koodiin on tehty JUnit testit, joten ne on helppo toistaa.

KomboEtsijaTestit testaa tarkasti BFSn kolme ehtometodia ja lis�ksi muutamia eri tilanteita kokonaisuuksina.

Romahtajan testit ovat vajaammat mutta my�s sen algoritmi on yksinkertaisempi. Testit testaavat erilaisia tilanteita.

Molemmat n�ist� on my�s testattu visuaalisesti hidastamalla animaatiota pelin aikava, ilman bugeja.



Nopeustestaus tehtiin jotta omien tietorakenteiden tehokkuutta voitiin verrata javan omiin, ja testien perusteella
omat tietorakenteet p�rj��v�t kyll�. 
Nopeustesteiss� luodaan NxM ruudukko joka taytet��n satunnaisesti ja Komboetsija k�y sen lapi.
Sen jalkeen loydetyt tuhotaan ja romahdutetaan uudet tilalle. Nyt komboetsija k�y lapi uudestaan
muuttuneet koordinaatit, ja palauttaa mahdolliset kombot ja n�in jatkuu kunnes peliruudukko on "stabiili".


Omille tietorakenteille on kuvaavat perustestit jotka luotiin TDD tyylill� ennen koodia.