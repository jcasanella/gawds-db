# Gawds DB

The purpose of this project is understanding some concepts under a distributed DB

## Network

We're using netty to build the client/server 

### How to start the server
```
gradlew db-server:run
```
Server by default is in port **8082**

TODO: Allow to change the port 
### How to start the client
```
gradlew db-client:run
```
By default, connects to **localhost** and port **8082** This is specified in the **build.gradle.kts**

It can be overridden adding this at the end of gradlew ```--args="localhost 8083"```
