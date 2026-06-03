class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int res = Integer.MAX_VALUE;
        for(int i=0;i<landStartTime.length;i++){            
         for(int j=0;j<waterStartTime.length;j++){
        int landFinish = landStartTime[i] + landDuration[i];
        int waterBeg = Math.max(landFinish,waterStartTime[j]);
        int finish1 = waterBeg + waterDuration[j];
        res = Math.min(res,finish1);
         int waterFinish = waterStartTime[j]+waterDuration[j];
         int landBeg = Math.max(waterFinish,landStartTime[i]);
         int finish2 = landBeg + landDuration[i];
         res = Math.min(res,finish2);
        }
        }
        return res;
    }
}