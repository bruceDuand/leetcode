import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=98 lang=java
 *
 * [98] Validate Binary Search Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.right == null && root.left == null))
            return true;
        return checkNode(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean checkNode(TreeNode node, long minval, long maxval) {
        if(node.val <= minval || node.val >= maxval)
            return false;

        boolean leftres  = true;
        boolean rightres = true;
        if (node.left != null) {
            leftres = checkNode(node.left, minval, node.val);
        }
        if (node.right != null) {
            rightres = checkNode(node.right, node.val, maxval);
        }

        if (leftres && rightres)
            return true;
        else
            return false;
    }
}
// @lc code=end

// note 
// in these problems, it is important
// to set a global min and max value to compare the node.val
// because all the some subnodes must be able to compare with
// some ahead values
