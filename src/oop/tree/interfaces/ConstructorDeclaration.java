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

public interface ConstructorDeclaration extends Declaration {
    List<Modifier> getModifiers();
    String getName();
    List<FormalParameter> getFormalParameters();
    List<InitializationListEntry> getInitializations();
    Block getConstructorBody();
} 
