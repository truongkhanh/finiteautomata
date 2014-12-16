package finiteautomata;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * States are labeled from 0 to V-1 where V is number of states.
 * Labels are numbered from 0. Label 0 is preserved for epsilon label (empty label)
 * @author khanh
 *
 */
public class Automata {
	public State[] getStates() {
		return states;
	}


	public int getNumLabels() {
		return numLabels;
	}


	public Set<Integer> getAcceptingStates() {
		return acceptingStates;
	}

	
	
	public int getInitState() {
		return initState;
	}


	public void setInitState(int initState) {
		this.initState = initState;
	}

	/**
	 * Init state index
	 */
	private int initState;
	
	/**
	 * State with outgoing transition
	 */
	private State[] states;
	
	/**
	 * Number of labels in this automata. 0 for lambda (empty) label
	 */
	private int numLabels;
	
	/**
	 * Set of accepting states
	 */
	private Set<Integer> acceptingStates;
	
	public Automata(int initState, int numStates, int numLabels){
		this.initState = initState;
		this.states = new State[numStates];
		for(int i = 0; i < numStates; i++){
			this.states[i] = new State(i);
		}
		
		this.numLabels = numLabels;
	}
	
	public Automata(int initState, State[] states, int numLabels){
		this.initState = initState;
		this.states = states;
		this.numLabels = numLabels;
	}
	
	public Automata(int initState, List<State> states, int numLabels){
		this.initState = initState;
		this.states = new State[states.size()];
		for(int i = 0; i < states.size(); i++){
			this.states[i] = states.get(i);
		}
		this.numLabels = numLabels;
	}
	
	/**
	 * Add transitions from source to dest with label
	 * @param source
	 * @param label
	 * @param dest
	 */
	public void addTrans(int source, int label, int dest){
		this.states[source].addTrans(label, dest);
	}

	/**
	 * Set accepting states
	 * @param acceptingStates
	 */
	public void setAcceptingStates(Collection<Integer> acceptingStates){
		this.acceptingStates = new HashSet<Integer>(acceptingStates);
	}
	
	/**
	 * Get set of destinations from sources by transitions with label
	 * @param sources
	 * @param label
	 * @return
	 */
	public Set<Integer> getDests(Set<Integer> sources, int label){
		Set<Integer> result = new HashSet<Integer>();
		for(Integer source: sources){
			result.addAll(states[source].getDest(label));
		}
		
		return result;
	}
	
	/**
	 * Compute epsilon closure from a set of states
	 * @param fromStates
	 * @return
	 */
	public Set<Integer> getEpsilonClosure(Set<Integer> fromStates){
		Set<Integer> result = new HashSet<Integer>();
		
		Stack<Integer> workingStates = new Stack<Integer>();
		workingStates.addAll(fromStates);
		
		boolean [] isVisited = new boolean[states.length];
		while(!workingStates.isEmpty()){
			int currentState = workingStates.pop();
			isVisited[currentState] = true;

			result.add(currentState);
			
			//add new states to workingState
			for(int child: states[currentState].getDest(0)){
				if(!isVisited[child]){
					workingStates.push(child);
					
				}
			}
		}
		
		return result;
	}
}
