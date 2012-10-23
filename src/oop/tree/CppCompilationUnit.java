package oop.tree;

public class CppCompilationUnit {
    
   /*
	To do: design structure
	
	Should contain any "using" declarations, namespaces, and the ClassDeclaration node for this class.
	Analogous to the CompilationUnit GNode that is the root of the Java AST.

   */
	
	/* Fields */
	private final NamespaceDeclaration namespace; 
	//private final List<UsingDeclaration> usingList; // not supported
	private final ClassDeclaration classDeclaration; 
	
	/* Constructors */
	public CppCompilationUnit() {	
	}
	
	public NamespaceDeclaration getNamespace() {
		return namespace; 
	}
		
	public ClassDeclaration getClassDeclaration() {
		return classDeclaration; 
	}
}
