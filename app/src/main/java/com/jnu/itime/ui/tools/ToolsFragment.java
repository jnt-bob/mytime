package com.jnu.itime.ui.tools;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.jnu.itime.AddActivity;
import com.jnu.itime.ND;
import com.jnu.itime.R;
import com.jnu.itime.ui.home.HomeFragment;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .initialColor(255)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        //changeBackgroundColor(selectedColor);

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
        return root;
    }

}