package oop.translator.tree;
import java.util.*;
import xtc.tree.Node;

class MethodDeclarationTranslator extends DeclarationTranslator
    implements MethodDeclaration {

    private class Output {
	private List<Modifier> modifiers;
	private String methodName;
	private TypeTranslator returnType;
	private List<FormalParameterTranslator> formalParameters;
	private ThrowsClauseTranslator throwsClause;
	private BlockTranslator body;
    }
    
    private Input java = new Input();
    private Output cpp = new Output();
    
    public void initialize(Node n) {
	
    }
    public String getMethodName() {
    
    }
    
    public List<Modifier> getModifiers() {
	
    }
    
    public TypeTranslator getReturnType() {
	
    }
    
    public List<TypeTranslator> getReturnTypeModifiers() {

    }
    
    public List<FormalParameterTranslator> getFormalParameters() {
	
    }
    
    public List<TypeTranslator> getParamTypes() {
	
    }
    
    public CppAstUtil.NodeName getNodeType() {
	
    }
    
    public ThrowsClauseTranslator getThrowsClause() {
	
    }
    
    public BlockTranslator getMethodBody() {
    
    }
    
    public MethodDeclarationTranslator(TranslatorNode parent) {
	super(parent);
    }
}