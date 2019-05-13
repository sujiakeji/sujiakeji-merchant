dependencies: 
- docker
- jdk 8
- nexus
- gradle
- mysql
- rabbitmq
- sujiakeji-eureka-server
- sujiakeji-config-server

update dependencies:
```
gradle dependencyUpdates -Drevision=release --info
```

build: 
```
cd sujiakeji-merchant
./gradlew idea
./gradlew clean build copyJar -x test
```

start:
```
java -Dspring.profiles.active=dev \
  -DEUREKA_SERVER_HOST=localhost \
  -DEUREKA_SERVER_PORT=9000 \
  -DMYSQL_HOST=localhost \
  -DMYSQL_PORT=3306 \
  -DRABBITMQ_HOST=localhost \
  -DRABBITMQ_PORT=5672 \
  -DSTORAGE_HOST=localhost \
  -DSTORAGE_PORT=10000 \
  -jar build/libs/sujiakeji-merchant.jar
```

docker:
```
./gradlew docker

docker build -t sujiakeji/sujiakeji-merchant:1.0.0-SNAPSHOT .

docker run -it --rm \
  --name sujiakeji-merchant \
  -p 10100:10100 \
  --link sujiakeji-eureka-server \
  --link sujiakeji-mysql \
  --link sujiakeji-rabbitmq \
  -e EUREKA_SERVER_HOST=sujiakeji-eureka-server \
  -e EUREKA_SERVER_PORT=9000 \
  -e MYSQL_HOST=sujiakeji-mysql \
  -e MYSQL_PORT=3306 \
  -e RABBITMQ_HOST=sujiakeji-rabbitmq \
  -e RABBITMQ_PORT=5672 \
  -e STORAGE_HOST=sujiakeji-storage \
  -e STORAGE_PORT=10000 \
  -e SPRING_PROFILES_ACTIVE=dev \
  sujiakeji/sujiakeji-merchant

docker-compose up
```
