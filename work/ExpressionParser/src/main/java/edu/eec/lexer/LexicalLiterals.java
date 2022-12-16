package edu.eec.lexer;

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
public class LexicalLiterals {

    /**
     * Empty String.
     */
    public static final String EMPTY_STRING = "";

    /**
     * Exponent Symbol.
     */
    public static final char EXPONENT_CHARACTER = '^';

    /**
     * Division Symbol.
     */
    public static final char DIVISION_CHARACTER = '/';

    /**
     * Period Symbol.
     */
    public static final char PERIOD_CHARACTER = '%';

    /**
     * Multiplication Symbol.
     */
    public static final char MULTIPLICATION_CHARACTER = '*';

    /**
     * Addition Symbol.
     */
    public static final char ADDITION_CHARACTER = '+';

    /**
     * Subtraction Symbol.
     */
    public static final char SUBTRACTION_CHARACTER = '-';

    /**
     * Open Parenthesis Symbol.
     */
    public static final char OPEN_PARENTHESIS_CHARACTER = '(';

    /**
     * Close Parenthesis Symbol.
     */
    public static final char CLOSE_PARENTHESIS_CHARACTER = ')';

    /**
     * Addition String
     */
    public static String addition() {
        return Character.toString(ADDITION_CHARACTER);
    }

    /**
     * Subtraction String
     */
    public static String subtraction() {
        return Character.toString(SUBTRACTION_CHARACTER);
    }

    /**
     * Multiplication String.
     */
    public static String multiplication() {
        return Character.toString(MULTIPLICATION_CHARACTER);
    }

    /**
     * Division String.
     */
    public static String division() {
        return Character.toString(DIVISION_CHARACTER);
    }

    /**
     * Period String.
     */
    public static String period() {
        return Character.toString(PERIOD_CHARACTER);
    }

    /**
     * Exponent String.
     */
    public static String exponent() {
        return Character.toString(EXPONENT_CHARACTER);
    }

    /**
     * Open Parenthesis String.
     */
    public static String openParenthesis() {
        return Character.toString(OPEN_PARENTHESIS_CHARACTER);
    }

    /**
     * Close Parenthesis String.
     */
    public static String closeParenthesis() {
        return Character.toString(CLOSE_PARENTHESIS_CHARACTER);
    }

    /**
     * Build character string of the integer number.
     */
    public static String charStringOf(int code){
        return String.valueOf((char)code);
    }

}
