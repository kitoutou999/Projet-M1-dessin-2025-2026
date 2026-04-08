#!/bin/bash
shopt -s globstar
mkdir -p out plugins plugins-src/darkmode/out
javac -d out src/**/*.java
cp -r src/resources out/ 2>/dev/null
javac -cp out -d plugins-src/darkmode/out plugins-src/darkmode/**/*.java
jar cfm plugins/darkmode.jar plugins-src/darkmode/MANIFEST.MF -C plugins-src/darkmode/out .
