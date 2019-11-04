#!/bin/sh

alias java-fx="/syncDir/UNSW/_COMP2511\ Assignments/H10B-GratefulLatestMegabyte/lib/jdk-jfx/bin/javac"
alias javac-fx="/syncDir/UNSW/_COMP2511\ Assignments/H10B-GratefulLatestMegabyte/lib/jdk-jfx/bin/javac"

mkdir tmp/src -p
mkdir tmp/lib -p

echo Copying source and library files...
cp -R ../H10B-GratefulLatestMegabyte/src/* tmp/src
cp -R ../H10B-GratefulLatestMegabyte/lib/json.jar tmp/lib
cp -R ./src/* tmp/src
cp -R ./lib/* tmp/lib

echo Compiling...

cd tmp/src
rm unsw/dungeon/tests -rf
find -name "*.java" > ../sources.txt

javac-fx -cp :../lib/* -d ../bin @../sources.txt

echo Creating JAR file
cd ../bin
mv ../lib/* .

for f in *.jar
do
  jar -xf $f
done
rm META-INF -rf
rm *.jar

jar -cvfe ../output.jar DungeonConsole * > /dev/null

cd ..
cp output.jar ../DungeonConsole.jar

echo Cleaning up...
cd ..
rm -rf tmp

