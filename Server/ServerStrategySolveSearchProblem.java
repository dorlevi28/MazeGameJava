package Server;


import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    public void handleClient(InputStream inputStream, OutputStream outputStream) throws IOException, ClassNotFoundException {
        ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
        ObjectInputStream fromClient = new ObjectInputStream(inputStream);

        Maze maze = (Maze)fromClient.readObject();

        //if solution already exists, need to check if we solve this maze already, if so return with solving again
        //else save the solution to the tmp directory
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        final String solved_mazes="Solved_mazes.txt";
        final String solutions="solutions.txt";

        final File maze_file=new File(tempDirectoryPath,solved_mazes);
        final File sol_file=new File(tempDirectoryPath,solutions);

        if(maze_file.createNewFile()) // if file has been created successfully
            System.out.println("Maze files created successfully");
        if(sol_file.createNewFile())
            System.out.println("Solutions files created successfully");


        ObjectInputStream readMazeObject= new ObjectInputStream(new FileInputStream(maze_file));
        //check if we solved identical maze
        boolean cont = true;
        boolean wrote=false;
        int index=0;
        while(cont){
            //while there are objects in the maze files
            if((Maze)readMazeObject.readObject() != null) {
                index++;
                Maze m=(Maze)readMazeObject.readObject();
                if (m.mazeArrayEquals(maze)) {
                    // if found identcial maze, run on all solution until you find the proper one
                    ObjectInputStream readSolution = new ObjectInputStream(new FileInputStream(sol_file));
                    Solution sol = new Solution();
                    int i=0;
                    while (i != index) {
                        //will he keep the object outside the loop?
                        sol = (Solution) readSolution.readObject();
                    }
                    try {
                        toClient.writeObject(sol);
                        wrote=true;
                        toClient.flush();
                        toClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else{
                cont = false;
            }
        }
        //if didnt find identical maze, write the maze and the solution TOGTHER!
        if(!wrote){
            Solution solution = (new BestFirstSearch()).solve(new SearchableMaze(maze));
            ObjectOutputStream write_mazes= new ObjectOutputStream(new FileOutputStream(maze_file));
            ObjectOutputStream write_solution= new ObjectOutputStream(new FileOutputStream(sol_file));
            write_mazes.writeObject(maze);
            write_solution.writeObject(solution);

            try {
                toClient.writeObject(solution);
                toClient.flush();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }






        //BufferedReader bf= new BufferedReader(new FileReader(tempDirectoryPath));


    }
}
