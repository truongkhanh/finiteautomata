package grammar;
import grammar.Absyn.*;
/** BNFC-Generated Abstract Visitor */
public class AbstractVisitor<R,A> implements AllVisitor<R,A> {
/* ModelRule */
    public R visit(grammar.Absyn.Model p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.ModelRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AutomataRule */
    public R visit(grammar.Absyn.Automata p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.AutomataRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AutomataInitRule */
    public R visit(grammar.Absyn.AutomataInitialState p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.AutomataInitRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AutomataTransitionRule */
    public R visit(grammar.Absyn.AutomataTransition p, A arg) { return visitDefault(p, arg); }
    public R visit(grammar.Absyn.AutomataEmptyTransition p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.AutomataTransitionRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AutomataAcceptingsRule */
    public R visit(grammar.Absyn.AutomataAcceptings p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.AutomataAcceptingsRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* StatementRule */
    public R visit(grammar.Absyn.CheckingStatement p, A arg) { return visitDefault(p, arg); }
    public R visit(grammar.Absyn.ActionStatement p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.StatementRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* CheckingStatementRule */
    public R visit(grammar.Absyn.EmptyChecking p, A arg) { return visitDefault(p, arg); }
    public R visit(grammar.Absyn.UniversalChecking p, A arg) { return visitDefault(p, arg); }
    public R visit(grammar.Absyn.SubsetChecking p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.CheckingStatementRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* ExportStatementRule */
    public R visit(grammar.Absyn.ExportDotStatement p, A arg) { return visitDefault(p, arg); }
    public R visit(grammar.Absyn.ExportTextStatement p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.ExportStatementRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AutomataExpressionRule */
    public R visit(grammar.Absyn.DFAAutomata p, A arg) { return visitDefault(p, arg); }
    public R visit(grammar.Absyn.NameAutomata p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.AutomataExpressionRule p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Name */
    public R visit(grammar.Absyn.NumberName p, A arg) { return visitDefault(p, arg); }
    public R visit(grammar.Absyn.LiteralName p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(grammar.Absyn.Name p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }

}
