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

telnet localhost 2181
Trying ::1...
Connected to localhost.
Escape character is '^]'.
srvr
Zookeeper version: 3.7.0-e3704b390a6697bfdf4b0bef79e3da7a4f6bac4b, built on 2021-03-17 09:46 UTC
Latency min/avg/max: 0/0.0/0
Received: 3
Sent: 2
Connections: 1
Outstanding: 0
Zxid: 0x0
Mode: standalone
Node count: 5
Connection closed by foreign host.
```
