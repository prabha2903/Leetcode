class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        if(intervals.length==0) return 0;
        int removal=0;
        Arrays.sort(intervals,(a,b)->{
            if(a[0]==b[0]) return b[1]-a[1];
            return a[0]-b[0];
            });
        int index=0,maxEnd=0;
        while(index<intervals.length){
            int currentEnd = intervals[index][1];
            if(currentEnd<=maxEnd){
                removal++;
            }else{
                maxEnd = currentEnd;
            }
            index++;
        }
        return intervals.length-removal;
    }
}