FROM mysql:latest

ADD ./persistence/src/main/resources/generate-schema.sql /docker-entrypoint-initdb.d

ENV MYSQL_DATABASE=chat
ENV MYSQL_ALLOW_EMPTY_PASSWORD=true

EXPOSE 3306