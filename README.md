# Gawds DB

The purpose of this project is understanding some concepts under a distributed DB

## Network

We're using netty to build the client/server 

### How to start the server
```
gradlew db-server:run
```
Server by default is in port **8082**

### How to start the client
```
gradlew db-client:run
```
By default, connects to **localhost** and port **8082** This is specified in the **build.gradle.kts**

It can be overridden adding this at the end of gradlew ```--args="localhost 8083"```

### Enable Zookeeper

```
docker pull zookeeper:3.7.0
docker run -e "ZOO_INIT_LIMIT=10" --name test-zookeeper --restart always -p 2181:2181 -p 2888:2888 -p 3888:3888  -p 8080:8080 -d zookeeper:3.7.0
```
