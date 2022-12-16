package edu.eec.grammar;

import edu.eec.parser.ParseMessages;
import edu.eec.parser.ParseViolation;
import edu.eec.reportutils.ReportUtils;

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
public class ParseTable {

    /**
     * Parsing table storage.
     */
    Map<LL1Entry, List<ProductionRule>> tableEntry;

    /**
     * LL1 Constituents.
     */
    private LL1Constituents constituents;

    /**
     * Default constructor.
     */
    public ParseTable() {
        this.constituents = new LL1Constituents();
    }

    /**
     * Overloaded with LL1 Constituents.
     */
    public ParseTable(LL1Constituents constituents) {
        this.constituents = constituents;
        this.tableEntry = new HashMap<>();
    }

    /**
     * Build table here.
     * <p>
     * For each production A -> w, the production to use on seeing the pair
     * (A, t) for any terminal t in FIRST(A) is w.  That is, if we want to
     * match a t, we should do so by expanding out A -> w, then matching
     * the t with the first character of w.
     * <p>
     * If FIRST(A) contains epsilon, then for each t in FOLLOW(A), the
     * production to use on seeing (A, t) is w.  That is, if A is nullable,
     * and we see a symbol that should come after A, the best option is to
     * expand A to w and then expand everything in w to the empty string.
     * <p>
     * If any conflicts arise in this process, we report an error because
     * the grammar is not LL(1).
     */
    public ParseTable build() {
        /**
         * Convert the terminal and non-terminal with grammar symbol.
         */
        for (ProductionRule rule : this.constituents.getGrammar().allRules()) {
            /**
             * 1. Collect first symbols from the given Non-Terminal.
             */
            Set<GrammarSymbol> firstOf = this.constituents.firstSetOf(rule.getLeftPart());
            /**
             * 2. Filter and make table entry for the all first set terminals.
             */
            firstOf.stream().filter(symbol -> !symbol.isEpsilon()).forEach(terminal -> {
                makeTableEntries(rule, terminal);
            });

            /**
             * 3. Check condition for the empty/epsilon production associated with the non-terminal.
             */
            if (firstOf.stream().anyMatch(GrammarSymbol::isEpsilon)) {
                for (GrammarSymbol tSymbol : this.constituents.followSetOf(rule.getLeftPart())) {
                    makeTableEntries(rule, tSymbol);
                }
            }
        }
        return this;
    }

    /**
     * Add Entry to the parsing table.
     */
    private void makeTableEntries(ProductionRule rule, GrammarSymbol terminal) {
        /**
         * Add the entry to the parsing table.
         */
        LL1Entry entry = new LL1Entry(rule.getLeftPart(), terminal);
        List<ProductionRule> rules = this.tableEntry.getOrDefault(entry, new ArrayList<>());

        /**
         * Check if the rule is already present in the table.
         */
        if(rules.stream().anyMatch(item -> item.equals(rule))){
            new ParseViolation(ParseMessages.tableEntryError(rule)).printStackTrace();
        }else {
            rules.add(rule);
            this.tableEntry.put(entry, rules);
        }
    }

    /**
     * Generate all the table entry points, this involves the building key for
     * the table entry which combines non-terminal and terminal.
     */
    private List<LL1Entry> tableEntries() {
        List<LL1Entry> allEntries = new ArrayList<>();
        for (GrammarSymbol nonTerminal : this.constituents.getGrammar().allNonTerminals()) {
            for (GrammarSymbol terminal : this.constituents.getGrammar().allTerminals()) {
                allEntries.add(new LL1Entry(nonTerminal, terminal));
            }
        }
        return allEntries;
    }

    /**
     * Parse table title.
     */
    private String parseTableTitle() {
        return "Parse Table Entries: \n";
    }

    /**
     * Returns the parse table.
     */
    public Map<LL1Entry, List<ProductionRule>> parseTableEntries() {
        return this.tableEntry;
    }

    /**
     * Returns the presentation: formatted table entries.
     */
    public String presentation() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(parseTableTitle());
        buffer.append(ReportUtils.getLineSeparator());

        //buffer.append(ReportUtils.gson.toJson(this.tableEntry));

        this.tableEntry.forEach((ll1Entry, rules) -> {
            buffer.append("\t");
            buffer.append(ll1Entry.presentation());
            buffer.append(" -> ");
            buffer.append(rules.stream().map(ProductionRule::presentation).collect(Collectors.joining(", ")));
            buffer.append("\n");
        });

        buffer.append("\n");
        buffer.append(ReportUtils.getLineSeparator());
        return buffer.toString();
    }

    /**
     * An empty instance.
     */
    public static ParseTable empty() {
        return new ParseTable();
    }

}
