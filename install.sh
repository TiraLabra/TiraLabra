#!/bin/bash

cd ./Tiralabra_maven
mvn install -Dmaven.compiler.target=1.7 -Dmaven.compiler.source=1.7 -DskipTests=true -B
