SHELL = /bin/sh

JAVA_HOME=/usr/lib/jvm/java-6-openjdk-amd64

#
# C++ compiler options
# 
CC = g++
CFLAGS = -g
INCLUDES = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux 
LIBS = -ljvm -L$(JAVA_HOME)/jre/lib/amd64 -L$(JAVA_HOME)/jre/lib/amd64/server/

#
# Ant
#
ANT = ant

#
# GIWS options
#
GIWS = giws
GFLAGS = -e -r -g
GIWS_OUTPUT = giws

#
# GIWS PROJECT INFORMATIONS 
#
OBJECT_NAME  = LaTeXGenerator
BINARY       = latex
PACKAGE      = GiwsExample.jar
MAIN         = latex.cpp

GIWS_CPP_FILE  = $(addprefix $(GIWS_OUTPUT)/, $(OBJECT_NAME).cpp GiwsException.cpp) 
GIWS_DESC_FILE = $(OBJECT_NAME).giws.xml

all: $(BINARY)

$(PACKAGE):
	ant

# build output binary
$(BINARY): $(GIWS_CPP_FILE) $(PACKAGE)
	$(CC) $(GIWS_CPP_FILE) $(MAIN) $(CFLAGS) $(LIBS) $(INCLUDES) -I$(GIWS_OUTPUT) -o $(BINARY)
	@if test $(MAKELEVEL) -eq 0; then \
		echo "==========================================================================="; \
		echo "Dont forget to set library path before running the program:"; \
		echo "# export LD_LIBRARY_PATH=$(LD_LIBRARY_PATH):$(JAVA_HOME)/jre/lib/amd64/server/"; \
		echo "==========================================================================="; \
	fi	


# run giws to generate cpp code
$(GIWS_CPP_FILE):
	mkdir -p $(GIWS_OUTPUT)
	$(GIWS) -f $(GIWS_DESC_FILE) $(GFLAGS) -o $(GIWS_OUTPUT)

# Generate jar
$(PACKAGE):
	ant

# cleanup giws generated files (cpp/hxx, bin) and .class
clean:
	rm -fr $(GIWS_OUTPUT) $(BINARY)
	ant clean

