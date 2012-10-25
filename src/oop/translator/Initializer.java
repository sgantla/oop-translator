package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.expressions.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class Initializer {
  
    private FieldDeclarationTranslator fieldDeclaration;
    private Expression expression;
    
    public Initializer(FieldDeclarationTranslator fieldDeclaration, Expression expression) {
	this.fieldDeclaration = fieldDeclaration;
	this.expression = expression;
    }
    
    public FieldDeclarationTranslator getFieldDeclaration() {
	return fieldDeclaration;
    }
    
    public Expression getExpression() {
	return expression;
    }
    
    public String getDeclarator() {
	return fieldDeclaration.getDeclarator();
    }
    
    public CppAstUtil.NodeName getNodeType() {
	return CppAstUtil.NodeName.InitializationListEntry;
    }
}
