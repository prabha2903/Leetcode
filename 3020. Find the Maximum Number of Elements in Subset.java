class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long,Integer> map = new HashMap<>();
        for(int num:nums){
            map.put((long)num,map.getOrDefault((long)num,0)+1);
        }
        int ans=1;
        if(map.containsKey(1L)){
            int count = map.get(1L);
            if(count%2==0){
                ans = count-1;
            }else {
                ans = count;
            }
        }
        for(long start:map.keySet()){
            if(start==1) continue;
            long current = start;
            int len=0;
            while(true){
                if(!map.containsKey(current)){
                    break;
                }
                if(map.get(current)>=2){
                    len+=2;
                    current = current * current;
                }else{
                    len++;
                    break;
                }
            }
            if(!map.containsKey(current)){
                len--;
            }
            ans = Math.max(ans,len);
        }
        return ans;
    }
}