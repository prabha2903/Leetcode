class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minLand = Integer.MAX_VALUE;
        int minWater = Integer.MAX_VALUE;
       int m = landStartTime.length;
       int n = waterStartTime.length;
       for(int i=0;i<m;i++){
        minLand = Math.min(minLand,landStartTime[i]+landDuration[i]);
       }
       for(int j=0;j<n;j++){
        minWater = Math.min(minWater,waterStartTime[j]+waterDuration[j]);
       }
       int res = Integer.MAX_VALUE;
       
        for(int j=0;j<n;j++){
            int start = Math.max(minLand,waterStartTime[j]);
            res = Math.min(res,start+waterDuration[j]);
        }
    
        for(int i=0;i<m;i++){
            int start = Math.max(minWater,landStartTime[i]);
            res = Math.min(res,start+landDuration[i]);
        }
    
       return res;
    }
}