package oop.tree.interfaces;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.expressions.*;
import oop.tree.statements.*;
import oop.tree.*;

import xtc.tree.*;
import xtc.util.*;

import java.util.*;

public interface MethodDeclaration extends Declaration {
    List<Modifier> getModifiers();
    Type getReturnType();
    List<Modifier> getReturnTypeModifiers();
    String getMethodName();
    List<FormalParameter> getFormalParameters();
    ThrowsClause getThrowsClause();
    Block getMethodBody();
}
