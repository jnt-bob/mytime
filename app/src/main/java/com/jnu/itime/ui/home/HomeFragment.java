package com.jnu.itime.ui.home;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jnu.itime.AddActivity;
import com.jnu.itime.ChangeActivity;
import com.jnu.itime.FileDataSource;
import com.jnu.itime.R;
import com.jnu.itime.set_kind_zhu;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    private HomeViewModel homeViewModel;
    static public ArrayList<set_kind_zhu> theset1;
    private GoodsArrayAdapter theAdaper;
    private FileDataSource fileDataSource;

    private CarouselView carouselView;

    ArrayList<Bitmap> sampleImages=new ArrayList<Bitmap>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView add = (ImageView) root.findViewById(R.id.add);
        add.setOnClickListener(this);

        InitData();
        theAdaper = new GoodsArrayAdapter(getContext(), R.layout.list_item_set_zhu, theset1);
        ListView listViewSuper = (ListView) root.findViewById(R.id.list_view_goods_1);
        listViewSuper.setAdapter(theAdaper);
        listViewSuper.setOnItemClickListener(this);
        //listViewSuper.setOnItemClickListener(this);

        carouselView = (CarouselView) root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.size());
        carouselView.setImageListener(imageListener);

        return root;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageBitmap(sampleImages.get(position));
        }
    };

    private void InitData() {
        theset1=new  ArrayList<set_kind_zhu>();
        fileDataSource=new FileDataSource(getContext());
        theset1=fileDataSource.load();
        for(int j=0;j<theset1.size();j++) {
            theset1.get(j).setDay_cha();
            sampleImages.add(theset1.get(j).getPictureId());
        }
        if(sampleImages.size()==0) {
            Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
            sampleImages.add(bitmap1);
        }
        /*Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.day, null);
        Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.day, null);
        theset1.add(new set_kind_zhu("只剩"+"\n"+"3天", "学习","2019年12月13日", bitmap));
        theset1.add(new set_kind_zhu("只剩"+"\n"+"3天", "复习","2019年12月13日", bitmap1));*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add:
                Intent intent = new Intent(getContext(), AddActivity.class);
                startActivityForResult(intent, 111);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==111)
        {
            theAdaper.notifyDataSetChanged();
            fileDataSource.save();
            sampleImages.clear();
            for(int j=0;j<theset1.size();j++) {
                sampleImages.add(theset1.get(j).getPictureId());
            }
            if(sampleImages.size()==0) {
                Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
                sampleImages.add(bitmap1);
            }
            carouselView.setPageCount(sampleImages.size());
            carouselView.setImageListener(imageListener);
        }
        else if(resultCode==RESULT_FIRST_USER&&requestCode==222)
        {
            int position=data.getIntExtra("position",0);
            theset1.remove(position);
            theAdaper.notifyDataSetChanged();
            fileDataSource.save();
            sampleImages.clear();
            for(int j=0;j<theset1.size();j++) {
                sampleImages.add(theset1.get(j).getPictureId());
            }
            if(sampleImages.size()==0) {
                Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
                sampleImages.add(bitmap1);
            }
            carouselView.setPageCount(sampleImages.size());
            carouselView.setImageListener(imageListener);
        }
        else if(resultCode==RESULT_CANCELED&&requestCode==222)
        {
            theAdaper.notifyDataSetChanged();
            fileDataSource.save();
            sampleImages.clear();
            for(int j=0;j<theset1.size();j++) {
                sampleImages.add(theset1.get(j).getPictureId());
            }
            if(sampleImages.size()==0) {
                Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
                sampleImages.add(bitmap1);
            }
            carouselView.setPageCount(sampleImages.size());
            carouselView.setImageListener(imageListener);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getContext(), ChangeActivity.class);
        intent.putExtra("position",position);
        startActivityForResult(intent, 222);
    }

    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                                            true);
        return dstbmp;
    }

    class GoodsArrayAdapter extends ArrayAdapter<set_kind_zhu> {
        private int resourceId;

        public GoodsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<set_kind_zhu> objects) {
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
            TextView string3 = (TextView) item.findViewById(R.id.text_view_string3);
            TextView string4 = (TextView) item.findViewById(R.id.text_view_string4);

            set_kind_zhu item1 = this.getItem(position);
            img.setImageBitmap(imageScale(item1.getPictureId(),20,20));
            string1.setText(item1.getDay_cha());
            string2.setText(item1.getTitle());
            string3.setText(item1.getDay());
            string4.setText(item1.getBei());

            return item;
        }
    }
}