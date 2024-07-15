FROM openjdk:17-oracle
COPY ./target/CurdApplication-0.0.1-SNAPSHOT.jar /D:/STS_WrokSpace/CurdApplication/target/
ENTRYPOINT [ "java","jar","CurdApplication-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8080