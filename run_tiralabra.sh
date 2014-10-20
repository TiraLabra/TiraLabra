#!bin/bash
# Automatisoitu mvn:n käsittely skripti

echo "Entering the Rabbit hole!\n"
echo "-------------------------\n"

# Ajetaan apuskripti run_demo.sh
sh run_demo.sh

# Luodaan cobertura ja pit dokumentaatio
mvn cobertura:cobertura
mvn org.pitest:pitest-maven:mutationCoverage
