#!/bin/bash
mkdir -p out
javac -d out src/Main.java src/model/*.java src/model/shapes/*.java src/view/*.java src/controller/*.java src/controller/commands/*.java src/controller/states/*.java
java -cp out Main
