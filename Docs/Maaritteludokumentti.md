# Teko‰ly shakkiin
======

## Aihe

Projektin tarkoituksena on luoda shakkia pelaava teko‰ly joka pystyy pelaamaan sek‰ itse‰‰n ett‰ ihmisit‰ vastaan. 

Koska kyseess‰ on pelkk‰ teko‰ly, ei itse shakin s‰‰ntˆj‰ ja logiikka luoda t‰ss‰ prjektissa. Shakkia k‰sitell‰‰n[Chesspresso](http://www.chesspresso.org/) API:n kautta. Chesspresso toteuttaa shakkiin liittyv‰n logiikan kuten laudan tilan lukemisen, lailliset/laittomat liikket, shakki/shakkimatti jne. Chesspresso liitet‰‰n projektiin maven riippuvuuden avulla.

## Algoritmit

Projektissa on tarkoitus k‰ytt‰‰ [Alpha-beta etsint‰‰](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning) mahdollisten liikkeiden etsimiseen, sek‰ [minmax](https://en.wikipedia.org/wiki/Minmax) liikkeiden potentiaalin arvioimiseen. Potentiaali lasketaan nappuloiden ja tiettyjen liikkeiden arvojen perusteella. Jokaisella nappulalla on luonnollisesti oma arvonsa, eli kuningatar on esimerkiksi arvokkaampi kuin sotilas, kuitenkin arvot vaihtuvat riippuen nappuloiden paikasta.

Yhdist‰m‰ll‰ n‰it‰ tietoja voidaan matemaattisesti laskea arvokkaita ja v‰hemm‰n arvokkaita liikkeit‰ ja t‰m‰n seurauksena pelata shakkia.

Shakin suurin ongelma on liikkeiden suuri m‰‰r‰. Mit‰ pidemm‰lle peli jatkuu sit‰ enemm‰n mahdollisia liikkeit‰ voidaan pelata, ollakseen toimiva t‰ytyy ohjelman katsoa pidemm‰lle kuin seuraavan liikkeen arvoon. T‰m‰n seurauksena arvioitavien liikkeiden m‰‰r‰ kasvaa eksponentiaalisesti mit‰ syvemm‰lle pelin lukemisessa menn‰‰n. AB-etsinn‰n tulisi helpottaa t‰t‰. Algolla voidaan p‰tki‰ tiettyj‰ v‰hemm‰n arvokkaita liikkeit‰ pois arvioitavista liikkeist‰ ja n‰in huomattavasti parantaa ohjelman nopeutta.

AB ei tosin toimi ilman jonkinlaista arvoa, t‰t‰ varten k‰ytet‰‰n minmaxia sek‰ nappuloiden relatiivsita arvoa. El imahdollisia liikkeit‰ k‰yd‰‰n l‰pi, liikeiden arvot lasketaan pisteytt‰m‰ll‰ nappulat ja paikat, ja AB vertaa teittyjen liikeiden arvoja kesken‰‰n lˆˆyt‰‰kseen ideaalin pelaamistavan.


## Aika ja tilavaatimukset

Sek‰ AB etsint‰ ett‰ minmax riippuvat t‰ysin siit‰ kuinka paljon niille halutaan antaa aikaa, mit ‰enemm‰n aikaa algoritmeille annetaan laudan ja liikkeiden analyysiin, sit‰ paremmin ohjelma pelaa. T‰m‰n takia en aseta suoraa aika-vaatimusta, vaan pyrin v‰hint‰‰n nopeuteen jolla ihmiset pelaisivat shakkia.

## L‰hteet:

https://chessprogramming.wikispaces.com/
http://www.chessbin.com/
http://www.chesspresso.org/