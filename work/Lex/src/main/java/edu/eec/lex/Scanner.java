package edu.eec.lex;

/**
 * This class is a part of the package edu.eec.lex and the package
 * is a part of the project Lex.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-12-09.
 */
public class Scanner {
    /**
     * Input.
     */
    final char[] expr;
    int index;

    /**
     * Default constructor.
     */
    public Scanner(){
        this.expr = "x+y*(z/w)".toCharArray();
        reset();
    }

    public void reset(){
        this.index = 0;
    }

    /**
     * Overloaded Constructor with input.
     */
    public Scanner(String input){
        this.expr = input.toCharArray();
        reset();
    }

    /**
     * Next token.
     */
    public char nextToken(){
        if(this.index < this.expr.length) return this.expr[index++];
        else return LexLiterals.NO_INPUT;
    }

}
