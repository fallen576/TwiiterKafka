package gianni.benjamin.server.twitter;

import gianni.benjamin.model.Tweet;
import gianni.benjamin.server.MyProperties;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
public class Stream {

    public static void main(String[] args) {
        Listen("politics");
    }

    public static void Listen(String hashtag) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(MyProperties.getProperty("ConsumerKey"))
                .setOAuthConsumerSecret(MyProperties.getProperty("ConsumerSecret"))
                .setOAuthAccessToken(MyProperties.getProperty("AccessToken"))
                .setOAuthAccessTokenSecret(MyProperties.getProperty("AccessTokenSecret"));

        TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();

        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery.track(new String[]{"#"+hashtag});
        tweetFilterQuery.language(new String[]{"en"});


        StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
                try {
                    Tweet tmp = new Tweet(status.getUser().getName(), (status.getGeoLocation() == null) ? "" : status.getGeoLocation().toString(),
                            status.getHashtagEntities(), status.getText(), status.getRetweetCount(), status.getCreatedAt());
                    if (tmp.getHashtags().size() > 0) {
                        // Send this to topic
                        System.out.println(tmp);
                    }
                    else {
                        System.out.println("@@@"+tmp);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
            public void onScrubGeo(long userId, long upToStatusId) {
                // TODO Auto-generated method stub

            }
            public void onStallWarning(StallWarning warning) {
                // TODO Auto-generated method stub

            }
        };

        twitterStream.addListener(listener);
        twitterStream.filter(tweetFilterQuery);

    }
}
