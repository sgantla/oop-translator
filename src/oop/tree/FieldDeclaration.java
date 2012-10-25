package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import xtc.type.*;

import java.util.ArrayList;
import java.util.List;

public class FieldDeclaration extends Declaration {
    List<Modifiers> modifiers;
    Type type;
    String name;
    Expression assignment;
}

