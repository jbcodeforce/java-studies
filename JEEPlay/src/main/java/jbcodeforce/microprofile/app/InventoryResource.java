package jbcodeforce.microprofile.app;

import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jbcodeforce.microprofile.domain.InventoryList;
import jbcodeforce.microprofile.domain.InventoryManager;

/**
 * 
 * 
 *
 */
@RequestScoped
@Path("/systems")
public class InventoryResource {
	  @Inject
	  InventoryManager manager;

	  @Inject
	  SystemClient systemClient;

	  @GET
	  @Path("/{hostname}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getPropertiesForHost(@PathParam("hostname") String hostname) {
	    // Get properties for host
	    Properties props = systemClient.getProperties(hostname);
	    if (props == null) {
	      return Response.status(Response.Status.NOT_FOUND)
	                     .entity("ERROR: Unknown hostname or the system service may not be "
	                             + "running on " + hostname)
	                     .build();
	    }

	    // Add to inventory
	    manager.add(hostname, props);
	    return Response.ok(props).build();
	  }

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public InventoryList listContents() {
	    return manager.list();
	  }
}
