package oop.tree;

import java.util.ArrayList;
import java.util.List;
import oop.tree.statements.*;
import oop.tree.expressions.*;

public class ConstructorDeclaration extends Declaration {
    public List<Modifiers> modifiers;
    public String name;
    public List<FormalParameter> formalParameters;
    //public ThrowsClause throws;
    public List<InitializationListEntry> initializations;
    public Block methodBlock;
}
