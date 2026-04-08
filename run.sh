#!/bin/bash
shopt -s globstar
mkdir -p out
javac -d out src/**/*.java && cp -r src/resources out/ 2>/dev/null && java -cp out Main
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
