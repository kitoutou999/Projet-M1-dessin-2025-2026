#!/bin/bash
shopt -s globstar
mkdir -p doc
javadoc -d doc -sourcepath src src/**/*.java
