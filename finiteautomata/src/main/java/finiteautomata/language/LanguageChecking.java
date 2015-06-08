package finiteautomata.language;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import finiteautomata.Automata;
import finiteautomata.AutomataConverter;

public class LanguageChecking {

	/**
	 * Check the nfa is universal
	 * 
	 * @return the word which is not accepted by this dfa
	 */
	public static List<Integer> findUnacceptingWord(Automata automata) {
		Automata dfa = AutomataConverter.toDFA(automata);
		return findUnacceptingWordInDFA(dfa);
	}

	/**
	 * Make sure to convert this automata to dfa
	 * 
	 * @return the word which is not accepted by this dfa
	 */
	public static List<Integer> findUnacceptingWordInDFA(Automata dfa){
		Set<Integer> acceptingStates = dfa.getAcceptingStates();
		
		//store the path from root to current Node
		List<Integer> path = new ArrayList<Integer>();
		//for each node in path, store its depth level
		List<Integer> depthList = new ArrayList<Integer>();

		//store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();
		workingStates.push(dfa.getInitState());
		
		Stack<Integer> labels = new Stack<Integer>();
		int INIT_LABEL = -1;
		labels.push(INIT_LABEL);
		
		//for each node in workingStates, store its depth level
		Stack<Integer> depthStack = new Stack<Integer>();
		depthStack.push(0);

		//check whether a node is visited or not
		boolean [] isVisited = new boolean[dfa.getStates().length];
		isVisited[dfa.getInitState()] = true;
		while(!workingStates.isEmpty()){
			int currentState = workingStates.pop();
			int label = labels.pop();
			int depthLevel = depthStack.pop();

			while(depthList.size() > 0){
					int lastDepth = depthList.get(depthList.size() - 1);
					if(lastDepth >= depthLevel){
						//back track a new node, remove nodes not in the path to this node (having depth level greater than or equal its depth level
						depthList.remove(depthList.size() - 1);
						path.remove(path.size() - 1);
					}
					else{
						break;
					}
			}
			
			//add this node and its depth level
			path.add(label);
			depthList.add(depthLevel);

			for(int i = 1; i < dfa.getNumLabels(); i++){
				Set<Integer> dests = dfa.getStates()[currentState].getDest(i);
				dests.retainAll(acceptingStates);
				
				//if dfa does not accept the label i, it is not universal
				if(dests.isEmpty()){
					path.add(i);
					path.remove(0);
					return path;
				}
				else{
					for(int dest: dests){
						if(!isVisited[dest]){
							workingStates.push(dest);
							labels.push(i);
							depthStack.push(depthLevel + 1);
							
							isVisited[dest] = true;
						}
					}
				}
			}
		}
		

		return null;
	}
	
	public static List<List<Integer>> findShortestUnacceptingWords(Automata automata) {
		Automata dfa = AutomataConverter.toDFA(automata);
		return findShortestUnacceptingWordsInDFA(dfa);
	}
	
	public static List<List<Integer>> findShortestUnacceptingWordsInDFA(Automata dfa) {
		List<Integer> wordMustAccept = findUnacceptingWordInDFA(dfa);
		if(wordMustAccept == null){
			return null;
		}
		
		Set<Integer> acceptingStates = dfa.getAcceptingStates();
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		int shortestWordLength = Integer.MAX_VALUE;
		
		//all waiting states
        List<Integer> working = new ArrayList<Integer>();
        //add init
        working.add(dfa.getInitState());
        
        //for each state, store the path from root to it
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        //add path
        List<Integer> pathToInit = new ArrayList<Integer>();
        paths.add(pathToInit);
                
        while (working.size() > 0)
        {
            int currentState = working.remove(0);
            List<Integer> currentPath = paths.remove(0);
            
			if (shortestWordLength > currentPath.size()) {
				for (int i = 1; i < dfa.getNumLabels(); i++) {
					Set<Integer> dests = dfa.getStates()[currentState].getDest(i);
					dests.retainAll(acceptingStates);

					// if dfa does not accept the label i, it is not universal

					if (dests.isEmpty()) {
						List<Integer> inputMustAccept = new ArrayList<Integer>(currentPath);
						inputMustAccept.add(i);
						result.add(inputMustAccept);

						// set the shortest
						shortestWordLength = Math.min(shortestWordLength, inputMustAccept.size());
					} else {
						for (int dest : dests) {
							working.add(dest);

							List<Integer> pathToChild = new ArrayList<Integer>(currentPath);
							pathToChild.add(i);
		            		paths.add(pathToChild);
						}
					}
				}
			}
        }


        return result;
	}



	

	public static boolean acceptsWord(Automata automata, int[] word) {
		List<Integer> wordAsList = new ArrayList<Integer>();
		for(int label: word){
			wordAsList.add(label);
		}
		return acceptsWord(automata, wordAsList);
	}
	/**
	 * return an accepted word by this automata
	 * 
	 * @return the word which is accepted by this automata
	 */
	public static boolean acceptsWord(Automata automata, List<Integer> word) {
		Set<Integer> acceptingStates = automata.getAcceptingStates();

		Set<Integer> initSet = new HashSet<Integer>();
		initSet.add(automata.getInitState());
		initSet = automata.getEpsilonClosure(initSet);

		// store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();

		// for each node in workingStates, store its depth level
		Stack<Integer> depthStack = new Stack<Integer>();

		for (int initState : initSet) {
			workingStates.push(initState);
			depthStack.push(0);
		}

		while (!workingStates.isEmpty()) {
			int currentState = workingStates.pop();
			int depthLevel = depthStack.pop();

			if (depthLevel == word.size()
					&& acceptingStates.contains(currentState)) {
				return true;
			}

			//
			if (depthLevel < word.size()) {
				Set<Integer> dests = automata.getStates()[currentState]
						.getDest(word.get(depthLevel));
				dests = automata.getEpsilonClosure(dests);
				for (int dest : dests) {
					workingStates.push(dest);
					depthStack.push(depthLevel + 1);
				}
			}
		}

		return false;
	}
}
