JC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	hashtagcounter.java \
	fibMaxHeap.java \
	fibMaxNode.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class