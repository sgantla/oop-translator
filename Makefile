JAVAC = javac
JFLAGS = -cp src:lib/xtc.jar -d bin/

SOURCES = $(wildcard src/oop/*/*.java)
CLASSES = $(SOURCES:.java=.class)

all : $(CLASSES:src=bin)

clean : 
	rm -rf bin/*

%.class : %.java
	$(JAVAC) $(JFLAGS) $<

jar:
	jar cf bin/oop.jar bin/oop

docs:
	javadoc -d doc -private src/oop/*/*.java
