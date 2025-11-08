## 🧠 **355. Design Twitter – Interview Notes**

### **1. Problem Statement (Goal)**

Design a simplified version of Twitter with four key operations:

* **postTweet(userId, tweetId)** → user posts a new tweet.
* **getNewsFeed(userId)** → returns the 10 most recent tweets from the user and their followees.
* **follow(followerId, followeeId)** → follow a user.
* **unfollow(followerId, followeeId)** → unfollow a user.

---

### **2. Data Structures Used**

| Data Structure                   | Purpose                                                        |
| -------------------------------- | -------------------------------------------------------------- |
| **`HashMap<Integer, User>`**     | Maps userId → User object for O(1) access.                     |
| **`User` class**                 | Holds user details and tweets.                                 |
| **`HashSet<Integer> followees`** | Stores followees to avoid duplicates and for O(1) lookup.      |
| **`LinkedList<Tweet> tweets`**   | Stores tweets in reverse chronological order (newest first).   |
| **`PriorityQueue<Tweet>`**       | Used in `getNewsFeed()` to fetch top 10 latest tweets by time. |

Each **Tweet** stores:

* `tweetId`
* `timestamp` (using global counter for ordering)

---

### **3. Approach / Core Logic**

#### **Posting a Tweet**

* If user doesn’t exist, create one.
* Add tweet to their `tweets` list with incrementing global `timeCounter`.

#### **Getting News Feed**

* Retrieve user’s own and followees’ tweets.
* Add up to 10 most recent tweets from each followee into a **max heap** based on timestamp.
* Extract the top 10 tweets from the heap for the final feed.

#### **Follow / Unfollow**

* `follow()`: Add followeeId to follower’s followee set.
* `unfollow()`: Remove followeeId (but ensure user can’t unfollow themselves).

---

### **4. Time and Space Complexities**

| Operation                 | Time Complexity                      | Space Complexity |
| ------------------------- | ------------------------------------ | ---------------- |
| `postTweet()`             | O(1)                                 | O(1)             |
| `follow()` / `unfollow()` | O(1)                                 | O(1)             |
| `getNewsFeed()`           | O(F log F) (F = number of followees) | O(F)             |

> In practice, since we limit to 10 tweets per followee, `getNewsFeed` is efficient.

---

### **5. Key Design Decisions**

* Each user **follows themselves** by default to include their own tweets in the feed.
* Use **LinkedList** so inserting new tweets is O(1) (at the head).
* Use **PriorityQueue (max heap)** for sorting tweets by recency.
* Maintain a **global time counter** to ensure chronological order across users.

---

### **6. Optimizations / Real-World Scaling**

* **Limit tweet storage** per user (e.g., last 100) to save memory.
* Use **k-way merge** of tweet lists (similar to merging sorted lists) for faster feed generation.
* Store tweets in a database and use **indexes** on timestamp for scalability.

---

### **7. Common Pitfalls**

| Pitfall                                 | Fix                                        |
| --------------------------------------- | ------------------------------------------ |
| Forgetting to follow self               | Add `followees.add(userId)` in constructor |
| Returning tweets in wrong order         | Use descending timestamp in `compareTo()`  |
| Processing too many tweets per followee | Only take 10 most recent tweets            |
| Not checking if user exists             | Always `putIfAbsent()` before operations   |

---

### **8. Example Summary (One-liner)**

> “I built a mini Twitter using HashMaps, Sets, and a PriorityQueue. Each user stores their own tweets and followees. For the news feed, I gather tweets from all followees, sort by timestamp using a heap, and return the top 10 most recent ones.”

---
