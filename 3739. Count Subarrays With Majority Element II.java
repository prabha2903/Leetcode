class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int OFFSET = n + 5;
        int SIZE = 2 * OFFSET + 5;
        long[] cntB = new long[SIZE];
        int balance = OFFSET;
        cntB[balance] = 1;
        long ans = 0;
        long sum = 0;
        for (int x : nums) {
            boolean isT = (x == target);
            if (isT) {
                sum += cntB[balance];
            } else {
                sum -= cntB[balance - 1];
            }
            balance += isT ? 1 : -1;
            cntB[balance]++;
            ans += sum;
        }
        return ans;
    }
}