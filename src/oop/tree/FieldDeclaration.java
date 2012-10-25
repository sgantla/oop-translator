package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import java.util.ArrayList;
import java.util.List;

public class FieldDeclaration extends Declaration {
    public List<Modifier> modifiers;
    public Type type;
    public String name;
    public Expression assignment;
}

