/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.people_mvvm.view;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.databinding.PeopleDetailActivityBinding;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.PeopleDetailViewModel;

public class PeopleDetailActivity extends AppCompatActivity {

  private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";

  private PeopleDetailActivityBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.people_detail_activity);
    setSupportActionBar(binding.toolbar);
    displayHomeAsUpEnabled();
    getExtrasFromIntent();
  }

  public static Intent launchDetail(Context context, People people) {
    Intent intent = new Intent(context, PeopleDetailActivity.class);
    intent.putExtra(EXTRA_PEOPLE, people);
    return intent;
  }

  private void displayHomeAsUpEnabled() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  private void getExtrasFromIntent() {
    People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
    PeopleDetailViewModel peopleDetailViewModel = new PeopleDetailViewModel(people);
    binding.setPeopleDetailViewModel(peopleDetailViewModel);
    setTitle(people.getName().getTitle() + "." + people.getName().getFirst() + " " + people.getName().getLast());
  }
}
