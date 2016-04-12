/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.people_mvvm.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jhordan.people_mvvm.model.People;

public class DetailViewModel {

  private People mPeople;

  public DetailViewModel(People people) {
    mPeople = people;
  }

  public String getFullUserName() {
    mPeople.mFullName = mPeople.mName.mTitle + "." + mPeople.mName.mFirts + " " + mPeople.mName.mLast;
    return mPeople.mFullName;
  }

  public String getUserName() {
    return mPeople.mUserName.userName;
  }

  public String getEmail() {
    return mPeople.mMail;
  }

  public int getEmailVisibility() {
    return mPeople.hasEmail() ? View.VISIBLE : View.GONE;
  }

  public String getCell() {
    return mPeople.mCell;
  }

  public String getPictureProfile() {
    return mPeople.mPicture.large;
  }

  public String getAddress() {
    return mPeople.mLocation.mStreet
        + " "
        + mPeople.mLocation.mCity
        + " "
        + mPeople.mLocation.mState;
  }

  public String getGender() {
    return mPeople.mGender;
  }

  @BindingAdapter({ "bind:imageUrl" })
  public static void loadImage(ImageView view, String imageUrl) {
    Glide.with(view.getContext()).load(imageUrl).into(view);
  }
}
