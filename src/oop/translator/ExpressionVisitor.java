package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.expressions.*;
import oop.tree.statements.*;
import oop.tree.*;

import xtc.type.*;
import xtc.tree.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

// TODO: Need a visit for Modifier
public class ExpressionVisitor extends Visitor
{

    public AdditiveExpression visitAdditiveExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = expressionDispatch(leftNode);
        Expression rightExp = expressionDispatch(rightNode);

        return new AdditiveExpression(leftExp, operator, rightExp);
    }

    public ArrayInitializer visitArrayInitializer(Node n) {

        //Not sure if legit
        Node expressionsNode = n.getNode(0);
        Node modifiersNode = n.getNode(1);

        List<Expression> expressions = new ArrayList<Expression>();
        for(Object eNode : expressionsNode) {
            if(eNode instanceof Node) {
                Expression expression = expressionDispatch((Node)eNode);
                expressions.add(expression);
            }
        }

        List<Modifiers> modifiers = new ArrayList<Modifiers>();
        for(Object mNode : modifiersNode) {
            if(mNode instanceof Node) {
                Modifiers modifier = new GeneralVisitor().visitModifiers((Node)mNode);
                modifiers.add(modifier);
            }
        }

        return new ArrayInitializer(expressions, modifiers);
    }

    public BasicCastExpression visitBasicCastExpression(Node n) {

        Node typeNameNode = n.getNode(0);
        String dimensionString = n.getString(1);
        Node expressionNode = n.getNode(2);

        TypeName typeName = null;// expressionDispatch(typeNameNode);

        int dimension = 0;
        if((dimensionString != null) && (!dimensionString.equals(""))) {
            dimension = Integer.parseInt(dimensionString); 
        }
        
        Expression expression = expressionDispatch(expressionNode);

        return new BasicCastExpression(typeName, dimension, expression);
    }

    public BitwiseAndExpression visitBitwiseAndExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = expressionDispatch(leftNode);
        Expression rightExp = expressionDispatch(rightNode);

        return new BitwiseAndExpression(leftExp, operator, rightExp);
    }

    public BitwiseNegationExpression visitBitwiseNegationExpression(Node n) {

        Node node = n.getNode(0);
        String operator = n.getString(1);

        Expression expression = expressionDispatch(node);

        return new BitwiseNegationExpression(expression, operator);
    }

    public BitwiseOrExpression visitBitwiseOrExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = expressionDispatch(leftNode);
        Expression rightExp = expressionDispatch(rightNode);

        return new BitwiseOrExpression(leftExp, operator, rightExp);
    }

    public BitwiseXorExpression visitBitwiseXorExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = expressionDispatch(leftNode);
        Expression rightExp = expressionDispatch(rightNode);

        return new BitwiseXorExpression(leftExp, operator, rightExp);
    }

    public BooleanLiteral visitBooleanLiteral(Node n) {

        String value = n.getString(0);

        return new BooleanLiteral(value);
    }

    public CallExpression visitCallExpression(Node n) {
        
        Node expressionNode = n.getNode(0);
        Node typeArgumentsNode = n.getNode(1);
        String name = n.getString(2);
        Node argsNode = n.getNode(3);

        Expression expression = null;
        if (expressionNode != null) {
            expression = expressionDispatch(expressionNode); }

        List<Type> typeArguments = null;
        if (typeArgumentsNode != null) {
            for(Object tNode : typeArgumentsNode) {
                if(tNode instanceof Node) {
                    Type type = new GeneralVisitor().visitType((Node)tNode);
                    typeArguments.add(type);
                }
            }
        }

        // this is an array of expressions, how to handle??
        // this can also be empty
        List<Expression> arguments = new ArrayList<Expression>();
        if(argsNode != null) {
            for(Object eNode : argsNode) {
                if(eNode instanceof Node) {
                    Expression exp = expressionDispatch((Node)eNode);
                    arguments.add(exp);
                }
            }
        }

        return new CallExpression(expression, typeArguments, name, arguments);
    }

    public CastExpression visitCastExpression(Node n) {

        Node typeNode = n.getNode(0);
        Node expressionNode = n.getNode(1);

        Expression expression = expressionDispatch(expressionNode);
        Type type = new GeneralVisitor().visitType(typeNode);

        return new CastExpression(type, expression);
    }

    public CharacterLiteral visitCharacterLiteral(Node n) {

        String value = n.getString(0);

        return new CharacterLiteral(value);
    }
    
    public ClassLiteralExpression visitClassLiteralExpression(Node n) {

        Node node = n.getNode(0);

        Type type = new GeneralVisitor().visitType(node);
        return new ClassLiteralExpression(type);
    }
    
    public ConditionalExpression visitConditionalExpression(Node n) {
        Node expressionNode1 = n.getNode(0);
        Node expressionNode2 = n.getNode(1);
        Node expressionNode3 = n.getNode(2);

        Expression expression1 = expressionDispatch(expressionNode1);
        Expression expression2 = expressionDispatch(expressionNode2);
        Expression expression3 = expressionDispatch(expressionNode3);

        return new ConditionalExpression(expression1, expression2, expression3);
    }

    public EqualityExpression visitEqualityExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);
        
        Expression leftExpression = expressionDispatch(leftNode);
        Expression rightExpression = expressionDispatch(rightNode);

        return new EqualityExpression(leftExpression, operator, rightExpression);
    }

    public FloatingPointLiteral visitFloatingPointLiteral(Node n) {

        String value = n.getString(0);

        return new FloatingPointLiteral(value);
    }

    public InstanceOfExpression visitInstanceOfExpression(Node n) {

        Node expressionNode = n.getNode(0);
        Node typeNode = n.getNode(1);

        Expression expression = expressionDispatch(expressionNode);
        Type type = new GeneralVisitor().visitType(typeNode);

        return new InstanceOfExpression(expression, type);
    }

    public IntegerLiteral visitIntegerLiteral(Node n) {
        String value = n.getString(0);

        return new IntegerLiteral(value);
    }

    public LogicalAndExpression visitLogicalAndExpression(Node n) {
        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExpression = expressionDispatch(leftNode);
        Expression rightExpression = expressionDispatch(rightNode);

        return new LogicalAndExpression(leftExpression, operator, rightExpression);
    }

    public LogicalNegationExpression visitLogicalNegationExpression(Node n) {
        Node expressionNode = n.getNode(0);
        String operator = n.getString(1);

        Expression expression = expressionDispatch(expressionNode);

        return new LogicalNegationExpression(expression, operator);
    }

    public LogicalOrExpression visitLogicalOrExpression(Node n) {
        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExpression = expressionDispatch(leftNode);
        Expression rightExpression = expressionDispatch(rightNode);

        return new LogicalOrExpression(leftExpression, operator, rightExpression);
    }

    public MultiplicativeExpression visitMultiplicationExpression(Node n) {
        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExpression = expressionDispatch(leftNode);
        Expression rightExpression = expressionDispatch(rightNode);

        return new MultiplicativeExpression(leftExpression, operator, rightExpression);
    }

    public NewArrayExpression visitNewArrayExpression(Node n) {
        Node typeNameNode = n.getNode(0);
        Node concreteDimensionsNode = n.getNode(1);
        String dimensionString = n.getString(2);
        Node expressionNode = n.getNode(3);
        
        TypeName typeName = null; // expressionDispatch(typeNameNode);

        List<Expression> concreteDimensions = null;
        if(concreteDimensionsNode != null) {
            concreteDimensions = new ArrayList<Expression>();
            for(Object eNode : concreteDimensionsNode) {
                if(eNode instanceof Node) {
                    Expression exp = expressionDispatch((Node)eNode);
                    concreteDimensions.add(exp);
                }
            }
        }

        int dimension = 0;
        if(dimensionString != null) {
            dimension = Integer.parseInt(dimensionString);  }

        Expression expression = null;
        if(expressionNode != null) {
            expression = expressionDispatch(expressionNode); }

        return new NewArrayExpression(typeName, concreteDimensions, dimension, expression);
    }

    
    // Do we need to mangle this puppy?
    public NewClassExpression visitNewClassExpression(Node n) {
        Node expressionNode = n.getNode(0);
        Node typeArgumentNode = n.getNode(1);
        Node typeNameNode = n.getNode(2);
        Node argumentsNode = n.getNode(3);
        Node classBodyNode = n.getNode(4);

        Expression expression = null;
        if(expressionNode != null) {
            expression = expressionDispatch(expressionNode); }

        TypeArgument typeArgument = null;
        if(typeArgumentNode != null) {
            typeArgument =  null; }//expressionDispatch(typeArgumentNode); }

        TypeName typeName = null;// visitTypeName(typeNameNode);

        List<Expression> arguments = new ArrayList<Expression>();
        for(Object aNode : argumentsNode) {
            if(aNode instanceof Node) {
                Expression argument = expressionDispatch((Node)aNode);
                arguments.add(argument);
            }
        }

        ClassBody classBody = null;
        if(classBodyNode != null) {
            classBody = new GeneralVisitor().visitClassBody(classBodyNode); }

        return new NewClassExpression(expression, typeArgument, typeName, arguments, classBody);
    }

    public NullLiteral visitNullLiteral(Node n) {
        return new NullLiteral();
    }

    public PostfixExpression visitPostFixExpression(Node n) {
        Node expressionNode = n.getNode(0);
        String operator = n.getString(1);

        Expression expression = expressionDispatch(expressionNode);

        return new PostfixExpression(expression, operator);
    }

    public PrimaryIdentifier visitPrimaryIdentifier(Node n) {
        String name = n.getString(1);
        
        return new PrimaryIdentifier(name);
    }

    public RelationalExpression visitRelationalExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);
        
        Expression leftExp = expressionDispatch(leftNode);
        Expression rightExp = expressionDispatch(rightNode);

        return new RelationalExpression(leftExp, operator, rightExp);
    }

    public SelectionExpression visitSelectionExpression(Node n) {
        Node expressionNode = n.getNode(0);
        String operator = n.getString(1);

        Expression expression = expressionDispatch(expressionNode);

        return new SelectionExpression(expression, operator);
    }

    public ShiftExpression visitShiftExpression(Node n) {
        Node leftExpressionNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightExpressionNode = n.getNode(0); // Shouldn't this be n.getNode(2);?

        Expression leftExpression = expressionDispatch(leftExpressionNode);
        Expression rightExpression = expressionDispatch(rightExpressionNode);

        return new ShiftExpression(leftExpression, operator, rightExpression);
    }

    public StringLiteral visitStringLiteral(Node n) {
        String value = n.getString(0);

        return new StringLiteral(value);
    }
    
    public SubscriptExpression visitSubscriptExpression(Node n) {
        Node leftExpressionNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightExpressionNode = n.getNode(0);

        Expression leftExpression = expressionDispatch(leftExpressionNode);
        Expression rightExpression = expressionDispatch(rightExpressionNode);

        return new SubscriptExpression(leftExpression, operator, rightExpression);
    }

    public SuperExpression visitSuperExpression(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = expressionDispatch(expressionNode);

        return new SuperExpression(expression);
    }
