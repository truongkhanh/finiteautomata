package finiteautomata;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

public class LangugeCheckingTest {
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
		
		Assert.assertFalse(LanguageChecking.isUniversal(automata).size() == 0);
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
		
		Assert.assertFalse(LanguageChecking.isUniversal(automata).size() == 0);
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
		
		Assert.assertTrue(LanguageChecking.isUniversal(automata).size() == 0);
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
		
		Assert.assertTrue(LanguageChecking.isUniversal(automata).size() == 0);
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
		
		Assert.assertFalse(LanguageChecking.isUniversal(automata).size() == 0);
	}
}
