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

import ibm.gse.eda.mq.start.domain.InventoryMessage;

@ApplicationScoped
public class InventoryMessageProducer {
    
    @Inject
    public JMSMQBase config;


    protected Jsonb jsonb = JsonbBuilder.create();;
    public JMSProducer producer = null;
    private JMSContext jmsContext = null;
    private Destination destination = null;
    private JmsConnectionFactory cf = null;

    public InventoryMessageProducer() throws JMSException {
        JmsFactoryFactory ff;
        ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
        cf = ff.createConnectionFactory();
    }

    public void init() throws JMSException {
        // Set the properties
        cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, config.mqHostname);
        cf.setIntProperty(WMQConstants.WMQ_PORT, config.mqHostport);
        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
        cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, config.mqQmgr);
        cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, config.appName);
        cf.setStringProperty(WMQConstants.WMQ_CHANNEL, config.mqChannel);
        cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
        cf.setStringProperty(WMQConstants.USERID, config.mqAppUser);
        cf.setStringProperty(WMQConstants.PASSWORD, config.mqPassword);
        // Create JMS objects
        jmsContext = cf.createContext();
        destination = jmsContext.createQueue("queue:///" + config.mqQueueName);
    }

    public void produceMessage(int nbMessageToProduce) throws JMSException {
        init();
        producer = getJmsContext().createProducer();
            
        for (int i = 0; i <  nbMessageToProduce; i++) {
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


    public JMSContext getJmsContext() {
        return jmsContext;
    }

    public Destination getDestination() {
        return destination;
    }

}