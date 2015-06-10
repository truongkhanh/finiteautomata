package finiteautomata.language;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import finiteautomata.Automata;

public class EmptyChecking {
	public static boolean isEmpty(Automata automata) {
		Set<Integer> acceptingStates = automata.getAcceptingStates();

		// store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();
		workingStates.push(automata.getInitState());

		// check whether a node is visited or not
		boolean[] isVisited = new boolean[automata.getStates().length];
		isVisited[automata.getInitState()] = true;
		while (!workingStates.isEmpty()) {
			int currentState = workingStates.pop();

			if (acceptingStates.contains(currentState)) {
				return false;
			}

			for (int i = 0; i < automata.getNumLabels(); i++) {
				Set<Integer> dests = automata.getStates()[currentState].getDest(i);
				for (int dest : dests) {
					if (!isVisited[dest]) {
						workingStates.push(dest);

						isVisited[dest] = true;
					}
				}
			}
		}

		return true;
	}
	
	/**
	 * Return word which is accepted by automata
	 * Return null if the automata is empty
	 */
	public static List<Integer> getAcceptedWord(Automata automata) {
		boolean isEmpty = true;

		Set<Integer> acceptingStates = automata.getAcceptingStates();

		// store the path from root to current Node
		List<Integer> path = new ArrayList<Integer>();
		// for each node in path, store its depth level
		List<Integer> depthList = new ArrayList<Integer>();

		// store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();
		workingStates.push(automata.getInitState());

		Stack<Integer> labels = new Stack<Integer>();
		int INIT_LABEL = -1;
		labels.push(INIT_LABEL);

		// for each node in workingStates, store its depth level
		Stack<Integer> depthStack = new Stack<Integer>();
		depthStack.push(0);

		// check whether a node is visited or not
		boolean[] isVisited = new boolean[automata.getStates().length];
		isVisited[automata.getInitState()] = true;
		while (!workingStates.isEmpty()) {
			int currentState = workingStates.pop();
			int label = labels.pop();
			int depthLevel = depthStack.pop();

			while (depthList.size() > 0) {
				int lastDepth = depthList.get(depthList.size() - 1);
				if (lastDepth >= depthLevel) {
					// back track a new node, remove nodes not in the path to
					// this node (having depth level greater than or equal its
					// depth level
					depthList.remove(depthList.size() - 1);
					path.remove(path.size() - 1);
				} else {
					break;
				}
			}

			// add this node and its depth level
			path.add(label);
			depthList.add(depthLevel);

			if (acceptingStates.contains(currentState)) {
				isEmpty = false;
				break;
			}

			for (int i = 0; i < automata.getNumLabels(); i++) {
				Set<Integer> dests = automata.getStates()[currentState].getDest(i);
				for (int dest : dests) {
					if (!isVisited[dest]) {
						workingStates.push(dest);
						labels.push(i);
						depthStack.push(depthLevel + 1);

						isVisited[dest] = true;
					}
				}
			}
		}

		if (isEmpty) {
			return null;
		} else {
			// remove dummy
			path.remove(0);

			path = removeEmptyLabel(path);
			return path;
		}
	}

	private static List<Integer> removeEmptyLabel(List<Integer> path) {
		List<Integer> result = new ArrayList<Integer>();
		for(int label: path){
			if(label != Automata.EPSILON_LABEL){
				result.add(label);
			}
		}
		
		return result;
	}
	
	/**
	 * Return shortest word which is accepted by automata
	 * Return null if the automata is empty
	 */
	public static List<Integer> getShortestAcceptedWord(Automata dfa) {
		Set<Integer> acceptingStates = dfa.getAcceptingStates();
				
		//all waiting states
        List<Integer> working = new ArrayList<Integer>();
        //add init
        working.add(dfa.getInitState());
        
        //for each state, store the path from root to it
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        //add path to init
        paths.add(new ArrayList<Integer>());
                
		// check whether a node is visited or not
		boolean [] isVisited = new boolean[dfa.getStates().length];
  		isVisited[dfa.getInitState()] = true;
        while (working.size() > 0)
        {
            int currentState = working.remove(0);
            List<Integer> currentPath = paths.remove(0);
            
            if (acceptingStates.contains(currentState)) {
				return removeEmptyLabel(currentPath);
			}
            
            for (int i = 0; i < dfa.getNumLabels(); i++) {
				Set<Integer> dests = dfa.getStates()[currentState].getDest(i);
				for (int dest : dests) {
					if (!isVisited[dest]) {
						working.add(dest);

						List<Integer> pathToChild = new ArrayList<Integer>(currentPath);
						pathToChild.add(i);
	            		paths.add(pathToChild);
						
						isVisited[dest] = true;
					}
				}
			}
        }


        return null;
	}
}
