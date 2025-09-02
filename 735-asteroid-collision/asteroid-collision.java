class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // Optimal -> Time => O(2n) && Space => O(n)
        List<Integer> st = new ArrayList<>();
        for (int a : asteroids) {
            if (a > 0) {
                st.add(a);
            } else {
                while (!st.isEmpty() && st.get(st.size() - 1) > 0
                        && st.get(st.size() - 1) < Math.abs(a)) {
                    st.remove(st.size() - 1); // smaller right asteroids destroyed
                }

                if (!st.isEmpty() && st.get(st.size() - 1) == Math.abs(a)) {
                    st.remove(st.size() - 1); // equal size collision
                } else if (st.isEmpty() || st.get(st.size() - 1) < 0) {
                    st.add(a); // no collision
                }
            }
        }

        int[] result = new int[st.size()];
        for (int i = 0; i < st.size(); i++)
            result[i] = st.get(i);
        return result;
    }
}