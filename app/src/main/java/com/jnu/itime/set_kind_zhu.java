package com.jnu.itime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class set_kind_zhu implements Serializable {
    private String day_cha;
    private String title;
    private String day;
    private String time;
    private byte[] pictureId;
    private String bei;
    private int fu;

    public void setDay_cha(String day_cha) {
        this.day_cha = day_cha;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBei() {
        return bei;
    }

    public void setBei(String bei) {
        this.bei = bei;
    }

    public int getFu() {
        return fu;
    }

    public void setFu(int fu) {
        this.fu = fu;
    }

    private Bitmap byte2Bitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    public String getDay_cha() {
        return day_cha;
    }

    public boolean setDay_cha() {
        ArrayList<Integer> day_z=zhuan(day);
        ArrayList<Integer> time_z=zhuan(time);
        Calendar cal=Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date=cal.getTime();


        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date toDate1 = null;
        int days=0;
        int hours=0;
        try {
            //toDate1 = simpleFormat.parse("2018-03-12 12:00");
            toDate1 = simpleFormat.parse(day_z.get(0)+"-"+day_z.get(1)+"-"+day_z.get(2)+" "+time_z.get(0)+":"+time_z.get(1));
            long from1 = date.getTime();
            long to1 = toDate1.getTime();
            days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
            hours = (int) ((to1 - from1) / (1000 * 60 * 60));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dates = String.valueOf(cal.get(Calendar.DATE));
        int x = Integer.parseInt( dates );
        if(x==day_z.get(2)) {
            day_cha = "今天";
            return true;
        }
        else if(day_z.get(2)>x) {
            day_cha = "只剩" + "\n" + (day_z.get(2) - x) + "  天";
            return true;
        }
        else{
            day_cha = "过了" + "\n" + (x - day_z.get(2)) + "  天";
            return false;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Bitmap getPictureId() {
        return byte2Bitmap(pictureId);
    }

    public void setPictureId(byte[] pictureId) {
        this.pictureId = pictureId;
    }
    public ArrayList<Integer> zhuan(String date)
    {
        ArrayList<Integer> re=new ArrayList<Integer>();
        date = date.trim();
        String str2 = "";
        if (date != null && !"".equals(date)) {
            for (int i = 0; i < date.length(); i++) {
                {
                    if (date.charAt(i) >= 48 && date.charAt(i) <= 57) {
                        str2 += date.charAt(i);
                    }
                    else
                    {
                        if(!str2.isEmpty())
                        {
                            int w=Integer.parseInt( str2 );
                            re.add(w);
                            str2="";
                        }
                    }
                }
            }
            if(!str2.isEmpty())
            {
                int w=Integer.parseInt( str2 );
                re.add(w);
            }
        }
        return re;
    }


    public set_kind_zhu(String one, String two, String time,byte[] pictureId,String bei,int fu) {
        title = one;
        day = two;
        this.time=time;
        this.pictureId = pictureId;
        this.fu=fu;
        this.bei=bei;
        setDay_cha();
    }

}
