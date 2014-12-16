package finiteautomata;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AutomataParser {
	
	public static Automata read(InputStream inputStream){
		//initState numStates numLabels 
		//num Trans
		//source label dest
		//num AcceptingStates
		//accepting states
		
		Scanner scanner = new Scanner(inputStream);
		
		int initState = scanner.nextInt();
		int numStates = scanner.nextInt();
		int numLabels = scanner.nextInt();
		
		
		Automata automata = new Automata(initState, numStates, numLabels);
		
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
	
	public static String write(Automata automata){
		StringBuilder result = new StringBuilder();
		char SPACE = ' ';
		char NEW_LINE = '\n';
		
		result.append(automata.getInitState());
		result.append(SPACE);
		result.append(automata.getStates().length);
		result.append(SPACE);
		result.append(automata.getNumLabels());
		result.append(NEW_LINE);
		
		StringBuilder trans = new StringBuilder();
		int transCount = 0;
		for(int i = 0; i < automata.getStates().length; i++){
			State state = automata.getStates()[i];
			Set<Integer> labels = state.getOutgoingLabels();
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
		
		result.append(automata.getAcceptingStates().size());
		result.append(NEW_LINE);
		for(Integer accepting: automata.getAcceptingStates()){
			result.append(accepting);
			result.append(SPACE);
		}
		
		return result.toString();
	}
}
