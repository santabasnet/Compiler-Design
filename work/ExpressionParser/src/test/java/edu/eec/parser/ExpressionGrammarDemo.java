package edu.eec.parser;

import edu.eec.grammar.ExpressionGrammar;

/**
 * This class is a part of the package edu.eec.parser and the package
 * is a part of the project ExpressionParser.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-25.
 */
public class ExpressionGrammarDemo {

    public static void main(String[] args) {
        ExpressionGrammar grammar = ExpressionGrammar.init();
        System.out.println(grammar.presentation());
    }

}
