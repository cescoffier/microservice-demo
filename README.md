# 50 shades of microservices

This project demonstrates several ways to build microservices with Quarkus:

* HTTP / CRUD (Villains service)
* graphQL (Heroes service)
* gRPC (Fight service)
* Kafka (Stats service)
* API Gateway (API service)

## Run the demo

### Villains Service

* Port: 9000
* http://localhost:9000/

```shell
> cd villains-service
> mvn quarkus:dev
```


### Heroes service

* Port: 9001
* http://localhost:9001/q/dev -> execute query from the GraphQL widget

```shell
> cd heroes-service
> mvn quarkus:dev
```

### Fight Service

* Port 9003 (9004 for gRPC)
* * http://localhost:9003/q/dev -> execute the method from the gRPC widget

```shell
> cd fight-service
> mvn quarkus:dev
```


### API Gateway

* Port: 9999
* UI: http://localhost:9999
* Endpoint: http://localhost:9999/api

```shell
> cd api-gateway
> mvn quarkus:dev
```

_NOTE_: Capture the kafka bootstrap server from the log.

### Stats Service

* Port: 9005

```shell
> cd stats-service
> mvn quarkus:dev -Dkafka.bootstrap.servers={captured-location}
```

Then, in another terminal:

```shell
> http :9005/stats --stream
```

Trigger fight using the UI from the API gateway.