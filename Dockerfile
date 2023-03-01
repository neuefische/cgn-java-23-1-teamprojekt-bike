FROM openjdk:19
EXPOSE 8080
WORKDIR /usr/src/myapp
ADD backend/target/app.jar app.jar
CMD ["sh", "-c", "java -jar app.jar"]