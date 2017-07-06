package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class ComposeActivity extends AppCompatActivity {

    EditText tvCompose;
    TwitterClient client;
    private final int REQUEST_CODE = 20;
    String tweet;
    Tweet myTweet;
    ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = TwitterApp.getRestClient();
        tvCompose = (EditText) findViewById(R.id.tvCompose);
        tweet = tvCompose.getText().toString();
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        mTextView = (TextView) findViewById(R.id.tvCounter);
        tvCompose.addTextChangedListener(tvCounter);

//        myTweet = getIntent().getParcelableArrayExtra("myTweet");
//
//        int radius = 8;
//        int margin = 3;
//
//        Glide.with(this)
//                .load(myTweet.user.profileImageUrl)
//                .bitmapTransform(new RoundedCornersTransformation(this, radius, margin))
//                .into(ivProfileImage);

    }

    private TextView mTextView;

    private final TextWatcher tvCounter = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            mTextView.setText(String.valueOf(140-s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    public void onSubmit(View v) {

        tweet = tvCompose.getText().toString();

        // closes the activity and returns to first screen
        client.sendTweet(tweet, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());


                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent i = new Intent(ComposeActivity.this, TimelineActivity.class);
                    i.putExtra("tweet", tweet); // pass arbitrary data to launched activity
                    startActivityForResult(i, REQUEST_CODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });

        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("name", tvCompose.getText().toString());
        data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);

        this.finish();
        }



}

