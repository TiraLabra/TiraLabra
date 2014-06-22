LabyrintitinTest testaa:
LuoNaapuruudet
onkoKayty
Kayty

Suorituskykytestaus, osa 1
100x100 prim labyrintti generoituu 200ms, A* haku siinä kulmasta kulmaan 100ms.
200x200 tapauksessa vastaavasti 1000ms ja 600-700ms
400x400 molemmat kestävät noin viisi sekuntia
800x800 kestää 40 sekuntia primillä ja 20 sekuntia A*...

prim näyttäisi aika lineaariselta ainakin ennen tuota 800x800

Suorituskykytestaus, osa 2
Implementoin kekoon javan hashmapilla indeksin joka nopeuttaa keon metodeiden: contains(E), poista(E), ja muuta(E,E) suoritusaikoja ja siten myös A* suoritusaikoja:
100x100 ajat ovat samaa luokkaa
200x200 180ms
400x400 400-900ms
800x800 10 sekunnin luokkaa.