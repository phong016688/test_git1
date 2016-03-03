package com.example.jhordan.people_mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jhordan.people_mvvm.PeopleAdapter;
import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.databinding.MainActivityBinding;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.MainViewModel;
import com.example.jhordan.people_mvvm.viewmodel.MainViewModelContract;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainViewModelContract.MainView {

    private MainActivityBinding mActivityMainBinding;
    private MainViewModel mMainViewModel;
    private MainViewModelContract.MainView mMainView = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(mActivityMainBinding.toolbar);
        setupListPeopleView(mActivityMainBinding.listPeople);

    }

    private void initDataBinding() {
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mMainViewModel = new MainViewModel(mMainView, getContext());
        mActivityMainBinding.setMainViewModel(mMainViewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainViewModel.destroy();
    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }

    @Override
    public void loadData(List<People> peoples) {
        PeopleAdapter peopleAdapter = (PeopleAdapter) mActivityMainBinding.listPeople.getAdapter();
        peopleAdapter.setPeopleList(peoples);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_github) {
            startActivityActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/erikcaffrey/People-MVVM")));
    }


}
