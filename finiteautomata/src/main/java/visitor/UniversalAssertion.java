package visitor;

import java.util.List;

import finiteautomata.language.LanguageChecking;

public class UniversalAssertion extends Assertion{

	private LabelAutomata labelAutomata;
	private List<Integer> notAcceptedWord;
	public UniversalAssertion(LabelAutomata labelAutomata){
		this.labelAutomata = labelAutomata;
	}
	
	public boolean verify() {
		notAcceptedWord = LanguageChecking.findUnacceptingWord(labelAutomata.getAutomata());
		
		return notAcceptedWord == null;
	}

	public String getResult() {
		if(notAcceptedWord == null){
			return "Automata " + labelAutomata.getName() + " is universal.";
		}
		StringBuilder result = new StringBuilder();
		result.append("Automata " + labelAutomata.getName() + " does not accept ");
		
		List<String> labels = labelAutomata.getLabels(notAcceptedWord);
		
		result.append(getLabelWord(labels));
		
		return result.toString();
	}

}
