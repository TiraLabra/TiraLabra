## Testaus

### Yksikkötestaus

Sovelluksen yksikkötestaus on toteutettu JUnitilla.

### Suorituskykytestaus

Ohjelman suorituskykyä testataan eri kokoisilla ja eri muotoisilla tiedostoilla:
* Lyhyitä ja pitkiä tekstitiedostoja
* Isoja ja pieniä bittikartta-kuvatiedostoja
* Isoja ja pieniä jpeg-kuvatiedostoja (pakkaako vielä lisää?)

Tiedostojen kokovertailussa tulee ilmi sovelluksen ajankäytön suhde tiedoston kokoon.
Tiedostojen tyyppivertailussa nähdään, miten tiedoston sisältö vaikuttaa pakkaustehoon (pakkautuuko teksti yhtä tehokkaasti kuin kuva tai pakkautuuko valokuva yhtä tehokkaasti kuin vain muutamaa väriä sisältävä piirrustus)

### Tuloksia

#### Tekstitiedostot

Testattavana oli 10, 100, 1000 ja 2000 kilotavun tiedostot Lorem ipsumia, joka edustaa suhteellisen hyvin englannin aakkoston kirjainfrekvenssejä.

** Testaus keskeytyi väliaikaisesti, löytyi melkoisesti aikaa syövä "virhe", johon pitää löytää ratkaisu. Purkuominaisuudessa uusi ratkaisu jo käytössä, pakkaus vielä hidas. **