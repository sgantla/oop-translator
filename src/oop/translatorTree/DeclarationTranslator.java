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

public abstract class DeclarationTranslator extends TranslatorNode 
    implements Declaration {
    
    public DeclarationTranslator(TranslatorNode parent) {
	super(parent);
    }
}

