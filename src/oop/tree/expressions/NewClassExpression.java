package oop.tree.expressions;

import java.util.List;

public class NewClassExpression extends Expression {

    //opt expression
    TypeArgument typeArgument; //opt
    TypeName typeName;
    List<Expression> arguments;
    ClassBody classBody;  //opt
    public NewClassExpression(Expression expression, TypeArgument typeArgument, TypeName typeName,
            List<Expression> arguments, ClassBody classBody) {

        this.expression = expression;
        this.typeArgument = typeArgument;
        this.typeName = typeName;
        this.arguments = arguments;
        this.classBody = classBody;
    }
}
