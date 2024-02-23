import java.util.ArrayList;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) {


//        int res_1 = climbStairs(1);
//        System.out.println(res_1);
//
//        int[] list_input_746 = new int[]{1,100,1,1,1,100,1,1,100,1};
//        int res_746 = minCostClimbingStairs(list_input_746);
//        System.out.println(res_746);

//        int res = uniquePaths(3, 7);
        int[][] input = new int[][]{{0,0,0}, {0,1,0}, {0,0,0}};

        int res = uniquePathsWithObstacles(input);
        System.out.println(res);

    }

    // 动态规划专题

    public static int climbStairs(int n) {
        if (n == 1 || n == 2){
            return n;
        }
        // 确定dp数组，dp[i]表示i阶的上法总数
        int[] dp = new int[n+1];

        // 3. 确定初始值
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++){  // 4. 确定遍历顺序
            // 2. 确定递推公式
            dp[i] = dp[i-1]+dp[i-2];
            System.out.println("dp: " + dp[i]);  // 5. 举例推导
        }
        return dp[n];

    }

    // leetcode746
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        if (n == 0 || n == 1) return 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
            System.out.printf("登陆第%d阶楼梯的花费：%d\n", i, dp[i]);
        }
        return dp[n];
    }

    // leetcode 62
    public static int uniquePaths(int m, int n) {
        // 1. 定义dp数组以及dp数组的初始值.dp[i][j]表示终点为（i， j）时的路径数量
        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        if (m == 1 || n == 1) return 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n-1];

    }


    // leetcode 63
    public static int uniquePathsWithObstacles(int[][] ob_grid) {
        // 1. 定义dp数组以及dp数组的初始值.dp[i][j]表示终点为（i， j）时的路径数量
        int m = ob_grid.length;
        int n = ob_grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m && ob_grid[i][0] == 0; i++) {
            if (ob_grid[i][0] != 1){
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n && ob_grid[0][j] == 0; j++) {
            if (ob_grid[0][j] != 1){
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (ob_grid[i][j]==0){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];

    }

}