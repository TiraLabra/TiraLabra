#Kayttoohje

Ohjelmaa voidaan käynnistää komennolla ```java -jar Tiralabra_maven-1.0-SNAPSHOT.jar``` tai tupla klikkaamalla jar-tiedostoa. Ohjelmassa komennot: ovat open, new maze, findPath ja exit.

![Alt Kuva sovelluksen komennoista](gui1.png)

Kuva sovelluksen komennoista

 - ```open``` avaa sokkeloa sisältävän tiedoston.
 - ```save``` tallentaa sokkelon tiedostoon.
 - ```new``` maze piirtää satunnaisesti generoidun sokkelon.
 - ```findPath``` piirtää lähtöruudusta maaliruutuun vievän reitin, jos sellaista löytyy.
 - ```exit``` sulkee sovelluksen.

![Alt Kuva tiedoston sokkelosta](gui6.png)

Ohjelman hyväksymä tekstitiedosto on muotoa:

```
S # # # # # # # # #
1 2 3 4 5 6 7 8 9 #
# # # 4 5 6 # # # #
1 9 7 6 8 6 # 8 # #
5 2 3 4 5 # 7 8 # #
1 7 3 8 3 6 5 8 4 #
8 2 3 4 # # # # 5 G

```

```S``` merkitsee lähtöruutua, ```#``` estettä ja ```G``` maaliruutua.
Ruudut on eroteltu toisistaan välilyönnein. Ruutuun on kirjoitettu sen paino.

![Alt Kuva satunnaisesta sokkelosta](gui2.png)
Kuva satunnaisesta sokkelosta.


![Alt Kuva satunnaisesta sokkelosta ratkaistuna](gui4.png)
Kuva satunnaisesta sokkelosta ratkaistuna.

