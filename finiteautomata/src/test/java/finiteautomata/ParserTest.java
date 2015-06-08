package finiteautomata;

import java.io.Reader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import visitor.Assertion;
import visitor.Specification;

public class ParserTest extends AbstractTest{

	
	@Test
	public void whenThereIsOneAutomata(){
		String fileName = "OneAutomata.txt";
		Reader reader = getReader(fileName);
		Specification specification = parseFromReader(reader);
		
		Automata automata = specification.getLabelAutomata("A").getAutomata();
		Assert.assertNotNull(automata);
		
		int numberOfStates = automata.getStates().length;
		Assert.assertEquals(4, numberOfStates);
		
		int numberOfAccepting = automata.getAcceptingStates().size();
		Assert.assertEquals(2, numberOfAccepting);
	}
	
	@Test
	public void whenThereIsTwoAutomata(){
		String fileName = "TwoAutomata.txt";
		Reader reader = getReader(fileName);
		Specification specification = parseFromReader(reader);
		
		Automata automata = specification.getLabelAutomata("A").getAutomata();
		Assert.assertNotNull(automata);
		
		int numberOfStates = automata.getStates().length;
		Assert.assertEquals(4, numberOfStates);
		
		int numberOfAccepting = automata.getAcceptingStates().size();
		Assert.assertEquals(2, numberOfAccepting);
		
		automata = specification.getLabelAutomata("B").getAutomata();
		Assert.assertNotNull(automata);
		
		numberOfStates = automata.getStates().length;
		Assert.assertEquals(4, numberOfStates);
		
		numberOfAccepting = automata.getAcceptingStates().size();
		Assert.assertEquals(1, numberOfAccepting);
	}
	
	@Test(expected=RuntimeException.class)
	public void whenDuplicateAutomataName(){
		String fileName = "DuplicateAutomataName.txt";
		Reader reader = getReader(fileName);
		parseFromReader(reader);
	}
	
	@Test
	public void whenThereAreAllTypesAssertions(){
		String fileName = "AllAssertions.txt";
		Reader reader = getReader(fileName);
		Specification specification = parseFromReader(reader);
		List<Assertion> assertions = specification.getAssertions();
		
		Assert.assertEquals(3, assertions.size());
	}
	
	@Test(expected=RuntimeException.class)
	public void whenAutomataUndefinedInAssertions(){
		String fileName = "UndefinedAutomata.txt";
		Reader reader = getReader(fileName);
		parseFromReader(reader);
	}
}
