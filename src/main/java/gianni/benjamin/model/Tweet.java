package gianni.benjamin.model;

import java.util.ArrayList;
import java.util.Date;
import twitter4j.HashtagEntity;

public class Tweet {

    public String user;
    public String geoLocation;
    public ArrayList<String> hashtags;
    public String message;
    public int retweetCount;
    public Date createdAt;

    public Tweet(String user, String geoLocation, HashtagEntity[] hashtags, String message, int retweetCount,
                 Date createdAt) {
        super();
        this.user = user;
        this.geoLocation = geoLocation;
        this.message = message;
        this.retweetCount = retweetCount;
        this.createdAt = createdAt;

        ArrayList<String> arr = new ArrayList<String>();
        for (HashtagEntity hte : hashtags) {
            arr.add(hte.getText());
        }

        this.hashtags = arr;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getGeoLocation() {
        return geoLocation;
    }
    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }
    public ArrayList<String> getHashtags() {
        return hashtags;
    }
    public void setHashtags(ArrayList<String> hashtags) {
        this.hashtags = hashtags;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getRetweetCount() {
        return retweetCount;
    }
    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String printTags() {
        String tags = "";

        for (String tag : hashtags) {
            tags += "#" + tag + " ";
        }

        return tags;
    }

    @Override
    public String toString() {

        return "Tweet [user=" + user + ", geoLocation=" + geoLocation + ", hashtags=" + printTags()
                + ", message=" + message + ", retweetCount=" + retweetCount + ", createdAt=" + createdAt + "]";
    }

}

