FROM adoptopenjdk/openjdk14:jdk-14.0.1_7-alpine-slim AS build
WORKDIR /workspace/assignment-1ot-backend
COPY . /workspace/assignment-1ot-backend
RUN build=/root/.gradle ./gradlew clean build
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM adoptopenjdk/openjdk14:jdk-14.0.1_7-alpine-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/assignment-1ot-backend/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /assignment-1ot-backend/lib
COPY --from=build ${DEPENDENCY}/META-INF /assignment-1ot-backend/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /assignment-1ot-backend
ENTRYPOINT ["java","-cp","assignment-1ot-backend:assignment-1ot-backend/lib/*","com.iot.assignment.Application"]

EXPOSE 8090