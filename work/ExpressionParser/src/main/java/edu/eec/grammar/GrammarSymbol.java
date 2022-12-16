package edu.eec.grammar;

import edu.eec.lexer.LexicalLiterals;

import java.util.List;

/**
 * This class is a part of the package edu.eec.parser and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-24.
 */
public interface GrammarSymbol {
    /**
     * Represents the string form of GrammarSymbol.
     *
     * @return grammarSymbol
     */
    String presentation();

    /**
     * Get Name.
     */
    String getName();

    /**
     * Check if the symbol is of Epsilon type or not.
     */
    boolean isEpsilon();

}
