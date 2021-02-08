# Flight-Path-Finder
This Java application uses a directed graph, structured by a 2-dimensional matrix and Dijkstra's algorithm to find the optimal path between two airports.

BACKEND ALGORITHM
The app is able to read from a simple excel file, storing all flight connections as attributes of Airport entities. 
The algorithm searches through the matrix to find the shortest path between any two airports and alerts the user if there is no path from one airport to another.
The algorithm is easily upgradable to find the best path between two airports based on any number of properties such as time, price, distance, etc.
  A simple if statement to check the weight of the lines would transform this algorithm from checking an unweighted directed graph to a weighted directed graph.

FRONT END
I know it's not pretty but the backend is where the fun stuff is, I promise. Just go look at the back-end code instead :')
