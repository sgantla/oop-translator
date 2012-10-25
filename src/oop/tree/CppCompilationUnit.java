package oop.tree;

public class CppCompilationUnit {
    
   /*
	To do: design structure
	
	Should contain any "using" declarations, namespaces, and the ClassDeclaration node for this class.
	Analogous to the CompilationUnit GNode that is the root of the Java AST.

   */
	
	/* Fields */
	public final NamespaceDeclaration namespace; 
	//private final List<UsingDeclaration> usingList; // not supported
	public final ClassDeclaration classDeclaration; 
	
	/* Constructors */
	public CppCompilationUnit() {	
            namespace = new NamespaceDeclaration();
            classDeclaration = new ClassDeclaration();
	}
	
	public NamespaceDeclaration getNamespace() {
		return namespace; 
	}
		
	public ClassDeclaration getClassDeclaration() {
		return classDeclaration; 
	}
}
