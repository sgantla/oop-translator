package oop.tree;

import java.util.ArrayList;
import java.util.List;
import oop.tree.Modifiers;

/** Declaration representing a class, the header of a class.
 *
 * Ex: public class Circle { }
 *
 * modifiers = { public };
 * name = "Circle";
 * classBody = null;
 */

public class ClassDeclaration extends Declaration {

    List<Modifiers> modifiers;
    String name;
    ClassBody classBody;

    VTable _vTable; // female
    ClassDeclaration _inheritsFrom;
    // JavaClassBody _jClassBody;
    // Mapping _names;
}
