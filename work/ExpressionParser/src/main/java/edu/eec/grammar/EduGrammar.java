package edu.eec.grammar;

import java.util.List;
import java.util.Map;

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
public interface EduGrammar {
    /**
     * Presentation view of the Grammar representation.
     *
     * @return string representation of the presentation view.
     */
    String presentation();

    /**
     * ProductionRule initialization.
     */
    List<ProductionRule> initializeRules();

    /**
     * Returns all the rules.
     *
     * @return listOfRules.
     */
    List<ProductionRule> allRules();

    /**
     * Returns all the Non-Terminals.
     */
    List<GrammarSymbol> allNonTerminals();

    /**
     * Returns all the Non-Terminals.
     */
    List<GrammarSymbol> allTerminals();

    /**
     * Check if the grammar is of empty instance or not.
     */
    boolean isEmpty();

    /**
     * Grouped Production rule by their NTs.
     */
    Map<GrammarSymbol, List<ProductionRule>> ntProductionRules();

    /**
     * The first non-terminal.
     */
    GrammarSymbol firstNonTerminal();
}
