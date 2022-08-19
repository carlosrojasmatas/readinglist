FROM amazoncorretto:18.0.1

RUN yum install -y shadow-utils && yum clean all && useradd readinglist
USER readinglist
WORKDIR /home/readlinglist
RUN mkdir bin
ADD build/libs/readinglist-0.0.1-SNAPSHOT.jar bin/app.jar
ADD docker/bin/init bin/init

CMD ["ls","-lah","/home/readinglist/bin"]