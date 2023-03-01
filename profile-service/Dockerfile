FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9091
ENTRYPOINT ["java",\
"-verbose:gc -XX:+PrintGCTimeStamps",\
"-XX:+PrintGCDetails",\
"-Xloggc:./GarabgeCollection-%t.log",\
"-XX:+UseStringDeduplication",\
"-XX:+UseParallelGC",\
"-Xms256m",\
"-Xmx512m",\
"-Dspring.profiles.active=h2",\
"-jar","/app.jar"]