package visitor;

import java.util.List;

import finiteautomata.Automata;
import finiteautomata.AutomataConverter;
import finiteautomata.language.UniversalChecking;

public class UniversalAssertion extends Assertion{

	private LabelAutomata labelAutomata;
	private Automata dfa;
	private boolean isUniversal;
	public UniversalAssertion(LabelAutomata labelAutomata){
		this.labelAutomata = labelAutomata;
		
		dfa = this.labelAutomata.getAutomata();
		if(!dfa.isDFA()){
			dfa = AutomataConverter.toDFA(dfa);
		}
	}
	
	public boolean verify() {
		isUniversal =  UniversalChecking.isUniversal(dfa);
		return isUniversal;
	}

	public String getResult() {
		if(isUniversal){
			return "Automata " + labelAutomata.getName() + " is universal.";
		}
		List<Integer> notAcceptedWord = UniversalChecking.findShortestUnacceptingWords(dfa);
		
		StringBuilder result = new StringBuilder();
		result.append("Automata " + labelAutomata.getName() + " does not accept ");
		
		List<String> labels = labelAutomata.getLabels(notAcceptedWord);
		
		result.append(getLabelWord(labels));
		
		return result.toString();
	}
	
	@Override
	public String toString() {
		return "Check whether automata " + labelAutomata.getName() + " is universal";
	}

}
