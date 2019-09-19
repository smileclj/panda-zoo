package com.panda.zoo.common.test.java.model2;

import java.io.Serializable;

/**
 * Created by qinghao on 2017/7/25.
 */
public class AuditImgVo implements Serializable {
    private static final long serialVersionUID = 50771311723115154L;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }


    private String imgId; //图片id
    private String kind; //图片类型
    private String memo;  //图片描述
    private String path;
    private String server;
    private String url;   //图片url
    private Byte [] content ; //图片内容


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Byte[] getContent() {
        return content;
    }

    public void setContent(Byte[] content) {
        this.content = content;
    }
}



