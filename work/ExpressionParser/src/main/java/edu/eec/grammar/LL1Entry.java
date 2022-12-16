package edu.eec.grammar;

/**
 * This class is a part of the package edu.eec.grammar and the package
 * is a part of the project ExpressionParser.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-29.
 */
public class LL1Entry {
    private final GrammarSymbol nonTerminal;
    private final GrammarSymbol terminal;

    public LL1Entry(GrammarSymbol nonTerminal, GrammarSymbol terminal) {
        this.nonTerminal = nonTerminal;
        this.terminal = terminal;
    }

    /**
     * Returns the Non-Terminal.
     */
    public GrammarSymbol getNonTerminal() {
        return this.nonTerminal;
    }

    /**
     * Returns the Terminal.
     */
    public GrammarSymbol getTerminal() {
        return this.terminal;
    }

    /**
     * Compares the object.
     *
     * @param o
     * @return true for given condition to be satisfied.
     */
    @Override
    public boolean equals(Object o) {
        LL1Entry entry = (LL1Entry) o;
        return this.terminal.equals(entry.terminal) &&
                this.nonTerminal.equals(entry.nonTerminal);
    }

    @Override
    public int hashCode() {
        return 31 * this.nonTerminal.hashCode() + this.terminal.hashCode();
    }

    @Override
    public String toString() {
        return "LL1Entry{" +
                "nonTerminal=" + nonTerminal +
                ", terminal=" + terminal +
                '}';
    }

    /**
     * Check if the terminal symbol is of empty derivation or not.
     */
    public boolean hasEmptyDerivation() {
        return this.terminal.isEpsilon();
    }

    /**
     * Presentation.
     */
    public String presentation() {
        return "[" + this.nonTerminal.getName() + ", " + this.terminal.getName() + "]";
    }
}
