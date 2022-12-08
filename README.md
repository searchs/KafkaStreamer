## Spark - Kafka  



*_Docker_*
With docker-compose.yml updated with Kafka + ZooKeeper configurations (single-node or multi-node Kafka setup)
```shell
docker-compose up -d
```

*_Kafka Monitoring_*
A simple Kafka Admin UI
```shell
docker run -p 8080:8080 \
	-e KAFKA_CLUSTERS_0_NAME=local \
	-e KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=kafka:9092 \
	-d provectuslabs/kafka-ui:latest
```