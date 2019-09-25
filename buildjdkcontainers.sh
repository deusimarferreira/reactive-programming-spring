#!bash/sh

cd quotes-mongodb-service
sh mvnw clean package

cd ../quotes-mongodb-client
sh mvnw clean package

cd ..

docker build -t quotes-service-openjdk -f Dockerfile.openjdk --build-arg JAR_FILE=quotes-mongodb-service/target/quotes-mongodb-service-0.0.1-SNAPSHOT.jar .
docker build -t quotes-client-openjdk -f Dockerfile.openjdk --build-arg JAR_FILE=quotes-mongodb-client/target/quotes-mongodb-client-0.0.1-SNAPSHOT.jar .

docker build -t quotes-service-oraclejdk -f Dockerfile.oraclejdk --build-arg JAR_FILE=quotes-mongodb-service/target/quotes-mongodb-service-0.0.1-SNAPSHOT.jar .
docker build -t quotes-client-oraclejdk -f Dockerfile.oraclejdk --build-arg JAR_FILE=quotes-mongodb-client/target/quotes-mongodb-client-0.0.1-SNAPSHOT.jar .

docker build -t quotes-service-graalvmce -f Dockerfile.graalvmce --build-arg JAR_FILE=quotes-mongodb-service/target/quotes-mongodb-service-0.0.1-SNAPSHOT.jar .
docker build -t quotes-client-graalvmce -f Dockerfile.graalvmce --build-arg JAR_FILE=quotes-mongodb-client/target/quotes-mongodb-client-0.0.1-SNAPSHOT.jar .

docker build -t quotes-service-zulu -f Dockerfile.zulu --build-arg JAR_FILE=quotes-mongodb-service/target/quotes-mongodb-service-0.0.1-SNAPSHOT.jar .
docker build -t quotes-client-zulu -f Dockerfile.zulu --build-arg JAR_FILE=quotes-mongodb-client/target/quotes-mongodb-client-0.0.1-SNAPSHOT.jar .