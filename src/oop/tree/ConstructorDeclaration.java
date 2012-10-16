package oop.tree;

import java.util.ArrayList;
import java.util.List;

public class ConstructorDeclaration extends Declaration {
    List<Modifier> modifiers;
    String name;
    List<FormalParameter> formalParameters;
    //ThrowsClause throws;
    List<InitializationListEntry> initializations;
    Block methodBlock;
}
