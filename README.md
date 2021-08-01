# RoadTrip 
  - *CS245 Data Structure & Algorithms* - final project
# Author
  Dion Feng
# Purpose 
 - Read data from multiple files and create classes to store the information
 - Calculate the shortest distance or time between two cities along with/out the attractions on the road
 - Implemented Dijkstra’s algorithm to return the shortest path
# Notice
  - User will provide: starting city, places to visit(optional), and destination.
  - Change the file directory if using in your own system (Roads.java, Places.java)
  ```
  File file = new File(".../attractions.csv");
  File file = new File(".../roads.csv");
  ... -> replace it with your directory path where you put the src data files
  ```
# Demonstration
  ```
  Name of starting city (or EXIT to quit): San Francisco CA
  Name of ending city: Medford OR
  List an attraction along the way (or ENOUGH to stop listing): enough
  =====ROUTE=====
  * San Francisco CA -> Sacramento CA
  * Sacramento CA -> Redding CA
  * Redding CA -> Medford OR
  Total cost: 400 miles
  ```
