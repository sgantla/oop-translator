/*
 * xtc - The eXTensible Compiler
 * Copyright (C) 2012 Robert Grimm
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,
 * USA.
 */
package oop.printer; 

import java.util.*; 
import java.io.*; 

import xtc.lang.JavaFiveParser;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.statements.*;
import oop.tree.interfaces.*; 
import oop.tree.expressions.*;
import oop.tree.PodIterator; 

//import oop.tree.*; 

import xtc.type.*;
import xtc.tree.*; 
import xtc.util.*;


/**
 * A translator from (a subset of) Java to (a subset of) C++.
 *
 * @author Robert Grimm
 * @version $Revision$
 */
public class CppPrinter extends Visitor {

	private boolean isPrint; 
	private boolean isReturn; 
	private boolean isArguments; 
	
	protected final BufferedWriter cout;
	protected final BufferedWriter hout; 
	protected final CompilationUnitPod rootPod;
	protected final CompilationUnitTranslator cppAstRootCNode; 

	public CppPrinter(BufferedWriter cout, BufferedWriter hout, CompilationUnitPod rootPod) {
		this.cout = cout; 
		this.hout = hout; 
		this.rootPod = rootPod; 
		cppAstRootCNode = rootPod.getCppCompilationUnit(); 
		ClassDeclaration rootClass = cppAstRootCNode.getClassDeclaration(); 
		visit((CNode)rootClass); 
	}
	
	/** Visit Expressions. */
	
	/** Visit the specified equality expression CNode. */
	public void visitEqualityExpression(CCNode n) throws IOException { 
		visitGenericBinaryExpression(n); 
	}
	
	/** Visit the specified additive expression CNode. */
	public void visitAdditiveExpression(CNode n) throws IOException {
		visitGenericBinaryExpression(n); 
	}

	/** Visit the specified multiplicative expression CNode. */ 
	public void visitMultiplicationExpression(CNode n) throws IOException {
		visitGenericBinaryExpression(n); 
	}
		
	/** Visit the specified bitwise and expression CNode. */
	public void visitBitwiseAndExpression(CNode n) throws IOException {
		visitGenericBinaryExpression(n); 
	}

	/** Visit the specified bitwise or expression CNode. */
	public void visitBitwiseOrExpression(CNode n) throws IOException { 
		visitGenericBinaryExpression(n); 
	}

	/** Visit the specified bitwise xor expression CNode. */
	public void visitBitwiseXorExpression(CNode n) throws IOException {
		visitGenericBinaryExpression(n); 
	}
	
	/** Visit the specified logical and expression CNode. */ 
	public void visitLogicalAndExprsesion(CNode n) throws IOException {
		visitGenericBinaryExpression(n); 
	}
	
	/** Visit the specified logical or expression CNode. */
	public void visitLogicalOrExpression(CNode n) throws IOException {
		visitGenericBinaryExpression(n); 
	}
	
	/** Visit the specified shift expression CNode. */ 
	public void visitShiftExpression(CNode n) throws IOException {
		visitGenericBinaryExpression(n); 
	}
	
	/** Visit a general binary expression. */
	public void visitGeneralBinaryExpression(CNode n) throws IOException {
		dispatch(n.leftExpression);
		cout.write(n.getString(1)); 
		dispatch(n.rightExpression); 
	}
	
	/** Visit the specified logical negation expression CNode. */
	public void visitLogicalNegationExpression(CNode n) throws IOException  {
		visitGeneralUnaryExpression(n); 
	}
	
	/** Visit the specified bitwise negation expression CNode. */
	public void visitBitwiseNegationExpression(CNode n) throws IOException  {
		visitGeneralUnaryExpression(n); 
	}
	
	/** Visit the specified postfix expression CNode. */
	public void visitPostfixExpression(CNode n) throws IOException  {
		visitGeneralUnaryExpression(n); 
	}
	
	/** Visit a general unary expression CNode. */
	public void visitGeneralUnaryExpression(CNode n) throws IOException {
		cout.write(n.getString(1) + n.getCNode(0)); 
	}
	
	
	/** Visit the specified boolean literal CNode. */
	public void visitBooleanLiteral(CNode n) throws IOException {
		visitGeneralLiteralExpression(n); 
	}
	
	/** Visit the specified character literal CNode. */
	public void visitCharacterLiteral(CNode n) throws IOException {
		visitGeneralLiteralExpression(n); 
	}
	
	/** Visit the specified floating point literal CNode. */
	public void  visitFloatingPointLiteral(CNode n) throws IOException { 
		visitGeneralLiteralExpression(n); 
	}
	
	/** Visit the specified integer literal CNode. */
	public void  visitIntegerLiteral(CNode n) throws IOException { 
		visitGeneralLiteralExpression(n); 
	}
	
	/** Visit the specified string literal expression CNode. */
	public void visitStringLiteral(CNode n) throws IOException { 
		if (isReturn) {
			cout.write("new __String(" + n.getString(0) + ")"); 
		}
		else {
			visit(n); 
		}
	}
	
	/** Visit the specified null literal expression CNode. */
	public void visitNullLiteral(CNode n) throws IOException {
		cout.write(" __rt::null()");
	}
	
	/** Visit a class literal expression. */
	public void visitClassLiteral(CNode n) throws IOException {
	}
	
	/** Visit a general literal expression. */
	public void visitGeneralLiteralExpression(CNode n) throws IOException {
		cout.write(n.getString(0)); 
	}
	
	/** Visit the basic cast expression CNode. */
	public void visitBasicCastExpression(CNode n)  throws IOException{
		
	}
	
	/** Visit the specified cast expression CNode. */
	public void visitCastExpression(CNode n) throws IOException {
		
	}

	/** Visit the specified call expression CNode. */
	public void visitCallExpression(CNode n) throws IOException { 
		
	}
	
	/** Visit the specified instance of expression CNode. */
	public void visitInstanceOfExpression(CNode n)  throws IOException{
		
	}
	
	/** Visit the specified new array expression CNode. */ 
	public void  visitNewArrayExpression(CNode n) throws IOException {
		
	}
	
	/** Visit the specified subscript expression CNode. */
	public void visitNewClassExpression(CNode n) throws IOException {
		
	}
	
	/** Visit the specified primary identifier expression CNode. */
	public void visitPrimaryIdentifier(CNode n) throws IOException {
	}
	
	/** Visit the specified relational expression CNode. */ 
	public void visitRelationalExpression(CNode n) throws IOException {
		
	}
	
	/** Visit the specified selection expression CNode. */
	public void visitSelectionExpression(CNode n)  throws IOException{
		
	}
	
	/** Visit the specified subscript expression CNode. */
	public void visitSubscriptExpression(CNode n) throws IOException {
		
	}
	
	/** Visit the specified super expression CNode. */
	public void visitSuperExpression(CNode n)  throws IOException{
	}
	
	/** Visit the specified ternary expression CNode. */
	public void visitTernaryExpression(CNode n)  throws IOException{
		
	}
	
	/** Visit the specified type name expression CNode. */
	public void visitTypeName(CNode n)  throws IOException{
	}
	
	/** Visit the specified type argument expression CNode. */
	public void visitTypeArgument(CNode n)  throws IOException{
		
	}
	
	/** Visit the specified type initialization expression CNode. */
	public void visitTypeInitialization(CNode n) throws IOException {
		
	}
	
	public CNode visit(CNode n) {
		if (null != n) {
			for (Object o : n) {
				if(o instanceof CNode) {
					dispatch((CNode)o);
				}
			}
		}
	}
	
}
