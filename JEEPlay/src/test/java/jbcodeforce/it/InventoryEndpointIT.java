
package jbcodeforce.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InventoryEndpointIT {

  private static String port;
  private static String baseUrl;

  private Client client;

  private final String SYSTEM_PROPERTIES = "System/properties";
  private final String INVENTORY_SYSTEMS = "inventory/systems";

  @BeforeClass
  public static void oneTimeSetup() {
    port = System.getProperty("liberty.test.port","9080");
    baseUrl = "http://localhost:" + port + "/JEEPlay/";
  }
 


  @Before
  public void setup() {
    client = ClientBuilder.newClient();
    
    client.register(JsrJsonpProvider.class);
  }

  @After
  public void teardown() {
    client.close();
  }
  
  @Test
  public void testSuite() {
    this.testEmptyInventory();
    this.testHostRegistration();
    this.testSystemPropertiesMatch();
    this.testUnknownHost();
  }

  public void testEmptyInventory() {
    Response response = this.getResponse(baseUrl + INVENTORY_SYSTEMS);
    this.assertResponse(baseUrl, response);

    JsonObject obj = response.readEntity(JsonObject.class);

    int expected = 0;
    int actual = obj.getInt("total");
    assertEquals("The inventory should be empty on application start but it wasn't",
                 expected, actual);

    response.close();
  }

  public void testHostRegistration() {
    this.visitLocalhost();

    Response response = this.getResponse(baseUrl + INVENTORY_SYSTEMS);
    this.assertResponse(baseUrl, response);

    JsonObject obj = response.readEntity(JsonObject.class);

    int expected = 1;
    int actual = obj.getInt("total");
    assertEquals("The inventory should have one entry for localhost", expected,
                 actual);

    boolean localhostExists = obj.getJsonArray("systems").getJsonObject(0)
                                 .get("localhost").toString()
                                 .contains("localhost");
    assertTrue("A host was registered, but it was not localhost",
               localhostExists);

    response.close();
  }

  public void testSystemPropertiesMatch() {
    Response invResponse = this.getResponse(baseUrl + INVENTORY_SYSTEMS);
    Response sysResponse = this.getResponse(baseUrl + SYSTEM_PROPERTIES);

    this.assertResponse(baseUrl, invResponse);
    this.assertResponse(baseUrl, sysResponse);

    JsonObject jsonFromInventory = (JsonObject) invResponse.readEntity(JsonObject.class)
                                                           .getJsonArray("systems")
                                                           .getJsonObject(0)
                                                           .get("properties");

    JsonObject jsonFromSystem = sysResponse.readEntity(JsonObject.class);

    String osNameFromInventory = jsonFromInventory.getString("os.name");
    String osNameFromSystem = jsonFromSystem.getString("os.name");
    this.assertProperty("os.name", "localhost", osNameFromSystem,
                        osNameFromInventory);

    String userNameFromInventory = jsonFromInventory.getString("user.name");
    String userNameFromSystem = jsonFromSystem.getString("user.name");
    this.assertProperty("user.name", "localhost", userNameFromSystem,
                        userNameFromInventory);

    invResponse.close();
    sysResponse.close();
  }

  public void testUnknownHost() {
    Response response = this.getResponse(baseUrl + INVENTORY_SYSTEMS);
    this.assertResponse(baseUrl, response);

    Response badResponse = client.target(baseUrl + INVENTORY_SYSTEMS + "/"
        + "badhostname").request(MediaType.APPLICATION_JSON).get();

    String obj = badResponse.readEntity(String.class);

    boolean isError = obj.contains("ERROR");
    assertTrue("badhostname is not a valid host but it didn't raise an error",
               isError);

    response.close();
    badResponse.close();
  }


  private Response getResponse(String url) {
    return client.target(url).request().get();
  }

  private void assertResponse(String url, Response response) {
    assertEquals("Incorrect response code from " + url, 200,
                 response.getStatus());
  }

  private void assertProperty(String propertyName, String hostname,
      String expected, String actual) {
    assertEquals("JVM system property [" + propertyName + "] "
        + "in the system service does not match the one stored in "
        + "the inventory service for " + hostname, expected, actual);
  }

  private void visitLocalhost() {
    Response response = this.getResponse(baseUrl + SYSTEM_PROPERTIES);
    this.assertResponse(baseUrl, response);
    response.close();

    Response targetResponse = client.target(baseUrl + INVENTORY_SYSTEMS
        + "/localhost").request().get();
    targetResponse.close();
  }
}

