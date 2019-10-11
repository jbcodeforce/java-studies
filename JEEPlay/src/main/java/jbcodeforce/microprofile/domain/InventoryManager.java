package jbcodeforce.microprofile.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

// singleton to manage the list of systems and add new system data
@ApplicationScoped
public class InventoryManager {
	private List<SystemData> systems = Collections.synchronizedList(new ArrayList<>());

	  public void add(String hostname, Properties systemProps) {
	    Properties props = new Properties();
	    props.setProperty("os.name", systemProps.getProperty("os.name"));
	    props.setProperty("user.name", systemProps.getProperty("user.name"));

	    SystemData system = new SystemData(hostname, props);
	    if (!systems.contains(system)) {
	      systems.add(system);
	    }
	  }

	  public InventoryList list() {
	    return new InventoryList(systems);
	  }
}
