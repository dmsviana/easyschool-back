FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

RUN ./gradlew build -x test --no-daemon || return 0

COPY . .

RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar easyschool-back.jar

CMD ["java", "-jar", "easyschool-back.jar"]
