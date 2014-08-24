# Testien ajaminen

QuickCheck/HUnit -testien ajaminen:

    cabal test

HPC-reportin kera:

    cabal configure --enable-tests --enable-library-coverage
    cabal test

Criterion-benchmarkkien ajaminen:

    cabal bench

# Tiilet, setit, kädet

`Mahjong.Tiles` ja `Mahjong.Hand.Mentsu` ovat suhteellisen yksinkertaisia,
tietorakenteisiin ja aksessoreihin keskittyneitä moduuleja `Mahjong.Hand.Algo`:n
rinnalla. Niiden muutamien funktioiden testit sijaitsevat
`tests/test.hs`-tiedostossa.

# Omat algoritmit

Algoritmien testit sijaitsee `tests/AlgoTest.hs`-tiedostossa.

Tiiliryhmiin jakavat algoritmit, eli `tilesGroupL` ja `tilesSplitGroupL` on
testattu quickcheck-tapauksilla jotka testaavat kuuluuko tietyntapaisella
syötteellä tiedetyt jaot tulokseen. Parissa testissä otetaan valmiiden settien
tiilet ja testataan sisältääkö tuloste alkuperäiset setit.

`shanten`-algoritmista testataan tapaukset joissa syöte on taatusti iishanten
(1), tenpai (0), valmis (-1), tai reunatapaus "väärästi valmis" (4 settiä ja
shuntsuwait) (ei mitään).

Odotuspuut (`buildGWT`, `buildGWTs`, `buildGWTs'`) on testattu siltä
**TODO writeme**

## Suorituskyky

Criterion-benchmarkkien implementaatiot ovat tiedostossa `tests/benchmark.hs`.

Huomataan, että `tilesGroupL` on yllättävän nopea 13 tiilellä (60
mikrosekuntia), eli normaalilla mahjong-kädellä. Mutta teennäisissä tapauksissa
missä tiiliä on enemmän, algoritmilla näyttää kasvavat eksponentiaalisesti.

Entäpä variaatio `tilesSplitGroupL`, joka käsittelee tiilityypit erikseen? Tämän
pitäisi intuitiivisesti olla huomattavasti nopeampaa ainakin suurissa
teennäistapauksissa.

Odotuspuiden suorituskyvyn testaus satunnaisella kädellä criterionilla on
vähintään humorista, koska puun koko vaihtelee todella paljon ja tuloksia ei
pysty tulkitsemaan criterionin heuristiikkojen hajotessa. Kuitenkin puun luonti
näyttäisi olevan yllätävän tehokasta yleisessäkin tapauksessa, alle puoli
sekuntia. Eihän näitä puita kukaan mihinkään oikeasti tällaisena voi käyttää
(eihän?), korkeintaan järkevämpien ratkaisuiden debuggauksen apuna.
