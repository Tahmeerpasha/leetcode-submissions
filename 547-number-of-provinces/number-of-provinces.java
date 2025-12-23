class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        // 1. Create adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());

        // 2. Fill adjacency list
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }

        // 3. Visited array
        int[] vis = new int[n];
        int provinces = 0;

        // 4. Count DFS calls
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                provinces++; // DFS called → new province
                dfs(i, adjList, vis);
            }
        }

        return provinces;
    }

    public void dfs(int node, ArrayList<ArrayList<Integer>> adjLst, int[] vis) {
        vis[node] = 1;
        for (Integer nd : adjLst.get(node)) {
            if (vis[nd] == 0)
                dfs(nd, adjLst, vis);
        }
    }
}