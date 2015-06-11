package finiteautomata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import finiteautomata.language.InclusionChecking;
import finiteautomata.language.InclusionCheckingImpl;
import finiteautomata.language.LanguageChecking;


public class InclusionCheckingImplTest {

	private InclusionChecking inclusionChecking;
	
	@Before
	public void setup(){
		inclusionChecking = new InclusionCheckingImpl();
	}
	@Test
	public void testcaseFromTacasPaper(){
		Automata automata1 = new Automata(0, 2, 3);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(0, 1, 0);
		automata1.addTrans(1, 1, 0);
		automata1.addTrans(1, 1, 1);
		automata1.addTrans(1, 2, 0);
		Set<Integer> accepting = new HashSet<Integer>();
		accepting.add(1);
		automata1.setAcceptingStates(accepting);
		
		Automata automata2 = new Automata(0, 2, 3);
		automata2.addTrans(0, 1, 1);
		automata2.addTrans(1, 1, 0);
		automata2.addTrans(1, 1, 1);
		automata2.addTrans(1, 2, 0);
		accepting = new HashSet<Integer>();
		accepting.add(1);
		automata2.setAcceptingStates(accepting);
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		List<Integer> counterExample = inclusionChecking.getShortestCounterExample(automata1, automata2);
		
		Assert.assertNull(counterExample);
	}
	@Test
	public void test1(){
		Automata automata1 = new Automata(0, 3, 3);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(0, 2, 1);
		
		automata1.addTrans(1, 1, 2);
		automata1.addTrans(1, 2, 2);
		
		automata1.addTrans(2, 1, 2);
		automata1.addTrans(2, 2, 2);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(0);
		acceptingStates.add(1);
		acceptingStates.add(2);
		
		automata1.setAcceptingStates(acceptingStates);
		
		
		Automata automata2 = new Automata(1, 2, 3);
		automata2.addTrans(1, 1, 0);
		automata2.addTrans(0, 2, 0);
		
		acceptingStates = new HashSet<Integer>();
		acceptingStates.add(0);
		
		automata2.setAcceptingStates(acceptingStates);
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		Assert.assertTrue(inclusionChecking.getShortestCounterExample(automata2, automata1) == null);
		
		List<Integer> notAcceptedWodBy2 = inclusionChecking.getShortestCounterExample(automata1, automata2);
		Assert.assertTrue(notAcceptedWodBy2 != null);
		
		int[] wordTemp = new int[notAcceptedWodBy2.size()];
		for(int i = 0; i < wordTemp.length; i++){
			wordTemp[i] = notAcceptedWodBy2.get(i);
		}
		
		Assert.assertFalse(LanguageChecking.acceptsWord(automata2, wordTemp));
	}
	
	@Test
	public void test2(){
		Automata automata1 = new Automata(0, 11, 3);
		automata1.addTrans(0, 0, 1);
		
		automata1.addTrans(1, 0, 2);
		
		automata1.addTrans(2, 1, 3);
		
		automata1.addTrans(3, 0, 6);
		
		automata1.addTrans(4, 2, 5);
		
		automata1.addTrans(5, 0, 6);
		
		automata1.addTrans(6, 0, 7);
		
		automata1.addTrans(7, 1, 8);
		
		automata1.addTrans(8, 2, 9);
		
		automata1.addTrans(9, 2, 10);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(10);
		
		automata1.setAcceptingStates(acceptingStates);
		
		Automata automata2 = new Automata(0, 11, 3);
		automata2.addTrans(0, 0, 1);
		automata2.addTrans(0, 0, 7);
		
		automata2.addTrans(1, 0, 2);
		automata2.addTrans(1, 0, 4);
		
		automata2.addTrans(2, 1, 3);
		
		automata2.addTrans(3, 0, 6);
		
		automata2.addTrans(4, 2, 5);
		
		automata2.addTrans(5, 0, 6);
		
		automata2.addTrans(6, 0, 7);
		automata2.addTrans(6, 0, 1);
		
		automata2.addTrans(7, 1, 8);
		
		automata2.addTrans(8, 2, 9);
		
		automata2.addTrans(9, 2, 10);
		
		acceptingStates = new HashSet<Integer>();
		acceptingStates.add(10);
		
		automata2.setAcceptingStates(acceptingStates);
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		Assert.assertTrue(inclusionChecking.getShortestCounterExample(automata1, automata2) == null);

	}
	
