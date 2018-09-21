package com.panda.zoo.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.*;

public class VoicePriceRecognition {

    private final static String NOT_HAS_PRICE_CONTENT = "no price";
    //private static final Logger vineLogger = Vine.getLogger(VoicePriceRecognition.class);

    private static final Character[] PRICE_UNIT = {'元', '块', '角', '毛', '分'};

    private static final List<Character> PRICE_UNIT_LIST = Arrays.asList(PRICE_UNIT);
    private static final Character[] CN_NUMERIC = {'一', '二', '三', '四', '五',
            '六', '七', '八', '九', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖',
            '十', '百', '千', '拾', '佰', '仟',
            '万', '亿',
            // '○', 'Ｏ',
            '零'};
    private static final Character[] EN_NUMERIC = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final String BASE_UNIT_YUAN = "元";
    private static final String BASE_UNIT_KUAI = "块";
    private static final String BASE_UNIT_JIAO = "角";
    private static final String BASE_UNIT_MAO = "毛";
    private static final String BASE_UNIT_FEN = "分";
    private static Map<Character, Integer> cnNumeric = null;
    private static Map<Character, Integer> enNumeric = null;

    static {
        cnNumeric = new HashMap<Character, Integer>(40, 0.85f);
        for (int j = 0; j < 9; j++) {
            cnNumeric.put(CN_NUMERIC[j], j + 1);
        }
        for (int j = 9; j < 18; j++) {
            cnNumeric.put(CN_NUMERIC[j], j - 8);
        }
        cnNumeric.put('两', 2);
        cnNumeric.put('零', 0);
        cnNumeric.put('十', 10);
        cnNumeric.put('拾', 10);
        cnNumeric.put('百', 100);
        cnNumeric.put('佰', 100);
        cnNumeric.put('千', 1000);
        cnNumeric.put('仟', 1000);
        cnNumeric.put('万', 10000);
        cnNumeric.put('亿', 100000000);
        for (int i = 0; i < EN_NUMERIC.length; i++) {
            cnNumeric.put(EN_NUMERIC[i], i);
        }
    }

    private static void commonRecognition(String voiceContent, String baseStr, Map<String, String> result) throws Exception {
        String itemName;
        StringBuffer priceContent = new StringBuffer();
        int baseIndex = voiceContent.lastIndexOf(baseStr);
        if (baseIndex == 0) {
            throw new InvalidParameterException("NOT_HAS_NAME");
        }
        //处理块 或 元 之前是 数字或者点 如 1234.5块（元），从块开始 倒着 一位一位的判断是否属于数字或者汉字数字直到非的那一位停止
        int baseIndex_ = baseIndex;
        if (Character.isDigit(voiceContent.charAt(baseIndex - 1))) {
            while (baseIndex > 0 && (Character.isDigit(voiceContent.charAt(--baseIndex)) || '.' == voiceContent.charAt(baseIndex))) {
                priceContent.insert(0, voiceContent.charAt(baseIndex));
            }
            result.put("type", "number");
        }
        //处理块 或 元 之前是 是汉字 如 一千二百元点五块（元）
        else {
            if (cnNumeric.containsKey(voiceContent.charAt(baseIndex - 1))) {
                while (baseIndex > 0 && (cnNumeric.containsKey(voiceContent.charAt(--baseIndex)) || '点' == voiceContent.charAt(baseIndex))) {
                    priceContent.insert(0, voiceContent.charAt(baseIndex));
                }
            }
            result.put("type", "cn");
        }
        //如果是汉字和数字的混合体 如 一百元56毛柒捌分
        if (baseIndex == 0 && cnNumeric.containsKey(voiceContent.charAt(baseIndex))) {
            throw new InvalidParameterException("NOT_HAS_NAME");
        }
        itemName = voiceContent.substring(0, baseIndex + 1);
        result.put("name", itemName);
        priceContent.append(voiceContent.substring(baseIndex_ == baseIndex ? baseIndex : baseIndex_, voiceContent.length()));
        if (priceContent.length() < 2) {
            throw new InvalidParameterException("NOT_HAS_PRICE");
        }
        result.put("price", priceContent.toString());
        getPrice(result);
        System.out.println(result);
    }

    private static void getPrice(Map<String, String> result) throws Exception {
        //按照规则 将部分字符替换
        String price = result.get("price");

      /* StringBuffer sb = new StringBuffer(price);
       while(price.indexOf('零') >-1){
           sb.deleteCharAt(price.indexOf('零'));
           price = sb.toString();
       }*/
        price = price
                .replace("块", "元")
                .replace("毛", "角")
                .replace('佰', '百')
                .replace('仟', '千')
                .replace('拾', '十')
                .replace('零', ' ')
                .replace("两", "二").trim();
        result.put("price", price);
        //检验是否具有价格语义
        checkemanticAndSetPrice(result);
    }

