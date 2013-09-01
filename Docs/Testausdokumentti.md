TESTAUSDOKUMENTTI

TiraLabrassa tehtyyn koodiin on tehty JUnit testit, joten ne on helppo toistaa.

KomboEtsijaTestit testaa tarkasti BFSn kolme ehtometodia ja lisäksi muutamia eri tilanteita kokonaisuuksina.

Romahtajan testit ovat vajaammat mutta myös sen algoritmi on yksinkertaisempi. Testit testaavat erilaisia tilanteita.

Molemmat näistä on myös testattu visuaalisesti hidastamalla animaatiota pelin aikava, ilman bugeja.



Nopeustestaus tehtiin jotta omien tietorakenteiden tehokkuutta voitiin verrata javan omiin, ja testien perusteella
omat tietorakenteet pärjäävät kyllä. 
Nopeustesteissä luodaan NxM ruudukko joka taytetään satunnaisesti ja Komboetsija käy sen lapi.
Sen jalkeen loydetyt tuhotaan ja romahdutetaan uudet tilalle. Nyt komboetsija käy lapi uudestaan
muuttuneet koordinaatit, ja palauttaa mahdolliset kombot ja näin jatkuu kunnes peliruudukko on "stabiili".


Omille tietorakenteille on kuvaavat perustestit jotka luotiin TDD tyylillä ennen koodia.