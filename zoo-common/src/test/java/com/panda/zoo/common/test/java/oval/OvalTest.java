package com.panda.zoo.common.test.java.oval;

import com.google.common.collect.Lists;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;
import net.sf.oval.constraint.NotEmptyCheck;
import org.junit.Assert;
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
    public void oval() {
        EntityC ovalC = new EntityC();
        Validator validator = new Validator();
        List<ConstraintViolation> constraintViolations = validator.validate(ovalC);
        Assert.assertTrue(constraintViolations.size() > 0);
    }


    @Test
    public void ovalCascaded() {
        EntityB entityB = new EntityB();
        entityB.setName("1");

        EntityP entityP = new EntityP();
        entityP.setId(null);
        entityB.setOvalP(entityP);

        EntityD entityD = new EntityD();
        entityD.setName("1");
        List<EntityD> entityDList = Lists.newArrayList(entityD);
        entityB.setEntityDList(entityDList);

        Validator validator = new Validator();
        List<ConstraintViolation> constraintViolations = validator.validate(entityB);
        Assert.assertTrue(constraintViolations.size() == 0);
    }

    @Test
    public void ovalProfile() {
        EntityProfile ep = new EntityProfile();
        ep.setId("1");

        Validator validator = new Validator();
        List<ConstraintViolation> constraintViolations = validator.validate(ep, Profile.INSERT);
        Assert.assertTrue(constraintViolations.size() > 0);
    }

    @Test
    public void ovalConstruct(){
        EntityConstruct ec = new EntityConstruct("");
    }

    @Test
    public void ovalWhen(){
        EntityWhen ew = new EntityWhen();
        ew.setAge(null);
        Validator validator = new Validator();
        List<ConstraintViolation> constraintViolations = validator.validate(ew);
        Assert.assertTrue(constraintViolations.size() > 0);
    }

    @Test
    public void ovalJSR(){
        EntityJSR ej = new EntityJSR();
        Validator validator = new Validator(new AnnotationsConfigurer(), new BeanValidationAnnotationsConfigurer());
        List<ConstraintViolation> constraintViolations = validator.validate(ej);
        Assert.assertTrue(constraintViolations.size() > 0);
    }

    @Test
    public void testModel(){
        TestModel testModel = new TestModel();
        testModel.setPrice(2.23);
        Validator validator = new Validator();
        List<ConstraintViolation> constraintViolations = validator.validate(testModel);
        Assert.assertTrue(constraintViolations.size() > 0);
    }
}

