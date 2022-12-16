package edu.eec.grammar;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is a part of the package edu.eec.grammar and the package
 * is a part of the project ExpressionParser.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-28.
 */
public class LL1Constituents {

    /**
     * A given grammar to be used for the parsing.
     */
    private final EduGrammar grammar;

    /**
     * The first set.
     */
    private Map<GrammarSymbol, Set<GrammarSymbol>> firstSetOfNTs;

    /**
     * The follow set.
     */
    private Map<GrammarSymbol, Set<GrammarSymbol>> followSetOfNTs;

    /**
     * The parsing table.
     */
    private ParseTable parseTable;

    /**
     * Grouped production rules.
     */
    private Map<GrammarSymbol, List<ProductionRule>> groupedProductionRules;

    public LL1Constituents() {
        this.grammar = null;
    }

    /**
     * Default constructor.
     */
    public LL1Constituents(EduGrammar grammar) {
        this.grammar = grammar;
        initGrammarConstituents();
    }

    /**
     * Initialize grammar constituents.
     */
    private void initGrammarConstituents() {
        /**
         * 1. Calculate Set of  First Symbols.
         */
        this.firstSetOfNTs = initializeFirstSymbols();
        /**
         * 2. Calculate Set of Follow Symbols.
         */
        this.followSetOfNTs = initializeFollowSymbols(this.firstSetOfNTs);
        /**
         * 3. Generate Parsing Table.
         */
        this.parseTable = new ParseTable(this).build();
    }

    /**
     * Calculate the first set for all the Non-Terminal.
     * 1. If x -> Є, is a production rule, then add ε to FIRST(x).
     * 2. If x is a terminal, then FIRST(x) = {‘x’}. The computation stops with that terminal added
     * to the result of first set.
     * 3. If X -> Y1 Y2 Y3….Yn is a production rule then,
     * 4. FIRST(X) = FIRST(Y1)
     * 5. If FIRST(Y1) contains ε then FIRST(X) = { FIRST(Y1) – ε } U { FIRST(Y2) }
     * 6. If FIRST (Yi) contains ε for all i = 1 to n, then add ε to FIRST(X).
     */
    Map<GrammarSymbol, Set<GrammarSymbol>> initializeFirstSymbols() {
        /**
         * 1. Check if the grammar is empty, no computation needed.
         */
        if (this.grammar.isEmpty()) return new HashMap<>();

        /* 2. Create a map store all the first sets of NTs. */
        Map<GrammarSymbol, Set<GrammarSymbol>> firstSet = this.grammar.allNonTerminals().stream()
                .collect(Collectors.toMap(symbol -> symbol, symbol -> new HashSet<>()));

        for (boolean shouldContinue = true; shouldContinue; ) {
            shouldContinue = false;
            for (ProductionRule productionRule : this.grammar.allRules()) {
                Set<GrammarSymbol> computedFirst = firstFromProductions(productionRule.getRightPart(), firstSet);
                if (firstSet.get(productionRule.getLeftPart()).addAll(computedFirst))
                    shouldContinue = true;
            }
        }

        return firstSet;
    }

    /**
     * Initialize the FOLLOW sets for the NonTerminals.
     * Initially, put $ (the end of input marker) in Follow(S) (S is the start symbol)
     * If there is a production A -> aBb, then everything in FIRST(b) except for ε is placed in FOLLOW(B).
     * If there is a production A -> aB, then everything in FOLLOW(A) is in FOLLOW(B)
     * If there is a production A → aBb, where FIRST(b) contains ε, then everything in FOLLOW(A) is in FOLLOW(B)
     */
    Map<GrammarSymbol, Set<GrammarSymbol>> initializeFollowSymbols(Map<GrammarSymbol, Set<GrammarSymbol>> firstSet) {
        /**
         * 3. Create a map store for all the follow sets of NTs.
         */
        Map<GrammarSymbol, Set<GrammarSymbol>> followSet = this.grammar.allNonTerminals().stream()
                .collect(Collectors.toMap(symbol -> symbol, symbol -> new HashSet<>()));

        boolean shouldContinue;
        do {
            shouldContinue = false;

            for (ProductionRule productionRule : this.grammar.allRules()) {
                List<GrammarSymbol> derivation = productionRule.getRightPart();

                for (int i = 0; i < derivation.size(); ++i) {
                    GrammarSymbol currentSymbol = derivation.get(i);
                    if (currentSymbol instanceof Terminal) continue;

                    /* Get the FIRST set of the remainder of the derivation. */
                    Set<GrammarSymbol> startOfRest = firstFromProductions(derivation.subList(i + 1, derivation.size()), firstSet);

                    for (GrammarSymbol symbol : startOfRest) {
                        if (symbol.isEpsilon()) continue;
                        if (followSet.get(currentSymbol).add(symbol))
                            shouldContinue = true;
                    }

                    if (hasEmptyProduction(startOfRest)) {
                        if (followSet.get(currentSymbol).addAll(followSet.get(productionRule.getLeftPart())))
                            shouldContinue = true;
                    }
                }
            }

        } while (shouldContinue);

        return followSet;
    }

