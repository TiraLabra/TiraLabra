
# Testausdokumentti

Ohjelman yksikk�testit on toteutettu mainion Boost-kirjaston "minimal test facilityn"
avulla. [1]

Testit on sijoitettu main.cpp tiedostoon, ja ne ajetaan kun ohjelma k��nnet��n -DRUN_TESTS
parametrin kanssa. Testien ajo onnistuu ajamalla komento "make test" src/
hakemistossa. Testit on jaettu kahteen funktioon, joista ensimm�inen testaa itse
toteutettujen tietorakenteiden ominaisuuksia, ja toinen testaa shakin siirtojen ja
teko�lyn ominaisuuksia. Testeiss� esimerkiksi varmistetaan ett� teko�ly l�yt�� oikean
m��r�n siirtoja alkutilanteessa.

Testien avulla havaitsin ett� on yh� muutamia siirtoja joita teko�ly ei jostain syyst�
l�yd�. Puuttuvia siirtoja alkaa tosin l�ytym��n vasta kun luodaan puu jonka syvyys
on 5. T�ll�in esimerkiksi oikea siirtojen m��r� alkutilanteessa olisi 4865609, mutta
teko�ly l�yt�� 4865319 eli puuttumaan j�� 290 siirtoa.

Eri puun syvyyksi� kokeilemalla sain seuraavia tuloksia ajan ja muistin
k�yt�st�. Testauksessa tein yhden siirron alkutilanteessa ja tarkastelin ohjelman
ilmoittamaa ajank�ytt�� ja k�yt�ss� olevan muistin m��r��.

| Puun syvyys | Aika (s) | Muisti (kt) |
|-------------|----------|-------------|
| 1           | 0        | 680         |
| 2           | 0        | 756         |
| 3           | 0.020    | 2232        |
| 4           | 0.534    | 42048       |
| 5           | 14.030   | 1021404     |

Muistink�ytt�� testattiin ottamalla v�liaikaisesti pois k�yt�st� muistin vapautusfunktiot
ja tekem�ll� yksi siirto. Syvyydell� 5 puu vaati jo noin gigatavun verran muistia, ja kun
puun syvyydeksi asetetaan 6 ohjelma vie jo liian paljon muistia ja kaatuu. Muistink�yt�n
tehokkuudessa olisi siis paljon parannettavaa. L�hes kaikkia aika kuluu puun luomiseen,
itse minimax haku suoriutuu teht�v�st��n hyvin nopeasti kun puu on luotu.

[1] http://www.boost.org/doc/libs/1_34_1/libs/test/doc/components/minimal_testing/index.html
