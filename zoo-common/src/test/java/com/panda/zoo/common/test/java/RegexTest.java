package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huixiangdou
 * @date 2018/9/17
 */
public class RegexTest {

    @Test
    public void test1() {
        //
        String str = "wwww.yellowcong.net";
        //表示  （字符串 .）
        String regEx = "([\\w]+\\.)([\\w]*\\.[\\w]*)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group()); //输出大组的数据， 通过 group(0)是一样的
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1)); //输出第一组的数据
            System.out.println(matcher.group(2)); //输出第二组的数据
        }
    }

    @Test
    public void test2() {
        String str = "你好，我么是";

        System.out.println(JSON.toJSONString(Splitter.on("。").splitToList(str)));
    }

    @Test
    public void test3() {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher("红烧肉100元100元");
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("======================");
        }
    }

    @Test
    public void test4() {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher("100红烧肉100元100元");
        System.out.println(matcher.lookingAt());
        System.out.println(matcher.group());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
    }

    @Test
    public void test5() {
        String regex = "\\d+\\.\\d+|\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher("红烧肉.3元");
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("======================");
        }
    }

    @Test
    public void test6() {
        System.out.println(Splitter.on(",").on(".").splitToList("你们,好.啦啦"));
    }

    @Test
    public void test7() {
        String regex = "\\d+\\.\\d+|\\d+\\.|\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher("红烧肉3.元");
        if (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.start());
            System.out.println(matcher.start());
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        }
    }

    @Test
    public void test8() {
        String str = "你好";
        System.out.println(str.substring(0, 0));
    }

    @Test
    public void test9() {
        System.out.println(Double.parseDouble("3."));
        System.out.println(Double.parseDouble(".3"));
    }

    @Test
    public void test10(){
        String str = "红烧肉100元";
        System.out.println(str.lastIndexOf("元"));
    }

    @Test
    public void test11(){
        String str = "你们";
        System.out.println(JSON.toJSONString(Splitter.on("元").splitToList(str)));
    }

    @Test
    public void test12(){
        new Character('你').toString();
        Set<String> s = Sets.newHashSet("你","好");
        System.out.println(s.contains("你"));
    }
}
