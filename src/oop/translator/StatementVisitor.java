package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.statements.*;
import oop.tree.expressions.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

//GeneralVisitor needs: Modifier, FormalParameter, Type
public class StatementVisitor extends Visitor
{

    public AssertStatement visitAssertStatement(Node n) {
        Node booleanExpressionNode = n.getNode(0);
        Node valueExpressionNode = n.getNode(1);

        if (booleanExpressionNode != null) {
            Expression booleanExpression = new ExpressionVisitor(currentScopeNamecurrentScopeName).visit(booleanExpressionNode); }
        else {
            Expression booleanExpression = null; }

        Expression valueExpression = new ExpressionVisitor(currentScopeNamecurrentScopeName).expressionDispatch(valueExpressionNode);

        return new AssertStatement(booleanExpression, valueExpression);
    }

    public BasicForControl visitBasicForControl(Node n) {
        List<Node> modifiersListNode = null; // n.getNode(0);
        Node typeNode = n.getNode(1);
        List<Node> declaratorsNode = null; // n.getList(2);
        List<Node> declaratorExpressionListNode = null; // n.getList(3);
        Node expressionNode = n.getNode(4);
        List<Node> expressionListNode = null; // n.getList(5);

        List<Modifier> modifiersList = new ArrayList<Modifier>();
        if (modifiersListNode != null) {
            for(Node mNode : modifiersListNode) {
                Modifier modifier = new GeneralVisitor().generalDispatch(mNode);
                modifiersList.add(modifier);
            }
        }
        else {
            modifiersList = null; }

        if (typeNode != null) {
            Type type = new GeneralVisitor().generalDispatch(typeNode); }
        else {
            Type type = null; }

        List<Declaration> declaratorsList = new ArrayList<Declarator>();
        if (declaratorsNode != null) {
            for(Node dNode : declaratorsNode) {
                Declaration declarator = new GeneralVisitor().generalDispatch(dNode);
                declaratorsList.add(declarator);
            }
        }
        else {
            declaratorsList = null; }

        List<Expression> declaratorExpressionList = new ArrayList<Expression>();
        if (declaratorExpressionListNode != null) {
            for(Node eNode : declaratorExpressionListNode) {
                Expression expression = new ExpressionVisitor(currentScopeNamecurrentScopeName).expressionDispatch(eNode);
                declaratorExpressionList.add(expression);
            }
        }
        else {
            declaratorExpressionList = null; }

        if (expressionNode != null) {
            Expression expression = new ExpressionVisitor(currentScopeNamecurrentScopeName).expressionDispatch(expressionNode); }
        else {
            Expression expression = null; }

        List<Expression> expressionList = new ArrayList<Expression>();
        if (expressionListNode != null) {
            for(Node eNode : expressionListNode) {
                Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(eNode);
                expressionList.add(expression);
            }
        }
        else {
            expressionList = null; }

        return new BasicForControl(modifiersList, type, declaratorsList, declaratorExpressionList, expression, expressionList);
    }

    public BreakStatement visitBreakStatement(Node n) {
        String label = n.getString(0);

        return new BreakStatement(label);
    }

    public CatchClause visitCatchClause(Node n) {
        Node formalParameterNode = n.getNode(0);
        Node statementNode = n.getNode(1);

        FormalParameter formalParameter = new GeneralVisitor().generalDispatch(formalParameterNode);
        Statement statement = statementDispatch(statementNode);

        return new CatchClause(formalParameter, statementNode);
    }

    public ConditionalStatement visitConditionalStatement(Node n) {
        //first child is binary expression
        Node expNode = n.getNode(0);
        //second is true statement
        Node trueStatement = n.getNode(1);
        //third is false statement
        Node falseStatement = n.getNode(2);
        
        Expression newExp = new ExpressionVisitor(currentScopeName).expressionDispatch(expNode);
        Statement trueStatement = statementDispatch(trueStatement);
        Statement falseStatement = statementDispatch(falseStatement);

        return new ConditionalStatement(newExp, trueStatement, falseStatement);
    }

    public ContinueStatement visitContinueStatement(Node n) {
        String label = n.getString(0);

        return new ContinueStatement(label);
    }

    public DoWhileStatement visitDoWhileStatement(Node n) {
        Node statementNode = n.getNode(0);
        Node expressionNode = n.getNode(1);

        Statement statement = statementDispatch(statementNode);
        Expression expression = new ExpressionNode.expressionDispatch(expressionNode);

        return new DoWhileStatement(statement, expression);
    }

    public EmptyStatement visitEmptyStatement(Node n) {
        return new EmptyStatement();
    }

    public EnhancedForControl visitEnhancedForControl(Node n) {
        List<Node> modifiersListNode = null; // n.getList(0);
        Node typeNode = n.getNode(1);
        String name = n.getString(2);
        Node expressionNode = n.getNode(3);

        List<Modifier> modifiersList = new ArrayList<Modifier>();
        for(Node mNode : modifiersListNode) {
            Modifier modifier = new GeneralVisitor().generalDispatch(mNode);
            modifiersList.add(modifier);
        }

        Type type = new GeneralVisitor().generalDispatch(typeNode);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        return new EnhancedForControl(modifiersList, type, name, expression);
    }

