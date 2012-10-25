package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;
import xtc.Constants;

import java.util.*;
import java.io.*;

public class MethodDeclarationTranslator extends MethodDeclaration {

    private class Input {
	private MethodT type;
	private List<Modifier> modifiers = new ArrayList<Modifier>();
    }
    private class Output {
	private List<Modifier> modifiers;
	private String methodName;
	private Type returnType;
	private List<FormalParameter> formalParameters;
	private ThrowsClause throwsClause;
	private BlockTranslator body;
    }
    private Input java = new Input();
    private Output cpp = new Output();
    
    public MethodDeclarationTranslator(CNode parent) {
	setParent(parent);
	setName(CppAstUtil.NodeName.MethodDeclaration);
    }
    public MethodDeclarationTranslator() {
	setName(CppAstUtil.NodeName.MethodDeclaration);
    }
   
    /* MethodDeclaration Members */
    // TODO: fill these out
    public List<Modifier> getModifiers() {
	return null;
    }
    public Type getReturnType() {
	return null;
    }
    public List<Modifier> getReturnTypeModifiers() {
	return null;
    }
    public String getMethodName() {
	return java.type.getName();
    }
    public List<FormalParameter> getFormalParameters() {
	return null;
    }   
    public ThrowsClause getThrowsClause() {
	return null;
    }   
    public BlockTranslator getMethodBody() {
	return null;
    }    
    
    /* TranslatorNode Members */
    public void initialize(Node n) {
    
	// Record scope name
    	String scopeName = n.getStringProperty(Constants.SCOPE);
	setScopeName(scopeName);
	
	// The MethodT class holds the method's name, return value, param values/identifiers, and exceptions
	java.type = Translator.resolveMethodType(this);
	
	// Get method body
	Node blockNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Block);
	if (blockNode != null) {
	    Block block = new StatementVisitor(this, scopeName).statementDispatch(blockNode);
	    cpp.body = block;
	}
	
	// Possible modifiers: public, protected, private, static, abstract, final 
	Node modifiersNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Modifiers);
	java.modifiers = JavaAstUtil.parseModifiers(modifiersNode);
	
	Translator.reportMethodModifiers(java.type, java.modifiers);
	
    }
    
    /* MethodDeclarationTranslator Members */
    public MethodT getMethodType() {
	return java.type;
    }
    public boolean isPrivate() {
	return (java.modifiers.indexOf(Modifier.PRIVATE) >= 0);
    }
    public boolean isStatic() {
	return (java.modifiers.indexOf(Modifier.STATIC) >= 0);
    }
    public void setMethodName(String name) {
	java.type = new MethodT(java.type.getResult(), name, java.type.getParameters(), false, java.type.getExceptions());
    }
}
