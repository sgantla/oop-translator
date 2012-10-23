package oop.tree.statements;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class SwitchStatement extends Statement {

    Expression expression;
    List<SwitchClause> switchClause;

    public SwitchStatement(Expression exp, List<SwitchClause> switchClause) {
        expression = exp;
        this.switchClause = switchClause;
    }
}
