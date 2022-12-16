package edu.eec.commentsapp;

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
 */
public class CommentsApp {

    private static String source = SourceReader.readFrom("resources/source.c");

    public static void main(String[] args){
        CommentUtility utility = new CommentUtility(source);
        List<String> comments = utility.comments();
        comments.forEach(comment -> System.out.println(comment + "\n"));
    }

}
