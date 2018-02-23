package com.pabji.wheatherapp.presentation.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;

import com.pabji.wheatherapp.R;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.presentation.adapters.CityListAdapter;
import com.pabji.wheatherapp.presentation.utils.ResultListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, SearchView.OnQueryTextListener, CityListAdapter.OnItemClickListener, SearchView.OnCloseListener {


    private MainPresenter mPresenter;
    private ResultListView rlSearch, rlHistory;

    private CityListAdapter searchAdapter, historyAdapter;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        initView();
        mPresenter.loadHistory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    private void initView() {
        rlSearch = findViewById(R.id.view_search);
        rlHistory =  findViewById(R.id.view_history);

        rlSearch.setTitle(getString(R.string.search_results));
        rlHistory.setTitle(getString(R.string.history_results));

        searchAdapter = new CityListAdapter(this);
        historyAdapter = new CityListAdapter(this);

        rlSearch.initAdapter(searchAdapter);
        rlHistory.initAdapter(historyAdapter);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText != null && newText.length() >= 3){
            mPresenter.searchCity(newText);
        }else{
            rlSearch.setVisibility(View.GONE);
            rlHistory.setVisibility(View.VISIBLE);
        }
        return false;
    }


    @Override
    public void showSearchList(List<CityModel> result) {

        rlSearch.showError(false,null);
        rlSearch.setVisibility(View.VISIBLE);
        rlHistory.setVisibility(View.GONE);

        searchAdapter.setData(result);
    }

    @Override
    public void showHistory(List<CityModel> result) {
        historyAdapter.setData(result);
    }

    @Override
    public void showEmptySearch(String message) {
        rlSearch.showEmpty(true,message);
    }

    @Override
    public void showEmptyHistory(String message) {
        rlHistory.showEmpty(true,message);
    }

    @Override
    public void showError(String error) {
        rlSearch.showError(true,error);
        rlHistory.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(CityModel model) {
        mPresenter.goToDetail(model);
    }

    @Override
    public boolean onClose() {
        rlSearch.setVisibility(View.GONE);
        rlHistory.setVisibility(View.VISIBLE);
        rlSearch.clearList();
        return false;
    }
}
