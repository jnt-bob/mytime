package com.jnu.itime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class set_kind_zhu implements Serializable {
    private String One;
    private String Two;
    private String three;
    private byte[] pictureId;


    private Bitmap byte2Bitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

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
        return byte2Bitmap(pictureId);
    }

    public void setPictureId(byte[] pictureId) {
        this.pictureId = pictureId;
    }

    public set_kind_zhu(String one, String two, String three, byte[] pictureId) {
        One = one;
        Two = two;
        this.three = three;
        this.pictureId = pictureId;
    }

}
