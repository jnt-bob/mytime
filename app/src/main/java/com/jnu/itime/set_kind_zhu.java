package com.jnu.itime;

import android.graphics.Bitmap;

import java.io.Serializable;

public class set_kind_zhu implements Serializable {
    private String One;
    private String Two;
    private String three;
    private Bitmap pictureId;

    public String getOne() {
        return One;
    }

    public void setOne(String one) {
        One = one;
    }

    public String getTwo() {
        return Two;
    }

    public void setTwo(String two) {
        Two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public Bitmap getPictureId() {
        return pictureId;
    }

    public void setPictureId(Bitmap pictureId) {
        this.pictureId = pictureId;
    }

    public set_kind_zhu(String one, String two, String three, Bitmap pictureId) {
        One = one;
        Two = two;
        this.three = three;
        this.pictureId = pictureId;
    }
}
