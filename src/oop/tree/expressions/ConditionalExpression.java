package oop.tree.expressions;

public class ConditionalExpression extends TernaryExpression {

    public ConditionalExpression(Expression exp1, Expression exp2, Expression exp3) {
        operand1 = exp1;
        operand2 = exp2;
        operand3 = exp3;
    }

    public Type getReturnType() {
        return returnType;
    }
}
