FROM sbtscala/scala-sbt:eclipse-temurin-17.0.13_11_1.10.7_3.3.4

WORKDIR /app

COPY build.sbt ./
COPY project/build.properties ./project/build.properties

RUN sbt update

COPY src/main ./src/main

RUN sbt compile

EXPOSE 3000

CMD ["sbt", "--batch", "run"]