    /**
     * 只针对千元进行校验，如果以后扩展到万  需要对万之前的特殊校验，beforeWan ，beforeYi
     *
     * @param beforeYuan
     * @throws InvalidParameterException
     */
    private static void checkBeforeYuan(String beforeYuan) throws InvalidParameterException {
        if (beforeYuan == null || beforeYuan.length() < 1 || "".equals(beforeYuan)) {
            return;
        }
        boolean invalid =
                beforeYuan.split("百").length > 2 ||
                        beforeYuan.split("千").length > 2 ||
                        beforeYuan.split("十").length > 2 ||
                        beforeYuan.split("零").length > 2;
        if (invalid) {
            throw new InvalidParameterException("价格无法识别:" + beforeYuan);
        }
    }

    private static void checkemanticAndSetPrice(Map<String, String> result) throws Exception {

        String targePrice = result.get("price");

        //检验单位顺序 和单位之间的值
        //1.单位个数是否都为1 如 元 角 分 是否只有一次而且顺序从小到大。按单位分出区间，各个数字区间是否是大小顺序排列。
        char[] targetPriceChars = targePrice.toCharArray();
        int yuanSum = 0;
        int jiaoSum = 0;
        int fenSum = 0;
        for (char s : targetPriceChars) {
            if ('元' == s) {
                yuanSum++;
            }
            if ('角' == s) {
                jiaoSum++;
            }
            if ('分' == s) {
                fenSum++;
            }
        }
        //String [] yuanSplit = targePrice.split("元");
        //String [] jiaoSplit = targePrice.split("角");
        //String [] fenSplit = targePrice.split("分");

        int yuanIndex = targePrice.lastIndexOf('元');
        int jiaoIndex = targePrice.lastIndexOf('角');
        int fenIndex = targePrice.lastIndexOf('分');
        int invalidLength = 2;
        if (yuanSum >= invalidLength || jiaoSum >= invalidLength || fenSum >= invalidLength) {
            throw new InvalidParameterException("价格无法识别:" + targePrice);
        }
        boolean shunXu = (yuanIndex > jiaoIndex && jiaoIndex > -1) || (jiaoIndex > fenIndex && fenIndex > -1) || (yuanIndex > fenIndex && fenIndex > -1);
        if (shunXu) {
            throw new InvalidParameterException("价格无法识别:" + targePrice);
        }
        //用于判断是否是最后一位
        boolean last = false;
        int lastIndex = 0;
        String beforeYuan = null;
        String betweenYuanAndJiao = null;
        String betweenJiaoAndFen = null;
        String afterCnDian = null;
        boolean has_dian = false;
        StringBuffer newPrice = new StringBuffer();
        if (yuanIndex > -1) {
            beforeYuan = targePrice.substring(0, yuanIndex);
            if (beforeYuan.contains("点")) {
                int dianIndex = beforeYuan.lastIndexOf('点');
                if (dianIndex > -1) {
                    afterCnDian = beforeYuan.substring(dianIndex + 1);
                    beforeYuan = beforeYuan.substring(0, dianIndex);
                }
            }
            newPrice.append(beforeYuan).append("元");
            lastIndex = yuanIndex;
        }
        //beforeyuan需要校验 计量单位 千  百  十 零 是否有多个
        checkBeforeYuan(beforeYuan);
        if (jiaoIndex > -1) {
            betweenYuanAndJiao = targePrice.substring(yuanIndex + 1, jiaoIndex).trim();
            lastIndex = jiaoIndex;
        }
        if (fenIndex > -1) {
            betweenJiaoAndFen = targePrice.substring(jiaoIndex > -1 ? jiaoIndex + 1 : yuanIndex + 1, fenIndex).trim();
            lastIndex = fenIndex;
        }
        Double lastedPrice = 0D;
        //如果不相等 说明最后还存在没有货币单位的数字存在
        if (lastIndex + 1 != targePrice.length()) {
            String lastStr = targePrice.substring(lastIndex + 1, targePrice.length());
            if (lastStr.length() > 1) {
                lastStr = lastStr.substring(0, 1);
            }
            //如果是数字 ，如果是可识别为数字的的汉字
            if (isCNNumeric(lastStr.charAt(0)) > -1) {
                if ('元' == targePrice.charAt(lastIndex)) {
                    lastedPrice = 0.1 * Double.valueOf(isCNNumeric(lastStr.charAt(0)));
                }
                if ('角' == targePrice.charAt(lastIndex)) {
                    lastedPrice = 0.01 * Double.valueOf(isCNNumeric(lastStr.charAt(0)));
                }
                if ('分' == targePrice.charAt(lastIndex)) {
                    lastedPrice = 0D;
                }
            }

            last = true;
        }

        //重新拼写价格
        if (betweenYuanAndJiao != null && betweenYuanAndJiao.length() > 1) {
            betweenYuanAndJiao = betweenYuanAndJiao.substring(0, 1);
            newPrice.append(betweenYuanAndJiao).append("角");
        }
        if (betweenJiaoAndFen != null && betweenJiaoAndFen.length() > 1) {
            betweenJiaoAndFen = betweenJiaoAndFen.substring(0, 1);
            newPrice.append(betweenJiaoAndFen).append("分");
        }
        if (last) {
            newPrice.append(targePrice.substring(lastIndex, targePrice.length()));
        }

        result.put("price", newPrice.toString());

        Double beforeYuanPrice = 0D;
        if (yuanIndex > -1) {
            if ("cn".equals(result.get("type"))) {

                beforeYuanPrice = Double.valueOf(cnNumericToArabic(beforeYuan));

            } else if ("number".equals(result.get("type"))) {
                try {
                    beforeYuanPrice = Double.valueOf(beforeYuan);
                } catch (NumberFormatException e) {
                    throw new InvalidParameterException("价格解析错误，无效价格");
                }
            } else if ("mixed".equals(result.get("type"))) {

            }
        }
        BigDecimal bigDecimal = new BigDecimal(beforeYuanPrice.toString());
        if (betweenYuanAndJiao != null && !"".equals(betweenYuanAndJiao) && isCNNumeric(betweenYuanAndJiao.charAt(0)) > -1) {
            bigDecimal = bigDecimal.add(BigDecimal.valueOf(isCNNumeric(betweenYuanAndJiao.charAt(0))).multiply(BigDecimal.valueOf(0.1D)));
        }
        if (betweenJiaoAndFen != null && !"".equals(betweenYuanAndJiao) && isCNNumeric(betweenJiaoAndFen.charAt(0)) > -1) {
            bigDecimal = bigDecimal.add(BigDecimal.valueOf(isCNNumeric(betweenJiaoAndFen.charAt(0))).multiply(BigDecimal.valueOf(0.01D)));
        }
        bigDecimal = bigDecimal.add(new BigDecimal(String.valueOf(lastedPrice)));
        //如果'点' 存在，处理点后面的。//解析两位 角 分
        BigDecimal afterCnDianPrice = null;
        if (afterCnDian != null) {

            if (afterCnDian.length() > 1) {
                char jiao = afterCnDian.charAt(0);
                char fen = afterCnDian.charAt(1);
                afterCnDianPrice = BigDecimal.valueOf(0.1).multiply(BigDecimal.valueOf(isCNNumeric(jiao))).add(BigDecimal.valueOf(0.01).multiply(BigDecimal.valueOf(isCNNumeric(fen))));

            }
            if (afterCnDian.length() == 1) {
                char jiao = afterCnDian.charAt(0);
                afterCnDianPrice = BigDecimal.valueOf(0.1).multiply(BigDecimal.valueOf(isCNNumeric(jiao)));
            }
        }
        if (afterCnDianPrice != null) {
            bigDecimal = bigDecimal.add(afterCnDianPrice);
        }
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        result.put("price", String.valueOf(bigDecimal));
    }

