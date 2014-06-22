#Käyttöohje

## Ohjelman tekstikäyttöliittymä

Ohjelmalle on toteutettu hyvin yksinkertainen ja karu käyttöliittymä. Koska varsinainen käyttöliittymä oli epäolennainen kurssin tavoitteiden kannalta, tähän ei ole panostettu paljoakaan. 

Komento 1 mahdollistaa hakualueen sekä alku- ja loppukoordinaattien valinnan. Aluksi kysytään hakualueen kuvatiedostonimi, sen jälkeen koordinaatit yksi kerrallaan.

Komento 2 mahdollistaa heuristiikan valinnan. Oletuksena on valittu Manhattan.

Komento 3 suorittaa varsinaisen haun. Tätä ennen on oltava sopiva hakualue määriteltynä.

Komento 4 tulostaa haun tuloksen sekä tekstimuodossa, että uuteen frameen aukeavana kuvana, johon on tulostettu punaisella toteutunut hakureitti.

Q sulkee ohjelman.

Käyttöliittymässä on valittuna oletuksena 100x100.bmp, koortinaateilla 0x0 ja 88x88. Jos siis haluaa nopeasti nähdä miten haku toimii, riittää että komentaa ensin '3', ja sen jälkeen '4'.

Suositeltava kartta kaikenlaiseen testailuun on maasto1.bmp. Se sisältää kohtalaisen tasapainoisesti erilaisia alueita. Toki myös omia bmp-kuvia voi kokeilla lisätä ja testailla, lopputulos voi olla tosin vaihteleva.

## Syötteet ja ohjelman käyttöönotto.

Ohjelman JAR löytyy repositorion juuresta erikseen tallennettuna, ilman että erikseen kääntää.

Ohjelma hyväksyy syötteinä bmp-kuvatiedostoja. Ne on tallennettu oletuksena alueet/ -hakemistoon.

HUOM! JAR-tiedoston kohdalla en ehtinyt selvittää tiedostopolkujen toimintaa, että ne toimisivat sellaisenaan. Tämän takia on käsin tehtävä samaan hakemistoon kyseinen alueet-hakemisto, ja lisättävä sinne vähintäänkin oletustiedostona oleva 100x100.bmp.

Jos koko repositoryn purkaa zippinä tai cloonaa sellaisenaan githubista, tällöin sen juuressa oleva JAR pitäiti toimia, sillä kuvahakemisto löytyy suhteessa oikealta paikaltaan. Tämä lienee siis toistaiseksi suositeltavin tapa ajaa JARia. Jos JARin ottaa itsenäisi, täytyy muistaa kopioida alueet/-hakemisto käsin.

Ratkaisu on valitettava, mutta en ehtinyt paneutua tiedostopolkujen ongelmaan aiemmin, sillä tajusin vaatimuksena olevan JARin liian myöhään, enkä ole aiemmissa javaprojekteissa säätänyt kuvatiedostojen kanssa.

## Tehokäyttö suoraan koodista

Jos ohjelmalla haluaa tehdä monipuolisemmin erilaisia kokeiluita, käytännössä tekstikäyttöliittymää näppärämmpi keino voi olla ajaa suoraan Netbeansista, koodin muuttujia säätämällä. Etenkin AStarSuorituskyky-luokan käyttäminen ajaa lähes saman asian kuin käsin näpyttely käyttöliittymän kautta.
