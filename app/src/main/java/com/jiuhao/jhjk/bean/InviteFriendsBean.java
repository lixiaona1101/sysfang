package com.jiuhao.jhjk.bean;

/**
 * 邀请医生banner
 */
public class InviteFriendsBean {

    /**
     * inviteCode : 100273
     * imgUrl : http://jhwj.oss-cn-hangzhou.aliyuncs.com/jhjk27310001.jpg?Expires=1876786636&OSSAccessKeyId=LTAI7BI8CsPY6zbY&Signature=DHbtw4cqqXeQuvx6g2UbsvZUhJ0=
     */

    private String inviteCode;
    private String imgUrl;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "InviteFriendsBean{" +
                "inviteCode='" + inviteCode + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
