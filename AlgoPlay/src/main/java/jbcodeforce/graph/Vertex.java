package jbcodeforce.graph;

public class Vertex {

	String label;
	
	public Vertex(String l ) {
		this.label = l;
	}
	
	@Override
	public boolean equals(Object that) {
		if (that instanceof Vertex ) {
			Vertex thatVertex = (Vertex)that;
			return thatVertex.getLabel().equals(this.getLabel());
		}
		return false;
	}

	public String getLabel() {
		return label;
	}
	
	@Override
	public int hashCode() {
		return this.label.hashCode();
	}
}
