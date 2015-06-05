package grammar.Absyn; // Java Package generated by the BNF Converter.

public abstract class AutomataExpressionRule implements java.io.Serializable {
  public abstract <R,A> R accept(AutomataExpressionRule.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(grammar.Absyn.DFAAutomata p, A arg);
    public R visit(grammar.Absyn.NameAutomata p, A arg);

  }

}