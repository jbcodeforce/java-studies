package ibm.gse.eda.mq.start.infrastructure.mq;

import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ibm.gse.eda.mq.start.domain.InventoryMessage;

@ApplicationScoped
public class InventoryMessageProducer {
    
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
    


    protected Jsonb jsonb = JsonbBuilder.create();;
    public JMSProducer producer = null;
    private JMSContext jmsContext = null;
    private Destination destination = null;
    private JmsConnectionFactory cf = null;

    private boolean running = true;

    public InventoryMessageProducer() throws JMSException {
        JmsFactoryFactory ff;
        ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
        cf = ff.createConnectionFactory();
    }

    public void init() throws JMSException {
        // Set the properties
        cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, this.mqHostname);
        cf.setIntProperty(WMQConstants.WMQ_PORT, this.mqHostport);
        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
        cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, this.mqQmgr);
        cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, this.appName);
        cf.setStringProperty(WMQConstants.WMQ_CHANNEL, this.mqChannel);
        cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
        cf.setStringProperty(WMQConstants.USERID, this.mqAppUser);
        cf.setStringProperty(WMQConstants.PASSWORD, this.mqPassword);
        // Create JMS objects
        jmsContext = cf.createContext();
        destination = jmsContext.createQueue("queue:///" + this.mqQueueName);
    }

    public void produceMessage(int nbMessageToProduce) throws JMSException {
        init();
        running = true;
        producer = getJmsContext().createProducer();
            
        for (int i = 0; (i <  nbMessageToProduce) && running ; i++) {
            InventoryMessage msg = new InventoryMessage(i);
            msg.itemCode="IT0" + ThreadLocalRandom.current().nextInt(1, 10);
            msg.storeName="NYC01";
            msg.quantity = Long.valueOf(ThreadLocalRandom.current().nextInt(2, 10));
            msg.price = Double.valueOf(ThreadLocalRandom.current().nextInt(10, 90));
            TextMessage message = getJmsContext().createTextMessage(jsonb.toJson(msg));
            producer.send(getDestination(), message);
            System.out.println("send message " + i);
        }
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


    public JMSContext getJmsContext() {
        return jmsContext;
    }

    public Destination getDestination() {
        return destination;
    }

	public void stop() {
        this.running = false;
	}

}