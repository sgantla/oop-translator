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

public class ConstructorDeclarationTranslator extends DeclarationTranslator
    implements ConstructorDeclaration {
    
    public ConstructorDeclarationTranslator(TranslatorNode parent) {
	super(parent);
    }
   
    /* ConstructorDeclaration Members */
    public List<Modifier> getModifiers() {return null;}
    public String getName() {return null;}
    public List<FormalParameter> getFormalParameters() {return null;}
    public List<InitializationListEntry> getInitializations() {return null;}
    public BlockTranslator getConstructorBody() {return null;}
    
    /* CppAstNode Members */
    public CppAstUtil.NodeName getNodeType() {
	return CppAstUtil.NodeName.ConstructorDeclaration;
    }
    
    /* TranslatorNode Members */
    public void initialize(Node n) {}
    
    /* ConstructorDeclarationTranslator Members */
    public void setInitializationList(List<InitializationField> list) {}
    public void prependStatementBlock(BlockTranslator block) {}
    public MethodT getMethodType() {return null;}
    public static ConstructorDeclarationTranslator newEmptyConstructor(ClassT classType) {return null;}
    
}


