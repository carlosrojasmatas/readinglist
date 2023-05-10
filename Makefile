k := /opt/homebrew/bin/kubectl
d := /opt/homebrew/bin/docker

.PHONY: all

all: post-build

pre-build:

build: pre-build test
	./gradlew clean bootJar

post-build: build

test:
	./gradlew test