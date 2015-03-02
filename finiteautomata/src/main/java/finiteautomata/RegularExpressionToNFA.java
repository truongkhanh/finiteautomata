package finiteautomata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegularExpressionToNFA {
	public Automata getAutomataFromRegularExpression(String expression){
		String[] lines = expression.split("\n");
		
		int numberOfLabels = Integer.parseInt(lines[0]);
		
		List<State> states = new ArrayList<State>();
		Set<Integer> acceptings = new HashSet<Integer>();
		
		State initialState = new State(0);
		states.add(initialState);
		
		State[] tempInitials = new State[lines.length - 1];
		for(int i = 1; i < lines.length; i++){
			tempInitials[i-1] = new State(i);
			
			initialState.addTrans(Automata.EPSILON_LABEL, i);
			states.add(tempInitials[i-1]);
		}
		
		for(int i = 1; i < lines.length; i++){
			List<Term> terms = handleLine(lines[i]);
			
			State currentState = tempInitials[i-1];
			State lastState = handleTerms(terms, currentState, states);
			acceptings.add(lastState.getId());
		}
		
		Automata result =  new Automata(0, states, numberOfLabels);
		result.setAcceptingStates(acceptings);
		
		return result;
	}
	

	
	private List<Term> handleLine(String line){
		List<Term> result = new ArrayList<RegularExpressionToNFA.Term>();
		
		int run = 0;
		
		int lastInteger = -1;
		while(run < line.length()){
			if(isStart(line.charAt(run))){
				result.add(new IntegerStarTerm(lastInteger));
				
				//ignore space
				run += 2;
			}
			else if(isPower(line.charAt(run))){
				int lastDigitIndex = getLastDigitIndex(line, run + 1);
				int numberOfRepeats = Integer.parseInt(line.substring(run+1, lastDigitIndex+1));
				result.add(new IntegerSequence(lastInteger, numberOfRepeats));
				
				//ignore the space
				run = lastDigitIndex + 2;
			}
			else{
				int lastDigitIndex = getLastDigitIndex(line, run);
				int newInteger = Integer.parseInt(line.substring(run, lastDigitIndex+1));
				
				if(lastDigitIndex + 1 >= line.length() || isSpace(line.charAt(lastDigitIndex+1))){
					result.add(new IntegerTerm(newInteger));
					
					//ignore space
					run = lastDigitIndex + 2;
				}
				else{
					run = lastDigitIndex+1;
					lastInteger = newInteger;
				}
			}
		}
		
		return result;
	}
	
	private State handleTerms(List<Term> terms, State initialState, List<State> states){
		for(Term term: terms){
			State nextState = term.addEdge(initialState, states);
			initialState = nextState;
		}
		
		return initialState;
	}
	
	private int getLastDigitIndex(String line, int start){
		int temp = start;
		while(temp < line.length() && isDigit(line.charAt(temp))){
			temp++;
		}
		
		return temp - 1;
		
	}
	
	private boolean isSpace(char c){
		return c == ' ';
	}
	private boolean isDigit(char c){
		return c >= '0' && c <= '9';
	}
	
	private boolean isStart(char c){
		return c == '*';
	}
	
	private boolean isPower(char c){
		return c == '^';
	}
	
	private abstract class Term{
		/**
		 * Add the next edge from currentState based on the term
		 * @return the destination state index
		 */
		public abstract State addEdge(State currentState, List<State> states);
	}
	
	
	private class IntegerTerm extends Term{
		private int value;
		
		public IntegerTerm(int value){
			this.value = value;
		}
		
		public State addEdge(State currentState, List<State> states){
			State nextState = new State(currentState.getId() + 1);
			currentState.addTrans(value, nextState.getId());
			states.add(nextState);
			return nextState;
		}
	}
	
	private class IntegerStarTerm extends Term{
		private int value;
		
		public IntegerStarTerm(int value){
			this.value = value;
		}
		
		public State addEdge(State currentState, List<State> states){
			State nextState = new State(currentState.getId() + 1);
			currentState.addTrans(Automata.EPSILON_LABEL, nextState.getId());
			nextState.addTrans(value, nextState.getId());
			
			states.add(nextState);
			return nextState;
		}
	}
	
	private class IntegerSequence extends Term{
		private int value;
		private int numberOfRepeats;
		
		public IntegerSequence(int value, int numberOfRepeats){
			this.value = value;
			this.numberOfRepeats = numberOfRepeats;
			
			if(numberOfRepeats <= 0){
				throw new IllegalArgumentException("numberOfRepeat must be positive!");
			}
		}
		
		public State addEdge(State currentState, List<State> states){
			State nextState = null;
			for(int i = 0; i < numberOfRepeats; i++){
				nextState = new State(currentState.getId() + 1);
				currentState.addTrans(value, nextState.getId());
				
				states.add(nextState);
				currentState = nextState;
			}
			
			return nextState;
		}
	}
}

