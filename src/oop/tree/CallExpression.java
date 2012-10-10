package oop.tree;

import java.util.ArrayList;
import java.util.List;

/** Expression representing a method call.
 * 
 * Ex: System.out.println("Hello world");
 * name = println
 * argumentExpressions = {"Hello world"};
 * implicitExpression = System.out, a PrimaryIdentifier
 */

public class CallExpression extends UnaryExpression {

    String name;
    List<Expression> argumentExpressions;
    Expression implicitExpression;          // doesn't always exist
}