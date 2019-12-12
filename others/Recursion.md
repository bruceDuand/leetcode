# Recursion notes

## Problem 105/106 - binary tree construction

Recursion is a method solving problem from top to bottom, so in these two problems, root node of a subtree is the key target, based on the root of a subtree, left and right tree are built by calling the same function, which is the characteristic of recursion.
IMP: finding a common target for all the subproblems, and then recursion could be implemented.

## Divide-and-Conquer

I can only say that everything can be divied and conquered, excellent algorithm. A simple application like finding the maximum subarray can prove the usefulness of divider-and-conquer, the kind of thinking supports the development of recursion.

**Three steps**:

1. **Divide** the problem into a number of subproblems that are smaller instances of the same problem.
2. **Conquer** the subproblems by solving them recursively, If the subproblem sizes are small enough, then, just try to solve it in a straightforward manner.
3. **Combine** the solutions to the subproblems into the the solution for the original one.



To solve problems concerning recursion, the idea of writing the recursive function is as follows( in the function):

1. dealing with the last/deepest case(**in aspect of the lowest level**)
2. calling the recursive function with 
   1. binary tree -> left/right tree
   2. graph -> all adjacent nodes of the current working one
3. dealing with the recursion results in the last step, if the return type is void, this step can be ignored.
4. return the result(**in aspect of the inner level**)

## 104. Maximum Depth of Binary Tree

This is a very classical implementation of recursion. In the solution, the function has a return type, which requires a further process of the returned value like step 3.

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int res = findMaxDepth(root, 0, 1);
        return res;
    }

    public int findMaxDepth(TreeNode node, int depth, int res) {
        if(node == null)
            return depth;
        int maxdepth1 = findMaxDepth(node.left, depth+1, res);
        int maxdepth2 = findMaxDepth(node.right, depth+1, res);
        return Math.max(maxdepth1, maxdepth2);
    }
}
```

This problem is similar to problem 110, which needs to check if the tree is a height balanced BST. The recursive function need to return the current depth for the purpose of recursio, so another global variable res is needed for storing the comparison result.

## 108. Convert Sorted Array to Binary Search Tree

Given an array where elements are sorted in ascending order, convert it to a height balanced BST, like given **[-10,-3,0,5,9]**, then form a BST.

**note**: height balanced BST means that for a single node, the height of the left tree and right tree cannot has a difference larger than 1.

Solution: from the given array, we can find that given a value, we store it in a node, then all the values that have a index from 0 to the index of cur-1 are the values stored in the left tree, values have index from cur+1 to end are stored in the right tree of the node. To let the cur node as the height balanced, the mid index is used in each loop

```java
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        TreeNode root = new TreeNode(0);
        convertToBST(nums, root, 0, nums.length - 1);
        return root;
    }

    private void convertToBST(int[] nums, TreeNode node, int minind, int maxind) {
        int mid = (minind + maxind) / 2;
        node.val = nums[mid];

        if (mid != minind) {
            TreeNode left_node = new TreeNode(0);
            node.left = left_node;
            convertToBST(nums, left_node, minind, mid - 1);
        } else
            node.left = null;

        if (mid != maxind) {
            TreeNode right_node = new TreeNode(0);
            node.right = right_node;
            convertToBST(nums, right_node, mid + 1, maxind);
        } else
            node.right = null;
        return;
    }

}
```