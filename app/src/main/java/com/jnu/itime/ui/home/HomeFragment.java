package com.jnu.itime.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jnu.itime.AddActivity;
import com.jnu.itime.R;
import com.jnu.itime.set_kind_1;
import com.jnu.itime.set_kind_2;
import com.jnu.itime.set_kind_zhu;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private ArrayList<set_kind_zhu> theset1;
    private GoodsArrayAdapter theAdaper;

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
        //listViewSuper.setOnItemClickListener(this);
        return root;
    }

    private void InitData() {
        theset1=new  ArrayList<set_kind_zhu>();
        theset1.add(new set_kind_zhu("只剩"+"\n"+"3天", "学习","2019年12月13日", R.drawable.day));
        theset1.add(new set_kind_zhu("只剩"+"\n"+"3天", "复习","2019年12月13日", R.drawable.set));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add:
                Intent intent = new Intent(getContext(), AddActivity.class);
                startActivity(intent);
                break;
        }
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

            set_kind_zhu item1 = this.getItem(position);
            img.setImageResource(item1.getPictureId());
            string1.setText(item1.getOne());
            string2.setText(item1.getTwo());
            string3.setText(item1.getThree());

            return item;
        }
    }
}