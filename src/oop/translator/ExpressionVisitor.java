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

public class ExpressionTranslator extends Visitor
{

    public RelationalExpression visitRelationalExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);
        
        Expression leftExp = dispatch(leftNode);
        Expression rightExp = dispatch(rightNode);

        return new RelationalExpression(leftExp, operator, rightExp);
    }
    
    public Expression dispatch(Node n)
    {
        Object o = super.dispatch(n);
        return (Expression) o;
    }

}
