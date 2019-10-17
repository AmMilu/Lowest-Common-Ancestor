# Lowest-Common-Ancestor
This includes three part of software engineering tasks.
1. Lowest Common Ancestor - in master - using Java and test by JUnit 5
   To implement a function that can calculate the lowest common ancestor in a graph, that may be structured as a binary tree. 
2. Lowest Common Ancestor - in branch - using Java and test by JUnit 5
   To enhance the previous LCA solution, implementing as a branch that must ultimately be merged with master branch, and implementation of LCA that can work for graphs structured in directed acyclic form.

# Explanation of the solutions of this project
For the binary tree LCA, the code and tests are in file LCA.
And for the Directed Acyclic Graph LCA, the code and tests are in file LCA2.
Because they are in separated files, the merge didn't work very well. 
To run the binary tree tests in LCA2 solution, I copied and pasted the tests to the DAG tests with a bit change to fit the Graph structure.

In DAG's tests, which is in LCA2 file, there are few more functions to support the Graph structure, so a few more tests were added to test all the funtions. In each function, there are two parts, the first parts is to test the DAG created in the tests and the second part is to test the previous binary tree. Arrows are added in binary tree graph to change it into a DAG. 

# For LCA (binary tree solution) file
the tests are:
1. constructor
2. insert
3. search
4. lca

# For LCA2 (Directed Acylic Graph solution) file
the tests are:
1. constructor (for BT testTree is created, for DAG testGraph is created. both are used for all the tests)
2. contains
3. inDegree (this is used in topSort function and topSort function is to used to test if the graph has a cycle)
4. topSort
5. parents (this function is to support lca)
6. lca
