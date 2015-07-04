package finiteautomata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import finiteautomata.language.InclusionChecking;
import finiteautomata.language.InclusionCheckingImpl;


public class InclusionCheckingImplTest {

	private InclusionChecking inclusionChecking = new InclusionCheckingImpl();
	
	@Test
	public void whenTwoAutomataAreSame(){
		Automata automata = new Automata(0, 3, 2);
		automata.addTrans(0, 0, 1);
		automata.addTrans(0, 1, 1);
		automata.addTrans(1, 0, 2);
		automata.addTrans(1, 1, 2);
		automata.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		automata = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata));
		
		boolean isSubset = inclusionChecking.isSubSet(automata, automata);
		Assert.assertTrue(isSubset);
		
		List<Integer> counterExample =inclusionChecking.findShortestCounterExample(automata, automata);
		Assert.assertNull(counterExample);
		
		counterExample =inclusionChecking.findCounterExample(automata, automata);
		Assert.assertNull(counterExample);
	}
	
	@Test
	public void whenOneAutomatonIsSubsetButNotEqual(){
		Automata automata1 = new Automata(0, 3, 2);
		automata1.addTrans(0, 0, 1);
		automata1.addTrans(1, 0, 2);
		automata1.addTrans(1, 1, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		Automata automata2 = new Automata(0, 3, 2);
		automata2.addTrans(0, 0, 1);
		automata2.addTrans(0, 1, 1);
		automata2.addTrans(1, 0, 2);
		automata2.addTrans(1, 1, 2);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertTrue(isSubset);
		
		List<Integer> counterExample =inclusionChecking.findShortestCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
		
		counterExample =inclusionChecking.findCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
	}
	
	@Test
	public void whenNotSubSetBecauseOneLabelIsNotEnabled(){
		Automata automata1 = new Automata(0, 3, 2);
		automata1.addTrans(0, 0, 1);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(1, 0, 2);
		automata1.addTrans(1, 1, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		Automata automata2 = new Automata(0, 3, 2);
		automata2.addTrans(0, 0, 1);
		automata2.addTrans(1, 0, 2);
		automata2.addTrans(1, 1, 2);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertFalse(isSubset);
		
		List<Integer> counterExample =inclusionChecking.findShortestCounterExample(automata1, automata2);
		Assert.assertEquals(1, counterExample.size());
		Assert.assertTrue(counterExample.get(0) == 1);
		
		counterExample =inclusionChecking.findCounterExample(automata1, automata2);
		Assert.assertEquals(1, counterExample.size());
		Assert.assertTrue(counterExample.get(0) == 1);
	}
	
	@Test
	public void whenNotSubSetBecauseNextStateIsNotAccepted(){
		Automata automata1 = new Automata(0, 3, 2);
		automata1.addTrans(0, 0, 1);
		automata1.addTrans(1, 1, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		Automata automata2 = new Automata(0, 3, 2);
		automata2.addTrans(0, 0, 1);
		automata2.addTrans(1, 1, 2);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertFalse(isSubset);
		
		List<Integer> counterExample =inclusionChecking.findShortestCounterExample(automata1, automata2);
		Assert.assertEquals(Arrays.asList(0, 1), counterExample);
		
		counterExample =inclusionChecking.findCounterExample(automata1, automata2);
		Assert.assertEquals(Arrays.asList(0, 1), counterExample);
	}
	
	@Test
	public void whenSubSetAndAutomata2ContainsEpsilon(){
		Automata automata1 = new Automata(0, 3, 2);
		automata1.addTrans(0, 0, 1);
		automata1.addTrans(1, 1, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		Automata automata2 = new Automata(0, 4, 2);
		automata2.addTrans(0, 0, 1);
		automata2.addTrans(1, Automata.EPSILON_LABEL, 2);
		automata2.addTrans(2, 1, 3);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(2, 3)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertTrue(isSubset);
		
		List<Integer> counterExample =inclusionChecking.findShortestCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
		
		counterExample =inclusionChecking.findCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
	}
	
	@Test
	public void whenNotSubSetBecauseNotAcceptEmptyWord(){
		Automata automata1 = new Automata(0, 3, 2);
		automata1.addTrans(0, 0, 1);
		automata1.addTrans(1, 1, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(0, 1, 2)));
		
		Automata automata2 = new Automata(0, 3, 2);
		automata2.addTrans(0, 0, 1);
		automata2.addTrans(1, 1, 2);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertFalse(isSubset);
		
		List<Integer> counterExample =inclusionChecking.findShortestCounterExample(automata1, automata2);
		Assert.assertTrue(counterExample.isEmpty());
		
		counterExample =inclusionChecking.findCounterExample(automata1, automata2);
		Assert.assertTrue(counterExample.isEmpty());
	}
}
