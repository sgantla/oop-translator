package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import xtc.type.*;

import java.util.ArrayList;
import java.util.List;

public class FieldDeclaration extends Declaration {
    public List<Modifiers> modifiers;
    public Type type;
    public String name;
    public Expression assignment;
}

