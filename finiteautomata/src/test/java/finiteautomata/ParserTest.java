package finiteautomata;

import grammar.Yylex;
import grammar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

import visitor.AllVisitorImpl;
import visitor.Specification;

public class ParserTest {

	
	@Test
	public void whenThereIsOneAutomata(){
		String fileName = "OneAutomata.txt";
		Reader reader = getReader(fileName);
		Specification specification = parseFromReader(reader);
		
		Automata automata = specification.getAutomata("A");
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
		
		Automata automata = specification.getAutomata("A");
		Assert.assertNotNull(automata);
		
		int numberOfStates = automata.getStates().length;
		Assert.assertEquals(4, numberOfStates);
		
		int numberOfAccepting = automata.getAcceptingStates().size();
		Assert.assertEquals(2, numberOfAccepting);
		
		automata = specification.getAutomata("B");
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
	
	private Reader getReader(String fileName) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			FileReader reader = new FileReader(file);
			return reader;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private Specification parseFromReader(Reader reader) {
		parser p;
		Yylex l = new Yylex(reader);
		p = new parser(l);

		try {
			grammar.Absyn.ModelRule parse_tree = p.pModelRule();

			Specification problem = new Specification();
			parse_tree.accept(new AllVisitorImpl(), problem);

			return problem;
		} catch (Throwable e) {

			String error = ("At line " + String.valueOf(l.line_num())
					+ ", near \"" + l.buff() + "\" :\n")
					+ ("     " + e.getMessage());
			throw new RuntimeException(error);
		}
	}
}
