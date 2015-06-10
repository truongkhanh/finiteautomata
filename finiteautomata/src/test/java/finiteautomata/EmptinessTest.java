package finiteautomata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import finiteautomata.language.EmptyChecking;

public class EmptinessTest {

	@Test
	public void whenLangueIsNotEmpty(){
		Automata automata = new Automata(1, 2, 3);
		automata.addTrans(1, 1, 0);
		automata.addTrans(0, 2, 0);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(0);
		
		automata.setAcceptingStates(acceptingStates);
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.getShortestAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(1), word);
		
		word = EmptyChecking.getAcceptedWord(automata);
		Assert.assertTrue(word.size() >= 1);
		Assert.assertTrue(word.get(0) == 1);
	}
	
	@Test
	public void whenLangueIsEmpty(){
		Automata automata = new Automata(1, 2, 3);
		automata.addTrans(1, 1, 0);
		automata.addTrans(0, 2, 0);
		
		Assert.assertTrue(EmptyChecking.isEmpty(automata));

		List<Integer> word = EmptyChecking.getShortestAcceptedWord(automata);
		Assert.assertNull(word);
		
		word = EmptyChecking.getAcceptedWord(automata);
		Assert.assertNull(word);
	}
	
	@Test
	public void whenLanguageIsOnlyOneWord(){
		Automata automata = new Automata(1, 2, 3);
		automata.addTrans(1, 1, 0);
		automata.addTrans(0, 2, 0);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(1);
		
		automata.setAcceptingStates(acceptingStates);
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.getShortestAcceptedWord(automata);
		Assert.assertTrue(word.isEmpty());
		
		word = EmptyChecking.getAcceptedWord(automata);
		Assert.assertTrue(word.isEmpty());
	}
	
	@Test
	public void whenAcceptedPathContainEmptyLabel(){
		Automata automata = new Automata(0, 5, 4);
		automata.addTrans(0, 1, 1);
		automata.addTrans(0, 2, 2);
		automata.addTrans(2, 0, 3);
		automata.addTrans(3, 3, 4);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(4);
		
		automata.setAcceptingStates(acceptingStates);
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.getShortestAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(2, 3), word);
		
		word = EmptyChecking.getAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(2, 3), word);
	}
	
	@Test
	public void whenFirstAcceptedIsNotShortest(){
		Automata automata = new Automata(0, 4, 3);
		automata.addTrans(0, 2, 1);
		automata.addTrans(0, 1, 3);
		automata.addTrans(1, 1, 2);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(2);
		acceptingStates.add(3);
		
		automata.setAcceptingStates(acceptingStates);
		
		Assert.assertFalse(EmptyChecking.isEmpty(automata));
		
		List<Integer> word = EmptyChecking.getShortestAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(1), word);
		
		word = EmptyChecking.getAcceptedWord(automata);
		Assert.assertEquals(Arrays.asList(2, 1), word);
	}
}
