package finiteautomata.language;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import finiteautomata.Automata;
import finiteautomata.State;

public class AntichainInclusionChecking implements InclusionChecking{
	
	/**
	 * Check whether the language of automataA is a subset of the language of
	 * automataB
	 * 
	 * @return if not subset, return the word accepted by automataA, but not
	 *         automataB
	 */
	public List<Integer> isSubSet(Automata automataA, Automata automataB) {
		// get accepting states
		Set<Integer> acceptingStates1 = automataA.getAcceptingStates();
		Set<Integer> acceptingStates2 = automataB.getAcceptingStates();

		// store the path from root to current Node
		List<Integer> path = new ArrayList<Integer>();
		// for each node in path, store its depth level
		List<Integer> depthList = new ArrayList<Integer>();

		// check whether a node is visited or not
		List<CompositionState> processedStates = new ArrayList<CompositionState>();
		Stack<CompositionState> nextStates = new Stack<CompositionState>();
		
		CompositionState state = getInitialCompositionState(automataA, automataB);
		nextStates.add(state);
		
		while (!nextStates.isEmpty()) {
			CompositionState currentState = nextStates.pop();
			processedStates.add(currentState);

			int currentDepth = currentState.getDepth();
			removeOldInformation(currentDepth, path, depthList);

			// add this node and its depth level
			path.add(currentState.getLabel());
			depthList.add(currentState.getDepth());

			// check acceptance condition, reach a state whether word is
			// accepted by automata1, but not automata2
			if (isAcceptingState(currentState, acceptingStates1, acceptingStates2)) {
				path.remove(0);
				return path;
			}

			State stateA = automataA.getStates()[currentState.getStateA()];
			for (int nextLabel : stateA.getOutgoingLabels()) {
				Set<Integer> destsA = stateA.getDest(nextLabel);
				Set<Integer> destsB = automataB.getDests(currentState.getStateB(), nextLabel);
				destsB = automataB.getEpsilonClosure(destsB);

				for (int dest1 : destsA) {
					CompositionState newState = new CompositionState(dest1, destsB, nextLabel, currentState.getDepth() + 1);
					
					if(needToVisit(newState, processedStates) && needToVisit(newState, nextStates)){
						
						removeSimulatedState(newState, processedStates);
						removeSimulatedState(newState, nextStates);
						
						nextStates.push(newState);
					}
				}
			}
		}

		return null;
	}

	private CompositionState getInitialCompositionState(Automata automata1,
			Automata automata2) {
		int initA = automata1.getInitState();

		Set<Integer> init2AsSet = new HashSet<Integer>();
		init2AsSet.add(automata2.getInitState());
		init2AsSet = automata2.getEpsilonClosure(init2AsSet);
		
		int initLabel = -1;
		int initDepth = 0;
		
		CompositionState state = new CompositionState(initDepth, init2AsSet, initA, initLabel);
		return state;
	}

	private void removeOldInformation(int currentDepth, List<Integer> path,
			List<Integer> depthList) {
		while (depthList.size() > 0) {
			int lastDepth = depthList.get(depthList.size() - 1);
			if (lastDepth >= currentDepth) {
				// back track a new node, remove nodes not in the path to
				// this node (having depth level greater than or equal its
				// depth level
				depthList.remove(depthList.size() - 1);
				path.remove(path.size() - 1);
			} else {
				break;
			}
		}
	}
	
	private boolean isAcceptingState(CompositionState state, Set<Integer> acceptingStates1,
			Set<Integer> acceptingStates2) {
		return acceptingStates1.contains(state.getStateA())
				&& !containsAcceptingState(state.getStateB(), acceptingStates2);
	}

	private boolean containsAcceptingState(Set<Integer> states, Set<Integer> accepting){
		for(int state: states){
			if(accepting.contains(state)){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean needToVisit(CompositionState newState, List<CompositionState> states){
		for(CompositionState state: states){
			if(isSimulated(newState, state)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Check whether state1 is simulated by state2
	 */
	private boolean isSimulated(CompositionState state1, CompositionState state2){
		return state1.getStateA() == state2.getStateA() && state1.getStateB().containsAll(state2.getStateB());
	}
	
	private void removeSimulatedState(CompositionState newState, List<CompositionState> states){
		for(int i = states.size() - 1; i >= 0; i--){
			CompositionState state = states.get(i);
			if(isSimulated(state, newState)){
				states.remove(i);
			}
		}
	}
	
	private class CompositionState{
		private int stateA;
		private Set<Integer> stateB;
		private int label;
		private int depth;
		
		public CompositionState(int stateA, Set<Integer> stateB, int label, int depth){
			this.stateA = stateA;
			this.stateB = stateB;
			this.label = label;
			this.depth = depth;
		}
		
		public int getStateA() {
			return stateA;
		}

		public Set<Integer> getStateB() {
			return stateB;
		}

		public int getLabel() {
			return label;
		}

		public int getDepth() {
			return depth;
		}	
		
		public String toString(){
			return stateA + "-" + stateB.toString();
		}
	}
}
