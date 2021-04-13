
package Server;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.ServerSocket;
        import java.net.Socket;

public class Server {
    private int port;//The port
    private int listeningInterval;
    private IServerStrategy serverStrategy;//The strategy for handling clients
    private volatile boolean stop;
    private int solvedMazeCount;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        //the opening of the directory saving the temp solved mazes should be initialized here?

        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        this.stop = false;
    }

    public void start(){
        new Thread(() -> {
            startServer();
        }).start();
    }

    public void startServer()
    {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);

            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();

                    new Thread(() -> {
                        clientHandle(clientSocket);
                    }).start();
                }
                catch (IOException e) {
                    System.out.println("Where are the clients??");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function receives client socket and handles it
     * @param clientSocket - The client socket
     */
    private void clientHandle(Socket clientSocket) {
        try {
            InputStream inFromClient = clientSocket.getInputStream();
            OutputStream outToClient = clientSocket.getOutputStream();
            this.serverStrategy.handleClient(inFromClient, outToClient);

            inFromClient.close();
            outToClient.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void stop()
    {
        System.out.println("The server has stopped!");
        this.stop = true;
    }

    public int getSolvedMazeCount() {
        return solvedMazeCount;
    }
}
