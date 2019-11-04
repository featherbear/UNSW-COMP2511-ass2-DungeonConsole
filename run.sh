#/bin/sh

alias java-fx="/syncDir/UNSW/_COMP2511\ Assignments/H10B-GratefulLatestMegabyte/lib/jdk-jfx/bin/java"

./compile.sh

rm dungeons -f
ln -s ../H10B-GratefulLatestMegabyte/dungeons/ dungeons
/syncDir/UNSW/_COMP2511\ Assignments/H10B-GratefulLatestMegabyte/lib/jdk-jfx/bin/java -jar DungeonConsole.jar
rm dungeons
