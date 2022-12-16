package edu.eec.lexer;

import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * This class is a part of the package edu.eec.lexer and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-19.
 */
public class TokenScanner {

    /**
     * Initialize String Reader.
     */
    private final Reader reader;

    /**
     * Stream tokenizer.
     */
    private ExpressionTokenizer expressionTokenizer;

    /**
     * Default constructor.
     */
    public TokenScanner(String input) {
        this.reader = new StringReader(input);
        this.expressionTokenizer = initTokenizer();
    }

    /**
     * Reset functionality to begin from the beginning of the string.
     */
    public void resetScanner() {
        this.expressionTokenizer = initTokenizer();
    }

    /**
     * Initialize the custom tokenizer.
     */
    private ExpressionTokenizer initTokenizer() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(this.reader);
        tokenizer.slashSlashComments(false);
        tokenizer.slashStarComments(false);
        tokenizer.eolIsSignificant(false);
        tokenizer.ordinaryChar('/');
        tokenizer.ordinaryChar('&');
        tokenizer.ordinaryChar('%');
        tokenizer.ordinaryChar('$');
        tokenizer.ordinaryChar('#');
        tokenizer.ordinaryChar('!');
        tokenizer.ordinaryChar('@');
        tokenizer.ordinaryChar('_');
        tokenizer.ordinaryChar('=');
        tokenizer.ordinaryChar('~');
        tokenizer.ordinaryChar('`');
        return tokenizer;
    }

    /**
     * Read token.
     */
    public Token nextToken() {
        try {
            int token = this.expressionTokenizer.nextToken();
            if (token == StreamTokenizer.TT_EOF) return Token.empty();
            else {
                switch (this.expressionTokenizer.ttype) {
                    case StreamTokenizer.TT_NUMBER:
                        return numericToken(this.expressionTokenizer);
                    case LexicalLiterals.ADDITION_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                        return additionToken(this.expressionTokenizer);
                    case LexicalLiterals.SUBTRACTION_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                    case LexicalLiterals.MULTIPLICATION_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                        return multiplicationToken(this.expressionTokenizer);
                    case LexicalLiterals.DIVISION_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                        return divisionToken(this.expressionTokenizer);
                    case LexicalLiterals.PERIOD_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                        return periodToken(this.expressionTokenizer);
                    case LexicalLiterals.EXPONENT_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                        return exponentToken(this.expressionTokenizer);
                    case LexicalLiterals.OPEN_PARENTHESIS_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                        return leftParenthesisToken(this.expressionTokenizer);
                    case LexicalLiterals.CLOSE_PARENTHESIS_CHARACTER:
                        this.expressionTokenizer.advanceTokenEnd();
                        return rightParenthesisToken(this.expressionTokenizer);
                    case StreamTokenizer.TT_WORD:
                        return variableToken(this.expressionTokenizer);
                    default:
                        this.expressionTokenizer.advanceTokenEnd();
                        return notDefinedToken(this.expressionTokenizer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Token.empty();
        }
    }

    /**
     * Build numeric constant token.
     */
    private Token numericToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.NUMERIC_CONSTANT)
                .withValue(Double.toString(tokenizer.nval))
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Build addition operator token.
     */
    private Token additionToken(ExpressionTokenizer tokenizer) {
        return Token.empty().withType(TokenTypes.ADD).withTokenName(LexicalLiterals.addition()).withLocation(
                Position.empty()
                        .withBegin(tokenizer.getLastTokenStart())
                        .withEnd(tokenizer.getLastTokenEnd())
        );
    }

    /**
     * Build subtraction operator token.
     */
    private Token subtractionToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.SUBTRACT)
                .withTokenName(LexicalLiterals.subtraction())
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Build multiplication operator token.
     */
    private Token multiplicationToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.MULTIPLY)
                .withTokenName(LexicalLiterals.multiplication())
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Build division operator token.
     */
    private Token divisionToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.DIVIDE)
                .withTokenName(LexicalLiterals.division())
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Build period operator token.
     */
    private Token periodToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.PERIOD)
                .withTokenName(LexicalLiterals.period())
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );

    }

    /**
     * Build exponent operator token.
     */
    private Token exponentToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.EXPONENT)
                .withTokenName(LexicalLiterals.exponent())
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Build left-parenthesis operator token.
     */
    private Token leftParenthesisToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.OPEN_PARENTHESIS)
                .withTokenName(LexicalLiterals.openParenthesis())
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );

    }

    /**
     * Build right-parenthesis operator token.
     */
    private Token rightParenthesisToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.CLOSE_PARENTHESIS)
                .withTokenName(LexicalLiterals.closeParenthesis())
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Build variable token.
     */
    private Token variableToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.VARIABLE)
                .withTokenName(tokenizer.sval)
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Build not-defined token.
     */
    private Token notDefinedToken(ExpressionTokenizer tokenizer) {
        return Token.empty()
                .withType(TokenTypes.NONE)
                .withTokenName(LexicalLiterals.charStringOf(tokenizer.ttype))
                .withLocation(
                        Position.empty()
                                .withBegin(tokenizer.getLastTokenStart())
                                .withEnd(tokenizer.getLastTokenEnd())
                );
    }

    /**
     * Factory for the token scanner.
     */
    public static TokenScanner of(String input) {
        return new TokenScanner(input);
    }
}
