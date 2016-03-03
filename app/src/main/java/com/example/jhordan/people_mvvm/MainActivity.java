package com.example.jhordan.people_mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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

    private void initDataBinding(){
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mMainViewModel = new MainViewModel(mMainView);
        mActivityMainBinding.setMainViewModel(mMainViewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void loadData(List<People> peoples) {


        PeopleAdapter peopleAdapter = (PeopleAdapter) mActivityMainBinding.listPeople.getAdapter();
        peopleAdapter.setPeopleList(peoples);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
