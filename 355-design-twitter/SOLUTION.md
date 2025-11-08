## 🧠 **355. Design Twitter – Complete Interview Notes (with Intuition)**

---

### **1. Problem Statement (Goal)**

Design a miniature version of Twitter that supports:

* **postTweet(userId, tweetId)** → user posts a tweet.
* **getNewsFeed(userId)** → fetches the 10 most recent tweets from the user and their followees.
* **follow(followerId, followeeId)** → follow a user.
* **unfollow(followerId, followeeId)** → unfollow a user.

**Intuition:**
It’s basically a *feed aggregation system*. Each user has their own “timeline” (tweets), and the news feed merges tweets from all the people they follow — sorted by time.
The core challenge is **keeping it efficient** while maintaining the **correct ordering** of tweets.

---

### **2. Data Structures Used**

| Data Structure                   | Purpose                      | Intuition                                             |
| -------------------------------- | ---------------------------- | ----------------------------------------------------- |
| **`HashMap<Integer, User>`**     | Maps userId → User object    | Quick O(1) access to any user                         |
| **`User` class**                 | Holds tweets and followees   | Encapsulates user-related info cleanly                |
| **`HashSet<Integer> followees`** | Tracks whom a user follows   | Avoid duplicates, O(1) lookup                         |
| **`LinkedList<Tweet>`**          | Stores tweets (newest first) | Add tweets at head instantly                          |
| **`PriorityQueue<Tweet>`**       | Used to get latest 10 tweets | Efficient way to get most recent items using max heap |

Each **Tweet** stores:

* **id** – tweetId
* **time** – global counter (for strict ordering across all users)

---

### **3. Approach / Core Logic**

#### **Posting a Tweet**

* If user doesn’t exist, create one.
* Assign a global `timeCounter` to maintain chronological order.
* Add the new tweet to the head of their tweet list.

**Intuition:**
Adding at the head ensures tweets are always sorted by recency, so we can just look at the first few later.

---

#### **Getting News Feed**

1. Retrieve all tweets from the user and their followees.
2. Add up to **10 most recent tweets per followee** into a **max heap** (based on timestamp).
3. Pop the top 10 tweets from the heap to form the feed.

**Intuition:**
Instead of merging every tweet ever posted, we only care about the **most recent ones**.
The heap gives us a natural way to always extract the *latest* tweet efficiently — like continuously picking the freshest item from multiple sorted lists.

---

#### **Follow / Unfollow**

* **Follow:** Add `followeeId` to the user’s `followees` set.
* **Unfollow:** Remove `followeeId` (except self).

**Intuition:**
A `HashSet` gives instant membership checks — following and unfollowing is as simple as adding/removing from a set.

---

### **4. Time and Space Complexities**

| Operation                 | Time Complexity | Space Complexity | Intuition                                |
| ------------------------- | --------------- | ---------------- | ---------------------------------------- |
| `postTweet()`             | O(1)            | O(1)             | Just add to list head                    |
| `follow()` / `unfollow()` | O(1)            | O(1)             | Simple HashSet add/remove                |
| `getNewsFeed()`           | O(F log F)      | O(F)             | Merge tweets from F followees using heap |

> Since we limit to 10 tweets per followee, the heap never grows too large, keeping it practical.

---

### **5. Key Design Decisions (and Why)**

| Decision                     | Why / Intuition                                      |
| ---------------------------- | ---------------------------------------------------- |
| User follows themselves      | So their own tweets always show up in their feed     |
| Use LinkedList for tweets    | Constant-time addition at head, recent tweets first  |
| Use global timestamp         | Prevent ties between tweets and keep global ordering |
| Use PriorityQueue            | Needed to efficiently get top 10 most recent tweets  |
| Limit 10 tweets per followee | Avoids unnecessary processing of old tweets          |

---

### **6. Optimizations / Real-World Scaling**

| Idea                                            | Intuition                                                |
| ----------------------------------------------- | -------------------------------------------------------- |
| **Limit stored tweets per user** (e.g., 100)    | Keeps memory manageable                                  |
| **Use k-way merge** (like merging sorted lists) | More efficient than heap when merging multiple timelines |
| **Persist tweets in DB with timestamp index**   | Needed for large-scale systems like real Twitter         |
| **Cache feeds** for active users                | Avoid recomputing same feed repeatedly                   |

---

### **7. Common Pitfalls & Fixes**

| Pitfall                   | Fix                                        |
| ------------------------- | ------------------------------------------ |
| Forgetting to follow self | Add `followees.add(userId)` in constructor |
| Wrong tweet order         | Sort by descending timestamp               |
| Fetching too many tweets  | Limit to 10 per followee                   |
| Missing null checks       | Always `putIfAbsent()` before operations   |

---

### **8. Example Summary (1-line overview)**

> “I used a HashMap of Users, where each user stores their tweets and followees.
> For the feed, I use a max heap to get the 10 most recent tweets from all followees.
> All core operations are O(1), and fetching the feed is O(F log F).”

---

### **9. Intuitive Analogy**

Imagine each user’s tweets as a **sorted timeline** (like stacks).
To build your feed, you take the top card (latest tweet) from each stack, compare timestamps, pick the most recent, and repeat — that’s exactly what the **PriorityQueue** does here.

---
