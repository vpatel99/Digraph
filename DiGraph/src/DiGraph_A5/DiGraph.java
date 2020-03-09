package DiGraph_A5;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	HashMap<Long, Node> nodesMap  = new HashMap<>(); 
	HashMap<Long, Edge> edgeMap  = new HashMap<>(); 
	HashMap<String, Long> idLabelMap = new HashMap<String, Long>(); // to find nodes
	HashMap<Edge, Long> idEdgeMap = new HashMap<Edge, Long>(); // find exact edges

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }

@Override
public boolean addNode(long idNum, String label) {
	Node newNode = new Node(label);
	if(idNum < 0) {
		return false;
	} else if(nodesMap.containsKey(idNum)) {
		return false;
	} else if(idLabelMap.containsKey(label)) {
		return false;
	} else {
		nodesMap.put(idNum, newNode);
		idLabelMap.put(label, idNum);
		return true;
	}
}


public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if(idNum < 0) { // idNum is negative bad
		return false;
	} else if (edgeMap.containsKey(idNum) || !idLabelMap.containsKey(sLabel) || !idLabelMap.containsKey(dLabel)) {
		return false;
	}
	Long temp;
	temp = idLabelMap.get(sLabel);
	if (nodesMap.get(temp).destinationMap.containsKey(dLabel)){
	return false;
	} else {
		Edge newEdge = new Edge(eLabel, sLabel, dLabel, weight);
		edgeMap.put(idNum, newEdge);
		idEdgeMap.put(newEdge, idNum);
		Long tempLong;
		tempLong = idLabelMap.get(sLabel);
		
		nodesMap.get(tempLong).destinationMap.put(dLabel, newEdge);
		tempLong = idLabelMap.get(dLabel);
		nodesMap.get(tempLong).sourceMap.put(sLabel, newEdge);
		return true;
		
	}
}


public boolean addEdge(long idNum, String sLabel, String dLabel) {
	if(idNum < 0) {
		return false;
	} else if (edgeMap.containsKey(idNum)|| !idLabelMap.containsKey(sLabel) || !idLabelMap.containsKey(dLabel)) {
		return false;
	}
		Long temp;
		temp = idLabelMap.get(sLabel);
	if (nodesMap.get(temp).destinationMap.containsKey(dLabel)){
		return false;
	} else {
		Edge newEdge = new Edge(sLabel, dLabel);
		edgeMap.put(idNum, newEdge);
		idEdgeMap.put(newEdge, idNum);
		Long tempLong;
		tempLong = idLabelMap.get(sLabel);
		nodesMap.get(tempLong).destinationMap.put(dLabel, newEdge);
		tempLong = idLabelMap.get(dLabel);
		nodesMap.get(tempLong).sourceMap.put(sLabel, newEdge);
		return true;
		// iterator to find node to increase the edge counter
		
	}
}
@Override
public boolean delNode(String label) {
	boolean didYouRemove = false;
	if(!idLabelMap.containsKey(label)) {
		return false;
	} else {
		Long keyLong;
		keyLong = idLabelMap.get(label);
		if(nodesMap.containsKey(keyLong)) {
			HashMap<String, Edge> smap = nodesMap.get(keyLong).sourceMap;
			HashMap<String, Edge> dmap = nodesMap.get(keyLong).destinationMap;
			// 1
			Iterator<Map.Entry<String,Edge>> setIterator = smap.entrySet().iterator();
			while(setIterator.hasNext()) {
			    Map.Entry<String,Edge> entry = setIterator.next();
			    Edge tempEdge = entry.getValue();
			    Long tempKey = idEdgeMap.get(tempEdge);
			    if(edgeMap.get(tempKey).dlabel.equals(label)) {
			    	Long tempLong = idLabelMap.get(tempEdge.slabel);
			    	nodesMap.get(tempLong).destinationMap.remove(label);
			    	edgeMap.remove(tempKey);
			    	idEdgeMap.remove(tempEdge);
			    	
			    }
			}
			
			//2
			Iterator<Map.Entry<String,Edge>> setIterator2 = dmap.entrySet().iterator();
			while(setIterator2.hasNext()) {
			    Map.Entry<String,Edge> entry = setIterator2.next();
			    Edge tempEdge = entry.getValue();
			    Long tempKey = idEdgeMap.get(tempEdge);
			    if(edgeMap.get(tempKey).slabel.equals(label)) {
			    	Long tempLong = idLabelMap.get(tempEdge.dlabel);
			    	nodesMap.get(tempLong).sourceMap.remove(label);
			    	edgeMap.remove(tempKey);
			    	idEdgeMap.remove(tempEdge);
			    
			    }
			}
			nodesMap.remove(keyLong);
			idLabelMap.remove(label);
			didYouRemove = true;
		}
		

	}
	return didYouRemove;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	
	if (!idLabelMap.containsKey(sLabel) || !idLabelMap.containsKey(dLabel)) {
		return false;
	}
	Long tempkey;
	tempkey = idLabelMap.get(sLabel);
	if(!nodesMap.get(tempkey).destinationMap.containsKey(dLabel)) {
		return false;
	}
	
	Edge tempEdge = nodesMap.get(tempkey).destinationMap.get(dLabel); 
	tempkey = idEdgeMap.get(tempEdge);
	edgeMap.remove(tempkey);
	tempkey = idLabelMap.get(sLabel);
	nodesMap.get(tempkey).destinationMap.remove(dLabel);
	tempkey = idLabelMap.get(dLabel);
	nodesMap.get(tempkey).sourceMap.remove(sLabel);
	return true;
	
	/*
	Iterator<Map.Entry<Long,Edge>> setIterator = edgeMap.entrySet().iterator();
	while(setIterator.hasNext()) {
	    Map.Entry<Long,Edge> entry = setIterator.next();
	    Long tempkey = entry.getKey();
	    if(edgeMap.get(tempkey).slabel.equals(sLabel) && edgeMap.get(tempkey).dlabel.equals(dLabel)) {
	    	edgeMap.remove(tempkey);
	    	tempkey = idLabelMap.get(sLabel);
	    	nodesMap.get(tempkey).destinationMap.remove(dLabel);
	    	nodesMap.get(tempkey).edgeCount--;
	    	tempkey = idLabelMap.get(dLabel);
	    	nodesMap.get(tempkey).sourceMap.remove(sLabel);
	    	
	    	return true;
	    }
	}
	return false;
	*/
}

