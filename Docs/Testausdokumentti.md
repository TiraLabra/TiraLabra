## Testaus

### Yksikkötestaus

Sovelluksen yksikkötestaus on toteutettu JUnitilla.

### Suorituskykytestaus

Ohjelman suorituskykyä testataan eri kokoisilla ja eri muotoisilla tiedostoilla:
* Lyhyitä ja pitkiä tekstitiedostoja
* Bittikartta-kuvatiedostoja
* Jpeg-kuvatiedostoja (pakkaako vielä lisää?)

Tiedostojen kokovertailussa tulee ilmi sovelluksen ajankäytön suhde tiedoston kokoon.
Tiedostojen tyyppivertailussa nähdään, miten tiedoston sisältö vaikuttaa pakkaustehoon (pakkautuuko teksti yhtä tehokkaasti kuin kuva tai pakkautuuko valokuva yhtä tehokkaasti kuin vain muutamaa väriä sisältävä piirrustus)

### Tuloksia

#### Pakkausnopeus

Testattavana oli noin 100, 1000, 5000 ja 10000 kilotavun tiedostot Lorem ipsumia, joka edustaa suhteellisen hyvin englannin aakkoston kirjainfrekvenssejä. Jokaisen tiedoston pakkaus ja purku suoritettiin kuusi kertaa, jotta päästäisiin mahdollisimman hyvin eroon mittausvirheistä.

100 kilotavun tiedostossa keskimääräinen pakkausaika oli 77 ja purkuaika 78 millisekuntia. 1000 kilotavun tiedostolla ajat olivat noin 280 ja 215 millisekuntia, 5000 kilotavun tiedostolla 1190 ja 970 millisekuntia ja 10000 kilotavun tiedostolla 2590 ja 1950 millisekuntia.

Suorituskykytestaustuloksia.ods-tiedostosta löytyvät tarkemmat tulokset sekä graafit, joista voidaan selvästi nähdä suoristusajan lineaarinen kasvu suhteessa tiedoston kokoon.

#### Pakkausteho

Saman tekstitiedoston, jota käytettiin nopeustestissä, ohjelma pakkasi 9826:sta kilotavusta 5271 kilotavuun, jolloin pakkattu tiedosto oli kooltaan noin 54 prosenttia. Bittikarttapiirustuksen sovellus onnistui pakkaamaan 118:sta kilotavusta 59 kilotavuun saavuttan näin 50 prosentin pakkaustehon. Valokuvilla (bmp, png ja jpg) pakkaus oli käytännössä turhaa: bmp-kuvalla päästiin alle prosentin pakkaustehoon ja png- sekä jpg-kuvilla pakatun tiedoston koko jopa kasvoi hitusen. (Johtuu mitä luultavimmin tiedostoon tallennettavasta Huffman-puusta.)

Myös näistä löytyvät tarkemmat tiedot Suorituskykytestaustuloksia.ods-tiedostosta toiselta välilehdeltä.
