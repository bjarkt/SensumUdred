#!/usr/bin/env bash

mkdir -p out/yee

# copy all files from java source folder to out folder
cp -r src/main/java/* out/yee

# remove all .java files from out folder
find out/yee -name "*.java" -type f -delete

# copy resource and lib folder
cp -r src/main/resources/* out/yee
cp -r lib/* out/yee

# specific stuff (will be deleted when DB is added)
cp users.txt out/yee

# classpath is libs, and the out folder
java -jar lib/aspectj/aspectjtools.jar -d out/yee -source 1.8 -sourceroots src/main/java/ -cp 'lib/jfoenix-8.0.1.jar:lib/aspectj/aspectjrt.jar:out/yee/'
