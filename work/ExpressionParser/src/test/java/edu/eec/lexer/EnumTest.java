package edu.eec.lexer;

import java.util.Arrays;
import java.util.stream.Collectors;

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
public class EnumTest {

    public static void main(String[] args){
        System.out.println(Arrays.stream(TokenTypes.values()).collect(Collectors.toList()));
        System.out.println(String.format("%s",TokenTypes.EXPONENT));
    }

}
