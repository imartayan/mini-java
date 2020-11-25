TEST_OK_FILES=$(wildcard ../tests/ok/*.java)
TEST_FAIL_FILES=$(wildcard ../tests/error/*.java)
MAIN_CLASSES=../bin/mj/SimpleHeap.class ../bin/mj/type_checker/TypeChecker.class
JAVA_FILES=$(`find mj -name "*.java"`)

default: runExample

$(MAIN_CLASSES): $(JAVA_FILES)
	javac -cp .:../mj.jar `find . -name "*.java"`
	mkdir -p ../bin
	cp --parents `find . -name "*.class"` ../bin
	rm -f `find . -name "*.class"`

test: posTests negTests
	@echo "All tests passed!"

posTests: $(TEST_OK_FILES) $(MAIN_CLASSES)
	@echo "Running all positive tests"
	@for FILE in $(TEST_OK_FILES) ; do \
		java -cp ../bin:../mj.jar mj.type_checker.TypeChecker $$FILE || (echo test $$FILE failed; exit 1) ; \
	done
	@echo ""
	@echo "All positive tests pass."
	@echo "# Pour l'instant 'make test' ne teste pas l'interpréteur, à vous de changer ça!"
	@echo ""

negTests: $(TEST_FAIL_FILES) $(MAIN_CLASSES)
	@echo "Runing all negative tests"
	@for FILE in $(TEST_FAIL_FILES) ; do \
		java -cp ../bin:../mj.jar mj.type_checker.TypeChecker $$FILE || (continue) ; \
		(echo test $$FILE failed; exit 1) \
	done
	@echo ""
	@echo "Tous les tests négatifs échouent correctement"
	@echo ""

runExample: $(MAIN_CLASSES)
	java -cp ../bin:../mj.jar mj.type_checker.TypeChecker ../tests/ok/BinaryTree.java
	java -cp ../bin:../mj.jar mj.SimpleHeap ../tests/ok/BinaryTree.java

clean:
	rm -f `find .. -name "*.class"`

.PHONY: clean test posTests negTests
