class Solution {
    public int gcdOfOddEvenSums(int n) {
        int e=0,o=0;
        for(int i=1;i<=n*2;i++){
            if(i%2==0){
               e += i;
            }else{
                o += i;
            }
        }
       return gcd(o,e);
    }
    static int gcd(int a,int b){
       while(b!=0){
        int temp = b;
        b = a%b;
        a = temp;
       }
       return a;
    }
}