package DiGraph_A5;

import java.util.HashMap;

public class Node {

	String label;
	long key;
	boolean known;
	long distance = -1;
	HashMap<String, Edge> sourceMap = new HashMap<String, Edge>();
	HashMap<String, Edge> destinationMap = new HashMap<String, Edge>();
	public Node(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public long getKey() {
		return key;
	}
	public long setKey(long idNum) {
		key = idNum;
		return key;
	}
}
