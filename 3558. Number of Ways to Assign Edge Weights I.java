class Solution {
    static final long MOD = 1000000007L;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        int maxDepth = bfs(graph, n);

        return (int) power(2, maxDepth - 1);
    }

    private int bfs(List<Integer>[] graph, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n + 1];

        q.offer(1);
        vis[1] = true;

        int depth = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            depth++;

            for (int i = 0; i < size; i++) {
                int node = q.poll();

                for (int nei : graph[node]) {
                    if (!vis[nei]) {
                        vis[nei] = true;
                        q.offer(nei);
                    }
                }
            }
        }

        return depth;
    }

    private long power(long base, long exp) {
        long ans = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans = (ans * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return ans;
    }
}