package finiteautomata.language;

import java.util.List;

import finiteautomata.Automata;

public interface InclusionChecking {

	public List<Integer> isSubSet(Automata automata1, Automata automata2);
}
