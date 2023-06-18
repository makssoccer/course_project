FROM maven:3-openjdk-17

COPY ./ ./

RUN mvn clean package -Dmaven.test.skip

CMD ["java", "-jar", "target/opinion_about_the_players-0.0.1-SNAPSHOT.jar"]