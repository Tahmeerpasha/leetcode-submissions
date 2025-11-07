class User {
    int id;
    HashSet<Integer> followees;
    List<Tweet> tweets;

    public User(int id) {
        this.id = id;
        this.followees = new HashSet<>();
        followees.add(id); // user follows themselves
        tweets = new LinkedList<>();
    }

    public void addTweet(Tweet t) {
        this.tweets.add(0, t);
    }

    public void follow(int followeeId) {
        followees.add(followeeId);
    }

    public void unfollow(int followeeId) {
        followees.remove(followeeId);
    }
}

class Tweet implements Comparable<Tweet> {
    int id;
    int time;

    public Tweet(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int compareTo(Tweet that) {
        return that.time - this.time; // desc
    }
}

class Twitter {
    HashMap<Integer, User> userMap;
    int timeCounter;

    public Twitter() {
        this.userMap = new HashMap<>();
        this.timeCounter = 0;
    }

    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId))
            userMap.put(userId, new User(userId));
        User user = userMap.get(userId);
        Tweet tweet = new Tweet(tweetId, timeCounter++);
        user.addTweet(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId))
            return new ArrayList<>();
        PriorityQueue<Tweet> pq = new PriorityQueue<>();
        User user = userMap.get(userId);
        for (int followerId : user.followees) {
            int count = 0;
            for (Tweet tweet : userMap.get(followerId).tweets) {
                if (count > 10)
                    break;
                pq.add(tweet);
                count++;
            }
        }
        List<Integer> list = new ArrayList<>();
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            list.add(pq.poll().id);
            count++;
        }
        return list;
    }

    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId))
            userMap.put(followerId, new User(followerId));
        if (!userMap.containsKey(followeeId))
            userMap.put(followeeId, new User(followeeId));
        User user = userMap.get(followerId);
        user.follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId))
            userMap.put(followerId, new User(followerId));
        if (!userMap.containsKey(followeeId))
            userMap.put(followeeId, new User(followeeId));
        User user = userMap.get(followerId);
        user.unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */