package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.statements.*;
import oop.tree.expressions.*;
import oop.tree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;
import xtc.Constants;

import java.util.*;
import java.io.*;

//GeneralVisitor needs: Modifier, FormalParameter, Type
public class StatementVisitor extends Visitor
{

    public AssertStatement visitAssertStatement(Node n) {
        Node booleanExpressionNode = n.getNode(0);
        Node valueExpressionNode = n.getNode(1);

        Expression booleanExpression = null;
        if (booleanExpressionNode != null) {
            booleanExpression = new ExpressionVisitor(currentScopeName).expressionDispatch(booleanExpressionNode); 
        }

        Expression valueExpression = new ExpressionVisitor(currentScopeName).expressionDispatch(valueExpressionNode);

        return new AssertStatement(booleanExpression, valueExpression);
    }

    public BasicForControl visitBasicForControl(Node n) {
        Node modifiersListNode = n.getNode(0);
        Node typeNode = n.getNode(1);
        Node declaratorsNode = n.getNode(2);
        Node declaratorExpressionListNode = n.getNode(3);
        Node expressionNode = n.getNode(4);
        Node expressionListNode = n.getNode(5);

        List<Modifiers> modifiersList = new ArrayList<Modifiers>();
        if (modifiersListNode != null) {
            for(Object mNode : modifiersListNode) {
                if(mNode instanceof Node) {
                    Modifiers modifier = new GeneralVisitor().visitModifiers((Node)mNode);
                    modifiersList.add(modifier);
                }
            }
        }
        else {
            modifiersList = null; }

        Type type = null;
        if (typeNode != null) {
            type = new GeneralVisitor().visitType(typeNode); }

        List<Statement> declaratorsList = new ArrayList<Statement>();
        if (declaratorsNode != null) {
            for(Object dNode : declaratorsNode) {
                if(dNode instanceof Node) {
                    Statement declarator = statementDispatch((Node)dNode);
                    declaratorsList.add(declarator);
                }
            }
        }
        else {
            declaratorsList = null; }

        List<Expression> declaratorExpressionList = new ArrayList<Expression>();
        if (declaratorExpressionListNode != null) {
            for(Object eNode : declaratorExpressionListNode) {
                if(eNode instanceof Node) {
                    Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch((Node)eNode);
                    declaratorExpressionList.add(expression);
                }
            }
        }
        else {
            declaratorExpressionList = null; }

        Expression expression = null;
        if (expressionNode != null) {
            expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode); }

        List<Expression> expressionList = new ArrayList<Expression>();
        if (expressionListNode != null) {
            for(Object eNode : expressionListNode) {
                if(eNode instanceof Node ) {
                    Expression exp = new ExpressionVisitor(currentScopeName).expressionDispatch((Node)eNode);
                    expressionList.add(exp);
                }
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

        FormalParameter formalParameter = new GeneralVisitor().visitFormalParameter(formalParameterNode);
        Statement statement = statementDispatch(statementNode);

        return new CatchClause(formalParameter, statement);
    }

    public ConditionalStatement visitConditionalStatement(Node n) {
        //first child is binary expression
        Node expNode = n.getNode(0);
        //second is true statement
        Node trueStatementNode = n.getNode(1);
        //third is false statement
        Node falseStatementNode = n.getNode(2);
        
        Expression newExp = new ExpressionVisitor(currentScopeName).expressionDispatch(expNode);
        Statement trueStatement = statementDispatch(trueStatementNode);
        Statement falseStatement = statementDispatch(falseStatementNode);

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
        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        return new DoWhileStatement(statement, expression);
    }

    public EmptyStatement visitEmptyStatement(Node n) {
        return new EmptyStatement();
    }

    public EnhancedForControl visitEnhancedForControl(Node n) {
        Node modifiersListNode = n.getNode(0);
        Node typeNode = n.getNode(1);
        String name = n.getString(2);
        Node expressionNode = n.getNode(3);

        List<Modifiers> modifiersList = new ArrayList<Modifiers>();
        for(Object mNode : modifiersListNode) {
            if(mNode instanceof Node) {
                Modifiers modifier = new GeneralVisitor().visitModifiers((Node)mNode);
                modifiersList.add(modifier);
            }
        }

        Type type = new GeneralVisitor().visitType(typeNode);

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
        Node statementNode = n.getNode(1);

        ForControl forControl = visitBasicForControl(forControlNode);
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
        Node caseClauseStatementNode = n.getNode(1);
        Node defaultClauseNode = n.getNode(2);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        List<Statement> caseClauseStatement = new ArrayList<Statement>();
        for(Object dNode: caseClauseStatementNode) {
            if(dNode instanceof Node) {
                Statement declaration = statementDispatch((Node)dNode);
                caseClauseStatement.add(declaration);
            }
        }

        List<Statement> defaultClause = new ArrayList<Statement>();
        for(Object dNode: defaultClauseNode) {
            if(dNode instanceof Node) {
                Statement declaration = statementDispatch((Node)dNode);
                defaultClause.add(declaration); }
        }

        return new SwitchClause(expression, caseClauseStatement, defaultClause);
    }

    public SwitchStatement visitSwitchStatement(Node n) {
        Node expressionNode = n.getNode(0);
        Node switchClauseListNode = n.getNode(1);

        Expression expression = new ExpressionVisitor(currentScopeName).expressionDispatch(expressionNode);

        List<SwitchClause> switchClauseList = new ArrayList<SwitchClause>();
        for(Object sNode: switchClauseListNode) {
            if(sNode instanceof Node) {
                SwitchClause switchClause = visitSwitchClause((Node)sNode);
                switchClauseList.add(switchClause);
            }
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
        Node catchClauseListNode = n.getNode(1);
        Node catchStatementListNode = n.getNode(2);

        Statement statement = statementDispatch(statementNode);

        List<CatchClause> catchClauseList = new ArrayList<CatchClause>();
        for(Object cNode : catchClauseListNode) {
            if(cNode instanceof Node) {
                CatchClause clause = visitCatchClause((Node)cNode);
                catchClauseList.add(clause);
            }
        }

        List<Statement> catchStatementList = new ArrayList<Statement>();
        for(Object sNode : catchStatementListNode) {
            if(sNode instanceof Node) {
                Statement state = statementDispatch((Node)sNode);
                catchStatementList.add(state);
            }
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
	
        Statement statement = (Statement) dispatch(n);
        
	if (newScopeEntered) {
	    currentScopeName = lastScopeName;
	}
	
        return statement;
    }
    
    public Block visitBlock(Node n) {
	
	List<Statement> statements = new ArrayList<Statement>();
	
	for (Object child : n) {
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
