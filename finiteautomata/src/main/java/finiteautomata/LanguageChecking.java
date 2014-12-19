package finiteautomata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LanguageChecking {

	/**
	 * Check the nfa is universal
	 * @return the word which is not accepted by this dfa
	 */
	public static List<Integer> isUniversal(Automata automata){
		Automata dfa = AutomataConverter.toDFA(automata);
		return isDFAUniversal(dfa);
	}
	
	/**
	 * Make sure to convert this automata to dfa
	 * @return the word which is not accepted by this dfa
	 */
	public static List<Integer> isDFAUniversal(Automata dfa){
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
		while(!workingStates.isEmpty()){
			int currentState = workingStates.pop();
			int label = labels.pop();
			int depthLevel = depthStack.pop();

			isVisited[currentState] = true;
			
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
						}
					}
				}
				
			}
		}
		

		return new ArrayList<Integer>();
	}
	
	/**
	 * return an accepted word by this automata
	 * @return the word which is accepted by this automata
	 */
	public static List<Integer> getAcceptedWord(Automata automata){
		boolean isEmpty = true;
		
		Set<Integer> acceptingStates = automata.getAcceptingStates();
		
		//store the path from root to current Node
		List<Integer> path = new ArrayList<Integer>();
		//for each node in path, store its depth level
		List<Integer> depthList = new ArrayList<Integer>();

		//store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();
		workingStates.push(automata.getInitState());
		
		Stack<Integer> labels = new Stack<Integer>();
		int INIT_LABEL = -1;
		labels.push(INIT_LABEL);
		
		//for each node in workingStates, store its depth level
		Stack<Integer> depthStack = new Stack<Integer>();
		depthStack.push(0);

		//check whether a node is visited or not
		boolean [] isVisited = new boolean[automata.getStates().length];
		while(!workingStates.isEmpty()){
			int currentState = workingStates.pop();
			int label = labels.pop();
			int depthLevel = depthStack.pop();

			isVisited[currentState] = true;
			
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
			

			if(acceptingStates.contains(currentState)){
				isEmpty = false;
				break;
			}
			
			for(int i = 0; i < automata.getNumLabels(); i++){
				Set<Integer> dests = automata.getStates()[currentState].getDest(i);
				for(int dest: dests){
					if(!isVisited[dest]){
						workingStates.push(dest);
						labels.push(i);
						depthStack.push(depthLevel + 1);
					}
				}
			}
		}
		

		if(isEmpty){
			return null;
		}
		else{
			//remove dummy
			path.remove(0);
			
			//remove empty letter
			for(int i = path.size() - 1; i >= 0; i--){
				if(path.get(i) == 0){
					path.remove(i);
				}
			}
			return path;
		}
	} 
	
	/**
	 * return an accepted word by this automata
	 * @return the word which is accepted by this automata
	 */
	public static boolean isEmpty (Automata automata){
		Set<Integer> acceptingStates = automata.getAcceptingStates();
		
		//store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();
		workingStates.push(automata.getInitState());


		//check whether a node is visited or not
		boolean [] isVisited = new boolean[automata.getStates().length];
		while(!workingStates.isEmpty()){
			int currentState = workingStates.pop();

			isVisited[currentState] = true;
			
			if(acceptingStates.contains(currentState)){
				return false;
			}
			
			for(int i = 0; i < automata.getNumLabels(); i++){
				Set<Integer> dests = automata.getStates()[currentState].getDest(i);
				for(int dest: dests){
					if(!isVisited[dest]){
						workingStates.push(dest);
					}
				}
			}
		}
		

		return true;
	} 
	
	/**
	 * return an accepted word by this automata
	 * @return the word which is accepted by this automata
	 */
	public static boolean acceptWord(Automata automata, int[] word){
		Set<Integer> acceptingStates = automata.getAcceptingStates();
		
		Set<Integer> initSet = new HashSet<Integer>();
		initSet.add(automata.getInitState());
		initSet = automata.getEpsilonClosure(initSet);
		
		//store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();
		
		//for each node in workingStates, store its depth level
		Stack<Integer> depthStack = new Stack<Integer>();
		
		for(int initState: initSet){
			workingStates.push(initState);
			depthStack.push(0);
		}

		while(!workingStates.isEmpty()){
			int currentState = workingStates.pop();
			int depthLevel = depthStack.pop();

			if(depthLevel == word.length && acceptingStates.contains(currentState)){
				return true;
			}
			
			//
			if(depthLevel < word.length){
				Set<Integer> dests = automata.getStates()[currentState].getDest(word[depthLevel]);
				dests = automata.getEpsilonClosure(dests);
				for(int dest: dests){
					workingStates.push(dest);
					depthStack.push(depthLevel + 1);
				}
			}
		}
		

		return false;
	} 
}
