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
# docker container run --rm -p 8080:8080 -e DB_HOST=foodorders-db --network foodorders-network rafhaeldev/foodorders-api