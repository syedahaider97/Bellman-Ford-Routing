# Bellman-Ford-Routing-
A project for my Introduction to Networking course that simulates the Bellman-Ford Algorithm across a 4 Router topology.


For Part 2 of the programming assignment, I designated afsaccess1.njit.edu to be Router0, afsaccess2.njit.edu to be Router1, afsaccess3.njit.edu to be Router2, and afsaccess4.njit.edu to be Router2.
In order to run this setup, the following commands are necessary:

ON afsaccess2.njit.edu:

java Router1

On screen Router1's initial routing table should be displayed.

ON afsaccess3.njit.edu:

java Router2

On screen Router2's initial routing table should be displayed.

ON afsaccess4.njit.edu:

java Router3

On screen Router3's initial routing table should be displayed.

ON afsaccess1.njit.edu:

java Router0

On screen Router0's initial routing table should be displayed.

Following this, the flow of execution between virtual machines should begin.

The next 16 tables that are printed are the result of running the Bellman-Ford Algorithm on the given topography.

Each set of 4 in the iteration is led by a display of the given Router's current table.
The following 3 are requests for tables from neighboring Routers.

The last table, denoted by "Final Display of RouterX" is the minimum distance between a given router and all other nodes in the graph.
Following this, the sockets are closed, and the program execution terminates. 
