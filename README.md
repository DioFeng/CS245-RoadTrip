# RoadTrip 
  - *CS245 Data Structure & Algorithms* - final project
# Author
  Dion Feng
# Purpose 
 - Read data from multiple files and create classes to store the information
 - Calculate the shortest distance or time between two cities along with/out the attractions on the road
 - Implemented modified Dijkstra’s algorithm to return the shortest path
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
  
  ```
  Name of starting city (or EXIT to quit): San Francisco CA
Name of ending city: Houston TX
List an attraction along the way (or ENOUGH to stop listing): Cloud Gate
List an attraction along the way (or ENOUGH to stop listing): National Dinosaur Monument
List an attraction along the way (or ENOUGH to stop listing): Point Reyes National Seashore
List an attraction along the way (or ENOUGH to stop listing): enough
=====ROUTE=====
* San Francisco CA -> Point Reyes Station CA
* Point Reyes Station CA -> Sacramento CA
* Sacramento CA -> Reno NV
* Reno NV -> Elko NV
* Elko NV -> Salt Lake City UT
* Salt Lake City UT -> Jensen UT
* Jensen UT -> Denver CO
* Denver CO -> North Platte NE
* North Platte NE -> Grand Island NE
* Grand Island NE -> Lincoln NE
* Lincoln NE -> Omaha NE
* Omaha NE -> Des Moines IA
* Des Moines IA -> Waterloo IA
* Waterloo IA -> Dyersville IA
* Dyersville IA -> Dubuque IA
* Dubuque IA -> Rockford IL
* Rockford IL -> Chicago IL
* Chicago IL -> Bloomington IL
* Bloomington IL -> Springfield IL
* Springfield IL -> St. Louis MO
* St. Louis MO -> Memphis TN
* Memphis TN -> Jackson MS
* Jackson MS -> Vicksburg MS
* Vicksburg MS -> Natchez MS
* Natchez MS -> Alexandria LA
* Alexandria LA -> Port Arthur TX
* Port Arthur TX -> Houston TX
Total cost: 3597 miles
  ```
