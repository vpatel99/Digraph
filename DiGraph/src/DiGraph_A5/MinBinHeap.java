package DiGraph_A5;


public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
	// TODO Auto-generated method stub
	array[size + 1] = entry;
	int counter = size + 1;
	size++;
	if(size == 1) {
		return;
	} else {
		int y = (int) Math.floor(counter/2); //y = parent
		int child = counter;
		while(entry.priority < array[y].priority) {
			EntryPair temp = null;
			temp = array[y];
			array[y] = array[child];
			array[child] = temp;
			child = y;
			y = (int) Math.floor(y/2);
			if(y < 1) {
				break;
			}
		}
		
		
		/*
		while(counter > 1) {
			int y = (int) Math.floor(counter/2);
			if(entry.priority < array[y].priority) {
				EntryPair temp = null;
				temp = array[y];
				array[y] = array[counter];
				array[counter] = temp;
			}
			counter = y;
		}
		*/
	}
	
}

@Override
public void delMin() {
	// TODO Auto-generated method stub
	if(size == 0) {
		return;
	} else if(size==1) {
		array[1] = null;
		size--;
	} else {
		array[1] = array[size];
		array[size] = null;
		size--;
		int counter = 1;
		while(counter < size) {
			int lc = 2 * counter;
			int rc = (2 * counter) + 1;
			int lesserchild = 0;
			if(array[lc] == null && array[rc] == null) {
				break;
			}
			if(array[lc] != null && array[rc] == null) {
				lesserchild = lc;
			} else if (array[rc] != null && array[lc] == null) {
				lesserchild = rc;
			} else if (array[lc].priority < array[rc].priority) {
				lesserchild = lc;
			} else {
				lesserchild = rc;
			}
			try {
				if(array[counter].priority > array[lesserchild].priority) {
					EntryPair temp = null;
					temp = array[lesserchild];
					array[lesserchild] = array[counter];
					array[counter] = temp;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			counter = lesserchild;
			
		}
		
	}
}

@Override
public EntryPair getMin() {
	if(size == 0) {
		return null;
	} else {
		return array[1];
	}
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return size;
}

@Override
public void build(EntryPair[] entries) {
	// TODO Auto-generated method stub
	
	
	for(int i = 0; i < entries.length; i++) {
		array[i+1] = entries[i];
		size++;
	}
	
	int child = size;
	int parentindex = (int) Math.floor(child/2);
	int lesserchild = 0;
	

	while(parentindex >= 1) {
		int lc = 2 * parentindex;
		int rc = (2 * parentindex) + 1;
	if(array[lc] == null && array[rc] == null) {
		break;
	}
	if(array[lc] != null && array[rc] == null) {
		lesserchild = lc;
	} else if (array[rc] != null && array[lc] == null) {
		lesserchild = rc;
	} else if (array[lc].priority < array[rc].priority) {
		lesserchild = lc;
	} else {
		lesserchild = rc;
	}
	try {
		if(array[parentindex].priority > array[lesserchild].priority) {
			EntryPair temp = null;
			temp = array[lesserchild];
			array[lesserchild] = array[parentindex];
			array[parentindex] = temp;
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	int otherindex = lesserchild;
	int lchild = 2 * otherindex;
	int rchild = (2 * otherindex) + 1;
	parentindex = (int) Math.floor((lc-1)/2);
	lc = 2 * parentindex;
	rc = (2 * parentindex) + 1;
	
		while(array[lchild] != null || array[rchild] != null) {
			if(array[lchild] == null && array[rchild] == null) {
				break;
			}
			if(array[lchild] != null && array[rchild] == null) {
				lesserchild = lchild;
			} else if (array[rchild] != null && array[lchild] == null) {
				lesserchild = rchild;
			} else if (array[lchild].priority < array[rchild].priority) {
				lesserchild = lchild;
			} else {
				lesserchild = rchild;
			}
			try {
				if(array[otherindex].priority > array[lesserchild].priority) {
					EntryPair temp = null;
					temp = array[lesserchild];
					array[lesserchild] = array[otherindex];
					array[otherindex] = temp;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			otherindex = lesserchild;
			lchild = 2 * otherindex;
			rchild = (2 * otherindex) + 1;
		}
	
	}
 
	
	
	
}




}