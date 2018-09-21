package com.panda.zoo.common.util;

import com.alibaba.fastjson.JSON;
import com.dfire.boss.log.Logger;
import com.dfire.boss.log.LoggerFactory;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 识别中文+数字
 *
 * @author huixiangdou
 * @date 2018/9/17
 */
public class RecognitionSupport {
    private static final Logger logger = LoggerFactory.getLogger(RecognitionSupport.class, "文本识别");
    private static final String END_MARK = "。";
    private static final String PAUSE_MARK_REGEX = "，|,";
    private static final String NUM_REGEX = "\\d+\\.\\d+|\\d+\\.|\\.\\d+|\\d+";
    private static final String MONEY_UNIT = "元";

    public static IdentifyResult discern(String input) {
        logger.info("文本识别入参:{}", input);
        //预处理
        List<String> prepareInput = preHandle(input);
        IdentifyResult result = new IdentifyResult();
        if (prepareInput.size() == 1) { //没有分隔符
            UnitNumber unitNumber = discernNumber(prepareInput.get(0));
            if (unitNumber != null) {
                String text = input.substring(0, unitNumber.getStartIndex());
                result.setUnitNumber(unitNumber);
                result.setText(text);
            }
        } else {
            UnitNumber unitNumber = discernNumber(prepareInput.get(1));
            result.setUnitNumber(unitNumber);
            result.setText(prepareInput.get(0));
        }
        return result;
    }

    /**
     * 识别出数字
     *
     * @param input
     * @returnå
     */
    private static UnitNumber discernNumber(String input) {
        //先识别阿拉伯数字
        UnitNumber unitNumber = discernArabNumber(input);
        if (unitNumber == null || unitNumber.getNumber() == null) {
            //识别中文数字
            unitNumber = discernChineseNumber(input);
        }
        return unitNumber;
    }

    /**
     * 识别阿拉伯数字
     *
     * @param input
     * @return
     */
    private static UnitNumber discernArabNumber(String input) {
        UnitNumber unitNumber = null;
        Pattern p = Pattern.compile(NUM_REGEX);
        Matcher matcher = p.matcher(input);
        String foundStr;
        if (matcher.find()) { //数字为阿拉伯数字
            foundStr = matcher.group();
            try {
                unitNumber = new UnitNumber();
                unitNumber.setStartIndex(matcher.start());
                unitNumber.setNumber(Double.parseDouble(foundStr));
                unitNumber.setLength(foundStr.length());
                unitNumber.setEndIndex(matcher.end());
                //单位为数字后一个字符
                String unit = null;
                try {
                    unit = input.substring(matcher.end(), matcher.end() + 1);
                } catch (Exception e) {
                    //not found
                }
                unitNumber.setUnit(unit);
            } catch (NumberFormatException e) {
                logger.error("找到的文本未能转换为数字:{}", foundStr);
                unitNumber = null;
            }
        }
        return unitNumber;
    }

    /**
     * 识别中文汉字
     * 中文目前采用以"元"结尾识别
     *
     * @param input
     * @return
     */
    private static UnitNumber discernChineseNumber(String input) {
        int moneyIndex = input.lastIndexOf(MONEY_UNIT);
        //未识别到单位或者只含单位
        if (moneyIndex < 1) {
            return null;
        }
        char[] cs = input.toCharArray();
        int numStartIndex = -1;
        boolean findNumber = false;
        for (int i = moneyIndex - 1; i >= 0; i--) {
            if (!NumberTransferUtils.chineseNumSet.contains(String.valueOf(cs[i]))) {
                numStartIndex = i + 1;
                break;
            } else {
                findNumber = true;
            }
        }
        if (findNumber && numStartIndex == -1) {
            numStartIndex = 0;
        }
        //未识别到价格
        if (numStartIndex == -1) {
            return null;
        }
        String number = input.substring(numStartIndex, moneyIndex);
        Double d = NumberTransferUtils.analyze(number);
        if (d == null) {
            return null;
        }
        UnitNumber unitNumber = new UnitNumber();
        unitNumber.setNumber(d);
        unitNumber.setUnit(MONEY_UNIT);
        unitNumber.setStartIndex(numStartIndex);
        unitNumber.setEndIndex(moneyIndex);
        unitNumber.setLength(unitNumber.getEndIndex() - unitNumber.getStartIndex());
        return unitNumber;
    }

    /**
     * 预处理
     *
     * @param input
     * @return 名称+价格 或者 名称价格
     */
    private static List<String> preHandle(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalStateException("param must be not empty");
        }
        //只取第一句
        input = Splitter.on(END_MARK).splitToList(input).get(0);
        return Splitter.onPattern(PAUSE_MARK_REGEX).splitToList(input);
    }

    public static class IdentifyResult {
        private String text;
        private UnitNumber unitNumber;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public UnitNumber getUnitNumber() {
            return unitNumber;
        }

        public void setUnitNumber(UnitNumber unitNumber) {
            this.unitNumber = unitNumber;
        }
    }

    /**
     * 对象为null表示未匹配到价格
     * 之后再看unit是否为空
     */
    public static class UnitNumber {
        /**
         * 数字
         */
        private Double number;
        /**
         * 数字所占长度
         */
        private int length;
        /**
         * 单位
         */
        private String unit;
        /**
         * 数字开始位置
         */
        private int startIndex = -1;
        /**
         * 数字结束为止
         */
        private int endIndex;

        public Double getNumber() {
            return number;
        }

        public void setNumber(Double number) {
            this.number = number;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }
    }

    public static void main(String[] args) {
        String s1 = "四喜丸子100元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s1)));

        String s2 = "四喜丸子100.2";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s2)));

        String s3 = "四喜丸子";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s3)));

        String s4 = "四喜丸子100.";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s4)));

        String s5 = "四喜丸子，100元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s5)));

        String s6 = "四喜丸子,100元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s6)));

        String s7 = "四喜丸子.3元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s7)));

        String s8 = "四喜丸子一百二十元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s8)));

        String s9 = "四喜丸子三千两百零五点二四元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s9)));

        String s10 = "四喜丸子零点五元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s10)));

        String s11 = "四喜丸子，十八元";
        System.out.println(JSON.toJSONString(RecognitionSupport.discern(s11)));
    }
}
