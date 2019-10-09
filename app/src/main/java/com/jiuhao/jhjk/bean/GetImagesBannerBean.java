package com.jiuhao.jhjk.bean;

public class GetImagesBannerBean {


    /**
     * id : 16
     * imgType : 2
     * imgUrl : http://jhwj.oss-cn-hangzhou.aliyuncs.com/jhjk/1568277795761.jpg?Expires=1883637796&OSSAccessKeyId=LTAI7BI8CsPY6zbY&Signature=%2F4HSG9xOo8hGOQkb%2FiDBWJJZDK4%3D
     * linkUrl : http://www.hzsysf.com/sysfShuffling3.html
     * concealState : 0
     * imgDescribe : 中国传统佳节
     * orderId : 5
     * createTime : 2019-08-08 16:20:30
     * updateTime : 2019-08-08 16:20:34
     */

    private int id;
    private int imgType;
    private String imgUrl;
    private String linkUrl;
    private int concealState;
    private String imgDescribe;
    private int orderId;
    private String createTime;
    private String updateTime;

    @Override
    public String toString() {
        return "GetImagesBannerBean{" +
                "id=" + id +
                ", imgType=" + imgType +
                ", imgUrl='" + imgUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", concealState=" + concealState +
                ", imgDescribe='" + imgDescribe + '\'' +
                ", orderId=" + orderId +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getConcealState() {
        return concealState;
    }

    public void setConcealState(int concealState) {
        this.concealState = concealState;
    }

    public String getImgDescribe() {
        return imgDescribe;
    }

    public void setImgDescribe(String imgDescribe) {
        this.imgDescribe = imgDescribe;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
