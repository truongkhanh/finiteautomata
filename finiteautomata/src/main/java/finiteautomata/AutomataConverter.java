package finiteautomata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AutomataConverter {
	public static Automata toDFA(Automata automata){
		List<State> allStatesDFA = new ArrayList<State>();
		Map<Set<Integer>, State> mapStates = new HashMap<Set<Integer>, State>();
		
		Stack<Set<Integer>> workingStates = new Stack<Set<Integer>>();
		Set<Integer> initSet = automata.getEpsilonClosure(automata.getInitState());
		
		workingStates.push(initSet);
		
		//state 0 will be the init state in new DFA
		State initInDFA = new State(0);
		mapStates.put(initSet, initInDFA);
		allStatesDFA.add(initInDFA);
		
		while(!workingStates.isEmpty()){
			Set<Integer> statesInNFA = workingStates.pop();
			State stateInDFA = mapStates.get(statesInNFA);
			
			for(int i = 1; i < automata.getNumLabels(); i++){
				Set<Integer> destsInNFA = automata.getEpsilonClosure(automata.getDests(statesInNFA, i));
				
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
		
		Automata dfa = new Automata(0, allStatesDFA, automata.getNumLabels());
		
		//compute accepting states
		Set<Integer> acceptingDFA = new HashSet<Integer>();
		for(Set<Integer> statesNFA: mapStates.keySet()){
			for(Integer stateNFA: statesNFA){
				if(automata.getAcceptingStates().contains(stateNFA)){
					acceptingDFA.add(mapStates.get(statesNFA).getId());
					break;
				}
			}
		}
		dfa.setAcceptingStates(acceptingDFA);
		
		//
		return dfa;
	}
	
	public static Automata toCompleteDFA(Automata dfa){
		int init = dfa.getInitState();
		List<State> states = new ArrayList<State>(Arrays.asList(dfa.getStates()));
		int numberOfLabels = dfa.getNumLabels();
		Set<Integer> accepting = dfa.getAcceptingStates();
		
		State dummyState = new State(states.size());
		
		//loop at dummy
		for(int i = 1; i < numberOfLabels; i++){
			dummyState.addTrans(i, dummyState.getId());
		}
		
		//complement transitions to dummy
		for(State state: states){
			Set<Integer> nextLabels = state.getOutgoingLabels();
			for(int i = 1; i < numberOfLabels; i++){
				if(!nextLabels.contains(i)){
					state.addTrans(i, dummyState.getId());
				}
			}
		}
		
		//
		states.add(dummyState);
		Automata result = new Automata(init, states, numberOfLabels);
		result.setAcceptingStates(accepting);
		
		return result;
	}
}
