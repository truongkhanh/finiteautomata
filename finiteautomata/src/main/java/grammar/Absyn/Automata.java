package grammar.Absyn; // Java Package generated by the BNF Converter.

public class Automata extends AutomataRule {
  public final String ident_;
  public final AutomataInitRule automatainitrule_;
  public final ListAutomataTransitionRule listautomatatransitionrule_;
  public final AutomataAcceptingsRule automataacceptingsrule_;
  public Automata(String p1, AutomataInitRule p2, ListAutomataTransitionRule p3, AutomataAcceptingsRule p4) { ident_ = p1; automatainitrule_ = p2; listautomatatransitionrule_ = p3; automataacceptingsrule_ = p4; }

  public <R,A> R accept(grammar.Absyn.AutomataRule.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof grammar.Absyn.Automata) {
      grammar.Absyn.Automata x = (grammar.Absyn.Automata)o;
      return this.ident_.equals(x.ident_) && this.automatainitrule_.equals(x.automatainitrule_) && this.listautomatatransitionrule_.equals(x.listautomatatransitionrule_) && this.automataacceptingsrule_.equals(x.automataacceptingsrule_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(37*(37*(this.ident_.hashCode())+this.automatainitrule_.hashCode())+this.listautomatatransitionrule_.hashCode())+this.automataacceptingsrule_.hashCode();
  }


}