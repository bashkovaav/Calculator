package com.example.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class ExpressionModel {
    private final List<ExpressionToken> tokens = new ArrayList<>();

    public void addToken(ExpressionToken token) {
        tokens.add(token);
    }

    public void removeLastToken() {
        if (!tokens.isEmpty()) tokens.remove(tokens.size() - 1);
    }

    public void clear() {
        tokens.clear();
    }

    public List<ExpressionToken> getTokens() {
        return new ArrayList<>(tokens);
    }

    public String getExpressionString() {
        StringBuilder sb = new StringBuilder();
        for (ExpressionToken token : tokens) {
            sb.append(token.toString());
        }
        return sb.toString();
    }

    // Для склеивания цифр в одно число
    public boolean isLastTokenNumber() {
        return !tokens.isEmpty() && tokens.get(tokens.size() - 1).getType() == TokenType.NUMBER;
    }

    public void appendDigitToLastNumber(int digit) {
        if (!isLastTokenNumber()) return;
        ExpressionToken last = tokens.get(tokens.size() - 1);
        int currentVal = (int) last.getValue();
        int newVal = currentVal * 10 + digit;
        String newStr = String.valueOf(newVal);
        tokens.set(tokens.size() - 1, new ExpressionToken(TokenType.NUMBER, newVal, newStr));
    }
}