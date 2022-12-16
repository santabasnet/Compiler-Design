package edu.eec.grammar;

import edu.eec.lexer.LexicalLiterals;

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
public class Terminal implements GrammarSymbol {
    /**
     * Default variable.
     */
    private final String name;

    /**
     * Default Constructor.
     */
    public Terminal() {
        this.name = LexicalLiterals.EMPTY_STRING;
    }

    /**
     * Overloaded Constructor.
     */
    public Terminal(String name) {
        this.name = name;
    }

    /**
     * Get name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Check if the symbol is of Epsilon type or not.
     */
    @Override
    public boolean isEpsilon() {
        return this.name.equals(GrammarLiterals.EPSILON);
    }

    /**
     * Build with given name.
     */
    public Terminal buildWithName(String name) {
        return new Terminal(name);
    }

    /**
     * Empty Instance for the NonTerminal.
     */
    public static Terminal empty() {
        return new Terminal();
    }

    /**
     * Build an empty production rule.
     */
    public static Terminal emptyDerivation() {
        return Terminal.empty().buildWithName(GrammarLiterals.EPSILON);
    }

    /**
     * Represents the string form of GrammarSymbol.
     *
     * @return grammarSymbol
     */
    @Override
    public String presentation() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (this.name.equals(((Terminal) (obj)).getName()));
    }

    @Override
    public String toString() {
        return "Terminal { name = [" + name + "] }";
    }

}
