package finiteautomata;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class AutomataParserTest {

	@Test
	public void test1() throws FileNotFoundException{
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
		
		Assert.assertEquals(0, automata.getInitState());
		Assert.assertEquals(4, automata.getStates().length);
		Assert.assertEquals(3, automata.getNumLabels());
		
		State[] states = automata.getStates();
		
		Assert.assertEquals(new HashSet<Integer>(){{add(0); add(1);}}, states[0].getDest(1));
		Assert.assertEquals(new HashSet<Integer>(){{add(0); add(2);}}, states[0].getDest(2));
		Assert.assertEquals(new HashSet<Integer>(){{add(3);}}, states[1].getDest(1));
		Assert.assertEquals(new HashSet<Integer>(){{add(3); }}, states[2].getDest(2));
		Assert.assertEquals(new HashSet<Integer>(){{add(3);}}, states[3].getDest(1));
		Assert.assertEquals(new HashSet<Integer>(){{add(3); }}, states[3].getDest(2));
		
		Assert.assertEquals(new HashSet<Integer>(){{add(1); add(2);}}, states[0].getOutgoingLabels());
		Assert.assertEquals(new HashSet<Integer>(){{add(1); }}, states[1].getOutgoingLabels());
		Assert.assertEquals(new HashSet<Integer>(){{add(2); }}, states[2].getOutgoingLabels());
		Assert.assertEquals(new HashSet<Integer>(){{add(1); add(2);}}, states[3].getOutgoingLabels());

	}
	
	@Test
	public void test2() throws FileNotFoundException{
		char NEW_LINE = '\n';
		
		StringBuilder s = new StringBuilder();
		s.append("1 4 3");s.append(NEW_LINE);
		
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
		
		Assert.assertEquals(1, automata.getInitState());
		Assert.assertEquals(4, automata.getStates().length);
		Assert.assertEquals(3, automata.getNumLabels());
		
		State[] states = automata.getStates();
		
		Assert.assertEquals(new HashSet<Integer>(){{add(0); add(1);}}, states[0].getDest(1));
		Assert.assertEquals(new HashSet<Integer>(){{add(0); add(2);}}, states[0].getDest(2));
		Assert.assertEquals(new HashSet<Integer>(){{add(3);}}, states[1].getDest(1));
		Assert.assertEquals(new HashSet<Integer>(){{add(3); }}, states[2].getDest(2));
		Assert.assertEquals(new HashSet<Integer>(){{add(3);}}, states[3].getDest(1));
		Assert.assertEquals(new HashSet<Integer>(){{add(3); }}, states[3].getDest(2));
		
		Assert.assertEquals(new HashSet<Integer>(){{add(1); add(2);}}, states[0].getOutgoingLabels());
		Assert.assertEquals(new HashSet<Integer>(){{add(1); }}, states[1].getOutgoingLabels());
		Assert.assertEquals(new HashSet<Integer>(){{add(2); }}, states[2].getOutgoingLabels());
		Assert.assertEquals(new HashSet<Integer>(){{add(1); add(2);}}, states[3].getOutgoingLabels());

	}
}
