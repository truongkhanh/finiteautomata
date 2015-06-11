package finiteautomata.language;

import java.util.List;

import finiteautomata.Automata;

public interface InclusionChecking {

	public boolean isSubSet(Automata automata1, Automata automata2);
	public List<Integer> findCounterExample(Automata automata1, Automata automata2);
	public List<Integer> findShortestCounterExample(Automata automata1, Automata automata2);
}
