FROM java:8
ADD build/libs/*.jar dockerapp.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "dockerapp.jar"]

FROM postgres
ENV POSTGRES_PASSWORD emsdb
ENV POSTGRES_DB emsdb
COPY src/main/resources/schema.sql /docker-entrypoint-initdb.d/