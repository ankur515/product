FROM openjdk:11.0-jdk-slim
VOLUME /tmp
COPY /target/ProductMS-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8300
ENV JAVA_OPTS=""
RUN sh -c "touch ProductMS-0.0.1-SNAPSHOT.jar"
ENTRYPOINT [ "java", "-jar", "ProductMS-0.0.1-SNAPSHOT.jar" ]
