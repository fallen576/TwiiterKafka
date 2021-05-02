## Welcome to my TwitterKafka Project
#### This project allows users to listen to live tweets determined by a certain hashtag.
#### When a user provides the hashtag they want to listen for a new Apache Kafka Topic is created and we begin listening to tweets via Twitter's API. Each time a Tweet is found that matches the hashtag the user provided, a message gets sent to the previously created topic displaying all the available information about the Tweet.
## Quick Start
#### Start ZooKeeper
```
bin/zookeeper-server-start.sh config/zookeeper.properties
``` 
#### Start Kafka Broker Service
```
bin/kafka-server-start.sh config/server.properties
``` 
#### Start Listening for a Tweet by Hashtag
```
mvn -P client
``` 
#### An example of what it looks like
```
Please specify which hashtag you would like to listen for: 
NFL       
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[Sun May 02 17:01:49 EDT 2021]Establishing connection.
[Sun May 02 17:01:51 EDT 2021]Connection established.
[Sun May 02 17:01:51 EDT 2021]Receiving status stream.
Consumer Record:(Tweet [user=Charlie Parent, geoLocation=, hashtags=#NFL #NFLDraft , message=RT @PTSTNews: Our #NFL reporter @mjhurleysports interviewed some of the top prospects in the 2021 #NFLDraft after they were selected in Cle…, retweetCount=0, createdAt=Sun May 02 17:01:04 EDT 2021], 0)
Consumer Record:(Tweet [user=TooAthletic Takes, geoLocation=, hashtags=#AaronRodgers #NFL #Packers #TooAthletic , message=Will Aaron Rodgers Be In Green Bay This Upcoming Season? #AaronRodgers #NFL #Packers #TooAthletic

https://t.co/e7PcFXdFzm, retweetCount=0, createdAt=Sun May 02 17:01:13 EDT 2021], 1)
Consumer Record:(Tweet [user=Dell Sacco, geoLocation=, hashtags=#MMA #NFL #NBA #PrizeFighting #SportsPodcast , message=RT @JTT81: Check out our podcast, That Other Sports Show, on @anchor! 
@zedlaV559 @TeamTOSS21 
#MMA #NFL #NBA #PrizeFighting #SportsPodcast…, retweetCount=0, createdAt=Sun May 02 17:01:16 EDT 2021], 2)
```

### Listen to a Previously Created Topic Directly via Kafka
```shell
hdadmin@hdserver:/usr/local/hadoop/kafka_2.12-2.8.0$ bin/kafka-console-consumer.sh --topic NFL --from-beginning --bootstrap-server localhost:9092
Tweet [user=Charlie Parent, geoLocation=, hashtags=#NFL #NFLDraft , message=RT @PTSTNews: Our #NFL reporter @mjhurleysports interviewed some of the top prospects in the 2021 #NFLDraft after they were selected in Cle…, retweetCount=0, createdAt=Sun May 02 17:01:04 EDT 2021]
Tweet [user=TooAthletic Takes, geoLocation=, hashtags=#AaronRodgers #NFL #Packers #TooAthletic , message=Will Aaron Rodgers Be In Green Bay This Upcoming Season? #AaronRodgers #NFL #Packers #TooAthletic

https://t.co/e7PcFXdFzm, retweetCount=0, createdAt=Sun May 02 17:01:13 EDT 2021]
Tweet [user=Dell Sacco, geoLocation=, hashtags=#MMA #NFL #NBA #PrizeFighting #SportsPodcast , message=RT @JTT81: Check out our podcast, That Other Sports Show, on @anchor! 
@zedlaV559 @TeamTOSS21 
#MMA #NFL #NBA #PrizeFighting #SportsPodcast…, retweetCount=0, createdAt=Sun May 02 17:01:16 EDT 2021]
```

#### Feeling Political? 
```
mvn -P politics
``` 