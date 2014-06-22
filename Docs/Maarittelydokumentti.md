Matematiikkakirjasto

Tarkoituksena olisi luoda jonkinlainen matematiikka-kirjasto, jolla pystyy
laskea tehokkaasti mm. matriisien yhteen- ja kertolaskuja, determinantteja, jne.

Myös matriisien, joiden sisältö on paljon nollia olisi kiva pystyä tallentaa
tehokkaasti, käyttäen esim. linkitettyjä listoja. Olisi myös kiva jos kirjasto
voisi kuitenkin jossain määrin piilottaa käyttäjältä kuinka tiedot on muistissa.

Kirjaston käyttöohjeiksi olisi hauska tehdä pari esimerkkisovellusta sen
käytöstä. Samalla niiden demoaminen olisi varmasti tehokkaampaa. Yksi niistä
olisi ainakin fibonacci-lukujen laskeminen matriisien potenssien avulla.
Muut todennäkösesti ovat monimutkaisempia ja täyttävät hyvin kaiken lopun
ajan. Ajattelin myös että ehkä voisin miettiä tekeväni yksinkertaisen
ray tracerin, tosin silloin olisi enemmän kyseessä vektorien käytöstä ja
ne taas ovat ulottuvuuden verran vähemmän kiinnostavia.

"Tehokas" laskeminen tässä tarkoittaa siis vähintään alle niiden naiivien
algoritmien aikavaativuuksien. Esimerkiksi neliömatriisien kertolaskun saa
helposti aikaan ajassa O(n^3), mutta olisi viihdyttävä jos käyttää jotain
nopeampaa algoritmia.

Matriisien kertolaskua on tarkoitus verrata Strassen-algoritmiin, joka
ainakin toivottavasti on nopeampi ajassa O(n^2.81). 

Matriisien potenssilaskut saa ehkä myös nopeammin aikaan, jos ensin suorittaa
ominaisdekompostion ja korottaa pelkän diagonaalimatriisin potenssia? Kuulemma
ominaisarvoja ja -vektoreita voi laskea ajassa O(n^3). Toisaalta luvut
todennäköisesti eivät enää ole kokonaislukuja siinä vaiheessa, joten olisi kiva
verrata tehokkuutta triviaalisemman divide-and-conquer O(n^3*log(n)) algoritmin
kanssa. Käänteismatriisin voi myös laskea eigendecompositionista, joten ainakin
se on monessa paikassa hyödyllinen.

Matriisit on tarkoitus myös toteuttaa geneerisinä, niin että ne voivat sisältää
mitä tahansa lukuja, joiden valinalla voi hienosäätää sovellusten tehokkuutta.
Tällöin olisi ehkä viihdyttävä myös toteuttaa algoritmit mielivaltaisten
kokonais- ja liukulukujen "pyörittelyyn".
