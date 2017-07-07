# Project 3 - *SimpleTwitter*

**SimpleTwitter** is an android app that allows a user to view his Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **20** hours spent in total

## User Stories

The following **required** functionality is completed:

Week 1
* [x]	User can **sign in to Twitter** using OAuth login
* [x]	User can **view tweets from their home timeline**
* [x] User is displayed the username, name, and body for each tweet
* [x] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [x] User can **compose and post a new tweet**
* [x] User can click a “Compose” icon in the Action Bar on the top right
* [x] User can then enter a new tweet and post this to twitter
* [x] User is taken back to home timeline with **new tweet visible** in timeline
* [x] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh

Week 2
* [x] User can switch between Timeline and Mention views using tabs. (3 points)
* [x] User can view their home timeline tweets.
* [x] User can view the recent mentions of their username.
* [x] User can compose tweets. See this conceptual guide for passing data into a timeline fragment.
* [x] User can navigate to view their own profile (2 points)
* [x] User can see picture, tagline, # of followers, # of following, and tweets on their profile.
* [x] The users/verify_credentials endpoint can be used to access this information.
* [x] User can click on the profile image in any tweet to see another user's profile. (3 points)
* [x] User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
* [x] Profile view should include that user's timeline
* [x] The users/show endpoint can be used to access this information.

The following **optional** features are implemented:

Week 1
* [x] User can **see a counter with total number of characters left for tweet** on compose tweet page
* [x] User can **pull down to refresh tweets timeline**
* [x] User is using **"Twitter branded" colors and styles**
* [ ] User sees an **indeterminate progress indicator** when any background or network task is happening
* [ ] User can **select "reply" from detail view to respond to a tweet**
* [x] User that wrote the original tweet is **automatically "@" replied in compose**
* [x] User can tap a tweet to **open a detailed tweet view**
* [x] User can **take favorite (and unfavorite) or reweet** actions on a tweet
* [ ] User can **see embedded image media within a tweet** on list or detail view.

Week 2
* [x] User can "reply" to any tweet from their home timeline (1 point)
* [x] User can search for tweets matching a particular query and see results. (1 point)

The following **bonus** features are implemented:

Week 1
* [ ] User can view more tweets as they scroll with infinite pagination
* [ ] Compose tweet functionality is build using modal overlay
* [X] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [x] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.com/android/Drawables#vector-drawables) where appropriate.
* [x] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [ ] User can view following / followers list through any profile they view.
* [ ] User can see embedded image media within the tweet detail view
* [ ] Use the popular ButterKnife annotation library to reduce view boilerplate.
* [ ] On the Twitter timeline, leverage the [CoordinatorLayout](http://guides.codepath.com/android/Handling-Scrolls-with-CoordinatorLayout#responding-to-scroll-events) to apply scrolling behavior that [hides / shows the toolbar](http://guides.codepath.com/android/Using-the-App-ToolBar#reacting-to-scroll).
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in offline mode.

Week 2
* [ ] Compose activity is replaced with a modal overlay (2 points)
* [x] Links in tweets are clickable and will launch the web browser (see autolink) (1 point)
* [x] User can view following / followers list through any profile they view. (2 points)
* [ ] Apply the popular ButterKnife annotation library to reduce view boilerplate. (1 point)
* [ ] Experiment with fancy scrolling effects on the Twitter profile view. (2 points)
* [ ] User can open the twitter app offline and see last loaded tweets persisted into sqlite (2 points)


The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/mandaleeyp/twitterApp/blob/master/twitter.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

Copyright [2017] [name of copyright owner]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
