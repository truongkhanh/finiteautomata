package visitor;

import java.util.List;

import finiteautomata.language.EmptyChecking;

public class EmptyAssertion extends Assertion{

	private LabelAutomata labelAutomata;
	private boolean isEmpty;
	public EmptyAssertion(LabelAutomata labelAutomata){
		this.labelAutomata = labelAutomata;
	}
	
	public boolean verify(){
		isEmpty = EmptyChecking.isEmpty(labelAutomata.getAutomata());
		
		return isEmpty;
	}
	
	public String getResult() {
		if(isEmpty){
			return "Automata " + labelAutomata.getName() + " is empty.";
		}
		StringBuilder result = new StringBuilder();
		result.append("Automata " + labelAutomata.getName() + " accepts ");
		
		List<Integer> word = EmptyChecking.getShortestAcceptedWord(labelAutomata.getAutomata());
		List<String> labels = labelAutomata.getLabels(word);
		
		result.append(getLabelWord(labels));
		
		return result.toString();
	}
	
	@Override
	public String toString() {
		return "Check whether automata " + labelAutomata.getName() + " is empty";
	}
}
