package oop.translator.tree;

import java.util.List;
import java.util.ArrayList;
import xtc.tree.Node;

class CompilationUnitTranslator extends TranslatorNode 
    implements CompilationUnit {

    private class Input {
        String packageDec;      
        List<String> imports = new ArrayList<String>(); 
    }
    private class Output {
        List<NamespaceDeclaration> namespaces = new ArrayList<NamespaceDeclaration>();
        List<UsingDeclaration> usingDeclarations = new ArrayList<UsingDeclaration>();
        ClassDeclarationTranslator classDeclaration;
    }
    private Input java = new Input();
    private Output cpp = new Output();

    public List<NamespaceDeclaration> getNameSpaceDeclarations() {
        return cpp.namespaces;
    }
    public List<UsingDeclaration> getUsingDeclarations() {
        return cpp.usingDeclarations;
    }
    public ClassDeclaration getClassDeclaration() {
        return cpp.classDeclaration;
    }

    public CompilationUnitTranslator(TranslatorNode parent) {
        super(parent);
    }

    public CppAstUtil.NodeName getNodeType () {
        return CppAstUtil.NodeName.CompilationUnit;
    }
    
    public void initialize(Node n) {
        Node packageNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.PackageDeclaration);
        if (packageNode != null) {
            java.packageDec = JavaAstUtil.extractString(packageNode);
        }
    
        for (Node importNode : JavaAstUtil.getChildrenByName(n, JavaAstUtil.NodeName.ImportDeclaration)) {
            java.imports.add(JavaAstUtil.extractString(importNode));
        }

        Node classDecNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.ClassDeclaration);
        cpp.classDeclaration = new ClassDeclarationTranslator(this);
        cpp.classDeclaration.initialize(classDecNode);
    }
}