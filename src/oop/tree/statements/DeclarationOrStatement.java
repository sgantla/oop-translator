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
public class DeclarationOrStatement {

    public Statement statement; 

    public DeclarationOrStatement(Statement statement) {
        this.statement = statement;
    }

    public DeclarationOrStatement(Declaration declaration, Statement statement) {
        this.statement = statement;
    }
}

