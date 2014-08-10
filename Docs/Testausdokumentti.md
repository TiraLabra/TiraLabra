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

Odotuspuu (`buildGreedyWaitTree`) ...

(TODO: toimiikohan se edes?)

## Suorituskyky

Criterion-benchmarkkien implementaatiot ovat tiedostossa `tests/benchmark.hs`.

Huomataan, että `tilesGroupL` on yllättävän nopea 13 tiilellä (60
mikrosekuntia), eli normaalilla mahjong-kädellä. Mutta teennäisissä tapauksissa
missä tiiliä on enemmän, algoritmilla näyttää kasvavat eksponentiaalisesti.

Entäpä variaatio `tilesSplitGroupL`, joka käsittelee tiilityypit erikseen? Tämän
pitäisi intuitiivisesti olla huomattavasti nopeampaa ainakin suurissa
teennäistapauksissa.

(TODO: benchmarkit on "semanttisesti rikki", korjataanpas ne ensin :)