@Override
public long numNodes() {
	// TODO Auto-generated method stub
	return nodesMap.size();
}

@Override
public long numEdges() {
	// TODO Auto-generated method stub
	return edgeMap.size();
}
 

@Override
public ShortestPathInfo[] shortestPath(String label) {
	ShortestPathInfo[] finalPathInfos = new ShortestPathInfo[nodesMap.size()];
	if(!idLabelMap.containsKey(label)) {
		return ifNull();
	}
	// resets the known boolean for all the nodes
	Iterator<Map.Entry<Long,Node>> setIterator = nodesMap.entrySet().iterator();
	while(setIterator.hasNext()) {
	    Map.Entry<Long,Node> entry = setIterator.next();
	    Node tempNode = entry.getValue();
	    tempNode.known = false;
	    tempNode.distance = -1;
	}
	long distance = (long) 0;
	MinBinHeap queue = new MinBinHeap();
	ShortestPathInfo temPathInfo = new ShortestPathInfo(label, 0);
	finalPathInfos[0] = temPathInfo;
	queue.insert(new EntryPair(label, 0));
	
	// HashMap<String, Edge> dmap = startingNode.destinationMap;
	
	int i = 0;
	
	while(queue.size() != 0) {
		Long currentLabel = idLabelMap.get(queue.getMin().value);
		Node currentNode = nodesMap.get(currentLabel);
		
		
		distance = queue.getMin().getPriority();
		queue.delMin();
		
		if(currentNode.known) {
			continue;
		} else {
			finalPathInfos[i] = new ShortestPathInfo(currentNode.label, distance);
			currentNode.known = true;
		}
		
		Iterator<Map.Entry<String,Edge>> setIterator2 = currentNode.destinationMap.entrySet().iterator();
		while(setIterator2.hasNext()) {
			Long tempLong = idLabelMap.get(setIterator2.next().getValue().dlabel);
			Node tempNode = nodesMap.get(tempLong);
		    if(!tempNode.known){
		    	Long newLong = distance + currentNode.destinationMap.get(tempNode.label).weight;
		    	
		    	if(tempNode.distance == -1 ||  newLong < tempNode.distance) {
		    		tempNode.distance = newLong;
		    		queue.insert(new EntryPair(tempNode.label, (int) tempNode.distance));
		    	}
		    	
		    	
		    	//distance = distancesMap.get(edgeKey) + tempEdge.weight;
		    	// if(distance < distancesMap.get(tempLong)) distancesMap.replace(tempLong, distance);
		    }
		    
		}
		i++;
		
	}
	
	if(i < numNodes()) {
		for (Node node: nodesMap.values()) {
			if(!node.known) {
				finalPathInfos[i] = new ShortestPathInfo(node.label, -1); // if there is NaN path
				i++;
			}
		}
	}
	
	
	
	return finalPathInfos;
	
	

}

public ShortestPathInfo[] ifNull() {
	int i = 0; 
	ShortestPathInfo[] nullPathInfos = new ShortestPathInfo[nodesMap.size()];
	Iterator<Map.Entry<Long,Node>> setIterator = nodesMap.entrySet().iterator();
	while(setIterator.hasNext()) {
	    Map.Entry<Long,Node> entry = setIterator.next();
	    Node tempNode = entry.getValue();
	    nullPathInfos[i] = new ShortestPathInfo(tempNode.label, -1);
	    i++;
	}
	return nullPathInfos;
}


  // rest of your code to implement the various operations
}