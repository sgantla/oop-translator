package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class FieldDeclarationTranslator extends DeclarationTranslator
    implements FieldDeclaration {

    private class Input {
	Type type;
    }
    private class Output {
	List<Modifier> modifiers = new ArrayList<Modifier>();
	Type type;
	String declarator;
	ExpressionTranslator assignment;
    }
    private Input java = new Input();
    private Output cpp = new Output();

    public FieldDeclarationTranslator (TranslatorNode parent) {
	super(parent);
    }
    
    /* FieldDeclaration Members */
    public String getDeclarator() {
	return cpp.declarator;
    }
    public Type getType() {
	return null; // Not implemented. Have to decide how to translate the java Type into cpp.
    }
    public ExpressionTranslator getExpression () {
	return cpp.assignment;
    }
    
    /* CppAstNode Members */
    public CppAstUtil.NodeName getNodeType() {
	return CppAstUtil.NodeName.FieldDeclaration;
    }
    
    /* TranslatorNode Members */ 
    public void initialize(Node n) {
    
	// Record if field is static 
	Node modifiersNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Modifiers);
	if (modifiersNode != null) {
	    for (Node modifierNode : JavaAstUtil.getChildrenByName(modifiersNode, JavaAstUtil.NodeName.Modifier)) {
		if (JavaAstUtil.extractString(modifierNode).equals("static")) {
		    cpp.modifiers.add(Modifier.STATIC);
		}
	    }
	}
	
	Node declaratorsNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Declarators);
	Node declaratorNode = JavaAstUtil.getChildByName(declaratorsNode, JavaAstUtil.NodeName.Declarator);
	cpp.declarator = declaratorNode.getString(0);
	// Resolve the declarator to a Type for the field
	java.type = Translator.resolveDeclaratorType(cpp.declarator, this);
	
	Node expressionNode = declaratorNode.getNode(2);
	if (expressionNode != null) {
	    /* Create child based on expression. Need to implement a general way to handle an expression child from any node */
	}
    }
    
    /* FieldDeclaratorTranslator Members */
    public ExpressionTranslator removeExpression () {
	ExpressionTranslator expr = cpp.assignment;
	cpp.assignment = null;
	return expr;
    }
    public void setDeclarator(String declarator) {
	cpp.declarator = declarator;
    }
    public boolean hasExpression () {
	return (cpp.assignment != null);
    }
    public boolean isStatic() {
	return (cpp.modifiers.indexOf(Modifier.STATIC) >= 0);
    }   
}