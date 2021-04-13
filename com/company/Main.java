package com.company;

import algorithms.mazeGenerators.*;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;


public class Main {

    public static void main(String[] args) {
	// write your code here
//        Position p1= new Position(0,0);
//        Position p2= new Position(4,4);
//
//        SimpleMazeGenerator g = new SimpleMazeGenerator();
//        Maze m = g.generate(5,5,p1,p2);
//        m.print();

          SimpleMazeGenerator smg= new SimpleMazeGenerator();
          Maze m=smg.generate(10,10);
          m.print();
          SearchableMaze sm = new SearchableMaze(m);
          BreadthFirstSearch bfs= new BreadthFirstSearch();
          Solution s = bfs.solve(sm);



          s.print();
          byte a=1;
          String s1=Integer.toString(a);
        System.out.println((byte)Integer.parseInt("11111110",2)& 0xFF);


        //RunMazeGenerator r = new RunMazeGenerator();

        //RunSearchOnMaze()


    }
}
