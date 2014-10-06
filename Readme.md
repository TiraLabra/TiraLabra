[![Build Status](https://travis-ci.org/TiraLabra/TiraLabra.svg?branch=master)](https://travis-ci.org/TiraLabra/TiraLabra)

##Tiralabra
* Pit lisätty
* Solmu luokka tietää värinsä
* Uusi luokka: PunaMustaPuu ja sen testit
* Testipäivitykset: Solmu, BinaarinenHakupuu, AVLpuu, SplayPuu
* Toteutus- ja Testausdokumentaatio jatkettu

#### Huomio!
Projektin puiden rakenteen esityksessä oli epäloogisuutta. Päädytty uuteen ratkaisuun.
Solmun tulisi tietää puun rakenteesta mahdollisimman vähän. Solmun toString "rikkoi"
tätä ajatusta, siksi siitä on luovuttu. BinaarinenHakupuu:n toStringi nimetty suomenkieliseksi
tulosta-metodiksi. Tulosta esittää merkkijonona puun rakenteen esijärjestyksessä.
Tulosta ei toimi kuten edeltäjänsä esittämällä solmujen arvoja, vaan esittää vain avaimia.
Tämä siksi, että arvojen esittäminen rajottaisi työtä liikaa. Pyritään mahdollistamaan solmuihin säilömisen kantaaottamattomuuden.
Tietenkin jotain rajoja on esim. null arvon lisäys. Tulosta metodille annetaan myös solmu, josta tulosta halutaan.
Tämä mahdollistaa myös puun osapuiden erottelun esityksessä.
