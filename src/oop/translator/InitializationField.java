package oop.translator;
class InitializationField {
    private FieldDeclarationTranslator fieldDeclaration;
    private ExpressionTranslator expression;
    
    public InitializationField(FieldDeclarationTranslator fieldDeclaration, ExpressionTranslator expression) {
	this.fieldDeclaration = fieldDeclaration;
	this.expression = expression;
    }
    
    public FieldDeclarationTranslator getFieldDeclaration() {
	return fieldDeclaration;
    }
    
    public ExpressionTranslator getExpression() {
	return expression;
    }
}
