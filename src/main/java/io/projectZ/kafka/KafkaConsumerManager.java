package io.projectZ.kafka;
/*
  Project : Openfire-Synchonorizer-provdier
  Author  : AmirHFF
  Created : 7/13/2026 - 8:04 AM
*/

import io.projectZ.dto.UserEvent;
import io.projectZ.provider.OpenFireAdminService;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KafkaConsumerManager implements Runnable ,AutoCloseable{

    private final Logger logger = LogManager.getLogger(KafkaConsumerManager.class);
    private final EventHandler handler;
    private KafkaConsumer<String , UserEvent> consumer ;
    private final String topicName;

    public KafkaConsumerManager(EventHandler handler, String address, String topicName) {
        this.handler = handler;
        this.topicName = topicName;
        this.consumer = new KafkaConsumer<>(loadProps(address));
    }

    private Properties loadProps(String address){
        Properties props =new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , address);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG , false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG , "earliest");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG , 10 );
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG , "300000" );
        return props;
    }
    @Override
    public void run() {
        consumer.subscribe(Collections.singletonList(topicName));
        consumeLoop();
    }

    private void consumeLoop(){
        logger.info("consuming event started");
        while (true){
            ConsumerRecords<String , UserEvent> records =  consumer.poll(Duration.ofMillis(500));

           try {
               for (ConsumerRecord<String, UserEvent> record : records) {

                   logger.debug(
                       "Receive topic={} partition={} offset={}",
                       record.topic(),
                       record.partition(),
                       record.offset());

                   handler.handle(record.value());
                   consumer.commitAsync();
               }
           }catch (Exception exception){
                logger.error("consuming event throw exception : " ,exception);
               break;
           }
        }
    }

    @Override
    public void close() throws Exception {
        consumer.close();
    }
}

