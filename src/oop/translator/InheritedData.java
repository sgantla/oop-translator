package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class InheritedData {
    private List<MethodData> virtualMethodTable = new ArrayList<MethodData>(); 
    private List<MethodData> staticMethodTable = new ArrayList<MethodData>();
    private List<ConstructorData> constructorTable = new ArrayList<ConstructorData>();
    private List<InitializationField> initializationList = new ArrayList<InitializationField>();
    private List<FieldDeclarationTranslator> fieldDeclarations = new ArrayList<FieldDeclarationTranslator>();
    
    public List<MethodData> getVirtualMethodTable() {
	return virtualMethodTable;
    }
    public List<MethodData> getStaticMethodTable() {
	return staticMethodTable;
    }
    public List<ConstructorData> getConstructorTable() { 
	return constructorTable;
    }
    public List<InitializationField> getInitializationList() { 
	return initializationList;
    }
    public List<FieldDeclarationTranslator> getFieldDeclarations() {
	return fieldDeclarations;
    }
    
    public void setVirtualMethodTable(List<MethodData> virtualMethodTable) {
	this.virtualMethodTable = virtualMethodTable;
    }
    public void setStaticMethodTable(List<MethodData> staticMethodTable) {
	this.staticMethodTable = staticMethodTable;
    }
    public void setConstructorTable(List<ConstructorData> constructorTable) { 
	this.constructorTable = constructorTable;
    }
    public void setInitializationList(List<InitializationField> initializationList) { 
	this.initializationList = initializationList;
    }
    public void setFieldDeclarations(List<FieldDeclarationTranslator> fieldDeclarations) {
	this.fieldDeclarations = fieldDeclarations;
    }
    
    public InheritedData deepCopy() {
	return this; // TODO: not yet implemented, do we need to implement clone() on all nodes?
    }
}
