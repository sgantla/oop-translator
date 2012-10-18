package oop.tree.interfaces;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
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