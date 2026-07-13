package io.projectZ;

import java.io.File;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.util.JiveGlobals;

public class ZChatUserSyncPlugin implements Plugin {

    private final Logger logger = LogManager.getLogger(ZChatUserSyncPlugin.class);
    private PluginContext context;

    @Override
    public void initializePlugin(PluginManager manager,
        File pluginDirectory) {
        logger.info("sync plugin started");
        String kafkaAddress = JiveGlobals.getProperty("kafka.address");
        String kafkaTopic =  JiveGlobals.getProperty("kafka.user-admin.topic");
        context = new PluginContext(kafkaTopic , kafkaAddress);

        context.start();

    }

    @Override
    public void destroyPlugin() {

        logger.info("sync plugin closed");
        if (context != null) {
            context.stop();
        }
    }

}