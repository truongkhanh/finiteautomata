package visitor;

import java.util.List;

import finiteautomata.Automata;
import finiteautomata.AutomataConverter;
import finiteautomata.language.InclusionChecking;
import finiteautomata.language.InclusionCheckingImpl;

public class SubsetAssertion extends Assertion{

	private LabelAutomata labelAutomata1;
	private LabelAutomata labelAutomata2;
	private Automata completeDFA2;
	
	private boolean isSubset;
	public SubsetAssertion(LabelAutomata labelAutomata1, LabelAutomata labelAutomata2){
		this.labelAutomata1 = labelAutomata1;
		this.labelAutomata2 = labelAutomata2;
		
		completeDFA2 = this.labelAutomata2.getAutomata();
		if(!completeDFA2.isDFA()){
			completeDFA2 = AutomataConverter.toDFA(completeDFA2);
		}
		if(!completeDFA2.isCompleteDFA()){
			completeDFA2 = AutomataConverter.toCompleteDFA(completeDFA2);
		}
	}
	
	public boolean verify() {
		InclusionChecking inclusionChecking = new InclusionCheckingImpl();
		isSubset = inclusionChecking.isSubSet(labelAutomata1.getAutomata(), completeDFA2);
		
		return isSubset;
	}

	public String getResult() {
		if(isSubset){
			return "Automata " + labelAutomata1.getName() + " is a subset of Automata " + labelAutomata2.getName() + ".";
		}
		
		List<Integer> counterExample = new InclusionCheckingImpl().findShortestCounterExample(labelAutomata1.getAutomata(), completeDFA2);
		List<String> labels = labelAutomata1.getLabels(counterExample);
		
		StringBuilder result = new StringBuilder();
		result.append("Automata " + labelAutomata1.getName() + " is not a subset of Automata " + labelAutomata2.getName() + ". ");
		result.append("Automata " + labelAutomata2.getName() + " does not accept ");
		
		result.append(getLabelWord(labels));
		
		return result.toString();
	}
	
	@Override
	public String toString() {
		return "Check whether automata " + labelAutomata1.getName() + " is subset of automata " + labelAutomata2.getName();
	}

}
