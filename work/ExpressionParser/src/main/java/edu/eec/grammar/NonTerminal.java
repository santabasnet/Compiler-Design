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
public class NonTerminal implements GrammarSymbol {

    /**
     * Default variable.
     */
    private String name;

    /**
     * Default Constructor.
     */
    public NonTerminal() {
        this.name = LexicalLiterals.EMPTY_STRING;
    }

    /**
     * Overloaded Constructor.
     */
    public NonTerminal(String name) {
        this.name = name;
    }

    /**
     * Get name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Check if the symbol is of Epsilon type or not.
     */
    @Override
    public boolean isEpsilon() {
        return false;
    }

    /**
     * Build with given name.
     */
    public NonTerminal buildWithName(String name) {
        return new NonTerminal(name);
    }

    /**
     * Empty Instance for the NonTerminal.
     */
    public static NonTerminal empty() {
        return new NonTerminal();
    }

    @Override
    public String presentation() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        return (this.name.equals(((NonTerminal) (obj)).getName()));
    }

    @Override
    public String toString() {
        return "NonTerminal { name = [" + name + "] }";
    }
}
