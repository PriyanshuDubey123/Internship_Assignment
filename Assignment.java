import java.util.Scanner;

public class Assignment {

    // Approach 1: Recursion + Memoization

    static int solve(int grid[][], int i, int j, int prev,Integer dp[][]){

        if(i<0 || i == grid.length || j == grid[0].length || grid[i][j]<=prev)
            return 0;

        if(dp[i][j]!=null){
            return dp[i][j];
        }

       // Traversing All Three Possible Directions

        return dp[i][j] = 1 + Math.max(solve(grid,i+1,j+1,grid[i][j],dp),
                Math.max(solve(grid,i-1,j+1,grid[i][j],dp),
                        solve(grid,i,j+1,grid[i][j],dp)));
    }

    static int maxMovesUsingMemo(int[][] grid) {
        int ans = 0;
        Integer dp[][] = new Integer[grid.length][grid[0].length];
        for(int i = 0;i<grid.length;i++){
            ans = Math.max(ans,solve(grid,i,0,-1,dp));
        }
        return ans-1;

        // Time Complexity = O(N*M)
        // Space Complexity = O(N*M)
    }

    /*.....................................................................................*/

    //Approach 2:  Using Tabulation
    static int maxMovesUsingTab(int[][] grid) {
        int dp[][] =new int[grid.length][grid[0].length];

        for(int i = grid[0].length-2;i>=0;i--){
            for(int j = 0;j<grid.length;j++){
                int ans = 0;
                if(grid[j][i+1]>grid[j][i])
                    ans = 1+dp[j][i+1];
                if(j+1<grid.length && grid[j+1][i+1]>grid[j][i])
                    ans = Math.max(ans,1+dp[j+1][i+1]);
                if(j-1>=0 && grid[j-1][i+1]>grid[j][i])
                    ans = Math.max(ans,1+dp[j-1][i+1]);

                dp[j][i] = ans;
            }
        }
        int ans = 0;
        for(int i = 0;i<dp.length;i++)
            ans = Math.max(ans,dp[i][0]);
        return ans;

        // Time Complexity = O(N*M)
        // Space Complexity = O(N*M)

    }

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);

        System.out.print("Enter number of rows: ");

        int n = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int m = sc.nextInt();

        int [][]grid = new int[n][m];

        System.out.print("Enter the space-separated values of Grid row-wise: ");

        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                grid[i][j] = sc.nextInt();
            }
        }

        // Approach 1:
        System.out.println("Answer from Memoization Approach: "+maxMovesUsingMemo(grid));

        // Approach 2:
        System.out.println("Answer from Tabulation Approach: "+maxMovesUsingTab(grid));

    }
}
