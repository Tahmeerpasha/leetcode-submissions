## 🔹 1. Infix → Postfix (Operators between operands → Operators after operands)

**Pattern:**

* Use a **stack for operators**.
* **Operands** → directly add to output.
* **Operator** → push on stack, but first pop higher/equal precedence operators from stack.
* **Left Parenthesis `(`** → push to stack.
* **Right Parenthesis `)`** → pop until `(` is found.
* At end → pop remaining operators.

👉 **Key pattern**:
`Operand → Output`
`Operator → Compare precedence → Stack`
`(` → Push
`)` → Pop until `(`

---

## 🔹 2. Infix → Prefix (Operators before operands)

**Pattern (shortcut trick):**

1. Reverse the infix expression.
2. Swap `(` with `)`.
3. Convert this to postfix.
4. Reverse the result.

---

## 🔹 3. Postfix → Infix (Operators after operands → Operators between operands)

**Pattern:**

* Use a **stack for expressions**.
* **Operand** → push.
* **Operator** → pop 2 operands (right first, then left), form `(left operator right)` and push back.
* End → stack top is full infix.

👉 **Key pattern**:
`Operand → Push`
`Operator → Pop right + left → (left op right)`

---

## 🔹 4. Prefix → Infix (Operators before operands → Operators between operands)

**Pattern:**

* Scan expression **right to left**.
* **Operand** → push.
* **Operator** → pop 2 operands (left first, then right), form `(left operator right)` and push back.
* End → stack top is full infix.

👉 **Key pattern**:
`Operand → Push`
`Operator → Pop left + right → (left op right)`
(scan right to left)

---

## 🔹 5. Postfix → Prefix

**Pattern:**

* Scan left to right.
* **Operand** → push.
* **Operator** → pop 2 operands (right first, then left), form `operator + left + right` and push back.
* End → prefix expression in stack.

👉 **Key pattern**:
`Operand → Push`
`Operator → Pop right + left → op + left + right`

---

## 🔹 6. Prefix → Postfix

**Pattern:**

* Scan right to left.
* **Operand** → push.
* **Operator** → pop 2 operands (left first, then right), form `left + right + operator` and push back.
* End → postfix expression in stack.

👉 **Key pattern**:
`Operand → Push`
`Operator → Pop left + right → left + right + op`
(scan right to left)

---
