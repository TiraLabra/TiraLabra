#!/bin/bash
PROJECT_DIR=MatrixCalc

if [ -d Tiralabra_maven ]
	then
	PROJECT_DIR=Tiralabra_maven
fi
if [ -d Tiralabra ]
	then
	PROJECT_DIR=Tiralabra
fi

if [ -f $PROJECT_DIR/pom.xml ]
  then
  cd $PROJECT_DIR
  mvn test
else
  cd $PROJECT_DIR
  ant test
fi
