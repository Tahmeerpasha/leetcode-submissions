## 🧠 Class Relationships — **One-Page Gist**

### 1️⃣ **Inheritance (`IS-A`)**

**Meaning:** One class is a specialized version of another.
**Rule:** Child gets parent’s behavior.

**Java:** `class Dog extends Animal`

**Key points:**

* Reuse behavior
* Strong coupling
* Use only when the relationship is **naturally hierarchical**

**UML:**
➡️ Solid line + **closed arrow**
<img width="2790" height="1032" alt="image" src="https://github.com/user-attachments/assets/f53a4a39-a7c7-4526-83e2-5d6453aced45" />

**Interview line:**

> “Inheritance represents an IS-A relationship where a subclass extends a superclass.”

---

### 2️⃣ **Association (`USES / KNOWS-A`)**

**Meaning:** One class knows about another.
**Rule:** No ownership. Both live independently.

**Java:**

```java
class Person { Car car; }
```

**Key points:**

* Long-term relationship
* Object stored as a field
* Weak coupling

**UML:**
➡️ Solid line
<img width="2790" height="1235" alt="image" src="https://github.com/user-attachments/assets/84d5bdb2-dbc8-4860-a449-09ae073abcdb" />

**Interview line:**

> “Association is a general relationship where objects interact but don’t own each other.”

---

### 3️⃣ **Aggregation (`HAS-A`, weak ownership)**

**Meaning:** Whole–part relationship, but parts can live alone.

**Example:** Team → Players

**Java:**

```java
class Team { List<Player> players; }
```

**Key points:**

* Container holds references
* Parts survive without the whole
* Still relatively loose coupling

**UML:**
◇ Open diamond → part
<img width="2790" height="1080" alt="image" src="https://github.com/user-attachments/assets/0d795071-9337-4f45-a4d4-6204c15de724" />

**Interview line:**

> “Aggregation represents a HAS-A relationship with independent lifecycles.”

---

### 4️⃣ **Composition (`HAS-A`, strong ownership)**

**Meaning:** Part **cannot exist** without the whole.

**Example:** House → Rooms

**Java:**

```java
class House { private Room room = new Room(); }
```

**Key points:**

* Strong coupling
* Whole controls lifecycle
* Destroy whole → parts gone

**UML:**
◆ Filled diamond → part
<img width="2790" height="1080" alt="image" src="https://github.com/user-attachments/assets/2b39a9d8-2b32-40a7-9fcd-10b94e9afd98" />

**Interview line:**

> “Composition is strong ownership where parts don’t exist independently.”

---

### 5️⃣ **Dependency (`USES TEMPORARILY`)**

**Meaning:** One class uses another **only for a method call**.

**Java:**

```java
void print(Printer printer)
```

**Key points:**

* Short-lived
* No field storage
* Lowest coupling

**UML:**
➡️ Dashed arrow
<img width="2920" height="1178" alt="image" src="https://github.com/user-attachments/assets/9405a685-b6e3-4d33-8817-f4c6f455ba5b" />

**Interview line:**

> “Dependency is a temporary relationship, usually via method parameters.”

---

### 6️⃣ **Realization (`IMPLEMENTS`)**

**Meaning:** Class fulfills an interface contract.

**Java:**

```java
class CreditCardPayment implements Payment
```

**Key points:**

* Behavior guarantee
* Enables polymorphism
* Core to clean architecture

**UML:**
➡️ Dashed line + closed arrow
<img width="2920" height="1178" alt="image" src="https://github.com/user-attachments/assets/30e8b9bf-1a04-4a04-8f42-76fd214c60e0" />

**Interview line:**

> “Realization occurs when a class implements an interface.”

---

## 🔥 **Most Important Comparisons (Memorize These)**

### Association vs Dependency

| Association     | Dependency       |
| --------------- | ---------------- |
| Stored as field | Passed to method |
| Long-term       | Short-term       |
| Stronger link   | Weaker link      |

---

### Aggregation vs Composition

| Aggregation    | Composition      |
| -------------- | ---------------- |
| Weak ownership | Strong ownership |
| Part survives  | Part dies        |
| Open diamond   | Filled diamond   |

---

## 🧩 **LLD Rule of Thumb**

* Prefer **composition over inheritance**
* Prefer **interfaces over concrete classes**
* Use **dependency** to reduce coupling
* Use **aggregation** for grouping
* Use **inheritance sparingly**

---

## 🧠 **One-Line Memory Hack**

> **Inheritance defines type,
> Composition owns life,
> Aggregation groups,
> Association knows,
> Dependency borrows,
> Realization promises.**

<img width="2920" height="1554" alt="image" src="https://github.com/user-attachments/assets/dbbfa020-db70-42ca-8c72-5ec9e69ce37e" />
