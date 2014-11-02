#!/bin/bash
PROJECT_DIR=Tiralabra_maven
if [ -f $PROJECT_DIR/pom.xml ]
  then
  cd $PROJECT_DIR
  mvn test
else
  cd $PROJECT_DIR
  ant test
fi
