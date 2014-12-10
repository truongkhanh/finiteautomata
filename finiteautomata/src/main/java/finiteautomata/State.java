package finiteautomata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
	private int id;
	
	private Map<Integer, Set<Integer>> outgoingTrans;
	
	public State(int id){
		this.setId(id);
		this.outgoingTrans = new HashMap<Integer, Set<Integer>>();
	}

	public void addTrans(int label, int dest){
		Set<Integer> destSet = outgoingTrans.get(label);
		if(destSet == null){
			destSet = new HashSet<Integer>();
			outgoingTrans.put(label, destSet);
		}
		
		destSet.add(dest);
	}
	
	public boolean hasDest(int label){
		return outgoingTrans.containsKey(label);
	}
	
	public Set<Integer> getDest(int label){
		Set<Integer> dest = outgoingTrans.get(label);
		if(dest != null){
			return dest;
		}
		else{
			return new HashSet<Integer>();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Set<Integer> outgoingLabels(){
		return outgoingTrans.keySet();
	}
}
