package com.panda.zoo.common.test.json;

import com.alibaba.fastjson.JSON;
import com.panda.zoo.common.test.java.model.EmptyField;
import com.panda.zoo.common.test.json.model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * Created by huixiangdou on 2017/3/1.
 */
public class FastJsonTest {
    /**
     * 测试反序列化新增字段
     */
    @Test
    public void addNewField() {
        String userJsonStr = "{\"id\":1}";
        User user = JSON.parseObject(userJsonStr, User.class);
    }

    @Test
    public void serializeFile() throws Exception {
        FileItemFactory factory = new DiskFileItemFactory(10240, new File("~/TransferStation/"));
        FileItem fileItem = factory.createItem("file", "text/plain", true,
                "a.jpg");
        fileItem.getOutputStream();
        MultipartFile file = new CommonsMultipartFile(fileItem);
        System.out.println(JSON.toJSONString(file));
    }

    @Test
    public void serializeEmptyField(){
        System.out.println(JSON.toJSONString(new EmptyField()));
    }
}
