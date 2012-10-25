package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.tree.*;
import oop.tree.expressions.*;
import oop.tree.statements.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class FieldDeclarationTranslator extends FieldDeclaration {

    private class Input {
	Type type;
	List<Modifiers> modifiers = new ArrayList<Modifiers>();
    }
    private class Output {
	List<Modifiers> modifiers = new ArrayList<Modifiers>();
	Type type;
	String declarator;
	Expression initializer;
    }
    private Input java = new Input();
    private Output cpp = new Output();

    public FieldDeclarationTranslator (CNode parent) {
	setParent(parent);
	setName(CppAstUtil.NodeName.FieldDeclaration);
    }
    public FieldDeclarationTranslator () {
	setName(CppAstUtil.NodeName.FieldDeclaration);
    }
    
    /* FieldDeclaration Members */
    public String getDeclarator() {
	return cpp.declarator;
    }
    public Type getType() {
	return cpp.type;
    }
    public Expression getInitializer () {
	return cpp.initializer;
    }
    public List<Modifiers> getModifiers() {
	return cpp.modifiers;
    }
  
    /* TranslatorNode Members */ 
    public void initialize(Node n) {
    
	Node declaratorsNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Declarators);
	Node declaratorNode = JavaAstUtil.getChildByName(declaratorsNode, JavaAstUtil.NodeName.Declarator);
	cpp.declarator = declaratorNode.getString(0);
	
	// Resolve the declarator to a Type for the field
	java.type = Translator.resolveDeclaratorType(cpp.declarator, this);
	
	// Possible modifiers: public, protected, private, static, final
	Node modifiersNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Modifiers);
	//java.modifiers = JavaAstUtil.parseModifiers(modifiersNode);
	
	Translator.reportFieldModifiersAndLocation(java.type, java.modifiers, n.getLocation());
	
	// Get initialization expression
	Node expressionNode = declaratorNode.getNode(2);
	if (expressionNode != null) {
	    cpp.initializer = new ExpressionVisitor(getParent().getScopeName()).expressionDispatch(expressionNode);
	}
    }
    
    /* FieldDeclaratorTranslator Members */
    public Expression removeInitializer () {
	Expression expr = cpp.initializer;
	cpp.initializer = null;
	return expr;
    }
    public void setDeclarator(String declarator) {
	cpp.declarator = declarator;
    }
    public boolean hasInitializer () {
	return (cpp.initializer != null);
    }
    public boolean isStatic() {
	return (cpp.modifiers.indexOf(Modifiers.STATIC) >= 0);
    }   
}
