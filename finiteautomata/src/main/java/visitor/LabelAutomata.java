package visitor;

import java.util.Map;

import finiteautomata.Automata;

public class LabelAutomata {

	private String name;
	private Automata automata;
	private Map<String, Integer> mapStateToIndex;
	private Map<String, Integer> mapLabelToIndex;
	
	public LabelAutomata(String name, Automata automata, Map<String, Integer> stateMap, Map<String, Integer> labelMap){
		this.name = name;
		this.automata = automata;
		this.mapStateToIndex = stateMap;
		this.mapLabelToIndex = labelMap;
	}
}
