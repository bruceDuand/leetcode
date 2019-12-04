import javax.swing.tree.TreeNode;
import jdk.nashorn.internal.ir.BlockLexicalContext;

/*
 * @lc app=leetcode id=100 lang=java
 *
 * [100] Same Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if((p==null && q!=null) || (p!=null && q==null))
            return false;
        else if(p==null && q==null)
            return true;

        if(p.val==q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
            return true;
        else
            return false;
        
    }
}
// @lc code=end

