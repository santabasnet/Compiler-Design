package edu.eec.lexer;

import edu.eec.reportutils.ReportUtils;

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
public class Token {

    /**
     * Token type.
     */
    private final TokenTypes type;

    /**
     * Token value.
     */
    private String value;

    /**
     * Token position, position at the input.
     */
    private final Position position;

    /**
     * Token name.
     */
    private final String tokenName;

    /**
     * Default constructor.
     */
    public Token() {
        this.type = TokenTypes.EMPTY;
        this.value = LexicalLiterals.EMPTY_STRING;
        this.tokenName = LexicalLiterals.EMPTY_STRING;
        this.position = Position.empty();
    }

    /**
     * Default Overloaded Constructor.
     *
     * @param type
     * @param value
     * @param position
     */
    public Token(TokenTypes type, String value, String tokenName, Position position) {
        this.type = type;
        this.value = value;
        this.tokenName = tokenName;
        this.position = position;
    }

    public TokenTypes getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Position getLocation() {
        return this.position;
    }

    public String getTokenName() {
        return this.tokenName;
    }

    /**
     * Check if the toke is empty or not.
     */
    public boolean isEmpty() {
        return this.type == TokenTypes.EMPTY;
    }

    /**
     * Check if the token is of empty type or not.
     */
    public boolean nonEmpty() {
        return !isEmpty();
    }

    /**
     * Empty Instance.
     */
    public static Token empty() {
        return new Token();
    }

    /**
     * Build with type.
     */
    public Token withType(TokenTypes type) {
        return new Token(type, this.value, this.tokenName, this.position);
    }

    /**
     * Build with value.
     */
    public Token withValue(String value) {
        return new Token(this.type, value, this.tokenName, this.position);
    }

    /**
     * Build with token name.
     */
    public Token withTokenName(String tokenName) {
        return new Token(this.type, this.value, tokenName, this.position);
    }

    /**
     * Build with position.
     */
    public Token withLocation(Position position) {
        return new Token(this.type, this.value, this.tokenName, position);
    }

    /**
     * Json representation of the token.
     */
    @Override
    public String toString() {
        return ReportUtils.gson.toJson(this);
    }

    /**
     * Get Header.
     */
    public static String getHeader() {
        return String.format("%4s %16s %25s %10s %6s %6s", "S.N.", "Token Name", "Token Type", "Value", "Start", "End\n");
    }

    /**
     * Get Table Title.
     */
    public static String getTitle(){
        return "Symbol Table : \n";
    }

    /**
     * Format given data to a line.
     */
    public String getLine(int count) {
        String cStr = count + ".";
        return String.format(
                "%4s %10s %30s %10s %6s %6s\n",
                cStr, this.getTokenName(), this.getType(), this.getValue(), this.getLocation().getBegin(), this.getLocation().getEnd()
        );
    }
}
