package edu.eec.parser;

import edu.eec.grammar.*;
import edu.eec.lexer.SymbolTable;
import edu.eec.reportutils.ReportUtils;

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
public class ExpressionParser implements Parser {

    /**
     * Grammar definition.
     */
    private EduGrammar grammar;

    /**
     * Symbol Table.
     */
    private SymbolTable symbolTable;

    /**
     * LL1 Grammar Constituents.
     */
    private LL1Constituents grammarConstituents;


    /**
     * An empty constructor.
     */
    public ExpressionParser() {
        this.grammar = null;
        this.symbolTable = SymbolTable.empty();
        this.grammarConstituents = new LL1Constituents();
    }

    /**
     * An overloaded constructor.
     */
    public ExpressionParser(EduGrammar grammar, SymbolTable symbolTable) {
        this.grammar = grammar;
        this.symbolTable = symbolTable;
        this.grammarConstituents = new LL1Constituents(grammar);
    }


    /**
     * Parse the expression using given grammar.
     */
    @Override
    public ParseTree parse() {
        /**
         * Perform parsing operation.
         */
        //System.out.println(this.grammarConstituents.formattedFirstSets());
        //System.out.println(this.grammarConstituents.formattedFollowSets());

        return null;
    }

    /**
     * Presentation formats for the parser constituents.
     */
    @Override
    public String presentation() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.grammarConstituents.titleFirst());
        buffer.append(ReportUtils.getLineSeparator());
        buffer.append(this.grammarConstituents.formattedFirstSets());
        buffer.append(this.grammarConstituents.titleFollow());
        buffer.append(ReportUtils.getLineSeparator());
        buffer.append(this.grammarConstituents.formattedFollowSets());
        buffer.append(ReportUtils.getLineSeparator());
        return buffer.toString();
    }

    /**
     * Grammar constituents.
     */
    public LL1Constituents getGrammarConstituents() {
        return this.grammarConstituents;
    }

    /**
     * An object factory for chaining.
     */
    public static ExpressionParser empty() {
        return new ExpressionParser();
    }

}
