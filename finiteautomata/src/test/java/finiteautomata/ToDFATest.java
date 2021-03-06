package finiteautomata;

import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

import visitor.Specification;

public class ToDFATest extends AbstractTest{

	@Test
	public void whenSameLabelOut(){
		String fileName = "NFASameLabelOut.txt";
		Reader reader = getReader(fileName);
		Specification specification = parseFromReader(reader);
		
		Automata automata = specification.getLabelAutomata("A").getAutomata();
		
		Automata dfa= AutomataConverter.toDFA(automata);
		
		Assert.assertEquals(2, dfa.getStates().length);
		Assert.assertTrue(dfa.isDFA());
	}
	
	@Test
	public void whenItContainsEmptyLabel(){
		String fileName = "NFAWithEmptyLabel.txt";
		Reader reader = getReader(fileName);
		Specification specification = parseFromReader(reader);
		
		Automata automata = specification.getLabelAutomata("A").getAutomata();
		
		Automata dfa= AutomataConverter.toDFA(automata);
		
		Assert.assertEquals(2, dfa.getStates().length);
		Assert.assertTrue(dfa.isDFA());
	}
	
	@Test
	public void whenItIsNotDFA(){
		String fileName = "NFA.txt";
		Reader reader = getReader(fileName);
		Specification specification = parseFromReader(reader);
		
		Automata automata = specification.getLabelAutomata("A").getAutomata();
		
		Automata dfa= AutomataConverter.toDFA(automata);
		
		Assert.assertEquals(4, dfa.getStates().length);
		Assert.assertTrue(dfa.isDFA());

	}
}
