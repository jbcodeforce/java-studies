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
import ibm.gse.eda.mq.start.infrastructure.mq.InventoryMessageConsumer;
import ibm.gse.eda.mq.start.infrastructure.mq.InventoryMessageProducer;
import ibm.gse.eda.mq.start.infrastructure.mq.JMSMQBase;

@Path("/mqdemo")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class MessageResource {

    @Inject
    public JMSMQBase config;

    @Inject
    public InventoryMessageProducer messageProducer;

    @Inject
    public InventoryMessageConsumer messageConsumer;

    private ExecutorService executorService;

    public MessageResource(){
    }

    @GET
    @Path("config")
    public String hello() {
        return config.toJson();
    }

    @POST 
    @Path("start")
    public Response startSendingMessage(Control control) {
        executorService = Executors.newSingleThreadExecutor();
        try{
            if (control != null && control.command == CommandType.START) {
                messageProducer.produceMessage(control.nbOfMessages);
                executorService.execute(messageConsumer);
            } else {
                messageConsumer.stop();
                executorService.shutdownNow();
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