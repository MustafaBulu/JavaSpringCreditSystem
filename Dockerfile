FROM openjdk:11.0.10-jre-slim
ENV TZ="Europe/Istanbul"
RUN date
COPY ./target/JavaSpringCreditSystem.jar JavaSpringCreditSystem.jar
ENTRYPOINT ["java","-jar","/JavaSpringCreditSystem.jar"]