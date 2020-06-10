package ibm.gse.eda.mq.start;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ibm.gse.eda.mq.start.domain.CommandType;
import ibm.gse.eda.mq.start.domain.Control;
import ibm.gse.eda.mq.start.infrastructure.mq.InventoryMessageProducer;

@Path("/mqdemo")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class MessageResource {

    @Inject
    public InventoryMessageProducer messageProducer;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MessageResource(){
    }

    @GET
    @Path("config")
    public String hello() {
        return messageProducer.toJson();
    }

    @POST 
    @Path("start")
    public Response startSendingMessage(Control control) {
        try{
            if (control != null && control.command == CommandType.START) {
                messageProducer.produceMessage(control.nbOfMessages);
            } else {
                messageProducer.stop();
            }
            return Response.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }
        return Response.status(500).build();
    }

}