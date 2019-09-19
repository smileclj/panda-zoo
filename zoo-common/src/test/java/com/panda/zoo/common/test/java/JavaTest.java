package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.TypeUtils;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.google.common.base.CaseFormat;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.panda.zoo.common.test.java.degrade.DegradeRule;
import com.panda.zoo.common.test.java.enums.EnumIndustry;
import com.panda.zoo.common.test.java.hibernate.EntityB;
import com.panda.zoo.common.test.java.model.*;
import com.panda.zoo.common.test.java.model2.*;
import com.panda.zoo.common.test.jvm.model.Student;
import com.panda.zoo.common.util.BeanMapUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.beans.Introspector;
import java.io.*;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

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
    public void testListToArray() {
        List<String> list = Lists.newArrayList("1", "2");
        System.out.println(JSON.toJSONString(list.toArray(new String[]{})));
    }

    @Test
    public void testDouble() {
        System.out.println(Double.parseDouble("2d"));
        System.out.println(Double.parseDouble("2f"));
    }

    @Test
    public void testD() {
        double d = 36.66d;
        System.out.println(new BigDecimal(d * 100).setScale(2, BigDecimal.ROUND_HALF_EVEN).intValue());
        System.out.println((int) (d * 100));
    }

    @Test
    public void testF() {
        float f = 29.01f;
        System.out.println(Float.valueOf(f).doubleValue());

    }

    @Test
    public void add() {
        int i = 100;

        i *= 1 + 5 / 100;
        System.out.println(i);
    }

    @Test
    public void clazzEqual() {
        System.out.println(Student.class.equals(Student.class));
        System.out.println(Student.class.equals(EntityB.class));
    }

    @Test
    public void testEnum2() {
        System.out.println(EnumJavaClientType.DAO);
        System.out.println(EnumJavaClientType.DAO.name().equals("DAO"));
    }

    @Test
    public void camel() {
        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "StudentClass"));

        System.out.println(Introspector.decapitalize("StudentClass"));
        System.out.println(Introspector.decapitalize("student_class"));
    }

    @Test
    public void eq() {
        System.out.println(2.01d == 2.01d);
    }

    @Test
    public void className() {
        System.out.println(Student.class.getName());
    }

    @Test
    public void toCharArray() {
        char[] cs = "MULTI_".toCharArray();
        byte[] bs1 = new byte[12];
        for (char c : cs) {
            ArrayUtils.addAll(bs1, charToByte(c));
        }

        byte[] bs2 = "MULTI_".getBytes();
    }

    @Test
    public void AndAnd() {
        System.out.println(getFalse() && getTrue());
        System.out.println("===================");
        System.out.println(getFalse() & getTrue());
        System.out.println("===================");
        System.out.println(getTrue() && getFalse());
        System.out.println("===================");
        System.out.println(getTrue() & getFalse());
    }

    private byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    private boolean getFalse() {
        System.out.println("getFalse");
        return false;
    }

    private boolean getTrue() {
        System.out.println("getTrue");
        return true;
    }

    @Test
    public void jBarCode() throws Exception {
        String sourceCode = "123";
        JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
        String code = sourceCode + localJBarcode.calcCheckSum(sourceCode);
        System.out.println(code);
    }

    @Test
    public void format2() throws Exception {
        System.out.println(String.format("%09d", 1));
    }

    @Test
    public void encode() throws Exception {
        System.out.println(URLEncoder.encode("水果", "utf-8"));
    }

    @Test
    public void toList() {
        List<String> menuIdList = Lists.newArrayList("1", "2");
        Map<String, Boolean> map = menuIdList.stream().collect(Collectors.toMap(Function.identity(), t -> {
            System.out.println(t);
            return false;
        }, (x, y) -> y));
        System.out.println(map);
    }

    @Test
    public void seri() {
        Seri seri = new Seri();
        Child1 c1 = new Child1();
        c1.setId("id");
        c1.setC1("c1");
        seri.setP(c1);
        System.out.printf(JSON.toJSONString(seri));
    }

    @Test
    public void stringBuilder() {
        StringBuilder sb = new StringBuilder(2);
        sb.append("222222");
        System.out.println(sb.toString());
    }

    @Test
    public void s() {
        System.out.println(String.valueOf(new Student()));

        Long l = new Long(1);
        System.out.println(String.valueOf(l));
    }

    @Test
    public void jsonTest() {
        List<String> str = Lists.newArrayList("1", "2");
        List<Long> ll = JSONArray.parseArray(JSON.toJSONString(str), Long.class);
    }

