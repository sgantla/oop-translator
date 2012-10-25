package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import xtc.type.*;

import java.util.ArrayList;
import java.util.List;

public class MethodDeclaration extends Declaration {
    List<Modifiers> modifiers;
    Type returnType;
    List<Modifiers> returnTypeModifiers;
    String name;
    List<FormalParameter> formalParameters;
    //ThrowsClause throws;
    Block methodBlock;

    boolean _isVirtual;
}
