import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class TreeNode_MUL {
        int val;
        List<TreeNode_MUL> children_node;


        TreeNode_MUL(int val) { this.val = val; }
    }
    public static class Solution104{
       int helper(TreeNode root){
           if(root==null) return 0;
           int depth_left = helper(root.left);
           int depth_right = helper(root.right);
           return 1 + Math.max(depth_left, depth_right);
       }
    }

    public static class Solution559{
        int helper(TreeNode_MUL root){
            if(root == null) return 0;

            int depth = 0;
            if (root.children_node != null){
                for (TreeNode_MUL child: root.children_node) {
                    depth = Math.max(helper(child), depth);
                }
            }

            return depth + 1;
        }
    }

    public static class Solution111{
        public static int solution (TreeNode root){
            if (root == null) return 0;
            return helper(root);
        }
        public static int helper(TreeNode root){
            if (root == null) return 0;

            int depth_left = helper(root.left);
            int depth_right = helper(root.right);

            if (root.left == null){
                return depth_right + 1;
            }
            if (root.right == null){
                return depth_left + 1;
            }
            return Math.min(depth_left, depth_right) + 1;
        }
    }


}
