package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;
import xtc.Constants;

import java.util.*;
import java.io.*;

public class MethodDeclarationTranslator extends DeclarationTranslator
    implements MethodDeclaration {

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
    
    public MethodDeclarationTranslator(TranslatorNode parent) {
	super(parent);
    }
   
    /* MethodDeclaration Members */
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
	return null;
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

    /* CppAstNode Members */
    public CppAstUtil.NodeName getNodeType() {
	return CppAstUtil.NodeName.MethodDeclaration;
    }
    
    /* TranslatorNode Members */
    public void initialize(Node n) {
    
	// Record scope name
    	String scopeName = n.getStringProperty(Constants.SCOPE);
	setQualifiedScopeName(scopeName);
	
	// The MethodT class holds the method's name, return value, param values/identifiers, and exceptions
	MethodT methodType = Translator.resolveMethodType(this);
	java.type = methodType;
	
	// Get method body
	Node blockNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Block);
	if (blockNode != null) {
	    BlockTranslator block = new BlockTranslator(this);
	    block.initialize(blockNode);
	    cpp.body = block;
	}
	
	// Record if method is private or static. 
	Node modifiersNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Modifiers);
	if (modifiersNode != null) {
	    for (Node modifierNode : JavaAstUtil.getChildrenByName(modifiersNode, JavaAstUtil.NodeName.Modifier)) {
		String mod = JavaAstUtil.extractString(modifierNode);
		if (mod.equals("static")) {
		    java.modifiers.add(Modifier.STATIC);
		} else if (mod.equals("private")) {
		    java.modifiers.add(Modifier.PRIVATE);
		} 
	    }
	}
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
    }
}