class Solution {

    Long[][][][] dp;
    Long[][][][] cnt;
    char[] arr;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    long solve(long x) {

        if (x <= 0) return 0;

        arr = String.valueOf(x).toCharArray();

        int n = arr.length;

        dp = new Long[n][11][11][2];
        cnt = new Long[n][11][11][2];

        return dfs(0, 10, 10, 0, true)[1];
    }

    // {number count , waviness sum}
    long[] dfs(int pos, int prev1, int prev2,
               int started, boolean tight) {

        if (pos == arr.length) {
            return new long[]{1, 0};
        }

        if (!tight && dp[pos][prev1][prev2][started] != null) {

            return new long[]{
                cnt[pos][prev1][prev2][started],
                dp[pos][prev1][prev2][started]
            };
        }

        int limit = tight ? arr[pos] - '0' : 9;

        long totalCount = 0;
        long totalWave = 0;

        for (int d = 0; d <= limit; d++) {

            boolean ntight = tight && (d == limit);

            long[] next;

            // leading zero
            if (started == 0 && d == 0) {

                next = dfs(pos + 1, 10, 10, 0, ntight);

            } else {

                int add = 0;

                // peak or valley
                if (prev2 != 10) {

                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {

                        add = 1;
                    }
                }

                next = dfs(pos + 1, d, prev1, 1, ntight);

                totalWave += add * next[0];
            }

            totalCount += next[0];
            totalWave += next[1];
        }

        if (!tight) {

            cnt[pos][prev1][prev2][started] = totalCount;
            dp[pos][prev1][prev2][started] = totalWave;
        }

        return new long[]{totalCount, totalWave};
    }
}