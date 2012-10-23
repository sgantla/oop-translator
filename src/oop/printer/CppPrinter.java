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

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.*; 
import java.io.*; 

import xtc.lang.JavaFiveParser;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.tree.interfaces.*; 

//import oop.tree.*; 

import xtc.tree.Location;
import xtc.tree.Locatable;
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
	/**
	 * The flag for printing additional parentheses to avoid gcc
	 * warnings.
	 */
	public static final boolean EXTRA_PARENTHESES = true;

	/**
	 * The base precedence level. This level corresponds to the
	 * expression nonterminal.
	 */
	public static final int PREC_BASE = 0;

	/**
	 * The list precedence level.  This level corresponds to the
	 * assignment expression nonterminal.
	 */
	public static final int PREC_LIST = 11;

	/**
	 * The constant precedence level.  This level corresponds to the
	 * conditional expression nonterminal.
	 */
	public static final int PREC_CONSTANT = 21;

	/** The flag for any statement besides an if or if-else statement. */
	public static final int STMT_ANY = 0;

	/** The flag for an if statement. */
	public static final int STMT_IF = 1;

	/** The flag for an if-else statement. */
	public static final int STMT_IF_ELSE = 2;

	/** The printer for this C printer. */
	protected final PodPrinter pprinter;


	/**
	 * The flag for whether to line up declarations and statements with
	 * their source locations.
	 */
	protected final boolean lineUp;


	/** The flag for whether we just printed a declaration. */
	protected boolean isDeclaration;

	/**
	 * The flag for whether we just printed a declaration spanning
	 * several lines.
	 */
	protected boolean isLongDecl;

	/** The flag for whether we just printed a statement. */
	protected boolean isStatement;

	/** The flag for whether the last statement ended with an open line. */
	protected boolean isOpenLine;

	/**
	 * The flag for whether the current statement requires nesting or
	 * for whether the current declaration is nested within a for
	 * statement.
	 */
	protected boolean isNested;

	/**
	 * The flag for whether this statement is the else clause of an
	 * if-else statement.
	 */
	protected boolean isIfElse;

	/**
	 * The flag for whether this compound statement is an expression (as
	 * in the GCC extension).
	 */
	protected boolean isStmtAsExpr;

	/**
	 * The flag for whether this declarator is a function definition.
	 */
	protected boolean isFunctionDef;

	/** The operator precedence level for the current expression. */
	protected int precedence;

	public CppPrinter(PodPrinter pprinter) {
		this(pprinter, false); 
	}

	/** Create a new translator. */
	public CppPrinter(PodPrinter pprinter, boolean lineUp) {
		this.pprinter = pprinter; 
		this.lineUp = lineUp; 
		pprinter.register(this); 

	}

	protected boolean startStatement(int kind, Node node) {
		if (isIfElse && ((STMT_IF == kind) || (STMT_IF_ELSE == kind))) {
			isNested = false;
		} else {
			if (lineUp) {
				if (isOpenLine) pprinter.pln();
				pprinter.lineUp(node);
			} else {
				if (isDeclaration || isOpenLine) {
					pprinter.pln();
				}
			}
			if (isNested) {
				pprinter.incr();
			}
		}

		isOpenLine     = false;
		boolean nested = isNested;
		isNested       = false;

		return nested;
	}

	protected void prepareNested() {
		isDeclaration = false;
		isStatement   = false;
		isOpenLine    = true;
		isNested      = true;
	}

	protected int startExpression(int prec) {
		if (prec < precedence) {
			pprinter.p('(');
		}

		int old    = precedence;
		precedence = prec;
		return old;
	}

	/**
	 * Stop printing an expression.
	 *
	 * @see #startExpression(int)
	 *
	 * @param prec The previous precedence level.
	 */
	protected void endExpression(int prec) {
		if (precedence < prec) {
			pprinter.p(')');
		}
		precedence = prec;
	}

	/**
	 * End a statement.
	 *
	 * @see #startStatement
	 *
	 * @param nested The flag for whether the current statement is nested.
	 */
	protected void endStatement(boolean nested) {
		if (nested) {
			pprinter.decr();
		}
		isDeclaration = false;
		isStatement   = true;
	}

	/** Visit the specified function definition node. */
	public void visitFunctionDefinition(Node n) {
		if (lineUp) {
			if (isOpenLine) pprinter.pln(); 
			pprinter.lineUp(n); 
		}

		else if (isStatement || isDeclaration) {//isLongDecl) 
			if (isOpenLine) {
				pprinter.pln(); 
			}
			pprinter.pln(); 
		}

		isDeclaration = false; 
		// isLongDecl = false; 
		isStatement = false; 
		isOpenLine = false; 
		isNested = false; 
		isIfElse = false; 

		// Print extension and return type 
		/*printer.indent(); 
		if ((null != n.get(0)) || (null != n.get(1))) {
			if (null != n.get(0)) {
				pprinter.p("__extension__ "); 
			}
			pprinter.p(n.getNode(1)); 
		}*/

		//Print function name and parameters. Pointer Declarators not supported
		pprinter.p(n.getNode(2)); 
		if (null != n.get(3)) {
			pprinter.pln().incr().p(n.getNode(3)).decr(); 
		}

		else {
			isOpenLine = true; 
		}

		isDeclaration = false;
		isLongDecl    = false;
		isStatement   = false;
		isNested      = false;
		isIfElse      = false;

		pprinter.p(n.getNode(4)).pln();

		isDeclaration = true;
		isLongDecl    = true;
		isStatement   = false;
		isOpenLine    = false;
		isNested      = false;
		isIfElse      = false;
	}

	/** Visit the specified empty definition. */
	public void visitEmptyDefinition(Node n) {
		// Don't print anything out 
	}

	/** Visit the specified declaration node */
	public void visitDeclaration(Node n) {

	}

	public void visitIfElseStatement(Node n) {

	}

	public void visitIfStatement(Node n) {

	}

	/** Visit the specified while statement node. */
	public void visitWhileStatement(Node n) {
		boolean nested = startStatement(STMT_ANY, n); 
		pprinter.indent().p("while (").p(n.getNode(0)).p(')');
		prepareNested(); 
		pprinter.p(n.getNode(1)); 
		endStatement(nested); 
	}

	/* Visit the specified SwitchClause
    public void visitSwitchClause(Node n) {
    	/*Node expressionNode = n.getNode(0); 
        Pair<Node> caseClauseStatementNode = n.getList(1);
        Pair<Node> defaultClauseNode = n.getList(2);

        int caseNumber = 1; 
        for(Node dNode : caseClauseStatementNode) {
        	DeclarationOrStatement declaration = dispatch(dNode); 
        	pprinter.p("case: ").p(caseNumber).p(":").indent().pln(declaration);
        	pprinter.p("break;"); 
        	caseNumber ++; 
        }

        for(Node dNode : defaultClauseNode) {
        	DeclarationOrStatement declaration = dispatch(dNode); 
        	pprinter.p("default: ").pln(declaration);
        	pprinter.p("break;"); 
        }

        pprinter.pln('}'); /

    }*/

	/** Visit the specified switch statement node. */ 
	public void visitSwitchStatement(Node n) {
		/*boolean nested = startStatement(STMT_ANY, n); 
		pprinter.indent().p("switch (").p(n.getNode(0)).p(") {"); 
		prepareNested(); 

        Node expressionNode = n.getNode(0);
        List<Node> switchClauseListNode = n.getList(1);

        List<SwitchClause> switchClauseList = new ArrayList<SwitchClause>();
        for(Node sNode: switchClauseListNode) {
        	SwitchClause switchClause = dispatch(sNode);
            SwitchClauseList.add(switchClause);
        }

        SwitchStatement s = new SwitchStatement(expression, switchClauseList);
		 */
	}

	/** Visit the specified continue statement node. */ 
	public void visitContinueStatement(Node n) {
		boolean nested = startStatement(STMT_ANY, n); 
		pprinter.indent().pln("continue;"); 
		endStatement(nested); 
	}

	/** Visit the specified return statement node. */ 
	public void visitReturnStatement(Node n) {
		Node expressionNode = n.getNode(0);

		pprinter.indent().p("return"); 
		if (null != expressionNode.getNode(0)) {
			pprinter.p(' ').p(expressionNode.getNode(0)); 
		}

		pprinter.pln(';'); 
	}

	/** Visit the specified expression statement node. */
	public void visitExpressionStatement(Node n) {
		boolean nested = startStatement(STMT_ANY, n);
		pprinter.indent().p(n.getNode(0)).pln(';');
		endStatement(nested);
	}

	/** Visit the specified empty statement node. */ 
	public void visitEmptyStatement(Node n) {
		boolean nested = startStatement(STMT_ANY, n); 
		pprinter.indent().pln(';'); 
		endStatement(nested); 
	}

	/** Visit the assignment expression node. */
	public void visitAssignmentExpression(Node n) {
		// Support?
		pprinter.p(n.getNode(0)); 
		
		pprinter.p(' ').p(n.getString(1)).p(' ').p(n.getNode(2)); 
	}

	/** Visit the specified logical or expression node. */
	public void visitLogicalOrExpression(Node n) {
		pprinter.p('(').p(n.getNode(0)).p(')'); 
		pprinter.p(" || "); 
		pprinter.p('(').p(n.getNode(1)).p(')'); 
	}

	/** Visit the specified logical and expression node. */ 
	public void visitLogicalAndExprsesion(Node n) {
		pprinter.p(n.getNode(0)).p(" && ").p(n.getNode(1)); 
	}

	/** Visit the specified bitwise xor expression node. */
	public void visitBitwiseXorExpression(Node n) {
		pprinter.p(n.getNode(0)).p(" ^ ").p(n.getNode(1)); 
	}

	/** Visit the specified additive expression node. */
	public void visitAdditiveExpression(Node n) {
		int prec1 = startExpression(120); 
		pprinter.p(n.getNode(0)).p(' ').p(n.getString(1)).p(' '); 
		pprinter.p(n.getNode(2)); 
		endExpression(prec1); 
	}

	public void visitArrayInitializer(Node n) {
	}
	
	/** Visit the basic cast expression node. */
	public void visitBasicCastExpression(Node n) {
		/*
		Node typeNameNode = n.getNode(0);
        Node dimensionString = n.getString(1);
        Node expressionNode = n.getNode(2);		*/
	}

	/** Visit the specified bitwise and expression node. */
	public void visitBitwiseAndExpression(Node n)  {
		pprinter.p('(').p(n.getNode(0)).p(')'); 
		pprinter.p(" & "); 
		pprinter.p('(').p(n.getNode(1)).p(')');
	}

	/** Visit the specified bitwise negation expression node. */
	public void visitBitwiseNegationExpression(Node n) {
		pprinter.p('~').p(n.getNode(0)); 
	}

	/** Visit the specified bitwise or expression node. */
	public void visitBitwiseOrExpression(Node n) { 
		pprinter.p('(').p(n.getNode(0)).p(')'); 
		pprinter.p(" | "); 
		pprinter.p('(').p(n.getNode(1)).p(')'); 
	}


	public void visitCallExpression(Node n) { 
	}

	/** Visit the specified cast expression node. */
	public void visitCastExpression(Node n) {
		pprinter.p('(').p(n.getNode(0)).p(')').p(n.getNode(1)); 

	}

	/** Visit the specified character literal node. */
	public void visitCharacterLiteral(Node n) {
		pprinter.p(n.getString(0)); 
	}

	/** Visit the specified class literal expression node. */
	public void visitClassLiteralExpression(Node n) {
	}

	/** Visit the specified conditional expression node */ 
	public void visitConditionalExpression(Node n) { 
		pprinter.p("if (").p(n.getNode(0)).p(") {"); 

		if (null != n.getNode(1)) {
			pprinter.p(n.getNode(1)).pln(';');
			pprinter.p('}'); 
		}

		else {
			pprinter.p(" /* Empty */ "); 
		}

		if (null != n.getNode(2)) {
			pprinter.pln("else { "); 
			pprinter.p(n.getNode(2)).p('}'); 
		}
	}

	/** Visit the specified equality expression node. */
	public void visitEqualityExpression(Node n) { 
		pprinter.p('(').p(n.getNode(0)).p(')'); 
		pprinter.p(' ').p(n.getString(1)).p(' '); 
		pprinter.p('(').p(n.getNode(2)).p(')'); 
	}

	/** Visit the specified floating point literal node. */
	public void  visitFloatingPointLiteral(Node n) { 
		pprinter.p(n.getString(0)).p('f'); 
	}

	/** Visit the specified instance of expression node. */
	public void visitInstanceOfExpression(Node n) {
		pprinter.p(n.getNode(0)).p(" instance of ").p(n.getNode(1)); 
		Node expressionNode = n.getNode(0);
	}

	/** Visit the specified logical negation expression node. */
	public void visitLogicalNegationExpression(Node n) {
		pprinter.p('!').p(n.getNode(0)); 
	}

	/** Visit the specified multiplicative expression node. */ 
	public void visitMultiplicationExpression(Node n) {
		int prec1 = startExpression(130);
		pprinter.p(n.getNode(0)).p(' ').p(n.getString(1)).p(' ');

		//int prec2 = enterContext();
		pprinter.p(n.getNode(2));
		//exitContext(prec2);

		endExpression(prec1);
	}

	/** Visit the specified new array expression node. */ 
	public void  visitNewArrayExpression(Node n) {
		Node typeNameNode = n.getNode(0);
		Node concreteDimensionsNode = n.getNode(1);
		String dimensionString = n.getString(2);
		Node expressionNode = n.getNode(3);

	}

	/** Visit the specified relational expression node. */ 
	public void visitRelationalExpression(Node n) {
		pprinter.p(n.getNode(0)).p(n.getString(1)).p(n.getNode(0));
	}

	/** Visit the specified shift expression node. */ 
	public void visitShiftExprsesion(Node n) {
		pprinter.p(n.getNode(0)).p(n.getString(1)).p(n.getNode(2)); 
	}

	/** Visit the specified unary minus expression node. */ 
	public void visitUnaryMinusExpression(Node n) {
		// Support?
		pprinter.p('-').p(n.getNode(0)); 
	}

	/** Visit the specified unary plus expression node. */ 
	public void visitUnaryPlusExpression(Node n) {
		// Support?
		pprinter.p('+').p(n.getNode(0)); 
	}

	/** Visit the specified address expression node. */ 
	public void visitAddressExpression(Node n) {
		pprinter.p("&(").p(n.getNode(0)).p(')'); 
	}


	/** Visit the specified function call node. */
	public void visitFunctionCall(Node n) {
		int prec = startExpression(160); 
		pprinter.p(n.getNode(0)).p('(').p(n.getNode(1)).p(')'); 
		endExpression(prec); 
	}

	/** Visit the specified null literal expression node. */
	public void visitNullLiteral(Node n) {
		pprinter.p("NULL"); 
	}

	/** Visit the specified postfix expression node. */
	public void visitPostFixExpression(Node n) {
		pprinter.p(n.getNode(0)).p(n.getString(1)); 
	}

	/** Visit the specified primary identifier expression node. */
	public void visitPrimaryIdentifier(Node n) {
		pprinter.p(n.getString(0)); 
	}

	/** Visit the specified statement as expression node. */
	public void visitStatementAsExpression(Node n) {
		// Support?
	}

	public void visitFloatingConstant(Node n) {
	}

	public void visitIntegerConstant(Node n) {
	}

	public void visitCharacterConstant(Node n) {
	}

	public void visitStringConstant(Node n) {
	}

	/** Visit the specified subscript expression node. */
	public void visitSubscriptExpression(Node n) {
	}

	/** Visit the specified subscript expression node. */
	public void visitNewClassExpression(Node n) {

		/* Node expressionNode = n.getNode(0);
        Node typeArgumentNode = n.getNode(1);
        Node typeNameNode = n.getNode(2);
        Node argumentsNode = n.getList(3);
        Node classBodyNode = n.getNode(4);

        if(expressionNode != null)
            Expression expression = dispatch(expressionNode);
        else
            Expression expression = null;

        if(typeArgumentNode != null)
            TypeArgument typeArgument = dispatch(typeArgumentNode);
        else
            TypeArgument typeArgument = null;

        TypeName typeName = dispatch(typeNameNode);

        List<Expression> arguments = new ArrayList<Expression>();
        for(Node aNode : argumentsNode) {
            Expression argument = dispatch(aNode);
            arguments.add(argument);
        }

        if(classBodyNode != null)
            ClassBody classBody = dispatch(classBodyNode);
        else
            ClassBody classBody = null;

        return new NewClassExpression(expression, typeArgument, typeName, arguments, classBody);*/
	}

	/** Visit the specified selection expression node. */
	public void visitSelectionExpression(Node n) {
	}

	/** Visit the specified string literal expression node. */
	public void visitStringLiteral(Node n) {
		pprinter.p(n.getString(0)); 
	}

	/** Visit the specified super expression node. */
	public void visitSuperExpression(Node n) {
	}

	/** Visit the specified type argument expression node. */
	public void visitTypeArgument(Node n) {
	}

	/** Visit the specified type name expression node. */
	public void visitTypeName(Node n) {
		if (null != n.get(1)) {
			pprinter.p(' ').p(n.getNode(1)); 
		}
	}

	/** Visit the specified type instantiation expression node. */
	public void visitTypeInstantiation(Node n) {

	}

	/** Visit the specified type assert statement node. */
	public void visitAssertStatement(Node n) {
		pprinter.p("(assert ").p(n.getNode(0)).p(") ;");
		visitExpressionStatement(n.getNode(1));
	}

	/** Visit the specified for statement. */ 
	public void visitForStatement(Node n) {
		boolean nested = startStatement(STMT_ANY, n); 
		Node forControlNode = n.getNode(1); 

		pprinter.indent().p("for ("); 
		if (null != n.get(0)) {
			pprinter.p(n.getNode(0)); 
		}

		pprinter.p(';'); 

		if (null != n.get(1)) {
			pprinter.p(' ').p(n.getNode(1)); 
		}

		pprinter.p(';'); 
		if (null != n.get(2)) {
			pprinter.p(' ').p(n.getNode(2)); 
		}
		
		pprinter.p(')'); 
		prepareNested(); 
		pprinter.p(n.getNode(3)); 
		
		endStatement(nested); 
		/*

		  Node forControlNode = n.getNode(0);
	        Statement statementNode = n.getNode(1);

	        ForControl forControl = dispatch(forControlNode);
	        Statement statement = dispatch(statementNode);

	        return new ForStatement(forControl, statement);*/ 
	}

	public void visitBasicForControl(Node n) {
	}

	public void visitEnhancedForControl(Node n) {
	}

	/** Visit the specified break statement node. */ 
	public void visitBreakStatement(Node n) {
		boolean nested = startStatement(STMT_ANY, n); 
		pprinter.indent().pln("break;");
		endStatement(nested); 

	}

	/** Visit the specified catch clause statement node. */ 
	public void visitCatchClause(Node n) {
		// For multiple catch clauses 
	}

	/** Visit the specified do while statement node.  */
	public void visitDoWhileStatement(Node n) {
		boolean nested = startStatement(STMT_ANY, n); 
		pprinter.indent().p("do"); 
		prepareNested(); 
		pprinter.p(n.getNode(0)); 
		if (isOpenLine) {
			pprinter.p(' '); 
		}

		else {
			pprinter.indent(); 
		}

		pprinter.p("while ("); 
		pprinter.p(n.getNode(1)); 
		pprinter.pln(");"); 
		endStatement(nested); 
	}

	//How do label statements in java translate to c++
	/** Visit the specified labeled statement node. */
	public void visitLabeledStatement(Node n) {

		/*String label = n.getString(0);
        Node statementNode = n.getNode(1);

        Statement statement = dispatch(statementNode);

        return new LabeledStatement(label, statement);*/ 
	}

	/** Visit the specified throw statement node. */
	public void visitThrowStatement(Node n) {
		if (null != n.get(0)) {
			pprinter.p("throws (").p(n.getNode(0)).p(')'); 
		}
	}

	/** Visit the specified try catch finally statement node. */
	public void visitTryCatchFinallyStatement(Node n) {
		/*
    	   Node statementNode = n.getNode(0);
           //TODO: im concerned this will not work because the rats documentation sort of makes it seem
           // like there is a list of nodes, each of which has a CatchClause and a Statement
           List<Node> catchClauseListNode = n.getList(1);
           List<Node> catchStatementListNode = n.getList(2);

           Statement statement = dispatch(statementNode);

           List<CatchClause> catchClauseList = new ArrayList<CatchClause>();
           for(Node cNode : catchClauseListNode) {
               CatchClause clause = dispatch(cNode);
               catchClauseList.add(clause);
           }

           List<Statement> catchStatementList = new ArrayList<Statement>();
           for(Node sNode : catchStatementListNode) {
               Statement state = dispatch(sNode);
               catchStatementList.add(state);
           }

           return new TryCatchFinallyStatement(statement, catchClauseList, catchStatementList);*/
	}

	/** Visit the specified method node. */
	public void visitMethodDeclaration(Node n) {
		
		// Print the modifiers
		List<Modifier> modifiers = n.getList(0); 
		/*private List<Modifier> modifiers;
		private String methodName;
		private Type returnType;
		private List<FormalParameter> formalParameters;
		private ThrowsClause throwsClause;
		private BlockTranslator body;
	    }*/
	}
	
	public void visitFieldDeclaration(Node n) {
		
	}
	
	public void visitConstructorDeclaration(Node n) {
	}
	
	public void visitClassDeclaration(Node n) {
	}
	
	public void visitCompilationUnit(Node n) {
		
	}
	
	public void visitSystemOutPrint(Node n) {
		// add #include<iostream.h> to top of c++ file 
		pprinter.p("cout << ");
		
		// TODO: Make sure nested calls don't blow up here
		pprinter.p(n.getNode(0)); 
	}
	
	public static void main(String[] args) {
		System.out.println("hi"); 
	}

}
