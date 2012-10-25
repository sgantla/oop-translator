package oop.tree.statements;

import oop.tree.*;
import oop.tree.interfaces.*;

import xtc.type.*;

import java.util.List;

public class FormalParameter extends CNode {

   List<Modifiers> modifiers;
   Type type;
   String name;

   public FormalParameter(List<Modifiers> modifiers, Type type, String name) {
       this.modifiers = modifiers;
       this.type = type;
       this.name = name;
   }
}
