package grammar;

import grammar.Absyn.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/** BNFC-Generated Fold Visitor */
public abstract class FoldVisitor<R,A> implements AllVisitor<R,A> {
    public abstract R leaf(A arg);
    public abstract R combine(R x, R y, A arg);

/* ModelRule */
    public R visit(grammar.Absyn.Model p, A arg) {
      R r = leaf(arg);
      for (AutomataRule x : p.listautomatarule_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      for (StatementRule x : p.liststatementrule_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* AutomataRule */
    public R visit(grammar.Absyn.Automata p, A arg) {
      R r = leaf(arg);
      r = combine(p.automatainitrule_.accept(this, arg), r, arg);
      for (AutomataTransitionRule x : p.listautomatatransitionrule_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      r = combine(p.automataacceptingsrule_.accept(this, arg), r, arg);
      return r;
    }

/* AutomataInitRule */
    public R visit(grammar.Absyn.AutomataInitialState p, A arg) {
      R r = leaf(arg);
      r = combine(p.name_.accept(this, arg), r, arg);
      return r;
    }

/* AutomataTransitionRule */
    public R visit(grammar.Absyn.AutomataTransition p, A arg) {
      R r = leaf(arg);
      r = combine(p.name_1.accept(this, arg), r, arg);
      r = combine(p.name_2.accept(this, arg), r, arg);
      r = combine(p.name_3.accept(this, arg), r, arg);
      return r;
    }
    public R visit(grammar.Absyn.AutomataEmptyTransition p, A arg) {
      R r = leaf(arg);
      r = combine(p.name_1.accept(this, arg), r, arg);
      r = combine(p.name_2.accept(this, arg), r, arg);
      return r;
    }

/* AutomataAcceptingsRule */
    public R visit(grammar.Absyn.AutomataAcceptings p, A arg) {
      R r = leaf(arg);
      for (Name x : p.listname_) {
        r = combine(x.accept(this,arg), r, arg);
      }
      return r;
    }

/* StatementRule */
    public R visit(grammar.Absyn.CheckingStatement p, A arg) {
      R r = leaf(arg);
      r = combine(p.checkingstatementrule_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(grammar.Absyn.ActionStatement p, A arg) {
      R r = leaf(arg);
      r = combine(p.exportstatementrule_.accept(this, arg), r, arg);
      return r;
    }

/* CheckingStatementRule */
    public R visit(grammar.Absyn.EmptyChecking p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(grammar.Absyn.UniversalChecking p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(grammar.Absyn.SubsetChecking p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* ExportStatementRule */
    public R visit(grammar.Absyn.ExportDotStatement p, A arg) {
      R r = leaf(arg);
      r = combine(p.automataexpressionrule_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(grammar.Absyn.ExportTextStatement p, A arg) {
      R r = leaf(arg);
      r = combine(p.automataexpressionrule_.accept(this, arg), r, arg);
      return r;
    }

/* AutomataExpressionRule */
    public R visit(grammar.Absyn.DFAAutomata p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(grammar.Absyn.NameAutomata p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* Name */
    public R visit(grammar.Absyn.NumberName p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(grammar.Absyn.LiteralName p, A arg) {
      R r = leaf(arg);
      return r;
    }


}
