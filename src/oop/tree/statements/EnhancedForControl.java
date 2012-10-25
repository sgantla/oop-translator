package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

import xtc.type.*;

import java.util.List;

public class EnhancedForControl extends ForControl {
    
    List<Modifier> modifiers;
    Type type;
    String name;
    Expression expression;

    public EnhancedForControl(List<Modifier> modifiers, Type type, String name, Expression expression) {
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
        this.expression = expression;
    }
}

