package com.panda.zoo.common.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.panda.zoo.common.test.java.model.EmptyField;
import com.panda.zoo.common.test.java.model.copy.StudentDO;
import com.panda.zoo.common.test.json.model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

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
    public void serializeEmptyField() {
        System.out.println(JSON.toJSONString(new EmptyField()));
    }

    @Test
    public void testNull() {
        User user = new User();

        System.out.println(JSON.toJSONString(user, new SerializerFeature[]{SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty}));
    }

    @Test
    public void testMap() {
        Map<String, User> map = new TreeMap<>();
        User user = new User();
        user.setId(1);
        user.setName("name");
        map.put(String.valueOf(user.getId()), user);

        String str = JSON.toJSONString(map);
        System.out.println(str);

        Map<String, JSONObject> map1 = JSON.parseObject(str, Map.class);
    }

    @Test
    public void testB() {
        StudentDO studentDO = new StudentDO();
        studentDO.setId(1);
        System.out.println(JSON.toJSONString(studentDO));

        StudentDO studentDO1 = JSON.parseObject(" {\"id\":\"1\",\"sex\":0}", StudentDO.class);
    }

    @Test
    public void testUser() {
        User user = new User();
        user.setId(1);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void testString(){
        System.out.println(JSON.toJSONString("22222"));
    }
}
