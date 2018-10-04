# MakeFile
# Brian Mynhardt


JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= Land.class Tree.class SunData.class ForestPanel.class SumArray.class TreeGrow.class  
	 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

run:
	java -cp bin TreeGrow 600_input.txt SequentialOutput.txt
runParallel:
	java -cp bin LauncherParallel 600_input.txt ParallelOutput.txt
runDocuments:
	javadoc ./src/*.java -d ./docs
