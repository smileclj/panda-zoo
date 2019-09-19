package com.panda.zoo.mvc.aware;

import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.instrument.classloading.LoadTimeWeaver;

/**
 * @author huixiangdou
 * @date 2019/8/27
 */
public class MyLoadTimeWeaverAware implements LoadTimeWeaverAware {
    @Override
    public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {

    }
}
