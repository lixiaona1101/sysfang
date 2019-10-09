package com.jiuhao.jhjk.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ShopedSelectBean implements Parcelable {

    /**
     * id : 6923
     * medId : 102520
     * medName : 大腹皮
     * medType : 2
     * medSpec : 1g
     * medUnit : g
     * medPrice : 0.12
     * medHeat : 0
     * medAlias : 槟榔皮、槟榔壳大腹毛、茯毛、槟榔衣、大腹绒
     * shopId : 10004
     * factoryId : 2
     * pinYin : DFP
     * createTime : 2019-02-26 16:04:09
     * updateTime : 2019-08-06 08:59:13
     */

    private int id;
    private int medId;
    private String medName;
    private int medType;
    private String medSpec;
    private String medUnit;
    private double medPrice;
    private int medHeat;
    private String medAlias;
    private int shopId;
    private int factoryId;
    private String pinYin;
    private String createTime;
    private String updateTime;
    private String MedFry;//煎法
    private int medNumber;//克数/袋数

    public ShopedSelectBean() {

    }

    public ShopedSelectBean(Parcel in) {
        id = in.readInt();
        medId = in.readInt();
        medName = in.readString();
        medType = in.readInt();
        medSpec = in.readString();
        medUnit = in.readString();
        medPrice = in.readDouble();
        medHeat = in.readInt();
        medAlias = in.readString();
        shopId = in.readInt();
        factoryId = in.readInt();
        pinYin = in.readString();
        createTime = in.readString();
        updateTime = in.readString();
        MedFry = in.readString();
        medNumber = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(medId);
        dest.writeString(medName);
        dest.writeInt(medType);
        dest.writeString(medSpec);
        dest.writeString(medUnit);
        dest.writeDouble(medPrice);
        dest.writeInt(medHeat);
        dest.writeString(medAlias);
        dest.writeInt(shopId);
        dest.writeInt(factoryId);
        dest.writeString(pinYin);
        dest.writeString(createTime);
        dest.writeString(updateTime);
        dest.writeString(MedFry);
        dest.writeInt(medNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShopedSelectBean> CREATOR = new Creator<ShopedSelectBean>() {
        @Override
        public ShopedSelectBean createFromParcel(Parcel in) {
            return new ShopedSelectBean(in);
        }

        @Override
        public ShopedSelectBean[] newArray(int size) {
            return new ShopedSelectBean[size];
        }
    };

    public int getMedNumber() {
        return medNumber;
    }

    public void setMedNumber(int medNumber) {
        this.medNumber = medNumber;
    }

    public String getMedFry() {
        return MedFry;
    }

    public void setMedFry(String MedFry) {
        this.MedFry = MedFry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getMedType() {
        return medType;
    }

    public void setMedType(int medType) {
        this.medType = medType;
    }

    public String getMedSpec() {
        return medSpec;
    }

    public void setMedSpec(String medSpec) {
        this.medSpec = medSpec;
    }

    public String getMedUnit() {
        return medUnit;
    }

    public void setMedUnit(String medUnit) {
        this.medUnit = medUnit;
    }

    public double getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(double medPrice) {
        this.medPrice = medPrice;
    }

    public int getMedHeat() {
        return medHeat;
    }

    public void setMedHeat(int medHeat) {
        this.medHeat = medHeat;
    }

    public String getMedAlias() {
        return medAlias;
    }

    public void setMedAlias(String medAlias) {
        this.medAlias = medAlias;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
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

    @Override
    public String toString() {
        return "ShopedSelectBean{" +
                "id=" + id +
                ", medId=" + medId +
                ", medName='" + medName + '\'' +
                ", medType=" + medType +
                ", medSpec='" + medSpec + '\'' +
                ", medUnit='" + medUnit + '\'' +
                ", medPrice=" + medPrice +
                ", medHeat=" + medHeat +
                ", medAlias='" + medAlias + '\'' +
                ", shopId=" + shopId +
                ", factoryId=" + factoryId +
                ", pinYin='" + pinYin + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
