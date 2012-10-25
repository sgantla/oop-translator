package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import xtc.type.*;

import java.util.ArrayList;
import java.util.List;

public class MethodDeclaration extends Declaration {
    public List<Modifiers> modifiers;
    public Type returnType;
    public List<Modifiers> returnTypeModifiers;
    public String name;
    public List<FormalParameter> formalParameters;
    //public ThrowsClause throws;
    public Block methodBlock;

    boolean _isVirtual;
}
