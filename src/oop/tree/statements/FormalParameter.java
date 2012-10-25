package oop.tree.statements;

import oop.tree.*;

import xtc.type.*;

import java.util.List;

public class FormalParameter extends CNode {

   public List<Modifier> modifiers;
   public Type type;
   public String name;

   public FormalParameter(List<Modifier> modifiers, Type type, String name) {
       this.modifiers = modifiers;
       this.type = type;
       this.name = name;
   }
}
