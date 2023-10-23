FROM openjdk:17-jdk-slim
MAINTAINER com.nikiforov.account
COPY build/libs/account-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#docker build -t bank .