package com.panda.zoo.common.test.java.model2;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huixiangdou
 * @date 2018/10/30
 */
public class ComplexModel2 {
    private Same1 same1;
    private Date d;
    private List<String> list;
    private List<Same1> same1List;
    private Map<String,Same1> map;
    private EnumType type;

    public Same1 getSame1() {
        return same1;
    }

    public void setSame1(Same1 same1) {
        this.same1 = same1;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<Same1> getSame1List() {
        return same1List;
    }

    public void setSame1List(List<Same1> same1List) {
        this.same1List = same1List;
    }

    public Map<String, Same1> getMap() {
        return map;
    }

    public void setMap(Map<String, Same1> map) {
        this.map = map;
    }

    public EnumType getType() {
        return type;
    }

    public void setType(EnumType type) {
        this.type = type;
    }
}
