package com.pabji.wheatherapp.presentation.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pabji.wheatherapp.R;

public class ResultListView extends LinearLayout {

    private View root;
    private TextView tvTitle;
    private TextView tvError;
    private RecyclerView rvList;
    private TextView tvEmpty;

    public ResultListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ResultListView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        root = inflate(context, R.layout.view_result_list, this);
        tvTitle = (TextView) root.findViewById(R.id.tv_title);
        tvError = (TextView) root.findViewById(R.id.tv_error);
        tvEmpty = (TextView) root.findViewById(R.id.tv_empty);

        rvList = (RecyclerView) root.findViewById(R.id.rv_list);

        rvList.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void initAdapter(RecyclerView.Adapter adapter){
        rvList.setAdapter(adapter);
    }

    public void showError(Boolean hasError ,String message){

        if(hasError){
            tvError.setText(message);
            rvList.setVisibility(GONE);
            tvError.setVisibility(VISIBLE);
        }else{
            rvList.setVisibility(VISIBLE);
            tvError.setVisibility(GONE);
        }
        tvEmpty.setVisibility(GONE);
    }

    public void showEmpty(Boolean isEmpty, String message){
        if(isEmpty){
            tvEmpty.setText(message);
            tvEmpty.setVisibility(VISIBLE);
            rvList.setVisibility(GONE);
        }else{
            tvEmpty.setVisibility(GONE);
            rvList.setVisibility(VISIBLE);
        }
    }

    public void clearList() {
        rvList.removeAllViews();
    }
}
