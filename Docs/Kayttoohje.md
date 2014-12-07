# Käyttöohje


        String map = map3();
        printOne(map, i);

Ohjelma käynnistetään App-luokasta. Ylläolevia rivejä muokkaamalla voidaan valita haluttu kartta sekä haluttu heuristiikka. Karttoja löytyy 5 erilaista: map1, map2 jne.

Ohjelmasta löytyy myös neljä erilaista heuristiikkaa, jotka ovat seuraavat:

 * 1 = Euclidean distance
 * 2 = Manhattan distance
 * 3 = Diagonal distance
 * 0 = Dijkstran algoritmi (eli ei heuristiikkaa)


Mikäli halutaan tulostaa vertailu kaikista kartoista, voidaan käyttää seuraavaa komentoa:

        printAll(map);

Tällöin ohjelma tulostaa halutun kartan käyttäen jokaista heuristiikkaa, tulostaa polun pituuden sekä käytetyn algorimin suoritusajan.
