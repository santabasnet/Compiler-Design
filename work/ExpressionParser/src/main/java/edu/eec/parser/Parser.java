package edu.eec.parser;

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
public interface Parser {

    /**
     * Perform Parse operation here.
     */
    ParseTree parse();

    /**
     * Presentation function, serializes the human-readable text.
     */
    String presentation();
}
