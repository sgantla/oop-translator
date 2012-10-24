package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public abstract class TernaryExpression extends Expression {

    Expression operand1;
    Expression operand2;
    Expression operand3;
}
