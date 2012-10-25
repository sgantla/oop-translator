package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import java.util.ArrayList;
import java.util.List;

public class MethodDeclaration extends Declaration {
    public List<Modifier> modifiers;
    public Type returnType;
    public List<Modifier> returnTypeModifiers;
    public String name;
    public List<FormalParameter> formalParameters;
    //public ThrowsClause throws;
    public Block methodBlock;

    boolean _isVirtual;
}
