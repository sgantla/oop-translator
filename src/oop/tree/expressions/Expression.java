package oop.tree.expressions;

import oop.tree.*;
import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

import java.util.*;

// TODO: do we need this to implement CppAstNode?
public abstract class Expression extends CNode {
    
    String operator;
    Type returnType;

    public Type getReturnType() {
        return returnType;
    }
}
