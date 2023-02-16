package cn.jho.tests;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * <p>ProducerTest class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
class ProducerTest extends Assertions {

    static String kafkaHost = "localhost:9092";

    static KafkaProducer<String, String> producer;

    @BeforeAll
    static void setup() {
        // 配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 创建kafka生成对象
        producer = new KafkaProducer<>(properties);
    }

    /**
     * 异步调用
     */
    @Test
    void asyncCall() {
        int msgCount = 5;

        assertDoesNotThrow(() -> {
            // 发送数据
            for (int i = 0; i < msgCount; i++) {
                producer.send(new ProducerRecord<>("my_topic", "hello-kafka-async" + i));
            }
        });
        producer.close();
    }

    /**
     * 异步调用并回调
     */
    @Test
    void asyncCallAndCallback() {
        int msgCount = 5;

        assertDoesNotThrow(() -> {
            // 发送数据
            for (int i = 0; i < msgCount; i++) {
                producer.send(new ProducerRecord<>("my_topic", "hello-kafka-callback" + i),
                        (metadata, e) -> {
                            if (e == null) {
                                System.out.println("Topic: " + metadata.topic() + " Partition:" + metadata.partition());
                            }
                        });
            }
        });
        producer.close();
    }

    @Test
    void syncCall() {
        int msgCount = 5;

        assertDoesNotThrow(() -> {
            // 发送数据
            for (int i = 0; i < msgCount; i++) {
                RecordMetadata metadata = producer.send(new ProducerRecord<>("my_topic", "hello-kafka-sync" + i)).get();
                System.out.println("Topic: " + metadata.topic() + " Partition:" + metadata.partition());
            }
        });
        producer.close();
    }

}
