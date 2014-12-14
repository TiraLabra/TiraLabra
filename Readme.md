# Labyrinth

## How to run the program:

Download the zip from the repository and execute following commands in the program folder(Tiralabra_maven):

        mvn compile

        mvn exec:java -Dexec.mainClass="com.mycompany.tiralabra_maven.App"

## How to change the settings

To change settings, you need to modify App.java. By default the program prints all the maps using the following command:

        printAll(map);

You can select one map with one heuristic with the following command:

        printOne(map, heuristic);


Heuristics are:

 * 1 = Euclidean distance
 * 2 = Manhattan distance
 * 3 = Diagonal distance
 * 0 = Dijkstra's algorithm

There are five different maps from map1() through map5().

