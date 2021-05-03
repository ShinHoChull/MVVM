package com.m2comm.test.roomtest.addr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ADDR_VZZ {

    @PrimaryKey
    @ColumnInfo(name = "field1")
    private Long field1;

    @ColumnInfo(name = "ADDR_CODE")
    private String ADDR_CODE;

    @ColumnInfo(name = "ADDR_NAME")
    private String ADDR_NAME;

    @ColumnInfo(name = "ADDR_LEVEL")
    private String ADDR_LEVEL;
    @ColumnInfo(name = "GROUP_A")
    private String GROUP_A;
    @ColumnInfo(name = "GROUP_B")
    private String GROUP_B;
    @ColumnInfo(name = "GROUP_C")
    private String GROUP_C;
    @ColumnInfo(name = "ADDR_1")
    private String ADDR_1;
    @ColumnInfo(name = "ADDR_2")
    private String ADDR_2;
    @ColumnInfo(name = "ADDR_3",typeAffinity = 2)
    private String ADDR_3;
    @ColumnInfo(name = "ADDR_4",typeAffinity = 2)
    private String ADDR_4;
    @ColumnInfo(name = "ADDR_5",typeAffinity = 2)
    private String ADDR_5;
    @ColumnInfo(name = "USE_TYPE",typeAffinity = 2)
    private String USE_TYPE;
    @ColumnInfo(name = "GPS_LATITUDE_Y",typeAffinity = 2)
    private String GPS_LATITUDE_Y;
    @ColumnInfo(name = "GPS_LONGITUDE_X",typeAffinity = 2)
    private String GPS_LONGITUDE_X;
    @ColumnInfo(name = "GPS_MANUAL_YN",typeAffinity = 2)
    private String GPS_MANUAL_YN;
    @ColumnInfo(name = "ADDR_SHORT_NAME",typeAffinity = 2)
    private String ADDR_SHORT_NAME;
    @ColumnInfo(name = "ADDR_CODE_1",typeAffinity = 2)
    private String ADDR_CODE_1;
    @ColumnInfo(name = "ADDR_CODE_2",typeAffinity = 2)
    private String ADDR_CODE_2;
    @ColumnInfo(name = "ADDR_CODE_3",typeAffinity = 2)
    private String ADDR_CODE_3;
    @ColumnInfo(name = "SHORT_ADDR_1",typeAffinity = 2)
    private String SHORT_ADDR_1;
    @ColumnInfo(name = "SHORT_ADDR_2",typeAffinity = 2)
    private String SHORT_ADDR_2;


    public String getADDR_CODE() {
        return ADDR_CODE;
    }

    public void setADDR_CODE(String ADDR_CODE) {
        this.ADDR_CODE = ADDR_CODE;
    }

    public Long getField1() {
        return field1;
    }

    public void setField1(Long field1) {
        this.field1 = field1;
    }

    public String getADDR_NAME() {
        return ADDR_NAME;
    }

    public void setADDR_NAME(String ADDR_NAME) {
        this.ADDR_NAME = ADDR_NAME;
    }

    public String getADDR_LEVEL() {
        return ADDR_LEVEL;
    }

    public void setADDR_LEVEL(String ADDR_LEVEL) {
        this.ADDR_LEVEL = ADDR_LEVEL;
    }

    public String getGROUP_A() {
        return GROUP_A;
    }

    public void setGROUP_A(String GROUP_A) {
        this.GROUP_A = GROUP_A;
    }

    public String getGROUP_B() {
        return GROUP_B;
    }

    public void setGROUP_B(String GROUP_B) {
        this.GROUP_B = GROUP_B;
    }

    public String getGROUP_C() {
        return GROUP_C;
    }

    public void setGROUP_C(String GROUP_C) {
        this.GROUP_C = GROUP_C;
    }

    public String getADDR_1() {
        return ADDR_1;
    }

    public void setADDR_1(String ADDR_1) {
        this.ADDR_1 = ADDR_1;
    }

    public String getADDR_2() {
        return ADDR_2;
    }

    public void setADDR_2(String ADDR_2) {
        this.ADDR_2 = ADDR_2;
    }

    public String getADDR_3() {
        return ADDR_3;
    }

    public void setADDR_3(String ADDR_3) {
        this.ADDR_3 = ADDR_3;
    }

    public String getADDR_4() {
        return ADDR_4;
    }

    public void setADDR_4(String ADDR_4) {
        this.ADDR_4 = ADDR_4;
    }

    public String getADDR_5() {
        return ADDR_5;
    }

    public void setADDR_5(String ADDR_5) {
        this.ADDR_5 = ADDR_5;
    }

    public String getUSE_TYPE() {
        return USE_TYPE;
    }

    public void setUSE_TYPE(String USE_TYPE) {
        this.USE_TYPE = USE_TYPE;
    }

    public String getGPS_LATITUDE_Y() {
        return GPS_LATITUDE_Y;
    }

    public void setGPS_LATITUDE_Y(String GPS_LATITUDE_Y) {
        this.GPS_LATITUDE_Y = GPS_LATITUDE_Y;
    }

    public String getGPS_LONGITUDE_X() {
        return GPS_LONGITUDE_X;
    }

    public void setGPS_LONGITUDE_X(String GPS_LONGITUDE_X) {
        this.GPS_LONGITUDE_X = GPS_LONGITUDE_X;
    }

    public String getGPS_MANUAL_YN() {
        return GPS_MANUAL_YN;
    }

    public void setGPS_MANUAL_YN(String GPS_MANUAL_YN) {
        this.GPS_MANUAL_YN = GPS_MANUAL_YN;
    }

    public String getADDR_SHORT_NAME() {
        return ADDR_SHORT_NAME;
    }

    public void setADDR_SHORT_NAME(String ADDR_SHORT_NAME) {
        this.ADDR_SHORT_NAME = ADDR_SHORT_NAME;
    }

    public String getADDR_CODE_1() {
        return ADDR_CODE_1;
    }

    public void setADDR_CODE_1(String ADDR_CODE_1) {
        this.ADDR_CODE_1 = ADDR_CODE_1;
    }

    public String getADDR_CODE_2() {
        return ADDR_CODE_2;
    }

    public void setADDR_CODE_2(String ADDR_CODE_2) {
        this.ADDR_CODE_2 = ADDR_CODE_2;
    }

    public String getADDR_CODE_3() {
        return ADDR_CODE_3;
    }

    public void setADDR_CODE_3(String ADDR_CODE_3) {
        this.ADDR_CODE_3 = ADDR_CODE_3;
    }

    public String getSHORT_ADDR_1() {
        return SHORT_ADDR_1;
    }

    public void setSHORT_ADDR_1(String SHORT_ADDR_1) {
        this.SHORT_ADDR_1 = SHORT_ADDR_1;
    }

    public String getSHORT_ADDR_2() {
        return SHORT_ADDR_2;
    }

    public void setSHORT_ADDR_2(String SHORT_ADDR_2) {
        this.SHORT_ADDR_2 = SHORT_ADDR_2;
    }
}
