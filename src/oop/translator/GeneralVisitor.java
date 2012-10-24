package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.statements.*;
import oop.tree.expressions.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

//GeneralVisitor needs: Modifier, FormalParameter, Type
public class GeneralVisitor extends Visitor
{
    public Object visit(Node n)
    {
        Object o = super.dispatch(n);
        return o;
    }
}
