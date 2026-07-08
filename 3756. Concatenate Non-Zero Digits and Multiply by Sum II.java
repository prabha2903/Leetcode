class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();

        ArrayList<Integer> digits = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                digits.add(d);
                pos.add(i);
            }
        }

        int n = digits.size();

        int[] ans = new int[queries.length];
        if (n == 0) return ans;

        long[] prefSum = new long[n + 1];

        long[] prefNum = new long[n + 1];

        // powers of 10 modulo MOD
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;

        for (int i = 0; i < n; i++) {
            int d = digits.get(i);
            prefSum[i + 1] = prefSum[i] + d;
            prefNum[i + 1] = (prefNum[i] * 10 + d) % MOD;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        int[] positions = new int[n];
        for (int i = 0; i < n; i++) positions[i] = pos.get(i);

        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];

            int left = lowerBound(positions, l);

            int right = upperBound(positions, r) - 1;

            if (left > right) {
                ans[qi] = 0;
                continue;
            }

            long sum = prefSum[right + 1] - prefSum[left];

            int len = right - left + 1;
            long num = (prefNum[right + 1] - (prefNum[left] * pow10[len]) % MOD + MOD) % MOD;

            ans[qi] = (int) ((sum % MOD) * num % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}