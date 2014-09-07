## Testausdokumentti

Luokkien ja metodien toiminta on testattu erittäin kattavasti JUnit-yksikkötesteillä. Testien kattavuutta on seurattu työn toteutuksen aikana sekä Coberturalla että PIT:llä. Dokumentointikansiosta löytyy näiden tuottamat selostukset yksikkötesteistä.

Algoritmien suorituskykyä on testattu luomalla reaalisia neliömatriiseja, jotka sisältävät satunnaisia liukulukuja väliltä -100..100. Testauksessa käytettiin matriiseja, joiden koot ovat väliltä 10..500. Lisäksi jokaista syötteen kokoa on testattu useamman kerran (10), ja otettu näistä otoksista keskiarvo, jotta saadaan mahdollisimman tarkka arvio algoritmin kuluttamasta ajasta. Suorituskykytestauksen main-metodi tulostaa syötteen koot ja algoritmin kuluttaman ajan nanosekunteina tiedostoon. Näistä tiedostoista on myöhemmin piirretty kuvat käyttämällä gnuplottia.

![Kertolasku](matrixcalc/strassen.png?raw=true)
Huomataan, että tavallinen neliömatriisien kertolasku (punaiset pisteet) kuuluu aikavaativuusluokkaan O(n^3). Strassenin algoritmi neliömatriisien kertolaskulle (vihreät pisteet) sen sijaan käyttäytyy kummallisesti. Tämä johtuu siitä, että kyseinen Strassenin toteutus luo aina kaksi uutta neliömatriisia, joiden dimensiot ovat seuraavia kahden potensseja ja sisältö sama kuin alkuperäisten kerrottavien matriisien, ja suorittaa algoritmin näille kahdelle uudelle matriisille. Tämän takia yllä oleva kuvaaja "hyppää" jokaisen kahden potenssin kohdalla. Siis kerrottaessa yhteen esimerkiksi 257x257-matriiseja kerrotaankin 512x512-matriiseja. Kuitenkin, esimerkiksi 500x500-matriiseille Strassenin algoritmi on jo paljon nopeampi kuin naiivi matriisien kertolasku, mitä voidaan pitää harjoitustyön kannalta onnistumisena.

![Determinantti](matrixcalc/determinant.png?raw=true)
Neliömatriisin determinantin laskeminen kuuluu myös aikavaativuusluokkaan O(n^3), mikä näkyy yllä olevasta kuvasta kauniina paraabelina. Determinantin laskeminen käyttää matriisin LU-hajotelmaa.

![Käänteismatriisi](matrixcalc/inversematrix.png?raw=true)
Neliömatriisin käänteismatriisin käyttää myös LU-hajotelmaa, joten sekin kuuluu aikavaativuusluokkaan O(n^3). Tämän vuoksi tämäkin graafinen esitys on paraabeli.

Suorituskykytestit voidaan toistaa ajamalla Main.java-tiedosto testien benchmark-paketista.
