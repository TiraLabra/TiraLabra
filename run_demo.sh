#!bin/bash
# Skripti alustaa ja suorittaa projektin. Täten demonstroi projektia.

# Siirrytään projektin juureen
cd Tiralabra_maven

# Projektin alustus
mvn clean install

#Luodaan jar tiedosto (juuresta lähtien /target/Tiralabra_maven-1.0-SNAPSHOT.jar)
mvn jar:jar

# Ajetaan jar-tiedosto, mikä suorittaa projektin Main-luokan
java -jar target/Tiralabra_maven-1.0-SNAPSHOT.jar
