package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.panda.zoo.common.test.java.model.*;
import org.junit.Test;

import java.io.*;
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
        Parent.id = "1";
        Child1.id = "2";
        Child2.id = "3";
        Child1 c1 = new Child1();
        c1.setId("4");
        System.out.println(Parent.id);
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
    public void ab(){
        AbstractModel abstractModel = new AbstractModel() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }
}
