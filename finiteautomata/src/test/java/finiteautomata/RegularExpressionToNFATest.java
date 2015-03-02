package finiteautomata;

import org.junit.Assert;
import org.junit.Test;

import finiteautomata.language.LanguageChecking;

public class RegularExpressionToNFATest {

	private RegularExpressionToNFA factory = new RegularExpressionToNFA();
	
	@Test
	public void whenThereAreOnlyInteger(){
		String expression = "4\n0 1 2 3";
		
		Automata automata = factory.getAutomataFromRegularExpression(expression);
		
		Assert.assertEquals(6, automata.getStates().length);
		Assert.assertTrue(LanguageChecking.acceptsWord(automata, new int[]{0, 1, 2, 3}));
	}
	
	@Test
	public void whenThereAreStars(){
		String expression = "4\n0 1* 2* 3";
		
		Automata automata = factory.getAutomataFromRegularExpression(expression);
		
		Assert.assertEquals(6, automata.getStates().length);
		Assert.assertTrue(LanguageChecking.acceptsWord(automata, new int[]{0, 1, 2, 3}));
		Assert.assertTrue(LanguageChecking.acceptsWord(automata, new int[]{0, 1, 1, 1, 3}));
		Assert.assertTrue(LanguageChecking.acceptsWord(automata, new int[]{0, 3}));
	}
	
	@Test
	public void whenThereAreSequence(){
		String expression = "3\n0 1^2 2";
		
		Automata automata = factory.getAutomataFromRegularExpression(expression);
		
		Assert.assertEquals(6, automata.getStates().length);
		Assert.assertTrue(LanguageChecking.acceptsWord(automata, new int[]{0, 1, 1, 2}));
		Assert.assertFalse(LanguageChecking.acceptsWord(automata, new int[]{0, 1, 2}));
	}
}