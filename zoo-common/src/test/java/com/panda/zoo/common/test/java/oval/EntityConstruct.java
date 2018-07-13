package com.panda.zoo.common.test.java.oval;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * @author huixiangdou
 * @date 2018/7/10
 */
@Guarded
public class EntityConstruct {
    private String id;

    public EntityConstruct(@NotNull @NotBlank String id) {
        this.id = id;
    }
}