//    @Test
//    public void typereferance() {
//        TypeReference<User<Integer>> typeReference = new TypeReference<User<Integer>>() {
//        };
//
//        User<Integer> user = load(typeReference);
//        System.out.println(user);
//    }

    @Test
    public void loadclass() throws Exception {
        TypeReference<User<Integer>> typeReference = new TypeReference<User<Integer>>() {
        };

        Class clazz = TypeUtils.loadClass(typeReference.getType().getTypeName());
    }

    private <T> T load(Type type) {

        return null;
    }

    @Test
    public void ss() {
        User user = new User<>();
        user.setT(1);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void testListObject() {
        Student s1 = new Student(1, "1");
        Student s2 = new Student(2, "2");
        List<Student> studentList = Lists.newArrayList(s1, s2);
        System.out.println(JSON.toJSONString(studentList));

        Student s3 = new Student(3, "3");
        studentList.set(1, s3);

        System.out.println(JSON.toJSONString(studentList));
    }

    @Test
    public void testObjectRef() {
        Info info = new Info();
        Address address1 = new Address("1");
        info.setAddress(address1);

        address1 = new Address("2");

        System.out.println(JSON.toJSONString(info));
    }

    @Test
    public void testCategory() {
        CategoryEntry categoryEntry = new CategoryEntry();
        AddItemEntry addItemEntry = new AddItemEntry();
        addItemEntry.setSelected(true);
        addItemEntry.setItemId("1");
        addItemEntry.setItemName("1");
        categoryEntry.setItemList(Lists.newArrayList(addItemEntry));
        System.out.println(JSON.toJSONString(categoryEntry));

    }

    @Test
    public void testPP() {
        AddItemEntry itemEntry = new AddItemEntry();
        itemEntry.setSelected(true);
        itemEntry.setItemId("1");
        itemEntry.setItemName("1");
        ItemEntry itemEntry1 = itemEntry;

        Field[] fields = itemEntry1.getClass().getDeclaredFields();

        Field[] fields2 = itemEntry1.getClass().getFields();

        System.out.println(JSON.toJSONString(itemEntry1));
    }

    @Test
    public void testFastJsonDefaultValue() {
        String str = "{}";
        Item item = JSON.parseObject(str, Item.class);
        System.out.println(item);
    }

    @Test
    public void smallToBig() {
        RepastItemReq repastItemReq = new RepastItemReq();
        repastItemReq.setId("1");
        repastItemReq.setName("小明");
        ItemReq itemReq = (ItemReq) repastItemReq;
        System.out.println(JSON.toJSONString(itemReq));
    }

    @Test
    public void bigToSmall() {
        ItemReq itemReq = new ItemReq();
        itemReq.setId("1");
        RepastItemReq repastItemReq = (RepastItemReq) itemReq;
        System.out.println(JSON.toJSONString(repastItemReq));
    }

    @Test
    public void testy() {
        Boolean b = new Boolean(true);
        System.out.println(!b);

        Integer i = new Integer(0);
        System.out.println(i < 5600);
    }

    @Test
    public void testYu() {
        System.out.println(1 & 4);
    }

    @Test
    public void testDozer() {
        CopyModel source = new CopyModel();
        source.setList(Lists.newArrayList("1"));

        CopyModel target = new CopyModel();
        target.setList(Lists.newArrayList("2"));

        BeanMapUtils.map(source, target);

        System.out.println(JSON.toJSONString(target));
    }

    @Test
    public void sss() {
        File dir = new File("/opt/tmp/a/b");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void cc() {
        System.out.println(StringUtils.upperCase("abc_cde"));
    }

    @Test
    public void dd() {
        System.out.println(1 / 100);
    }

    @Test
    public void JsonNum() {
        NumberModel numberModel = new NumberModel();
        numberModel.setD(1d);

        System.out.println(JSON.toJSONString(numberModel));
    }

    @Test
    public void mmmm() {
        System.out.println(100 / 100);
        System.out.println(100 / 100.0);
    }

    @Test
    public void splitter() {
        String clickUrl = "http://www.baidu.com";
        System.out.println(JSON.toJSONString(Splitter.on("?").splitToList(clickUrl)));
    }

    @Test
    public void bai() {
        System.out.println(0 % 6);
        System.out.println(1 % 6);
        System.out.println(0 / 6);
    }

    public static void main(String[] args) {
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//        pool.execute(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("a");
//        });
//
//        pool.execute(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("b");
//        });

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("a");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b");
        }).start();

        System.out.println("main");
    }

    @Test
    public void adapter() {
        System.out.println(Object.class.equals(Object.class));
        System.out.println(Student.class.isInstance(Object.class));
        System.out.println(Student.class.isAssignableFrom(Object.class));
    }

    @Test
    public void testDataOutPutStream() {
        int v = 2147400000;
        System.out.println(StringUtils.leftPad(Integer.toBinaryString(v), 32, "0"));
        System.out.println((v >>> 24));
        System.out.println((v >>> 16));
        System.out.println((v >>> 8));
        System.out.println((v >>> 0));
        System.out.println("===========================");
        System.out.println((v >>> 24) & 0xFF);
        System.out.println((v >>> 16) & 0xFF);
        System.out.println((v >>> 8) & 0xFF);
        System.out.println((v >>> 0) & 0xFF);
    }

    @Test
    public void security() {
        System.out.println(System.getSecurityManager());
    }

    @Test
    public void parseJar() throws Exception {
        // 项目中jar包所在物理路径
        URL url = this.getClass().getResource("/boss-center-client-1.0.74-cloudcash-item-SNAPSHOT.jar");
        URL url2 = this.getClass().getResource("/twodfire-util-1.4.0.jar");
        URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url, url2}, Thread.currentThread().getContextClassLoader());

        JarFile jarFile = new JarFile(new File(url.toURI()));
        Enumeration<JarEntry> entrys = jarFile.entries();
        while (entrys.hasMoreElements()) {
            JarEntry jarEntry = entrys.nextElement();
            System.out.println(jarEntry.getName());
        }
        Class clazz1 = myClassLoader.loadClass("com.twodfire.share.result.Result");
        Class clazz = myClassLoader.loadClass("com.dfire.soa.boss.center.item.facade.service.IItemFacadeService");

        Method[] methods = clazz.getDeclaredMethods();

        clazz.getMethods();

        for (Method m : methods) {
            System.out.println(m.getName());
            System.out.println(m.toGenericString());

        }
    }

    @Test
    public void binary() {
        int a = 0b1010;
        int b = 0b101010100101001001000;
        System.out.println(Integer.toBinaryString(a & b));
    }

    @Test
    public void resize() {
        int n = 5;// 17
        System.out.println("n |= n >>> 1 --> " + (n |= n >>> 1));
        System.out.println("n |= n >>> 2 --> " + (n |= n >>> 2));
        System.out.println("n |= n >>> 4 --> " + (n |= n >>> 4));
        System.out.println("n |= n >>> 8 --> " + (n |= n >>> 8));
        System.out.println("n |= n >>> 16 --> " + (n |= n >>> 16));
        int result = (n < 0) ? 1 : n + 1;
        System.out.println(result);
    }

    @Test
    public void testIf() {
        int a = -1, b = 4;
        if (a > 0) {
            System.out.println("a > 0");
        } else if (b < 5) {
            System.out.println("a < 0 & b < 5");
        } else {
            System.out.println("a < 0 & b >= 5");
        }
    }

    @Test
    public void testSort() {
        List<Integer> list = Lists.newArrayList(1, 0, 1, 0);
        list.sort((a, b) -> b - a);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void ceil() {
        System.out.println(Math.ceil(5.0 / 2));
        System.out.println(5.0 / 2);
    }

    @Test
    public void yu() {
        System.out.println(1 | 2 | 4);
    }

    @Test
    public void yichu() {
        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE + 2);
    }

    @Test
    public void chuyi() {
        System.out.println(1 / 2);
        System.out.println(1 % 2);
    }

    @Test
    public void stringFormat() {
        System.out.println(String.format("%s可享受贷款", "开通"));

        DecimalFormat format = new DecimalFormat("###,###");
        System.out.println(format.format(1000));
        System.out.println(format.format(1000000));
        System.out.println(format.format(1000000000));
    }

    @Test
    public void calculate() {
        System.out.println(1 << 30);
        System.out.println(Math.pow(2, 30));
    }

    @Test
    public void mmap() {
        File file = new File("mmap");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
            byte[] bs = new byte[1024 * 2];
            for (int i = 0; i < bs.length; i++) {
                bs[i] = (byte) 1;
            }
            mappedByteBuffer.put(bs);
            System.out.println("11");
            mappedByteBuffer.force();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void seril() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Student s = new Student();
        oos.writeObject(s);
    }

    @Test
    public void seril2() throws Exception {
        Hessian2Output hout = new Hessian2Output();
        Student s = new Student();
        hout.writeObject(s);

        Hessian2Input hiput = new Hessian2Input();
        hiput.readObject();
    }

    @Test
    public void toS(){
        System.out.println("份".toLowerCase());
    }

    @Test
    public void ddd(){
        String source = "[{\"count\":10.0,\"grade\":2,\"limitApp\":\"default\",\"passCount\":0,\"resource\":\"ad_xunfa\",\"timeWindow\":60}]";
        List<DegradeRule> degradeRuleList = JSON.parseObject(source, new TypeReference<List<DegradeRule>>(){});
        System.out.println(degradeRuleList);
    }

    @Test
    public void testThrow(){
        try {
            System.out.println(1/0);
        } finally {
            System.out.println("finally");
        }
    }


    @Test
    public void indexOfString(){
        String couponName = "发： 49.99元";
        String separator = "：";
        if(couponName.contains("：")){
            couponName = couponName.substring(0,couponName.indexOf(separator));
        }
        System.out.println(couponName);
    }

}
