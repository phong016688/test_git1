package com.example.jhordan.people_mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.databinding.PeopleDetailActivityBinding;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.DetailViewModel;
import com.example.jhordan.people_mvvm.viewmodel.DetailViewModelContract;

/**
 * Created by Jhordan on 03/03/16.
 */
public class PeopleDetailActivity extends AppCompatActivity implements DetailViewModelContract.DetailView {

    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";

    private PeopleDetailActivityBinding mPeopleDetailActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPeopleDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.people_detail_activity);
        setSupportActionBar(mPeopleDetailActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static Intent launchDetail(Context context, People people) {
        Intent intent = new Intent(context, PeopleDetailActivity.class);
       //Bundle bundle = new Bundle();
       // bundle.putSerializable(EXTRA_PEOPLE, people);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void getExtrasFromIntent() {
      //  Bundle bundle = getIntent().getExtras();
        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        DetailViewModel detailViewModel = new DetailViewModel(getContext(), people);
        mPeopleDetailActivityBinding.setDetailViewModel(detailViewModel);
        setTitle(people.mName.mTitle + "." + people.mName.mFirts + " " + people.mName.mLast);
    }


    @Override
    public Context getContext() {
        return PeopleDetailActivity.this;
    }
}
