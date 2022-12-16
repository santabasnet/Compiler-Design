package edu.eec.dfa;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is a part of the package edu.eec.dfa and the package
 * is a part of the project dfa.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-12-14.
 */
public class DFAApp {

    private static String input = "aaab";
    private static int index = 0;

    private static char scan() {
        if (index >= input.length()) return '\0';
        else return input.charAt(index++);
    }

    /**
     * Input alphabets.
     */
    private static Set<Character> alphabets = initializeAlphabets();

    /**
     * All states.
     */
    private static Set<Integer> states = initializeStates();

    /**
     * All final states.
     */
    private static Set<Integer> finalStates = initializeFinalStates();

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nAlphabets: " + alphabets);
        System.out.println("\nStates: " + states);

        char ch = scan();
        int state = 0;
        while (ch != '\0') {

            if (!alphabets.contains(ch)) {
                inputError(ch);
                state = 10;
            } else {

                switch (state) {
                    case 0:
                        if (ch == 'a') state = 1;
                        if (ch == 'b') state = 2;
                        break;
                    case 1:
                        if (ch == 'b') state = 2;
                        if (ch == 'a') state = 1;
                        break;

                    case 2:
                        if (ch == 'b' || ch == 'a') state = 10;
                        break;

                    default:
                        break;
                }
            }
            ch = scan();
        }

        if (isFinalState(state)) {
            System.out.println("The string is accepted !");
        } else
            System.out.println("The string is rejected !");

    }

    private static boolean isFinalState(int state) {
        return finalStates.contains(state);
    }

    /**
     * Initialize input alphabets.
     */
    private static Set<Character> initializeAlphabets() {
        Set<Character> inputs = new HashSet<>();
        inputs.add('a');
        inputs.add('b');
        return inputs;
    }

    /**
     * Initialize States.
     */
    private static Set<Integer> initializeStates() {
        Set<Integer> states = new HashSet<>();
        states.add(0);
        states.add(1);
        states.add(2);
        return states;
    }

    /**
     * Initialize States.
     */
    private static Set<Integer> initializeFinalStates() {
        Set<Integer> states = new HashSet<>();
        states.add(1);
        states.add(2);
        return states;
    }

    private static void inputError(char ch) {
        System.out.println("\nNot defined input: " + ch);
    }
}

