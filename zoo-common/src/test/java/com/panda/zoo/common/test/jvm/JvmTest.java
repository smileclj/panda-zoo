package com.panda.zoo.common.test.jvm;

import com.google.common.collect.Lists;
import com.panda.zoo.common.test.jvm.model.Student;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by huixiangdou on 2017/5/4.
 */
public class JvmTest {
    private static int a = 1;

    static class OOMObject {
        private byte[] text = new byte[1024];

        public byte[] getText() {
            return text;
        }

        public void setText(byte[] text) {
            this.text = text;
        }
    }

    /**
     * 堆溢出
     */
    public static void heapOutOfMemory() {
        List<OOMObject> list = Lists.newArrayList();
        while (true) {
            list.add(new OOMObject());
        }
    }

    /**
     * 栈溢出(虚拟栈和本地方法栈 hotspot)
     */
    public static void stackOutOfMemory() {
        System.out.println(a++);
        stackOutOfMemory();
    }

    /**
     * 方法区溢出
     * jdk动态加载class，对于同一个class只会加载一次
     * 实现 采用cglib动态代理的方式，不断地创建子类
     */
    public static void permGenSpaceOutOfMemory() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Student.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            //通过字节码技术动态创建子类实例
            enhancer.create();
        }
    }

    /**
     * 直接内存溢出
     */
    public static void directOutOfMemory() throws InterruptedException {
        ByteBuffer.allocateDirect(1024 * 1024 * 11);
    }

    /**
     * gc
     */
    public static void gc() {
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
//        heapOutOfMemory();

//        stackOutOfMemory();

//        permGenSpaceOutOfMemory();

//        directOutOfMemory();

        gc();
    }
}
