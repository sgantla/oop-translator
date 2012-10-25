package oop.tree;

import oop.tree.expressions.*;
import oop.tree.statements.*;

import java.util.ArrayList;
import java.util.List;

/** A list of statements, primarily the meat of a method.
 *
 * Ex:
 * public int sum(int x, int y, int z) {
 *  int sum = 0;
 *  sum += x;
 *  sum += y;
 *  sum += z;
 *  return sum;
 *
 *  statements = {int sum = 0,  not sure what this is actually...
 *                sum += x,     GBinaryExpression
 *                sum += y,     GBinaryExpression
 *                sum += z,     GBinaryExpression
 *                return sum}   ReturnStatement
 */

public class Block extends Statement {

    public List<Statement> statements;
    
    public Block(List<Statement> statements) {
	this.statements = statements;
    }
}
