package grammar;
import grammar.Absyn.*;
/*** BNFC-Generated Visitor Design Pattern Skeleton. ***/
/* This implements the common visitor design pattern.
   Tests show it to be slightly less efficient than the
   instanceof method, but easier to use. 
   Replace the R and A parameters with the desired return
   and context types.*/

public class VisitSkel
{
  public class ModelRuleVisitor<R,A> implements ModelRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.Model p, A arg)
    {
      /* Code For Model Goes Here */

      for (AutomataRule x : p.listautomatarule_) {
      }
      for (StatementRule x : p.liststatementrule_) {
      }

      return null;
    }

  }
  public class AutomataRuleVisitor<R,A> implements AutomataRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.Automata p, A arg)
    {
      /* Code For Automata Goes Here */

      //p.ident_;
      p.automatainitrule_.accept(new AutomataInitRuleVisitor<R,A>(), arg);
      for (AutomataTransitionRule x : p.listautomatatransitionrule_) {
      }
      p.automataacceptingsrule_.accept(new AutomataAcceptingsRuleVisitor<R,A>(), arg);

      return null;
    }

  }
  public class AutomataInitRuleVisitor<R,A> implements AutomataInitRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.AutomataInitialState p, A arg)
    {
      /* Code For AutomataInitialState Goes Here */

      p.name_.accept(new NameVisitor<R,A>(), arg);

      return null;
    }

  }
  public class AutomataTransitionRuleVisitor<R,A> implements AutomataTransitionRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.AutomataTransition p, A arg)
    {
      /* Code For AutomataTransition Goes Here */

      p.name_1.accept(new NameVisitor<R,A>(), arg);
      p.name_2.accept(new NameVisitor<R,A>(), arg);
      p.name_3.accept(new NameVisitor<R,A>(), arg);

      return null;
    }
    public R visit(grammar.Absyn.AutomataEmptyTransition p, A arg)
    {
      /* Code For AutomataEmptyTransition Goes Here */

      p.name_1.accept(new NameVisitor<R,A>(), arg);
      p.name_2.accept(new NameVisitor<R,A>(), arg);

      return null;
    }

  }
  public class AutomataAcceptingsRuleVisitor<R,A> implements AutomataAcceptingsRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.AutomataAcceptings p, A arg)
    {
      /* Code For AutomataAcceptings Goes Here */

      for (Name x : p.listname_) {
      }

      return null;
    }

  }
  public class StatementRuleVisitor<R,A> implements StatementRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.CheckingStatement p, A arg)
    {
      /* Code For CheckingStatement Goes Here */

      p.checkingstatementrule_.accept(new CheckingStatementRuleVisitor<R,A>(), arg);

      return null;
    }
    public R visit(grammar.Absyn.ActionStatement p, A arg)
    {
      /* Code For ActionStatement Goes Here */

      p.exportstatementrule_.accept(new ExportStatementRuleVisitor<R,A>(), arg);

      return null;
    }

  }
  public class CheckingStatementRuleVisitor<R,A> implements CheckingStatementRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.EmptyChecking p, A arg)
    {
      /* Code For EmptyChecking Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(grammar.Absyn.UniversalChecking p, A arg)
    {
      /* Code For UniversalChecking Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(grammar.Absyn.SubsetChecking p, A arg)
    {
      /* Code For SubsetChecking Goes Here */

      //p.ident_1;
      //p.ident_2;

      return null;
    }

  }
  public class ExportStatementRuleVisitor<R,A> implements ExportStatementRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.ExportDotStatement p, A arg)
    {
      /* Code For ExportDotStatement Goes Here */

      p.automataexpressionrule_.accept(new AutomataExpressionRuleVisitor<R,A>(), arg);

      return null;
    }
    public R visit(grammar.Absyn.ExportTextStatement p, A arg)
    {
      /* Code For ExportTextStatement Goes Here */

      p.automataexpressionrule_.accept(new AutomataExpressionRuleVisitor<R,A>(), arg);

      return null;
    }

  }
  public class AutomataExpressionRuleVisitor<R,A> implements AutomataExpressionRule.Visitor<R,A>
  {
    public R visit(grammar.Absyn.DFAAutomata p, A arg)
    {
      /* Code For DFAAutomata Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(grammar.Absyn.NameAutomata p, A arg)
    {
      /* Code For NameAutomata Goes Here */

      //p.ident_;

      return null;
    }

  }
  public class NameVisitor<R,A> implements Name.Visitor<R,A>
  {
    public R visit(grammar.Absyn.NumberName p, A arg)
    {
      /* Code For NumberName Goes Here */

      //p.integer_;

      return null;
    }
    public R visit(grammar.Absyn.LiteralName p, A arg)
    {
      /* Code For LiteralName Goes Here */

      //p.ident_;

      return null;
    }

  }
}