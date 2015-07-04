package finiteautomata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import finiteautomata.language.EmptyChecking;

public class EmptinessCheckingTest {

	@Test
	public void whenLangueIsNotEmpty(){
		Automata automata = new Automata(1, 2, 2);
		automata.addTrans(1, 0, 0);
		automata.addTrans(0, 1, 0);
		automata.setAcceptingStates(new HashSet<Integer>(Arrays.asList(0)));
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.findShortestAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(0), word);
		
		word = EmptyChecking.findAcceptedWord(automata);
		Assert.assertTrue(word.size() >= 1);
		Assert.assertTrue(word.get(0) == 0);
	}
	
	@Test
	public void whenLangueIsEmpty(){
		Automata automata = new Automata(1, 2, 2);
		automata.addTrans(1, 0, 0);
		automata.addTrans(0, 1, 0);
		
		Assert.assertTrue(EmptyChecking.isEmpty(automata));

		List<Integer> word = EmptyChecking.findShortestAcceptedWord(automata);
		Assert.assertNull(word);
		
		word = EmptyChecking.findAcceptedWord(automata);
		Assert.assertNull(word);
	}
	
	@Test
	public void whenLanguageIsOnlyOneWord(){
		Automata automata = new Automata(1, 2, 2);
		automata.addTrans(1, 0, 0);
		automata.addTrans(0, 1, 0);
		automata.setAcceptingStates(new HashSet<Integer>(Arrays.asList(1)));
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.findShortestAcceptedWord(automata);
		Assert.assertTrue(word.isEmpty());
		
		word = EmptyChecking.findAcceptedWord(automata);
		Assert.assertTrue(word.isEmpty());
	}
	
	@Test
	public void whenAcceptedPathContainEmptyLabel(){
		Automata automata = new Automata(0, 5, 3);
		automata.addTrans(0, 0, 1);
		automata.addTrans(0, 1, 2);
		automata.addTrans(2, Automata.EPSILON_LABEL, 3);
		automata.addTrans(3, 2, 4);
		automata.setAcceptingStates(new HashSet<Integer>(Arrays.asList(4)));
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.findShortestAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(1, 2), word);
		
		word = EmptyChecking.findAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(1, 2), word);
	}
	
	@Test
	public void whenFirstAcceptedIsNotShortest(){
		Automata automata = new Automata(0, 4, 2);
		automata.addTrans(0, 1, 1);
		automata.addTrans(0, 0, 3);
		automata.addTrans(1, 0, 2);
		automata.setAcceptingStates(new HashSet<Integer>(Arrays.asList(2, 3)));
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.findShortestAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(0), word);
		
		word = EmptyChecking.findAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(1, 0), word);
	}
}
