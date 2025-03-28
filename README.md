# Compiler Design

Undergraduate course materials of Compiler Design for the elective course of final year Engineering and IT students.

## 1. Lecture Materials
  Contains all the slides and the examples used in the lecture class.
  
  
  
## 2. Lab Materials
  Contains all the lab-works and their solutions.


```C
/** Thanks to Alfred V. Aho and Jeffrey D. Ullman **/
#include<stdio.h>
int main(){
  printf("Thanks to Alfred V. Aho and Jeffrey D. Ullman.\n");
  return (0);
}
```

## 3. Class Work: Live

A fully implemented parser in Java includes a tokenizer, LL(1) grammar representation, First/Follow set generation, and a parsing table. https://github.com/santabasnet/Compiler-Design/tree/main/work/ExpressionParser 
Its usage:
```Java
import edu.eec.lexer.SymbolTable;
import edu.eec.lexer.Tokenizer;
import edu.eec.grammar.ExpressionGrammar;
import edu.eec.parser.ExpressionParser;

/**
 * This class is a part of the package edu.eec.ccapp and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-19.
 */
public class CCApp {
    /**
     * Program starts from here.
     */
    public static void main(String[] args) {
        /**
         * 1. Tokenize the input expression, build the symbol table and
         *  display the symbol table.
         */
        SymbolTable symbolTable = Tokenizer.ofExpression().tokenizeWith(input());
        System.out.println(symbolTable.presentation());

        /**
         * 2. Parse the generated tokens.
         *    a. Initialize Grammar.
         *    b. Parse It.
         */
        ExpressionGrammar grammar = ExpressionGrammar.init();
        System.out.println(grammar.presentation());

        /**
         * 3. Generate intermediate code.
         *      a. Postfix representation.
         *      b. Prefix representation.
         *      b. Three address codes representation.
         */
        ExpressionParser expressionParser = new ExpressionParser(grammar, symbolTable);
        System.out.println(expressionParser.presentation());

        /**
         * 4. Object code generation, we use stack machine.
         */
        expressionParser.parse();
        System.out.println(expressionParser.getGrammarConstituents().parseTablePresentation());
    }
    /**
     * Input Text.
     */
    private static String input() {
        return "44+    x % y     ( 78 / z ) ^ 2";
    }
}
```
