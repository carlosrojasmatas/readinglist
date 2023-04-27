checkout:
	git pull origin master

build:
	./gradlew clean bootJar

test:
	./gradlew test

buildImage:
	kubectl apply --file k8s/kaniko.yaml

#pushImage:
#	docker push crojasma/readinglist:0.1
#
#deploy:
#	kubectl apply -f k8s/readinglist.yaml