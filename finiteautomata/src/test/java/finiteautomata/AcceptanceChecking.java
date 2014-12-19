package finiteautomata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class AcceptanceChecking {

	@Test
	public void test1(){
		Automata automata = new Automata(0, 2, 3);
		automata.addTrans(0, 1, 1);
		automata.addTrans(1, 2, 1);
		
		Set<Integer> acceptingStates = new HashSet<Integer>();
		acceptingStates.add(1);
		
		automata.setAcceptingStates(acceptingStates);
		
		Assert.assertFalse(LanguageChecking.isEmpty(automata));
		
		List<Integer> word = LanguageChecking.getAcceptedWord(automata);
		Assert.assertEquals(1, word.get(0).intValue());
		for(int i = 1; i < word.size(); i++){
			Assert.assertEquals(2, word.get(i).intValue());
		}
		
		Assert.assertTrue(LanguageChecking.acceptWord(automata, new int[]{1}));
		Assert.assertTrue(LanguageChecking.acceptWord(automata, new int[]{1, 2}));
		Assert.assertTrue(LanguageChecking.acceptWord(automata, new int[]{1, 2, 2, 2, 2}));
		
		Assert.assertFalse(LanguageChecking.acceptWord(automata, new int[]{1, 2, 1, 2, 2}));
		Assert.assertFalse(LanguageChecking.acceptWord(automata, new int[]{2, 1, 2, 2}));
		
		
	}
	
	@Test
	public void test2(){
		//corresponding dfa
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
		
		Assert.assertFalse(LanguageChecking.isEmpty(automata));
		
		Assert.assertTrue(LanguageChecking.acceptWord(automata, new int[]{1, 1, 1, 1, 1, 2, 2}));
		Assert.assertTrue(LanguageChecking.acceptWord(automata, new int[]{2, 2, 1, 2, 2, 1, 2, 2}));
		Assert.assertTrue(LanguageChecking.acceptWord(automata, new int[]{2, 1, 2, 1, 1, 2, 2}));
		
		Assert.assertFalse(LanguageChecking.acceptWord(automata, new int[]{2, 2, 2, 2, 2, 2, 2}));
		Assert.assertFalse(LanguageChecking.acceptWord(automata, new int[]{1, 1, 1, 1, 1, 2}));
		
		
	}
}
