
package tree;

import java.util.ArrayList;

public class FieldDeclaration extends Declaration {
    List<Modifier> modifiers;
    Type type;
    String name;
    Expression assignment;
}

