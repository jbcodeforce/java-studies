package ibm.gse.eda.mq.start;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ibm.gse.eda.mq.start.domain.CommandType;
import ibm.gse.eda.mq.start.domain.Control;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MessageResourceTest {

    @BeforeAll
    public static void startDependencies(){

    }

    @Test
    public void testGetConfigEndpoint() {
        

        given()
          .when().get("/mqdemo/config")
          .then()
             .statusCode(200)
             .body("qmgr",equalTo("QM1"));
    }

    @Test
    public void testControlSendingMessage(){
        Control c = new Control();
        c.command = CommandType.START;
        c.nbOfMessages = 2;

    given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(c).
    when()
        .post("/mqdemo")
        .then().statusCode(404);
    }
}