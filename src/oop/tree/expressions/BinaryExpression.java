package oop.tree;

/**
 * Abstract representation of a binary expresion. 
 *
 * Ex: x + 1;
 * operand1 = x, a PrimaryIdentifier
 * operand2 = 1, a LiteralExpression
 *
 * Binary expressions can be nested too:
 * Ex: x + y + 2 + z;
 * operand1 = x + y, a GBinaryExpression
 * operand2 = 2 + z, another GBinaryExpression
 * 
 */

public abstract class BinaryExpression extends Expression {

    Expression leftExpression;
    Expression rightExpression;
}
