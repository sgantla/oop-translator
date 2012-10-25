package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.statements.*;
import oop.tree.expressions.*;
import oop.tree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

//GeneralVisitor needs: Modifier, FormalParameter, Type, ClassBody
public class GeneralVisitor extends Visitor
{
    public Modifiers visitModifiers(Node n) {
        return null;
    }

    public FormalParameter visitFormalParameter(Node n) {
        return null;
    }

    public Type visitType(Node n) {
        return null;
    }

    public ClassBody visitClassBody(Node n) {
        return null;
    }

    public Object generalDispatch(Node n)
    {
        Object o = super.dispatch(n);
        return o;
    }
}
