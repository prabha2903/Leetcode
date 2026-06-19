class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int current =0,max=0;
        for(int num:gain){
            current = current+num;
            max = Math.max(max,current);
        }
        return max;
    }
}