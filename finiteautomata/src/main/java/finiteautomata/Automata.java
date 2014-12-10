package finiteautomata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * States are numbered from 0. State 0 is the initial state
 * Labels are numbered from 0. Label 0 is preserved for epsilon label (empty label)
 * @author khanh
 *
 */
public class Automata {
	private State[] states;
	private int numLabels;
	private Set<Integer> acceptingStates;
	
	public Automata(int numStates, int numLabels){
		this.states = new State[numStates];
		for(int i = 0; i < numStates; i++){
			this.states[i] = new State(i);
		}
		
		this.numLabels = numLabels;
	}
	
	public static Automata loadFromFile(String fileName) throws FileNotFoundException{
		//numStates numLabels
		//num Trans
		//source label dest
		//num AcceptingStates
		//accepting states
		
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		int numStates = scanner.nextInt();
		int numLabels = scanner.nextInt();
		
		Automata automata = new Automata(numStates, numLabels);
		
		int numTrans = scanner.nextInt();
		for(int i = 0; i < numTrans; i++){
			int source = scanner.nextInt();
			int label = scanner.nextInt();
			int dest = scanner.nextInt();
			
			automata.addTrans(source, label, dest);
		}
		
		int numAcceptingStates = scanner.nextInt();
		Set<Integer> acceptingStates = new HashSet<Integer>();
		for(int i = 0; i < numAcceptingStates; i++){
			int accepting = scanner.nextInt();
			acceptingStates.add(accepting);
		}
		
		automata.setAcceptingStates(acceptingStates);
		
		scanner.close();
		
		//
		return automata;
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		char SPACE = ' ';
		char NEW_LINE = '\n';
		
		result.append(states.length);
		result.append(SPACE);
		result.append(numLabels);
		result.append(NEW_LINE);
		
		StringBuilder trans = new StringBuilder();
		int transCount = 0;
		for(int i = 0; i < states.length; i++){
			State state = states[i];
			Set<Integer> labels = state.outgoingLabels();
			for(Integer label: labels){
				Set<Integer> dests = state.getDest(label);
				
				for(Integer dest: dests){
					trans.append(state.getId());
					trans.append(SPACE);
					trans.append(label);
					trans.append(SPACE);
					trans.append(dest);
					trans.append(NEW_LINE);
					
					transCount++;
				}
			}
		}
		result.append(transCount);
		result.append(NEW_LINE);
		result.append(trans);
		
		result.append(acceptingStates.size());
		result.append(NEW_LINE);
		for(Integer accepting: acceptingStates){
			result.append(accepting);
			result.append(SPACE);
		}
		
		return result.toString();
	}
	
	public void addTrans(int source, int label, int dest){
		this.states[source].addTrans(label, dest);
	}
	
	public void setState(int stateIndex, State state){
		this.states[stateIndex] = state;
	}
	
	public void setAcceptingStates(Collection<Integer> acceptingStates){
		this.acceptingStates = new HashSet<Integer>(acceptingStates);
	}
	
	public Set<Integer> getDests(Set<Integer> sources, int label){
		Set<Integer> result = new HashSet<Integer>();
		for(Integer source: sources){
			result.addAll(states[source].getDest(label));
		}
		
		return result;
	}
	
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
	
	public Automata toDFA(){
		List<State> allStatesDFA = new ArrayList<State>();
		Map<Set<Integer>, State> mapStates = new HashMap<Set<Integer>, State>();
		
		Stack<Set<Integer>> workingStates = new Stack<Set<Integer>>();
		Set<Integer> initSet = new HashSet<Integer>();
		initSet.add(0);
		initSet = getEpsilonClosure(initSet);
		
		workingStates.push(initSet);
		
		State initInDFA = new State(0);
		mapStates.put(initSet, initInDFA);
		allStatesDFA.add(initInDFA);
		
		while(!workingStates.isEmpty()){
			Set<Integer> statesInNFA = workingStates.pop();
			State stateInDFA = mapStates.get(statesInNFA);
			
			for(int i = 1; i < numLabels; i++){
				Set<Integer> destsInNFA = getEpsilonClosure(getDests(statesInNFA, i));
				
				if(!destsInNFA.isEmpty()){
					State destInDFA = mapStates.get(destsInNFA);
					
					if(destInDFA == null){
						destInDFA = new State(mapStates.size());
						mapStates.put(destsInNFA, destInDFA);
						allStatesDFA.add(destInDFA);
						
						//new
						workingStates.push(destsInNFA);
					}
					
					stateInDFA.addTrans(i, destInDFA.getId());
				}
			}
		}
		
		Automata dfa = new Automata(allStatesDFA.size(), numLabels);
		for(int i = 0; i < allStatesDFA.size(); i++){
			dfa.setState(i, allStatesDFA.get(i));
		}
		
		//compute accepting states
		Set<Integer> acceptingDFA = new HashSet<Integer>();
		for(Set<Integer> statesNFA: mapStates.keySet()){
			for(Integer stateNFA: statesNFA){
				if(this.acceptingStates.contains(stateNFA)){
					acceptingDFA.add(mapStates.get(statesNFA).getId());
					break;
				}
			}
		}
		dfa.setAcceptingStates(acceptingDFA);
		
		//
		return dfa;
	}
	
	/**
	 * Check the nfa is universal
	 * @return the word which is not accepted by this dfa
	 */
	public List<Integer> isUniversal(){
		Automata dfa = toDFA();
		return dfa.isDFAUniversal();
	}
	
	/**
	 * Make sure to convert this automata to dfa
	 * @return the word which is not accepted by this dfa
	 */
	public List<Integer> isDFAUniversal(){		
		//store the path from root to current Node
		List<Integer> path = new ArrayList<Integer>();
		//for each node in path, store its depth level
		List<Integer> depthList = new ArrayList<Integer>();

		//store nodes waiting to visit
		Stack<Integer> workingStates = new Stack<Integer>();
		workingStates.push(0);
		
		Stack<Integer> labels = new Stack<Integer>();
		int INIT_LABEL = -1;
		labels.push(INIT_LABEL);
		
		//for each node in workingStates, store its depth level
		Stack<Integer> depthStack = new Stack<Integer>();
		depthStack.push(0);

		//check whether a node is visited or not
		boolean [] isVisited = new boolean[states.length];
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

			for(int i = 1; i < numLabels; i++){
				Set<Integer> dests = states[currentState].getDest(i);
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
}
