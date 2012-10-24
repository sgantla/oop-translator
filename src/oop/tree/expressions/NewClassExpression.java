package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import java.util.List;

public class NewClassExpression extends UnaryExpression {

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

    public Type getReturnType() {
        return returnType;
    }
}
