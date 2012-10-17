package oop.translator.tree;

abstract class DeclarationTranslator extends TranslatorNode 
    implements Declaration {
    
    public DeclarationTranslator(TranslatorNode parent) {
	super(parent);
    }
}