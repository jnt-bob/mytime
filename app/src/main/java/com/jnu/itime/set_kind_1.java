package com.jnu.itime;

public class set_kind_1 {
    private String One;
    private String Two;
    private int pictureId;

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

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public set_kind_1(String one, String two, int pictureId) {
        One = one;
        Two = two;
        this.pictureId = pictureId;
    }
}
