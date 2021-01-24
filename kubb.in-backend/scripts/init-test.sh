#!/usr/bin/env bash
cd ../test-env/;
docker-compose up -d;
cd ../server/;
touch logs.txt;
sed -i 's/true/false/' ./src/main/resources/application.properties
mvn spring-boot:run >> log.txt &
