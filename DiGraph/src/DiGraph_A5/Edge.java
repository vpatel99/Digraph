package DiGraph_A5;


public class Edge {
	String elabel;
	String slabel;
	String dlabel;
	long weight;
	long key;
	public Edge (String elabel, String slabel, String dlabel, long weight) {
		this.slabel = slabel;
		this.dlabel = dlabel;
		this.weight = weight;
	}
	public Edge (String slabel, String dlabel) {
		this.slabel = slabel;
		this.dlabel = dlabel;
		this.weight = 1;
	}
	
	public String getEdgeLabel() {
		return elabel;
	}
	public String getSourceLabel() {
		return slabel;
	}
	public String getDestinationLabel() {
		return dlabel;
	}
	public long getWeight() {
		return weight;
	}
	
	public String setEdgeLabel(String s) {
		elabel = s;
		return elabel;
	}
	public String setSourceLabel(String s) {
		slabel = s;
		return slabel;
	}
	public String setDestinationLabel(String s) {
		dlabel = s;
		return dlabel;
	}
	public long setWeight(long s) {
		weight = s;
		return weight;
	}
}
