package edu.eec.lexer;

import edu.eec.reportutils.ReportUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is a part of the package edu.eec.lexer and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-19.
 */
public class SymbolTable {

    /**
     * All the token types and their definition.
     */
    private final ArrayList<Token> items;

    /**
     * Default Constructor.
     */
    public SymbolTable() {
        this.items = new ArrayList<>();
    }

    /**
     * Overloaded Constructor.
     */
    public SymbolTable(ArrayList<Token> items) {
        this.items = items;
    }

    /**
     * Append token item in the symbol table.
     */
    public void appendItem(Token item) {
        this.items.add(item);
    }

    /**
     * List all the variables from the symbol table.
     */
    public List<Token> allVariables() {
        return this.items.stream()
                .filter(item -> item.getType() == TokenTypes.VARIABLE)
                .collect(Collectors.toList());
    }

    /**
     * List all the items, both constant and variable are served here.
     */
    public ArrayList<Token> allSymbols() {
        return this.items;
    }

    /**
     * Set value for the variable.
     */
    public void setValueOf(String name, String value) {
        for (Token token : this.items) {
            if (token.getType() == TokenTypes.VARIABLE && token.getTokenName().equals(name)) {
                token.setValue(value);
            }
        }
    }

    /**
     * Serialize to table for display purpose.
     */
    public String presentation() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(ReportUtils.getLineSeparator());
        buffer.append(Token.getTitle());
        buffer.append(ReportUtils.getLineSeparator());
        buffer.append(Token.getHeader());
        buffer.append(ReportUtils.getLineSeparator());
        IntStream.range(0, this.items.size())
                .forEach(index -> buffer.append(items.get(index).getLine(index + 1)));
        buffer.append(ReportUtils.getLineSeparator());
        return buffer.toString();
    }

    /**
     * Json Representation of the symbol table.
     */
    @Override
    public String toString() {
        return ReportUtils.gson.toJson(this);
    }

    /**
     * Returns empty symbol table.
     */
    public static SymbolTable empty() {
        return new SymbolTable();
    }
}
