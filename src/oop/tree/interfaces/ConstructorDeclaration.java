package oop.tree.interfaces;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;

public interface ConstructorDeclaration extends Declaration {
    List<Modifier> getModifiers();
    String getName();
    List<FormalParameter> getFormalParameters();
    List<InitializationListEntry> getInitializations();
    Block getConstructorBody();
} 