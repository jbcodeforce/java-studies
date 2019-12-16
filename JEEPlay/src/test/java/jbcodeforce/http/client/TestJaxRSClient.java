package jbcodeforce.http.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.Assert;
import org.junit.Test;

public class TestJaxRSClient {
    
    @Test
    public void testBasicGet(){
        Client client = ClientBuilder.newClient();
        Response rep = client.target("http://www.google.com").request().get();
        Assert.assertTrue(Response.Status.OK.getStatusCode() == rep.getStatus());
    }
}