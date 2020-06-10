package ibm.gse.eda.mq.start.infrastructure.mq;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class JMSMQBase {
  
    @Inject
    @ConfigProperty(name = "mq.host")
    public String mqHostname;

    @Inject
    @ConfigProperty(name = "mq.port")
    public int mqHostport;

    @Inject
    @ConfigProperty(name = "mq.qmgr", defaultValue = "QM1")
    public String mqQmgr;

    @Inject
    @ConfigProperty(name = "mq.channel", defaultValue = "DEV.APP.SVRCONN")
    public String mqChannel;

    @Inject
    @ConfigProperty(name = "mq.app_user", defaultValue = "app")
    public String mqAppUser;

    @Inject
    @ConfigProperty(name = "mq.app_password", defaultValue = "passw0rd")
    public String mqPassword;

    @Inject
    @ConfigProperty(name = "mq.queue_name", defaultValue = "DEV.QUEUE.1")
    public String mqQueueName;

    @Inject
    @ConfigProperty(name = "app.name", defaultValue = "TestApp")
    public String appName;

    public JMSMQBase() {
    } 

    public String toJson(){
        return "{ \"host\": \"" + mqHostname
               + "\", \"port\": " + mqHostport 
               + ", \"qmgr\": \"" + mqQmgr 
               + "\", \"mqChannel\": \"" + mqChannel 
               + "\", \"mqQueueName\": \"" + mqQueueName 
               + "\", \"mqAppUser\": \"" + mqAppUser 
               + "\"}";
    }

}