#!/bin/bash
cd "$(dirname "$0")/.."

JUNIT_JAR="lib/junit-4.13.2.jar"
HAMCREST_JAR="lib/hamcrest-core-1.3.jar"

CLASSPATH="out:$JUNIT_JAR:$HAMCREST_JAR"

echo "Compilation des tests..."
javac -cp "$CLASSPATH" -d out \
    test/model/CollisionTest.java \
    test/model/GameModelTest.java \
    test/model/ScoreCalculatorTest.java

echo ""
echo "=== Lancement des tests ==="
java -cp "$CLASSPATH" org.junit.runner.JUnitCore \
    model.CollisionTest \
    model.GameModelTest \
    model.ScoreCalculatorTest
