package gianni.benjamin.client;

import java.util.*;

import gianni.benjamin.server.twitter.Stream;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Listener {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please specify which hashtag you would like to listen for: ");
        String hashtagListener = scan.nextLine();

        Consumer<Long, String> consumer = new KConsumer().createConsumer(hashtagListener);
        final int giveUp = 100;   int noRecordsCount = 0;

        new Stream().Listen(hashtagListener);

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(1000);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) {
                    break;
                }
                else {
                    continue;
                }
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%s, %d)\n",
                        record.value(),
                        record.offset());
            });

            consumer.commitAsync();
        }
        consumer.close();
    }
}
