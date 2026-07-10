class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int LOG = 17;
        while ((1 << LOG) <= n) LOG++;

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);

        int[] val = new int[n];
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            val[i] = nums[idx[i]];
            pos[idx[i]] = i;
        }

        int[] comp = new int[n];
        int c = 0;

        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (val[i] - val[i - 1] > maxDiff)
                c++;

            comp[i] = c;
        }

        int[][] right = new int[LOG][n];
        int[][] left = new int[LOG][n];

        int r = 0;

        for (int i = 0; i < n; i++) {

            if (r < i) r = i;

            while (r + 1 < n && val[r + 1] - val[i] <= maxDiff) {
                r++;
            }

            right[0][i] = r;
        }

        int l = 0;

        for (int i = 0; i < n; i++) {
            while (val[i] - val[l] > maxDiff)
                l++;

            left[0][i] = l;
        }

        for (int k = 1; k < LOG; k++) {

            for (int i = 0; i < n; i++) {
                right[k][i] = right[k - 1][right[k - 1][i]];
                left[k][i] = left[k - 1][left[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int u = queries[q][0];
            int v = queries[q][1];

            int a = pos[u];
            int b = pos[v];

            if (a == b) {
                ans[q] = 0;
                continue;
            }

            if (comp[a] != comp[b]) {
                ans[q] = -1;
                continue;
            }

            int dist = 0;

            if (a < b) {

                for (int k = LOG - 1; k >= 0; k--) {

                    if (right[k][a] < b) {
                        a = right[k][a];
                        dist += (1 << k);
                    }
                }

                ans[q] = dist + 1;

            } else {

                for (int k = LOG - 1; k >= 0; k--) {

                    if (left[k][a] > b) {
                        a = left[k][a];
                        dist += (1 << k);
                    }
                }

                ans[q] = dist + 1;
            }
        }

        return ans;
    }
}

