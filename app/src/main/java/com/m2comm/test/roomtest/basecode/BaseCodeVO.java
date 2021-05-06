package com.m2comm.test.roomtest.basecode;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BaseCodeVO {

    public BaseCodeVO(String CODE,
                      int DISPLAY_INDEX,
                      String GROUP_ID,
                      String NAME,
                      String ETC1,
                      String ETC2,
                      String ETC3
                      ) {
        this.CODE = CODE;
        this.DISPLAY_INDEX = DISPLAY_INDEX;
        this.GROUP_ID = GROUP_ID;
        this.NAME = NAME;
        this.ETC1 = ETC1;
        this.ETC2 = ETC2;
        this.ETC3 = ETC3;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String CODE;
    private int DISPLAY_INDEX;
    private String GROUP_ID;
    private String NAME;
    private String ETC1;
    private String ETC2;
    private String ETC3;
    private String ETC4;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public int getDISPLAY_INDEX() {
        return DISPLAY_INDEX;
    }

    public void setDISPLAY_INDEX(int DISPLAY_INDEX) {
        this.DISPLAY_INDEX = DISPLAY_INDEX;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getETC1() {
        return ETC1;
    }

    public void setETC1(String ETC1) {
        this.ETC1 = ETC1;
    }

    public String getETC2() {
        return ETC2;
    }

    public void setETC2(String ETC2) {
        this.ETC2 = ETC2;
    }

    public String getETC3() {
        return ETC3;
    }

    public void setETC3(String ETC3) {
        this.ETC3 = ETC3;
    }

    public String getETC4() {
        return ETC4;
    }

    public void setETC4(String ETC4) {
        this.ETC4 = ETC4;
    }

    @Override
    public String toString() {
        return "BaseCodeVO{" +
                "id=" + id +
                ", CODE='" + CODE + '\'' +
                ", DISPLAY_INDEX=" + DISPLAY_INDEX +
                ", GROUP_ID='" + GROUP_ID + '\'' +
                ", NAME='" + NAME + '\'' +
                ", ETC1='" + ETC1 + '\'' +
                ", ETC2='" + ETC2 + '\'' +
                ", ETC3='" + ETC3 + '\'' +
                ", ETC4='" + ETC4 + '\'' +
                '}';
    }
}
