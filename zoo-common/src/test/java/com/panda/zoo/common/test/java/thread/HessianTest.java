package com.panda.zoo.common.test.java.thread;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.google.common.collect.Lists;
import com.panda.zoo.common.test.java.model2.AuditImgVo;
import com.panda.zoo.common.test.java.model2.NewPaymentAccAuditAttrVo;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author huixiangdou
 * @date 2018/10/17
 */
public class HessianTest {

    @Test
    public void m() throws Exception{
        NewPaymentAccAuditAttrVo n = new NewPaymentAccAuditAttrVo();
        n.setAbbreviation("1.0");
        AuditImgVo auditImgVo = new AuditImgVo();
        auditImgVo.setKind("1");
        n.setAuditImgVos(Lists.newArrayList(auditImgVo));

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        //Hessian的序列化输出
        HessianOutput ho = new HessianOutput(os);

        ho.writeObject(n);

        byte[] zhansanByte = os.toByteArray();

        ByteArrayInputStream is = new ByteArrayInputStream(zhansanByte);
        //Hessian的反序列化读取对象
        HessianInput hi = new HessianInput(is);
        NewPaymentAccAuditAttrVo n2 = (NewPaymentAccAuditAttrVo)hi.readObject();
    }
}