	@Test
	public void whenTwoAutomataAreSameThenCounterExampleIsNull(){
		Automata automata = new Automata(0, 3, 3);
		automata.addTrans(0, 1, 1);
		automata.addTrans(0, 2, 1);
		automata.addTrans(1, 1, 2);
		automata.addTrans(1, 2, 2);
		
		Set<Integer> accepting = new HashSet<Integer>();
		accepting.add(1);
		accepting.add(2);
		automata.setAcceptingStates(accepting);
		
		automata = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata));
		
		boolean isSubset = inclusionChecking.isSubSet(automata, automata);
		Assert.assertTrue(isSubset);
		
		List<Integer> counterExample =inclusionChecking.getShortestCounterExample(automata, automata);
		Assert.assertNull(counterExample);
		
		counterExample =inclusionChecking.getCounterExample(automata, automata);
		Assert.assertNull(counterExample);
	}
	
	@Test
	public void whenOneAutomatonIsSubsetButNotEqual(){
		Automata automata1 = new Automata(0, 3, 3);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(1, 1, 2);
		automata1.addTrans(1, 2, 2);
		
		
		Automata automata2 = new Automata(0, 3, 3);
		automata2.addTrans(0, 1, 1);
		automata2.addTrans(0, 2, 1);
		automata2.addTrans(1, 1, 2);
		automata2.addTrans(1, 2, 2);
		
		Set<Integer> accepting = new HashSet<Integer>();
		accepting.add(1);
		accepting.add(2);
		automata1.setAcceptingStates(accepting);
		automata2.setAcceptingStates(accepting);
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertTrue(isSubset);
		
		List<Integer> counterExample =inclusionChecking.getShortestCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
		
		counterExample =inclusionChecking.getCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
	}
	
	@Test
	public void whenNotSubSetBecauseOneLabelIsNotEnabled(){
		Automata automata1 = new Automata(0, 3, 3);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(0, 2, 1);
		automata1.addTrans(1, 1, 2);
		automata1.addTrans(1, 2, 2);
		
		Automata automata2 = new Automata(0, 3, 3);
		automata2.addTrans(0, 1, 1);
		automata2.addTrans(1, 1, 2);
		automata2.addTrans(1, 2, 2);
		
		Set<Integer> accepting = new HashSet<Integer>();
		accepting.add(1);
		accepting.add(2);
		automata1.setAcceptingStates(accepting);
		automata2.setAcceptingStates(accepting);
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertFalse(isSubset);
		
		List<Integer> counterExample =inclusionChecking.getShortestCounterExample(automata1, automata2);
		Assert.assertEquals(1, counterExample.size());
		Assert.assertTrue(counterExample.get(0) == 2);
		
		counterExample =inclusionChecking.getCounterExample(automata1, automata2);
		Assert.assertEquals(1, counterExample.size());
		Assert.assertTrue(counterExample.get(0) == 2);
	}
	
	@Test
	public void whenNotSubSetBecauseNextStateIsNotAccepted(){
		Automata automata1 = new Automata(0, 3, 3);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(1, 2, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		Automata automata2 = new Automata(0, 3, 3);
		automata2.addTrans(0, 1, 1);
		automata2.addTrans(1, 2, 2);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertFalse(isSubset);
		
		List<Integer> counterExample =inclusionChecking.getShortestCounterExample(automata1, automata2);
		Assert.assertEquals(Arrays.asList(1, 2), counterExample);
		
		counterExample =inclusionChecking.getCounterExample(automata1, automata2);
		Assert.assertEquals(Arrays.asList(1, 2), counterExample);
	}
	
	@Test
	public void whenSubSetAndAutomata2ContainsEpsilon(){
		Automata automata1 = new Automata(0, 3, 3);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(1, 2, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		Automata automata2 = new Automata(0, 4, 3);
		automata2.addTrans(0, 1, 1);
		automata2.addTrans(1, 0, 2);
		automata2.addTrans(2, 2, 3);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(2, 3)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertTrue(isSubset);
		
		List<Integer> counterExample =inclusionChecking.getShortestCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
		
		counterExample =inclusionChecking.getCounterExample(automata1, automata2);
		Assert.assertNull(counterExample);
	}
	
	@Test
	public void whenNotSubSetBecauseNotAcceptEmptyWord(){
		Automata automata1 = new Automata(0, 3, 3);
		automata1.addTrans(0, 1, 1);
		automata1.addTrans(1, 2, 2);
		automata1.setAcceptingStates(new HashSet<Integer>(Arrays.asList(0, 1, 2)));
		
		Automata automata2 = new Automata(0, 3, 3);
		automata2.addTrans(0, 1, 1);
		automata2.addTrans(1, 2, 2);
		automata2.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1, 2)));
		
		automata2 = AutomataConverter.toCompleteDFA(AutomataConverter.toDFA(automata2));
		
		boolean isSubset = inclusionChecking.isSubSet(automata1, automata2);
		Assert.assertFalse(isSubset);
		
		List<Integer> counterExample =inclusionChecking.getShortestCounterExample(automata1, automata2);
		Assert.assertTrue(counterExample.isEmpty());
		
		counterExample =inclusionChecking.getCounterExample(automata1, automata2);
		Assert.assertTrue(counterExample.isEmpty());
	}
}
