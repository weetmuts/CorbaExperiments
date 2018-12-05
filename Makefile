
EMPTY=
SPACE=$(EMPTY) $(EMPTY)

$(shell mkdir -p target/generated)
$(shell mkdir -p target/classes)

JACORB_HOME=jacorb-3.9

JACORB_JARS=$(JACORB_HOME)/lib/jacorb-3.9.jar:$(JACORB_HOME)/lib/jacorb-omgapi-3.9.jar
JAVAX_RMI_JAR=jars/jboss-rmi-api_1.0_spec-1.0.6.Final.jar
LF4J_JARS=jars/slf4j-api-1.7.14.jar:jars/slf4j-jdk14-1.7.14.jar

JARS=$(JACORB_JARS):$(JAVAX_RMI_JAR):$(LF4J_JARS)

IDLS=$(shell find src/main/idls -name "*.idl")

JAVA_SOURCES=$(shell find src/main/java -name "*.java")

$(file > target/java_sources.list,$(JAVA_SOURCES))

target/classes.generated: $(JAVA_SOURCES) target/idls.generated
	javac -d target/classes -sourcepath target/generated -cp $(JARS) @target/java_sources.list @target/generated_sources.list
	touch target/classes.generated

target/idls.generated: $(IDLS)
	$(JACORB_HOME)/bin/idl -d target/generated $(IDLS)
	$(shell find target/generated -name "*.java" > target/generated_sources.list)
	touch target/idls.generated

server:
	java -cp $(JARS):target/classes foo.Server ior.file

client:
	java -cp $(JARS):target/classes foo.Client ior.file

clean:
	rm -rf target
