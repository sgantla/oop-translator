package oop.tree;

import java.util.ArrayList;
import java.util.List;

public class MethodDeclaration extends Declaration {
    List<Modifier> modifiers;
    Type returnType;
    List<Modifier> returnTypeModifiers;
    String name;
    List<FormalParameter> formalParameters;
    //ThrowsClause throws;
    Block methodBlock;

    boolean _isVirtual;
}
