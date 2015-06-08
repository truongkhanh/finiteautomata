package visitor;

import java.util.ArrayList;
import java.util.List;
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

	public Automata getAutomata() {
		return automata;
	}

	public String getName() {
		return name;
	}
	
	public String getLabel(int index){
		for(String label: mapLabelToIndex.keySet()){
			if(mapLabelToIndex.get(label) == index){
				return label;
			}
		}
		
		throw new RuntimeException("No label has index " + index);
	}
	
	public List<String> getLabels(List<Integer> word){
		List<String> result = new ArrayList<String>();
		
		for(int index: word){
			result.add(getLabel(index));
		}
		
		return result;
	}
}
