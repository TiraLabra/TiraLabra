## Testausdokumentti

Luokkien ja metodien toiminta on testattu erittäin kattavasti JUnit-yksikkötesteillä. Testien kattavuutta on seurattu työn toteutuksen aikana sekä Coberturalla että PIT:llä. Dokumentointikansiosta löytyy näiden tuottamat selostukset yksikkötesteistä.
Algoritmien suorituskykyä on testattu luomalla matriiseja, jotka sisältävät satunnaisia liukulukuja väliltä -100..100. Testauksessa käytettiin matriiseja, joiden koot ovat väliltä 100..500. Lisäksi jokaista syötteen kokoa on testattu useamman kerran, ja otettu näistä otoksista keskiarvo, jotta saadaan mahdollisimman tarkka arvio algoritmin kuluttamasta ajasta.
Suorituskykytestit voidaan toistaa ajamalla Main.java-tiedosto testien benchmark-paketista.
