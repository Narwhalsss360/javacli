# Makefile Parameters

MAIN_PACKAGE = test
MAIN_CLASS = Program
SOURCES_DIRECTORY = src
BINARIES_DIRECTORY = bin
JAVAC_FLAGS = -parameters

# Constants

__FILE__ = $(abspath $(lastword $(MAKEFILE_LIST)))
__PROJECT_DIRECTORY__ = $(dir $(__FILE__))
rwildcard=$(foreach d,$(wildcard $(1:=/*)),$(call rwildcard,$d,$2) $(filter $(subst *,%,$2),$d))

# Generated

MAIN = $(MAIN_PACKAGE).$(MAIN_CLASS)
ARCHIVE_FILE = $(MAIN_PACKAGE).jar
SOURCES = $(call rwildcard,$(SOURCES_DIRECTORY),*.java)
CLASSES = $(patsubst bin/%,%,$(call rwildcard,$(BINARIES_DIRECTORY),*.class))

# Platform specific functions
rmdir_recursive = rmdir $1 /s /q
rm = del $1
null_redirect = nul

all: build

clean:
	-@$(call rmdir_recursive,$(BINARIES_DIRECTORY)) 2> $(null_redirect)
	-@$(call rm,$(ARCHIVE_FILE)) 2> $(null_redirect)

compile: clean
	javac -g -cp $(SOURCES_DIRECTORY) -d $(BINARIES_DIRECTORY) $(JAVAC_FLAGS) $(SOURCES) $(INTERFACES)

archive:
	cd $(BINARIES_DIRECTORY) && jar --create --file $(__PROJECT_DIRECTORY__)$(ARCHIVE_FILE) --main-class $(MAIN) $(CLASSES)

build: compile archive

run-bin:
	java -cp $(BINARIES_DIRECTORY) $(MAIN)

run-jar:
	java -jar $(ARCHIVE_FILE)

run-build-jar: build run-jar