    public ExpressionStatement visitExpressionStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        return new ExpressionStatement(expression);
    }

    public ForStatement visitForStatement(Node n) {
        Node forControlNode = n.getNode(0);
        Statement statementNode = n.getNode(1);

        ForControl forControl = statementDispatch(forControlNode);
        Statement statement = statementDispatch(statementNode);

        return new ForStatement(forControl, statement);
    }

    public LabeledStatement visitLabeledStatement(Node n) {
        String label = n.getString(0);
        Node statementNode = n.getNode(1);

        Statement statement = statementDispatch(statementNode);

        return new LabeledStatement(label, statement);
    }

    public ReturnStatement visitReturnStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        return new ReturnStatement(expression);
    }

    public SwitchClause visitSwitchClause(Node n) {
        Node expressionNode = n.getNode(0);
        List<Node> caseClauseStatementNode = null; // n.getList(1);
        List<Node> defaultClauseNode = null; // n.getList(2);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        List<DeclarationOrStatement> caseClauseStatement = new ArrayList<DeclarationOrStatement>();
        for(Node dNode: caseClauseStatementNode) {
            DeclarationOrStatement declaration = statementDispatch(dNode);
            caseClauseStatement.add(declaration);
        }

        List<DeclarationOrStatement> defaultClause = new ArrayList<DeclarationOrStatement>();
        for(Node dNode: defaultClauseNode) {
            DeclarationOrStatement declaration = statementDispatch(dNode);
            defaultClause.add(declaration);
        }

        return new SwitchClause(expression, caseClauseStatement, defaultClause);
    }

    public SwitchStatement visitSwitchStatement(Node n) {
        Node expressionNode = n.getNode(0);
        List<Node> switchClauseListNode = null; // n.getList(1);

        List<SwitchClause> switchClauseList = new ArrayList<SwitchClause>();
        for(Node sNode: switchClauseListNode) {
            SwitchClause switchClause = statementDispatch(sNode);
            switchClauseList.add(switchClause);
        }

        return new SwitchStatement(expression, switchClauseList);
    }

    public ThrowStatement visitThrowStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        return new ThrowStatement(expression);
    }

    public TryCatchFinallyStatement visitTryCatchFinallyStatement(Node n) {
        Node statementNode = n.getNode(0);
        //TODO: im concerned this will not work because the rats documentation sort of makes it seem
        // like there is a list of nodes, each of which has a CatchClause and a Statement
        List<Node> catchClauseListNode = null; // n.getList(1);
        List<Node> catchStatementListNode = null; // n.getList(2);

        Statement statement = statementDispatch(statementNode);

        List<CatchClause> catchClauseList = new ArrayList<CatchClause>();
        for(Node cNode : catchClauseListNode) {
            CatchClause clause = statementDispatch(cNode);
            catchClauseList.add(clause);
        }

        List<Statement> catchStatementList = new ArrayList<Statement>();
        for(Node sNode : catchStatementListNode) {
            Statement state = statementDispatch(sNode);
            catchStatementList.add(state);
        }

        return new TryCatchFinallyStatement(statement, catchClauseList, catchStatementList);
    }

    public WhileStatement visitWhileStatement(Node n) {
        Node expressionNode = n.getNode(0);
        Node statementNode = n.getNode(1);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);
        Statement statement = statementDispatch(statementNode);

        return new WhileStatement(expression, statement);
    }

    public Statement statementDispatch(Node n) {	
    
	boolean newScopeEntered;
	String lastScopeName;
	if (SymbolTable.hasScope(n)) {
	    String scopeName = n.getStringProperty(Constants.SCOPE);
	    lastScopeName = currentScopeName;
	    currentScopeName = scopeName;
	    newScopeEntered = true;
	}
	
	Node lastParent = currentParent;
	currentParent = n;

        Statement statement = (Statement) dispatch(n);
        
        currentParent = lastParent;
        statement.setParent(currentParent);
        
	if (newScopeEntered) {
	    currentScopeName = lastScopeName;
	}
	
        return statement;
    }
    
    public Block visitBlock(Node n) {
	
	List<Statement> statements = new ArrayList<Statement>();
	
	for (Node child : n) {
	    if (child instanceof Node) {
		Node childNode = (Node) child;
		statements.add(statementDispatch(childNode));
	    }
	}
	
	return new Block(statements);
    }

    public StatementVisitor(CNode n, String scope) {
	
	currentParent = n;
	currentScopeName = scope;
    }
    
    public StatementVisitor(CNode n) {
	
	currentParent = n;
    }
    
    public StatementVisitor() {
	
    }
    
    private CNode currentParent;
    private String currentScopeName;
}
