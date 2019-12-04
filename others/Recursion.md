# Recursion notes

## Problem 105/106 - binary tree construction

Recursion is a method solving problem from top to bottom, so in these two problems, root node of a subtree is the key target, based on the root of a subtree, left and right tree are built by calling the same function, which is the characteristic of recursion.
IMP: finding a common target for all the subproblems, and then recursion could be implemented.

## Divide-and-Conquer

I can only say that everything can be divied and conquered, excellent algorithm. A simple application like finding the maximum subarray can prove the usefulness of divider-and-conquer, the kind of thinking supports the development of recursion.





To solve problems concerning recursion, the idea of writing the recursive function is as follows( in the function):

1. dealing with the last/deepest case(**in aspect of the lowest level**)
2. calling the recursive function with 
   1. binary tree -> left/right tree
   2. graph -> all adjacent nodes of the current working one
3. dealing with the recursion results in the last step, if the return type is void, this step can be ignored.
4. return the result(**in aspect of the top level**)

## 104.Maximum Depth of Binary Tree

This is a very classical implementation of recursion. In the solution, the function has a return type, which requires a further process of the returned value in step 3.

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

