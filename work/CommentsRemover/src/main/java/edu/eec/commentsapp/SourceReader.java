package edu.eec.commentsapp;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
public class SourceReader {
    public static String readFrom(String fileName){
        StringBuffer buffer = new StringBuffer();
        try {
            FileReader reader = new FileReader(fileName);
            int ch = reader.read();
            while(ch != -1){
                buffer.append((char)ch);
                ch = reader.read();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
