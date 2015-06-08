package visitor;

import java.util.HashMap;
import java.util.Map;

import finiteautomata.Automata;

public class Specification {

	private Map<String, Automata> mapIdToAutomata = new HashMap<String, Automata>();
	
	public void add(String name, Automata automata){
		if(mapIdToAutomata.containsKey(name)){
			throw new RuntimeException("Automata name" + name + " already exists!");
		}
		
		mapIdToAutomata.put(name, automata);
	}
	
	public Automata getAutomata(String name){
		return mapIdToAutomata.get(name);
	}
}
