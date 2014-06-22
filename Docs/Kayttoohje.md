#Käyttöohje

## Ohjelman tekstikäyttöliittymä

Ohjelmalle on toteutettu hyvin yksinkertainen ja karu käyttöliittymä. Koska varsinainen käyttöliittymä oli epäolennainen kurssin tavoitteiden kannalta, tähän ei ole panostettu paljoakaan. 

Komento 1 mahdollistaa hakualueen sekä alku- ja loppukoordinaattien valinnan. Aluksi kysytään hakualueen kuvatiedostonimi, sen jälkeen koordinaatit yksi kerrallaan.

Komento 2 mahdollistaa heuristiikan valinnan. Oletuksena on valittu Manhattan.

Komento 3 suorittaa varsinaisen haun. Tätä ennen on oltava sopiva hakualue määriteltynä.

Komento 4 tulostaa haun tuloksen sekä tekstimuodossa, että uuteen frameen aukeavana kuvana, johon on tulostettu punaisella toteutunut hakureitti.

Q sulkee ohjelman.

## Syötteet ja ohjelman käyttöönotto.

Ohjelman JAR löytyy repositorion juuresta.

Ohjelma hyväksyy syötteinä bmp-kuvatiedostoja. Ne on tallennettu oletuksena alueet/ -hakemistoon.

HUOM! JAR-tiedoston kohdalla en ehtinyt selvittää tiedostopolkujen toimintaa, että ne toimisivat sellaisenaan. Tämän takia on käsin tehtävä samaan hakemistoon kyseinen alueet-hakemisto, ja lisättävä sinne vähintäänkin oletustiedostona oleva 100x100.bmp.

Järkevintä on siis kopioida projektin alta koko alueet kansio JAR:in kanssa samaan sijaintiin.

Ratkaisu on valitettava, mutta en ehtinyt paneutua tiedostopolkujen ongelmaan aiemmin, sillä tajusin vaatimuksena olevan JARin liian myöhään, enkä ole aiemmissa javaprojekteissa säätänyt kuvatiedostojen kanssa.
