package oop.tree.statements;

public class DeclarationOrStatement {

    Declaration declaration;
    Statement statement;

    public DeclarationOrStatement(Declaration declaration) {
        this.declaration = declaration;
        statement = null;
    }

    public DeclarationOrStatement(Statement statement) {
        this.declaration = null;
        this.statement = statement;
    }

    public DeclarationOrStatement(Declaration declaration, Statement statement) {
        this.declaration = declaration;
        this.statement = statement;
    }
}

