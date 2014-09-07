## Toteutusdokumentti

Ohjelma jaetaan hyvän ohjelmointikäytännön mukaisesti erillisiin pakkauksiin. Näistä yksi (math) sisältää kaikki tarvittavat tietorakenteet, eli luokat kompleksiluvuille (Complex.java), reaaliluku- (RealMatrix.java) ja kompleksilukumatriiseille (ComplexMatrix.java). Lisäksi näiden luokkien sisällä on toteutettu työn kannalta naiivit algoritmit, eli peruslaskutoimitukset. Toinen paketti (algorithms) sen sijaan sisältää monimutkaisemmat algoritmit, eli Strassenin algoritmin (Strassen.java) ja LU-hajotelman (LUDecomposition.java). Tämä paketti jaetaan kahteen osaan, joista yksi (algorithms.real) käsittelee reaalisia ja toinen (algorithms.complex) kompleksisia matriiseja. Lisäksi ohjelma sisältää erillisessä paketissa (cli) komentorivikäyttöliittymän (CLI.java), joka käynnistetään Main-luokan main-metodista.

Matriisien yhteen- ja vähennyslasku on pseudokoodina seuraavanlainen:

```
for (i = 0; i < rows; i++) {
  for (j = 0; j < cols; j++) {
    C[i][j] = A[i][j] +/- B[i][j]
  }
}
```

Huomataan, että kyseiset operaatiot sisältävät kaksi sisäkkäistä for-looppia, jotka käyvät läpi matriisien rivit ja sarakkeet. Tällöin näiden operaatioiden aikavaativuus on O(mn), missä m on matriisin rivien ja n matriisin sarakkeiden määrä. Myös skalaarikertolaskun ja transpoosin algoritmit käyvät for-loopeilla matriisin alkiot läpi, jolloin näidenkin aikavaativuus on sama O(mn).

Kahden matriisin kertolasku on taas pseudokoodina seuraava:

```
multiply(A, B) {
  for (i = 0; i < A.rows; i++) {
    for (j = 0; j < B.cols; j++) {
      for (k = 0; k < A.cols; k++) {
        C[i][j] = C[i][j] + (A[i][k] * B[k][j])
      }
    }
  }
}
```

Tässä taas huomataan, että algoritmi sisältää kolme for-looppia, joista ensimmäinen käy läpi ensimmäisen matriisin rivit, toinen toisen matriisin sarakkeet, ja kolmas taas ensimmäisen matriisin sarakkeet. Siis, jos kerrotaan keskenään m x n- ja n x p-matriiseja, kertolaskun aikavaativuus on O(mnp).

Strassenin algoritmin rekursioyhtälöstä seuraa, että sen aikavaativuus on luokkaa O(n^(log_2(7))) (Cormen s. 80), mutta tämä ei päde käytännössä ilman raskaita optimointeja. LU-hajotelman pseudokoodista (Cormen s. 824) taas seuraa, että sen aikavaativuusluokka on O(n^3), missä n on neliömatriisin dimensio, sillä siinä on kolme sisäkkäistä for-looppia.

Strassen on rekursiivinen hajota ja hallitse -menetelmään perustuva algoritmi, joka jakaa jokaisella kierroksella kerrottavat neliömatriisit neljään osaan. Tällöin kyseisten neliömatriisien dimensioiden on oltava jokin kahden potenssi, sillä muuten algoritmi niin sanotusti menee rikki. Siksi olen toteutuksessani laskenut aina seuraavan kahden potenssin ja luonut uudet matriisit, joiden dimensio on tämä kahden potenssi, ja kopioinut kerrottavien matriisien alkiot näihin uusiin matriiseihin. Tämän vuoksi Strassen on tehokkaampi ainoastaan niiden matriisien tapauksissa, joiden dimensio on jokin kahden potenssi, tai hieman alle. Tämä ongelma voi kuitenkin olla korjattavissa. Lisäksi Strassenin toteutusta voisi optimoida käyttämällä matriisien kopioimisen sijaan viitteitä alkuperäisten taulukoiden osiin, vaikka tämä ei vaikuta itse aikavaativuusluokkaan.

Komentorivikäyttöliittymä on toistaiseksi erittäin yksinkertainen, ja sen koodi on rumaa kopipastaa. Sitä voisi laajentaa ja refaktoroida.

**Lähteet:** Cormen, Leiserson, Rivest, Stein: Introduction to Algorithms (Third Edition)
