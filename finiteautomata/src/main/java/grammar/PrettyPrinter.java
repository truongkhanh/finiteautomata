package grammar;
import grammar.Absyn.*;

public class PrettyPrinter
{
  //For certain applications increasing the initial size of the buffer may improve performance.
  private static final int INITIAL_BUFFER_SIZE = 128;
  //You may wish to change the parentheses used in precedence.
  private static final String _L_PAREN = new String("(");
  private static final String _R_PAREN = new String(")");
  //You may wish to change render
  private static void render(String s)
  {
    if (s.equals("{"))
    {
       buf_.append("\n");
       indent();
       buf_.append(s);
       _n_ = _n_ + 2;
       buf_.append("\n");
       indent();
    }
    else if (s.equals("(") || s.equals("["))
       buf_.append(s);
    else if (s.equals(")") || s.equals("]"))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals("}"))
    {
       _n_ = _n_ - 2;
       backup();
       backup();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals(","))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals(";"))
    {
       backup();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals("")) return;
    else
    {
       buf_.append(s);
       buf_.append(" ");
    }
  }


  //  print and show methods are defined for each category.
  public static String print(grammar.Absyn.ModelRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.ModelRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.AutomataRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.AutomataRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.AutomataInitRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.AutomataInitRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.AutomataTransitionRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.AutomataTransitionRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.AutomataAcceptingsRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.AutomataAcceptingsRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.StatementRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.StatementRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.CheckingStatementRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.CheckingStatementRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.ExportStatementRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.ExportStatementRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.AutomataExpressionRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.AutomataExpressionRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.Name foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.Name foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.ListAutomataRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.ListAutomataRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.ListAutomataTransitionRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.ListAutomataTransitionRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.ListStatementRule foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.ListStatementRule foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(grammar.Absyn.ListName foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(grammar.Absyn.ListName foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  /***   You shouldn't need to change anything beyond this point.   ***/

  private static void pp(grammar.Absyn.ModelRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.Model)
    {
       grammar.Absyn.Model _model = (grammar.Absyn.Model) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_model.listautomatarule_, 0);
       pp(_model.liststatementrule_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.AutomataRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.Automata)
    {
       grammar.Absyn.Automata _automata = (grammar.Absyn.Automata) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("automata");
       pp(_automata.ident_, 0);
       render("{");
       pp(_automata.automatainitrule_, 0);
       pp(_automata.listautomatatransitionrule_, 0);
       pp(_automata.automataacceptingsrule_, 0);
       render("}");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.AutomataInitRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.AutomataInitialState)
    {
       grammar.Absyn.AutomataInitialState _automatainitialstate = (grammar.Absyn.AutomataInitialState) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("init");
       render(":");
       pp(_automatainitialstate.name_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.AutomataTransitionRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.AutomataTransition)
    {
       grammar.Absyn.AutomataTransition _automatatransition = (grammar.Absyn.AutomataTransition) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_automatatransition.name_1, 0);
       render(",");
       pp(_automatatransition.name_2, 0);
       render("->");
       pp(_automatatransition.name_3, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof grammar.Absyn.AutomataEmptyTransition)
    {
       grammar.Absyn.AutomataEmptyTransition _automataemptytransition = (grammar.Absyn.AutomataEmptyTransition) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_automataemptytransition.name_1, 0);
       render("->");
       pp(_automataemptytransition.name_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.AutomataAcceptingsRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.AutomataAcceptings)
    {
       grammar.Absyn.AutomataAcceptings _automataacceptings = (grammar.Absyn.AutomataAcceptings) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("accepting");
       render(":");
       pp(_automataacceptings.listname_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.StatementRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.CheckingStatement)
    {
       grammar.Absyn.CheckingStatement _checkingstatement = (grammar.Absyn.CheckingStatement) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_checkingstatement.checkingstatementrule_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof grammar.Absyn.ActionStatement)
    {
       grammar.Absyn.ActionStatement _actionstatement = (grammar.Absyn.ActionStatement) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_actionstatement.exportstatementrule_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.CheckingStatementRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.EmptyChecking)
    {
       grammar.Absyn.EmptyChecking _emptychecking = (grammar.Absyn.EmptyChecking) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("check");
       pp(_emptychecking.ident_, 0);
       render("empty");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof grammar.Absyn.UniversalChecking)
    {
       grammar.Absyn.UniversalChecking _universalchecking = (grammar.Absyn.UniversalChecking) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("check");
       pp(_universalchecking.ident_, 0);
       render("universal");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof grammar.Absyn.SubsetChecking)
    {
       grammar.Absyn.SubsetChecking _subsetchecking = (grammar.Absyn.SubsetChecking) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("check");
       pp(_subsetchecking.ident_1, 0);
       render("subset");
       pp(_subsetchecking.ident_2, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.ExportStatementRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.ExportDotStatement)
    {
       grammar.Absyn.ExportDotStatement _exportdotstatement = (grammar.Absyn.ExportDotStatement) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("exportDot");
       pp(_exportdotstatement.automataexpressionrule_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof grammar.Absyn.ExportTextStatement)
    {
       grammar.Absyn.ExportTextStatement _exporttextstatement = (grammar.Absyn.ExportTextStatement) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("exportText");
       pp(_exporttextstatement.automataexpressionrule_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.AutomataExpressionRule foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.DFAAutomata)
    {
       grammar.Absyn.DFAAutomata _dfaautomata = (grammar.Absyn.DFAAutomata) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("dfa");
       pp(_dfaautomata.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof grammar.Absyn.NameAutomata)
    {
       grammar.Absyn.NameAutomata _nameautomata = (grammar.Absyn.NameAutomata) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_nameautomata.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.Name foo, int _i_)
  {
    if (foo instanceof grammar.Absyn.NumberName)
    {
       grammar.Absyn.NumberName _numbername = (grammar.Absyn.NumberName) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_numbername.integer_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof grammar.Absyn.LiteralName)
    {
       grammar.Absyn.LiteralName _literalname = (grammar.Absyn.LiteralName) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_literalname.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(grammar.Absyn.ListAutomataRule foo, int _i_)
  {
     for (java.util.Iterator<AutomataRule> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }
  }

  private static void pp(grammar.Absyn.ListAutomataTransitionRule foo, int _i_)
  {
     for (java.util.Iterator<AutomataTransitionRule> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(";");
       } else {
         render("");
       }
     }
  }

  private static void pp(grammar.Absyn.ListStatementRule foo, int _i_)
  {
     for (java.util.Iterator<StatementRule> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }
  }

  private static void pp(grammar.Absyn.ListName foo, int _i_)
  {
     for (java.util.Iterator<Name> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }


  private static void sh(grammar.Absyn.ModelRule foo)
  {
    if (foo instanceof grammar.Absyn.Model)
    {
       grammar.Absyn.Model _model = (grammar.Absyn.Model) foo;
       render("(");
       render("Model");
       render("[");
       sh(_model.listautomatarule_);
       render("]");
       render("[");
       sh(_model.liststatementrule_);
       render("]");
       render(")");
    }
  }

  private static void sh(grammar.Absyn.AutomataRule foo)
  {
    if (foo instanceof grammar.Absyn.Automata)
    {
       grammar.Absyn.Automata _automata = (grammar.Absyn.Automata) foo;
       render("(");
       render("Automata");
       sh(_automata.ident_);
       sh(_automata.automatainitrule_);
       render("[");
       sh(_automata.listautomatatransitionrule_);
       render("]");
       sh(_automata.automataacceptingsrule_);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.AutomataInitRule foo)
  {
    if (foo instanceof grammar.Absyn.AutomataInitialState)
    {
       grammar.Absyn.AutomataInitialState _automatainitialstate = (grammar.Absyn.AutomataInitialState) foo;
       render("(");
       render("AutomataInitialState");
       sh(_automatainitialstate.name_);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.AutomataTransitionRule foo)
  {
    if (foo instanceof grammar.Absyn.AutomataTransition)
    {
       grammar.Absyn.AutomataTransition _automatatransition = (grammar.Absyn.AutomataTransition) foo;
       render("(");
       render("AutomataTransition");
       sh(_automatatransition.name_1);
       sh(_automatatransition.name_2);
       sh(_automatatransition.name_3);
       render(")");
    }
    if (foo instanceof grammar.Absyn.AutomataEmptyTransition)
    {
       grammar.Absyn.AutomataEmptyTransition _automataemptytransition = (grammar.Absyn.AutomataEmptyTransition) foo;
       render("(");
       render("AutomataEmptyTransition");
       sh(_automataemptytransition.name_1);
       sh(_automataemptytransition.name_2);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.AutomataAcceptingsRule foo)
  {
    if (foo instanceof grammar.Absyn.AutomataAcceptings)
    {
       grammar.Absyn.AutomataAcceptings _automataacceptings = (grammar.Absyn.AutomataAcceptings) foo;
       render("(");
       render("AutomataAcceptings");
       render("[");
       sh(_automataacceptings.listname_);
       render("]");
       render(")");
    }
  }

  private static void sh(grammar.Absyn.StatementRule foo)
  {
    if (foo instanceof grammar.Absyn.CheckingStatement)
    {
       grammar.Absyn.CheckingStatement _checkingstatement = (grammar.Absyn.CheckingStatement) foo;
       render("(");
       render("CheckingStatement");
       sh(_checkingstatement.checkingstatementrule_);
       render(")");
    }
    if (foo instanceof grammar.Absyn.ActionStatement)
    {
       grammar.Absyn.ActionStatement _actionstatement = (grammar.Absyn.ActionStatement) foo;
       render("(");
       render("ActionStatement");
       sh(_actionstatement.exportstatementrule_);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.CheckingStatementRule foo)
  {
    if (foo instanceof grammar.Absyn.EmptyChecking)
    {
       grammar.Absyn.EmptyChecking _emptychecking = (grammar.Absyn.EmptyChecking) foo;
       render("(");
       render("EmptyChecking");
       sh(_emptychecking.ident_);
       render(")");
    }
    if (foo instanceof grammar.Absyn.UniversalChecking)
    {
       grammar.Absyn.UniversalChecking _universalchecking = (grammar.Absyn.UniversalChecking) foo;
       render("(");
       render("UniversalChecking");
       sh(_universalchecking.ident_);
       render(")");
    }
    if (foo instanceof grammar.Absyn.SubsetChecking)
    {
       grammar.Absyn.SubsetChecking _subsetchecking = (grammar.Absyn.SubsetChecking) foo;
       render("(");
       render("SubsetChecking");
       sh(_subsetchecking.ident_1);
       sh(_subsetchecking.ident_2);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.ExportStatementRule foo)
  {
    if (foo instanceof grammar.Absyn.ExportDotStatement)
    {
       grammar.Absyn.ExportDotStatement _exportdotstatement = (grammar.Absyn.ExportDotStatement) foo;
       render("(");
       render("ExportDotStatement");
       sh(_exportdotstatement.automataexpressionrule_);
       render(")");
    }
    if (foo instanceof grammar.Absyn.ExportTextStatement)
    {
       grammar.Absyn.ExportTextStatement _exporttextstatement = (grammar.Absyn.ExportTextStatement) foo;
       render("(");
       render("ExportTextStatement");
       sh(_exporttextstatement.automataexpressionrule_);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.AutomataExpressionRule foo)
  {
    if (foo instanceof grammar.Absyn.DFAAutomata)
    {
       grammar.Absyn.DFAAutomata _dfaautomata = (grammar.Absyn.DFAAutomata) foo;
       render("(");
       render("DFAAutomata");
       sh(_dfaautomata.ident_);
       render(")");
    }
    if (foo instanceof grammar.Absyn.NameAutomata)
    {
       grammar.Absyn.NameAutomata _nameautomata = (grammar.Absyn.NameAutomata) foo;
       render("(");
       render("NameAutomata");
       sh(_nameautomata.ident_);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.Name foo)
  {
    if (foo instanceof grammar.Absyn.NumberName)
    {
       grammar.Absyn.NumberName _numbername = (grammar.Absyn.NumberName) foo;
       render("(");
       render("NumberName");
       sh(_numbername.integer_);
       render(")");
    }
    if (foo instanceof grammar.Absyn.LiteralName)
    {
       grammar.Absyn.LiteralName _literalname = (grammar.Absyn.LiteralName) foo;
       render("(");
       render("LiteralName");
       sh(_literalname.ident_);
       render(")");
    }
  }

  private static void sh(grammar.Absyn.ListAutomataRule foo)
  {
     for (java.util.Iterator<AutomataRule> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(grammar.Absyn.ListAutomataTransitionRule foo)
  {
     for (java.util.Iterator<AutomataTransitionRule> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(grammar.Absyn.ListStatementRule foo)
  {
     for (java.util.Iterator<StatementRule> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(grammar.Absyn.ListName foo)
  {
     for (java.util.Iterator<Name> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }


  private static void pp(Integer n, int _i_) { buf_.append(n); buf_.append(" "); }
  private static void pp(Double d, int _i_) { buf_.append(d); buf_.append(" "); }
  private static void pp(String s, int _i_) { buf_.append(s); buf_.append(" "); }
  private static void pp(Character c, int _i_) { buf_.append("'" + c.toString() + "'"); buf_.append(" "); }
  private static void sh(Integer n) { render(n.toString()); }
  private static void sh(Double d) { render(d.toString()); }
  private static void sh(Character c) { render(c.toString()); }
  private static void sh(String s) { printQuoted(s); }
  private static void printQuoted(String s) { render("\"" + s + "\""); }
  private static void indent()
  {
    int n = _n_;
    while (n > 0)
    {
      buf_.append(" ");
      n--;
    }
  }
  private static void backup()
  {
     if (buf_.charAt(buf_.length() - 1) == ' ') {
      buf_.setLength(buf_.length() - 1);
    }
  }
  private static void trim()
  {
     while (buf_.length() > 0 && buf_.charAt(0) == ' ')
        buf_.deleteCharAt(0); 
    while (buf_.length() > 0 && buf_.charAt(buf_.length()-1) == ' ')
        buf_.deleteCharAt(buf_.length()-1);
  }
  private static int _n_ = 0;
  private static StringBuilder buf_ = new StringBuilder(INITIAL_BUFFER_SIZE);
}

