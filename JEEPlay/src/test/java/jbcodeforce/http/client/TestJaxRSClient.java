package jbcodeforce.http.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import java.io.StringReader;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Assert;
import org.junit.Test;

public class TestJaxRSClient {

    @Test
    public void testBasicGet() {
        Client client = ClientBuilder.newClient();
        Response rep = client.target("http://www.google.com").request().get();
        Assert.assertTrue(Response.Status.OK.getStatusCode() == rep.getStatus());
    }

    /**
     * Call Https server and parse the response as string
     * 
     * @throws Exception
     */
    @Test
    public void testBasicHttpsGet() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        // create a context without key manager and truth manager
        sslContext.init(null, null, new SecureRandom());
        Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
        Response rep = client.target("https://jsonplaceholder.typicode.com/todos/1").request().get();
        Assert.assertTrue(Response.Status.OK.getStatusCode() == rep.getStatus());
        String repAsString = rep.readEntity(String.class);
        System.out.println(repAsString);
        JsonReader reader = Json.createReader(new StringReader(repAsString));
        JsonObject objected = reader.readObject();
        Assert.assertTrue(1 == objected.getInt("userId"));
    }

    public KeyManager[] buildKeyManagers() {
        return new KeyManager[] {};
    }

    public TrustManager[] getTruthManagers() {
        return new TrustManager[] { 
            new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
           }
        };
    }
}