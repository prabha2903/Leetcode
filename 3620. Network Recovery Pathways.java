class Solution {
    static class Edge {
        int to;
        long cost;

        Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<Edge>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int c = e[2];

            graph[u].add(new Edge(v, c));
            indegree[v]++;
            maxCost = Math.max(maxCost, c);
        }
        int[] topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (Edge e : graph[u]) {
                if (--indegree[e.to] == 0) {
                    q.offer(e.to);
                }
            }
        }

        int low = 0, high = maxCost;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canReach(mid, graph, topo, online, k)) {
                ans = mid;
                low = mid + 1; 
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int minEdgeCost, List<Edge>[] graph, int[] topo, boolean[] online, long k) {
        int n = online.length;
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == INF) continue;

            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (Edge e : graph[u]) {
                int v = e.to;
                long w = e.cost;

                if (w < minEdgeCost) continue;
                if (v != n - 1 && !online[v]) continue;

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}