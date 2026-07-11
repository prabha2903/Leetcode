class Solution {

    List<Integer>[] graph;
    boolean[] visited;
    int vertices;
    int edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {

        graph = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {

                vertices = 0;
                edgeCount = 0;

                dfs(i);
                edgeCount /= 2;
                if (edgeCount == vertices * (vertices - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node) {

        visited[node] = true;
        vertices++;
        edgeCount += graph[node].size();

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}