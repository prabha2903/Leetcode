class Solution {
    public long sumAndMultiply(int n) {
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        long sum=0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
           if(c!='0'){
            sb.append(c);
            sum += c-'0';
           }
        }
        if(sb.length()==0){
            return 0;
        }
        long num = Long.parseLong(sb.toString());
        return sum*num;
    }
}