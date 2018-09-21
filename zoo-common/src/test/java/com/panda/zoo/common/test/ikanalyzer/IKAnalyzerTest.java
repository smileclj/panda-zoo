package com.panda.zoo.common.test.ikanalyzer;

import org.junit.Test;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author huixiangdou
 * @date 2018/9/17
 */
public class IKAnalyzerTest {

    @Test
    public void test1() {
        try {
            String input = "四喜丸子123元";
            IKSegmenter ikSeg = new IKSegmenter(new StringReader(input), true);
            Lexeme lexeme;
            while ((lexeme = ikSeg.next()) != null) {
                System.out.println(lexeme.getLexemeText());
                System.out.println(lexeme.getLexemeTypeString());
                System.out.println(lexeme.getLength());
                System.out.println("====================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            String input = "四喜丸子123.5元";
            IKSegmenter ikSeg = new IKSegmenter(new StringReader(input), true);
            Lexeme lexeme;
            while ((lexeme = ikSeg.next()) != null) {
                System.out.println(lexeme.getLexemeText());
                System.out.println(lexeme.getLexemeTypeString());
                System.out.println(lexeme.getLength());
                System.out.println("====================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            String input = "四喜丸子0.5元";
            IKSegmenter ikSeg = new IKSegmenter(new StringReader(input), true);
            Lexeme lexeme;
            while ((lexeme = ikSeg.next()) != null) {
                System.out.println(lexeme.getLexemeText());
                System.out.println(lexeme.getLexemeTypeString());
                System.out.println(lexeme.getLength());
                System.out.println("====================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try {
            String input = "四喜丸子十三元";
            IKSegmenter ikSeg = new IKSegmenter(new StringReader(input), true);
            Lexeme lexeme;
            while ((lexeme = ikSeg.next()) != null) {
                System.out.println(lexeme.getLexemeText());
                System.out.println(lexeme.getLexemeTypeString());
                System.out.println(lexeme.getLength());
                System.out.println("====================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            String input = "四喜丸子零点三元";
            IKSegmenter ikSeg = new IKSegmenter(new StringReader(input), true);
            Lexeme lexeme;
            while ((lexeme = ikSeg.next()) != null) {
                System.out.println(lexeme.getLexemeText());
                System.out.println(lexeme.getLexemeTypeString());
                System.out.println(lexeme.getLength());
                System.out.println("====================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
