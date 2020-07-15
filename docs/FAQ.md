# FAQ

## Get UUID

```java
    UUID uuid = UUID.randomUUID();
    order.setOrderID(uuid.toString());
```

## Java SE

### Read from a properties file

```java
Properties props = new Properties();
 try {
        props.load(  new FileInputStream(filename));
 } catch (FileNotFoundException e){
         e.printStackTrace();
  } catch (IOException e) {
        e.printStackTrace();
 }
```

### How to read json file

```java
FileReader input= new FileReader("src/test/resources/testpumps.json");
Gson parser = new Gson();
AssetEvent[] assets = parser.fromJson(new JsonReader(input), AssetEvent[].class);
```

### Json binding JSR 367

[Introduction from Baeldung](https://www.baeldung.com/java-json-binding-api)

## JAXRS

### Get startup and destroy event:

```java
@ApplicationScoped
public class StartupListener {

    public void init(@Observes
                     @Initialized(ApplicationScoped.class) ServletContext context) {
        // Perform action during application's startup
    }

    public void destroy(@Observes
                        @Destroyed(ApplicationScoped.class) ServletContext context) {
        // Perform action during application's shutdown
    }
```

### Unit test when there is CDI

Add a constructor with the injectable bean as parameter:

```java
	@Inject
	private OrderRepository repository;

	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}
```

## Securing communication with SSL

To secure a service exposing end point with TLS. In Java a client can have a client key, it saves is a keystore, and then consume server certificate that needs to match a certificate saved in is truststore. 

Suppose the app expose a SSL end point, so it will have to define a server certificate, and then it will consume other service so it needs to import the other service end point to its truststore so during theSSL handshake, the connection can be estalished. 

### Good articles

* [https://www.baeldung.com/java-ssl](https://www.baeldung.com/java-ssl)
* [oracle doc](https://docs.oracle.com/cd/E54932_01/doc.705/e54936/cssg_create_ssl_cert.htm#CSVSG180)

### Concept summary

SSL is necessary to support the three main information security principles:

* Encryption: protect data transmissions between parties
* Authentication: ensure the server we connect to is indeed the proper server
* Data integrity: guarantee that the requested data is what is effectively delivered

A self-signed certificate is one that you create for your server, in the server's KeyStore.

The truststore is the file containing trusted certificates that Java uses to validate secured connections.

add the public certificate of the server to the default cacerts truststore used by Java. while initiating the SSL connection.
Set the javax.net.ssl.trustStore environment variable to point to the truststore file so that the application can pick up that file which contains the public certificate of the server we are connecting to.
The steps to install a new certificate into the Java default truststore are:


An error like *unable to find valid certification path* to requested target while establishing the SSL connection, it indicates that we don't have the public certificate of the server which we're trying to connect in the Java truststore.

### Summary of what needs to be done

* create a self signed certificate to be the server of your end point

* Import the ca certificate of the server you want to connect to.
    
    * Extract cert from server: `openssl s_client -connect server:443`
    * Import certificate into truststore using keytool: `keytool -import -alias alias.server.com -keystore $JAVA_HOME/jre/lib/security/cacerts` or to a truststore file you can use in a dockerfile, or upload as a truststore and then mount to a pod.

