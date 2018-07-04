package com.panda.zoo.common.test.jdk8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.panda.zoo.common.test.java.model.Parent;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huixiangdou
 * @date 2017/12/28
 */
public class CollectionTest {

    class Inner {
        private String id;

        public Inner(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Inner inner = (Inner) o;

            return id != null ? id.equals(inner.id) : inner.id == null;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

    /**
     * 取list对象元素的某一属性组成新的list
     */
    @Test
    public void test1() {
        List<Inner> list = Lists.newArrayList();
        list.add(new Inner("1"));
        list.add(new Inner("2"));
        list.add(new Inner("2"));
        Stream stream = list.stream();
//        System.out.println("count->" + stream.count());
//        System.out.println("distinct->" + JSON.toJSONString(stream.distinct().collect(Collectors.toList())));
//        System.out.println("distinct->" + JSON.toJSONString(stream.limit(2).collect(Collectors.toList())));
        System.out.println("distinct->" + JSON.toJSONString(stream.skip(1).collect(Collectors.toList())));
    }

    @Test
    public void testFilter() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, null);
        List<Integer> newList = list.stream().map(i -> {
            if (i != null) {
                return i;
            }
            return null;
        }).filter(i -> i != null).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(newList));
    }

    @Test
    public void shortAndInteger() {

        Short s = Short.valueOf("1");
        Integer i = Integer.valueOf(1);
        System.out.println(s.shortValue() == i.intValue());

        System.out.println(null instanceof Short);
    }

    @Test
    public void distinct() {
        List<String> list = Lists.newArrayList("1", "1", "2");
        List<String> list2 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list2));
    }

    @Test
    public void removeIf() {
        List<String> listOld = Lists.newArrayList("1");
        List<String> listNew = Lists.newArrayList("1", "2");
        listNew.removeIf(s -> listOld.contains(s));
        System.out.println(JSON.toJSONString(listNew));
    }

    @Test
    public void m(){
        List<Parent> l = Lists.newArrayList();
        l.add(new Parent("1"));
        l.add(new Parent("2"));
        l.add(new Parent("3"));

        Set<String> set = Sets.newHashSet();
        set = l.stream().map(Parent::getId).collect(Collectors.toSet());
        System.out.println(JSON.toJSONString(set));
    }
}
