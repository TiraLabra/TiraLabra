Matematiikkakirjasto

Tarkoituksena olisi luoda jonkinlainen matematiikka-kirjasto, jolla pystyy
laskea tehokkaasti mm. matriisien yhteen- ja kertolaskuja, determinantteja, jne.

Myös matriisien, joiden sisältö on paljon nollia olisi kiva pystyä tallentaa
tehokkaasti, käyttäen esim. linkitettyjä listoja. Olisi myös kiva jos kirjasto
voisi kuitenkin jossain määrin piilottaa käyttäjältä kuinka tiedot on muistissa.

Kirjaston käyttöohjeiksi olisi hauska tehdä pari esimerkkisovellusta sen
käytöstä. Samalla niiden demoaimen olisi varmasti tehokkaampaa. Yksi niistä
olisi ainakin fibonacci-lukujen laskeminen matriisien potenssien avulla.
Muut todennäkösesti ovat monimutkaisempia ja täyttävät hyvin kaiken lopun
ajan. Ajattelin myös että ehkä voisin miettiä tekeväni yksinkertaisen
ray tracerin, tosin silloin olisi enemmän kyseessä vektorien käytöstä ja
ne taas ovat ulottuvuuden verran vähemmän kiinnostavia.

"Tehokas" laskeminen tässä tarkoittaa siis vähintään alle niiden naiivien
algoritmien aikavaativuuksien. Esimerkiksi neliömatriisien kertolaskun saa
helposti aikaan ajassa O(n^3), mutta olisi viihdyttävä jos käyttää jotain
nopeampaa algoritmia.


