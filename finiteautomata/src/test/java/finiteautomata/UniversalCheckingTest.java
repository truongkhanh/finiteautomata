package finiteautomata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import finiteautomata.language.UniversalChecking;

public class UniversalCheckingTest {

	@Test
	public void whenLanguageIsUnivesal(){
		Automata automata = new Automata(0, 2, 3);
		automata.addTrans(0, 1, 1);
		automata.addTrans(0, 2, 1);
		automata.addTrans(1, 1, 1);
		automata.addTrans(1, 2, 1);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(0);
		acceptingStates.add(1);
		
		automata.setAcceptingStates(acceptingStates);
		
		if(!automata.isDFA()){
			automata = AutomataConverter.toDFA(automata);
		}
		Assert.assertTrue(UniversalChecking.isUniversal(automata));
		
		List<Integer> word = UniversalChecking.findShortestUnacceptingWords(automata);
		Assert.assertNull(word);
		
		word = UniversalChecking.findUnacceptingWordInDFA(automata);
		Assert.assertNull(word);
	}
	
	@Test
	public void whenLanguageIsNotUnivesal(){
		Automata automata = new Automata(0, 2, 3);
		automata.addTrans(0, 1, 1);
		automata.addTrans(0, 2, 1);
		automata.addTrans(1, 1, 1);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(0);
		acceptingStates.add(1);
		
		automata.setAcceptingStates(acceptingStates);
		
		if(!automata.isDFA()){
			automata = AutomataConverter.toDFA(automata);
		}
		Assert.assertFalse(UniversalChecking.isUniversal(automata));
		
		List<Integer> word = UniversalChecking.findShortestUnacceptingWords(automata);
		Assert.assertEquals(2, word.size());
		Assert.assertTrue(word.get(1) == 2);
		
		word = UniversalChecking.findUnacceptingWordInDFA(automata);
		Assert.assertTrue(word.size() >= 2);
	}
	
	@Test
	public void whenLanguageDoesNotAcceptEmptyLabel(){
		Automata automata = new Automata(0, 2, 3);
		automata.addTrans(0, 1, 1);
		automata.addTrans(0, 2, 1);
		automata.addTrans(1, 1, 1);
		automata.addTrans(1, 2, 1);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(1);
		
		automata.setAcceptingStates(acceptingStates);
		
		if(!automata.isDFA()){
			automata = AutomataConverter.toDFA(automata);
		}
		Assert.assertFalse(UniversalChecking.isUniversal(automata));
		
		List<Integer> word = UniversalChecking.findShortestUnacceptingWords(automata);
		Assert.assertEquals(0, word.size());
		
		word = UniversalChecking.findUnacceptingWordInDFA(automata);
		Assert.assertTrue(word.size() >= 0);
	}
}
