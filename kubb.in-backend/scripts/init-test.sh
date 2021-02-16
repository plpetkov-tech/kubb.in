#!/usr/bin/env bash
cd ../test-env/;
docker-compose up -d --build;
cd ../server/;
mvn spring-boot:run;
