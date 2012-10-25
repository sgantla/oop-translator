package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.tree.*;
import oop.tree.expressions.*;
import oop.tree.statements.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;
import xtc.Constants;

import java.util.*;
import java.io.*;

public class ConstructorDeclarationTranslator extends ConstructorDeclaration {
    
    private class Input {
	private MethodT type;
	private List<Modifiers> modifiers;
    }
    private class Output {
	private Block body;
    }
    private Input java = new Input();
    private Output cpp = new Output();
    
    public ConstructorDeclarationTranslator(CNode parent) {
	setParent(parent);
	setName(CppAstUtil.NodeName.ConstructorDeclaration);
    }
    public ConstructorDeclarationTranslator() {
	setName(CppAstUtil.NodeName.ConstructorDeclaration);
    }
    
    /* TranslatorNode Members */
    public void initialize(Node n) {
	// The AST has been simplified so that constructors look like methods without a return value
	
	// Record scope name
    	String scopeName = n.getStringProperty(Constants.SCOPE);
	setScopeName(scopeName);
	
	// The MethodT class holds the method's name, return value, param values/identifiers, and exceptions
	java.type = Translator.resolveMethodType(this);
	
	// Get method body
	Node blockNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Block);
	if (blockNode != null) {
	    Block block = (Block) new StatementVisitor(this, scopeName).statementDispatch(blockNode);
	    cpp.body = block;
	}
	
	// Possible modifiers: public, protected, private
	Node modifiersNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Modifiers);
	java.modifiers = new ArrayList<Modifiers>(); //JavaAstUtil.parseModifiers(modifiersNode);
	
	Translator.reportMethodModifiers(java.type, java.modifiers);
    }
}


