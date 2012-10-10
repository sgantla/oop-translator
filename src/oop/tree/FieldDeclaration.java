package oop.tree;

import java.util.ArrayList;
import java.util.List;

public class FieldDeclaration extends Declaration {
    List<Modifier> modifiers;
    Type type;
    String name;
    Expression assignment;
}

