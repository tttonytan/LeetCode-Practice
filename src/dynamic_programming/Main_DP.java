import java.util.ArrayList;
import java.util.Arrays;

public class Main_DP {
    public static void main(String[] args) {
//        int res = numTrees(3);
//        System.out.println(res);
//        int[][] package_value = new int[][]{{1,1}, {1},{10}};
//        int res = maxValue(package_value);
//        System.out.println(res);
//        int[] nums = new int[]{1,5,11,5};
//        boolean res = canPartition2(nums);
//        System.out.println(5/2);
        int[] stones = new int[]{2,7,4,1,8,1};
        int res = lastStoneWeightII(stones);
        System.out.println(res);
    }
    public static int numTrees(int n){
//        1. 确定dp数组及含义
        int[] dp = new int[n+1];
//        3. 确定dp数组初始值
        dp[0] = 1;
        dp[1] = 1;
//        确定遍历顺序
        for (int i = 2; i < n+1; i++) {
            for (int j = 0; j < i; j++) {
//                2. 确定递推公式
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }
    public static int maxValue(int[][] input){
        int package_capacity = input[0][1];
        int material_num = input[0][0];

        int [] dp = new int[package_capacity+1];
        for (int i = 0; i < material_num; i++) {
            int value = input[2][i];
            int weight = input[1][i];
            for (int j = package_capacity; j >=weight; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight]+value);
            }
        }
        return dp[package_capacity];
    }

    public static boolean canPartition(int[] nums) {
        if(Arrays.stream(nums).sum() %2 != 0){
            return false;
        }
        int total_num = nums.length;
        int package_capacity = Arrays.stream(nums).sum()/2;
        int[] dp = new int[package_capacity+1];
        for (int num : nums) {
            for (int j = package_capacity; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        if (dp[package_capacity] == package_capacity){
            return true;
        }else {
            return false;
        }
    }
    public static boolean canPartition2(int[] nums){
        if(Arrays.stream(nums).sum() %2 != 0){
            return false;
        }
        int total_num = nums.length;
        int package_capacity = Arrays.stream(nums).sum()/2;
        int[][] dp = new int[total_num][package_capacity+1];
        for (int j = 0; j < package_capacity; j++) {
            if(j<nums[0]){
                dp[0][j] = 0;
            }else {
                dp[0][j] = nums[0];

            }
        }
        for (int i = 1; i < total_num; i++) {
            for (int j = 1; j <=package_capacity ; j++) {
                if(nums[i]<=j){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i]]+nums[i]);
                }
            }
        }
        return dp[total_num - 1][package_capacity] == package_capacity;
    }

    public static int lastStoneWeightII(int[] stones) {
        int total_num = stones.length;
//      和除以2 向下取整
        int sum = 0;
        for(int num : stones){
            sum += num;
        }
        int target = sum/2;

        int[] dp = new int[target + 1];
        for (int i = 0; i < total_num; i++) {
            for (int j = target; j>=stones[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-stones[i]]+stones[i]);
            }
        }
        int retain = Arrays.stream(stones).sum() - dp[target];
        return retain - dp[target];
    }
}