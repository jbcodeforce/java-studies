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
