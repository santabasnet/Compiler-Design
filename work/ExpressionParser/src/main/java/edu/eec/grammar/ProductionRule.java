package edu.eec.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a part of the package edu.eec.parser and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-24.
 * <p>
 * Grammar definition.
 * E  -> T E'
 * <p>
 * E' -> + T E' | - T E' | epsilon
 * T  -> F T'
 * <p>
 * T' -> * F T' | / F T' | epsilon
 * F  -> U V'
 * <p>
 * V' -> ^ U V' | % U V' | epsilon
 * U  -> (E) | id | constant
 */
public class ProductionRule {
    /**
     * Left part.
     * A context free grammar (cfg) has single Non-Terminal at left side.
     */
    private final GrammarSymbol leftPart;

    /**
     * Right Part.
     */
    private final List<GrammarSymbol> rightPart;

    /**
     * Default Constructor.
     */
    public ProductionRule() {
        this.leftPart = NonTerminal.empty();
        this.rightPart = new ArrayList<>();
    }

    /**
     * Overloaded Constructor.
     */
    public ProductionRule(GrammarSymbol leftPart, List<GrammarSymbol> rightPart) {
        this.leftPart = leftPart;
        this.rightPart = rightPart;
    }

    /**
     * Get left part of the rule.
     */
    public GrammarSymbol getLeftPart() {
        return this.leftPart;
    }

    /**
     * Get right part of the rule.
     */
    public List<GrammarSymbol> getRightPart() {
        return this.rightPart;
    }

    /**
     * Get Right Symbols.
     */
    public String rightPartSymbols() {
        return this.getRightPart().stream().map(GrammarSymbol::getName).collect(Collectors.joining(" "));
    }

    /**
     * Build with NonTerminal.
     */
    public ProductionRule withLeftPart(GrammarSymbol nonTerminal) {
        return new ProductionRule(nonTerminal, this.rightPart);
    }

    /**
     * Build with right part, i.e. the collection of terminal and the non-terminal.
     */
    public ProductionRule withRightPart(List<GrammarSymbol> rightPart) {
        return new ProductionRule(this.leftPart, rightPart);
    }

    /**
     * String representation for presentation of the ProductionRule.
     */
    public String presentation() {
        String rightStr = rightPart.stream().map(GrammarSymbol::presentation).collect(Collectors.joining(" "));
        return String.format("%4s -> %10s", leftPart.presentation(), rightStr);
    }

    /**
     * An empty instance, for factory purpose.
     */
    public static ProductionRule empty() {
        return new ProductionRule();
    }

    /**
     * Get Title for the rules.
     */
    public static String getTitle() {
        return "Grammar Rules: \n";
    }

    /**
     * Checks if the production rule has an empty production or not.
     */
    public boolean hasEpsilonProduction() {
        return this.getRightPart().stream().anyMatch(GrammarSymbol::isEpsilon);
    }

    /**
     * Text Message.
     */
    public String textMessage() {
        return presentation();
    }

    @Override
    public boolean equals(Object other) {
        ProductionRule otherRule = (ProductionRule) other;
        return otherRule.leftPart.getName().equals(this.leftPart.getName())
                && otherRule.rightPartSymbols().equals(this.rightPartSymbols());
    }


}
