#!/bin/bash

PROJECT_DIR=Tiralabra #Muuta projektisi nimi, jos muutat projektisi nimeä!
#PROJECT_DIR=Tiralabra_maven #poista rivin alusta kommentti jos käytät pohjassa oletuksena mukana ollutta maven pohjaa!

if [ -f $PROJECT_DIR/pom.xml ]
	then
	cd $PROJECT_DIR
	mvn test
else
	cd $PROJECT_DIR
	ant test
fi