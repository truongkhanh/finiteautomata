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
			
			for(int i = 0; i < automata.getNumLabels(); i++){
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
		
		Automata dfa = new Automata(initInDFA.getId(), allStatesDFA, automata.getNumLabels());
		
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
		int numberOfLabels = dfa.getNumLabels();
		List<State> states = new ArrayList<State>(Arrays.asList(dfa.getStates()));
		Set<Integer> accepting = dfa.getAcceptingStates();
		
		Automata result = new Automata(init, states.size() + 1, numberOfLabels);
		result.setAcceptingStates(new HashSet<Integer>(accepting));
		
		int dummyState = states.size();
		
		//copy transition
		for(State state: states){
			for(int i = 0; i < numberOfLabels; i++){
				Set<Integer> nexts = state.getDest(i);
				if(nexts.isEmpty()){
					//add transition to dummy
					result.addTrans(state.getId(), i, dummyState);
				}
				else{
					for(int next: nexts){
						result.addTrans(state.getId(), i, next);
					}
				}
			}
		}
		
		//loop at dummy
		for(int i = 0; i < numberOfLabels; i++){
			result.addTrans(dummyState, i, dummyState);
		}
				
		return result;
	}
	
	public static Automata getComplement(Automata automata){
		if(!automata.isDFA()){
			automata = toDFA(automata);
		}
		
		if(!automata.isCompleteDFA()){
			automata = toCompleteDFA(automata);
		}
		
		Automata result = new Automata(automata.getInitState(), Arrays.asList(automata.getStates()), automata.getNumLabels());
		
		Set<Integer> acceptings = automata.getAcceptingStates();
		Set<Integer> complementAccepting = new HashSet<Integer>();
		for(int state = 0; state < automata.getStates().length; state++){
			if(!acceptings.contains(state)){
				complementAccepting.add(state);
			}
		}
		
		result.setAcceptingStates(complementAccepting);
		
		return result;
	}

}
