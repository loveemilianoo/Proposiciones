#!/bin/bash


# Busca y guarda todos los archivos java
find . -name "*.java" > sources.txt

mkdir -p bin
# Compila dentro de ./bin los archivos .java
javac -d bin @sources.txt
