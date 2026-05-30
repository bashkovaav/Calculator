package com.example.calculator.model;

public class ExpressionToken {
    private final TokenType type;
    private final double value;
    private final String symbol;

    public ExpressionToken(TokenType type, double value, String symbol) {
        this.type = type;
        this.value = value;
        this.symbol = symbol;
    }

    public ExpressionToken(TokenType type, String symbol) {
        this(type, 0, symbol);
    }

    public TokenType getType() { return type; }
    public double getValue() { return value; }
    public String getSymbol() { return symbol; }

    @Override
    public String toString() {
        return type == TokenType.NUMBER ? String.valueOf((int)value) : symbol;
    }
}