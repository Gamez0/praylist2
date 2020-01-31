/*
 * Copyright 2018 Rozdoum
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.rozdoum.socialcomponents.main.post;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rozdoum.socialcomponents.R;
import com.rozdoum.socialcomponents.main.pickImageBase.PickImageActivity;

/**
 * Created by Alexey on 03.05.18.
 */
public abstract class BaseCreatePostActivity<V extends BaseCreatePostView, P extends BaseCreatePostPresenter<V>>
        extends PickImageActivity<V, P> implements BaseCreatePostView {

    protected ImageView imageView;
    protected ProgressBar progressBar;
    protected EditText titleEditText;
    protected EditText descriptionEditText;
    protected Button submitPrayList;
    protected Button whoCanReadButton;
    protected Button prayerForButton;
    protected TextView prayerNameTextView;

    protected boolean isGlobal = true;
    protected String prayerFor="Y";
    static final int request_ok_who =1;
    static final int request_ok_prayer=2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_create_post_activity);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        titleEditText = findViewById(R.id.descriptionEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.slide_image);
        submitPrayList = findViewById(R.id.submitPrayList);
        whoCanReadButton = findViewById(R.id.button_who_can_read);
        prayerForButton = findViewById(R.id.button_prayer_for);

        descriptionEditText.setHint(R.string.hint_description2);

        imageView.setOnClickListener(v -> onSelectImageClick(v));

        titleEditText.setOnTouchListener((v, event) -> {
            if (titleEditText.hasFocus() && titleEditText.getError() != null) {
                titleEditText.setError(null);
                return true;
            }

            return false;
        });
//        submitpraylist button 누르는게 안됨...ㅠㅠ
//        submitPrayList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.doSavePost(imageUri);
//            }
//        });
        submitPrayList.setOnClickListener(v->{
            presenter.doSavePost(imageUri);
        });
//        submitPrayList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG,"submit PrayList");
//            }
//        });

        whoCanReadButton.setOnClickListener(v->{
            // intent go new activity to get who can receive prayList
            Intent intent = new Intent(this.getApplicationContext(),WhoCanReadActivity.class);
            startActivityForResult(intent,request_ok_who);
            Log.d(TAG,"whoCanReadButton pressed.");
        });
        prayerForButton.setOnClickListener(v->{
            Intent intent = new Intent(this.getApplicationContext(),PrayerForActivity.class);
            startActivityForResult(intent, request_ok_prayer);
            Log.d(TAG,"prayerForButton pressed.");
        });
//        Intent intent = getIntent();
//        int num = intent.getExtras().getInt("whoCanRead");
//        Log.d(TAG,"공개범위는 :"+num);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this.getApplicationContext(),"onActivityResult() called.",Toast.LENGTH_LONG);// not working
        Log.d(TAG,"onActivityResult called.");
            if(requestCode==request_ok_who){
                Log.d(TAG,"requestCode == request_ok");
                if(resultCode==0){
                    //모두에게 공개
                    Toast.makeText(this.getApplicationContext(),"모두에게 공개",Toast.LENGTH_LONG);
                    Log.d(TAG,"모두에게 공개");
                    isGlobal =true;
                } else if(resultCode==1){
                    //동역자들에게 공개
                    Log.d(TAG,"동역자에게 공개"); // 이건 아직 구현하기 힘듬으로 ㅠㅠ
                } else {
                    //나만
                    Log.d(TAG,"나만 보기");
                    isGlobal = false;
                }
            }else if(requestCode==request_ok_prayer){
                if(resultCode==0){
                    //for Jesus
                    prayerFor="J";
                }else if(resultCode==1){
                    //for Others
                    prayerFor="O";
                }else{// resultCode==2 또는 선택 안한 경우
                    //for You
                    prayerFor="Y";
                }
            }
    }

    @Override
    protected ProgressBar getProgressView() {
        return progressBar;
    }

    @Override
    protected ImageView getImageView() {
        return imageView;
    }

    @Override
    protected void onImagePikedAction() {
        loadImageToImageView(imageUri);
    }

    @Override
    public void setDescriptionError(String error) {
        descriptionEditText.setError(error);
        descriptionEditText.requestFocus();
    }

    @Override
    public void setTitleError(String error) {
        titleEditText.setError(error);
        titleEditText.requestFocus();
    }

    @Override
    public String getTitleText() {
        return "제목 삭제해야됨.";
//        return titleEditText.getText().toString()
    }

    @Override
    public String getDescriptionText() {
        return descriptionEditText.getText().toString();
    }

    @Override
    public boolean getGlobal() {
        return isGlobal;
    }

    @Override
    public String getPrayerFor() {
        return prayerFor;
    }

    @Override
    public void requestImageViewFocus() {
        imageView.requestFocus();
    }

    @Override
    public void onPostSavedSuccess() {
        setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public Uri getImageUri() {
        return imageUri;
    }

    @Override
    public void setNoneImageUri(){
        Uri uri =Uri.parse("none");
        imageUri=uri;
    }

    @Override
    public void setImageUri(Uri uri) {
        imageUri = uri;
    }

    public void onClick(View view) {
        Log.d(TAG,"submit prayList clicked.");
    }
}
