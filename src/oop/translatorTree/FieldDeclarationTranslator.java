package oop.translator.tree;
import java.util.*;
import xtc.tree.Node;

class FieldDeclarationTranslator extends DeclarationTranslator
    implements FieldDeclaration {

    private class Input {
	Type type;
    }
    private class Output {
	List<Modifier> modifiers = new ArrayList<Modifier>();
	TypeTranslator type;
	String declarator;
	ExpressionTranslator assignment;
    }
    
    private Input java = new Input();
    private Output cpp = new Output();

    public String getDeclarator() {
	return cpp.declarator;
    }
    
    public Type getType() {
	return cpp.Type;
    }
    
    public ExpressionTranslator getExpression () {
	return cpp.assignment;
    }
    
    public ExpressionTranslator removeExpression () {
	ExpressionTranslator expr = cpp.assignnment;
	cpp.assignment = null;
	return expr;
    }
    
    public boolean hasExpression () {
	return (cpp.assignment != null);
    }
    
    public boolean isStatic() {
	for (Modifier m : cpp.modifiers) {
	    if (m == Modifier.STATIC) {
		return true;
	    }
	}
    }
    
    public CppAstUtil.NodeName getNodeType() {
	return CppAstUtil.NodeName.FieldDeclaration;
    }
    
    public FieldDeclarationTranslator (TranslatorNode parent) {
	super(parent);
    }
    
    public void initialize(Node n) {
	
	Node modifiersNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Modifiers);
	if (modifiersNode != null) {
	    for (Node modifierNode : JavaAstUtil.getChildrenByName(modifiersNode, JavaAstUtil.NodeName.Modifier)) {
		if (JavaAstUtil.extractString(modifierNode).equals("static")) {
		    cpp.modifiers.add(MODIFIER.STATIC);
		}
	    }
	}
	
	Node typeNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Type);
	TypeTranslator type = new TypeTranslator(this);
	type.initialize(typeNode);
	
	Node declaratorsNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Declarators);
	Node declaratorNode = JavaAstUtil.getChildByName(declaratorsNode, JavaAstUtil.NodeName.Declarator);
	
	int declaratorDimensions;
	if (declaratorNode != null) {
	    cpp.declarator = declaratorNode.getString(0);
	    
	    declaratorDimensions = JavaAstUtil.getDimensionNumber(declaratorNode.getNode(1)); // unimplemented
	    
	    Node expressionNode = declaratorNode.getNode(2);
	    if (expressionNode != null) {
		cpp.assignment = new ExpressionTranslator(this); // unimplemented
		cpp.assignment.initialize(expressionNode);
	    }
	}
	
	type.setDimensions(type.getDimensions() + declaratorDimensions);
	
	cpp.type = type;
    }
}