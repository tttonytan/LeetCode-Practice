import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        TreeNode<Integer> A = new TreeNode<>(1);
//        TreeNode<Integer> B = new TreeNode<>(2);
//        TreeNode<Integer> C = new TreeNode<>(2);
//        TreeNode<Integer> D = new TreeNode<>(3);
//        TreeNode<Integer> E = new TreeNode<>(4);
//        TreeNode<Integer> F = new TreeNode<>(4);
//        TreeNode<Integer> G = new TreeNode<>(3);
//
//        A.left = B;
//        A.right = C;
//        B.left = D;
//        B.right = E;
//        C.left = F;
//        C.right = G;
//
//        System.out.println(isSymertic(A));
//    }
//
//    static boolean helper(TreeNode<Integer> left, TreeNode<Integer> right){
//        if(left == null && right != null) return false;
//        else if (left != null && right == null) return false;
//        else if (left == null && right == null) return true;
//        else if (left.value!= right.value) return false;
//
//        boolean outer = helper(left.left, right.right);
//        boolean inter = helper(left.right, right.left);
//        return  outer &&inter;
//    }
//    static boolean isSymertic(TreeNode<Integer> root){
//        if (root == null) return true;
//        return helper(root.left, root.right);
//    }
//    static class TreeNode<E> {
//        E value;
//        TreeNode<E> left;
//        TreeNode<E> right;
//        TreeNode(E value){
//            this.value = value;
//        }
        //        TreeNode<Integer> A = new TreeNode<>(1);

        Leetcode.TreeNode A = new Leetcode.TreeNode(2);
        Leetcode.TreeNode B = new Leetcode.TreeNode(3);
        Leetcode.TreeNode C = new Leetcode.TreeNode(4);
        Leetcode.TreeNode D = new Leetcode.TreeNode(5);
        Leetcode.TreeNode E = new Leetcode.TreeNode(6);


        A.right = B;
        B.right = C;
        C.right = D;
        D.right = E;

//        Leetcode.Solution104 solution104 = new Leetcode.Solution104();
//        int dep = solution104.helper(A);
//        System.out.println(dep);

        Leetcode.Solution111 solution111 = new Leetcode.Solution111();
        int dep = solution111.solution(A);
        System.out.println(dep);

        Solution solution = new Solution();
        int res = solution.fib(6);
        System.out.println("res"+res);

        int res_1 = climbStairs(1);
        System.out.println(res_1);

        int[] list_input_746 = new int[]{1,100,1,1,1,100,1,1,100,1};
        int res_746 = minCostClimbingStairs(list_input_746);
        System.out.println(res_746);

        int res_343 = integerBreak(10);
        System.out.println(res_343);

    }


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

    // leetcode 343
    public static int integerBreak(int n){
        //1. 定义dp数组以及含义：dp[i]表示i拆分和得到的最大乘积
        int[] dp = new int[n+1];

        //3. 初始化
        dp[1] = 1;
        dp[2] = 1;
        if (n<=2){
            return 1;
        }
        for (int i = 3; i < n+1; i++) {
            for (int j = 1; j < Math.ceil((double) i /2); j++) {
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
            }
            System.out.println(i+ "_res_343:" + dp[i]);
        }
        return dp[n];

    }


}