# TheFamilyTreeBuilder_DSA
How It Works
Add Family Members:

Define a parent and their child. Specify whether the child is the left or right child.
If the parent doesn't exist, it notifies the user.
If a child slot (left/right) is already occupied, it notifies the user.
Traversal:

Pre-order Traversal: Visits the root, then left child, then right child.
In-order Traversal: Visits the left child, then root, then right child.
Menu Options:

Add members, display the tree in two traversal orders, or exit the program.

In the context of binary trees, the terms "left child" and "right child" refer to the position of child nodes relative to their parent node. Each node in a binary tree can have at most two children:

Left Child

The left child is the node connected to the left side of its parent.
Typically used to represent one type of relationship or hierarchical order (e.g., "first child" in a family tree).
Right Child

The right child is the node connected to the right side of its parent.
Represents another type of relationship or order (e.g., "second child" in a family tree).
Visual Example
Here’s an example of a binary tree:

markdown
Copy code
       John
      /    \
   Mary     Tom
   /          \
 Bob          Anna
John is the root node (no parent).
Mary is the left child of John.
Tom is the right child of John.
Bob is the left child of Mary.
Anna is the right child of Tom.
Why Use Left and Right?
In Family Trees:

You can represent family members based on their birth order or another relationship type. For instance, Mary (left child) could be the firstborn, while Tom (right child) could be the second-born.
In Data Trees (e.g., Binary Search Tree):

The left child might store values smaller than the parent, and the right child stores values larger than the parent.
For the Family Tree Builder
When you add a child:

If the user specifies left child, the program connects the new member as the parent's left child.
If the user specifies right child, the program connects the new member as the parent's right child.
This design helps create and traverse structured hierarchies like family trees.
