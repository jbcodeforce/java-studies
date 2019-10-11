package jbcodeforce.it;

import java.util.Properties;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicRestAppIT {

	private static final Jsonb jsonb = JsonbBuilder.create();
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
    public void testGetProperties() {
        String port = System.getProperty("liberty.test.port");
        String war = System.getProperty("war.name");
        String url = "http://localhost:" + port + "/" + war + "/";

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url + "System/properties");
        Response response = target.request().get();

        Assert.assertTrue("Incorrect response code from " + url,
                     Response.Status.OK.getStatusCode() == response.getStatus());

        String json = response.readEntity(String.class);
        Properties sysProps = jsonb.fromJson(json, Properties.class);

        Assert.assertTrue("The system property for the local and remote JVM should match",
                     System.getProperty("os.name").equals(sysProps.getProperty("os.name")));
        response.close();
    }

}
