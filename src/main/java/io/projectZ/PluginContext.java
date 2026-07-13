package io.projectZ;

import io.projectZ.kafka.EventHandler;
import io.projectZ.kafka.KafkaConsumerManager;
import io.projectZ.provider.OpenfireServiceProvider;
import java.util.Properties;

public class PluginContext {

    private final String topicName;
    private KafkaConsumerManager kafkaConsumerManager;
    private String kafkaAddress;
    public PluginContext(String topicName , String address) {
        this.topicName = topicName;
        kafkaAddress = address;
    }

    public void start() {

        OpenfireServiceProvider serviceProvider =
            new OpenfireServiceProvider();

        EventHandler handler =
            new EventHandler(serviceProvider);

        kafkaConsumerManager =
            new KafkaConsumerManager(handler, kafkaAddress , topicName);

        kafkaConsumerManager.run();

    }

    public void stop() {

        if (kafkaConsumerManager != null) {
            try {
                kafkaConsumerManager.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

}