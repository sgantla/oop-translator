package oop.translator.tree;

import java.util.List;
import java.util.ArrayList;
import xtc.tree.Node;

class ClassBodyTranslator extends TranslatorNode 
    implements ClassBody {

    private class Output {
        List<DeclarationTranslator> declarations = new ArrayList<DeclarationTranslator>();
    }
    private Output cpp = new Output();

    private List<FieldDeclarationTranslator> fieldDeclarations = new ArrayList<FieldDeclarationTranslator>();
    
    private List<MethodDeclarationTranslator> privateMethodDeclarations = new ArrayList<MethodDeclarationTranslator();
    private List<MethodDeclarationTranslator> staticMethodDeclarations = new ArrayList<MethodDeclarationTranslator();
    private List<MethodDeclarationTranslator> virtualMethodDeclarations = new ArrayList<MethodDeclarationTranslator();
    private List<MethodData> virtualMethodTable;
    
    private List<ConstructorDeclarationTranslator> constructorDeclarations = new ArrayList<ConstructorDeclarationTranslator>();
    private List<InitializationField> initializationList = new ArrayList<InitializationField>();
    
    private InheritedData inheritedData = AstUtil.retrieveInheritedData(this);  // unimplemented   
    // assuming that the inherited data is deep copied, and will not be modified from an external class later.
       
    public InheritedData getInheritedData() {
        return inheritedData;
    }
    
    public List<DeclarationTranslator> getDeclarations() {
	
	    List<DeclarationTranslator> retList = new List<DeclarationTranslator>();
	    retList.addAll(fieldDeclarations);
	    retList.addAll(privateMethodDeclarations);
	    retList.addAll(staticMethodDeclarations);
	    retList.addAll(virtualMethodDeclarations);
	    retList.addAll(constructorDeclarations);
	    
	    return retList;
    }    
    public CppAstUtil.NodeName getNodeType () {
	    return CppAstUtil.NodeName.ClassBody;
    }
    public ClassBodyTranslator(TranslatorNode parent) {
	    super(parent);
    }
    
    public void initialize(Node n) {   
    	for (Object child : n) {
    	    if (child instanceof Node) {
		Node childNode = (Node) child;
		JavaAstUtil.NodeName childName = JavaAstUtil.NodeName.valueOf(childNode.getName());

		if (childName == JavaAstUtil.NodeName.FieldDeclaration) {
		    initializeFieldDeclaration(childNode);
		} else if (childName == JavaAstUtil.NodeName.MethodDeclaration) {
		    initializeMethodDeclaration(childNode);
		} else if (childName == JavaAstUtil.NodeName.ConstructorDeclaration) {
		    initializeConstructorDeclaration(childNode);
		}
	    }
    	}

	finishFieldInitialization();
	finishMethodInitialization();
	finishConstructorInitialization();
    }
    
    private void initializeFieldDeclaration(Node fieldDecNode) {
	List<Node> singleDeclarations = JavaAstUtil.splitFieldDeclarationByDeclarator(childNode); // unimplemented
	List<FieldDeclarationTranslator> parentFieldDeclarations = inheritedData.getFieldDeclarations();
	
	for (Node declaration : singleDeclarations) {
	    FieldDeclarationTranslator fd = new FieldDeclarationTranslator(this);
	    fd.initialize(declaration);
    
    	    String newDeclarator = fd.getDeclarator();
	    for (FieldDeclarationTranslator parentDeclaration : parentFieldDeclarations) {
		if (parentDeclaration.getDeclarator().equals(newDeclarator)) {
		    // do the name mangling
		}
	    }
	    
	    if (fd.hasExpression() && !fd.isStatic()) { 
		Expression expr = fd.removeExpression();
		initializationList.add(new InitializationField(fd, expr));
	    }
	    
	    fieldDeclarations.add(fd);
	}
    }
    
    private void initializeMethodDeclaration(Node methodDecNode) {
	MethodDeclarationTranslator md = new MethodDeclarationTranslator(this);
	methodDeclarations.add(md);
	md.initialize(childNode);
	
	String methodName = md.getMethodName();
	Type returnType = md.getReturnType();
	List<Type> paramTypes = md.getParamTypes();
	ClassReference class = AstUtil.getClassReference(this); // unimplemented
	
	MethodData newMethod = new MethodData(methodName, returnType, paramTypes, class); // unimplemented

	List<MethodData> staticMethodTable = inheritedData.getStaticMethodTable(); // unimplemented
	List<MethodData> virtualMethodTable = inheritedData.getVirtualMethodTable(); // unimplemented
	
	if (md.isPrivateMethod()) {
	    privateMethodDeclarations.add(md);
	} else if (md.isStatic()) {
	    int i = inheritedData.indexOf(md);
	    if (i >= 0) {
		staticMethodTable.set(i, newMethod);
	    } else {
		staticMethodTable.add(newMethod);
	    }
	    staticMethodDeclarations.add(md);
	} else {
	    int i = virtualMethodTable.indexOf(newMethod);
	    if (i >= 0) {
		virtualMethodTable.set(i, newMethod);
	    } else {
		virtualMethodTable.add(newMethod);
	    }
	    virtualMethodDeclarations.add(md);
	}
    }
    
    private void initializeConstructorDeclaration(Node constructorDecNode) {
	ConstructorDeclarationTranslator cd = new ConstructorDeclarationTranslator(this);
	constructorDeclarations.add(cd);
	cd.initialize(childNode);
    }
    
    private void addStaticMethodReference(MethodData method) {
	staticMethodDeclarations.add(MethodDeclarationTranslator.newStaticReference(method)); // unimplemented
    }
    
    private void finishFieldInitialization() {
	List<FieldDeclarationTranslator> parentFieldDeclarations = inheritedData.fieldDeclarations();
	parentFieldDeclarations.addAll(fieldDeclarations);
	fieldDeclarations = new ArrayList<FieldDeclarationTranslator>().addAll(parentFieldDeclarations);
    }
    
    private void finishMethodInitialization() {
	List<MethodData> staticMethodTable = inheritedData.getStaticMethodTable(); 
	Set<String> staticMethodNames = new HashSet<String>();
	
	for (MethodDeclarationTranslator methodDec : staticMethodDeclarations) {
	    staticMethodNames.add(methodDec.getMethodName());
	}
	for (MethodData method : staticMethodTable) {
	    if (!staticMethodNames.contains(method.getMethodName()) {
		addStaticMethodReference(method);
	    }
	}
	
	virtualMethodTable = inheritedData.getVirtualMethodTable(); 
    }
    
    private void finishConstructorInitialization() {
	List<InitializationField> parentInitializationList = inheritedData.getInitializationList();
	parentInitializationList.addAll(initializationList);
	initializationList = new ArrayList<Initialization>().addAll(parentInitializationList);

	ConstructorDeclarationTranslator constructor;
	if (constructorDeclarations.size() > 0) {
	    constructor = constructorDeclarations.get(0);
	} else {
	    constructor = ConstructorDeclarationTranslator.newEmptyConstructor(this); // unimplemented
	}
	
	List<ConstructorData> constructorTable = inheritedData.getConstructorTable(); 
	// assume one parent constructor
	ConstructorData parentConstructor = constructorTable.get(0);
	BlockTranslator parentConstructorBody = constructor.getBodyTranslator();
	
	constructor.setInitializationList(initializationList); // unimplemented
	constructor.prependStatementBlock(parentConstructorBody); // unimplemented
	
	String constructorName = constructor.getName();
	List<Type> parameterTypes = constructor.getParamTypes();
	BlockTranslator body = constructor.getBodyTranslator();
	
	ConstructorData constructorData = new ConstructorData(constructorName, parameterTypes, body);
	constructorTable.set(0, constructor);
    }
}