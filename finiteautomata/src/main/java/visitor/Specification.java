package visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specification {

	private Map<String, LabelAutomata> mapIdToAutomata = new HashMap<String, LabelAutomata>();
	private List<Assertion> assertions = new ArrayList<Assertion>();
	
	public void addAssertion(Assertion assertion){
		this.assertions.add(assertion);
	}
	
	public void addLabelAutomata(String name, LabelAutomata automata){
		if(mapIdToAutomata.containsKey(name)){
			throw new RuntimeException("Automata name " + name + " already exists!");
		}
		
		mapIdToAutomata.put(name, automata);
	}
	
	public LabelAutomata getLabelAutomata(String name){
		return mapIdToAutomata.get(name);
	}

	public List<Assertion> getAssertions() {
		return assertions;
	}
}
