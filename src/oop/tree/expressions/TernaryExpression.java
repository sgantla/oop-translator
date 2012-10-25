package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public abstract class TernaryExpression extends Expression {

    public Expression operand1;
    public Expression operand2;
    public Expression operand3;
}
