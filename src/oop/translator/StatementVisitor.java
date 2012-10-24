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
            Expression booleanExpression = new ExpressionVisitor().visit(booleanExpressionNode); }
        else {
            Expression booleanExpression = null; }

        Expression valueExpression = new ExpressionVisitor().visit(valueExpressionNode);

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
                Modifier modifier = new GeneralVisitor().visit(mNode);
                modifiersList.add(modifier);
            }
        }
        else {
            modifiersList = null; }

        if (typeNode != null) {
            Type type = new GeneralVisitor().visit(typeNode); }
        else {
            Type type = null; }

        List<Declaration> declaratorsList = new ArrayList<Declarator>();
        if (declaratorsNode != null) {
            for(Node dNode : declaratorsNode) {
                Declaration declarator = new GeneralVisitor().visit(dNode);
                declaratorsList.add(declarator);
            }
        }
        else {
            declaratorsList = null; }

        List<Expression> declaratorExpressionList = new ArrayList<Expression>();
        if (declaratorExpressionListNode != null) {
            for(Node eNode : declaratorExpressionListNode) {
                Expression expression = new ExpressionVisitor().visit(eNode);
                declaratorExpressionList.add(expression);
            }
        }
        else {
            declaratorExpressionList = null; }

        if (expressionNode != null) {
            Expression expression = new ExpressionVisitor().visit(expressionNode); }
        else {
            Expression expression = null; }

        List<Expression> expressionList = new ArrayList<Expression>();
        if (expressionListNode != null) {
            for(Node eNode : expressionListNode) {
                Expression expression = new ExpressionVisitor().visit(eNode);
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

        FormalParameter formalParameter = new GeneralVisitor().visit(formalParameterNode);
        Statement statement = visit(statementNode);

        return new CatchClause(formalParameter, statementNode);
    }

    public ConditionalStatement visitConditionalStatement(Node n) {
        //first child is binary expression
        Node expNode = n.getNode(0);
        //second is true statement
        Node trueStatement = n.getNode(1);
        //third is false statement
        Node falseStatement = n.getNode(2);
        
        Expression newExp = new ExpressionVisitor().visit(expNode);
        Statement trueStatement = visit(trueStatement);
        Statement falseStatement = visit(falseStatement);

        return new ConditionalStatement(newExp, trueStatement, falseStatement);
    }

    public ContinueStatement visitContinueStatement(Node n) {
        String label = n.getString(0);

        return new ContinueStatement(label);
    }

    public DoWhileStatement visitDoWhileStatement(Node n) {
        Node statementNode = n.getNode(0);
        Node expressionNode = n.getNode(1);

        Statement statement = visit(statementNode);
        Expression expression = new ExpressionNode.visit(expressionNode);

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
            Modifier modifier = new GeneralVisitor().visit(mNode);
            modifiersList.add(modifier);
        }

        Type type = new GeneralVisitor().visit(typeNode);

        Expression expression = new ExpressionVisitor().visit(expressionNode);

        return new EnhancedForControl(modifiersList, type, name, expression);
    }

    public ExpressionStatement visitExpressionStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor().visit(expressionNode);

        return new ExpressionStatement(expression);
    }

    public ForStatement visitForStatement(Node n) {
        Node forControlNode = n.getNode(0);
        Statement statementNode = n.getNode(1);

        ForControl forControl = visit(forControlNode);
        Statement statement = visit(statementNode);

        return new ForStatement(forControl, statement);
    }

    public LabeledStatement visitLabeledStatement(Node n) {
        String label = n.getString(0);
        Node statementNode = n.getNode(1);

        Statement statement = visit(statementNode);

        return new LabeledStatement(label, statement);
    }

    public ReturnStatement visitReturnStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor().visit(expressionNode);

        return new ReturnStatement(expression);
    }

    public SwitchClause visitSwitchClause(Node n) {
        Node expressionNode = n.getNode(0);
        List<Node> caseClauseStatementNode = null; // n.getList(1);
        List<Node> defaultClauseNode = null; // n.getList(2);

        Expression expression = new ExpressionVisitor().visit(expressionNode);

        List<DeclarationOrStatement> caseClauseStatement = new ArrayList<DeclarationOrStatement>();
        for(Node dNode: caseClauseStatementNode) {
            DeclarationOrStatement declaration = visit(dNode);
            caseClauseStatement.add(declaration);
        }

        List<DeclarationOrStatement> defaultClause = new ArrayList<DeclarationOrStatement>();
        for(Node dNode: defaultClauseNode) {
            DeclarationOrStatement declaration = visit(dNode);
            defaultClause.add(declaration);
        }

        return new SwitchClause(expression, caseClauseStatement, defaultClause);
    }

    public SwitchStatement visitSwitchStatement(Node n) {
        Node expressionNode = n.getNode(0);
        List<Node> switchClauseListNode = null; // n.getList(1);

        List<SwitchClause> switchClauseList = new ArrayList<SwitchClause>();
        for(Node sNode: switchClauseListNode) {
            SwitchClause switchClause = visit(sNode);
            switchClauseList.add(switchClause);
        }

        return new SwitchStatement(expression, switchClauseList);
    }

    public ThrowStatement visitThrowStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor().visit(expressionNode);

        return new ThrowStatement(expression);
    }

    public TryCatchFinallyStatement visitTryCatchFinallyStatement(Node n) {
        Node statementNode = n.getNode(0);
        //TODO: im concerned this will not work because the rats documentation sort of makes it seem
        // like there is a list of nodes, each of which has a CatchClause and a Statement
        List<Node> catchClauseListNode = null; // n.getList(1);
        List<Node> catchStatementListNode = null; // n.getList(2);

        Statement statement = visit(statementNode);

        List<CatchClause> catchClauseList = new ArrayList<CatchClause>();
        for(Node cNode : catchClauseListNode) {
            CatchClause clause = visit(cNode);
            catchClauseList.add(clause);
        }

        List<Statement> catchStatementList = new ArrayList<Statement>();
        for(Node sNode : catchStatementListNode) {
            Statement state = visit(sNode);
            catchStatementList.add(state);
        }

        return new TryCatchFinallyStatement(statement, catchClauseList, catchStatementList);
    }

    public WhileStatement visitWhileStatement(Node n) {
        Node expressionNode = n.getNode(0);
        Node statementNode = n.getNode(1);

        Expression expression = new ExpressionVisitor().visit(expressionNode);
        Statement statement = visit(statementNode);

        return new WhileStatement(expression, statement);
    }

    public Statement visit(Node n)
    {
        Object o = super.dispatch(n);
        return (Statement) o;
    }
    /*
    public Statement dispatch(Node n)
    {
        Object o = super.visit(n);
        return (Statement) o;
    }
    */
}
