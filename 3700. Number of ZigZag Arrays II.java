class Solution {
    static final long MOD = 1_000_000_007L;
    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }
    private long[][] power(long[][] mat, long exp) {
        int n = mat.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, mat);
            }

            mat = multiply(mat, mat);
            exp >>= 1;
        }

        return res;
    }

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] T = new long[size][size];
        for (int x = 0; x < m; x++) {
            for (int y = x + 1; y < m; y++) {
                T[y][m + x] = 1;
            }
        }
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < x; y++) {
                T[m + y][x] = 1;
            }
        }
        long[][] P = power(T, n - 1);
        long ans = 0;
        for (int col = 0; col < size; col++) {
            long sum = 0;

            for (int row = 0; row < size; row++) {
                sum = (sum + P[row][col]) % MOD;
            }

            ans = (ans + sum) % MOD;
        }

        return (int) ans;
    }
}