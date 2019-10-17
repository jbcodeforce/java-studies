# FAQ

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
