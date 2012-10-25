package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import java.util.List;

public class ArrayInitializer extends CNode {

    List<Expression> expressions;
    List<Modifiers> modifiers;

    public ArrayInitializer(List<Expression> expressions, List<Modifiers> modifiers) {
        this.expressions = expressions;
        this.modifiers = modifiers;
    }
}
