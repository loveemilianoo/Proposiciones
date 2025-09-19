#!/bin/bash
JAR_NAME="Proposiciones.jar"

# Crear el jar
jar cfm $JAR_NAME Manifest.txt -C bin .

echo "Jar creado"