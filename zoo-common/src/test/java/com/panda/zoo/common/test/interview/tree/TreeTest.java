package com.panda.zoo.common.test.interview.tree;

import org.junit.Test;

/**
 * @author huixiangdou
 * @date 2019/1/16
 */
public class TreeTest {
    @Test
    public void avl() {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.insert(8);
        avlTree.insert(7);
        avlTree.insert(10);
        avlTree.insert(5);
        avlTree.insert(6);
        avlTree.printTree(); //先序打印
    }
}
