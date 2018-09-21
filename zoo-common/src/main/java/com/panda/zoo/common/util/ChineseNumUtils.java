package com.panda.zoo.common.util;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChineseNumUtils {
    private static final int[] numLen = {16, 8, 4, 3, 2, 1};//对应下面单位后面多少个零
    private static final String[] dw = {"兆", "亿", "万", "千", "百", "十"}; //简体中文单位
    private static final String[] dw1 = {"兆", "亿", "萬", "仟", "佰", "拾"}; //繁体中文单位
    private static final String[] sz = {"零", "一", "二", "两", "三", "四", "五", "六", "七", "八", "九", "十"}; //简体中文数字
    private static String[] sz1 = {"〇", "壹", "贰", "兩", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"}; //繁体中文数字
    private static final String CHINESE_POINT = "点";
    private static final String ARAB_POINT = ".";
    private static Map<String, Integer> numberMap = new HashMap<String, Integer>() {{
        put("零", 0);
        put("一", 1);
        put("二", 2);
        put("两", 2);
        put("三", 3);
        put("四", 4);
        put("五", 5);
        put("六", 6);
        put("七", 7);
        put("八", 8);
        put("九", 9);
        put("十", 10);
    }};

    /**
     * 解析中文数字
     * <p>
     * 返回null表示未识别到
     *
     * @param chNum
     * @return
     */
    public static Double analyze(String chNum) {
        if (StringUtils.isBlank(chNum)) {
            return null;
        }
        try {
            List<String> list = Splitter.on(CHINESE_POINT).splitToList(chNum);
            if (list.size() == 1) { //不含小数点
                return Double.parseDouble(String.valueOf(ch2Num(chNum)));
            } else {
                long head = ch2Num(list.get(0));
                long tail = ch2Decimal(list.get(1));
                return Double.parseDouble(new StringBuilder(String.valueOf(head)).append(ARAB_POINT).append(String.valueOf(tail)).toString());
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //解析整数
    private static long ch2Num(String chNum) {
        if (StringUtils.isBlank(chNum)) {
            return 0;//空对象返回0
        }

        //预处理
        chNum = prepare(chNum);

        if (chNum.length() == 1) {
            Integer n = numberMap.get(chNum);
            return n != null ? n : 0;
        }

        chNum = strReverse(chNum); //调转输入的字符串
        for (int i = 0; i < dw.length; i++) {
            if (chNum.contains(dw[i])) {
                String part[] = chNum.split(dw[i], 2); //把字符串分割2部分
                long num1 = ch2Num(strReverse(part[1]));
                long num2 = ch2Num(strReverse(part[0]));
                return (long) ((num1 == 0 ? 1 : num1) * Math.pow(10, numLen[i]) + num2);
            }
        }
        return revolveOneByOne(chNum);
    }

    //逐字解析
    private static long revolveOneByOne(String chNum) {
        char[] c = chNum.toCharArray();
        long sum = 0;
        for (int i = 0; i < c.length; i++) { //一个个解析数字
            String tem = String.valueOf(c[i]); //根据索引转成对应数字
            sum += ch2Num(tem) * Math.pow(10, i);//根据位置给定数字
        }
        return sum;
    }

    //解析小数
    private static long ch2Decimal(String chNum) {
        return revolveOneByOne(strReverse(chNum));
    }

    //预处理
    private static String prepare(String chNum) {
        //统一文字版本
        for (int i = 0; i < sz.length; i++) {
            if (i < dw.length) {
                chNum = chNum.replaceAll(dw1[i], dw[i]);
            }
            chNum = chNum.replaceAll(sz1[i], sz[i]);
        }
        return chNum.replaceAll("(百.)\\b", "$1十"); //正则替换为了匹配中文类似二百五这样的词
    }

    //字符串掉转
    private static String strReverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
//        System.out.println(ChineseNumUtils.ch2Num("十一"));
//        System.out.println(ChineseNumUtils.ch2Num("一百零五"));
//        System.out.println(ChineseNumUtils.ch2Num("两百零五"));
//        System.out.println(ChineseNumUtils.ch2Num("一千零五百"));
//        System.out.println(ChineseNumUtils.ch2Num("一万五千二百三十一"));
//        System.out.println(ChineseNumUtils.ch2Num("十一"));
//        System.out.println(ChineseNumUtils.analyze("六五十一"));
        System.out.println(ChineseNumUtils.analyze("六十一点五二"));
        System.out.println(ChineseNumUtils.analyze("一百零五点二三"));
    }
}