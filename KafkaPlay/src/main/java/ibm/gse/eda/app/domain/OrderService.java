package ibm.gse.eda.app.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ibm.gse.eda.app.infrastructure.events.EventEmitter;
import ibm.gse.eda.app.infrastructure.events.OrderEvent;
import ibm.gse.eda.app.infrastructure.events.OrderPayload;
import ibm.gse.eda.app.infrastructure.kafka.OrderEventProducer;

@ApplicationScoped
public class OrderService {

	@Inject
	private OrderEventProducer eventProducer;
	
	public OrderService() {}
	
	public OrderService(OrderEventProducer eventProducer) {
		this.eventProducer = eventProducer;
	}
	
	public void createOrder(OrderEntity order) {
		OrderEvent orderEvent = new OrderEvent(System.currentTimeMillis(),
				OrderEvent.TYPE_ORDER_CREATED);
		OrderPayload orderPayload = new OrderPayload(order.getOrderID(),
				order.getProductID(),
				order.getCustomerID(),
				order.getQuantity(),
				order.getStatus(),
				order.getDeliveryAddress());
		orderEvent.setPayload(orderPayload);
		try {
			eventProducer.emit(orderEvent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
