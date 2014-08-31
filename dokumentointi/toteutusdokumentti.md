## Toteutusdokumentti

Ohjelma on jaettu kolmeen pakkaukseen, joiden nimet ovat algorithms, cli ja math. Näistä cli sisältää ainoastaan komentorivipohjaisen käyttöliittymän matriisilaskimelle.
Math-paketissa on luokat Complex, ComplexMatrix ja RealMatrix. Complex on luokka kompleksiluvuille, joita ei ole Javassa valmiina. Vastaavasti ComplexMatrix sisältää kompleksilukumatriisin ja RealMatrix reaalilukumatriisin.
Algorithms-paketti on jaettu paketteihin real ja complex, jossa paketissa real ovat reaalimatriiseille toteutetut algoritmit ja paketissa complex kompleksimatriisien algoritmit. Ohjelma sisältää toistaiseksi LU-hajotelman sekä reaali- että kompleksimatriiseille ja Strassenin algoritmin reaalimatriiseille.

Huomataan helposti, että matriisien peruslaskutoimitusten (yhteenlasku, vähennyslasku, skalaarikertolasku, transpoosi) aikavaativuus on O(mn), missä m ja n ovat matriisin dimensioita.
Vastaavasti kertolaskun aikavaativuus m*n- ja n*p-kokoisille matriiseille on O(mnp). Strassenin algoritmi toimii ainoastaan neliömatriiseille, ja sen aikavaativuus on luokkaa O(n^log_2(7)).
Luonnollisesti kaikkien algoritmien tilavaativuudet ovat O(mn), missä m ja n ovat matriisien dimensioita.
Determinantin laskeminen ja matriisin kääntäminen onnistuu LU-hajotelman avulla. Näiden molempien aikavaativuus on O(n^3), missä n on neliömatriisin dimensio.

Toistaiseksi Strassenin algoritmi on toteutettu idioottimaisella tavalla, jossa uusien taulukoiden muodostaminen vie älyttömästi aikaa. Tämä korjataan seuraavaan versioon mennessä.
