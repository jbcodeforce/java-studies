package ibm.gse.eda.app.infrastructure.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import ibm.gse.eda.app.infrastructure.events.EventEmitter;
import ibm.gse.eda.app.infrastructure.events.OrderEvent;

@ApplicationScoped
public class OrderEventProducer implements EventEmitter {
	 private static final Logger logger = Logger.getLogger(OrderEventProducer.class.getName());

		@Inject
		private KafkaConfiguration kafkaConfiguration;
		
		private Jsonb jsonb = JsonbBuilder.create();
	    private KafkaProducer<String, String> kafkaProducer;


	    public OrderEventProducer() {
	        init();
	    }
	    
	    protected void init() {
	    	Properties properties = kafkaConfiguration.getProducerProperties("order-event-producer");
	        kafkaProducer = new KafkaProducer<String, String>(properties);
	    	
	    }
	    
	    @Override
	    public void emit(OrderEvent orderEvent) throws Exception  {
	    	String value = jsonb.toJson(orderEvent);
	    	logger.info("Send " + value);
	    	String key = orderEvent.getPayload().getOrderID();
	        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaConfiguration.getOrdersTopicName(), key, value);

	        Future<RecordMetadata> send = kafkaProducer.send(record);
	        try {
				send.get(KafkaConfiguration.PRODUCER_TIMEOUT_SECS, TimeUnit.SECONDS);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
			}
	    }

		@Override
		public void safeClose() {
			kafkaProducer.close();
		}
}
