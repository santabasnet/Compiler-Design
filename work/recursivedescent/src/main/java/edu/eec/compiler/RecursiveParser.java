package edu.eec.compiler;

/**
 * This class is a part of the package edu.eec.compiler and the package
 * is a part of the project recursivedescent.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2023-01-04.
 */

/**
 * Grammar Definition
 * E    -> TE'
 * E'   -> +TE'|ɛ
 * T    -> FT'
 * T'   -> *FT'|ɛ
 * F    -> (E)|id
 */
public class RecursiveParser {

    /**
     * No Input Definition.
     */
    private final char NO_INPUT = '\0';

    /**
     * Input String.
     */
    private final String input;
    private int lookup;

    RecursiveParser(String input) {
        this.input = input;
        this.lookup = 0;
    }

    private int maxLength() {
        return this.input.length();
    }

    void error() {
        System.out.println("Not valid string !");
        System.exit(0);
    }

    char nextInputSymbol() {
        if (this.lookup >= maxLength()) return NO_INPUT;
        else return this.input.charAt(this.lookup);
    }

    void advance() {
        ++this.lookup;
    }

    String E() {
        T();
        EPRIME();
        return "Success";
    }

    String T() {
        F();
        TPRIME();
        return "Success";
    }

    String EPRIME() {
        System.out.println(nextInputSymbol() + "\tEPRIME");
        if (nextInputSymbol() == NO_INPUT) return "Success";
        else if (nextInputSymbol() == '+') {
            advance();
            T();
            EPRIME();
        } else error();
        return "Success";
    }

    String F() {
        System.out.println(nextInputSymbol() + "\tF");
        if (nextInputSymbol() == 'd') advance();
        else if (nextInputSymbol() == '(') {
            advance();
            E();
            if (nextInputSymbol() != ')') {
                advance();
                error();
            }
        }
        return "Success";
    }

    String TPRIME() {
        System.out.println(nextInputSymbol() + "\tTPRIME");
        if (nextInputSymbol() == NO_INPUT) return "Success";
        else if (nextInputSymbol() == '*') {
            advance();
            F();
            TPRIME();
        }
        return "Success";
    }

    public static void main(String[] args) {
        String input = "d+d*d";
        RecursiveParser parser = new RecursiveParser(input);
        String result = parser.E();
        System.out.println(result);
    }
}
