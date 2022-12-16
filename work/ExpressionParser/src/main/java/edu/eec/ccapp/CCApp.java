package edu.eec.ccapp;

import edu.eec.lexer.SymbolTable;
import edu.eec.lexer.Tokenizer;
import edu.eec.grammar.ExpressionGrammar;
import edu.eec.parser.ExpressionParser;

/**
 * This class is a part of the package edu.eec.ccapp and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-19.
 */
public class CCApp {

    /**
     * Program starts from here.
     */
    public static void main(String[] args) {

        /**
         * 1. Tokenize the input expression, build the symbol table and
         *  display the symbol table.
         */
        SymbolTable symbolTable = Tokenizer.ofExpression().tokenizeWith(input());
        System.out.println(symbolTable.presentation());

        /**
         * 2. Parse the generated tokens.
         *    a. Initialize Grammar.
         *    b. Parse It.
         */
        ExpressionGrammar grammar = ExpressionGrammar.init();
        System.out.println(grammar.presentation());

        /**
         * 3. Generate intermediate code.
         *      a. Postfix representation.
         *      b. Prefix representation.
         *      b. Three address codes representation.
         */
        ExpressionParser expressionParser = new ExpressionParser(grammar, symbolTable);
        System.out.println(expressionParser.presentation());

        /**
         * 4. Object code generation, we use stack machine.
         */
        expressionParser.parse();
        System.out.println(expressionParser.getGrammarConstituents().parseTablePresentation());
    }

    /**
     * Input Text.
     */
    private static String input() {
        return "44+    x % y     ( 78 / z ) ^ 2";
    }

}
