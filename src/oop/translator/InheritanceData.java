package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class InheritanceData {
    private VTable virtualMethodTable = new ArrayList<MethodData>(); 
    private List<InstanceFieldStructData> instanceFieldStructs = new ArrayList<FieldDeclarationTranslator>();
    
    public VTable getVirtualMethodTable() {}
    public List<InstanceFieldStructData> getInstanceFieldStructs() {}
    
    public void setVirtualMethodTable(VTable table) {}
    public void setInstanceFieldStructs(List<InstanceFieldStructData> instanceFieldStructs) {}
}

public class ConstructorInitializationData {
    
    private FieldDeclaration declaration;
    private Expression expression;
}