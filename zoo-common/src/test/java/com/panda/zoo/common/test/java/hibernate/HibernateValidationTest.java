package com.panda.zoo.common.test.java.hibernate;

import org.junit.Test;

import javax.validation.*;
import java.util.Set;

/**
 * @author huixiangdou
 * @date 2018/7/5
 */
public class HibernateValidationTest {

    @Test
    public void validate(){
        EntityB ovalB = new EntityB();
        ovalB.setName("1");
        EntityP ovalP = new EntityP();
        ovalB.setOvalP(ovalP);

        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<EntityB>> constraintViolationSet = validator.validate(ovalB);
    }
}
