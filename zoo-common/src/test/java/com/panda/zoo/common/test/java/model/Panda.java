package com.panda.zoo.common.test.java.model;

/**
 * @author huixiangdou
 * @date 2018/1/17
 */
public class Panda extends Animal {
    private String name = "panda";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal.getName());

        Panda panda = new Panda();
        System.out.println(panda.getName());

        Animal a = (Animal) panda;
        System.out.println(a.getName());
    }
}
