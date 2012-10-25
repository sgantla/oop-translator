package oop.tree;

import java.util.ArrayList;
import java.util.List;
import oop.tree.interfaces.*;

public class ConstructorDeclaration extends Declaration {
    public List<Modifier> modifiers;
    public String name;
    public List<FormalParameter> formalParameters;
    //public ThrowsClause throws;
    public List<InitializationListEntry> initializations;
    public Block methodBlock;
}
