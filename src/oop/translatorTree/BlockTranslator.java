package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class BlockTranslator extends StatementTranslator 
    implements Block {
	
    public List<Statement> getStatements() {return null;}
    public void initialize(Node n){}
    
    public CppAstUtil.NodeName getNodeType() {
	return CppAstUtil.NodeName.Block;
    }

    public BlockTranslator(TranslatorNode parent) {
	super(parent);
    }
}