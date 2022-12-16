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
public class LexApp {

    private static final String customExpr = "x+7+y";

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(customExpr);
        char ch = scanner.nextToken();
        while(ch != LexLiterals.NO_INPUT){
            Token token = new Token();
            System.out.println(ch + " >> " + token.identify(ch));
            ch = scanner.nextToken();
        }
    }


}
