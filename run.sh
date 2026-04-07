#!/bin/bash
mkdir -p out
javac -d out src/Main.java src/model/*.java src/model/shapes/*.java src/view/*.java src/controller/*.java src/controller/commands/*.java src/controller/states/*.java src/model/strategy/*.java
cp -r src/resources out/
#javadoc
javadoc -d doc -sourcepath src \
  src/Main.java \
  src/model/*.java \
  src/model/shapes/*.java \
  src/view/*.java \
  src/controller/*.java \
  src/controller/commands/*.java \
  src/controller/states/*.java \
  src/model/strategy/*.java
java -cp out Main
