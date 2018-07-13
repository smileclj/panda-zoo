package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.base.CaseFormat;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.panda.zoo.common.test.java.enums.EnumIndustry;
import com.panda.zoo.common.test.java.hibernate.EntityB;
import com.panda.zoo.common.test.java.model.*;
import com.panda.zoo.common.test.jvm.model.Student;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.beans.Introspector;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by huixiangdou on 2017/2/25.
 */
public class JavaTest {

    @Test
    public void testStatic() {
        StaticModel staticModel = new StaticModel();
        StaticModel.id = "2";
        staticModel.setId("1");
        System.out.println(staticModel.getId());
        System.out.println(StaticModel.id);
    }

    @Test
    public void testExtend() {
//        Parent.id = "1";
//        Child1.id = "2";
//        Child2.id = "3";
//        Child1 c1 = new Child1();
//        c1.setId("4");
//        System.out.println(Parent.id);
    }

    @Test
    public void testList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == 1) {
                it.remove();
            }
        }

        System.out.println(JSON.toJSONString(list));
    }

    private <T> List<T> get() {
        return new ArrayList<>();
    }

    @Test
    public void testExtendV2() {
        ChildExtend childExtend = new ChildExtend();
        childExtend.say();
        System.out.println(childExtend.getId());
    }

    @Test
    public void timeUnit() {
        System.out.println(TimeUnit.MINUTES.toMillis(3));
    }

    @Test
    public void calendar() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
    }

    @Test
    public void extend() {
        Parent p1 = new Child1();
        Parent p2 = new Child2();

        System.out.println(p1);
        System.out.println(p2);
    }

    @Test
    public void testBoolean() {
        System.out.println(Boolean.valueOf("1"));
        System.out.println(Boolean.valueOf("0"));
        System.out.println(Boolean.valueOf("true"));
        System.out.println(Boolean.valueOf("false"));
        System.out.println(Boolean.valueOf(true));
    }

    @Test
    public void testArrayCopy() {
        Integer[] arr1 = new Integer[]{1, 2, 3, 4};
        Integer[] arr2 = new Integer[]{5, 6, 7, 8};
        System.arraycopy(arr1, 0, arr2, 2, 2);
    }

    @Test
    public void testEnum() {
        System.out.println(TypeEnum.A == TypeEnum.A);
    }

    @Test
    public void hashcode() {
        Integer a = 1;
        System.out.println(a.hashCode());
    }

    @Test
    public void serialize() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            SC sc1 = new SC();
            sc1.setId(2);
            oos.writeObject(sc1);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            SC sc = (SC) ois.readObject();
            System.out.println(sc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void convert() {
        SC sc = new SC();
        sc.setId(1);
        sc.setName("name");

        SP sp = (SP) sc;
        System.out.println(sp);


        SP sp1 = new SP();
        sp1.setId(1);
        SC sc1 = (SC) sp1;
        System.out.println(sc1);
    }

    @Test
    public void random() {
        Random r1 = new Random(10);
        Random r2 = new Random(10);
        for (int i = 0; i < 10; i++) {
            System.out.println(r1.nextInt());
            System.out.println(r2.nextInt());
        }
    }

    @Test
    public void seq() {
        System.out.println((int) (System.currentTimeMillis() % Integer.MAX_VALUE));
    }

    @Test
    public void getByte() throws UnsupportedEncodingException {
        String msg = "我们";
        int length = msg.getBytes("UTF-8").length;
        System.out.println(length);
    }

    @Test
    public void listString() {
        List<String> list = Lists.newArrayList("1", "2");
        for (String s : list) {
            s = "a" + s;
        }
        System.out.println(list.toString());
    }

    @Test
    public void ab() {
        AbstractModel abstractModel = new AbstractModel() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }

    @Test
    public void int_max() {
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void print() {
        SC sc = new SC();
        sc.setId(1);
        sc.setName("2");
        sc.print();
    }

    @Test
    public void forceConvert() {
        Object obj = null;
        Student s = (Student) obj;
    }

    @Test
    public void list() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        Integer[] arr = list.toArray(new Integer[0]);
    }

    @Test
    public void listRemoveAll() {
        List list1 = Lists.newArrayList(1, 2, 3);
        List list3 = Lists.newArrayList(list1);
        List list2 = Lists.newArrayList(1);
        list1.removeAll(list2);
        System.out.println(JSON.toJSONString(list1));
    }

    @Test
    public void testBooleanValueOf() {
        Boolean b1 = Boolean.valueOf("true");
        Boolean b2 = Boolean.valueOf(null);
        Integer i1 = Integer.valueOf(null);
    }

    @Test
    public void subStr() {
        String v = "123";
        System.out.println(v.substring(2, 3));
    }

    @Test
    public void m() {
//        List<int[]> list = Lists.newArrayList();
//        list.add(new int[]{1, 2});
//        list.add(new int[]{1, 2, 3});
//        list.add(new int[]{1, 2, 3, 4});
//        System.out.println(Iterables.toArray(list, int[].class));

        int[][] i = new int[3][];

        i[1] = new int[]{1, 2, 3};
        i[2] = new int[]{1, 2};
        System.out.println(JSON.toJSONString(i));
    }

    @Test
    public void testMap() {
        Map<String, String> map = Maps.newHashMap();
        map.put(null, "a");
        String v = map.get(null);
    }

    @Test
    public void replaceChars() {
        String str = "MemberReq";
        String executor = "Executor";
        String req = "Req";

        System.out.println(Introspector.decapitalize(StringUtils.replace(str, req, executor)));

    }

    @Test
    public void startWith() {
        String str = "{1";
        System.out.println(str.startsWith("{"));
    }

    public static final int add(int i) {
        i++;
        return i;
    }

    public static void main(String[] args) {
        int i = 1;
        i = add(i);
        i = add(i);
        System.out.println(i);
    }

    @Test
    public void parseUrl() throws Exception {
        URL url = new URL("http://www.runoob.com/index.html?language=cn#j2se");
        System.out.println("URL 为：" + url.toString());
        System.out.println("协议为：" + url.getProtocol());
        System.out.println("验证信息：" + url.getAuthority());
        System.out.println("文件名及请求参数：" + url.getFile());
        System.out.println("主机名：" + url.getHost());
        System.out.println("路径：" + url.getPath());
        System.out.println("端口：" + url.getPort());
        System.out.println("默认端口：" + url.getDefaultPort());
        System.out.println("请求参数：" + url.getQuery());
        System.out.println("定位位置：" + url.getRef());
    }

    @Test
    public void sub() {
        String str = "/upload_files/a/b/c.jpg";
        System.out.println(str.substring(14));
        System.out.println(str.substring(1));
    }

    @Test
    public void messageFormat() {
        System.out.println(MessageFormat.format("entityId:{0},name:{1},age:{2}", new Object[]{"11", "22", "3"}));
    }

    @Test
    public void replaceAll() {
        String str = "name:{},id:{}";
        str = str.replaceAll("\\{}", "%s");
        System.out.println(str);
        str = String.format(str, new Object[]{"小明", 1});
        System.out.println(str);
    }

    @Test
    public void debug() {
        int i = 1;
        int a = 2;
        System.out.println(i);
    }

    @Test
    public void debug2() {
        Student s = new Student();
        s.setId(1);
        s.setName("name");
        System.out.println(s);
    }

    @Test
    public void enumString() {
        System.out.println(EnumIndustry.REPAST.toString());
    }

    @Test
    public void map() {
        Map<String, Student> map = Maps.newHashMap();
        Student s = new Student();
        s.setId(1);
        s.setName("小明");
        map.put("1", s);

        Student st = map.get("1");
        st = new Student();
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void v() {
        Properties properties = new Properties();
        properties.put("1", "2");

        System.out.println(properties);
    }

    @Test
    public void mm() {
        System.out.println(1 % 300);
    }

    @Test
    public void format() {
        int i = 1;
        System.out.println(++i);
//        System.out.println(++i);
    }

    @Test
    public void write() {
        FileWriter writer = null;
        File file = new File("/Users/chenlijiang/Downloads/4.txt");
        try {
            writer = new FileWriter(file);
            writer.write("我们是一家人");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void write2() {
        BufferedWriter bw = null;
        File file = new File("/Users/chenlijiang/Downloads/3.txt");
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            bw.write("我们是一家人");
            bw.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void double2long() {
        System.out.println(Double.valueOf(500d).longValue());
    }

    @Test
    public void BooleanEquals() {
        System.out.println(105 * 0.01);
        System.out.println(100 * 0.01);

        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(Double.valueOf(df.format(500d * 0.01)));


        BigDecimal bg = new BigDecimal(500d * 0.01);
        System.out.println(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());


    }

    @Test
    public void b() {
        System.out.println(Boolean.valueOf("0"));
        System.out.println(Boolean.valueOf("1"));
    }

    @Test
    public void split() {
        List<String> list = Splitter.on(" ").splitToList("6933211466768 6933211466775 6933211466782 ");

        list = Lists.newArrayList(Collections2.filter(list, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return StringUtils.isNotBlank(input);
            }
        }));
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void removeAll() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        list.removeAll(Lists.newArrayList(1, 2));
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void stringEquals() {
        System.out.println(StringUtils.equals(null, null));
    }

    @Test
    public void doubleEquals() {
        System.out.println(2.12d == 2.1233d);
    }

    @Test
    public void RemoveAll() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3);
        List<Integer> list2 = Lists.newArrayList(1);
        List<Integer> list3 = (List) CollectionUtils.removeAll(list1, list2);

        System.out.println(JSON.toJSONString(list1));
        System.out.println(JSON.toJSONString(list2));
        System.out.println(JSON.toJSONString(list3));
    }

    @Test
    public void longequal() {
        Long l1 = 1l;
        Long l2 = 2l;
        System.out.println(StringUtils.equals(String.valueOf(l1), String.valueOf(l2)));
    }

    @Test
    public void testBoolean1() {
        System.out.println(Boolean.FALSE.booleanValue() == false);
    }

    @Test
    public void testListFor() {
        List<String> list = null;
        for (String l : list) {
            System.out.println(l);
        }
    }

    @Test
    public void testListToArray(){
        List<String> list = Lists.newArrayList("1","2");
        System.out.println(JSON.toJSONString(list.toArray(new String[]{})));
    }

    @Test
    public void testDouble(){
        System.out.println(Double.parseDouble("2d"));
        System.out.println(Double.parseDouble("2f"));
    }

    @Test
    public void testD(){
        double d = 36.66d;
        System.out.println(new BigDecimal(d*100).setScale(2,BigDecimal.ROUND_HALF_EVEN).intValue());
        System.out.println((int)(d*100));
    }

    @Test
    public void testF(){
        float f = 29.01f;
        System.out.println(Float.valueOf(f).doubleValue());

    }

    @Test
    public void add(){
        int i = 100;

        i*= 1 + 5/100;
        System.out.println(i);
    }

    @Test
    public void clazzEqual(){
        System.out.println(Student.class.equals(Student.class));
        System.out.println(Student.class.equals(EntityB.class));
    }

    @Test
    public void testEnum2(){
        System.out.println(EnumJavaClientType.DAO);
        System.out.println(EnumJavaClientType.DAO.name().equals("DAO"));
    }

    @Test
    public void camel(){
        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,"StudentClass"));

        System.out.println(Introspector.decapitalize("StudentClass"));
        System.out.println(Introspector.decapitalize("student_class"));
    }

    @Test
    public void eq(){
        System.out.println(2.01d == 2.01d);
    }

    @Test
    public void className(){
        System.out.println(Student.class.getName());
    }
}
