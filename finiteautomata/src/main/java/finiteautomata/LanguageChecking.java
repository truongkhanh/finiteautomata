package finiteautomata;

import java.util.ArrayList;
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
				dests.retainAll(dfa.getAcceptingStates());
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
}
