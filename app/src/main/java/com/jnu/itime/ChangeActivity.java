package com.jnu.itime;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<switch_button> theset2;
    private GoodsArrayAdapter2 theAdaper2;
    private ImageView re;
    private ImageView delect;
    private ImageView change;
    private TextView titles;
    private TextView days;
    private TextView dao;
    private RelativeLayout relativeLayout;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change);

        titles=findViewById(R.id.title);
        days=findViewById(R.id.day);
        dao=findViewById(R.id.dao);
        relativeLayout=findViewById(R.id.relativelayout);

        String title= getIntent().getStringExtra("title");
        String bei= getIntent().getStringExtra("bei");
        String day= getIntent().getStringExtra("day");
        String time= getIntent().getStringExtra("time");
        int fu= getIntent().getIntExtra("fu",0);
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("PictureId");
        position=getIntent().getIntExtra("position",0);

        titles.setText(title);
        days.setText(day+" "+time);
        Bitmap bitmap1=imageScale(bitmap,5,5);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap1);
        relativeLayout.setBackground(drawable);

        ArrayList<Integer> day_z=zhuan(day);
        ArrayList<Integer> time_z=zhuan(time);
        Calendar cal=Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date=cal.getTime();


        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date toDate1 = null;
        long dayss=0;
        long hours=0;
        long min=0;
        long secord=0;
        try {
            //toDate1 = simpleFormat.parse("2018-03-12 12:00");
            toDate1 = simpleFormat.parse(day_z.get(0)+"-"+day_z.get(1)+"-"+day_z.get(2)+" "+time_z.get(0)+":"+time_z.get(1));
            long from1 = date.getTime();
            long to1 = toDate1.getTime();
            if(to1>from1) {
                dayss = ((to1 - from1) / (1000 * 60 * 60 * 24));
                hours =  ((to1 - from1 - dayss * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                min =  ((to1 - from1 - dayss * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60));
                secord = ((to1 - from1 - dayss * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - min * (1000 * 60)) / (1000));
            }
            else
            {
                dayss =  ((from1-to1) / (1000 * 60 * 60 * 24));
                hours =  ((from1-to1 - dayss * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                min = ((from1-to1 - dayss * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60));
                secord =  ((from1-to1 - dayss * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - min * (1000 * 60)) / (1000));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dao.setText(dayss+"天 "+hours+"小时 "+min+"分钟 "+secord+"秒");

        re=findViewById(R.id.re);
        delect=findViewById(R.id.delect);
        change=findViewById(R.id.change);

        re.setOnClickListener(this);
        delect.setOnClickListener(this);
        change.setOnClickListener(this);

        theset2 = new ArrayList<switch_button>();
        theset2.add(new switch_button("通知栏", R.drawable.picture));
        theset2.add(new switch_button("在日历中显示", R.drawable.qian));
        theset2.add(new switch_button("快捷图标", R.drawable.picture));
        theset2.add(new switch_button("悬浮窗口", R.drawable.qian));
        theAdaper2 = new GoodsArrayAdapter2(this, R.layout.switch_button, theset2);
        ListView listViewSuper2 = (ListView) findViewById(R.id.list_view_goods_2);
        listViewSuper2.setAdapter(theAdaper2);
    }

    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) * src_w;
        float scale_h = ((float) dst_h) * src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix, true);
        return dstbmp;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.re:
                Intent intent1 = new Intent(this, ND.class);
                startActivity(intent1);
                break;
            case R.id.delect:
                //Toast.makeText(ChangeActivity.this, "点击了确定！", Toast.LENGTH_LONG).show();
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(ChangeActivity.this);
                //normalDialog.setIcon(R.drawable.buttom_yello);
                //normalDialog.setTitle("清除缓存");
                normalDialog.setMessage("是否删除该计时");
                normalDialog.setPositiveButton("确定",
                                               new DialogInterface.OnClickListener() {
                                                   @Override
                                                   public void onClick(DialogInterface dialog, int which) {
                                                       //Toast.makeText(ChangeActivity.this, "点击了确定！", Toast.LENGTH_LONG).show();
                                                       Intent intent = new Intent(ChangeActivity.this, ND.class);
                                                       intent.putExtra("position", position);
                                                       setResult(RESULT_CANCELED, intent);
                                                       ChangeActivity.this.finish();
                                                   }
                                               });
                normalDialog.setNegativeButton("取消",
                                               new DialogInterface.OnClickListener() {
                                                   @Override
                                                   public void onClick(DialogInterface dialog, int which) {
                                                       //Toast.makeText(ChangeActivity.this,"点击了取消！",Toast.LENGTH_LONG).show();
                                                   }
                                               });
                normalDialog.show();
                break;
            case R.id.change:
                break;
        }
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

    class GoodsArrayAdapter2 extends ArrayAdapter<switch_button> {
        private int resourceId;

        public GoodsArrayAdapter2(@NonNull Context context, @LayoutRes int resource, @NonNull List<switch_button> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater mInflater = LayoutInflater.from(this.getContext());
            View item = mInflater.inflate(this.resourceId, null);

            ImageView img = (ImageView) item.findViewById(R.id.image_view);
            TextView string1 = (TextView) item.findViewById(R.id.text_view_string1);

            switch_button item1 = this.getItem(position);
            img.setImageResource(item1.getPictureId());
            string1.setText(item1.getOne());

            return item;
        }
    }
}
