package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    TwitterClient client;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        boolean userProfile = getIntent().getExtras().getBoolean("userProfile");

        client = TwitterApp.getRestClient();
        if (userProfile) {

            String screenName = getIntent().getStringExtra("screen_name");
            // create the user fragment
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName);
            // display the user timeline fragment inside the container (dynamically)
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            // make change
            ft.replace(R.id.flContainer, userTimelineFragment);
            // commit
            ft.commit();

            Tweet tweet;

            client.getUserInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // deserialize the User object
                    User user = null;
                    try {
                        user = User.fromJSON(response);
                        // set the title of the ActionBAr based on the user information
                        getSupportActionBar().setTitle(user.screenName);
                        // populate the user headline
                        populateUserHeadline(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {

            tweet = getIntent().getParcelableExtra("tweet");
            Long uid = tweet.uid;
            final String name = tweet.user.screenName;

            // create the user fragment
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(name);
            // display the user timeline fragment inside the container (dynamically)
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            // make change
            ft.replace(R.id.flContainer, userTimelineFragment);
            // commit
            ft.commit();


            client.getOtherUserProfile(name, Long.toString(uid), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // deserialize the User object
                    User user = null;
                    try {
                        user = User.fromJSON(response);
                        // set the title of the ActionBAr based on the user information
                        getSupportActionBar().setTitle(user.name);
                        // populate the user headline
                        populateUserHeadline(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    public void populateUserHeadline(User user){
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);

        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvName.setText(user.name);

         tvTagline.setText(user.tagLine);
         tvFollowers.setText(user.followersCount + "Followers");
         tvFollowing.setText(user.followingCount + "Following");
        // load profile image with Glide
        Glide.with(this).load(user.profileImageUrl).into(ivProfileImage);
    }
}
