class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        for (int v = 1; v <= m; v++) {
            up[v] = m - v;     
            down[v] = v - 1;   
        }
        for (int len = 3; len <= n; len++) {
            long[] nUp = new long[m + 1];
            long[] nDown = new long[m + 1];

            long pref = 0;
            for (int v = 1; v <= m; v++) {
                nDown[v] = pref;
                pref = (pref + up[v]) % MOD;
            }
            long suff = 0;
            for (int v = m; v >= 1; v--) {
                nUp[v] = suff;
                suff = (suff + down[v]) % MOD;
            }
            up = nUp;
            down = nDown;
        }
        long ans = 0;
        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }
        return (int) ans;
    }
}