package com.bytedance.scenedemo.multi_stack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bytedance.scene.Scene;
import com.bytedance.scene.ui.template.AppCompatScene;
import com.bytedance.scenedemo.MainListScene;
import com.bytedance.scenedemo.utility.ColorUtil;

/**
 * Created by JiangQi on 8/7/18.
 */
public class MultiStackTabChildScene extends AppCompatScene {

    private TextView lifecycleview;

    @Nullable
    @Override
    protected View onCreateContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(ColorUtil.getMaterialColor(getResources(), getArguments().getInt("index")));

        lifecycleview = new TextView(getActivity());
        layout.addView(lifecycleview);

        Button button = new Button(getActivity());
        button.setAllCaps(false);
        button.setText("Clear ");
        layout.addView(button, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifecycleview.setText("");
            }
        });

        button = new Button(getActivity());
        button.setAllCaps(false);
        button.setText("在当前Tab的Stack启动空白页面 ");
        layout.addView(button, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNavigationScene().push(MainListScene.class);
            }
        });

        button = new Button(getActivity());
        button.setAllCaps(false);
        button.setText("在整个Stack启动空白页面 ");
        layout.addView(button, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentScene().getNavigationScene().push(MainListScene.class);
            }
        });

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setNavigationIcon(null);
        getToolbar().setTitle("" + getArguments().getInt("index"));
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleview.setText(getStateHistory());
    }

    public static class EmptyScene extends Scene {
        @NonNull
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final View view = new View(getActivity());
            view.setBackgroundColor(ColorUtil.getMaterialColor(getResources(), 3));
            return view;
        }
    }
}