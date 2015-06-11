package finiteautomata.language;

import java.util.List;

import finiteautomata.Automata;

public interface InclusionChecking {

	public boolean isSubSet(Automata automata1, Automata automata2);
	public List<Integer> getCounterExample(Automata automata1, Automata automata2);
	public List<Integer> getShortestCounterExample(Automata automata1, Automata automata2);
}
