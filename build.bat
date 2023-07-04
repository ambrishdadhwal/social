#!/usr/bin/env bash
cls
echo $PWD
cd $PWD
echo $PWD
mvn clean install -DskipTests
:: Start Social Profile Module
cd profile-service/
echo 'Building profile service Module.....'
mvn clean package spring-boot:repackage -DskipTests

echo 'Building Docker image for social profile Module.....'
docker build -t social-profile-service .
:: End Social Profile Service

cd ../
:: Start Eureka Server Module
cd eureka-server/

echo 'Building Eureka Server Module.....'
mvn clean package spring-boot:repackage -DskipTests

echo 'Building Docker image for Eureka Server Module.....'
docker build -t social-eureka-server-service .
:: End Eureka Server Module

cd ../
:: Start Config Server Module
cd config-server/

echo 'Building Config Server Module.....'
mvn clean package spring-boot:repackage -DskipTests

echo 'Building Docker image for Config Server Module.....'
docker build -t social-config-server-service .
:: End Config Server Module

echo 'Image created'