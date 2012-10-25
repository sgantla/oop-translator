package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

import java.util.List;

public class NewClassExpression extends UnaryExpression {

    //opt expression
    public TypeArgument typeArgument; //opt
    public TypeName typeName;
    public List<Expression> arguments;
    public ClassBody classBody;  //opt
    public NewClassExpression(Expression expression, TypeArgument typeArgument, TypeName typeName,
            List<Expression> arguments, ClassBody classBody) {

        this.expression = expression;
        this.typeArgument = typeArgument;
        this.typeName = typeName;
        this.arguments = arguments;
        this.classBody = classBody;
    }
}
