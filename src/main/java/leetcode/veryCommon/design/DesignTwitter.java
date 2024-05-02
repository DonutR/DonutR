package leetcode.veryCommon.design;

import java.util.*;
import java.util.stream.Collectors;

public class DesignTwitter {
    /*
        Integer time;
        HashMap<Integer, Set<Integer>> followerFollowee;
        HashMap<Integer, Set<Integer>> followeeFollower;
        HashMap<Integer, PriorityQueue<Tweet>> tweetFeed;
        HashMap<Integer, LinkedList<Tweet>> userTweet;

        postTweet
            add tweet to postTweet
            callback feedupdate(followee)
        getNewsFeed
            get tweetFeed
        follow
            add followee to followerFollowee
            add follower to followeeFollower
            callback feedupdate(followee)
        unfollow
            remoove followee to followerFollowee
            remoove follower to followeeFollower
            callback feedupdate(followee)
     */
    class Twitter {
        class Tweet {
            int tweeterId;
            int tweetId;
            int time;

            public Tweet(int tweeterId, int tweetId, int time) {
                this.tweeterId = tweeterId;
                this.tweetId = tweetId;
                this.time = time;
            }
        }

        Integer time;
        HashMap<Integer, Set<Integer>> followerFollowee;
        HashMap<Integer, Set<Integer>> followeeFollower;
        HashMap<Integer, PriorityQueue<Tweet>> tweetFeed;
        HashMap<Integer, LinkedList<Tweet>> userTweet;

        public Twitter() {
            time = 0;
            followerFollowee = new HashMap<>();
            followeeFollower = new HashMap<>();
            tweetFeed = new HashMap<Integer, PriorityQueue<Tweet>>();
            userTweet = new HashMap<>();
        }

        public void initialize(int userId) {
            followerFollowee.computeIfAbsent(userId, x -> new HashSet<>()).add(userId);
            followeeFollower.computeIfAbsent(userId, x -> new HashSet<>()).add(userId);
            tweetFeed.computeIfAbsent(userId, x -> new PriorityQueue<>((a, b) -> a.time - b.time));
            userTweet.computeIfAbsent(userId, x -> new LinkedList<>());
        }

        public void postTweet(int userId, int tweetId) {
            initialize(userId);
            Tweet tweet = new Tweet(userId, tweetId, ++time);
            for (int follower : followeeFollower.get(userId)) {
                tweetFeed.get(follower).add(tweet);
                if (tweetFeed.get(follower).size() > 10)
                    tweetFeed.get(follower).poll();
            }
            userTweet.get(userId).addFirst(tweet);
        }

        public List<Integer> getNewsFeed(int userId) {
            initialize(userId);
            return tweetFeed.get(userId)
                    .stream()
                    .sorted((a, b) -> b.time - a.time)
                    .map(x -> x.tweetId).collect(Collectors.toList());
        }

        public void follow(int followerId, int followeeId) {
            initialize(followerId);
            initialize(followeeId);
            followerFollowee.get(followerId).add(followeeId);
            followeeFollower.get(followeeId).add(followerId);
            rePopulateFeed(followerId);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            if (followerId == followeeId) return;
            initialize(followerId);
            initialize(followeeId);
            //Rebuild news feed of follower without including followee
            followerFollowee.get(followerId).remove(followeeId);
            followeeFollower.get(followeeId).remove(followerId);
            cleanFeed(followerId, followeeId);
            rePopulateFeed(followerId);
        }

        public void cleanFeed(int followerId, int followeeId) {
            PriorityQueue<Tweet> pq = tweetFeed.get(followerId);
            List<Tweet> toRemove = new LinkedList<>();
            for (Tweet t : pq)
                if (t.tweeterId == followeeId)
                    toRemove.add(t);
            pq.removeAll(toRemove);
        }

        public void rePopulateFeed(int userId) {
            Set<Integer> followees = followerFollowee.get(userId);
            tweetFeed.get(userId).clear();
            for (Integer followee : followees) {
                for (Tweet tweet : userTweet.get(followee)) {
                    tweetFeed.get(userId).add(tweet);
                    if (tweetFeed.get(userId).size() > 10)
                        tweetFeed.get(userId).poll();
                }
            }
        }
    }
}
