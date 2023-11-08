FROM openjdk:11-jre-slim

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]

# mvn clean package
# docker image build -t foodorders-api .
# mvn package -Pdocker
# docker image tag foodorders-api rafhaeldev/foodorders-api
# docker run --rm -p 8080:8080 -e DB_HOST=foodorders-db --network foodorders-network rafhaeldev/foodorders-api
# docker run --rm -it --network rest-api-studies_foodorders-network alpine sh (run: nslookup foodorders-api)
# docker compose up --scale foodorders-api=2
# apk add curl
# docker run --rm -it --network rest-api-studies_foodorders-network redis:6.2.1-alpine sh
# redis-cli -h foodorders-redis -p 6379
# keys *
# set name key-name
# get name
# del name