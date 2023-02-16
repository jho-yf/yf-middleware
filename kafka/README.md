# Kafka

## 启动Kafka

这里使用docker compose启动Kafka单机，如要本地安装参考[kafka官网](https://kafka.apache.org/documentation/#quickstart)。

前置环境：

- docker
- docker-compose

```shell
cd kafka-docker
docker-compose up -d

# 查看kafka是否正确启动
docker-compose ps
```

## 生产者示例代码

### 异步调用

进入docker容器，启动消费者监听

```shell
# 进入docker容器
docker exec -it kafka-docker_kafka_1 /bin/bash
cd /opt/bitnami/kafka/bin
# 启动消费者
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic --from-beginning
```

打开[ProducerTest#asyncCall](./src/test/java/cn/jho/tests/ProducerTest.java)并运行测试用例，查看消费者是否正确消息