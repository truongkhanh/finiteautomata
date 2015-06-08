package finiteautomata;

import grammar.Yylex;
import grammar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import visitor.AllVisitorImpl;
import visitor.Specification;

public abstract class AbstractTest {

	protected Reader getReader(String fileName) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			FileReader reader = new FileReader(file);
			return reader;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	protected Specification parseFromReader(Reader reader) {
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
