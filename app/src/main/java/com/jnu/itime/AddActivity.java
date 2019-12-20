package com.jnu.itime;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<set_kind_1> theset1;
    private GoodsArrayAdapter theAdaper;
    private ArrayList<set_kind_2> theset2;
    private GoodsArrayAdapter2 theAdaper2;
    private ImageView re;
    private ImageView finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add);
        re=(ImageView) this.findViewById(R.id.re);
        finish=(ImageView) this.findViewById(R.id.finish);

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


    }

    private void InitData() {
        theset1=new  ArrayList<set_kind_1>();
        theset1.add(new set_kind_1("日期", "时间", R.drawable.day));
        theset1.add(new set_kind_1("重复设置", "无", R.drawable.set));
        theset2=new  ArrayList<set_kind_2>();
        theset2.add(new set_kind_2("图片",R.drawable.picture));
        theset2.add(new set_kind_2("添加标签", R.drawable.qian));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.re:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.finish:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId())
        {
            case R.id.list_view_goods_1:
                if(position==0)
                {Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);}
                break;
            case R.id.finish:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
        }
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
