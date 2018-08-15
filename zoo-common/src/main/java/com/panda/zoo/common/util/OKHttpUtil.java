package com.panda.zoo.common.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 远程文件操作工具类
 * 不填projectName默认为zmfile，域名对应为ifiletest或者ifile
 * 如果是有特殊需求，可以向架构申请projectName
 *
 * @author huixiangdou
 * @date 2018/7/25
 */
public class OKHttpUtil {
    public static final String OFFLINE_URL = "http://upload.2dfire-daily.com/uploadfile";
    public static final String ONLINE_URL = "http://upload.2dfire-inc.com/uploadfile";
    public static int CONNECTION_TIMEOUT = 15;
    public static int READ_TIMEOUT = 15;
    public static final String URL = EnvUtil.isOnLine() ? ONLINE_URL : OFFLINE_URL;
    public static final String DEFAULT_PROJECT_NAME = "zmfile";
    public static final String FILE_MEDIA_TYPE = "application/octet-stream";

    public static final String ZM_DOWNLOAD_DOMAIN_OFFLINE = "https://ifiletest.2dfire.com/";
    public static final String ZM_DOWNLOAD_DOMAIN_ONLINE = "https://ifile.2dfire.com/";

    public static final String ZM_DOWNLOAD_DOMAIN = EnvUtil.isOnLine() ? ZM_DOWNLOAD_DOMAIN_ONLINE : ZM_DOWNLOAD_DOMAIN_OFFLINE;
    public static final String COMMON_DOWNLOAD_DOMAIN = "https://assets.2dfire.com/";

    public static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();

    public static String uploadFile(CommonsMultipartFile file, String path) {
        return uploadFile(file, path, DEFAULT_PROJECT_NAME);
    }

    public static String uploadFile(CommonsMultipartFile file, String path, String projectName) {
        DiskFileItem diskFileItem = (DiskFileItem) file.getFileItem();
        if (diskFileItem == null) {
            throw new IllegalArgumentException("file not found");
        }
        if (diskFileItem.isInMemory()) {
            return uploadFile(diskFileItem.get(), file.getName(), path, projectName);
        } else {
            return uploadFile(diskFileItem.getStoreLocation(), path, projectName);
        }
    }

    public static String uploadFile(File file, String path) {
        return uploadFile(file, path, DEFAULT_PROJECT_NAME);
    }

    public static String uploadFile(File file, String path, String projectName) {
        RequestBody fileBody = RequestBody.create(MediaType.get(FILE_MEDIA_TYPE), file);
        return uploadFile(fileBody, file.getName(), path, projectName);
    }

    public static String uploadFile(byte[] file, String fileName, String path, String projectName) {
        RequestBody fileBody = RequestBody.create(MediaType.get(FILE_MEDIA_TYPE), file);
        return uploadFile(fileBody, fileName, path, projectName);
    }

    private static String uploadFile(RequestBody fileBody, String fileName, String path, String projectName) {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("path must be not empty");
        }
        if (StringUtils.isBlank(projectName)) {
            projectName = DEFAULT_PROJECT_NAME;
        }
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("projectName", projectName)
                .addFormDataPart("path", path)
                .addFormDataPart("file", fileName, fileBody).build();

        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            String bodyString = response.body().string();
            if (StringUtils.isBlank(bodyString)) {
                throw new RuntimeException("result is empty");
            }
            Result result = JSON.parseObject(bodyString, Result.class);
            if (StringUtils.isBlank(result.getData())) {
                throw new RuntimeException("file upload fail");
            }
            return assemblePath(projectName, result.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String assemblePath(String projectName, String path) {
        String domain;
        if (DEFAULT_PROJECT_NAME.equals(projectName)) {
            domain = ZM_DOWNLOAD_DOMAIN;
        } else {
            domain = COMMON_DOWNLOAD_DOMAIN;
        }
        return domain + path;
    }

    private static class Result implements Serializable {
        private Integer code;
        private String data;
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File(OKHttpUtil.class.getResource("/a.png").toURI());
            String response1 = uploadFile(file, "99932535/menu");
            System.out.println(response1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
