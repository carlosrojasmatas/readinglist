k := /opt/homebrew/bin/kubectl
d := /opt/homebrew/bin/docker

.PHONY: all

all: post-build

pre-build:
	git clone https://github.com/carlosrojasmatas/readinglist.git

build: pre-build test
	./gradlew clean bootJar

post-build: build

test:
	./gradlew test