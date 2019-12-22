package com.jnu.itime;

public class switch_button {
    private String One;
    private int pictureId;

    public String getOne() {
        return One;
    }

    public void setOne(String one) {
        One = one;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public switch_button(String one, int pictureId) {
        One = one;
        this.pictureId = pictureId;
    }
}
