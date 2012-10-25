package oop.tree.statements;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.statements.*;
import oop.tree.*;

//import oop.tree.*; 

import xtc.tree.Location;
import xtc.tree.Locatable;
import xtc.type.*;
import xtc.tree.*; 
public class DeclarationOrStatement extends Statement {

    public Declaration declaration;
    public Statement statement;

    public DeclarationOrStatement(Declaration declaration) {
        this.declaration = declaration;
        statement = null;
    }

    public DeclarationOrStatement(Statement statement) {
        this.statement = statement;
        this.declaration = null;
    }

    public DeclarationOrStatement(Declaration declaration, Statement statement) {
        this.statement = statement;
        this.declaration = declaration;
    }
}

