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

public class InstanceFieldStructData {

    private ClassT classType;
    private String typeName;
    private String declarator;
    private List<FieldDeclarationTranslator> fields;
    
    public void setClassType(ClassT classType) {
	this.classType = classType;
    }
    public void setTypeName(String typeName) {
	this.typeName = typeName;
    }
    public void setDeclarator(String declarator) {
	this.declarator = declarator;
    }
    public void setFieldDeclarations(List<FieldDeclarationTranslator> fields) {
	this.fields = fields;
    }
    
    public ClassT getClassType() {
	return classType;
    }
    public String getTypeName() {
	return typeName;
    }
    public String getDeclarator() {
	return declarator;
    }
    public List<FieldDeclarationTranslator> getFields() {
	return fields;
    }
}