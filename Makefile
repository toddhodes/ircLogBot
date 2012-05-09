.PHONY: all compile run clean

all: compile run

compile:
	javac -cp lib/pircbot.jar org/jibble/logbot/*java

run:
	java -classpath .:./lib/pircbot.jar org.jibble.logbot.LogBotMultiChannelMain multi.ini > /dev/null 2>&1

clean:
	rm -f ./org/jibble/logbot/*.class
