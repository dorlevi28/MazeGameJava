package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    public void handleClient(InputStream inputStream, OutputStream outputStream) throws IOException, ClassNotFoundException {
        ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
        ObjectInputStream fromClient = new ObjectInputStream(inputStream);

        int[] dim = (int[])fromClient.readObject();

        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(dim[0], dim[1]); //Generate new maze
        try {
            // save maze to a file
            //shouldent we use Mycompressor?
            MyCompressorOutputStream send_compressed = new MyCompressorOutputStream(toClient);
            byte[] maze_rep=maze.toByteArray();
            send_compressed.write(maze_rep);
            send_compressed.flush();
            send_compressed.close();
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
