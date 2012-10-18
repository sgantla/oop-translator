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

public class ConstructorDeclarationTranslator extends DeclarationTranslator
    implements ConstructorDeclaration {
    
    private class Input {
	private MethodT type;
    }
    private class Output {
	private BlockTranslator body;
    }
    private Input java = new Input();
    private Output cpp = new Output();
    
    public ConstructorDeclarationTranslator(TranslatorNode parent) {
	super(parent);
    }
    /* ConstructorDeclaration Members */
    public List<Modifier> getModifiers() {return null;}
    public String getName() {return null;}
    public List<FormalParameter> getFormalParameters() {return null;}
    public List<InitializationListEntry> getInitializations() {return null;}
    public BlockTranslator getConstructorBody() {return cpp.body;}
    
    /* CppAstNode Members */
    public CppAstUtil.NodeName getNodeType() {
	return CppAstUtil.NodeName.ConstructorDeclaration;
    }
    
    /* TranslatorNode Members */
    public void initialize(Node n) {
	// The AST has been simplified so that constructors look like methods without a return value
	
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
	
    }
    
    /* ConstructorDeclarationTranslator Members */
    public void setInitializationList(List<InitializationField> list) {}
    public void prependStatementBlock(BlockTranslator block) {}
    public MethodT getMethodType() {return null;}
    public static ConstructorDeclarationTranslator newEmptyConstructor(ClassT classType, TranslatorNode parent) {
   
	//MethodT method = MethodT(null, classType.getName(), new List<Type>(), false, new List<Type>());
	return null; // Not implemented
    }
    
}


