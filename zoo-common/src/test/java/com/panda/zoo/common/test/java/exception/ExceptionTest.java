package com.panda.zoo.common.test.java.exception;

/**
 * Created by huixiangdou on 2017/4/28.
 */
public class ExceptionTest {
    private static final void m() throws OneException {
        System.out.println(1 / 0);
    }


    public static final void m2() {
        try {
            m();
        }
//        catch (TwoException e) {
//            System.out.println("TwoException");
//            e.printStackTrace();
//        }
        catch (OneException e) {
            System.out.println("OneException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        m2();
    }
}
