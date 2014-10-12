#!bin/bash
# Automatisoitu mvn:n käsittely skripti

# Siirryttään projektin juureen
echo "Entering the Rabbit hole!\n"
echo "-------------------------\n"
cd Tiralabra_maven

# Projektin alustus
mvn clean install

# Luodaan cobertura ja pit dokumentaatio
mvn cobertura:cobertura
mvn org.pitest:pitest-maven:mutationCoverage

# Luodaan jar tiedosto (juuresta lähtien /target/Tiralabra_maven-1.0-SNAPSHOT.jar
mvn jar:jar

# Rivinvaihto, jotta ohjelmantulosteen lukeminen olisi selkeämpää."
echo "\n------------------"

# Ajetaan jar-tiedosto, mikä suorittaan projektin Main-luokan
java -jar target/Tiralabra_maven-1.0-SNAPSHOT.jar
