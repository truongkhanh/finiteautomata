package finiteautomata;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class NFA2DFATest {

	@Test
	public void test1(){
		Automata automata = new Automata(0, 11, 3);
		automata.addTrans(0, 0, 1);
		automata.addTrans(0, 0, 7);
		
		automata.addTrans(1, 0, 2);
		automata.addTrans(1, 0, 4);
		
		automata.addTrans(2, 1, 3);
		
		automata.addTrans(3, 0, 6);
		
		automata.addTrans(4, 2, 5);
		
		automata.addTrans(5, 0, 6);
		
		automata.addTrans(6, 0, 7);
		automata.addTrans(6, 0, 1);
		
		automata.addTrans(7, 1, 8);
		
		automata.addTrans(8, 2, 9);
		
		automata.addTrans(9, 2, 10);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(10);
		
		automata.setAcceptingStates(acceptingStates);
		
		Automata dfa = AutomataConverter.toDFA(automata);
		
//		0 5 3
//		10
//		0 1 1
//		0 2 2
//		1 1 1
//		1 2 3
//		2 1 1
//		2 2 2
//		3 1 1
//		3 2 4
//		4 1 1
//		4 2 2
//		1
//		4 
		System.out.println(AutomataParser.write(dfa));
		
		Assert.assertEquals(5, dfa.getStates().length);
		
		Assert.assertFalse(LanguageChecking.isEmpty(automata));
		
		Assert.assertTrue(LanguageChecking.acceptsWord(dfa, new int[]{1, 1, 1, 1, 1, 2, 2}));
		Assert.assertTrue(LanguageChecking.acceptsWord(dfa, new int[]{2, 2, 1, 2, 2, 1, 2, 2}));
		Assert.assertTrue(LanguageChecking.acceptsWord(dfa, new int[]{2, 1, 2, 1, 1, 2, 2}));
		
		Assert.assertFalse(LanguageChecking.acceptsWord(dfa, new int[]{2, 2, 2, 2, 2, 2, 2}));
		Assert.assertFalse(LanguageChecking.acceptsWord(dfa, new int[]{1, 1, 1, 1, 1, 2}));
	}
	
	@Test
	public void test2(){
		Automata automata = new Automata(0, 4, 3);
		automata.addTrans(0, 1, 1);
		automata.addTrans(0, 1, 2);
		
		automata.addTrans(1, 1, 1);
		automata.addTrans(1, 1, 2);
		
		automata.addTrans(2, 2, 1);
		automata.addTrans(2, 2, 3);
		
		automata.addTrans(3, 1, 1);
		automata.addTrans(3, 1, 2);
		
		
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(0);
		acceptingStates.add(1);
		
		automata.setAcceptingStates(acceptingStates);
		
		Automata dfa = AutomataConverter.toDFA(automata);
		
		
//		0 3 3
//		4
//		0 1 1
//		1 1 1
//		1 2 2
//		2 1 1
//		3
//		0 1 2 
		System.out.println(AutomataParser.write(dfa));
		
		Assert.assertEquals(3, dfa.getStates().length);
		
		Assert.assertFalse(LanguageChecking.isEmpty(automata));
		
		Assert.assertTrue(LanguageChecking.acceptsWord(dfa, new int[]{}));
		Assert.assertTrue(LanguageChecking.acceptsWord(dfa, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
		Assert.assertTrue(LanguageChecking.acceptsWord(dfa, new int[]{1, 1, 1, 1, 2, 1, 2, 1, 2}));
		
		Assert.assertFalse(LanguageChecking.acceptsWord(dfa, new int[]{2, 1, 1, 1, 1}));
		Assert.assertFalse(LanguageChecking.acceptsWord(dfa, new int[]{1, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2}));
		
	}
}
