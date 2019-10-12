package jbcodeforce.microprofile.inventory.domain;

import java.util.List;
/**
 * This bean will be map as json
 * {"systems":[],"total":0}
 * 
 */
public class InventoryList {
	  private List<SystemData> systems;

	  public InventoryList(List<SystemData> systems) {
	    this.systems = systems;
	  }

	  public List<SystemData> getSystems() {
	    return systems;
	  }

	  public int getTotal() {
	    return systems.size();
	  }

}
