FROM amazoncorretto:17-alpine3.21
ARG dir="/app"
WORKDIR $dir
COPY /build/libs/test-0.0.1-SNAPSHOT.jar $dir
COPY /src/main/resources/tests $dir
ENTRYPOINT java -jar "/app/test-0.0.1-SNAPSHOT.jar"