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
import android.os.Handler;
import android.os.Message;
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

import com.jnu.itime.ui.home.HomeFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

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

    private long dayss=0;
    private long hours=0;
    private long min=0;
    private long secord=0;


    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change);

        titles=findViewById(R.id.title);
        days=findViewById(R.id.day);
        dao=findViewById(R.id.dao);
        relativeLayout=findViewById(R.id.relativelayout);

        position=getIntent().getIntExtra("position",0);
        String title= HomeFragment.theset1.get(position).getTitle();
        String bei= getIntent().getStringExtra("bei");
        String day= HomeFragment.theset1.get(position).getDay();
        String time= HomeFragment.theset1.get(position).getTime();
        int fu= HomeFragment.theset1.get(position).getFu();
        //Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("PictureId");

        titles.setText(title);
        days.setText(day+" "+time);
        Bitmap bitmap= HomeFragment.theset1.get(position).getPictureId();
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        relativeLayout.setBackground(drawable);

        ArrayList<Integer> day_z=zhuan(day);
        ArrayList<Integer> time_z=zhuan(time);
        Calendar cal=Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date=cal.getTime();


        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date toDate1 = null;
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

        startTime();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.re:
                Intent intent1 = new Intent(this, ND.class);
                ChangeActivity.this.finish();
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
                                                       setResult(RESULT_FIRST_USER, intent);
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
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
                ChangeActivity.this.finish();
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

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //time.setText(msg.arg1 + "");
            dao.setText(dayss+"天 "+hours+"小时 "+min+"分钟 "+secord+"秒");
            startTime();
        };
    };

    public void startTime() {
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                if (dayss>0||hours>0||min>0||secord>0) {   //加入判断不能小于0
                    secord--;
                    if(secord==-1)
                    {
                        min--;
                        secord=59;
                        if(min==-1)
                        {
                            hours--;
                            min=59;
                            if(hours==-1)
                            {
                                dayss--;
                                hours=23;
                            }
                        }
                    }
                    Message message = mHandler.obtainMessage();
                    mHandler.sendMessage(message);
                }
            }
        };
        timer.schedule(task, 1000);
    }

    public void stopTime(){
        timer.cancel();
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
