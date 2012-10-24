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
            Expression booleanExpression = new ExpressionVisitor().dispatch(booleanExpressionNode); }
        else {
            Expression booleanExpression = null; }

        Expression valueExpression = new ExpressionVisitor().dispatch(valueExpressionNode);

        return new AssertStatement(booleanExpression, valueExpression);
    }

    public BasicForControl visitBasicForControl(Node n) {
        List<Node> modifiersListNode = n.getList(0);
        Node typeNode = n.getNode(1);
        List<Node> declaratorsNode = n.getList(2);
        List<Node> declaratorExpressionListNode = n.getList(3);
        Node expressionNode = n.getNode(4);
        List<Node> expressionListNode = n.getList(5);

        List<Modifier> modifiersList = new ArrayList<Modifier>();
        if (modifiersListNode != null) {
            for(Node mNode : modifiersListNode) {
                Modifier modifier = new GeneralVisitor().dispatch(mNode);
                modifiersList.add(modifier);
            }
        }
        else {
            modifiersList = null; }

        if (typeNode != null) {
            Type type = new GeneralVisitor().dispatch(typeNode); }
        else {
            Type type = null; }

        List<Declaration> declaratorsList = new ArrayList<Declarator>();
        if (declaratorsNode != null) {
            for(Node dNode : declaratorsNode) {
                Declaration declarator = new GeneralVisitor().dispatch(dNode);
                declaratorsList.add(declarator);
            }
        }
        else {
            declaratorsList = null; }

        List<Expression> declaratorExpressionList = new ArrayList<Expression>();
        if (declaratorExpressionListNode != null) {
            for(Node eNode : declaratorExpressionListNode) {
                Expression expression = new ExpressionVisitor().dispatch(eNode);
                declaratorExpressionList.add(expression);
            }
        }
        else {
            declaratorExpressionList = null; }

        if (expressionNode != null) {
            Expression expression = new ExpressionVisitor().dispatch(expressionNode); }
        else {
            Expression expression = null; }

        List<Expression> expressionList = new ArrayList<Expression>();
        if (expressionListNode != null) {
            for(Node eNode : expressionListNode) {
                Expression expression = new ExpressionVisitor().dispatch(eNode);
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

        FormalParameter formalParameter = new GeneralVisitor().dispatch(formalParameterNode);
        Statement statement = dispatch(statementNode);

        return new CatchClause(formalParameter, statementNode);
    }

    public ConditionalStatement visitConditionalStatement(Node n) {
        //first child is binary expression
        Node expNode = n.getNode(0);
        //second is true statement
        Node trueStatement = n.getNode(1);
        //third is false statement
        Node falseStatement = n.getNode(2);
        
        Expression newExp = new ExpressionVisitor().dispatch(expNode);
        Statement trueStatement = dispatch(trueStatement);
        Statement falseStatement = dispatch(falseStatement);

        return new ConditionalStatement(newExp, trueStatement, falseStatement);
    }

    public ContinueStatement visitContinueStatement(Node n) {
        String label = n.getString(0);

        return new ContinueStatement(label);
    }

    public DoWhileStatement visitDoWhileStatement(Node n) {
        Node statementNode = n.getNode(0);
        Node expressionNode = n.getNode(1);

        Statement statement = dispatch(statementNode);
        Expression expression = new ExpressionNode.dispatch(expressionNode);

        return new DoWhileStatement(statement, expression);
    }

    public EmptyStatement visitEmptyStatement(Node n) {
        return new EmptyStatement();
    }

    public EnhancedForControl visitEnhancedForControl(Node n) {
        List<Node> modifiersListNode = n.getList(0);
        Node typeNode = n.getNode(1);
        String name = n.getString(2);
        Node expressionNode = n.getNode(3);

        List<Modifier> modifiersList = new ArrayList<Modifier>();
        for(Node mNode : modifiersListNode) {
            Modifier modifier = new GeneralVisitor().dispatch(mNode);
            modifiersList.add(modifier);
        }

        Type type = new GeneralVisitor().dispatch(typeNode);

        Expression expression = new ExpressionVisitor().dispatch(expressionNode);

        return new EnhancedForControl(modifiersList, type, name, expression);
    }

    public ExpressionStatement visitExpressionStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor().dispatch(expressionNode);

        return new ExpressionStatement(expression);
    }

    public ForStatement visitForStatement(Node n) {
        Node forControlNode = n.getNode(0);
        Statement statementNode = n.getNode(1);

        ForControl forControl = dispatch(forControlNode);
        Statement statement = dispatch(statementNode);

        return new ForStatement(forControl, statement);
    }

    public LabeledStatement visitLabeledStatement(Node n) {
        String label = n.getString(0);
        Node statementNode = n.getNode(1);

        Statement statement = dispatch(statementNode);

        return new LabeledStatement(label, statement);
    }

    public ReturnStatement visitReturnStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor().dispatch(expressionNode);

        return new ReturnStatement(expression);
    }

    public SwitchClause visitSwitchClause(Node n) {
        Node expressionNode = n.getNode(0);
        List<Node> caseClauseStatementNode = n.getList(1);
        List<Node> defaultClauseNode = n.getList(2);

        Expression expression = new ExpressionVisitor().dispatch(expressionNode);

        List<DeclarationOrStatement> caseClauseStatement = new ArrayList<DeclarationOrStatement>();
        for(Node dNode: caseClauseStatementNode) {
            DeclarationOrStatement declaration = dispatch(dNode);
            caseClauseStatement.add(declaration);
        }

        List<DeclarationOrStatement> defaultClause = new ArrayList<DeclarationOrStatement>();
        for(Node dNode: defaultClauseNode) {
            DeclarationOrStatement declaration = dispatch(dNode);
            defaultClause.add(declaration);
        }

        return new SwitchClause(expression, caseClauseStatement, defaultClause);
    }

    public SwitchStatement visitSwitchStatement(Node n) {
        Node expressionNode = n.getNode(0);
        List<Node> switchClauseListNode = n.getList(1);

        List<SwitchClause> switchClauseList = new ArrayList<SwitchClause>();
        for(Node sNode: switchClauseListNode) {
            SwitchClause switchClause = dispatch(sNode);
            switchClauseList.add(switchClause);
        }

        return new SwitchStatement(expression, switchClauseList);
    }

    public ThrowStatement visitThrowStatement(Node n) {
        Node expressionNode = n.getNode(0);

        Expression expression = new ExpressionVisitor().dispatch(expressionNode);

        return new ThrowStatement(expression);
    }

    public TryCatchFinallyStatement visitTryCatchFinallyStatement(Node n) {
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

        return new TryCatchFinallyStatement(statement, catchClauseList, catchStatementList);
    }

    public WhileStatement visitWhileStatement(Node n) {
        Node expressionNode = n.getNode(0);
        Node statementNode = n.getNode(1);

        Expression expression = new ExpressionVisitor().dispatch(expressionNode);
        Statement statement = dispatch(statementNode);

        return new WhileStatement(expression, statement);
    }

    public Statement dispatch(Node n)
    {
        Object o = super.dispatch(n);
        return (Statement) o;
    }
}
