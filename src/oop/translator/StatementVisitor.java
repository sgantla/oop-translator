package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class StatementTranslator extends Visitor
{

    public ConditionalStatement visitConditionalStatement(Node n) {
        //first child is binary expression
        Node expNode = n.getNode(0);
        //second is true statement
        Node trueStatement = n.getNode(1);
        //third is false statement
        Node falseStatement = n.getNode(2);
        
        Expression newExp = new ExpressionVisitor().dispatch(expNode);
        Statement trueStatement = dispatch(trueStatement);
        Statement falseStatement = dispatch(falseStatement);

        return new ConditionalStatement(newExp, trueStatement, falseStatement);
    }

    public Statement dispatch(Node n)
    {
        Object o = super.dispatch(n);
        return (Statement) o;
    }
}
