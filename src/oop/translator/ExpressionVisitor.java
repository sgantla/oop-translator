package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

// TODO: Modifier, Literals, TypeArgument, TypeName
public class ExpressionTranslator extends Visitor
{

    public AdditiveExpression visitAdditiveExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = dispatch(leftNode);
        Expression rightExp = dispatch(rightNode);

        return new AdditiveExpression(leftExp, operator, rightExp);
    }

    public ArrayInitializer visitArrayInitializer(Node n) {

        //Not sure if legit
        List<Node> expressionsNode = n.getList(0);
        List<Node> modifiersNode = n.getList(1);

        List<Expression> expressions = new ArrayList<Expression>();
        for(Node eNode : expressionsNode) {
            Expression expression = dispatch(eNode);
            expressions.add(expression);
        }

        List<Modifier> modifiers = new ArrayList<Modifier>();
        for(Node mNode : modifiersNode) {
            Modifier modifier = dispatch(mNode);
            modifiers.add(modifier);
        }

        return new ArrayInitializer(expressions, modifiers);
    }

    public BasicCastExpression visitBasicCastExpression(Node n) {

        Node typeNameNode = n.getNode(0);
        Node dimensionString = n.getString(1);
        Node expressionNode = n.getNode(2);

        TypeName typeName = dispatch(typeNameNode);

        if(dimensionString != null && dimensionString != "")
            int dimension = Integer.parseInt(dimensionString); 
        else
            int dimension = 0;
        
        Expression expression = dispatch(expressionNode);

        return new BasicCastExpression(typeName, dimension, expression);
    }

    public BitwiseAndExpression visitBitwiseAndExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = dispatch(leftNode);
        Expression rightExp = dispatch(rightNode);

        return new BitwiseAndExpression(leftExp, operator, rightExp);
    }

    public BitwiseNegationExpression visitBitwiseNegationExpression(Node n) {

        Node node = n.getNode(0);
        String operator = n.getString(1);

        Expression expression = dispatch(node);

        return new BitwiseNegationExpression(node, operator);
    }

    public BitwiseOrExpression visitBitwiseOrExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = dispatch(leftNode);
        Expression rightExp = dispatch(rightNode);

        return new BitwiseOrExpression(leftExp, operator, rightExp);
    }

    public BitwiseXorExpression visitBitwiseXorExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExp = dispatch(leftNode);
        Expression rightExp = dispatch(rightNode);

        return new BitwiseXorExpression(leftExp, operator, rightExp);
    }

    public BooleanLiteral visitBooleanLiteral(Node n) {

        String value = n.getString(0);

        return new BooleanLiteral(value);
    }

    public CallExpression visitCallExpression(Node n) {
        
        Node expressionNode = n.getNode(0);
        Node typeArguementsNode = n.getNode(1);
        String name = n.getString(2);
        List<Node> argsNode = n.getList(3);

        if (expressionNode != null)
            Expression expression = dispatch(expressionNode);
        else
            Expression expression = null;

        if (typeArgumentsNode != null)
            TypeArgument typeArguments = dispatch(typeArgumentsNode);
        else
            TypeArgument typeArguments = null;

        String newName = Translator.getName(name); // i don't know how we're going to resolve mangled names, or how we're calling vmethods

        // this is an array of expressions, how to handle??
        // this can also be empty
        List<Expression> arguments = new ArrayList<Expression>();
        for(Node eNode : argsNode) {
            Expression expression = dispatch(eNode);
            arguments.add(expression);
        }

        return new CallExpression(expression, typeArguments, newName, arguments);
    }

    public CastExpression visitCastExpression(Node n) {

        Node expressionNode = n.getNode(0);
        Node typeNode = n.getNode(1);

        Expression expression = dispatch(expressionNode);
        Type type = dispatch(typeNode);

        return new CastExpression(expression, type);
    }

    public CharacterLiteral visitCharacterLiteral(Node n) {

        String value = n.getString(0);

        return new CharacterLiteral(value);
    }
    
    public ClassLiteralExpression visitClassLiteralExpression(Node n) {

        Node node = n.getNode(0);

        Type type = dispatch(node);
        return new ClassLiteralExpression(type);
    }
    
    public ConditionalExpression visitConditionalExpression(Node n) {
        Node expressionNode1 = n.getNode(0);
        Node expressionNode2 = n.getNode(1);
        Node expressionNode3 = n.getNode(2);

        Expression expression1 = dispatch(expressionNode1);
        Expression expression2 = dispatch(expressionNode2);
        Expression expression3 = dispatch(expressionNode3);

        return new ConditionalExpression(expression1, expression2, expression3);
    }

    public EqualityExpression visitEqualityExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);
        
        Expression leftExpression = dispatch(leftNode);
        Expression rightExpression = dispatch(rightNode);

        return new EqualityExpression(leftExpression, operator, rightExpression);
    }

    public FloatingPointLiteral visitFloatingPointLiteral(Node n) {

        String value = n.getString(0);

        return new FloatingPointLiteral(value);
    }

    public InstanceOfExpression visitInstanceOfExpression(Node n) {

        Node expressionNode = n.getNode(0);
        Node typeNode = n.getNode(1);

        Expression expression = dispatch(expressionNode);
        Type type = dispatch(typeNode);

        return new InstanceOfExpression(expression, type);
    }

    public IntegerLiteral visitIntegerLiteral(Node n) {
        String value = n.getString(0);

        retun new IntegerLiteral(value);
    }

    public LogicalAndExpression visitLogicalAndExpression(Node n) {
        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExpression = dispatch(leftNode);
        Expression rightExpression = dispatch(rightNode);

        return new LogicalAndExpression(leftExpression, operator, rightExpression);
    }

    public LogicalNegationExpression visitLogicalNegationExpression(Node n) {
        Node expressionNode = n.getNode(0);
        String operator = n.getString(1);

        Expression expression = dispatch(expressionNode);

        return new LogicalNegationExpression(expression, operator);
    }

    public LogicalOrExpression visitLogicalOrExpression(Node n) {
        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExpression = dispatch(leftNode);
        Expression rightExpression = dispatch(rightNode);

        return new LogicalOrExpression(leftExpression, operator, rightExpression);
    }

    public MultiplicationExpression visitMultiplicationExpression(Node n) {
        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);

        Expression leftExpression = dispatch(leftNode);
        Expression rightExpression = dispatch(rightNode);

        return new MultiplicationExpression(leftExpression, operator, rightExpression);
    }

    public NewArrayExpression visitNewArrayExpression(Node n) {
        Node typeNameNode = n.getNode(0);
        Node concreteDimensionsNode = n.getNode(1);
        String dimensionString = n.getString(2);
        Node expressionNode = n.getNode(3);
        
        TypeName typeName = dispatch(typeNameNode);

        if(concreteDimensionsNode != null)
            ConcreteDimension concreteDimensions = dispatch(concreteDimensionsNode);
        else
            ConcreteDimension concreteDimensions = null;

        if(dimensionNode != null)
            int dimension = Integer.parseInt(dimensionString); 
        else
            int dimension = 0;

        if(expressionNode != null)
            Expression expression = dispatch(expressionNode);
        else
            Expression expression = null;

        return new NewArrayExpression(typeName, concreteDimensions, dimension, expression);
    }

    public RelationalExpression visitRelationalExpression(Node n) {

        Node leftNode = n.getNode(0);
        String operator = n.getString(1);
        Node rightNode = n.getNode(2);
        
        Expression leftExp = dispatch(leftNode);
        Expression rightExp = dispatch(rightNode);

        return new RelationalExpression(leftExp, operator, rightExp);
    }
    
    public Expression dispatch(Node n)
    {
        Object o = super.dispatch(n);
        return (Expression) o;
    }

}
