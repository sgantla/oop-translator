package oop.translator;
import java.util.List;

interface CppAstNode {
    CppAstUtil.NodeName getNodeType();
}

interface ClassDeclaration extends CppAstNode {
    List<Modifier> getModifiers();
    String getClassName();
    List<String> getExtensions();
    ClassBody getClassBody(); 
}

interface CompilationUnit extends CppAstNode {
    List<NamespaceDeclaration> getNameSpaceDeclarations();
    List<UsingDeclaration> getUsingDeclarations();
    ClassDeclaration getClassDeclaration();
}

interface ClassBody extends CppAstNode {
    List<Declaration> getDeclarations();
}

interface Declaration {
    
}
interface FieldDeclaration extends Declaration {
    String getDeclarator();
    Type getType();
    Expression getExpression();
}

interface MethodDeclaration extends Declaration {
    List<Modifier> getModifiers();
    Type getReturnType();
    List<Modifier> getReturnTypeModifiers();
    String getMethodName();
    List<FormalParameter> getFormalParameters();
    ThrowsClause getThrowsClause();
    Block getMethodBody();
}

