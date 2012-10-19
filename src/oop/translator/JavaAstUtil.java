package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class JavaAstUtil {

    public static enum NodeName {
	AdditiveExpression,
	Annotation,
	AnnotationDeclaration,
	AnnotationMethod,
	Annotations,
	Arguments,
	ArrayInitializer,
	AssertStatement,
	BasicCastExpression,
	BasicForControl,
	BitwiseAndExpression,
	BitwiseNegationExpression,
	BitwiseOrExpression,
	BitwiseXorExpression,
	Block,
	BlockDeclaration,
	BooleanLiteral,
	Bound,
	BreakStatement,
	CallExpression,
	CaseClause,
	CastExpression,
	CharacterLiteral,
	ClassBody,
	ClassDeclaration,
	ClassLiteralExpression,
	CompilationUnit,
	ConcreteDimensions,
	ConditionalExpression,
	ConditionalStatement,
	ConstructorDeclaration,
	ContinueStatement,
	Declarator,
	Declarators,
	DefaultClause,
	DefaultValue,
	Dimensions,
	DoWhileStatement,
	EmptyDeclaration,
	EmptyStatement,
	EnhancedForControl,
	EnumConstant,
	EnumConstants,
	EnumMembers,
	EqualityExpression,
	Expression,
	ExpressionList,
	ExpressionStatement,
	Extension,
	FieldDeclaration,
	FloatingPointLiteral,
	FormalParameter,
	FormalParameters,
	ForStatement,
	Implementation,
	ImportDeclaration ,
	InstanceOfExpression,
	InstantiatedType,
	IntegerLiteral,
	InterfaceDeclaration,
	LabeledStatement,
	LogicalAndExpression,
	LogicalNegationExpression,
	LogicalOrExpression,
	MethodDeclaration,
	Modifier,
	Modifiers,
	MultiplicativeExpression,
	NewArrayExpression,
	NewClassExpression ,
	NullLiteral,
	PackageDeclaration,
	PostfixExpression,
	PrimaryIdentifier,
	PrimitiveType,
	QualifiedIdentifier,
	RelationalExpression,
	ReturnStatement,
	SelectionExpression,
	ShiftExpression,
	SomeCatchClause,
	SomeDeclaration,
	SomeDeclarators,
	SomeExpression,
	SomeExpressionList,
	SomeImportDeclaration,
	SomeModifier,
	SomeStatement,
	StringLiteral,
	SubscriptExpression,
	SuperExpression,
	SwitchStatement,
	SynchronizedStatement,
	ThisExpression,
	ThrowsClause,
	ThrowStatement,
	TryCatchFinallyStatement,
	Type,
	TypeArguments,
	TypeInstantiation,
	TypeParameter,
	TypeParameters,
	UnaryExpression,
	VoidType,
	WhileStatement,
	Wildcard,
	WildcardBound,
    }
    
    public static List<Node> splitFieldDeclarationByDeclarator(Node n) {
	Node modifiers = n.getNode(0);
	Node type = n.getNode(1);
	Node declarators = n.getNode(2);
	
	List<Node> fieldDeclarations = new ArrayList<Node>();
	for (Node declarator : getChildrenByName(declarators, JavaAstUtil.NodeName.Declarator)) {
	    Node newFieldDec = GNode.create(JavaAstUtil.NodeName.FieldDeclaration.toString());
	    newFieldDec.add(modifiers);
	    newFieldDec.add(type);
	    
	    Node newDeclarators = GNode.create(JavaAstUtil.NodeName.Declarators.toString());
	    newDeclarators.add(declarator);
	    
	    newFieldDec.add(newDeclarators);
	    fieldDeclarations.add(newFieldDec);
	}
	
	return fieldDeclarations;
    }
    
    public static Node getChildByName(Node n, NodeName name) {
	for (Object child : n) {
	    if (child instanceof Node) {
		if (NodeName.valueOf(((Node)child).getName()) == name) {
		    return (Node)child;
		}
	    }
	}
	return null;
    }
    
    public static List<Node> getChildrenByName(Node n, NodeName name) {
	List<Node> returnList = new ArrayList<Node>();
	for (Object child : n) {
	    if (child instanceof Node) {
		if (NodeName.valueOf(((Node)child).getName()) == name) {
		    returnList.add((Node)child);
		}
	    }
	}
	return returnList;
    }
    
    public static String extractString(Node n) {
	NodeName name = NodeName.valueOf(n.getName());
	switch (name) {
	
	    case PackageDeclaration:
		Node qualifiedIdentifierNode = n.getNode(1);
		return extractString(qualifiedIdentifierNode);
		
	    case ImportDeclaration:
		qualifiedIdentifierNode = n.getNode(1);
		String packageName = extractString(qualifiedIdentifierNode);
		if (n.get(2) != null) {
		    packageName += "." + n.getString(2);
		}
		return packageName;
		
	    case Modifier:
		return n.getString(0);
		
	    case Extension:
		Node typeNode = n.getNode(0);
		qualifiedIdentifierNode = n.getNode(0);
		return extractString(qualifiedIdentifierNode);
		
	    case QualifiedIdentifier:
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n.size(); i++) {
		    sb.append(n.getString(i) + ".");
		}
		return sb.substring(0, sb.length() - 1);
		
	    default:
		return null;
	}
    }
}