    private static int cnNumericToArabic(String cnn) {

        cnn = cnn.trim();
        if (cnn.length() == 1) {
            return isCNNumeric(cnn.charAt(0));
        }
        int yi = -1, wan = -1, qian = -1, bai = -1, shi = -1;
        int val = 0;
        yi = cnn.lastIndexOf('亿');
        if (yi > -1) {
            val += cnNumericToArabic(cnn.substring(0, yi)) * 100000000;
            if (yi < cnn.length() - 1) {
                cnn = cnn.substring(yi + 1, cnn.length());
            } else {
                cnn = "";
            }

            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10) {
                    val += arbic * 10000000;
                }
                cnn = "";
            }
        }

        wan = cnn.lastIndexOf('万');
        if (wan > -1) {
            val += cnNumericToArabic(cnn.substring(0, wan)) * 10000;
            if (wan < cnn.length() - 1) {
                cnn = cnn.substring(wan + 1, cnn.length());
            } else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10) {
                    val += arbic * 1000;
                }
                cnn = "";
            }
        }

        qian = cnn.lastIndexOf('千');
        if (qian > -1) {
            val += cnNumericToArabic(cnn.substring(0, qian)) * 1000;
            if (qian < cnn.length() - 1) {
                cnn = cnn.substring(qian + 1, cnn.length());
            } else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10) {
                    val += arbic * 100;
                }
                cnn = "";
            }
        }

        bai = cnn.lastIndexOf('百');
        if (bai > -1) {
            val += cnNumericToArabic(cnn.substring(0, bai)) * 100;
            if (bai < cnn.length() - 1) {
                cnn = cnn.substring(bai + 1, cnn.length());
            } else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10) {
                    val += arbic * 10;
                }
                cnn = "";
            }
        }

        shi = cnn.lastIndexOf('十');
        if (shi > -1) {
            if (shi == 0) {
                val += 1 * 10;
            } else {
                val += cnNumericToArabic(cnn.substring(0, shi)) * 10;
            }
            if (shi < cnn.length() - 1) {
                cnn = cnn.substring(shi + 1, cnn.length());
            } else {
                cnn = "";
            }
        }

        cnn = cnn.trim();
        //特殊处理 如 三三三元 处理成为3元，也可以理解为处理成为三百三十三元。
        if (cnn.length() > 1) {
            cnn = cnn.substring(0, 1);
        }
        for (int j = 0; j < cnn.length(); j++) {
            val += isCNNumeric(cnn.charAt(j))
                    * Math.pow(10, cnn.length() - j - 1);
        }

        return val;
    }

    private static int isCNNumeric(char c) {
        Integer i = cnNumeric.get(c);
        if (i == null) {
            return -1;
        }
        return i.intValue();
    }

    private static int isENNumeric(char c) {
        Integer i = enNumeric.get(c);
        if (i == null) {
            return -1;
        }
        return i.intValue();
    }

    public static Map<String, String> priceRecognition(String voiceContent) throws Exception {
        //1.非空 verify
        //vineLogger.info("获取语音输入内容："+voiceContent);
        Map<String, String> result = new HashMap<>();
        result.put("voiceContent", voiceContent);
        if (StringUtils.isEmpty(voiceContent)) {
            throw new InvalidParameterException("NOT_HAS_PRICE");
        }

        //2.识别价格区间
        //按照块 和 元 进行基准位置，如果没有块或者元，按照角，毛，分进行
        if (PRICE_UNIT_LIST.contains(voiceContent.charAt(voiceContent.length() - 1))) {
            if (voiceContent.contains(BASE_UNIT_YUAN)) {
                commonRecognition(voiceContent, BASE_UNIT_YUAN, result);
            } else if (voiceContent.contains(BASE_UNIT_KUAI)) {
                commonRecognition(voiceContent, BASE_UNIT_KUAI, result);
            } else if (voiceContent.contains(BASE_UNIT_MAO)) {
                commonRecognition(voiceContent, BASE_UNIT_MAO, result);
            } else if (voiceContent.contains(BASE_UNIT_JIAO)) {
                commonRecognition(voiceContent, BASE_UNIT_JIAO, result);
            } else if (voiceContent.contains(BASE_UNIT_FEN)) {
                commonRecognition(voiceContent, BASE_UNIT_FEN, result);
            }
        } else {
            //不存在价格
            commonRecognition(voiceContent + "元", BASE_UNIT_YUAN, result);
            //throw new InvalidParameterException("NOT_HAS_PRICE",NOT_HAS_PRICE_CONTENT);
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        String s0 = "毛血旺28";//"毛豆炸酱煲仔饭14";

        String s1 = "醋0.5元";
        String s2 = "西红柿2金一千二百块二分";
        String s4 = "西红柿2金12.4元";
        String s5 = "西红柿2金2222212.42222块";
        String s6 = "西红柿2金一百元56毛柒捌分";
        String s7 = "红烧肉一百一百五十五十元五十毛柒捌分";
        String s8 = "手抓饼一二千三四百五六十七八元一二毛三四分";
        String s9 = "一千二百三十四元五毛六分";
        List<String> sb = new ArrayList<>(9);
        sb.add(s0);
        sb.add(s1);
        sb.add(s2);
        sb.add(s4);
        sb.add(s5);
        sb.add(s6);
        sb.add(s7);
        sb.add(s8);
        sb.add(s9);
        for (String s : sb) {
            try {
                priceRecognition(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}