package visitor;

import java.util.List;

import finiteautomata.language.AntichainInclusionChecking;
import finiteautomata.language.InclusionChecking;

public class SubsetAssertion extends Assertion{

	private LabelAutomata labelAutomata1;
	private LabelAutomata labelAutomata2;

	private List<Integer> counterExample;
	public SubsetAssertion(LabelAutomata labelAutomata1, LabelAutomata labelAutomata2){
		this.labelAutomata1 = labelAutomata1;
		this.labelAutomata2 = labelAutomata2;
	}
	
	public boolean verify() {
		InclusionChecking inclusionChecking = new AntichainInclusionChecking();
		counterExample = inclusionChecking.isSubSet(labelAutomata1.getAutomata(), labelAutomata2.getAutomata());
		
		
		return counterExample == null;
	}

	public String getResult() {
		if(counterExample == null){
			return "Automata " + labelAutomata1.getName() + " is a subset of Automata " + labelAutomata2.getName() + ".";
		}
		StringBuilder result = new StringBuilder();
		result.append("Automata " + labelAutomata1.getName() + " is not a subset of Automata " + labelAutomata2.getName() + ". ");
		result.append("Automata " + labelAutomata2.getName() + " does not accept ");
		
		List<String> labels = labelAutomata1.getLabels(counterExample);
		
		result.append(getLabelWord(labels));
		
		return result.toString();
	}

}
