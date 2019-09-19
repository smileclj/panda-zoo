package com.panda.zoo.common.util;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;

/**
 * @author huixiangdou
 * @date 2018/10/30
 */
public class BeanMapUtils {
    private static DozerBeanMapper mapper = new DozerBeanMapper();

    static {
        mapper.setMappingFiles(Lists.newArrayList("dozer.xml"));
    }

    public static void map(Object source, Object destination, String mapId) throws MappingException {
        mapper.map(source, destination, mapId);
    }

    /**
     * {@inheritDoc}
     */
    public static <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException {
        return mapper.map(source, destinationClass, mapId);
    }

    /**
     * {@inheritDoc}
     */
    public static <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        return mapper.map(source, destinationClass);
    }

    public static void map(Object source, Object destination) throws MappingException {
        mapper.map(source, destination);
    }
}
