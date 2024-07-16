FROM openjdk:17-oracle
COPY ./target/CurdApplication.jar CurdApplication.jar
ENTRYPOINT [ "java","jar","CurdApplication.jar" ]
EXPOSE 8080