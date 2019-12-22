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
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jnu.itime.ui.home.HomeFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static androidx.core.os.LocaleListCompat.create;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ArrayList<set_kind_1> theset1;
    private GoodsArrayAdapter theAdaper;
    private ArrayList<set_kind_2> theset2;
    private GoodsArrayAdapter2 theAdaper2;
    private ImageView re;
    private ImageView finish;
    private String date="";
    private String time="";
    private AlertDialog alertDialog1;

    private View alertDialogView;
    private RelativeLayout relativeLayout;
    private EditText edit_title;
    private EditText edit_bei;
    private Bitmap mBitmap;
    private final String[] items = {"每周", "每月", "每年", "自定义"};
    private int time_fu = 0;

    private int i = 0;
    private int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add);

        relativeLayout = this.findViewById(R.id.relativelayout);
        edit_bei = this.findViewById(R.id.bei);
        edit_title = this.findViewById(R.id.title);

        re = (ImageView) this.findViewById(R.id.re);
        finish = (ImageView) this.findViewById(R.id.finish);

        re.setOnClickListener(this);
        finish.setOnClickListener(this);


        InitData();
        theAdaper = new GoodsArrayAdapter(this, R.layout.list_item_set_1, theset1);
        ListView listViewSuper = (ListView) findViewById(R.id.list_view_goods_1);
        listViewSuper.setAdapter(theAdaper);
        listViewSuper.setOnItemClickListener(this);
        //this.registerForContextMenu(listViewSuper);

        theAdaper2 = new GoodsArrayAdapter2(this, R.layout.list_item_set_2, theset2);
        ListView listViewSuper2 = (ListView) findViewById(R.id.list_view_goods_2);
        listViewSuper2.setAdapter(theAdaper2);
        listViewSuper2.setOnItemClickListener(this);
        //this.registerForContextMenu(listViewSuper2);

        position=getIntent().getIntExtra("position",-1);
        if(position!=-1)
        {
            edit_title.setText(HomeFragment.theset1.get(position).getTitle());
            edit_bei.setText(HomeFragment.theset1.get(position).getBei());
            mBitmap=HomeFragment.theset1.get(position).getPictureId();
            Drawable drawable = new BitmapDrawable(getResources(),mBitmap);
            relativeLayout.setBackground(drawable);
            date=HomeFragment.theset1.get(position).getDay();
            time=HomeFragment.theset1.get(position).getTime();
            set_kind_1 times = theset1.get(0);
            times.setTwo(date + " " + time);
            set_kind_1 timess = theset1.get(1);
            time_fu=HomeFragment.theset1.get(position).getFu();
            if(time_fu!=0)
            timess.setTwo(items[time_fu]);
            theAdaper.notifyDataSetChanged();
        }


    }

    private void InitData() {
        theset1 = new ArrayList<set_kind_1>();
        theset1.add(new set_kind_1("日期", "无", R.drawable.day));
        theset1.add(new set_kind_1("重复设置", "无", R.drawable.set));
        theset2 = new ArrayList<set_kind_2>();
        theset2.add(new set_kind_2("图片", R.drawable.picture));
        theset2.add(new set_kind_2("添加标签", R.drawable.qian));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re:
                Intent intent1 = new Intent(this, ND.class);
                AddActivity.this.finish();
                break;
            case R.id.finish:
                if(edit_title.getText().toString().isEmpty())
                    Toast.makeText(AddActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                else
                {
                    if(position==-1) {
                        Intent intent = new Intent(this, ND.class);
                        if (date.isEmpty()) {
                            Calendar calendars = Calendar.getInstance();
                            calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                            String year = String.valueOf(calendars.get(Calendar.YEAR));

                            String month = String.valueOf(calendars.get(Calendar.MONTH));

                            String day = String.valueOf(calendars.get(Calendar.DATE));

                            String hour = String.valueOf(calendars.get(Calendar.HOUR));

                            String min = String.valueOf(calendars.get(Calendar.MINUTE));

                            date = year + "年" + month + "月" + day+"日";
                            time = hour + ":" + min;
                        }
                        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.day, null);
                        if (mBitmap == null)
                            mBitmap = bitmap1;
                        byte[] w = bitmap2Bytes(mBitmap);
                        HomeFragment.theset1.add(new set_kind_zhu(edit_title.getText().toString().trim(), date, time, w, edit_bei.getText().toString().trim(), time_fu));
                        setResult(RESULT_OK, intent);
                        AddActivity.this.finish();
                    }
                    else
                    {
                        //Toast.makeText(AddActivity.this, "233", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, ChangeActivity.class);
                        byte[] w = bitmap2Bytes(mBitmap);
                        //HomeFragment.theset1.add(new set_kind_zhu(edit_title.getText().toString().trim(), date + "日", time, w, edit_bei.getText().toString().trim(), time_fu));
                        set_kind_zhu gai=HomeFragment.theset1.get(position);
                        gai.setTitle(edit_title.getText().toString().trim());
                        gai.setBei(edit_bei.getText().toString().trim());
                        gai.setDay(date);
                        gai.setTime(time);
                        gai.setFu(time_fu);
                        gai.setPictureId(w);
                        gai.setDay_cha();
                        intent.putExtra("position",position);
                        setResult(RESULT_OK, intent);
                        startActivity(intent);
                        AddActivity.this.finish();
                    }
            }
            break;
            case R.id.textView1:
                if (i == 0) {
                    view.setBackgroundResource(R.drawable.mark_beselected);
                    i = 1;
                } else {
                    view.setBackgroundResource(R.drawable.mark_notbeselected);
                    view.setTag(true);
                    i = 0;
                }
                //Toast.makeText(AddActivity.this, "233", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId()) {
            case R.id.list_view_goods_1:
                if (position == 0) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            AddActivity.this,
                            now.get(Calendar.YEAR), // Initial year selection
                            now.get(Calendar.MONTH), // Initial month selection
                            now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                    );
                    dpd.show(getSupportFragmentManager(), "Datepickerdialog");
                } else if (position == 1) {

                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setTitle("周期");
                    alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Toast.makeText(AddActivity.this, items[i], Toast.LENGTH_SHORT).show();
                            time_fu = i+1;
                            set_kind_1 times = theset1.get(1);
                            if (i != 3) {
                                times.setTwo(items[i]);
                                theAdaper.notifyDataSetChanged();
                            } else {
                                final EditText inputServer = new EditText(AddActivity.this);
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                                builder.setTitle("周期").setView(inputServer)
                                        .setNegativeButton("取消", null);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        inputServer.getText().toString();
                                        times.setTwo(inputServer.getText().toString() + "天");
                                        theAdaper.notifyDataSetChanged();
                                    }
                                });
                                builder.show();
                            }
                            alertDialog1.dismiss();
                        }
                    });
                    alertDialog1 = alertBuilder.create();
                    alertDialog1.show();
                }
                break;
            case R.id.list_view_goods_2:
                if (position == 1) {
                    alertDialogView = getLayoutInflater().inflate(R.layout.myalter, null, false);
                    AlertDialog.Builder loginAlertDialog = new AlertDialog.Builder(AddActivity.this);
                    loginAlertDialog.setView(alertDialogView);
                    loginAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    loginAlertDialog.show();
                } else if (position == 0) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    //设置请求码，以便我们区分返回的数据
                    startActivityForResult(intent, 100);
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (100 == requestCode) {
            if (data != null) {
                //获取数据
                //获取内容解析者对象
                try {
                    mBitmap = BitmapFactory.decodeStream(
                            getContentResolver().openInputStream(data.getData()));
                    //imageview.setImageBitmap(mBitmap);
                    Drawable drawable = new BitmapDrawable(getResources(), mBitmap);
                    if (mBitmap != null)
                        relativeLayout.setBackground(drawable);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth+"日";
        Calendar now1 = Calendar.getInstance();
        TimePickerDialog dpd1 = TimePickerDialog.newInstance(
                AddActivity.this,
                now1.get(Calendar.HOUR_OF_DAY), // Initial year selection
                now1.get(Calendar.MINUTE), // Initial month selection
                true// Inital day selection
        );
        dpd1.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        if (minute > 9)
            time = hourOfDay + ":" + minute;
        else
            time = hourOfDay + ":0" + minute;
        set_kind_1 times = theset1.get(0);
        times.setTwo(date + " " + time);
        theAdaper.notifyDataSetChanged();
    }


    class GoodsArrayAdapter extends ArrayAdapter<set_kind_1> {
        private int resourceId;

        public GoodsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<set_kind_1> objects) {
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
            TextView string2 = (TextView) item.findViewById(R.id.text_view_string2);

            set_kind_1 item1 = this.getItem(position);
            img.setImageResource(item1.getPictureId());
            string1.setText(item1.getOne());
            string2.setText(item1.getTwo());

            return item;
        }
    }

    class GoodsArrayAdapter2 extends ArrayAdapter<set_kind_2> {
        private int resourceId;

        public GoodsArrayAdapter2(@NonNull Context context, @LayoutRes int resource, @NonNull List<set_kind_2> objects) {
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

            set_kind_2 item1 = this.getItem(position);
            img.setImageResource(item1.getPictureId());
            string1.setText(item1.getOne());

            return item;
        }
    }
}
