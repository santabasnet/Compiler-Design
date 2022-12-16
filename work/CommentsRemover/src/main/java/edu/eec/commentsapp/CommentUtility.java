package edu.eec.commentsapp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a part of the package edu.eec.commentsapp and the package
 * is a part of the project CommentsRemover.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-12-16.
 *
 * State Definition:
 *  1.  delta(1, /)      => 2
 *  2.  delta(1, else)   => 6
 *  3.  delta(2, else)   => 6
 *  4.  delta(2, *)      => 3
 *  5.  delta(3, else)   => 3
 *  6.  delta(3, *)      => 4
 *  7.  delta(4, /)      => 6
 *  8.  delta(4, else)   => 3
 *  9.  delta(6, else)   => 6
 *  10. delta(6, /)      => 2
 *
 */
public class CommentUtility {

    private String source;

    public CommentUtility(String source) {
        this.source = source;
    }

    public List<String> comments() {
        List<String> comments = new ArrayList<>();
        int index = 0;
        int state = 1;
        String accumulator = "";
        while (index < this.source.length()) {
            char ch = this.source.charAt(index);
            switch (state) {
                case 1:
                    if (ch == '/') {
                        accumulator = "/";
                        state = 2;
                    } else {
                        accumulator = "";
                        state = 6;
                    }
                    break;
                case 2:
                    if (ch == '*') {
                        accumulator += ch;
                        state = 3;
                    } else {
                        accumulator = "";
                        state = 6;
                    }
                    break;
                case 3:
                    if (ch == '*') state = 4;
                    accumulator += ch;
                    break;
                case 4:
                    if (ch == '/') {
                        state = 6;
                        accumulator += ch;
                        if (accumulator.length() > 0) {
                            comments.add(accumulator);
                            accumulator = "";
                        }
                    } else {
                        state = 3;
                        accumulator += ch;
                    }
                    break;
                case 6:
                    if (ch == '/') {
                        state = 2;
                        accumulator += ch;
                    } else {
                        state = 6;
                        accumulator = "";
                    }
                    break;
            }

            index++;
        }
        return comments;
    }
}
