package oop.tree.expressions;

import java.util.List;

public class ArrayInitializer {

    List<Expression> expressions;
    List<Modifier> modifiers;

    public ArrayInitializer(List<Expression> expressions, List<Modifier> modifiers) {
        this.expressions = expressions;
        this.modifiers = modifiers;
    }
}
