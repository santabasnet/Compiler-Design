package edu.eec.parser;

import edu.eec.grammar.ProductionRule;

/**
 * This class is a part of the package edu.eec.parser and the package
 * is a part of the project ExpressionParser.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-12-01.
 */
public class ParseMessages {

    /**
     * Duplicate entry.
     */

    public static String tableEntryError(ProductionRule rule) {
        return String
                .format("Duplicate entry in the LL(1) parsing table for %s. Unable to make entry.", rule.textMessage());
    }

}
