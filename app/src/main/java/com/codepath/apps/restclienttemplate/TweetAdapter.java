package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by mandaleeyp on 6/26/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private List<Tweet> mTweets;
    Context context;

    // pass in the Tweets array into the constructor
    public TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }
    // for each row, inflate the layout and cache references into ViewHolder

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    // bind the values based on the position of the element

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // get the data according to position
        Tweet tweet = mTweets.get(position);

        // populate the views according to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvScreenName.setText('@' + tweet.user.screenName);
        holder.tvCreatedAt.setText(" • " + getRelativeTimeAgo(tweet.createdAt));

        int radius = 8;
        int margin = 3;

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, radius, margin))
                .into(holder.ivProfileImage);

    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // create ViewHolder class

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvScreenName;
        public TextView tvBody;
        public TextView tvCreatedAt;
        public ImageButton replyButton;
        public RelativeLayout relative_layout;

        public ViewHolder (View itemView) {
            super(itemView);

            // perform findViewById lookups

            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvScreenName = (TextView) itemView.findViewById(R.id.tvScreenName);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tvCreatedAt);
            replyButton = (ImageButton) itemView.findViewById(R.id.replyButton);
            relative_layout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);


            replyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, ReplyActivity.class);
                    i.putExtra("tweet", mTweets.get(pos));
                    context.startActivity(i);
                }
            });

            relative_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("tweet", mTweets.get(pos));
                    context.startActivity(i);
                }
            });
        }
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

    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}
