### 🧠 **Core Idea**

Simulate a simplified version of Twitter with these operations:

1. **postTweet(userId, tweetId)** – user posts a tweet
2. **getNewsFeed(userId)** – retrieve the 10 most recent tweets in user’s feed (their own + followees’)
3. **follow(followerId, followeeId)**
4. **unfollow(followerId, followeeId)**

---

### 🧩 **Data Structures Used**

| Component                        | Purpose                               | Type         |
| -------------------------------- | ------------------------------------- | ------------ |
| `User` class                     | Holds user data and operations        | Custom class |
| `HashSet<Integer> followees`     | Track followees for each user         | O(1) lookup  |
| `List<Tweet> tweets`             | Maintain posted tweets (newest first) | LinkedList   |
| `HashMap<Integer, User> userMap` | Map userId → User object              | O(1) access  |
| `PriorityQueue<Tweet>`           | Sort tweets by time (latest first)    | Min/Max heap |

---

### ⚙️ **How Each Operation Works**

#### **1. postTweet(userId, tweetId)**

* Ensure user exists; if not, create.
* Add new `Tweet` to user’s tweet list with increasing `timeCounter`.
* **O(1)** time.

#### **2. getNewsFeed(userId)**

* Get all tweets from user’s followees (and themselves).
* Use a **max heap (PriorityQueue)** to get latest tweets first.
* Return top 10 tweets by timestamp.
* **Time complexity:**

  * Worst case: `O(F * T * log(F*T))`, where `F` = number of followees, `T` = tweets per followee (limited to 10 here).
  * Effectively bounded by `O(F * 10 * log(F*10))` → **O(F log F)** in practice.
* **Space:** `O(F * 10)` for heap.

#### **3. follow(followerId, followeeId)**

* Add followee to follower’s `followees` set.
* **O(1)**

#### **4. unfollow(followerId, followeeId)**

* Remove followee from follower’s `followees` set.
* **O(1)**

---

### ⏱️ **Complexity Summary**

| Operation       | Time       | Space |
| --------------- | ---------- | ----- |
| `postTweet()`   | O(1)       | O(1)  |
| `getNewsFeed()` | O(F log F) | O(F)  |
| `follow()`      | O(1)       | O(1)  |
| `unfollow()`    | O(1)       | O(1)  |

---

### 🧭 **Common Pitfalls & Fixes**

1. ❌ **Bug:** Iterating over `userMap.get(followerId)` directly without checking existence.
   ✅ **Fix:** Always ensure both users exist before follow/unfollow (`userMap.putIfAbsent()`).

2. ❌ **Bug:** Returning >10 tweets.
   ✅ **Fix:** Stop after pushing 10 from each followee + polling top 10 only.

3. ❌ **Performance issue:** Traversing entire tweet list per followee.
   ✅ **Fix:** Only take most recent 10 per followee — no need to process all tweets.

4. ❌ **Order mismatch:** Returning tweets in ascending order.
   ✅ **Fix:** Use **max heap or reverse sorting** by `time` (`compareTo` returning descending order).

---

### 🪄 **Tips & Tricks**

* **Always follow self** – ensures user’s own tweets show up in feed.
* Use **global time counter** to maintain strict chronological order.
* Keep **LinkedList for tweets** → O(1) addFirst for new tweets.
* **Cap tweet storage per user** to avoid memory overflow in heavy tests.
* When merging multiple tweet lists efficiently, you can use **k-way merge** with a heap (like merging k sorted lists).

---

### 💡 **Conceptual Takeaway**

This problem teaches:

* How to design scalable social media systems (mini Twitter).
* Using **HashMap + Heap combination** for fast aggregation and ordering.
* Trade-offs between **time (heap merge)** vs **space (storing all tweets)**.

---
