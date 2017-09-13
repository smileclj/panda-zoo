package com.panda.zoo.common.test.java;

import com.google.common.collect.Lists;
import com.panda.zoo.common.test.java.model.oval.OvalB;
import com.panda.zoo.common.test.java.model.oval.OvalC;
import com.panda.zoo.common.test.java.model.oval.OvalP;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.NotEmptyCheck;
import org.junit.Test;

import java.util.List;

/**
 * Created by huixiangdou on 2017/6/29.
 */
public class OvalTest {
    @Test
    public void NotEmptyCheck() {
        List<String> list = Lists.newArrayList();
        String str = "";
        System.out.println(new NotEmptyCheck().isSatisfied(null, str, null, null));
    }

    @Test
    public void ovalExtend() {
        OvalC ovalC = new OvalC();

        Validator validator = new Validator();
        List<ConstraintViolation> constraintViolations = validator.validate(ovalC);

        System.out.println(constraintViolations);
    }

    /**
     * oval不能校验对象里的对象
     */
    @Test
    public void ovalContain() {
        OvalB ovalB = new OvalB();
        ovalB.setName("1");
        OvalP ovalP = new OvalP();
        ovalB.setOvalP(ovalP);

        Validator validator = new Validator();
        List<ConstraintViolation> constraintViolations = validator.validate(ovalB);

        System.out.println(constraintViolations);
    }
}
