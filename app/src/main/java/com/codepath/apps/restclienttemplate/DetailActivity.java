package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {

//    private List<Tweet> mTweets;

    TextView tvUsername;
    TextView tvScreenName;
    TextView tvBody;
    TextView tvCreatedAt;
    ImageView ivProfileImage;
    Tweet tweet;
    TwitterClient client;
    ImageButton ibFav;
    ImageButton ic_retweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tweet = getIntent().getParcelableExtra("tweet");
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvUsername = (TextView) findViewById(R.id.tvUserName);
        tvBody = (TextView) findViewById(R.id.tvBody);
        tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvCreatedAt = (TextView) findViewById(R.id.tvCreatedAt);
        tvUsername.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        tvScreenName.setText('@' + tweet.user.screenName);
        tvCreatedAt.setText(" • " + getRelativeTimeAgo(tweet.createdAt));
        ibFav = (ImageButton) findViewById(R.id.favButton);
        ic_retweet = (ImageButton) findViewById(R.id.retweetButton);

        int radius = 8;
        int margin = 3;

        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(this, radius, margin))
                .into(ivProfileImage);

        client = TwitterApp.getRestClient();

        if(tweet.fav == false) {
            ibFav.setBackgroundResource(R.drawable.iv_fav);
        } else {
            ibFav.setBackgroundResource(R.drawable.ic_isfav);
        }

        ibFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (tweet.fav) {
                    client.unfavTweet(tweet, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d("TwitterClient", response.toString());
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
                    tweet.fav = false;
                    ibFav.setBackgroundResource(R.drawable.iv_fav);
                }
                else{
                    client.favTweet(tweet, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("TwitterClient", response.toString());
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
                    tweet.fav = true;
                    ibFav.setBackgroundResource(R.drawable.ic_isfav);

                }
            }


        });

        if(tweet.retweet == false) {
            ic_retweet.setImageResource(R.drawable.ic_retweet);
        } else {
            ibFav.setImageResource(R.drawable.ic_retweeted);
        }

        ic_retweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (tweet.retweet) {
                    client.unTweet(tweet, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("TwitterClient", response.toString());
                            tweet.retweet = false;
                            ic_retweet.setImageResource(R.drawable.ic_retweet);
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

                }
                else{
                    client.reTweet(tweet, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("TwitterClient", response.toString());
                            tweet.retweet = true;
                            ic_retweet.setImageResource(R.drawable.ic_retweeted);
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


                }
            }
        });
    }


    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