    /**
     * Verify if the final accumulated FIRST set has epsilon for the given Non-terminal or
     * not.
     */
    private boolean hasEmptyProduction(Set<GrammarSymbol> symbols) {
        return symbols.stream().anyMatch(GrammarSymbol::isEpsilon);
    }

    /**
     * First computation for a given production rule.
     */
    private Set<GrammarSymbol> firstFromProductions(List<GrammarSymbol> allSymbols, Map<GrammarSymbol, Set<GrammarSymbol>> fSet) {
        Set<GrammarSymbol> result = new HashSet<>();
        for (GrammarSymbol grammarSymbol : allSymbols) {
            /* If it's a terminal, we're done and return the result. */
            if (grammarSymbol instanceof Terminal) {
                result.add(grammarSymbol);
                return result;
            }
            /* If the FIRST set for this nonterminal does not contain empty production,
             * add everything from its FIRST set to the result set and stop.
             */
            Set<GrammarSymbol> ntSet = fSet.getOrDefault(grammarSymbol, new HashSet<>());
            if (!hasEmptyProduction(ntSet)) {
                result.addAll(fSet.get(grammarSymbol));
                return result;
            }

            /* Otherwise, it does contain epsilon.  Add everything in from that
             * set, and take out the epsilon at the end.
             */
            result.addAll(fSet.get(grammarSymbol));
            result = result.stream().filter(symbol -> !symbol.isEpsilon()).collect(Collectors.toSet());
        }
        //Till here, we need to add empty as the FIRST symbol.
        result.add(Terminal.emptyDerivation());
        return result;
    }

    /**
     * All first set.
     */
    public Map<GrammarSymbol, Set<GrammarSymbol>> firstSet() {
        return this.firstSetOfNTs;
    }

    /**
     * First set associated with given non-terminal.
     */
    public Set<GrammarSymbol> firstSetOf(GrammarSymbol symbol) {
        return this.firstSet().getOrDefault(symbol, new HashSet<>());
    }

    /**
     * Formatted First Set.
     */
    public String formattedFirstSets() {
        return formatMapOfSymbols(this.firstSetOfNTs);
    }

    /**
     * All follow set.
     */
    public Map<GrammarSymbol, Set<GrammarSymbol>> followSet() {
        return this.followSetOfNTs;
    }

    /**
     * Follow set associated with the given non-terminal.
     */
    public Set<GrammarSymbol> followSetOf(GrammarSymbol symbol) {
        return this.followSet().getOrDefault(symbol, new HashSet<>());
    }

    /**
     * Formatted Follow Set.
     */
    public String formattedFollowSets() {
        return formatMapOfSymbols(this.followSetOfNTs);
    }

    /**
     * Format Grammar Symbol of hash map.
     */
    private String formatMapOfSymbols(Map<GrammarSymbol, Set<GrammarSymbol>> symbols) {
        StringBuffer buffer = new StringBuffer();
        symbols.forEach((symbol, fSet) -> {
            buffer.append(String.format("%4s", symbol.getName()));
            buffer.append(" --> {");
            buffer.append(fSet.stream().map(GrammarSymbol::getName).collect(Collectors.joining(", ")));
            buffer.append("}\n");
        });
        return buffer.toString();
    }

    /**
     * Constituents title.
     */
    public String titleFirst() {
        return "FIRST sets: \n";
    }

    /**
     * Constituents title.
     */
    public String titleFollow() {
        return "FOLLOW sets: \n";
    }

    /**
     * Returns the grammar.
     */
    public EduGrammar getGrammar() {
        return this.grammar;
    }

    /**
     * Parse table.
     */
    public String parseTablePresentation() {
        return this.parseTable.presentation();
    }

}
