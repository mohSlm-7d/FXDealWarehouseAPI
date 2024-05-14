# Makefile

# Variables
APP_NAME = fxdealwarehouseapi

# Commands
.PHONY: build run docker-build docker-run

build:
	mvn clean install

run:
	mvn spring-boot:run

docker-build:
	mvn clean install
	docker-compose build

docker-run:
	docker-compose up
