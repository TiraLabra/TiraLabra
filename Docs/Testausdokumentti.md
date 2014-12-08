## Testaus

### Yksikk�testaus

Sovelluksen yksikk�testaus on toteutettu JUnitilla.

### Suorituskykytestaus

Ohjelman suorituskyky� testataan eri kokoisilla ja eri muotoisilla tiedostoilla:
* Lyhyit� ja pitki� tekstitiedostoja
* Bittikartta-kuvatiedostoja
* Jpeg-kuvatiedostoja (pakkaako viel� lis��?)

Tiedostojen kokovertailussa tulee ilmi sovelluksen ajank�yt�n suhde tiedoston kokoon.
Tiedostojen tyyppivertailussa n�hd��n, miten tiedoston sis�lt� vaikuttaa pakkaustehoon (pakkautuuko teksti yht� tehokkaasti kuin kuva tai pakkautuuko valokuva yht� tehokkaasti kuin vain muutamaa v�ri� sis�lt�v� piirrustus)

### Tuloksia

#### Pakkausnopeus

Testattavana oli noin 100, 1000, 5000 ja 10000 kilotavun tiedostot Lorem ipsumia, joka edustaa suhteellisen hyvin englannin aakkoston kirjainfrekvenssej�. Jokaisen tiedoston pakkaus ja purku suoritettiin kuusi kertaa, jotta p��st�isiin mahdollisimman hyvin eroon mittausvirheist�.

100 kilotavun tiedostossa keskim��r�inen pakkausaika oli 77 ja purkuaika 78 millisekuntia. 1000 kilotavun tiedostolla ajat olivat noin 280 ja 215 millisekuntia, 5000 kilotavun tiedostolla 1190 ja 970 millisekuntia ja 10000 kilotavun tiedostolla 2590 ja 1950 millisekuntia.

Suorituskykytestaustuloksia.ods-tiedostosta l�ytyv�t tarkemmat tulokset sek� graafit, joista voidaan selv�sti n�hd� suoristusajan lineaarinen kasvu suhteessa tiedoston kokoon.

#### Pakkausteho

Saman tekstitiedoston, jota k�ytettiin nopeustestiss�, ohjelma pakkasi 9826:sta kilotavusta 5271 kilotavuun, jolloin pakkattu tiedosto oli kooltaan noin 54 prosenttia. Bittikarttapiirustuksen sovellus onnistui pakkaamaan 118:sta kilotavusta 59 kilotavuun saavuttan n�in 50 prosentin pakkaustehon. Valokuvilla (bmp, png ja jpg) pakkaus oli k�yt�nn�ss� turhaa: bmp-kuvalla p��stiin alle prosentin pakkaustehoon ja png- sek� jpg-kuvilla pakatun tiedoston koko jopa kasvoi hitusen. (Johtuu mit� luultavimmin tiedostoon tallennettavasta Huffman-puusta.)

My�s n�ist� l�ytyv�t tarkemmat tiedot Suorituskykytestaustuloksia.ods-tiedostosta toiselta v�lilehdelt�.
