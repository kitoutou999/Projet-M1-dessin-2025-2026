#!/bin/bash
mkdir -p out
javac -d out src/Main.java src/model/*.java src/view/*.java src/controller/*.java
java -cp out Main
