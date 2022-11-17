checkout:
	git pull origin master

build:
	./gradlew clean bootJar

test:
	./gradlew test

buildImage:
	docker build -t crojasma/readinglist:0.1 .

pushImage:
	docker push crojasma/readinglist:0.1

deploy:
	kubectl apply -f k8s/readinglist.yaml