package finiteautomata;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import finiteautomata.language.LanguageChecking;

public class UniversalTest {
	@Test
	public void test1(){
		char NEW_LINE = '\n';
		
		StringBuilder s = new StringBuilder();
		s.append("0 4 3");s.append(NEW_LINE);
		
		//transition
		s.append(8);s.append(NEW_LINE);
		
		s.append("0 1 0");s.append(NEW_LINE);
		s.append("0 2 0");s.append(NEW_LINE);
		s.append("0 1 1");s.append(NEW_LINE);
		s.append("0 2 2");s.append(NEW_LINE);
		s.append("1 1 3");s.append(NEW_LINE);
		s.append("2 2 3");s.append(NEW_LINE);
		s.append("3 1 3");s.append(NEW_LINE);
		s.append("3 2 3");s.append(NEW_LINE);
		
		//accepting states
		s.append(1);s.append(NEW_LINE);
		s.append(3);s.append(NEW_LINE);
		
		String originalAutomata = s.toString();
		Automata automata = AutomataParser.read(new ByteArrayInputStream(originalAutomata.getBytes()));
		
		//universal checking
		List<Integer> notAcceptedWord = LanguageChecking.findUnacceptingWord(automata);
		Assert.assertTrue(notAcceptedWord != null);
		
		int[] wordTemp = new int[notAcceptedWord.size()];
		for(int i = 0; i < wordTemp.length; i++){
			wordTemp[i] = notAcceptedWord.get(i);
		}
		Assert.assertFalse(LanguageChecking.acceptsWord(automata, wordTemp));
	}
	
	@Test
	public void test2(){
		char NEW_LINE = '\n';
		
		StringBuilder s = new StringBuilder();
		s.append("0 4 3");s.append(NEW_LINE);
		
		//transition
		s.append(8);s.append(NEW_LINE);
		
		s.append("0 1 0");s.append(NEW_LINE);
		s.append("0 2 0");s.append(NEW_LINE);
		s.append("0 1 1");s.append(NEW_LINE);
		s.append("0 2 2");s.append(NEW_LINE);
		s.append("1 1 3");s.append(NEW_LINE);
		s.append("2 2 3");s.append(NEW_LINE);
		s.append("3 1 3");s.append(NEW_LINE);
		s.append("3 2 3");s.append(NEW_LINE);
		
		//accepting states
		s.append(1);s.append(NEW_LINE);
		s.append(3);s.append(NEW_LINE);
		
		String originalAutomata = s.toString();
		Automata automata = AutomataParser.read(new ByteArrayInputStream(originalAutomata.getBytes()));
		automata = AutomataConverter.toDFA(automata);
		
		//universal checking
		List<Integer> notAcceptedWord = LanguageChecking.findUnacceptingWord(automata);
		Assert.assertTrue(notAcceptedWord != null);
		
		int[] wordTemp = new int[notAcceptedWord.size()];
		for(int i = 0; i < wordTemp.length; i++){
			wordTemp[i] = notAcceptedWord.get(i);
		}
		Assert.assertFalse(LanguageChecking.acceptsWord(automata, wordTemp));
	}
	
	@Test
	public void test3(){
		char NEW_LINE = '\n';
		
		StringBuilder s = new StringBuilder();
		s.append("0 2 3");s.append(NEW_LINE);
		
		//transition
		s.append(3);s.append(NEW_LINE);
		
		s.append("0 1 0");s.append(NEW_LINE);
		s.append("0 2 0");s.append(NEW_LINE);
		s.append("0 1 1");s.append(NEW_LINE);
		
		
		//accepting states
		s.append(2);s.append(NEW_LINE);
		s.append("0 1"); s.append(NEW_LINE);
		
		String originalAutomata = s.toString();
		Automata automata = AutomataParser.read(new ByteArrayInputStream(originalAutomata.getBytes()));
		
		//universal checking
		List<Integer> notAcceptedWord = LanguageChecking.findUnacceptingWord(automata);
		Assert.assertTrue(notAcceptedWord == null);
	}
	
	@Test
	public void test4(){
		char NEW_LINE = '\n';
		
		StringBuilder s = new StringBuilder();
		s.append("0 2 3");s.append(NEW_LINE);
		
		//transition
		s.append(3);s.append(NEW_LINE);
		
		s.append("0 1 0");s.append(NEW_LINE);
		s.append("0 2 0");s.append(NEW_LINE);
		s.append("0 1 1");s.append(NEW_LINE);
		
		
		//accepting states
		s.append(2);s.append(NEW_LINE);
		s.append("0 1"); s.append(NEW_LINE);
		
		String originalAutomata = s.toString();
		Automata automata = AutomataParser.read(new ByteArrayInputStream(originalAutomata.getBytes()));
		automata = AutomataConverter.toDFA(automata);
		
		//universal checking
		List<Integer> notAcceptedWord = LanguageChecking.findUnacceptingWord(automata);
		Assert.assertTrue(notAcceptedWord == null);
	}
	
	@Test
	public void test5(){
		char NEW_LINE = '\n';
		
		StringBuilder s = new StringBuilder();
		s.append("1 4 3");s.append(NEW_LINE);
		
		//transition
		s.append(3);s.append(NEW_LINE);
		
		s.append("1 0 2");s.append(NEW_LINE);
		s.append("2 1 3");s.append(NEW_LINE);
		s.append("3 1 1");s.append(NEW_LINE);
		
		
		//accepting states
		s.append(1);s.append(NEW_LINE);
		s.append("3"); s.append(NEW_LINE);
		
		String originalAutomata = s.toString();
		Automata automata = AutomataParser.read(new ByteArrayInputStream(originalAutomata.getBytes()));
		automata = AutomataConverter.toDFA(automata);
		
		//universal checking
		List<Integer> notAcceptedWord = LanguageChecking.findUnacceptingWord(automata);
		Assert.assertTrue(notAcceptedWord != null);
		
		int[] wordTemp = new int[notAcceptedWord.size()];
		for(int i = 0; i < wordTemp.length; i++){
			wordTemp[i] = notAcceptedWord.get(i);
		}
		Assert.assertFalse(LanguageChecking.acceptsWord(automata, wordTemp));
	}
	
	
}
