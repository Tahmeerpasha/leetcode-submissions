class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> st = new ArrayList<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > 0)
                st.add(asteroids[i]);
            else {
                while (st.size() != 0 && st.get(st.size() - 1) >= 0
                        && st.get(st.size() - 1) < Math.abs(asteroids[i]))
                    st.remove(st.size() - 1);

                if (st.size() != 0 && st.get(st.size() - 1) == Math.abs(asteroids[i]))
                    st.remove(st.size() - 1);
                else if (st.size() == 0 || st.get(st.size() - 1) < 0)
                    st.add(asteroids[i]);
            }
        }
        int[] result = new int[st.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = st.get(i);
        return result;
    }
}