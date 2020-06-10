package ibm.gse.eda.mq.start.infrastructure.mq;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.json.bind.Jsonb;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import ibm.gse.eda.mq.start.domain.InventoryMessage;

@ApplicationScoped
public class InventoryMessageConsumer implements Runnable {

    @Inject
    public JMSMQBase jmsConfig;
    protected JMSConsumer consumer = null;
    private JMSContext jmsContext = null;
    private Destination destination = null;
    private JmsConnectionFactory cf = null;
    protected Jsonb jsonb = null;
    protected boolean continueToRun = true;

    public InventoryMessageConsumer() throws JMSException {
        JmsFactoryFactory ff;
        ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
        cf = ff.createConnectionFactory();
    }

    @Override
    public void run() {
        consumer = getJmsContext().createConsumer(getDestination()); // autoclosable
        while (continueToRun) {
            String receivedMessage = consumer.receiveBody(String.class, 15000); // in ms or 15 seconds
            InventoryMessage itemSold = jsonb.fromJson(receivedMessage, InventoryMessage.class);
            // process the itemSold HERE
            System.out.println("\nReceived message:\n" + receivedMessage);
        }
    }

	public void stop() {
        continueToRun = false;
        consumer.close();
	}
    
    public JMSContext getJmsContext() {
        return jmsContext;
    }

    public Destination getDestination() {
        return destination;
    }
}