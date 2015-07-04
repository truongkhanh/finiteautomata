package visitor;

import grammar.AllVisitor;
import grammar.Absyn.ActionStatement;
import grammar.Absyn.Automata;
import grammar.Absyn.AutomataAcceptings;
import grammar.Absyn.AutomataEmptyTransition;
import grammar.Absyn.AutomataInitialState;
import grammar.Absyn.AutomataRule;
import grammar.Absyn.AutomataTransition;
import grammar.Absyn.AutomataTransitionRule;
import grammar.Absyn.CheckingStatement;
import grammar.Absyn.DFAAutomata;
import grammar.Absyn.EmptyChecking;
import grammar.Absyn.ExportDotStatement;
import grammar.Absyn.ExportTextStatement;
import grammar.Absyn.ListName;
import grammar.Absyn.LiteralName;
import grammar.Absyn.Model;
import grammar.Absyn.Name;
import grammar.Absyn.NameAutomata;
import grammar.Absyn.NumberName;
import grammar.Absyn.StatementRule;
import grammar.Absyn.SubsetChecking;
import grammar.Absyn.UniversalChecking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllVisitorImpl implements AllVisitor<Object, Specification>{

	private Map<String, Integer> mapStateToIndex = new HashMap<String, Integer>();
	private Map<String, Integer> mapLabelToIndex = new HashMap<String, Integer>();
	
	private int initState;
	private List<Edge> edges;
	private Set<Integer> acceptingStates;
	
	public Object visit(Model p, Specification arg) {
		for(AutomataRule automata: p.listautomatarule_){
			automata.accept(this, arg);
		}
		
		for(StatementRule statement: p.liststatementrule_){
			statement.accept(this, arg);
		}
		
		return null;
	}

	public Object visit(Automata p, Specification arg) {
		mapStateToIndex = new HashMap<String, Integer>();
		
		edges = new ArrayList<Edge>();
		acceptingStates = new HashSet<Integer>();
		
		String name = p.ident_;
		
		p.automatainitrule_.accept(this, arg);
		for(AutomataTransitionRule transition: p.listautomatatransitionrule_){
			transition.accept(this, arg);
		}
		p.automataacceptingsrule_.accept(this, arg);
		
		int numberOfStates = countNumberOfStates();
		
		finiteautomata.Automata automata = new finiteautomata.Automata(initState, numberOfStates, mapLabelToIndex.size());
		automata.setAcceptingStates(acceptingStates);
		for(Edge edge: edges){
			automata.addTrans(edge.source, edge.label, edge.dest);
		}
		
		LabelAutomata labelAutomata = new LabelAutomata(name, automata, mapStateToIndex, mapLabelToIndex);
		arg.addLabelAutomata(name, labelAutomata);
		
		return null;
	}

	public Object visit(AutomataInitialState p, Specification arg) {
		this.initState = getIndex(p.name_, mapStateToIndex);
		return null;
	}

	public Object visit(AutomataTransition p, Specification arg) {
		int label = getIndex(p.name_1, mapLabelToIndex);
		int source = getIndex(p.name_2, mapStateToIndex);
		int dest = getIndex(p.name_3, mapStateToIndex);
		
		Edge edge = new Edge(source, label, dest);
		edges.add(edge);
		
		return null;
	}

	public Object visit(AutomataEmptyTransition p, Specification arg) {
		int source = getIndex(p.name_1, mapStateToIndex);
		int dest = getIndex(p.name_2, mapStateToIndex);
		
		Edge edge = new Edge(source, finiteautomata.Automata.EPSILON_LABEL, dest);
		edges.add(edge);
		
		return null;
	}

	public Object visit(AutomataAcceptings p, Specification arg) {
		List<String> names = getNames(p.listname_);
		List<Integer> indexes = getIndexes(names, mapStateToIndex);
		
		acceptingStates.addAll(indexes);
		
		return null;
	}

	public Object visit(CheckingStatement p, Specification arg) {
		p.checkingstatementrule_.accept(this, arg);
		return null;
	}

	public Object visit(ActionStatement p, Specification arg) {
		p.exportstatementrule_.accept(this, arg);
		return null;
	}

	public Object visit(EmptyChecking p, Specification arg) {
		validateAutomataName(p.ident_, arg);
		arg.addAssertion(new EmptyAssertion(arg.getLabelAutomata(p.ident_)));
		return null;
	}

	public Object visit(UniversalChecking p, Specification arg) {
		validateAutomataName(p.ident_, arg);
		arg.addAssertion(new UniversalAssertion(arg.getLabelAutomata(p.ident_)));
		return null;
	}

	public Object visit(SubsetChecking p, Specification arg) {
		validateAutomataName(p.ident_1, arg);
		validateAutomataName(p.ident_2, arg);
		
		arg.addAssertion(new SubsetAssertion(arg.getLabelAutomata(p.ident_1), arg.getLabelAutomata(p.ident_2)));
		return null;
	}

	public Object visit(ExportDotStatement p, Specification arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ExportTextStatement p, Specification arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(DFAAutomata p, Specification arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(NameAutomata p, Specification arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(NumberName p, Specification arg) {
		return p.integer_.toString();
	}

	public Object visit(LiteralName p, Specification arg) {
		return p.ident_;
	}
	
	private Integer getIndex(Name name, Map<String, Integer> mapping){
		String nameLiteral = getName(name);
		
		return getIndex(nameLiteral, mapping);
	}
	
	private String getName(Name name){
		return (String) name.accept(this, null);
	}
	
	private Integer getIndex(String name, Map<String, Integer> mapping){
		if(mapping.containsKey(name)){
			return mapping.get(name);
		}
		else{
			int value = mapping.size();
			mapping.put(name, value);
			return value;
		}
	}
	
	private List<String> getNames(ListName names){
		List<String> result = new ArrayList<String>();
		for(Name name: names){
			result.add(getName(name));
		}
		
		return result;
	}
	
	private List<Integer> getIndexes(List<String> names, Map<String, Integer> mapping){
		List<Integer> result = new ArrayList<Integer>();
		for(String name: names){
			result.add(getIndex(name, mapping));
		}
		
		return result;
	}
	
	private int countNumberOfStates() {
		Set<Integer> states = new HashSet<Integer>();
		for(Edge edge: edges){
			states.add(edge.source);
			states.add(edge.dest);
		}
		
		int numberOfStates = states.size();
		return numberOfStates;
	}
	
	private void validateAutomataName(String name, Specification specification){
		LabelAutomata automata = specification.getLabelAutomata(name);
		if(automata == null){
			throw new RuntimeException("Automata name " + name + " does not exists!");
		}
	}
	
	private class Edge{
		public int source;
		public int label;
		public int dest;
		
		public Edge(int source, int label, int dest){
			this.source = source;
			this.label = label;
			this.dest = dest;
		}
	}

}
