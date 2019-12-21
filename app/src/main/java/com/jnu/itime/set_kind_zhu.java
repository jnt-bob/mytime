package com.jnu.itime;

public class set_kind_zhu {
    private String One;
    private String Two;
    private String three;
    private int pictureId;

    public set_kind_zhu(String one, String two, String three, int pictureId) {
        One = one;
        Two = two;
        this.three = three;
        this.pictureId = pictureId;
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

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
