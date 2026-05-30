package com.example.calculator.model;

import java.util.*;

public class CalculatorEngine {

    public double evaluate(List<ExpressionToken> tokens) {
        List<ExpressionToken> rpn = toRPN(tokens);
        return evaluateRPN(rpn);
    }

    private List<ExpressionToken> toRPN(List<ExpressionToken> tokens) {
        List<ExpressionToken> output = new ArrayList<>();
        Stack<ExpressionToken> stack = new Stack<>();

        for (ExpressionToken token : tokens) {
            switch (token.getType()) {
                case NUMBER:
                    output.add(token);
                    break;
                case SQRT:
                case LEFT_PAREN:
                    stack.push(token);
                    break;
                case RIGHT_PAREN:
                    while (!stack.isEmpty() && stack.peek().getType() != TokenType.LEFT_PAREN) {
                        output.add(stack.pop());
                    }
                    if (!stack.isEmpty()) stack.pop();
                    break;
                default: // операторы
                    while (!stack.isEmpty() && precedence(stack.peek().getType()) >= precedence(token.getType())) {
                        output.add(stack.pop());
                    }
                    stack.push(token);
            }
        }
        while (!stack.isEmpty()) output.add(stack.pop());
        return output;
    }

    private int precedence(TokenType type) {
        switch (type) {
            case PLUS: case MINUS: return 1;
            case MULTIPLY: return 2;
            case POWER: return 3;
            case SQRT: return 4;
            default: return 0;
        }
    }

    private double evaluateRPN(List<ExpressionToken> rpn) {
        Stack<Double> stack = new Stack<>();
        for (ExpressionToken token : rpn) {
            if (token.getType() == TokenType.NUMBER) {
                stack.push(token.getValue());
            } else {
                if (token.getType() == TokenType.SQRT) {
                    double a = stack.pop();
                    stack.push(Math.sqrt(a));
                } else {
                    double b = stack.pop();
                    double a = stack.pop();
                    switch (token.getType()) {
                        case PLUS: stack.push(a + b); break;
                        case MINUS: stack.push(a - b); break;
                        case MULTIPLY: stack.push(a * b); break;
                        case POWER: stack.push(Math.pow(a, b)); break;
                    }
                }
            }
        }
        return stack.pop();
    }
}