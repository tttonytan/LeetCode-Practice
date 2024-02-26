package dynamic_programming;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main_Package {
    public static void main(String[] args) {
//        int[][] package_value = new int[][]{{1,1}, {1},{10}};
//        int res = maxValue(package_value);
//        System.out.println(res);
//        int[] nums = new int[]{1,1,1,1,1};
//        int target = 3;
//        int res = findTargetSumWays(nums, target);
//        System.out.println(res);
//        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
//        String[] strs = new String[]{"10", "1", "0"};
//        int m = 1, n = 1;
//        System.out.println(findMaxForm(strs, m, n));
//        int amount = 5;
//        int amount = 2;
//        int[] coins = new int[]{1, 2, 5};
//        int[] coins = new int[]{3};
//        System.out.println(change(amount, coins));

//        int[] nums = new int[]{1,2,3};
//        int target = 4;
//        System.out.println(combinationSum4(nums, target));
//        Scanner scanner = new Scanner(System.in);
//        climb_stair(scanner);
//        int[] coins = new int[]{2,5,10,1};
//        int amount = 27;
//        System.out.println(coinChange(coins, amount));

        System.out.println(numSquares(12));
    }
    public static int maxValue(int[][] package_value) {
        int max_value = 0;
        int package_capacity = package_value[0][1];
        int material_num = package_value[0][0];
//        1. 确定dp数组及含义
        int[][] dp = new int[material_num][package_capacity+1];
        for (int w = 0; w <package_capacity; w++) {
            dp[0][w] =  package_value[2][0];
        }
        for (int i = 1; i < material_num; i++) {
            int value = package_value[2][i];
            for (int j = 1; j <=package_capacity; j++) {
                int weight = package_value[1][i];
                if (weight > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight]+value);
                }
            }
        }
        for(int[] arr: dp){
            Arrays.sort(arr);
            max_value = arr[arr.length-1];
        }
        return max_value;
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

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num: nums){
            sum += num;
        }
        if((sum+target)%2 == 1) return 0;
        if(target>sum){
            return 0;
        }
        if(target<(-sum)){
            return 0;
        }
        int package_target = (sum+target)/2;
        int[] dp = new int[package_target + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = package_target; j >= nums[i] ; j--) {
                dp[j] += dp[j-nums[i]];
//                System.out.println("==="+dp[j]);
            }
        }
        return dp[package_target];
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int content_num = strs.length;
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i < content_num; i++) {
            char[] char_i = strs[i].toCharArray();
            int zero_num = 0;
            int one_num = 0;
            for(char c: char_i){
                if(c=='0') zero_num += 1;
                if (c=='1') one_num += 1;
            }
            for (int k = m; k >=zero_num ; k--) {
                for (int j = n; j >=one_num ; j--) {
                    dp[k][j] = Math.max(dp[k][j], dp[k-zero_num][j-one_num]+1);
                }
            }
        }
        return dp[m][n];
    }

    public static int change(int amount, int[] coins) {
        int coins_num = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins_num; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j-coins[i]];
            }
        }
        return dp[amount];
    }


    public static int combinationSum4(int[] nums, int target) {
        int item_num = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 0; j <=target ; j++) {
            for (int i = 0; i < item_num; i++) {
                if(j>=nums[i]){
                    dp[j] += dp[j-nums[i]];
                }
            }
        }
        return dp[target];
    }

//  卡码57. 爬楼梯（第八期模拟笔试）
    public static void climb_stair(Scanner scanner){
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int j = 0; j <=n ; j++) {
            for (int i = 1; i <= m; i++) {
                if(j>=i) dp[j] += dp[j-i];
            }
        }
        System.out.println((dp[n]));
//        return dp[n];
    }

//  Leetcode 322. 零钱兑换
    public static int coinChange(int[] coins, int amount) {
        int coin_num = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < coin_num; i++) {
            for (int j = coins[i]; j <= amount ; j++) {
                if(dp[j-coins[i]]!=Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
            }
        }
        if(dp[amount]==Integer.MAX_VALUE){
            return -1;
        }

        return dp[amount];
    }

//  leetcode 279. 完全平方数
    public static int numSquares(int n) {
        int item_num = (int) Math.sqrt(n) + 1;

        int[] dp = new int[n+1];
        for(int index = 0; index < n+1; index ++){
            dp[index] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 1; i <item_num ; i++) {
            for (int j = i*i; j <= n; j++) {
                if(dp[j- i*i]!=Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }
//  LeetCode 139.单词拆分
//    public static boolean wordBreak(String s, List<String> wordDict) {
//        int s_length = s.length();
//        boolean[] dp = new boolean[s_length+1];
//        dp[0] = true;
//
//        for (int j = 1; j <= s_length; j++) {
//            for (int i = 0; i < j; i++) {
//                String word = s.substring(i, j-i);
//                if(wordDict.contains(word) && dp[i])
//            }
//        }
//    }
}