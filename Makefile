checkout:
	git pull origin master

build:
	./gradlew clean bootJar

test:
	./gradlew test

prepareImage:
	docker build -t crojasma/readinglist:0.1 docker/Dockerfile .

push:
	docker push crojasma/readinglist:0.1
