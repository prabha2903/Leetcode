class Solution {
    static int[][] arr;
    static int[][] sol;
    static int n,m;
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        n = grid.size();
        m = grid.get(0).size();
        arr = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = grid.get(i).get(j);
            }
        }
        sol = new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(sol[i],-1);
        }
      return findPath(0,0,health);
    }
    boolean findPath(int i,int j,int health){
        if(i<0||j<0||i>=n||j>=m){
            return false;
        }
        health -= arr[i][j];
        if(health<=0 || sol[i][j]>=health){
            return false;
        }
        sol[i][j] = health;
         if(i==n-1 && j==m-1){
            return true;
        }
        return findPath(i-1,j,health)||
        findPath(i+1,j,health)||
        findPath(i,j-1,health)||
        findPath(i,j+1,health);
    }
}