/*
    public TypeInstantiation visitTypeInstantiation(Node n) {
        String typeInstantiation = n.getString(0);
        Node typeArgumentsNode = n.getNode(1);

        TypeArgument typeArguments = null;
        if (typeArgumentsNode != null)
            typeArguments = expressionDispatch(typeArgumentsNode);

        return new TypeInstantiation(typeInstantiation, typeArguments);
    }

    public TypeName visitTypeName(Node n) {
        String primitiveType = n.getString(0);
        Node qualifiedIdentifiersNode = n.getNode(1);
        Node typeInstantiationsNode = n.getNode(2);

        List<String> qualifiedIdentifiers = new ArrayList<String>();
        for(Node tNode : qualifiedIdentifiersNode) {
            String identifier = tNode.getString(0);
            qualifiedIdentifiers.add(identifier);
        }

        List<TypeInstantiation> typeInstantiations = new ArrayList<TypeInstantiation>();
        for(Node tNode : typeInstantiationsNode) {
            TypeInstantiation identifier = expressionDispatch(tNode);
            typeInstantiations.add(identifier);
        }

        return new TypeName(primitiveType, qualifiedIdentifiers, typeInstantiations);
    }
*/
    public Expression expressionDispatch(Node n)
    {
        Expression expr = (Expression) dispatch(n);
        
	expr.setLocation(n.getLocation());
	expr.setScopeName(scopeName);
	
	if (firstExpression) {
	    Translator.registerUnresolvedExpression(expr);
	    firstExpression = false;
	}
	
        return expr;
    }

    public ExpressionVisitor(String scopeName) {
	this.scopeName = scope;
    }
    
    private String scopeName;
    private bool firstExpression = true;

}
