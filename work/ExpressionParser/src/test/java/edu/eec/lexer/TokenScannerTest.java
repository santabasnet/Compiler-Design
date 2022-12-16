package edu.eec.lexer;

/**
 * This class is a part of the package edu.eec.lexer and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-21.
 */
public class TokenScannerTest {
    public static void main(String[] args) {

        SymbolTable symbolTable = Tokenizer.ofExpression().tokenizeWith(input());
        //symbolTable.allSymbols().forEach(System.out::println);
        System.out.println(symbolTable.presentation());

    }

    /**
     * Input Text.
     */
    private static String input() {
        return "44+    x % y ( 78 / z ) ^ 2";
    }

}
