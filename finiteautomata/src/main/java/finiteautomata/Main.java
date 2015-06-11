package finiteautomata;

import grammar.Yylex;
import grammar.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import visitor.AllVisitorImpl;
import visitor.Assertion;
import visitor.Specification;


public class Main {

	public static void main(String[] args) {
		String fileName = args[0];
		Specification specification = parse(fileName);
		
		for(Assertion assertion: specification.getAssertions()){
			boolean isSatisfied = assertion.verify();
			System.out.println(assertion.toString() + ": " + isSatisfied);
			System.out.println(assertion.getResult());
		}
	}
	
	public static Specification parse(String fileName) {
		try {
			return parseFromReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static Specification parseFromReader(Reader reader) {
		parser p;
		Yylex l = new Yylex(reader);
		p = new parser(l);
		
		try {
			grammar.Absyn.ModelRule parse_tree = p.pModelRule();

			Specification problem = new Specification();
			parse_tree.accept(new AllVisitorImpl(), problem);
			
			return problem;
		} catch (Throwable e) {
			
			String error = ("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :\n") +
							("     " + e.getMessage());
			throw new RuntimeException(error);
		}
	}

}
