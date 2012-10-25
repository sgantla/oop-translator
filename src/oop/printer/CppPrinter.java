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

import oop.tree.*; 
import oop.tree.expressions.*; 
import oop.tree.statements.*; 
//import oop.tree.interfaces.*; 
//import oop.tree.statements.FormalParameter;

import xtc.type.*;
import xtc.tree.*; 
import xtc.util.*;


/**
 * A translator from (a subset of) Java to (a subset of) C++.
 *
 * @author Robert Grimm
 * @version $Revision$
 */
public class CppPrinter {

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
		//ClassDeclaration rootClass = cppAstRootCNode.getClassDeclaration(); 
		/*visit((CNode)rootClass); */
	}

	/** Visit Expressions. */

	/** Visit the specified equality expression CNode. */
	public void visitEqualityExpression(EqualityExpression n) throws IOException { 
		//visitBinaryExpression(n); 
	}

	/** Visit the specified additive expression CNode. */
	public void visitAdditiveExpression(AdditiveExpression n) throws IOException { 
		// Tomorrow
		getBinary(n); 
	}

	/** Visit the specified multiplicative expression CNode. */ 
	public void visitMultiplicationExpression(MultiplicativeExpression n) throws IOException {
		// Tomorrow
		getBinary(n); 
	}

	/** Visit the specified bitwise and expression CNode. */
	public void visitBitwiseAndExpression(BitwiseAndExpression n) throws IOException {
		getBinary(n); 
	}

	/** Visit the specified bitwise or expression CNode. */
	public void visitBitwiseOrExpression(BitwiseOrExpression n) throws IOException { 
		getBinary(n); 
	}

	/** Visit the specified bitwise xor expression CNode. */
	public void visitBitwiseXorExpression(BitwiseXorExpression n) throws IOException {
		getBinary(n); 
	}

	/** Visit the specified logical and expression CNode. */ 
	public void visitLogicalAndExpression(LogicalAndExpression n) throws IOException {
		getBinary(n); 
	}

	/** Visit the specified logical or expression CNode. */
	public void visitLogicalOrExpression(LogicalOrExpression n) throws IOException {
		getBinary(n); 
	}

	/** Visit the specified shift expression CNode. */ 
	public void visitShiftExpression(ShiftExpression n) throws IOException {
		getBinary(n); 
	}

	/** Visit a general binary expression. */
	public void getBinary(BinaryExpression n) throws IOException {
		visit(n.leftExpression);
		cout.write(n.operator); 
		visit(n.rightExpression); 
	}

	/** Visit the specified logical negation expression CNode. */
	public void visitLogicalNegationExpression(LogicalNegationExpression n) throws IOException  {
		getUnary(n); 
	}

	/** Visit the specified bitwise negation expression CNode. */
	public void visitBitwiseNegationExpression(BitwiseNegationExpression n) throws IOException  {
		getUnary(n); 
	}

	/** Visit the specified postfix expression CNode. */
	public void visitPostfixExpression(PostfixExpression n) throws IOException  {
		getUnary(n); 
	}

	/** Visit a general unary expression CNode. */
	public void getUnary(UnaryExpression n) throws IOException {
		cout.write(n.operator); 
		visit(n.expression); 
	}


	/** Visit the specified boolean literal CNode. */
	public void visitBooleanLiteral(BooleanLiteral n) throws IOException {
		getLiteral(n); 
	}

	/** Visit the specified character literal CNode. */
	public void visitCharacterLiteral(CharacterLiteral n) throws IOException {
		getLiteral(n); 
	}

	/** Visit the specified floating point literal CNode. */
	public void  visitFloatingPointLiteral(FloatingPointLiteral n) throws IOException { 
		getLiteral(n); 
	}

	/** Visit the specified integer literal CNode. */
	public void  visitIntegerLiteral(IntegerLiteral n) throws IOException { 
		getLiteral(n); 
	}

	/** Visit the specified string literal expression CNode. */
	public void visitStringLiteral(StringLiteral n) throws IOException { 
		if (isReturn) {
			cout.write("new __String(" + n.value + ")"); 
		}
		else {
			visit(n); 
		}
	}

	/** Visit the specified null literal expression CNode. */
	public void visitNullLiteral(NullLiteral n) throws IOException {
		cout.write(" __rt::null()");
	}

	/** Visit a class literal expression. */
	/*public void visitClassLiteral(ClassLiteral n) throws IOException {
		getLiteral(n); 
	}
	 */
	/** Visit a general literal expression. */
	public void getLiteral(Literal n) throws IOException {
		//cout.write(n.value); 
	}

	/** Visit the basic cast expression CNode. */
	public void visitBasicCastExpression(BasicCastExpression n)  throws IOException{

	}

	/** Visit the specified cast expression CNode. */
	public void visitCastExpression(CastExpression n) throws IOException {

	}

	/** Visit the specified call expression CNode. */
	public void visitCallExpression(CallExpression n) throws IOException { 
		

	}

	/** Visit the specified instance of expression CNode. */
	public void visitInstanceOfExpression(InstanceOfExpression n)  throws IOException{

	}

	/** Visit the specified new array expression CNode. */ 
	public void  visitNewArrayExpression(NewArrayExpression n) throws IOException {

	}

	/** Visit the specified subscript expression CNode. */
	public void visitNewClassExpression(NewClassExpression n) throws IOException {

	}

	/** Visit the specified primary identifier expression CNode. */
	public void visitPrimaryIdentifier(PrimaryIdentifier n) throws IOException {
	}

	/** Visit the specified relational expression CNode. */ 
	public void visitRelationalExpression(RelationalExpression n) throws IOException {
		// Tomorrow
		visit(n.leftExpression);
		cout.write(n.operator); 
		visit(n.rightExpression); 

	}

	/** Visit the specified selection expression CNode. */
	public void visitSelectionExpression(SelectionExpression n)  throws IOException{

	}

	/** Visit the specified subscript expression CNode. */
	public void visitSubscriptExpression(SubscriptExpression n) throws IOException {

	}

	/** Visit the specified super expression CNode. */
	public void visitSuperExpression(SuperExpression n)  throws IOException{
	}

	/** Visit the specified ternary expression CNode. */
	public void visitTernaryExpression(TernaryExpression n)  throws IOException{

	}

	/** Visit the specified type name expression CNode. */
	public void visitTypeName(TypeName n)  throws IOException{
	}

	/** Visit the specified type argument expression CNode. */
	public void visitTypeArgument(TypeArgument n)  throws IOException{

	}

	/** Visit the specified type initialization expression CNode. */
	public void visitTypeInstantiation(TypeInstantiation n) throws IOException {

	}

	/** Statements */

	/** Visit the specified type assert statement CNode. */
	public void visitAssertStatement(AssertStatement n) throws IOException{
	}

	/** Visit the specified for statement. */ 
	public void visitForStatement(ForStatement n) throws IOException {

	}

	/** Visit the specified basic for control statement. */ 
	public void visitBasicForControl(BasicForControl n) throws IOException {

	}

	/** Visit the specified basic for control statement. */ 
	public void visitEnhancedForControl(EnhancedForControl n) throws IOException {

	}

	/** Visit the specified break statement CNode. */ 
	public void visitBreakStatement(BreakStatement n) throws IOException {

	}

	/** Visit the specified catch clause statement CNode. */ 
	public void visitCatchClause(CatchClause n) throws IOException {

	}

	/** Visit the specified conditional expression CNode */ 
	public void visitConditionalExpression(ConditionalExpression n) throws IOException { 
		cout.write("(("); 
		visit(n.operand1); 
		cout.write(") ? "); 
		visit(n.operand2); 
		cout.write(" : "); 
		visit(n.operand3); 
		cout.write(" )"); 

		//tomorrow
		/*operand1 = exp1;
        operand2 = exp2;
        operand3 = exp3;

        Type returnType1 = exp2.getReturnType();
        Type returnType2 = exp3.getReturnType();*/
	}

	/** Visit the specified continue statement CNode. */ 
	public void visitContinueStatement(ContinueStatement n) throws IOException {

	}

	/** Visit the specified declaration or statement CNode. */
	public void visitDeclarationOrStatement(DeclarationOrStatement n) throws IOException{

	}

	/** Visit the specified do while statement CNode.  */
	public void visitDoWhileStatement(DoWhileStatement n) throws IOException{

	}

	/** Visit the specified while statement CNode. */
	public void visitWhileStatement(WhileStatement n) throws IOException {
		cout.write("while ("); 
		visit(n.expression); 
		cout.write(") { "); 
		cout.newLine(); 
		visit(n.statement); 
		cout.write("}"); 
		cout.newLine(); 

		/* public Expression expression;
		    public Statement statement;*/

	}

	/** Visit the specified empty statement CNode. */ 
	public void visitEmptyStatement(EmptyStatement n) throws IOException{
		// Do nothing
	}

	/** Visit the specified formal parameter CNode. */
	public void visitFormalParameter(FormalParameter n) throws IOException{
		for (Modifier m : n.modifiers) {
			getModifier(m); 
			cout.write(' ');
		}

		// Print the type
		visit(n.type); 
		cout.write(' '); 

		// Print the name 
		cout.write(n.name + ";"); 

		/*public List<Modifier> modifiers;
		   public Type type;
		   public String name;*/
	}

	/** Visit the specified labeled statement CNode. */
	public void visitLabeledStatement(LabeledStatement n)throws IOException {

	}

	/** Visit the specified return statement CNode. */ 
	public void visitReturnStatement(ReturnStatement n) throws IOException {
		cout.write("return "); 
		visit(n.returnExpression);
	}

	/** Visit the specified switch clause statement CNode.  */
	public void visitSwitchClause(SwitchClause n) throws IOException {

	}

	/** Visit the specified switch statement CNode. */ 
	public void visitSwitchStatement(SwitchStatement n) throws IOException {

	}

	/** Visit the specified throw statement CNode. */
	public void visitThrowStatement(ThrowStatement n) throws IOException {

	}

	/** Visit the specified try catch finally statement CNode. */
	public void visitTryCatchFinallyStatement(TryCatchFinallyStatement n) throws IOException {

	}

	/** Visit things in tree that are not already included previous to this. */

	/** Visit the specified block node. */
	public void visitBlock(Block n) throws IOException {
		for (Statement s : n.statements) {
			visit(s); 
		}
		//    List<Statement> statements;

	}

	/** Visit the specified class body node. */
	public void visitClassBody(ClassBody n) throws IOException {
		for (Declaration d : n.declarations) {
			visit(d); 
		}
		//List<Declaration> declarations;
	}

	/** Visit the specified class declaration node. */
	public void visitClassDeclaration(ClassDeclaration n) throws IOException {
		// Print the modifiers
		for (Modifier m : n.modifiers) {
			getModifier(m); 
			cout.write(" "); 
		}

		// Print class name 
		cout.write(n.name + "{"); 

		// Print the class body 
		visit(n.classBody); 
		cout.newLine(); 
		cout.write("}"); 

		/* If you need to print the v table for debugging
		visit(n._vTable);  
		 */

		/* If you need to print the parent class declaration for debugging
		visit(n._inheritsFrom); 
		 */

		/*List<Modifier> modifiers;
			    String name;
			    ClassBody classBody;

			    VTable _vTable; // female
			    ClassDeclaration _inheritsFrom;*/

	}

	/** Visit the specified constructor declaration node. */
	public void visitConstructorDeclaration(ConstructorDeclaration n) throws IOException {
		// Print the modifiers
		for (Modifier m : n.modifiers) {
			getModifier(m); 
			cout.write(" "); 
		}

		// Print the name 
		cout.write(n.name + " ("); 

		// Print the parameters if there are any
		for (FormalParameter f : n.formalParameters) {
			//visit(f); 
			cout.write(", "); 
			// This prints an extra comma at the end. Fix.
		}

		cout.write(") {"); 

		/* Print the initialization list for debugging 
		for (InitializationListEntry i : n.initializations) {
			visit(i); 
			cout.newLine(); 
		} */

		/* Print throws when we support them
		cout.write("throws "); 
		visit(n.throws); 
		cout.write("{"); 
		cout.newLine(); */

		// Print method block
		visit(n.methodBlock); 

		/*
	    List<Modifier> modifiers;
	    String name;
	    List<FormalParameter> formalParameters;
	    //ThrowsClause throws;
	    List<InitializationListEntry> initializations;
	    Block methodBlock;*/
	}

	/** Visit the specified CppCompilationUnit node. */
	public void visitCppCompilationUnit(CppCompilationUnit n) throws IOException {
		//visit(n.namespace); 
		//visit(n.classDeclaration); 		

		/*
		private NamespaceDeclaration namespace; 
		//private final List<UsingDeclaration> usingList; // not supported
		private final ClassDeclaration classDeclaration;*/ 
	}

	/** Visit the specified field declaration node. */
	public void visitFieldDeclaration(FieldDeclaration n) throws IOException {
		// Print the modifiers
		for (Modifier m : n.modifiers) {
			getModifier(m); 
			cout.write(" "); 
		}

		// Print the type 
		visit(n.type); 
		cout.write(" " + n.name + " = "); 

		// Print the assignment expression
		visit(n.assignment); 
		cout.write(";"); 


		/* 
		 *     public List<Modifier> modifiers;
    public Type type;
    public String name;
    public Expression assignment;
		 */
	}

	/** Visit the specified method declaration node. */
	public void visitMethodDeclaration(MethodDeclaration n) throws IOException {

		// Print the modifiers
		for (Modifier m : n.modifiers) {
			getModifier(m); 
			cout.write(" "); 
		}

		// Print return type modifiers if there are any
		if (null != n.returnTypeModifiers) {
			for (Modifier rm : n.returnTypeModifiers) {
				getModifier(rm); 
				cout.write(" "); 
			}
		}

		// Print the return type 
		visit(n.returnType); 
		cout.write(" "); 

		// Print the name of the method 
		cout.write(n.name + " "); 
		
		// Print the formal parameters if there are any
		/*if (null != n.formalParameters) {
			for (FormalParameter f : n.formalParameters) {
				visit(f); 
				cout.write(", "); // This will print an extra comma at the end. Fix.
			}
		}*/ 
		cout.write(")"); 
		
		/* Print throws clause if there is one 
		if (null != n.throws) {
			cout.write("throws "); 
			visit(n.throws); 
			
		} */
		
		cout.write(" {"); 
		cout.newLine(); 

		// Print method block 
		visit(n.methodBlock); 
		/*
	    List<Modifier> modifiers;
	    Type returnType;
	    List<Modifier> returnTypeModifiers;
	    String name;
	    List<FormalParameter> formalParameters;
	    //ThrowsClause throws;
	    Block methodBlock;*/
	}

	/** Return lower case names of the specified modifier. */
	public void getModifier(Modifier n) throws IOException {
		cout.write(n.name().toLowerCase()); 
	}

	/** Visit the specified namespace declaration node. */
	public void visitNamespaceDeclaration(NamespaceDeclaration n) throws IOException {
		// Gets a list of strings 
	}

	/** Visit the specified object type. */
	public void visitObjectType(ObjectType n) throws IOException {

	}

	/** Visit the specified pointer type node. */
	public void visitPointerType(PointerType n) throws IOException {

	}

	/** Visit the specified primitive type node. */
	public void visitPrimitiveType(PrimitiveType n) throws IOException {

	}

	/** Visit the specified v table node. */
	public void visitVTable(VTable n) throws IOException {

	}

	/** Visit the specified v table field declaration node. */
	public void visitVTableFieldDeclaration(VTableFieldDeclaration n) throws IOException {

	}

	/** Visit the specified System.out.print node. */
	public void visitSystemOutPrint(CNode n) throws IOException {

	}

	/** Visit the specified System.out.println node. */
	public void visitSystemOutPrintLn(CNode n) throws IOException {

	}


	// Redo this 
	public CNode visit(CNode n) {
<<<<<<< HEAD
		/*if (null != n) {
			for (Object o : n) {
				if(o instanceof CNode) {
					dispatch((CNode)o);
				}
=======
		if (null != n) {
			switch (n.getName()) {
			      AdditiveExpression:
				      visitAdditiveExpression(n);
				      break;
			      Annotation:
				      visitAnnotation(n);
				      break;
			      AnnotationDeclaration:
				      visitAnnotationDeclaration(n);
				      break;
			      AnnotationMethod:
				      visitAnnotationMethod(n);
				      break;
			      Annotations:
				      visitAnnotations(n);
				      break;
			      Arguments:
				      visitArguments(n);
				      break;
			      ArrayInitializer:
				      visitArrayInitializer(n);
				      break;
			      AssertStatement:
				      visitAssertStatement(n);
				      break;
			      BasicCastExpression:
				      visitBasicCastExpression(n);
				      break;
			      BasicForControl:
				      visitBasicForControl(n);
				      break;
			      BitwiseAndExpression:
				      visitBitwiseAndExpression(n);
				      break;
			      BitwiseNegationExpression:
				      visitBitwiseNegationExpression(n);
				      break;
			      BitwiseOrExpression:
				      visitBitwiseOrExpression(n);
				      break;
			      BitwiseXorExpression:
				      visitBitwiseXorExpression(n);
				      break;
			      Block:
				      visitBlock(n);
				      break;
			      BlockDeclaration:
				      visitBlockDeclaration(n);
				      break;
			      BooleanLiteral:
				      visitBooleanLiteral(n);
				      break;
			      Bound:
				      visitBound(n);
				      break;
			      BreakStatement:
				      visitBreakStatement(n);
				      break;
			      CallExpression:
				      visitCallExpression(n);
				      break;
			      CaseClause:
				      visitCaseClause(n);
				      break;
			      CastExpression:
				      visitCastExpression(n);
				      break;
			      CharacterLiteral:
				      visitCharacterLiteral(n);
				      break;
			      ClassBody:
				      visitClassBody(n);
				      break;
			      ClassDeclaration:
				      visitClassDeclaration(n);
				      break;
			      ClassLiteralExpression:
				      visitClassLiteralExpression(n);
				      break;
			      CompilationUnit:
				      visitCompilationUnit(n);
				      break;
			      ConcreteDimensions:
				      visitConcreteDimensions(n);
				      break;
			      ConditionalExpression:
				      visitConditionalExpression(n);
				      break;
			      ConditionalStatement:
				      visitConditionalStatement(n);
				      break;
			      ConstructorDeclaration:
				      visitConstructorDeclaration(n);
				      break;
			      ContinueStatement:
				      visitContinueStatement(n);
				      break;
			      Declarator:
				      visitDeclarator(n);
				      break;
			      Declarators:
				      visitDeclarators(n);
				      break;
			      DefaultClause:
				      visitDefaultClause(n);
				      break;
			      DefaultValue:
				      visitDefaultValue(n);
				      break;
			      Dimensions:
				      visitDimensions(n);
				      break;
			      DoWhileStatement:
				      visitDoWhileStatement(n);
				      break;
			      EmptyDeclaration:
				      visitEmptyDeclaration(n);
				      break;
			      EmptyStatement:
				      visitEmptyStatement(n);
				      break;
			      EnhancedForControl:
				      visitEnhancedForControl(n);
				      break;
			      EnumConstant:
				      visitEnumConstant(n);
				      break;
			      EnumConstants:
				      visitEnumConstants(n);
				      break;
			      EnumMembers:
				      visitEnumMembers(n);
				      break;
			      EqualityExpression:
				      visitEqualityExpression(n);
				      break;
			      Expression:
				      visitExpression(n);
				      break;
			      ExpressionList:
				      visitExpressionList(n);
				      break;
			      ExpressionStatement:
				      visitExpressionStatement(n);
				      break;
			      Extension:
				      visitExtension(n);
				      break;
			      FieldDeclaration:
				      visitFieldDeclaration(n);
				      break;
			      FloatingPointLiteral:
				      visitFloatingPointLiteral(n);
				      break;
			      FormalParameter:
				      visitFormalParameter(n);
				      break;
			      FormalParameters:
				      visitFormalParameters(n);
				      break;
			      ForStatement:
				      visitForStatement(n);
				      break;
			      Implementation:
				      visitImplementation(n);
				      break;
			      ImportDeclaration:
				      visitImportDeclaration(n);
				      break;
			      InitializationListEntry:
				      visitInitializationListEntry(n);
				      break;
			      InstanceOfExpression:
				      visitInstanceOfExpression(n);
				      break;
			      InstantiatedType:
				      visitInstantiatedType(n);
				      break;
			      IntegerLiteral:
				      visitIntegerLiteral(n);
				      break;
			      InterfaceDeclaration:
				      visitInterfaceDeclaration(n);
				      break;
			      LabeledStatement:
				      visitLabeledStatement(n);
				      break;
			      LogicalAndExpression:
				      visitLogicalAndExpression(n);
				      break;
			      LogicalNegationExpression:
				      visitLogicalNegationExpression(n);
				      break;
			      LogicalOrExpression:
				      visitLogicalOrExpression(n);
				      break;
			      MethodDeclaration:
				      visitMethodDeclaration(n);
				      break;
			      Modifier:
				      visitModifier(n);
				      break;
			      Modifiers:
				      visitModifiers(n);
				      break;
			      MultiplicativeExpression:
				      visitMultiplicativeExpression(n);
				      break;
			      NewArrayExpression:
				      visitNewArrayExpression(n);
				      break;
			      NewClassExpression :
				      visitNewClassExpression(n);
				      break;
			      NullLiteral:
				      visitNullLiteral(n);
				      break;
			      PackageDeclaration:
				      visitPackageDeclaration(n);
				      break;
			      PostfixExpression:
				      visitPostfixExpression(n);
				      break;
			      PrimaryIdentifier:
				      visitPrimaryIdentifier(n);
				      break;
			      PrimitiveType:
				      visitPrimitiveType(n);
				      break;
			      QualifiedIdentifier:
				      visitQualifiedIdentifier(n);
				      break;
			      RelationalExpression:
				      visitRelationalExpression(n);
				      break;
			      ReturnStatement:
				      visitReturnStatement(n);
				      break;
			      SelectionExpression:
				      visitSelectionExpression(n);
				      break;
			      ShiftExpression:
				      visitShiftExpression(n);
				      break;
			      SomeCatchClause:
				      visitSomeCatchClause(n);
				      break;
			      SomeDeclaration:
				      visitSomeDeclaration(n);
				      break;
			      SomeDeclarators:
				      visitSomeDeclarators(n);
				      break;
			      SomeExpression:
				      visitSomeExpression(n);
				      break;
			      SomeExpressionList:
				      visitSomeExpressionList(n);
				      break;
			      SomeImportDeclaration:
				      visitSomeImportDeclaration(n);
				      break;
			      SomeModifier:
				      visitSomeModifier(n);
				      break;
			      SomeStatement:
				      visitSomeStatement(n);
				      break;
			      StringLiteral:
				      visitStringLiteral(n);
				      break;
			      SubscriptExpression:
				      visitSubscriptExpression(n);
				      break;
			      SuperExpression:
				      visitSuperExpression(n);
				      break;
			      SwitchStatement:
				      visitSwitchStatement(n);
				      break;
			      SynchronizedStatement:
				      visitSynchronizedStatement(n);
				      break;
			      ThisExpression:
				      visitThisExpression(n);
				      break;
			      ThrowsClause:
				      visitThrowsClause(n);
				      break;
			      ThrowStatement:
				      visitThrowStatement(n);
				      break;
			      TryCatchFinallyStatement:
				      visitTryCatchFinallyStatement(n);
				      break;
			      Type:
				      visitType(n);
				      break;
			      TypeArguments:
				      visitTypeArguments(n);
				      break;
			      TypeInstantiation:
				      visitTypeInstantiation(n);
				      break;
			      TypeParameter:
				      visitTypeParameter(n);
				      break;
			      TypeParameters:
				      visitTypeParameters(n);
				      break;
			      UnaryExpression:
				      visitUnaryExpression(n);
				      break;
			      VoidType:
				      visitVoidType(n);
				      break;
			      WhileStatement:
				      visitWhileStatement(n);
				      break;
			      Wildcard:
				      visitWildcard(n);
				      break;
			      WildcardBound:
				      visitWildcardBound(n);
				      break;
>>>>>>> 0cf43dac463cb0faccbe976bb6b2fbaf165bbc59
			}
		}*/
		return n; 
	}

}
