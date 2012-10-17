package oop.translator.tree;

import java.util.List;
import java.util.ArrayList;
import xtc.tree.Node;

class ClassDeclarationTranslator extends DeclarationTranslator 
    implements ClassDeclaration {

    private class Input { 
	List<String> modifiers = new ArrayList<String>();	
	String className;
	String extension;
    }
    
    private class Output {
	List<Modifier> modifiers = new ArrayList<Modifier>();	
	List<String> extensions = new ArrayList<String>();	
	String className;
	ClassBodyTranslator classBody;
    }

    private Input java = new Input();
    private Output cpp = new Output();

    public List<Modifier> getModifiers() {
	return cpp.modifiers;
    }

    public String getClassName() {
	return cpp.className;
    }

    public List<String> getExtensions() {
	return cpp.extensions;
    }

    public ClassBody getClassBody() {
	return cpp.classBody;
    }

    public ClassDeclarationTranslator(TranslatorNode parent) {
	super(parent);
    }
    
    public CppAstUtil.NodeName getNodeType () {
	return CppAstUtil.NodeName.ClassDeclaration;
    }

    public void initialize(Node n) {
	Node modifiersNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Modifiers);
	if (modifiersNode != null) {
	    for (Node modifierNode : JavaAstUtil.getChildrenByName(modifiersNode, JavaAstUtil.NodeName.Modifier)) {
		java.modifiers.add(JavaAstUtil.extractString(modifierNode));
	    }
	}

	Node extensionNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Extension);
	if (extensionNode != null) {
	    java.extension = JavaAstUtil.extractString(extensionNode);
	}

	java.className = n.getString(1);
	cpp.className = java.className;
	cpp.modifiers.add(Modifier.PUBLIC);

	Node classBodyNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.ClassBody);
	if (classBodyNode != null) {
	    cpp.classBody = new ClassBodyTranslator(this);
	    cpp.classBody.initialize(classBodyNode);
	}
    }
}