package jbcodeforce.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServletEndPointIT {
	private static String URL;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String port = System.getProperty("liberty.test.port","9080");
        String war = System.getProperty("war.name","JEEPlay");
        URL = "http://localhost:" + port + "/" + war + "/" + "servlet";
	}

	@Test
	public void testServlet() throws Exception {
		
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet method = new HttpGet(URL);
        try {
        	// 
        	CloseableHttpResponse response = client.execute(method);

            assertEquals("HTTP GET failed", HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
            // response has a HTTP content entity: can have stream, self-contained or wrapped in another entity
            HttpEntity entity = response.getEntity();
            if ( entity != null) {
            	String responseStr = EntityUtils.toString(entity);
                // content can be read more than one time
                assertTrue("Unexpected response body",
                    responseStr.contains("Bonjour! How are you today?"));
            } else {
            	Assert.fail("No entity in response");
            }
            
        } finally {
            method.releaseConnection();
        }
    }

	/**
	 * to be able to read entity content more than once, use the  BufferedHttpEntity class. 
	 * This will cause the content of the original entity to be read into a in-memory buffer
	 * 
	 * HttpEntity entity = response.getEntity();
		if (entity != null) {
		    entity = new BufferedHttpEntity(entity);
		}
	 */
}
