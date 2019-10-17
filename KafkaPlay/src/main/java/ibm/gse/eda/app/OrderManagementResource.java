package ibm.gse.eda.app;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import ibm.gse.eda.app.domain.OrderEntity;
import ibm.gse.eda.app.domain.OrderService;
import ibm.gse.eda.app.dto.OrderCreateParameters;

@Path("orders")
@RequestScoped
public class OrderManagementResource {

	@Inject
	@ConfigProperty(name="message")
	private String message;
	@Inject
	private OrderService orderService;
	
	public OrderManagementResource(){
	
	}
	
	@GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Response getMessage() {
       return  Response.ok(message).build();
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Request to create an order", description = "")
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Bad create order request", content = @Content(mediaType = "text/plain")),
            @APIResponse(responseCode = "200", description = "Order created, return order unique identifier", content = @Content(mediaType = "text/plain")) })
	public Response createShippingOrder(OrderCreateParameters orderParameters) {
		if (orderParameters == null ) {
			return Response.status(400, "No parameter sent").build();
		}
		OrderEntity order = OrderFactory.createNewOrder(orderParameters);
		try {
			
			orderService.createOrder(order);
		} catch(Exception e) {
			return Response.serverError().build();
		}
	    return Response.ok().entity(order.getOrderID()).build();
	}
}
