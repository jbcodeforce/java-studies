# maxPathSum Project

Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree.

binary - tree: node with two children and a int value

maxSum is to add sum of a path between two nodes

* a binary tree as node with two paths and a parent
* not all of the children can be present
* when selecting a leaf the tree needs to go up to the parent
* the value of the current node is part of the sum
* when the node is selected we can compute the sum of value for each path from this node and get the max.

## Use tests to validate the solution

```
mvn verify
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
quarkus dev
```
