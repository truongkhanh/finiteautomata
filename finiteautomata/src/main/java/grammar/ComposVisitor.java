package grammar;
import grammar.Absyn.*;
/** BNFC-Generated Composition Visitor
*/

public class ComposVisitor<A> implements
  grammar.Absyn.ModelRule.Visitor<grammar.Absyn.ModelRule,A>,
  grammar.Absyn.AutomataRule.Visitor<grammar.Absyn.AutomataRule,A>,
  grammar.Absyn.AutomataInitRule.Visitor<grammar.Absyn.AutomataInitRule,A>,
  grammar.Absyn.AutomataTransitionRule.Visitor<grammar.Absyn.AutomataTransitionRule,A>,
  grammar.Absyn.AutomataAcceptingsRule.Visitor<grammar.Absyn.AutomataAcceptingsRule,A>,
  grammar.Absyn.StatementRule.Visitor<grammar.Absyn.StatementRule,A>,
  grammar.Absyn.CheckingStatementRule.Visitor<grammar.Absyn.CheckingStatementRule,A>,
  grammar.Absyn.ExportStatementRule.Visitor<grammar.Absyn.ExportStatementRule,A>,
  grammar.Absyn.AutomataExpressionRule.Visitor<grammar.Absyn.AutomataExpressionRule,A>,
  grammar.Absyn.Name.Visitor<grammar.Absyn.Name,A>
{
/* ModelRule */
    public ModelRule visit(grammar.Absyn.Model p, A arg)
    {
      ListAutomataRule listautomatarule_ = new ListAutomataRule();
      for (AutomataRule x : p.listautomatarule_) {
        listautomatarule_.add(x.accept(this,arg));
      }
      ListStatementRule liststatementrule_ = new ListStatementRule();
      for (StatementRule x : p.liststatementrule_) {
        liststatementrule_.add(x.accept(this,arg));
      }

      return new grammar.Absyn.Model(listautomatarule_, liststatementrule_);
    }

/* AutomataRule */
    public AutomataRule visit(grammar.Absyn.Automata p, A arg)
    {
      String ident_ = p.ident_;
      AutomataInitRule automatainitrule_ = p.automatainitrule_.accept(this, arg);
      ListAutomataTransitionRule listautomatatransitionrule_ = new ListAutomataTransitionRule();
      for (AutomataTransitionRule x : p.listautomatatransitionrule_) {
        listautomatatransitionrule_.add(x.accept(this,arg));
      }
      AutomataAcceptingsRule automataacceptingsrule_ = p.automataacceptingsrule_.accept(this, arg);

      return new grammar.Absyn.Automata(ident_, automatainitrule_, listautomatatransitionrule_, automataacceptingsrule_);
    }

/* AutomataInitRule */
    public AutomataInitRule visit(grammar.Absyn.AutomataInitialState p, A arg)
    {
      Name name_ = p.name_.accept(this, arg);

      return new grammar.Absyn.AutomataInitialState(name_);
    }

/* AutomataTransitionRule */
    public AutomataTransitionRule visit(grammar.Absyn.AutomataTransition p, A arg)
    {
      Name name_1 = p.name_1.accept(this, arg);
      Name name_2 = p.name_2.accept(this, arg);
      Name name_3 = p.name_3.accept(this, arg);

      return new grammar.Absyn.AutomataTransition(name_1, name_2, name_3);
    }
    public AutomataTransitionRule visit(grammar.Absyn.AutomataEmptyTransition p, A arg)
    {
      Name name_1 = p.name_1.accept(this, arg);
      Name name_2 = p.name_2.accept(this, arg);

      return new grammar.Absyn.AutomataEmptyTransition(name_1, name_2);
    }

/* AutomataAcceptingsRule */
    public AutomataAcceptingsRule visit(grammar.Absyn.AutomataAcceptings p, A arg)
    {
      ListName listname_ = new ListName();
      for (Name x : p.listname_) {
        listname_.add(x.accept(this,arg));
      }

      return new grammar.Absyn.AutomataAcceptings(listname_);
    }

/* StatementRule */
    public StatementRule visit(grammar.Absyn.CheckingStatement p, A arg)
    {
      CheckingStatementRule checkingstatementrule_ = p.checkingstatementrule_.accept(this, arg);

      return new grammar.Absyn.CheckingStatement(checkingstatementrule_);
    }
    public StatementRule visit(grammar.Absyn.ActionStatement p, A arg)
    {
      ExportStatementRule exportstatementrule_ = p.exportstatementrule_.accept(this, arg);

      return new grammar.Absyn.ActionStatement(exportstatementrule_);
    }

/* CheckingStatementRule */
    public CheckingStatementRule visit(grammar.Absyn.EmptyChecking p, A arg)
    {
      String ident_ = p.ident_;

      return new grammar.Absyn.EmptyChecking(ident_);
    }
    public CheckingStatementRule visit(grammar.Absyn.UniversalChecking p, A arg)
    {
      String ident_ = p.ident_;

      return new grammar.Absyn.UniversalChecking(ident_);
    }
    public CheckingStatementRule visit(grammar.Absyn.SubsetChecking p, A arg)
    {
      String ident_1 = p.ident_1;
      String ident_2 = p.ident_2;

      return new grammar.Absyn.SubsetChecking(ident_1, ident_2);
    }

/* ExportStatementRule */
    public ExportStatementRule visit(grammar.Absyn.ExportDotStatement p, A arg)
    {
      AutomataExpressionRule automataexpressionrule_ = p.automataexpressionrule_.accept(this, arg);

      return new grammar.Absyn.ExportDotStatement(automataexpressionrule_);
    }
    public ExportStatementRule visit(grammar.Absyn.ExportTextStatement p, A arg)
    {
      AutomataExpressionRule automataexpressionrule_ = p.automataexpressionrule_.accept(this, arg);

      return new grammar.Absyn.ExportTextStatement(automataexpressionrule_);
    }

/* AutomataExpressionRule */
    public AutomataExpressionRule visit(grammar.Absyn.DFAAutomata p, A arg)
    {
      String ident_ = p.ident_;

      return new grammar.Absyn.DFAAutomata(ident_);
    }
    public AutomataExpressionRule visit(grammar.Absyn.NameAutomata p, A arg)
    {
      String ident_ = p.ident_;

      return new grammar.Absyn.NameAutomata(ident_);
    }

/* Name */
    public Name visit(grammar.Absyn.NumberName p, A arg)
    {
      Integer integer_ = p.integer_;

      return new grammar.Absyn.NumberName(integer_);
    }
    public Name visit(grammar.Absyn.LiteralName p, A arg)
    {
      String ident_ = p.ident_;

      return new grammar.Absyn.LiteralName(ident_);
    }

}