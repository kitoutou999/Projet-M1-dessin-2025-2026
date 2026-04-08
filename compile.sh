#!/bin/bash
shopt -s globstar
mkdir -p out
javac -d out src/**/*.java
cp -r src/resources out/ 2>/dev/null
