package edu.eec.lex;

/**
 * This class is a part of the package edu.eec.lex and the package
 * is a part of the project Lex.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-12-09.
 */
public class Token {

    public static final String VARIABLE = "VARIABLE";
    public static final String OPERATOR = "OPERATOR";
    public static final String OPEN_PARENTHESIS = "OPEN_PARENTHESIS";
    public static final String CLOSE_PARENTHESIS = "CLOSE_PARENTHESIS";
    public static final String DIGIT = "DIGIT";
    public static final String NONE = "NONE";

    public Token() {

    }

    public String identify(char ch) {
        if (isOperator(ch)) return OPERATOR;
        if (isOperand(ch)) return VARIABLE;
        if (isOpenParenthesis(ch)) return OPEN_PARENTHESIS;
        if (isCloseParenthesis(ch)) return CLOSE_PARENTHESIS;
        if(isDigit(ch)) return DIGIT;
        return NONE;
    }

    private boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private boolean isOperand(char ch){
        return ch >= 'a' && ch <= 'z';
    }

    private boolean isOpenParenthesis(char ch){
        return ch == '(' ;
    }

    private boolean isCloseParenthesis(char ch){
        return ch == ')' ;
    }

    private boolean isDigit(char ch){
        return ch >= '0' && ch <= '9';
    }

}