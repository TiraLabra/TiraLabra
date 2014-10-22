#!bin/bash
# Automatisoitu mvn:n käsittely skripti

# Siirrytään maven-projektin juureen
cd Tiralabra_maven

# Luodaan cobertura ja pit dokumentaatio
mvn cobertura:cobertura
mvn org.pitest:pitest-maven:mutationCoverage

# Luodaan javadoc
mvn javadoc:javadoc
