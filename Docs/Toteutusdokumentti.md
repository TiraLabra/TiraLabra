###Rakenne
Kirjaston voi helposti jakaa (ja paketit jakavatkin) kolmeen osaan:
- Matriiseihin, jotka sisältävät N*M numeroa. Niille on toteuttu kaikenlaista
operaatiota ja algoritmia, kuten Strassenin kertolaskualgoritmi.

- Numeroihin, jotka kaikki laajentavat/toteuttavat abstraktin
Number-pohjaluokan, joka lupaa tarvittavat metodit matriiseille ja vektoreille.

- Vektoreihin, jotka tallettavat N määrän mitä tahansa Number-pohjaluokan
toteuttavaa luokkaa. Vektorit myös itse toteuttavat Number-pohjaluokan, ja täten
ovat myös käytännössä numeroita ja voivat tallettaa itseään.

Mitään kuvaa edes on tarpeeton piirtää, koska kaikki kolme ovat erillisiä, eikä
edes sisäisesti ole toisistaan riippuvaisia.

###O-analyysiä

Fibonacci-esimerkkiohjelma vertailee fibonacci lukujen laskemista matriisien
potenssiin korotuksella ja tavallisella iteratiivisella menetelmällä laskemista.
Ohjelma kuitenkin ennemminki tuo esille kaksi kiinnostaavaa piirrettä kirjaston
käytöstä:

####Integerien toString() kestää huomattavasti enemmän kuin itse sarjan laskeminen.
Merkkijonoksi muuttaminen tapahtuu jotakuinkin näin:

```
res = ""
for i = 0 -> words.length:
	res += words[i] * (radix ^ i)
return res
```

Mistä ensinnäkin näkyy että metodi on lineaarinen luvun sanojen määrän suhteen.
Yhteenlaskut ovat O(n), missä n on lukujen määrä sanassa, kertolaskut ovat
O(n^2). Potenssi on toteutettu neliöimällä, jolloin sen aikavaativuus on
O((2 * log (n))^2). Sanan pituus voi enintäään olla 5, koska radix on 46340.
Koko operaation aikavaativuus siis pahimmassa tapauksessa on
O(n * (n + n^2 + (2*log(n))^2)), joka taas saadaan Wolfram Alphalla muotoon
O(n^3 + n^2 + 4*n*log(n)^2), missä n on luvun sanojen määrä.

Matriisien potenssiin korotus taas itse tapahtuu myös neliöimällä, mutta
matriisien kertolaskut on toteutettu ajassa O(n*m*j) missä n ja m ovat matriisin
rivit ja sarakkeet ja j on toisen matriisin sarakkeet. Fibonaccin lukuja
laskiessa ovat kaikki 2. Aikavaativuus täten on O(16 * log(n))).

1000:nennen luvun laskiessa aikaa kuluu ensin 60.9 ms ja merkkijonoksi
muuttamiseen kuluu 1365 ms. Eli huomattavasti kauemmin.

####Iteratiivinen ratkaisu on jostain syystä nopeampi.

Vaikka yhteenlaskuja iteroimalla aikavaativuus on O(n) ja matriisien
potensseilla O(16 * log(n)), missä n on molemmissa halutun fibonacci luvun
indeksi. 
Fibonacci(100000) vie matriiseilla 4272 ms ja yhteenlaskuilla 1296 ms.
Eli jostain syystä väärä metodi on nopeampi.

###Puutteita

Olisi ollut kiva luoda myös luokka symbolisille numeroille. Toimisivat
ulkopuolelle kuin kaikki muut numerot, mutta toString() palauttaisi tarkan
arvon, eikä välttämättä edes lukuja.

Matriisit olisi voinut myös laittaa toteuttamaan Number-luokan, mutta on jo 
sunnuntai ja krapula.
