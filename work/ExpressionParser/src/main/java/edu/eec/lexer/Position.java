package edu.eec.lexer;

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
public class Position {

    /**
     * Position begin at the input.
     */
    private final int begin;

    /**
     * Token value length.
     */
    private final int end;

    /**
     * Default Constructor.
     */
    public Position() {
        this.begin = -1;
        this.end = 0;
    }

    /**
     * Overloaded Constructor.
     */
    public Position(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public int getBegin() {
        return this.begin;
    }

    public int getEnd() {
        return this.end;
    }

    public static Position empty() {
        return new Position();
    }

    /**
     * With begin value.
     */
    public Position withEnd(int end) {
        return new Position(this.begin, end);
    }

    /**
     * With begin value.
     */
    public Position withBegin(int begin) {
        return new Position(begin, this.end);
    }

    /**
     * String representation of the location instance.
     *
     * @return strLocation
     */
    @Override
    public String toString() {
        return "Position{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }
}
