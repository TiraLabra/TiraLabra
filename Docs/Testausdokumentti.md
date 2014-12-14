
# Testausdokumentti

Ohjelman yksikkötestit on toteutettu mainion Boost-kirjaston "minimal test facilityn"
avulla. [1]

Testit on sijoitettu main.cpp tiedostoon, ja ne ajetaan kun ohjelma käännetään -DRUN_TESTS
parametrin kanssa. Testien ajo onnistuu ajamalla komento "make test" src/
hakemistossa. Testit on jaettu kahteen funktioon, joista ensimmäinen testaa itse
toteutettujen tietorakenteiden ominaisuuksia, ja toinen testaa shakin siirtojen ja
tekoälyn ominaisuuksia. Testeissä esimerkiksi varmistetaan että tekoäly löytää oikean
määrän siirtoja alkutilanteessa.

Testien avulla havaitsin että on yhä muutamia siirtoja joita tekoäly ei jostain syystä
löydä. Puuttuvia siirtoja alkaa tosin löytymään vasta kun luodaan puu jonka syvyys
on 5. Tällöin esimerkiksi oikea siirtojen määrä alkutilanteessa olisi 4865609, mutta
tekoäly löytää 4865319 eli puuttumaan jää 290 siirtoa.

Eri puun syvyyksiä kokeilemalla sain seuraavia tuloksia ajan ja muistin
käytöstä. Testauksessa tein yhden siirron alkutilanteessa ja tarkastelin ohjelman
ilmoittamaa ajankäyttöä ja käytössä olevan muistin määrää.

| Puun syvyys | Aika (s) | Muisti (kt) |
|-------------|----------|-------------|
| 1           | 0        | 680         |
| 2           | 0        | 756         |
| 3           | 0.020    | 2232        |
| 4           | 0.534    | 42048       |
| 5           | 14.030   | 1021404     |

Muistinkäyttöä testattiin ottamalla väliaikaisesti pois käytöstä muistin vapautusfunktiot
ja tekemällä yksi siirto. Syvyydellä 5 puu vaati jo noin gigatavun verran muistia, ja kun
puun syvyydeksi asetetaan 6 ohjelma vie jo liian paljon muistia ja kaatuu. Muistinkäytön
tehokkuudessa olisi siis paljon parannettavaa. Lähes kaikkia aika kuluu puun luomiseen,
itse minimax haku suoriutuu tehtävästään hyvin nopeasti kun puu on luotu.

[1] http://www.boost.org/doc/libs/1_34_1/libs/test/doc/components/minimal_testing/index.html
