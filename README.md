# MazeGameJava

In this game we created a maze game with different creating and solving algorithms over a client-server architecture. 
The maze is represented by bytes ( 0 or 1) while 0 is a wall and 1 mean clear path.
The maze is sent to the client compressed over java Outputstream and being decompreesed at the client side.
UI is created using JAVAFX and css. 
Class are designed to abstract and using as much interfaces as possible to achive modularity.

Maze Generators: In this package we used Dijkstra's Algorithm/BFS/DFS for creation of the maze. AMazeGerator and IMazeGenerator use as interface and abstract classes for desired implmentation
by the developers.

IO: represents the compression and decompression of the maze object. 

search: This package represents the algortihms for solve and abstract class to represent a general state in a given maze. 

Client-Server: we used compression techniques (Hexcadicemial converation and Huffman code) for sending the maze object over to client and server using JavaInput/output stream.

Model: model represents the model layer in our Controller and Handlers Architecture. UI was implemented by JAVAFX with css elements. 

Server: represents server side with prefrences for Generating and solving the maze.

View: view was is the display layer in the UI.

Hope you like it! (:
