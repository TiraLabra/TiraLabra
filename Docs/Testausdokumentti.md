## Testaus

### Yksikk�testaus

Sovelluksen yksikk�testaus on toteutettu JUnitilla.

### Suorituskykytestaus

Ohjelman suorituskyky� testataan eri kokoisilla ja eri muotoisilla tiedostoilla:
* Lyhyit� ja pitki� tekstitiedostoja
* Isoja ja pieni� bittikartta-kuvatiedostoja
* Isoja ja pieni� jpeg-kuvatiedostoja (pakkaako viel� lis��?)

Tiedostojen kokovertailussa tulee ilmi sovelluksen ajank�yt�n suhde tiedoston kokoon.
Tiedostojen tyyppivertailussa n�hd��n, miten tiedoston sis�lt� vaikuttaa pakkaustehoon (pakkautuuko teksti yht� tehokkaasti kuin kuva tai pakkautuuko valokuva yht� tehokkaasti kuin vain muutamaa v�ri� sis�lt�v� piirrustus)

### Tuloksia

#### Tekstitiedostot

Testattavana oli 10, 100, 1000 ja 2000 kilotavun tiedostot Lorem ipsumia, joka edustaa suhteellisen hyvin englannin aakkoston kirjainfrekvenssej�.

** Testaus keskeytyi v�liaikaisesti, l�ytyi melkoisesti aikaa sy�v� "virhe", johon pit�� l�yt�� ratkaisu. Purkuominaisuudessa uusi ratkaisu jo k�yt�ss�, pakkaus viel� hidas. **