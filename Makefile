run:
	javac -sourcepath src/ -d out/ -encoding utf-8  src/*.java
	java -cp ":out/" Main

