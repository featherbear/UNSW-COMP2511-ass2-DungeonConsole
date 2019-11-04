#/bin/sh

alias java-fx="/syncDir/UNSW/_COMP2511\ Assignments/H10B-GratefulLatestMegabyte/lib/jdk-jfx/bin/java"

./compile.sh

ln -s ../H10B-GratefulLatestMegabyte/dungeons/ dungeons
/syncDir/UNSW/_COMP2511\ Assignments/H10B-GratefulLatestMegabyte/lib/jdk-jfx/bin/java -jar DungeonConsole.jar
rm dungeons
