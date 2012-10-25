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

    public List<Modifiers> modifiers;
    public String name;
    public ClassBody classBody;

    public VTable _vTable; // female
    public ClassDeclaration _inheritsFrom;
    // JavaClassBody _jClassBody;
    // Mapping _names;
}
