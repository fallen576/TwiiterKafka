package gianni.benjamin.server;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Topic {

    private String name;
    private Properties props = new Properties();

    public static void main(String[] args) {
        Topic tmp = new Topic("something");
        tmp.createTopic();
        tmp.sendMessage("we be really here my dawg");

    }

    public Topic(String name) {
        this.name = name;
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        createTopic();
    }

    private void createTopic() {
        AdminClient adminClient = AdminClient.create(props);
        NewTopic newTopic = new NewTopic(name, 1, (short)1);
        List<NewTopic> newTopics = new ArrayList<NewTopic>();
        newTopics.add(newTopic);
        adminClient.createTopics(newTopics);
        adminClient.close();
    }

    public void sendMessage(String message) {
        KafkaProducer<String, String> kafkaProducer = null;
        try {
            kafkaProducer = new KafkaProducer<String, String>(props);
            kafkaProducer.send(new ProducerRecord<String, String>(name, "key", message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}
