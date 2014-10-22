# Testaus


## Reitinhaku

Ensin testataan polunetsintää hyvin yksinkertaisesti kahdella eri lähtöpisteellä. Ensin testataan lähtöpistettä jolla ei ole mitään maalipisteen välissä ja sen jälkeen lähtöpistettä joka on seinänä sisällä.

Luokan monimutkaisinta metodia "VierusSolmut" testattaessa annetaan testi verkolle muutama solmu jonka jälkeen metodia kutsutaan ja verkon uusia solmuja ja niiden arvoja tarkastellaa.

### Verkko
Verkosta testataan konstruktori, solmun luominen ja heurestiikan laskeminen. Heurestiikan laskeminen on tärkein.

### Solmu
Solmusta testataan konstruktori ja kekoa varten toteutettu kahden solmun vertailu.


### Minimikeko
Kekoa testataan lisäämällä testikekoon muutama solmu ja sitten tarkistamalla että ne tulevat poistettaessa ulos oikeassa järjestyksessä.