package cn.jho.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * <p>CustomProducer class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
public class CustomProducer {

    static String kafkaHost = "192.168.2.111:9092";

    public static void main(String[] args) {
        // 配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 创建kafka生成对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 发送数据
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<String, String>("hello", "kafka" + i));
        }

        producer.close();
    }

}
