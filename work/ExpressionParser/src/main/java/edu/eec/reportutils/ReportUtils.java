package edu.eec.reportutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class is a part of the package edu.eec.reportutils and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-21.
 */
public class ReportUtils {

    /**
     * Json object for pretty printing.
     */
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Line separator.
     */
    public static String getLineSeparator(){
        return "-------------------------------------------------------------------------\n";
    }

}
