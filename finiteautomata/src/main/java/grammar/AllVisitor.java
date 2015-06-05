package grammar;

import grammar.Absyn.*;

/** BNFC-Generated All Visitor */
public interface AllVisitor<R,A> extends
  grammar.Absyn.ModelRule.Visitor<R,A>,
  grammar.Absyn.AutomataRule.Visitor<R,A>,
  grammar.Absyn.AutomataInitRule.Visitor<R,A>,
  grammar.Absyn.AutomataTransitionRule.Visitor<R,A>,
  grammar.Absyn.AutomataAcceptingsRule.Visitor<R,A>,
  grammar.Absyn.StatementRule.Visitor<R,A>,
  grammar.Absyn.CheckingStatementRule.Visitor<R,A>,
  grammar.Absyn.ExportStatementRule.Visitor<R,A>,
  grammar.Absyn.AutomataExpressionRule.Visitor<R,A>,
  grammar.Absyn.Name.Visitor<R,A>
{}
