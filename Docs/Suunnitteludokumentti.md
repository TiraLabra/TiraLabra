Toteutusdokumetti

Peli on toteutettu javalla ja siinä käytetään itse implementoituja tietorakenteita. 

Itse tekoälyn algoritmi on yksinkertainen. Se käy läpi kaikki mahdolliset siirrot ja etsii niistä parhaimman. 
Kehitysideana voisi tekoäly arpoa hyvyyden-laskemis funktionsa. Tekoäly voisi esimerkiksi painottaa vastustajan estämiseen.

Mahdollisten siirtojen määrä vaihtelee, kuten myöskäytössä olevien laattojen. Algoritmi käy kuitenkin aineiston aina kertaalleen läpi joten aikavaativuus on O(n), kuten myös tilavaativuus.

Työhön tehdyt tietorakenteet minimikeko, linkitettylista ja hashmap noudattavat näille tyypillisiä aikavaativuuksia. En onnistunut kehittämään uutta läpimurtoa tietorakenteisiin.

Tekoäly löytyy paketista blokusAI.  