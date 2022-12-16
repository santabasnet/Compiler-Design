package edu.eec.lexer;

import java.util.ArrayList;
import java.util.List;

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
public class Tokenizer {

    /**
     * Stores all the available tokens.
     */
    private final List<Token> tokens;

    /**
     * Default overloaded object with empty set of tokens.
     */
    public Tokenizer() {
        this.tokens = new ArrayList<>();
    }

    /**
     * Perform tokenization here.
     */
    public SymbolTable tokenizeWith(String input) {
        /**
         * Initialize Symbol Table.
         */
        SymbolTable symbolTable = SymbolTable.empty();
        /**
         * Accumulate all the tokens.
         */
        TokenScanner scanner = TokenScanner.of(input);
        Token token = scanner.nextToken();
        while(token.nonEmpty()){
            symbolTable.appendItem(token);
            this.tokens.add(token);
            token = scanner.nextToken();
        }
        return symbolTable;
    }

    /**
     * Returns the list of tokens.
     */
    public List<Token> getTokens() {
        return this.tokens;
    }

    /**
     * Of Expression, a factory utility.
     */
    public static Tokenizer ofExpression(){
        return new Tokenizer();
    }

}
