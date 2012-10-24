package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import java.util.ArrayList;
import java.util.List;

public class FieldDeclaration extends Declaration {
    List<Modifier> modifiers;
    Type type;
    String name;
    Expression assignment;
}

